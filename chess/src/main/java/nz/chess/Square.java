package nz.chess;

import javafx.scene.layout.StackPane;
import nz.chess.controllers.ChessController;
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
        ChessController.handleSquareClick(this);
    }

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

    public void movePiece(Square targetSquare) {
        targetSquare.setPiece(piece);
        this.piece = null;
    }

    // Set a border for the selected square
    public void highlight() {
        setStyle("-fx-border-color: red; -fx-border-width: 5px;");
    }

    // Remove the border from the selected square
    public void unhighlight() {
        setStyle("");
    }
}