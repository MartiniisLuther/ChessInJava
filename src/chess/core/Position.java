package chess.core;

import java.util.Objects;

/**
 * Immutabke representation of a coordinate on the chess board.
 * 
 * Coordinate System:
 * - Row 0 = Top (black starting side)
 * - Row 7 = Bottom (white starting side)
 * - Col 0 = Left ('a' file)
 * - Col 7 = Right ('h' file)
*/

public final class Position {

    private final int row;
    private final int col;

    /**
     * Creates a new position using 0-based array indices.     * 
     * @param row 0 to 7
     * @param col 0 to 7
     * @throws IllegalArgumentException if row or col are out of bounds
    */

    // Constructor
    public Position(int row, int col) {
        if(!isValid(row, col)) {
            throw new IllegalArgumentException("Position out of bounds: (" + row + ", " + col + ")");
        }
        this.row = row;
        this.col = col;
    }

    public Position(String algebraic) {
        if (algebraic == null || algebraic.length() != 2) {
            throw new IllegalArgumentException("Invalid algebraic notation: " + algebraic);
        }
        // 'a' is column 0
        this.col = algebraic.charAt(0) - 'a';
        // '8' is row 0
        this.row = 8 - Character.getNumericValue(algebraic.charAt(1));

        if (!isValid(this.row, this.col)) {
            throw new IllegalArgumentException("Position out of bounds: " + algebraic);
        }
    }

    /**
     * Checks if coordinates fall within the 8x8 board.
     * Useful for move generation logic to avoid exceptions.
     */
    public static boolean isValid(int row, int col) {
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;
    }

    /** @return the row index */
    public int getRow() { return row; }
    /** @return the column index */
    public int getCol() { return col; }

    /**
     * Converts the position to standard chess notation (e.g., "e4").
     */
    public String toAlgebraic() {
        char file = (char) ('a' + col);
        int rank = 8 - row;
        return "" + file + rank;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;
        Position other = (Position) obj;
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")[" + toAlgebraic() + "]";
    }

    
}
