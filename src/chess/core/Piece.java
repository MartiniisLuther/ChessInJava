package chess.core;

import java.util.List;
/**
 * Abstract base class for all chess pieces.
 * 
 * Each piece stores:
 * - its color
 * - its current board position
 * - its piece type (King, Queen, etc)
 * - a hasMoved flag (used for rules like castling & pawn mov't).
 * 
 * Subclasses (King, Queen, Rook, etc) will implement the getLegalMoves()
 * method to provide movement logic.
 * 
 * This class doesn't contain rendering code or GUI logic.
 * It belongs to model layer.
*/

public abstract class Piece {
    
    protected final Color color;
    protected Position position;
    protected final PieceType type;
    protected boolean hasMoved;
    
    /**
     * Creates a new piece instance.
     * 
     * @param color the piece color.
     * @param position the starting board position
     * @param type the type of this piece i.e., King, Queen, etc
    */

    public Piece(Color color, Position position, PieceType type) {
        this.color = color;
        this.position = position;
        this.type = type;
        this.hasMoved = false;
    }

    /** @return the piece's color */
    public Color getColor() {
        return color;
    }

    /** @return the piece's current board position */
    public Position getPosition() {
        return position;
    }

    /** Updates the piece's board position */
    public void setPosition(Position newPos) {
        this.position = newPos;
    }

    /** @return the piece type King, Queen, etc */
    public PieceType getType() {
        return type;
    }

    /** @return true if the piece has moved at least once*/
    public boolean hasMoved() {
        return hasMoved;
    }

    /** Sets the mov't flag (used for castling, pawn logic) */
    public void setHasMoved(boolean moved){
        this.hasMoved = moved;
    }

    /**
     * Each piece type must define its legal moves.
     * The ChessBoard model will pass itself in for reference.
     * 
     * @param board the current game board.
     * @param list of legal destination squares.
    */
    public abstract List<Position> getLegalMoves(ChessBoard board);

    @Override
    public String toString() {
        return color + " " + type + " @ " + position;
    }


}
