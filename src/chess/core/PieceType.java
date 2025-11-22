package chess.core;

/**
 * Represents the different types of chess pieces.
 * 
 * This enum allows for database to refer to each piece type safely - avoiding
 * errors from arbitrary strings or integers.
 * 
 * Future extensions:
 * - Add piece-specific metadat e.g. starting value for evaluation
 */

public enum PieceType {
    KING,
    QUEEN,
    ROOK,
    BISHOP,
    KNIGHT,
    PAWN;
}
