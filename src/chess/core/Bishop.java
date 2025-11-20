package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Bishop chess piece.
 * 
 * Bishops move diagonally any number of squares until blocked.
 * They can't jump over pieces.
 * 
 * This class implements only mov't generation.
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
        int row = position.getRow();
        int col = position.getCol();

        // For each diagonal direction
        for (int[] dir : DIRECTIONS) {
            int dRow = dir[0];
            int dCol = dir[1];

            int newRow = row + dRow;
            int newCol = col + dCol;

            // Slide until edge or piece collision
            while (newRow >= 0 && newRow <= 7 && newCol >= 0 && newCol <= 7) {
                Position newPos = new Position(newRow, newCol);
                Piece target = board.getPiece(newPos);

                if (target == null) {
                    moves.add(newPos); // empty sqaure
                } else {
                    if (target.getColor() != this.color) {
                        moves.add(newPos); // capture
                    }
                    break; // stop sliding when any piece is hit
                }

                newRow += dRow;
                newCol += dCol;
            }
        }
        return moves;
    }
}
