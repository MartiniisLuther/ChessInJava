package chess.core;

/**
 * Represents a coordinate on the chessboard.
 * 
 * A position stores:
 *  - row (0-7)
 *  - col (0-7)
 * 
 * This class is immutable: once created, the values cannot change.
 * This prevents accidental board corruption.
*/

public final class Position {

    private final int row;
    private final int col;

    /**
     * Creates a new board psn.
     * 
     * @param row Row index (0-7)
     * @param col Column index (0-7)
    */

    // Constructor
    public Position(int row, int col) {
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            throw new IllegalArgumentException("Position must be between 0 and 7.");
        }

        this.row = row;
        this.col = col;
    }

    /** @return the row index */
    public int getRow() {
        return row;
    }

    /** @return the column index */
    public int getCol() {
        return col;
    }

    /**
     * Positions are equal if both row & column match.
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;

        Position other = (Position) obj;
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        return row * 31 + col;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    
}
