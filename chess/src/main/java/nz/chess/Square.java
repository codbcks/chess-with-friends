package nz.chess;

import javafx.scene.layout.StackPane;
import nz.chess.pieces.Piece;

public class Square extends StackPane {
    private int x, y;
    private Piece piece;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
        setPrefSize(100, 100);
        setOnMouseClicked(event -> handleClick());
    }

    // Handle click event
    private void handleClick() {
        System.out.println("Square clicked: " + x + ", " + y);
        System.out.println("Piece: " + piece);
    }

    // Update the display based on the piece
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}