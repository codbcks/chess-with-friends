package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;

public class Knight extends Piece{

    public Knight(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public boolean isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board) {

        // Check if the target square is occupied by piece of the same color
        if (board[targetX][targetY].getPiece() != null && board[targetX][targetY].getPiece().isWhite() == isWhite()) {
            return false;
        } else {
            return (Math.abs(currentX - targetX) == 2 && Math.abs(currentY - targetY) == 1) || 
            (Math.abs(currentX - targetX) == 1 && Math.abs(currentY - targetY) == 2);
        }
    }
}
