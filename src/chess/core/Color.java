package chess.core;

/**
 * Represents the two possible sides in a chess game.
 * 
 * This enum is minimal - only to different btn black & white, hence the 
 * enum ensures type-safety snd avoids bugs from strings or booleans.
 * 
 * Future extensions:
 *  -Add a helper method e.g. opposite()
*/

public enum Color {
    WHITE,
    BLACK;

    /**
     * Returns the opposite color.
     * Useful for toggling turns.
     * 
     * @return Black if White; White if Black.
    */
    public Color opposite() {
        return this == WHITE ? BLACK : WHITE;
    }
    
}
