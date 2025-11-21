package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the King chess piece.
 * 
 * King movement:
 * - 1 square in any direction (8 possible squares).
 * 
 * Castling is NOT implemented yet. That requires:
 * - rook unmoved
 * - king unmoved
 * - path empty
 * - path not under attack
 * 
 * This class provides only the raw one-step movement.
 */

public class King extends Piece {
    
    // Offsets for all 8 directions
    private static final int[][] OFFSETS = {
        {-1, -1}, {-1, 0}, {-1, 1},
        { 0, -1},          { 0, 1},
        { 1, -1}, { 1, 0}, { 1, 1}
    };

    //  Constructor
    public King(Color color, Position position) {
        super(color, position, PieceType.KING);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = position.getRow();
        int col = position.getCol();

        for (int[] off : OFFSETS) {
            int newRow = row + off[0];
            int newCol = col + off[1];

            // bounds check
            if (newRow < 0 || newRow > 7 || newCol < 0 || newCol > 7) {
                continue; 
            }

            Position newPos = new Position(newRow, newCol);
            Piece target = board.getPiece(newPos);

            // King can move to empty or enemy piece square
            if (target == null || target.getColor() != this.color) {
                moves.add(newPos);
            }
        }

        return moves;
    }
}
