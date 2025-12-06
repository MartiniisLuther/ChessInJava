package chess.core;

/**
 * Represents the side (White or Black).
 * Contains logic for turn switching and pawn movement direction.
*/

public enum Color {
    WHITE(-1), // Moves "up" the array (indices -> 0)
    BLACK(1); // Moves "down" the array (indices -> 7)

    public final int direction;

    // Constructor
    Color(int direction) {
        this.direction = direction;
    }

    /**
     * @return The opposite color.
    */
    public Color opposite() {
        return this == WHITE ? BLACK : WHITE;
    }

    /**
     * @return -1 for SHITE (moving up rows), 1 for BLACK (moiving down rows).
     */
    public int getDirection() {
        return direction;
    }
    
}
