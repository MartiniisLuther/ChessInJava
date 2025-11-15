package chess.gui;

import java.awt.*;
import javax.swing.*;


/**
 * SidePanel renders game-related information alongside the chessboard.
 * Responsibilities:
 * - Display captured pieces.
 * - Show current player's turn.
 * - Provide buttons for game actions (e.g. Restart, Undo).
*/
public class SidePanel extends JPanel {
    // Constructor
    public SidePanel() {
        setPreferredSize(new Dimension(400, 800)); // width x height
        setBackground(new Color(0x111827)); // dark gray background
        setLayout(new BorderLayout());

        // Placeholder for captured pieces panel
        JLabel title = new JLabel("Game Info", SwingConstants.CENTER);
        title.setFont(new Font("Courier New", Font.BOLD, 25));
        title.setForeground(Color.YELLOW);

        add(title, BorderLayout.NORTH);
    }
    
}
