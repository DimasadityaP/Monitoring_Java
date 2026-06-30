package components;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.net.URL;

public class IconButtonRender extends DefaultTableCellRenderer {

    private final ImageIcon icon;

    public IconButtonRender(String iconPath) {
        URL url = getClass().getResource(iconPath);
        if (url != null) {
            Image scaled = new ImageIcon(url).getImage()
                               .getScaledInstance(18, 18, Image.SCALE_SMOOTH);
            this.icon = new ImageIcon(scaled);
        } else {
            this.icon = null;
        }
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setIcon(icon != null ? icon : null);
        label.setText(icon == null ? "Edit" : ""); // fallback kalau icon gagal load
        label.setBackground(isSelected ? new Color(235, 238, 255) : Color.WHITE);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return label;
    }
}