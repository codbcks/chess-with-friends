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
        // Select the square if it has a piece
        if (ChessController.getSelectedSquare() == null && piece != null) {
            ChessController.setSelectedSquare(this);
            highlight();

        // Move the piece if a square is selected
        } else if (ChessController.getSelectedSquare() != null && ChessController.getSelectedSquare() != this) {
            ChessController.getSelectedSquare().movePiece(this);
            ChessController.getSelectedSquare().unhighlight();
            ChessController.setSelectedSquare(null);

        // Unselect the square if it is clicked again
        } else if (ChessController.getSelectedSquare() != null && ChessController.getSelectedSquare() == this) {
            ChessController.getSelectedSquare().unhighlight();
            ChessController.setSelectedSquare(null);
        }

        System.out.println("Square clicked: " + x + ", " + y);
        System.out.println("Piece: " + piece);
        System.out.println("Selected square: " + ChessController.getSelectedSquare());
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