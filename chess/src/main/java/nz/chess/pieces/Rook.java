package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;

public class Rook extends Piece{

    public Rook(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public boolean isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board) {
        if (currentX == targetX || currentY == targetY) {
            
            // Check if there are any blocking pieces
            if (currentX == targetX) {
                for (int y = Math.min(currentY, targetY) + 1; y < Math.max(currentY, targetY); y++) {
                    if (board[currentX][y].getPiece() != null) {
                        return false;
                    }
                }
            } else {
                for (int x = Math.min(currentX, targetX) + 1; x < Math.max(currentX, targetX); x++) {
                    if (board[x][currentY].getPiece() != null) {
                        return false;
                    }
                }
            }
            return board[targetX][targetY].getPiece() == null || board[targetX][targetY].getPiece().isWhite() != isWhite();
        } else {
            return false;
        }
    }
}
