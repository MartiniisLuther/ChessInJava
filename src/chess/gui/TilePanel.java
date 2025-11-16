package chess.gui;

import java.awt.*;
import javax.swing.*;

/**
 * TilePanel represents an individual square on the chessboard.
 * 
 * Responsibilities:
 * - Render the tile with appropriate color.
 * - Optionally display a chess piece.
 * 
 * Future improvements:
 * - Add functionality to highlight the tile.
 * - Integrate with game logic for piece placement.
 */

public class TilePanel extends JPanel {
    
    private JLabel pieceLabel;

    // Constructor
    public TilePanel(Color bgColor) {
        setBackground(bgColor);
        setLayout(new BorderLayout());

        pieceLabel = new JLabel();
        pieceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pieceLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(pieceLabel, BorderLayout.CENTER);
    }

    /** 
     * Renders a chess piece from /assets.
     * @param imageName The file path to the piece image.
     */
    public void setPieceIcon(String imageName) {
        if (imageName == null) {
            pieceLabel.setIcon(null);
            return;
        }

        // Load image from resources
        ImageIcon icon = new ImageIcon("assets/" + imageName);
        // Scale image to fit tile
        Image scaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        pieceLabel.setIcon(new ImageIcon(scaledImage)); 
     }

}
