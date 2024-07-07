package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;
import nz.chess.controllers.ChessController.moveType;

public class Knight extends Piece{

    public Knight(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public moveType isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board, Square[] lastMove) {

        // Check if the target square is occupied by piece of the same color
        if (board[targetX][targetY].getPiece() != null && board[targetX][targetY].getPiece().isWhite() == isWhite()) {
            return moveType.INVALID;
        } else {
            if ((Math.abs(currentX - targetX) == 2 && Math.abs(currentY - targetY) == 1) || 
            (Math.abs(currentX - targetX) == 1 && Math.abs(currentY - targetY) == 2)) {
                return moveType.NORMAL;
            }
        }
        return moveType.INVALID;
    }
}
