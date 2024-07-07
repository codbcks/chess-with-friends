package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;
import nz.chess.controllers.ChessController.moveType;

public class King extends Piece{

    public King(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public moveType isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board, Square[] lastMove) {
        if (Math.abs(currentX - targetX) <= 1 && Math.abs(currentY - targetY) <= 1) {
            if (board[targetX][targetY].getPiece() == null || board[targetX][targetY].getPiece().isWhite() != isWhite()) {
                return moveType.NORMAL;
            }
        }
        
        return moveType.INVALID;
   
    }
}
