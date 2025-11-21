package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Pawn chess Piece.
 * 
 * Pawn rules implemented here:
 * - move 1 square forward if empty
 * - move 2 squares forward on first move if both squares are empty
 * - capture diagonally one square
 * 
 * Not handled here:
 * - en passant
 * - promotion
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

        // White moves upward (towards row 0), black downward
        int direction = (this.color == Color.WHITE) ? -1 : 1;

        int oneStepRow = row + direction;
        int twoStepRow = row + 2 * direction;

        // ---- Single forward step -----
        if (isInsideBoard(oneStepRow, col) && board.getPiece(new Position(oneStepRow, col)) == null) {

            moves.add(new Position(oneStepRow, col));

            // ---- Double step; only if unmoved && single step empty ----
            if (!this.hasMoved && isInsideBoard(twoStepRow, col) 
                && board.getPiece(new Position(twoStepRow, col)) == null) {
                    
                moves.add(new Position(twoStepRow, col));
            }
        }

        // ------ Diagonal captures -----
        // left capture
        int leftCol = col - 1;
        if (isInsideBoard(oneStepRow, leftCol)) {
            Piece target = board.getPiece(new Position(oneStepRow, leftCol));
            if (target != null && target.getColor() != this.color) {
                moves.add(new Position(oneStepRow, leftCol));
            }
        }

        // right capture
        int rightCol = col + 1;
        if (isInsideBoard(oneStepRow, rightCol)) {
            Piece target = board.getPiece(new Position(oneStepRow, leftCol));
            if (target != null && target.getColor() != this.color) {
                moves.add(new Position(oneStepRow, rightCol));
            }
        }

        return moves;
    }

    // helper for hounds checking
    private boolean isInsideBoard(int r, int c) {
        return r >= 0 && r <= 7 && c >= 0 && c <= 7;
    }
    
}
