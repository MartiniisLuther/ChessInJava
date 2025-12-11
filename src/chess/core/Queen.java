package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Queen chess piece.
 * <p>
 * The Queen combines movements of the Rook and Bishop.
 */
public class Queen extends Piece {
    
    // All 8 directions (Rook-like + Bishop-like)
    private static final int[][] DIRECTIONS = {
        // Horizontal/Vertical (Rook-like) directions
        {-1, 0}, {1, 0}, {0, -1}, {0, 1},
        // Diagonal (Bishop-like) directions
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    // Constructor
    public Queen(Color color, Position position) {
        super(color, position, PieceType.QUEEN);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        
        // Use the utility method defined in the abstract Piece class
        addSlidingMoves(board, moves, DIRECTIONS);

        return moves;
    }
}
