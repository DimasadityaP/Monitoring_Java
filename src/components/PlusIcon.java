package components;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.BasicStroke;
import javax.swing.Icon;

public class PlusIcon implements Icon {
    private final int size;

    public PlusIcon(int size) {
        this.size = size;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2.2f));

        int midX = x + size / 2;
        int midY = y + size / 2;
        int pad = 4;

        g2.drawLine(midX, y + pad, midX, y + size - pad);
        g2.drawLine(x + pad, midY, x + size - pad, midY);

        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }
}
