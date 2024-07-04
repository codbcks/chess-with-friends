package nz.chess.pieces;

import javafx.scene.image.ImageView;

public class Knight extends Piece{

    public Knight(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public boolean isValidMove(int currentX, int currentY, int targetX, int targetY) {
        return true;
    }
}
