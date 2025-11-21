package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Queen chess piece.
 * 
 * The Queen combines the mov't of both the Rook & Bishop:
 * - horizontal & vertical sliding
 * - diagonal sliding
 * 
 * The Queen cannot jump over pieces.
 * 
 * This class generates raw movement only.
 * King-safety checks to be handled by game controller.
 */

public class Queen extends Piece {
    
    // Rook + Bishop movements
    private static final int[][] DIRECTIONS = {
        // Rook-like directions
        {-1, 0}, {1, 0}, {0, -1}, {0, 1},
        // Bishop-like directions
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    // Constructor
    public Queen(Color color, Position position) {
        super(color, position, PieceType.QUEEN);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = position.getRow();
        int col = position.getCol();

        // Sliding movement in 8 directions
        for (int[] dir : DIRECTIONS) {
            int dRow = dir[0];
            int dCol = dir[1];

            int newRow = row + dRow;
            int newCol = col + dCol;

            while (newRow >= 0 && newRow <= 7 && newCol >0 && newCol <= 7) {
                Position newPos = new Position(newRow, newCol);
                Piece target = board.getPiece(newPos);

                if (target == null) {
                    moves.add(newPos);
                } else {
                    if (target.getColor() != this.color) {
                        moves.add(newPos); // capture allowed
                    }
                    break; // stop sliding upon collision
                }

                newRow += dRow;
                newCol += dCol;
            }
        }

        return moves;
    }
}
