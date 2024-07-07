package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;
import nz.chess.controllers.ChessController.moveType;

public class Pawn extends Piece{

    public Pawn(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public moveType isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board, Square[] lastMove) {
        
        // If the pawn is moving forward
        if (targetX == currentX && board[targetX][targetY].getPiece() == null) {
            if (isWhite()) {
                if (currentY == 1) {
                    if (targetY == 2 || targetY == 3) {
                        return moveType.NORMAL;
                    }
                } else {
                    if (targetY == currentY + 1) {
                        return moveType.NORMAL;
                    }
                }
            } else {
                if (currentY == 6) {
                    if (targetY == 5 || targetY == 4) {
                        return moveType.NORMAL;
                    }
                } else {
                    if (targetY == currentY - 1) {
                        return moveType.NORMAL;
                    }
                }
            }
        
        // If the pawn is moving diagonally
        } else if (Math.abs(targetX - currentX) == 1 && Math.abs(targetY - currentY) == 1) {
            if (isWhite()) {
                if (targetY == currentY + 1) {
                    if (board[targetX][targetY].getPiece() != null && !board[targetX][targetY].getPiece().isWhite()) {
                        return moveType.NORMAL;
                    }
                    else if ((lastMove[0] == board[targetX][targetY+1] && lastMove[1] == board[targetX][targetY-1] && lastMove[1].getPiece() instanceof Pawn)) {
                        return moveType.EN_PASSANT;
                    }
                }
            } else {
                if (targetY == currentY - 1) {
                    if (board[targetX][targetY].getPiece() != null && board[targetX][targetY].getPiece().isWhite()) {
                        return moveType.NORMAL;
                    }
                    else if (lastMove[0] == board[targetX][targetY-1] && lastMove[1] == board[targetX][targetY+1] && lastMove[1].getPiece() instanceof Pawn) {
                        return moveType.EN_PASSANT;
                    }
                }
            }
        }
        return moveType.INVALID;
    }
}
