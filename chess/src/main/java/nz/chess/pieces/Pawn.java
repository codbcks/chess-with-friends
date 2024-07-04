package nz.chess.pieces;

import javafx.scene.image.ImageView;

public class Pawn extends Piece{

    public Pawn(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public boolean isValidMove(int currentX, int currentY, int targetX, int targetY) {
        if (isWhite()) {
            if (currentY == 1) {
                return (targetY == 2 && currentX == targetX) || (targetY == 3 && currentX == targetX);
            } else {
                return targetY == currentY + 1 && currentX == targetX;
            }
        } else {
            if (currentY == 6) {
                return (targetY == 5 && currentX == targetX) || (targetY == 4 && currentX == targetX);
            } else {
                return targetY == currentY - 1 && currentX == targetX;
            }
        }
    }
}
