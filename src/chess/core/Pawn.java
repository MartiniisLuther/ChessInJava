package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Pawn chess Piece.
 * <p>
 * Implements basic pawn movement: 1-square forward, 2-square initial move, and  1-square diagonal capture.
 * <p>
 * NOT handled: en passant and promotion.
 */
public class Pawn extends Piece {

    // Constructor
    public Pawn(Color color, Position position) {
        super(color, position, PieceType.PAWN);
    }

    @Override
    public List<Position> getLegalMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getCol();

        // The direction pawns move depends on their color (-1 for white, +1 for black)
        int direction = this.color.getDirection();

        // ------------------------------------------------------------
        // ---- FORWARD MOVEMENT (Non-capture) ------------------------
        // ------------------------------------------------------------
        
        int oneStepRow = row + direction;
        Position oneStepPos = new Position(oneStepRow, col);

        // Check 1: Single forward step
        if (Position.isValid(oneStepRow, col) && !board.isOccupied(oneStepPos)) {
            moves.add(oneStepPos);

            // Check 2: Double forward step (only if on starting rank AND single step is empty)
            if (!this.hasMoved) {
                int twoStepRow = row + 2 * direction;
                Position twoStepPos = new Position(twoStepRow, col);

                if (Position.isValid(twoStepRow, col) && !board.isOccupied(twoStepPos)) {
                    moves.add(twoStepPos);
                }
            }
        }

        // ------------------------------------------------------------
        // ---- DIAGONAL CAPTURES -------------------------------------
        // ------------------------------------------------------------

        // Ca[ture offsets: left and right relative to pawn's forward direction
        int[][] captureOffsets = {
            {direction, -1}, // diagonal left
            {direction, 1}   // diagonal right
        };

        // Check each diagonal capture possibility
        for (int[] offset : captureOffsets) {
            int targetRow = row + offset[0];
            int targetCol = col + offset[1];

            if (Position.isValid(targetRow, targetCol)) {
                Position targetPos = new Position(targetRow, targetCol);
                Piece target = board.getPiece(targetPos);

                // A diagonal move is legal ONLY if it is a capture
                if (isOpponent(target)) {
                    moves.add(targetPos);
                }
            }
        }

        return moves;
    }
}
