package chess.core;


/**
 * Represents the entire 8x8 chessboard and stores all piece position.
 * 
 * Responsibilities:
 * - Hold an 8x8 grid of Piece references.
 * - initialize and reset starting positions.
 * - Provide safe access to pieces (get/set).
 * - Perform basic board validation (e.g., inside bounds)
 * 
 * This class contains NO rendering or GUI logic.
 * It is the central Model class in the MVC architecture.
 */

public class ChessBoard {

    private final Piece[][] board; // 8x8 grid of pieces

    /**
     * Creates a new empty chessboard and populates it with 
     * the standard starting layout.
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

        // Place black major pieces : Row 0
        board[0][0] = new Rook(Color.BLACK, new Position(0, 0));
        board[0][1] = new Knight(Color.BLACK, new Position(0, 1));
        board[0][2] = new Bishop(Color.BLACK, new Position(0, 2));
        board[0][3] = new Queen(Color.BLACK, new Position(0, 3));
        board[0][4] = new King(Color.BLACK, new Position(0, 4));
        board[0][5] = new Bishop(Color.BLACK, new Position(0, 5));
        board[0][6] = new Knight(Color.BLACK, new Position(0, 6));
        board[0][7] = new Rook(Color.BLACK, new Position(0, 7));

        // Black pawns (row 1)
        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn(Color.BLACK, new Position(1, col));
        }

        // White major pieces :  7
        board[7][0] = new Rook(Color.WHITE, new Position(7, 0));
        board[7][1] = new Knight(Color.WHITE, new Position(7, 1));
        board[7][2] = new Bishop(Color.WHITE, new Position(7, 2));
        board[7][3] = new Queen(Color.WHITE, new Position(7, 3));
        board[7][4] = new King(Color.WHITE, new Position(7, 4));
        board[7][5] = new Bishop(Color.WHITE, new Position(7, 5));
        board[7][6] = new Knight(Color.WHITE, new Position(7, 6));
        board[7][7] = new Rook(Color.WHITE, new Position(7, 7));

        // White pawns row 6
        for (int col = 0; col < 8; col++) {
            board[6][col] = new Pawn(Color.WHITE, new Position(6, col));
        }
    }

    /**
     * Removes all pieces from the board.
     * Called internally before reinitialization.
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
     * @param piece - the piece to place
     * @param pos - the target position
     */
    public void setPiece(Piece piece, Position pos) {
        if (piece != null) {
            piece.setPosition(pos);
        }
        board[pos.getRow()][pos.getCol()] = piece;
    }

    /** 
     * Moves a piece from source to destination.
     * No legality checking performed here yet.
     * 
     * @param from the piece's current posn
     * @param to the desired target position
    */
    public void movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        board[to.getRow()][to.getCol()] = piece;
        board[(from.getRow())][from.getCol()] = null;

        if (piece != null) {
            piece.setPosition(to);
            piece.setHasMoved(true);
        }
    }

    /**
     * @return true if a square has a piece
     */
    public boolean isOccupied(Position pos) {
        return getPiece(pos) != null;
    }

    /**
     * Returns a human-readable representation of the chess board with row indices.
     * Each square is shown as "--" for empty or the first character of the piece type followed by a space (e.g. "K ").
     * @return formatted multi-line string showing rows 0–7 and their contents
     */
    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder("ChessBoard:\n");
        for (int row = 0; row < 8; row++) {
            sBuilder.append(row).append(": ");
            for (int col = 0; col < 8; col++) {
                sBuilder.append(board[row][col] == null ? "-- " : board[row][col].getType().toString().charAt(0) + " ");
            }
            sBuilder.append("\n");
        }
        return sBuilder.toString();
    }

    // Store turns - who plays next
    private chess.core.Color currentTurn = chess.core.Color.WHITE;
    public chess.core.Color getCurrentTurn() {
        return currentTurn;
    }

    // switch turns
    public void switchTurn() {
        currentTurn = (currentTurn == chess.core.Color.WHITE) ? chess.core.Color.BLACK : chess.core.Color.WHITE;
    }


}
