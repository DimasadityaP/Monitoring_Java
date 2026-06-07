package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComboBox;

public class RoundedComboBox extends JComboBox<String> {
    private int radius = 10;
    private Color borderColor = Color.BLACK;

    public RoundedComboBox() {
        super(new String[] { "Pilih", "Konstruksi", "Pengadaan", "Administrasi" });
        setFont(new Font("Segoe UI", Font.PLAIN, 16));
        setOpaque(false);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(320, 42));
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
