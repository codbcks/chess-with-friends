package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;
import nz.chess.controllers.ChessController.moveType;

public class Rook extends Piece{

    public Rook(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public moveType isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board, Square[] lastMove) {
        if (currentX == targetX || currentY == targetY) {

            // Check if there are any blocking pieces
            if (currentX == targetX) {
                for (int y = Math.min(currentY, targetY) + 1; y < Math.max(currentY, targetY); y++) {
                    if (board[currentX][y].getPiece() != null) {
                        return moveType.INVALID;
                    }
                }
            } else {
                for (int x = Math.min(currentX, targetX) + 1; x < Math.max(currentX, targetX); x++) {
                    if (board[x][currentY].getPiece() != null) {
                        return moveType.INVALID;
                    }
                }
            }
            if (board[targetX][targetY].getPiece() == null || board[targetX][targetY].getPiece().isWhite() != isWhite()) {
                return moveType.NORMAL;
            }
        }

        return moveType.INVALID;
    }
}
