package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;

public abstract class Piece {
    private boolean isWhite;
    private ImageView image;
    
    public Piece(boolean isWhite, ImageView image) {
        this.isWhite = isWhite;
        this.image = image;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public ImageView getImage() {
        return image;
    }

    // Abstract method to check if a move is valid
    public abstract boolean isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board, Square[] lastMove);
}

