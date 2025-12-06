package chess.core;

/**
 * Enumeration of the chess piece types.
 * Includes standard point values and short identifiers.
 */
public enum PieceType {
    KING("K", 1000),    // King value is arbitrary/infinite
    QUEEN("Q", 9),
    ROOK("R", 5),
    BISHOP("B", 3),
    KNIGHT("N", 3),     // Standard notation uses 'N' for knight
    PAWN("P", 1);

    private final String shortName;
    private final int value;

    // Constructor
    PieceType(String shortName, int value) {
        this.shortName = shortName;
        this.value = value;
    }

    public String getShortName() {
        return shortName;
    }

    public int getValue() {
        return value;
    }
}
