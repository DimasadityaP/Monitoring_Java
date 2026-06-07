package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/** Label + AppComboBox, siap drag-drop lewat Choose Bean: components.FormComboField */
public class FormComboField extends JPanel {
    private JLabel label;
    private AppComboBox comboBox;
    private String labelText = "Label";

    public FormComboField() {
        setOpaque(false);
        setLayout(new BorderLayout(0, 6));
        setBorder(new EmptyBorder(0, 0, 0, 0));
        label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(AppColors.TEXT_DARK);
        comboBox = new AppComboBox();
        add(label, BorderLayout.NORTH);
        add(comboBox, BorderLayout.CENTER);
        setPreferredSize(new Dimension(420, 72));
    }

    public String getLabelText() { return labelText; }
    public void setLabelText(String labelText) { this.labelText = labelText; label.setText(labelText); }
    public Object getSelectedItem() { return comboBox.getSelectedItem(); }
    public void setSelectedItem(Object item) { comboBox.setSelectedItem(item); }
    public AppComboBox getComboBox() { return comboBox; }
}
