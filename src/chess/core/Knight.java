package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Knight chess piece.
 * <p>
 * Knights move in an 'L' shape (two squares in one cardinal direction, 
 * then one square perpendicular). Knights are unique because they can "jump" over other pieces.
 */
public class Knight extends Piece {
    
    // A;; 8 possible "L-shaped" move offsets
    private static final int[][] MOVE_OFFSETS = {
        {-2, -1}, {-2, 1},
        {-1, -2}, {-1,2},
        {1, -2}, {1, 2},
        {2, -1}, {2, 1}
    };

    /**
     * Constrcuts a Knight piece.
     * @param color The color of the piece.
     * @param position The starting position of the piece.
     */
    public Knight(Color color, Position position) {
        super(color, position, PieceType.KNIGHT);
    }

    /**
     * Calculates all raw legal destination squares for the Knight.
     * <p>
     * Raw moves do not account for King safety (leaving King in check).
     * @param board The current state of the game board.
     * @return A list of positions the Knight can move to.
     */
    @Override
    public List<Position> getLegalMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = position.getRow();
        int col = position.getCol();

        for (int[] offset : MOVE_OFFSETS) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];

            // 1. Check if the target square is within board bounds
            if (Position.isValid(newRow, newCol)) {

                Position newPos = new Position(newRow, newCol);
                Piece target = board.getPiece(newPos);

                // 2. Knight can move onto empty or capture am opponent piece
                if (target == null || isOpponent(target)) {
                    moves.add(newPos);
                }
            }
        }
        return moves;
    }
}
