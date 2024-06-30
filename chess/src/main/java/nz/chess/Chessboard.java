package nz.chess;

import nz.chess.pieces.Piece;

public class Chessboard {
    private static Chessboard instance;
    private Piece[][] board;

    private Chessboard() {
        board = new Piece[8][8];
    }

    public static Chessboard getInstance() {
        if (instance == null) {
            instance = new Chessboard();
        }
        return instance;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }
}
