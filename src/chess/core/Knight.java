package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Knight chess piece.
 * 
 * Knights move in L - shape.
 * - 2 squares in one direction, 1 perpendicular
 * 
 * Mov't is unaffected by blocking pieces - knights can "jump".
 * 
 * This class implements only raw mov't generation, without checking 
 * for check conditions. That logic will be handled later at the game level
 */

public class Knight extends Piece {
    
    // L-shaped move offsets
    private static final int[][] MOVE_OFFSETS = {
        {-2, -1}, {-2, 1},
        {-1, -2}, {-1,2},
        {1, -2}, {1, 2},
        {2, -1}, {2, 1}
    };

    public Knight(Color color, Position position) {
        super(color, position, PieceType.KNIGHT);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = position.getRow();
        int col = position.getCol();

        for (int[] offset : MOVE_OFFSETS) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];

            // Check board bounds
            if (newRow < 0|| newRow > 7 || newCol < 0 || newCol > 7) {
                continue; //
            }

            Position newPos = new Position(newRow, newCol);
            Piece target = board.getPiece(newPos);

            // Knight can move onto empty or opponent piece
            if (target == null || target.getColor() != this.color) {
                moves.add(newPos);
            }
        }
        return moves;
    }
}
