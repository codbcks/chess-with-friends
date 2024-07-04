package nz.chess.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import nz.chess.Square;
import nz.chess.pieces.Bishop;
import nz.chess.pieces.King;
import nz.chess.pieces.Knight;
import nz.chess.pieces.Pawn;
import nz.chess.pieces.Queen;
import nz.chess.pieces.Rook;

public class ChessController {
    
    @FXML
    private AnchorPane boardPane;

    public static Square selectedSquare = null;
    public static boolean isWhiteTurn = true;

    // White pieces
    @FXML private ImageView waPawn;
    @FXML private ImageView wbPawn;
    @FXML private ImageView wcPawn;
    @FXML private ImageView wdPawn;
    @FXML private ImageView wePawn;
    @FXML private ImageView wfPawn;
    @FXML private ImageView wgPawn;
    @FXML private ImageView whPawn;
    @FXML private ImageView waRook;
    @FXML private ImageView whRook;
    @FXML private ImageView wbKnight;
    @FXML private ImageView wgKnight;
    @FXML private ImageView wcBishop;
    @FXML private ImageView wfBishop;
    @FXML private ImageView wQueen;
    @FXML private ImageView wKing;

    // Black pieces
    @FXML private ImageView baPawn;
    @FXML private ImageView bbPawn;
    @FXML private ImageView bcPawn;
    @FXML private ImageView bdPawn;
    @FXML private ImageView bePawn;
    @FXML private ImageView bfPawn;
    @FXML private ImageView bgPawn;
    @FXML private ImageView bhPawn;
    @FXML private ImageView baRook;
    @FXML private ImageView bhRook;
    @FXML private ImageView bbKnight;
    @FXML private ImageView bgKnight;
    @FXML private ImageView bcBishop;
    @FXML private ImageView bfBishop;
    @FXML private ImageView bQueen;
    @FXML private ImageView bKing;

    public void initialize() {

        // Initialize the chess board
        double squareSize = 100.0;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square square = new Square(x, y);
                square.setLayoutX(x * squareSize);
                square.setLayoutY((7 - y) * squareSize);
                square.setPrefSize(squareSize, squareSize);
                boardPane.getChildren().add(square);
            }
        }

        // Set the initial pieces

        // White pawns
        for (int x = 0; x < 8; x++) {
            getSquare(x, 1).setPiece(new Pawn(true, (ImageView) boardPane.lookup("#w" + (char) (x + 97) + "Pawn")));
        }

        // Black pawns
        for (int x = 0; x < 8; x++) {
            getSquare(x, 6).setPiece(new Pawn(false, (ImageView) boardPane.lookup("#b" + (char) (x + 97) + "Pawn")));
        }

        // Set initial rooks
        getSquare(0, 0).setPiece(new Rook(true, waRook));
        getSquare(7, 0).setPiece(new Rook(true, whRook));
        getSquare(0, 7).setPiece(new Rook(false, baRook));
        getSquare(7, 7).setPiece(new Rook(false, bhRook));

        // Set initial knights
        getSquare(1, 0).setPiece(new Knight(true, wbKnight));
        getSquare(6, 0).setPiece(new Knight(true, wgKnight));
        getSquare(1, 7).setPiece(new Knight(false, bbKnight));
        getSquare(6, 7).setPiece(new Knight(false, bgKnight));

        // Set initial bishops
        getSquare(2, 0).setPiece(new Bishop(true, wcBishop));
        getSquare(5, 0).setPiece(new Bishop(true, wfBishop));
        getSquare(2, 7).setPiece(new Bishop(false, bcBishop));
        getSquare(5, 7).setPiece(new Bishop(false, bfBishop));

        // Set initial queens
        getSquare(3, 0).setPiece(new Queen(true, wQueen));
        getSquare(3, 7).setPiece(new Queen(false, bQueen));

        // Set initial kings
        getSquare(4, 0).setPiece(new King(true, wKing));
        getSquare(4, 7).setPiece(new King(false, bKing));
    }

    // What to do when a square is clicked
    public static void handleSquareClick(Square square) {
        // Select the square if it has a piece
        if (selectedSquare == null && square.getPiece() != null) {
            if (isWhiteTurn == square.getPiece().isWhite()) {
                selectedSquare = square;
                square.highlight();
            }

        // Move the piece if a square is selected
        } else if (selectedSquare != null && selectedSquare != square) {
            if (selectedSquare.getPiece().isValidMove(selectedSquare.getX(), selectedSquare.getY(), square.getX(), square.getY())) {
                selectedSquare.getPiece().getImage().relocate(square.getLayoutX()+10, square.getLayoutY()+10);
                selectedSquare.movePiece(square);
                selectedSquare.unhighlight();
                selectedSquare = null;
                isWhiteTurn = !isWhiteTurn;
            } else {
                selectedSquare.unhighlight();
                selectedSquare = null;
            }

        // Unselect the square if it is clicked again
        } else if (selectedSquare != null && selectedSquare == square) {
            selectedSquare.unhighlight();
            selectedSquare = null;
        }

        System.out.println("Square clicked: " + square.getX() + ", " + square.getY());
        System.out.println("Piece: " + square.getPiece());
        System.out.println("Selected square: " + selectedSquare);
    }

    private Square getSquare(int x, int y) {
        for (javafx.scene.Node node : boardPane.getChildren()) {
            if (node instanceof Square) {
                Square square = (Square) node;
                if (square.getX() == x && square.getY() == y) {
                    return square;
                }
            }
        }
        return null;
    }
}