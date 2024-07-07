package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;
import nz.chess.controllers.ChessController;
import nz.chess.controllers.ChessController.moveType;

public class King extends Piece{

    private boolean hasMoved;

    public King(boolean isWhite, ImageView image) {
        super(isWhite, image);
        this.hasMoved = false;
    }
    
    @Override
    public moveType isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board, Square[] lastMove) {
        if (Math.abs(currentX - targetX) <= 1 && Math.abs(currentY - targetY) <= 1) {
            if (board[targetX][targetY].getPiece() == null || board[targetX][targetY].getPiece().isWhite() != isWhite()) {
                return moveType.NORMAL;
            }
        }

        if (!hasMoved && currentY == targetY && Math.abs(currentX - targetX) == 2) {
            int direction = (targetX - currentX) / 2;
            if (isValidCastle(currentX, currentY, direction, board)) {
                return moveType.CASTLE;
            }
        }

        return moveType.INVALID;
    }

    private boolean isValidCastle(int x, int y, int direction, Square[][] board) {
        int rookX = (direction == -1) ? 0 : 7;
        Piece rook = board[rookX][y].getPiece();
        if (rook instanceof Rook && !((Rook) rook).hasMoved()) {
            // Check if inbetween there are any blocking pieces or if the king is in check
            for (int i = 1; i < Math.abs(rookX - x); i++) {
                if (board[x + i * direction][y].getPiece() != null) {
                    return false;
                }
            }

            return !ChessController.isInCheck(isWhite()) && !ChessController.isInCheckAfterMove(x, y, x + direction, y, isWhite()) && !ChessController.isInCheckAfterMove(x, y, x + 2 * direction, y, isWhite());
        }
        return false;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
