package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Rook chess piece.
 * <p>
 * Rooks move any number of squarees vertically or horizontally until blocked.
 */
public class Rook extends Piece {

    // Movementt directions: up, down, left, right
    private static final int[][] DIRECTIONS = {
        {-1, 0}, // up
        {1, 0},  // down
        {0, -1}, // left
        {0, 1}   // right
    };

    // Constructor
    public Rook(Color color, Position position) {
        super(color, position, PieceType.ROOK);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();

        // Use the utility method defined in the abstract Piece class
        addSlidingMoves(board, moves, DIRECTIONS);
        
        return moves;
    }
}
