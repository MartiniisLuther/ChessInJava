package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Rook chess piece.
 * 
 * Rooks move any number of squares horizontally or vertically.
 * Mov't is blocked by any piece in the path.
 * 
 * This class handles only basic mov't generation.
 * King safety, check rules, and rook castling to be added later.
*/
public class Rook extends Piece {
    // Mov't directions: up, down, left, right
    private static final int[][] DIRECTIONS = {
        {-1, 0}, //up
        {1, 0}, // down
        {0, -1}, //left
        {0, 1} // right
    };

    // Constructor
    public Rook(Color color, Position position) {
        super(color, position, PieceType.ROOK);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = position.getRow();
        int col = position.getCol();

        // Explore eaach sliding direction
        for (int[] dir : DIRECTIONS) {
            int dRow = dir[0];
            int dCol = dir[1];

            int newRow = row + dRow;
            int newCol = col + dCol;

            // Slide until we hit boundary or piece
            while (newRow >= 0 && newRow <= 7 && newCol >= 0 && newCol <= 7) {
                Position newPos = new Position(newRow, newCol);
                Piece target = board.getPiece(newPos);

                if (target == null) {
                    // Empty square -> legal move
                    moves.add(newPos);
                } else {
                    // Occupied square
                    if (target.getColor() != this.color) {
                        // Opponent piece -> capture
                        moves.add(newPos);
                    }
                    // Stop sliding after any piece
                    break;
                }

                // Continue sliding in the same direction
                newRow += dRow;
                newCol = dCol;
            }
        }
        return moves;
    }
}
