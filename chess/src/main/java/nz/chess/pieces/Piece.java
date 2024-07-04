package nz.chess.pieces;

import javafx.scene.image.ImageView;

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

    public abstract boolean isValidMove(int currentX, int currentY, int targetX, int targetY);
}

