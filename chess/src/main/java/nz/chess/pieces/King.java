package nz.chess.pieces;

import javafx.scene.image.ImageView;

public class King extends Piece{

    public King(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public boolean isValidMove(int currentX, int currentY, int targetX, int targetY) {
        return Math.abs(currentX - targetX) <= 1 && Math.abs(currentY - targetY) <= 1;
    }
}
