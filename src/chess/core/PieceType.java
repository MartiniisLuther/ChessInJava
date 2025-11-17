package chess.core;

/**
 * Represents the different types of chess pieces.
 * 
 * This enum allows for doebase to refer to each piece type safely - avoiding
 * errors from arbitrary strings or integers.
 * 
 * Future extensions:
 * - Add piece-speacific metadat e.g. starting value for evaluation
 */

public enum PieceType {
    KING,
    QUEEN,
    ROOK,
    BISHOP,
    KNIGHT,
    PAWN;
}
