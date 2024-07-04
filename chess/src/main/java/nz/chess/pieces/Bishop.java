package nz.chess.pieces;

import javafx.scene.image.ImageView;

public class Bishop extends Piece{

    public Bishop(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public boolean isValidMove(int currentX, int currentY, int targetX, int targetY) {
        return true;
    }
}
