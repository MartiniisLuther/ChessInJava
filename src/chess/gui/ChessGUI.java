package chess.gui;

import chess.core.ChessBoard;
import chess.core.Piece;
import chess.core.Position;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * The ChessGUI class is the main entry point for the Chess game.
 * It initializes the main game window (JFrame) and sets up the graphical 
 * board layout.
 * 
 * Responsibilities:
 * - Create & configure the main application window.
 * - Initiate & display the chessboard panel.
 * - Handle basic window settings ~ size, layout
 * 
 * Future improvements: 
 * - Add menus (e.g. Restart, Undo, Help).
 * - Connect GUI to game logic
 */

public class ChessGUI {
	
	// Window dimensions 
	private static final int WINDOW_WIDTH = 1200;
	private static final int WINDOW_HEIGHT = 800;

	private static Position selectedTile = null;
	private static Piece selectedPiece = null;
	private static List<chess.core.Position> highlightedSquares = new ArrayList<>();
	private static ChessBoard modelBoard = new ChessBoard();
	private static BoardPanel boardPanelReference;
	private static SidePanel sidePanelReference;


	// main method
	public static void main(String[] args) {
		// Swing apps should run on the Event Dispatch Thread for thread safety.
		SwingUtilities.invokeLater(() -> {
			new ChessGUI().createAndShowGUI();
		});
	}
	
	/**
	 * Sets up displays the main game window.
	 * Adds the chessboard panel to the JFrame.
	*/
	private void createAndShowGUI() {
		JFrame frame = new JFrame("Chess in Java");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setResizable(false);
		// frame.setBackground(new Color(0x111827)); // dark gray background
		
		// Create and add the chessboard panel
		ChessBoardWithCoords boardWithCoords = new ChessBoardWithCoords();
		boardPanelReference = boardWithCoords.getBoardPanel();
		sidePanelReference = new SidePanel();

		// Layout setup
		frame.setLayout(new BorderLayout());
		frame.add(boardWithCoords, BorderLayout.CENTER);
		frame.add(sidePanelReference, BorderLayout.EAST);
		
		// Center the window on screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/** Handle tile click: 
	 * - select a piece or move selected piece if pos is a legal move; 
	 * - updates selection/highlightedSquares
	 * - and may call modelBoard.movePiece(...). 
	 * 
	 * @param pos clicked tile (non-null) 
	 */
	public static void handleTileClicked(Position pos) {
		Piece piece = modelBoard.getPiece(pos);

		if (selectedPiece == null) {
			if (piece != null) {
				selectedPiece = piece;
				selectedTile = pos;
				highlightedSquares = piece.getLegalMoves(modelBoard);
				if (boardPanelReference != null) {
					boardPanelReference.refreshBoard();
				}
			}
		} else {
			if (highlightedSquares.contains(pos)) {
				modelBoard.movePiece(selectedTile, pos);
				modelBoard.switchTurn();
				if (sidePanelReference != null) {
					sidePanelReference.updateTurn(modelBoard.getCurrentTurn());
				}
			}
			selectedPiece = null;
			selectedTile = null;
			highlightedSquares.clear();
			if (boardPanelReference != null) {
				boardPanelReference.refreshBoard();
			}
		}
	}


	/**
	 * Checks whether a specific board position is currently highlighted in the GUI.
	 * Queries the internal highlightedSquares collection to determine if the given position is marked.
	 *
	 * @param pos the board Position to check for highlighting
	 * @return true if the specified position is highlighted, false otherwise
	 */
	public static boolean isHighlighted(Position pos) {
		return highlightedSquares.contains(pos);
	}

	// getter for ChessBoard modelBoard
	public static ChessBoard getModalChessBoard() {
		return modelBoard;
	}


}
