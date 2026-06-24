package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;

public class RoundedTablePanel extends RoundedPanel {
    private JTable table;
    private DefaultTableModel model;
    private int[] editableColumns = {1};  // Default hanya column 1

    public RoundedTablePanel() {
        super(18);
        init();
    }
    
    public void setEditableColumns(int... columns) {
        this.editableColumns = columns;
    }
    
    private boolean isColumnEditable(int column) {
        for (int col : editableColumns) {
            if (col == column) return true;
        }
        return false;
    }
    
    public boolean isEditing() {
        return table.isEditing();
    }

    public void stopEditing() {
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }
    }

    private void init() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(780, 300));

        model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return isColumnEditable(column);
            }
        };

        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setForeground(RoundedColors.TEXT_DARK);
        table.setRowHeight(34);
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 235));
        table.setSelectionBackground(new Color(235, 238, 255));
        table.setSelectionForeground(RoundedColors.TEXT_DARK);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setForeground(RoundedColors.TEXT_DARK);
        header.setBackground(Color.WHITE);
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 235)));
        add(scroll, BorderLayout.CENTER);
    }

        public void setColumnEditor(int columnIndex, TableCellEditor editor) {
        if (table.getColumnCount() > columnIndex) {
            table.getColumnModel().getColumn(columnIndex).setCellEditor(editor);
        }
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setTableData(Object[][] data, Object[] columns) {
        model = new DefaultTableModel(data, columns) {
            public boolean isCellEditable(int row, int column) {
                return isColumnEditable(column);
            }
        };
        table.setModel(model);
    }

    public void addRow() {
        int rowCount = model.getRowCount();
        model.addRow(new Object[]{rowCount + 1, "", ""});
    }

    public void clearTable() {
        model.setRowCount(0);
    }

    public Object[][] getTableData() {
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        Object[][] data = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i][j] = model.getValueAt(i, j);
            }
        }
        return data;
    }
}
