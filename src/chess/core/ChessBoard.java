package chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * The central Model class. Manages the 8x8 grid, tracks piece positions,
 * and evalutes game-state logic (like Checks and Attacks).
 */
public class ChessBoard {

    private final Piece[][] board; // 8x8 grid of pieces
    private Color currentTurn = Color.WHITE;

    /**
     * Creates a new empty chessboard and populates it with the standard starting layout.
    */
    public ChessBoard() {
        board = new Piece[8][8];
        setupStartingPosition();
    }

    /**
     * Places all pieces in their standard starting layout.
     * This method clears the board first.
    */
    public void setupStartingPosition() {
        clearBoard();

        // Black pieces
        setupRank(0, Color.BLACK);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Color.BLACK, new Position(1, i));
        }

        //White Pieces
        setupRank(7, Color.WHITE);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Color.WHITE, new Position(6, i));
        }
    }

    //
    private void setupRank(int row, Color color) {
        board[row][0] = new Rook(color, new Position(row, 0));
        board[row][1] = new Knight(color, new Position(row, 1));
        board[row][2] = new Bishop(color, new Position(row, 2));
        board[row][3] = new Queen(color, new Position(row, 3));
        board[row][4] = new King(color, new Position(row, 4));
        board[row][5] = new Bishop(color, new Position(row, 5));
        board[row][6] = new Knight(color, new Position(row, 6));
        board[row][7] = new Rook(color, new Position(row, 7));
    }

    /**
     * Removes all pieces from the board.
     * Called internally before re-initialization.
    */
    private void clearBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null;
            }
        }
    }

    /**
     * Retrieves the piece at a given position.
     * 
     * @param pos a valid Position object
     * @return the piece located there, or null if empty
    */
    public Piece getPiece(Position pos) {
        return board[pos.getRow()][pos.getCol()];
    }

    /**
     * Places a piece on a specific square.
     * This overwrites whatever was on that square.
     * 
     * @param piece The piece to place
     * @param pos The target position
     */
    public void setPiece(Piece piece, Position pos) {
        if (piece != null) {
            piece.setPosition(pos);
        }
        board[pos.getRow()][pos.getCol()] = piece;
    }

    /** 
     * Executes a move on the board.
     * Handles standard moves, caaptures, and special logic like Castling.
     */
    public void movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        if (piece == null) return;

        // --- Special Logic: Castling ---
        if (piece instanceof King && Math.abs(to.getCol() - from.getCol()) == 2) {
            executeCastlingMove((King) piece, from, to);
        } else {
            // Standard Move/Capture
            setPiece(null, from);
            setPiece(piece, to);
        }

        piece.setHasMoved(true);
    }

    /**
     * Helper to move the a Rook when a King castles
     * @param pos
     * @return
     */
    private void executeCastlingMove(King king, Position from, Position to) {
        int row = from.getRow();
        boolean isKingSide = to.getCol() > from.getCol();

        int rookFromCol = isKingSide ? 7 : 0;
        int rookToCol = isKingSide ? 5 : 3;

        Position rookFrom = new Position(row, rookFromCol);
        Position rookTo = new Position(row, rookToCol);
        Piece rook = getPiece(rookFrom);

        // Move King
        setPiece(null, from);
        setPiece(king, to);

        // Move Rook
        setPiece(null, rookFrom);
        setPiece(rook, rookTo);
        if (rook != null) rook.setHasMoved(true);
    }

    /**
     * Checks if a square is being attacked by any piece of the specified color.
     * Crucial for King safety and Castling rules.
     * @param pos
     * @return atackerColor
     */
    public boolean isSquareAttacked(Position pos, Color attackerColor) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board[r][c];
                if (piece != null && piece.getColor() == attackerColor) {
                    // Get raw moves for this piece
                    // Note: This could cause recursion if not careful,
                    // but since we only check physical paths, it works.
                    List<Position> moves = piece.getLegalMoves(this);
                    if (moves.contains(pos)) return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the King of the specified color is in check.
     * @param color
     * @return true if the King of the specified color is in check
     */
    public boolean isKingInCheck(Color color) {
        Position kingPos = findKing(color);
        if (kingPos == null) return false; // Should not happen in real chess
        return isSquareAttacked(kingPos, color.opposite());
    }

    /**
     * Finds the position of the King of the specified color.
     * @param color The color of the King to find
     * @return the Position of the King, or null if not found
     */
    private Position findKing(Color color) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board[r][c];
                if (p instanceof King && p.getColor() == color) {
                    return p.getPosition();
                }
            }
        }
        return null;
    }

    /**
     * Helper to check if a square is occupied.
     * @param pos The position to check
     * @return true if the square is occupied, false otherwise
     */
    public boolean isOccupied(Position pos) {
        return getPiece(pos) != null;
    }

    // Get color of current turn
    public Color getCurrentTurn() {
        return currentTurn; 
    }

    // Switch turn to the other player
    public void switchTurn() {
        currentTurn = currentTurn.opposite();
    }

    /**
     * Generates a string representation of the board for debugging.
     * Empty squares are represented by '.'.
     * @return A multi-line string showing the board state.
     */
    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder(" a b c d e f g h\n");
        for (int row = 0; row < 8; row++) {
            sBuilder.append(8 -row).append(" ");
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                sBuilder.append(piece == null ? ". " : piece.getType().getShortName() + " ");
            }
            sBuilder.append(8 - row).append("\n");
        }
        sBuilder.append(" a b c d e f g h\n");
        return sBuilder.toString();
    }

    /**
     * Filters pseudo-legal moves to return only moves that are 100% legal.
     * A move is legal only if it does not leave the player's own King in check.
     * @param piece The piece to check moves for.
     * @return A list of positions the piece can safely move to.
     */
    public List<Position> getValidMoves(Piece piece) {
        List<Position> pseudoLegalMoves = piece.getLegalMoves(this);
        List<Position> legalMoves = new ArrayList<>();

        for (Position targetPos : pseudoLegalMoves) {
            if (isMoveLegal(piece.getPosition(), targetPos)) {
                legalMoves.add(targetPos);
            }
        }
        return legalMoves;
    }

    /**
     * Simulates a move to see if it would put/leave the King in check.
     * This follows the "Try-Check-Undo" pattern.
     * @param from The starting position of the piece.
     * @param to The target position of the piece.
     * @return true if the move is legal, false if it leaves the King in check
     */
    private boolean isMoveLegal(Position from, Position to) {
        Piece movingPiece = getPiece(from);
        Piece capturedPiece = getPiece(to); // Save in case we need to undo a capture
        Color sideColor = movingPiece.getColor();

        // 1. Physically simulate the move
        board[to.getRow()][to.getCol()] = movingPiece;
        board[from.getRow()][from.getCol()] = null;
        Position originalPos = movingPiece.getPosition();
        movingPiece.setPosition(to);

        // 2. Check of the King is safe
        boolean kingSafe = !isKingInCheck(sideColor);

        // 3. Undo the move (restore state)
        board[from.getRow()][from.getCol()] = movingPiece;
        board[to.getRow()][to.getCol()] = capturedPiece;
        movingPiece.setPosition(originalPos);
        
        return kingSafe;
    }

}
