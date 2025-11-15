package chess.gui;

import java.awt.*;
import javax.swing.*;

/**
 * A Wrapper panel that surrounds the core chessboard (BoardPanel) with
 * rank (1-8) and file (a-h) coordinates.
 * 
 * This keeps BoardPanel focused on rendering the chessboard itself,
 * 
 */

public class ChessBoardWithCoords extends JPanel {

    private static final String[] FILES = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private static final String[] RANKS = {"8", "7", "6", "5", "4", "3", "2", "1"};

    // Constructor
    public ChessBoardWithCoords() {
        setLayout(new BorderLayout());

        // Core chessboard panel
        BoardPanel board = new BoardPanel();

        // Add rank labels (left and right)
        add(createRankLabelsPanel(), BorderLayout.WEST);
        add(createRankLabelsPanel(), BorderLayout.EAST);

        // Add file labels (top and bottom)
        add(createFileLabelsPanel(), BorderLayout.NORTH);
        add(createFileLabelsPanel(), BorderLayout.SOUTH);

        // Add the chessboard at the center
        add(board, BorderLayout.CENTER);
    }

    // Create rank labels (1-8)
    private JPanel createRankLabelsPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 1));
        for (String rank : RANKS) {
            JLabel label = new JLabel(rank, SwingConstants.CENTER);
            label.setFont(new Font("Courier New", Font.BOLD, 14));
            panel.add(label);
        }
        return panel;
    }

    // Create file labels (A-H)
    private JPanel createFileLabelsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 8));
        for (String file : FILES) {
            JLabel label = new JLabel(file, SwingConstants.CENTER);
            label.setFont(new Font("Courier New", Font.BOLD, 14));
            panel.add(label);
        }
        return panel;
    }
    
}
