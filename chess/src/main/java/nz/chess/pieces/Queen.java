package nz.chess.pieces;

import javafx.scene.image.ImageView;

public class Queen extends Piece{

    public Queen(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public boolean isValidMove(int currentX, int currentY, int targetX, int targetY) {
        return currentX == targetX || currentY == targetY || Math.abs(currentX - targetX) == Math.abs(currentY - targetY);
    }
}
