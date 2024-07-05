package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;

public class Rook extends Piece{

    public Rook(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public boolean isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board) {
        return currentX == targetX || currentY == targetY;
    }
}
