package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/** Label + AppTextField, siap drag-drop lewat Choose Bean: components.FormTextField */
public class FormTextField extends JPanel {
    private JLabel label;
    private AppTextField textField;
    private String labelText = "Label";

    public FormTextField() {
        setOpaque(false);
        setLayout(new BorderLayout(0, 6));
        setBorder(new EmptyBorder(0, 0, 0, 0));
        label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(AppColors.TEXT_DARK);
        textField = new AppTextField();
        add(label, BorderLayout.NORTH);
        add(textField, BorderLayout.CENTER);
        setPreferredSize(new Dimension(420, 72));
    }

    public String getLabelText() { return labelText; }
    public void setLabelText(String labelText) { this.labelText = labelText; label.setText(labelText); }
    public String getText() { return textField.getText(); }
    public void setText(String text) { textField.setText(text); }
    public AppTextField getTextField() { return textField; }
}
