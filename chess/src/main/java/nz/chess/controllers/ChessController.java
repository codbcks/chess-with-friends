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
    public static Square[] lastMove = new Square[2]; // [0] is the starting square, [1] is the target square
    public static boolean isWhiteTurn = true;
    private static Square[][] board = new Square[8][8];

    public enum moveType {
        NORMAL, CASTLE, EN_PASSANT, PROMOTION, INVALID
    }

    // White pieces
    @FXML private ImageView waPawn, wbPawn, wcPawn, wdPawn, wePawn, wfPawn, wgPawn, whPawn;
    @FXML private ImageView waRook, whRook, wbKnight, wgKnight, wcBishop, wfBishop, wQueen, wKing;

    // Black pieces
    @FXML private ImageView baPawn, bbPawn, bcPawn, bdPawn, bePawn, bfPawn, bgPawn, bhPawn;
    @FXML private ImageView baRook, bhRook, bbKnight, bgKnight, bcBishop, bfBishop, bQueen, bKing;

    public void initialize() {

        // Initialize the chess board
        double squareSize = 100.0;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square square = new Square(x, y);
                square.setLayoutX(x * squareSize);
                square.setLayoutY((7 - y) * squareSize);
                square.setPrefSize(squareSize, squareSize);
                board[x][y] = square;
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

        // If a square is already selected
        } else if (selectedSquare != null && selectedSquare != square) {

            // Check if the move is valid
            if (selectedSquare.getPiece().isValidMove(selectedSquare.getX(), selectedSquare.getY(), square.getX(), square.getY(),
             board, lastMove) == moveType.NORMAL) {
                movePiece(square);
                lastMove[0] = selectedSquare;
                lastMove[1] = square;
                selectedSquare.unhighlight();
                selectedSquare = null;
                isWhiteTurn = !isWhiteTurn;

            // Check if the move is a castle
            } else if (selectedSquare.getPiece().isValidMove(selectedSquare.getX(), selectedSquare.getY(), square.getX(), square.getY(),
            board, lastMove) == moveType.CASTLE) {

                // TODO castle or sumin

            // Check if the move is a castle    
            } else if (selectedSquare.getPiece().isValidMove(selectedSquare.getX(), selectedSquare.getY(), square.getX(), square.getY(),
            board, lastMove) == moveType.EN_PASSANT) {

                // TODO enpassant or sumin

            // Check if the move is a promotion
            } else if (selectedSquare.getPiece().isValidMove(selectedSquare.getX(), selectedSquare.getY(), square.getX(), square.getY(),
            board, lastMove) == moveType.PROMOTION) {
                
                // TODO promotion or sumin

            // Select the new square if it has a piece of the same color
            } else if (square.getPiece() != null && square.getPiece().isWhite() == selectedSquare.getPiece().isWhite()) {
                selectedSquare.unhighlight();
                selectedSquare = square;
                square.highlight();

            // Unselect the square if the move is invalid
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

    private static boolean isInCheck(boolean isWhite) {
        Square kingSquare = findKing(isWhite);

        // Check if any of the opponent's pieces can attack the king
        for (Square[] row : board) {
            for (Square square : row) {
                if (square.getPiece() != null && square.getPiece().isWhite() != isWhite) {
                    if (square.getPiece().isValidMove(square.getX(), square.getY(), kingSquare.getX(), kingSquare.getY(), board,
                     lastMove) != moveType.INVALID) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Find the square for the king of given color
    private static Square findKing(boolean isWhite) {
        for (Square[] row : board) {
            for (Square square : row) {
                if (square.getPiece() instanceof King && square.getPiece().isWhite() == isWhite) {
                    return square;
                }
            }
        }
        return null;
    }

    // Get the square at the given coordinates
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

    private static void movePiece(Square targetSquare) {
        
        // Remove the piece at target square if it exists
        if (targetSquare.getPiece() != null) {
            targetSquare.getPiece().getImage().setVisible(false);
        }

        // Move the selected piece to the target square
        selectedSquare.getPiece().getImage().relocate(targetSquare.getLayoutX()+10, targetSquare.getLayoutY()+10);
        targetSquare.setPiece(selectedSquare.getPiece());
        selectedSquare.setPiece(null);
    }
}