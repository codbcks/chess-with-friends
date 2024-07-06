package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;

public class Pawn extends Piece{

    public Pawn(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public boolean isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board, Square[] lastMove) {
        
        // If the pawn is moving forward
        if (targetX == currentX && board[targetX][targetY].getPiece() == null) {
            if (isWhite()) {
                if (currentY == 1) {
                    return targetY == 2 || targetY == 3;
                } else {
                    return targetY == currentY + 1;
                }
            } else {
                if (currentY == 6) {
                    return targetY == 5 || targetY == 4;
                } else {
                    return targetY == currentY - 1;
                }
            }
        
        // If the pawn is moving diagonally
        } else if (Math.abs(targetX - currentX) == 1 && Math.abs(targetY - currentY) == 1) {
            if (isWhite()) {
                return targetY == currentY + 1 && 
                ((board[targetX][targetY].getPiece() != null && !board[targetX][targetY].getPiece().isWhite()) ||
                (lastMove[0] == board[targetX][targetY+1] && lastMove[1] == board[targetX][targetY-1] &&
                lastMove[1].getPiece() instanceof Pawn)); // The last condition is for en passant
            } else {
                return targetY == currentY - 1 && 
                ((board[targetX][targetY].getPiece() != null && board[targetX][targetY].getPiece().isWhite()) ||
                (lastMove[0] == board[targetX][targetY-1] && lastMove[1] == board[targetX][targetY+1] &&
                lastMove[1].getPiece() instanceof Pawn)); // The last condition is for en passant
            }
        } else {
            return false;
        }
    }
}
