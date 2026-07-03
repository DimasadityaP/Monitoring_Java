package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DashboardCard extends RoundedPanel {
    private final JLabel lblTitle;
    private final JLabel lblCount;
    private final IconPanel iconPanel;

    public DashboardCard() {
        this("Title", "0", Color.BLUE, "project");
    }

    public DashboardCard(String title, String count, Color themeColor, String iconType) {
        super(20); // radius 20
        setBackground(new Color(245, 247, 250));
        setBorder(new EmptyBorder(16, 20, 16, 20));
        setLayout(new BorderLayout(15, 0));

        // Left side: Icon Panel
        iconPanel = new IconPanel(themeColor, iconType);
        add(iconPanel, BorderLayout.WEST);

        // Right side: Info Panel (Count and Title)
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        lblCount = new JLabel(count);
        lblCount.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblCount.setForeground(RoundedColors.TEXT_DARK);
        lblCount.setAlignmentX(Component.RIGHT_ALIGNMENT);

        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTitle.setForeground(new Color(120, 120, 140));
        lblTitle.setAlignmentX(Component.RIGHT_ALIGNMENT);

        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(lblCount);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        infoPanel.add(lblTitle);
        infoPanel.add(Box.createVerticalGlue());

        add(infoPanel, BorderLayout.EAST);
    }

    public void setCount(String count) {
        lblCount.setText(count);
        repaint();
    }

    private static class IconPanel extends JPanel {
        private final Color themeColor;
        private final String iconType;

        public IconPanel(Color themeColor, String iconType) {
            this.themeColor = themeColor;
            this.iconType = iconType;
            setPreferredSize(new Dimension(54, 54));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            // Draw circular background with low opacity (15%)
            g2.setColor(new Color(themeColor.getRed(), themeColor.getGreen(), themeColor.getBlue(), 38)); // 38/255 = 15%
            g2.fillOval(0, 0, w, h);

            // Draw vector icon based on iconType
            g2.setColor(themeColor);
            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            int cx = w / 2;
            int cy = h / 2;

            if ("project".equalsIgnoreCase(iconType)) {
                // Clipboard/Folder Icon
                g2.drawRoundRect(cx - 10, cy - 12, 20, 24, 4, 4);
                g2.fillRect(cx - 6, cy - 14, 12, 4);
                g2.drawLine(cx - 5, cy - 4, cx + 5, cy - 4);
                g2.drawLine(cx - 5, cy + 2, cx + 5, cy + 2);
            } else if ("barang".equalsIgnoreCase(iconType)) {
                // Box/Package Icon
                g2.drawRect(cx - 10, cy - 10, 20, 20);
                g2.drawLine(cx - 10, cy - 10, cx - 4, cy - 4);
                g2.drawLine(cx + 10, cy - 10, cx + 4, cy - 4);
                g2.drawRect(cx - 4, cy - 4, 8, 8);
            } else if ("administrasi".equalsIgnoreCase(iconType)) {
                // Document Icon
                g2.drawRoundRect(cx - 9, cy - 12, 18, 24, 3, 3);
                g2.drawLine(cx - 5, cy - 6, cx + 5, cy - 6);
                g2.drawLine(cx - 5, cy - 1, cx + 5, cy - 1);
                g2.drawLine(cx - 5, cy + 4, cx + 1, cy + 4);
            } else if ("reimbursement".equalsIgnoreCase(iconType)) {
                // Receipt/Cash Icon
                g2.drawRoundRect(cx - 12, cy - 8, 24, 16, 4, 4);
                g2.drawOval(cx - 4, cy - 4, 8, 8);
                g2.drawLine(cx - 8, cy, cx - 7, cy);
                g2.drawLine(cx + 7, cy, cx + 8, cy);
            }

            g2.dispose();
        }
    }
}
