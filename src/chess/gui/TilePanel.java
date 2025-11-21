package chess.gui;

import chess.core.Piece;
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
    private chess.core.Position tilePosition;

    // Constructor
    public TilePanel(Color bgColor, chess.core.Position pos) {
        this.tilePosition = pos;
        setBackground(bgColor);
        setLayout(new BorderLayout());

        pieceLabel = new JLabel();
        pieceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pieceLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(pieceLabel, BorderLayout.CENTER);

        // event listener
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                chess.gui.ChessGUI.handleTileClicked(tilePosition);
            }
        });
    }

    /** 
     * Renders a chess piece from /assets/assets
     */
    public void setPieceIcon(Piece piece) {
        this.removeAll();

        if (piece == null) {
            revalidate();
            repaint();
            return;
        }

        // Load image path
        String iconPath = "/assets/pieces" 
            + piece.getColor().toString().toLowerCase() + "-"
            + piece.getType().toString().toLowerCase() + ".png";

        //
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            pieceLabel.setIcon(icon);
            this.add(pieceLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("Missing icon: " + iconPath);
        }

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (chess.gui.ChessGUI.isHighlighted(tilePosition)) {
            g.setColor(new Color(255, 255, 0, 120));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

}
