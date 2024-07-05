package nz.chess.pieces;

import javafx.scene.image.ImageView;
import nz.chess.Square;

public class Queen extends Piece{

    public Queen(boolean isWhite, ImageView image) {
        super(isWhite, image);
    }
    
    @Override
    public boolean isValidMove(int currentX, int currentY, int targetX, int targetY, Square[][] board) {

        // If moving like a rook
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

        // If moving like a bishop
        } else if (Math.abs(currentX - targetX) == Math.abs(currentY - targetY)) {
            
            // Check if there are any blocking pieces
            int xDir = currentX < targetX ? 1 : -1;
            int yDir = currentY < targetY ? 1 : -1;
            for (int i = 1; i < Math.abs(currentX - targetX); i++) {
                if (board[currentX + i * xDir][currentY + i * yDir].getPiece() != null) {
                    return false;
                }
            }
            return board[targetX][targetY].getPiece() == null || board[targetX][targetY].getPiece().isWhite() != isWhite();
        } else {
            return false;
        }
    }
}
