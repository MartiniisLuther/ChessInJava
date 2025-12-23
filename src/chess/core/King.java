package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a King chess piece.
 * <p>
 * Implements basic one-step movement and checks for the conditions required for castling.
 * King safety (being in check) is handled by the ChessBoard/Game Logic.
 */
public class King extends Piece {
    
    // Offsets for all 8 directions
    private static final int[][] OFFSETS = {
        {-1, -1}, {-1, 0}, {-1, 1},
        { 0, -1},          { 0, 1},
        { 1, -1}, { 1, 0}, { 1, 1}
    };

    /**
     * Constructs a King piece.
     * @param color The color of the piece.
     * @param position The starting position.
     */
    public King(Color color, Position position) {
        super(color, position, PieceType.KING);
    }

    /**
     * Calculates all raw legal destination squares, including castling moves.
     * @param board The current state of the game board.
     * @return A list of position the King can move to.
     */
    @Override
    public List<Position> getLegalMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = position.getRow();
        int col = position.getCol();

        // 1. Standard one-step movement
        for (int[] off : OFFSETS) {
            int newRow = row + off[0];
            int newCol = col + off[1];

            if (Position.isValid(newRow, newCol)){
                Position newPos = new Position(newRow, newCol);
                Piece target = board.getPiece(newPos);

                // Can move to empty or enemy piece square
                if (target == null || isOpponent(target)) {
                    // NOTE: This does not check for self-check yet (done at Board level)
                    moves.add(newPos);
                }
            }
        }

        // 2. Castling Checks
        addCastlingMoves(board, moves);

        return moves;
    }

    /**
     * Adds castling moves (King-side and Queen-side) if they are legal based on
     * piece positions and movement history.
     */
    private void addCastlingMoves(ChessBoard board, List<Position> moves) {
        // Castling requires King and Rook to be unmoved.
        if (this.hasMoved()) {
            return;
        }

        // Castling logic relies on the King's starting postion (e1 for white, e8 for black)
        // Row 7 (index) is White's back rank. Row 0 is Black's back rank.
        int backRank = (this.color == Color.WHITE) ? 7 : 0;

        // Ensure King is actually at its starting square (e1 or e8)
        if (position.getRow() != backRank || position.getCol() != 4) {
            return;
        }

        // --- King-side Castling (Short Castling) ---
        // Positions: f1/f8 (col 5), g1/g8 (col 6), h1/h8 (Rook, col 7)
        if (canCastle(board, backRank, 0, 1, 3)) {
            // 0 = Rook Col, 1,2,3 = empty path
            // Target square for King is c1/c8
            moves.add(new Position(backRank, 2));
        }
    }

    /**
     * Helper method to verify general castling conditions.
     * @param board The board state.
     * @param backRank The row (0 or 7).
     * @param rookCol The column of the Rook (0 or 7).
     * @param startPathCol The first column to check for emptiness (1 for Q-side, 5 for K-side).
     * @param endPathCol The last column to check for emptiness (3 for Q-side, 6 for K-side).
     * @return True if the path is clear and the Rook is unmoved.
     */
    private boolean canCastle(ChessBoard board, int backRank, int rookCol, int startPathCol, int endPathCol) {

        // 1. Check if the Rook exists and has not moved
        Position rookPos = new Position(backRank, rookCol);
        Piece rook = board.getPiece(rookPos);

        if (!(rook instanceof Rook) || rook.hasMoved() || rook.getColor() != this.color) {
            return false;
        }

        // 2. Check if the path between King and Rok is empty
        // Note: The loop needs to handle both directions (0..7 and 7..0)
        int step = (rookCol > position.getCol()) ? 1 : -1;

        // Iterate through all squares between King (col 4) and Rook
        for (int col = position.getCol() + step; col != rookCol; col += step) {
            Position pathPos = new Position(backRank, col);
            if (board.isOccupied(pathPos)) {
                return false; // Path is blocked
            }

            // 3. CRUCIAL CHECK: The squares the King passes throguh cannot be attacked.
            // We will NEED to implement ChessBoard.isSquareAttacked(Position, Color) next.
            // For now, assume this method exists and handles the check safety:
            if (board.isSquareAttacked(pathPos, this.color.opposite())) {
                return false; // Path is under attack
            }
        }

        // 4. Also check the King's target square (handled in the path loop for K-side, but explicitly for Q-side):
        // King target: c-file (col 2) for long, g-file (col 6) for short
        int kingTargetCol = (rookCol == 7) ? 6 : 2;
        Position kingTargetPos = new Position(backRank, kingTargetCol);

        // The King's target square must not be attacked either
        if (board.isSquareAttacked(kingTargetPos, this.color.opposite())) {
            return false;
        }

        // The King must also not be in* check before castling
        if (board.isKingInCheck(this.color)) {
            return false;
        }

        return true;
    }
}
