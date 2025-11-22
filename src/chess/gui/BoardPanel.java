package chess.gui;

import chess.core.ChessBoard;
import chess.core.Piece;
import chess.core.Position;
import java.awt.*;
import javax.swing.*;

/**
 * The BoardPanel class represents the graphical chessboard component.
 * It is responsible for rendering the chessboard and pieces.
 * 
 * Responsibilities:
 * - Draw the chessboard grid.
 * - Display chess pieces on the board.
 * - Handle user interactions (e.g. piece selection, movement).
 * 
 * Future improvements:
 * - Implement piece dragging and dropping.
 * - Highlight valid moves.
 * - Connect to game logic for move validation.
 */

public class BoardPanel extends JPanel {
    // Board dimensions
    private static final int ROWS = 8;
    private static final int COLS = 8;

    // 2D array to hold TilePanels
    private TilePanel[][] tiles = new TilePanel[ROWS][COLS];

    public static final ChessBoard modelBoard = ChessGUI.getModalChessBoard();

    /**
     * Constructor to build the 8x8 grid.
    */
    public BoardPanel() {
        setLayout(new GridLayout(ROWS, COLS));
        initializeBoard(); //
    }

    /**
     * Initializes the chessboard squares.
     * Alternates colors to create the classic chessboard pattern.
    */
    private void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                Color tileColor = (row + col) % 2 == 0 
                    ? new Color(240, 217, 181) 
                    : new Color(181, 136, 99);
                
                //
                TilePanel tile = new TilePanel(tileColor, new Position(row, col));
                tiles[row][col] = tile; //store a reference
                add(tile); // add to the gui
            }
        }
        refreshBoard();
    }

    //
    public void refreshBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Position pos = new Position(row, col);
                Piece piece = modelBoard.getPiece(pos);
                tiles[row][col].setPieceIcon(piece);
                tiles[row][col].repaint();
            }
        }
    }
}
