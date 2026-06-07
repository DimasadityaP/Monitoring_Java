package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/** Label + AppTextArea, siap drag-drop lewat Choose Bean: components.FormTextAreaField */
public class FormTextAreaField extends JPanel {
    private JLabel label;
    private AppTextArea textArea;
    private String labelText = "Label";

    public FormTextAreaField() {
        setOpaque(false);
        setLayout(new BorderLayout(0, 6));
        setBorder(new EmptyBorder(0, 0, 0, 0));
        label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(AppColors.TEXT_DARK);
        textArea = new AppTextArea();
        add(label, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);
        setPreferredSize(new Dimension(420, 118));
    }

    public String getLabelText() { return labelText; }
    public void setLabelText(String labelText) { this.labelText = labelText; label.setText(labelText); }
    public String getText() { return textArea.getText(); }
    public void setText(String text) { textArea.setText(text); }
    public AppTextArea getTextArea() { return textArea; }
}
