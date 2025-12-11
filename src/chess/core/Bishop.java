package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Bishop chess piece.
 * <p>
 * Bishops moove diagonally any number of squares until blocked.
 */
public class Bishop extends Piece {
    
    // Diagonal direction offsets: NW, NE, SW, SE
    private static final int[][] DIRECTIONS = {
        {-1, -1}, //up-left
        {-1, 1},  // up-right
        {1, -1},  // down-left
        {1, 1}    // down-right
    };

    // Constructor
    public Bishop(Color color, Position position) {
        super(color, position, PieceType.BISHOP);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();

        // use the utility method defined in abstract Piece class
        addSlidingMoves(board, moves, DIRECTIONS);
        
        return moves;
    }
}
