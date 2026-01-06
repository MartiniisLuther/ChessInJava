package chess.gui;

import chess.core.ChessBoard;
import chess.core.Piece;
import chess.core.Position;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Main GUI class for the Chess game.
 * Initializes the game window, chessboard panel, and handles tile clicks.
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

	/**
	 * Handles tile clicks. Selects pieces and executes moves if valid.
	 * @param pos the clicked board position
	 */
	public static void handleTileClicked(Position pos) {
		Piece piece = modelBoard.getPiece(pos);

		// CASE 1: No piece currently selected
		if (selectedPiece == null) {
			// Only select if it's the piece's turn
			if (piece != null && piece.getColor() == modelBoard.getCurrentTurn()) {
				selectedPiece = piece;
				selectedTile = pos;
				// Highlight legal moves for the selected piece
				highlightedSquares = piece.getLegalMoves(modelBoard);
				
				if (boardPanelReference != null) {
					boardPanelReference.refreshBoard();
				}
			}
		}
		// CASE 2: A piece is already selected
		else {
			if (highlightedSquares.contains(pos)) {
				// Valid move - execute it
				modelBoard.movePiece(selectedTile, pos);
				modelBoard.switchTurn();

				if (sidePanelReference != null) {
					sidePanelReference.updateTurn(modelBoard.getCurrentTurn());
				}
			}

			// Deselect regardless if whether move was successful
			selectedPiece = null;
			selectedTile = null;
			highlightedSquares.clear();

			// Refresh the board display
			if (boardPanelReference != null) {
				boardPanelReference.refreshBoard();
			}
		}
	}


	/**
	 * Checks if a position is highlighted.
	 * @param pos the board position to check
	 * @return true if highlighted, false otherwise
	 */
	public static boolean isHighlighted(Position pos) {
		return highlightedSquares.contains(pos);
	}

	// getter for ChessBoard modelBoard
	public static ChessBoard getModalChessBoard() {
		return modelBoard;
	}


}
