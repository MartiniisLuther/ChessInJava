package chess.core;

import java.util.List;
/**
 * Abstract base class for all chess pieces.
 * 
 * Contains fundamental properties (color, Position, Type, hasMoved)
 * and shared utility methods for move generation.
 */
public abstract class Piece {
    
    protected final Color color;
    protected Position position;
    protected final PieceType type;
    protected boolean hasMoved;
    
    /**
     * Creates a new piece instance.
     * @param color The piece color (White or Black).
     * @param position The starting board position.
     * @param type The type of this piece i.e., King, Queen, etc
     */
    public Piece(Color color, Position position, PieceType type) {
        this.color = color;
        this.position = position;
        this.type = type;
        this.hasMoved = false;
    }

    /** @return The piece's color. */
    public Color getColor() {
        return color;
    }

    /** @return The piece's current board position. */
    public Position getPosition() {
        return position;
    }

    /** Updates the piece's board position */
    public void setPosition(Position newPos) {
        this.position = newPos;
    }

    /** @return The piece type (King, Queen, etc). */
    public PieceType getType() {
        return type;
    }

    /** @return True if the piece has moved at least once. */
    public boolean hasMoved() {
        return hasMoved;
    }

    /** Sets the movement flag (used for castling, pawn logic). */
    public void setHasMoved(boolean moved){
        this.hasMoved = moved;
    }

    /**
     * Crucial Shared logic:** Calculatesand adds all valid moves
     * for sliding pieces (Rok, Bishop, Queen) in the give directions.
     * <p>
     * This method handles board boundaries, piece blocking, and capture logic.
     * 
     * @param board The current game board state.
     * @param moves The list to populate with calculated postions.
     * @param directions 2D array of {row_offset, col_offset} defining slide path.
     */
    protected void addSlidingMoves(ChessBoard board, List<Position> moves, int[][] directions){
        int startRow = position.getRow();
        int startCol = position.getCol();

        for (int[] dir : directions) {
            int dRow = dir[0];
            int dCol = dir[1];

            int newRow = startRow + dRow;
            int newCol = startCol + dCol;

            // Slide in this direction until blocked or edge
            while (Position.isValid(newRow, newCol)) {
                Position newPos = new Position(newRow, newCol);
                Piece target = board.getPiece(newPos);

                if (target == null) {
                    moves.add(newPos); // Empty square, keep sliding
                } else {
                    if (target.getColor() != this.color) {
                        moves.add(newPos); // Capture opponent piece, then stop
                    }
                    break; // Blocked by any piece
                }

                newRow += dRow;
                newCol += dCol;
            }
        }
    }

    /**
     * Checks if piece at the target position is an opponent's piece.
     * @param target The piece to check.
     * @preturn True if target is non-null and of opposite color.
     */
    protected boolean isOpponent(Piece target) {
        return target != null && target.getColor() != this.color;
    }

    /**
     * Each piece type must define its raw, legal movement paths.
     * Note: This method generates only the physical moves, it does NOT check
     * if the move places the KING in check. That's for ChessBoard to handle.
     * @param board The current game board state.
     * @return List of legal target positions for this piece.
     */
    public abstract List<Position> getLegalMoves(ChessBoard board);

    @Override
    public String toString() {
        return color.toString().charAt(0) + " " + type.getShortName() + position.toAlgebraic();
    }


}
