package chess.gui;

import java.awt.*;
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
		// BoardPanel boardPanel = new BoardPanel();
		ChessBoardWithCoords boardWithCoords = new ChessBoardWithCoords();
		SidePanel sidePanel = new SidePanel();

		// Layout setup
		frame.setLayout(new BorderLayout());
		frame.add(boardWithCoords, BorderLayout.CENTER);
		frame.add(sidePanel, BorderLayout.EAST);
		
		// Center the window on screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
