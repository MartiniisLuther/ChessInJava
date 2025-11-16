package chess.gui;

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

    /**
     * Constructor to build the 8x8 grid.
    */
    public BoardPanel() {
        setLayout(new GridLayout(ROWS, COLS));
        initializeBoard(); //
        loadStartingPieces();
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
                TilePanel tile = new TilePanel(tileColor);
                tiles[row][col] = tile; //store a reference
                add(tile); // add to the gui
            }
        }
    }

    /**
     * Loads the starting pieces onto the board.
     * This is a placeholder implementation; actual piece placement
     * should be based on game logic.
    */
    private void loadStartingPieces() {
        // Black pieces
        tiles[0][0].setPieceIcon("black-rook.png");
        tiles[0][1].setPieceIcon("black-knight.png");
        tiles[0][2].setPieceIcon("black-bishop.png");
        tiles[0][3].setPieceIcon("black-queen.png");
        tiles[0][4].setPieceIcon("black-king.png");
        tiles[0][5].setPieceIcon("black-bishop.png");
        tiles[0][6].setPieceIcon("black-knight.png");
        tiles[0][7].setPieceIcon("black-rook.png");

        for (int col = 0; col < 8; col++) {
            tiles[1][col].setPieceIcon("black-pawn.png");
        }

        // White pieces
        tiles[7][0].setPieceIcon("white-rook.png");
        tiles[7][1].setPieceIcon("white-knight.png");
        tiles[7][2].setPieceIcon("white-bishop.png");
        tiles[7][3].setPieceIcon("white-queen.png");
        tiles[7][4].setPieceIcon("white-king.png");
        tiles[7][5].setPieceIcon("white-bishop.png");
        tiles[7][6].setPieceIcon("white-knight.png");
        tiles[7][7].setPieceIcon("white-rook.png"); 

        for (int col = 0; col < 8; col++) {
            tiles[6][col].setPieceIcon("white-pawn.png");
        }
    }

}
