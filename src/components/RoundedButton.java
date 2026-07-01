package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedButton extends JButton {
    private int radius = 18;
    private Color buttonColor = RoundedColors.PRIMARY;

    public RoundedButton() {
        this("Button");
    }

    public RoundedButton(String text) {
        super(text);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setBorder(new EmptyBorder(8, 20, 8, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(160, 48));
    }

    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; repaint(); }

    public Color getButtonColor() { return buttonColor; }
    public void setButtonColor(Color buttonColor) { this.buttonColor = buttonColor; repaint(); }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(buttonColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.dispose();
        super.paintComponent(g);
    }
}
