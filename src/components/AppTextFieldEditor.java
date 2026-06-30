package components;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AppTextFieldEditor extends AbstractCellEditor implements TableCellEditor {
    private AppTextField textField;

    public AppTextFieldEditor() {
        textField = new AppTextField();
        
        // Handle Enter key to stop editing
        textField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "stopEdit");
        textField.getActionMap().put("stopEdit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopCellEditing();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, 
                                                 boolean isSelected, int row, int column) {
        if (value != null) {
            textField.setText(value.toString());
        } else {
            textField.setText("");
        }
        return textField;
    }

    @Override
    public Object getCellEditorValue() {
        return textField.getText();
    }
}
