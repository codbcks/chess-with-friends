package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;
import nz.chess.controllers.ChessController.moveType;

public class Bishop extends Piece{

    public Bishop(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public moveType isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board, Square[] lastMove) {
        if (Math.abs(currentX - targetX) == Math.abs(currentY - targetY)) {

            // Check if there are any blocking pieces
            int xDir = currentX < targetX ? 1 : -1;
            int yDir = currentY < targetY ? 1 : -1;
            for (int i = 1; i < Math.abs(currentX - targetX); i++) {
                if (board[currentX + i * xDir][currentY + i * yDir].getPiece() != null) {
                    return moveType.INVALID;
                }
            }
            if (board[targetX][targetY].getPiece() == null || board[targetX][targetY].getPiece().isWhite() != isWhite()) {
                return moveType.NORMAL;
            }
        }
        return moveType.INVALID;
    }
}
