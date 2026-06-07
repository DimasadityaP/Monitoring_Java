package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class RoundedTextArea extends JTextArea {
    private int radius = 10;
    private Color borderColor = Color.BLACK;

    public RoundedTextArea() {
        setOpaque(false);
        setFont(new Font("Segoe UI", Font.PLAIN, 16));
        setForeground(RoundedColors.TEXT_DARK);
        setLineWrap(true);
        setWrapStyleWord(true);
        setBorder(new EmptyBorder(8, 12, 8, 12));
        setPreferredSize(new Dimension(420, 86));
        setRows(3);
    }

    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; repaint(); }

    public Color getBorderColor() { return borderColor; }
    public void setBorderColor(Color borderColor) { this.borderColor = borderColor; repaint(); }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        if (borderColor != null) {
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        }
        g2.dispose();
        super.paintComponent(g);
    }
}
