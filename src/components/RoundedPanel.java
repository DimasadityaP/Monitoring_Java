package components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
    private int radius = 30;
    private Color borderColor = null;
    private int borderWidth = 0;
    private boolean flatLeft = false;

    public RoundedPanel() {
        setOpaque(false);
        setBackground(Color.WHITE);
    }

    public RoundedPanel(int radius) {
        this();
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        repaint();
    }

    public boolean isFlatLeft() {
        return flatLeft;
    }

    public void setFlatLeft(boolean flatLeft) {
        this.flatLeft = flatLeft;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int r = Math.max(0, radius);

        g2.setColor(getBackground());
        if (flatLeft) {
            g2.fillRoundRect(-r, 0, width + r, height, r, r);
            g2.fillRect(0, 0, Math.max(1, r / 2), height);
        } else {
            g2.fillRoundRect(0, 0, width, height, r, r);
        }

        if (borderColor != null && borderWidth > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderWidth));
            int pad = Math.max(1, borderWidth);
            if (flatLeft) {
                g2.drawRoundRect(-r, pad / 2, width + r - pad, height - pad, r, r);
            } else {
                g2.drawRoundRect(pad / 2, pad / 2, width - pad, height - pad, r, r);
            }
        }

        g2.dispose();
        super.paintComponent(g);
    }
}
