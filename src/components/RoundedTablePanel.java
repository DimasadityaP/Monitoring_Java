//package components;
//
//import java.awt.*;
//import javax.swing.*;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import javax.swing.table.TableCellEditor;
//
//public class RoundedTablePanel extends RoundedPanel {
//    private JTable table;
//    private DefaultTableModel model;
//    private int[] editableColumns = {1};  // Default hanya column 1
//
//    public RoundedTablePanel() {
//        super(18);
//        init();
//    }
//    
//    public void setEditableColumns(int... columns) {
//        this.editableColumns = columns;
//    }
//    
//    private boolean isColumnEditable(int column) {
//        for (int col : editableColumns) {
//            if (col == column) return true;
//        }
//        return false;
//    }
//    
//    public boolean isEditing() {
//        return table.isEditing();
//    }
//
//    public void stopEditing() {
//        if (table.isEditing()) {
//            table.getCellEditor().stopCellEditing();
//        }
//    }
//
//    private void init() {
//        setLayout(new BorderLayout());
//        setBackground(Color.WHITE);
//        setPreferredSize(new Dimension(780, 300));
//
//        model = new DefaultTableModel() {
//            public boolean isCellEditable(int row, int column) {
//                return isColumnEditable(column);
//            }
//        };
//
//        table = new JTable(model);
//        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        table.setForeground(RoundedColors.TEXT_DARK);
//        table.setRowHeight(34);
//        table.setShowGrid(true);
//        table.setGridColor(new Color(230, 230, 235));
//        table.setSelectionBackground(new Color(235, 238, 255));
//        table.setSelectionForeground(RoundedColors.TEXT_DARK);
//
//        JTableHeader header = table.getTableHeader();
//        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
//        header.setForeground(RoundedColors.TEXT_DARK);
//        header.setBackground(Color.WHITE);
//        header.setReorderingAllowed(false);
//
//        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
//        center.setHorizontalAlignment(SwingConstants.CENTER);
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(center);
//        }
//
//        JScrollPane scroll = new JScrollPane(table);
//        scroll.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 235)));
//        add(scroll, BorderLayout.CENTER);
//    }
//
//        public void setColumnEditor(int columnIndex, TableCellEditor editor) {
//        if (table.getColumnCount() > columnIndex) {
//            table.getColumnModel().getColumn(columnIndex).setCellEditor(editor);
//        }
//    }
//
//    public JTable getTable() {
//        return table;
//    }
//
//    public DefaultTableModel getModel() {
//        return model;
//    }
//
//    public void setTableData(Object[][] data, Object[] columns) {
//        model = new DefaultTableModel(data, columns) {
//            public boolean isCellEditable(int row, int column) {
//                return isColumnEditable(column);
//            }
//        };
//        table.setModel(model);
//    }
//
//    public void addRow() {
//        int rowCount = model.getRowCount();
//        model.addRow(new Object[]{rowCount + 1, "", ""});
//    }
//
//    public void clearTable() {
//        model.setRowCount(0);
//    }
//
//    public Object[][] getTableData() {
//        int rowCount = model.getRowCount();
//        int colCount = model.getColumnCount();
//        Object[][] data = new Object[rowCount][colCount];
//        for (int i = 0; i < rowCount; i++) {
//            for (int j = 0; j < colCount; j++) {
//                data[i][j] = model.getValueAt(i, j);
//            }
//        }
//        return data;
//    }
//}

package components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class RoundedTablePanel extends RoundedPanel {
    private JTable table;
    private DefaultTableModel model;
    private int[] editableColumns = {};
    

    // Warna sidebar/header ungu
    private static final Color HEADER_BG     = new Color(63, 55, 155);
    private static final Color HEADER_FG     = Color.WHITE;
    private static final Color ROW_ODD       = Color.WHITE;
    private static final Color ROW_EVEN      = new Color(245, 245, 252);
    private static final Color SEL_BG        = new Color(180, 175, 230);
    private static final Color GRID_COLOR    = new Color(220, 218, 240);
    private static final Color TEXT_COLOR    = new Color(40, 40, 60);

    // Aksi column
    private boolean showActionColumn = false;
    private ActionClickListener actionClickListener;
    private String actionIconPath = null; // optional icon path
    private boolean deleteOnlyMode = false;
    private ActionClickListener deleteClickListener;   // <-- baru
    private String deleteIconPath = null;

    public interface ActionClickListener {
        void onActionClick(int row);
    }

    public RoundedTablePanel() {
        super(18);
        init();
    }

    // ── Editable columns ─────────────────────────────────────────────────────
    public void setEditableColumns(int... columns) {
        this.editableColumns = columns;
    }

    private boolean isColumnEditable(int column) {
        // Kolom aksi tidak pernah editable
        if (showActionColumn && column == model.getColumnCount() - 1) return false;
        for (int col : editableColumns) {
            if (col == column) return true;
        }
        return false;
    }

    public boolean isEditing() { return table.isEditing(); }

    public void stopEditing() {
        if (table.isEditing()) table.getCellEditor().stopCellEditing();
    }

    // ── Action column ─────────────────────────────────────────────────────────
    /**
     * Aktifkan kolom Aksi (Edit).
     * @param iconPath path icon di classpath, boleh null (pakai teks "Edit")
     * @param listener callback saat baris di-klik
     */
    public void setActionColumn(String iconPath, ActionClickListener listener) {
        this.showActionColumn = true;
        this.deleteOnlyMode = false;
        this.actionIconPath   = iconPath;
        this.actionClickListener = listener;
    }

    /**
     * Aktifkan icon Edit saja di kolom Aksi.
     * Bisa dikombinasi dengan setDeleteAction().
     */
    public void setEditAction(String iconPath, ActionClickListener listener) {
        this.showActionColumn = true;
        this.deleteOnlyMode = false;
        this.actionIconPath = iconPath;
        this.actionClickListener = listener;
    }

    /**
     * Aktifkan icon Delete di kolom Aksi.
     * Bisa dikombinasi dengan setEditAction() — kalau keduanya dipanggil,
     * kolom Aksi akan menampilkan dua icon (edit kiri, delete kanan).
     */
    public void setDeleteAction(String iconPath, ActionClickListener listener) {
        this.showActionColumn = true;
        this.deleteOnlyMode = false;
        this.deleteIconPath = iconPath;
        this.deleteClickListener = listener;
    }

    /**
     * Aktifkan kolom Aksi dengan icon Delete SAJA (tanpa Edit).
     * Cocok untuk tabel item di dalam form.
     */
    public void setDeleteOnlyColumn(String iconPath, ActionClickListener listener) {
        this.showActionColumn = true;
        this.deleteOnlyMode = true;
        this.deleteIconPath = iconPath;
        this.deleteClickListener = listener;
        this.actionClickListener = null;
    }

    public void hideActionColumn() {
        this.showActionColumn = false;
    }

    // ── Init ──────────────────────────────────────────────────────────────────
    private void init() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(780, 300));

        model = buildModel(new Object[0][0], new Object[0]);
        table = buildTable();

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(Color.WHITE);

        add(scroll, BorderLayout.CENTER);
    }

    private DefaultTableModel buildModel(Object[][] data, Object[] cols) {
        return new DefaultTableModel(data, cols) {
            public boolean isCellEditable(int row, int column) {
                return isColumnEditable(column);
            }
        };
    }
    
    private JTable buildTable() {
        JTable t = new JTable(model) {
            // Warna baris selang-seling
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? ROW_ODD : ROW_EVEN);
                    c.setForeground(TEXT_COLOR);
                } else {
                    c.setBackground(SEL_BG);
                    c.setForeground(TEXT_COLOR);
                }
                return c;
            }
        };

        t.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        t.setForeground(TEXT_COLOR);
        t.setRowHeight(36);
        t.setShowGrid(true);
        t.setGridColor(GRID_COLOR);
        t.setSelectionBackground(SEL_BG);
        t.setSelectionForeground(TEXT_COLOR);
        t.setIntercellSpacing(new Dimension(0, 1));
        t.setFillsViewportHeight(true);

        // Header ungu
        JTableHeader header = t.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setForeground(HEADER_FG);
        header.setBackground(HEADER_BG);
        header.setReorderingAllowed(false);
        header.setDefaultRenderer(new HeaderRenderer());

        // Mouse listener untuk kolom aksi
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!showActionColumn) return;
                int row = t.rowAtPoint(e.getPoint());
                int col = t.columnAtPoint(e.getPoint());
                int aksiCol = t.getColumnCount() - 1;
                if (row < 0 || col != aksiCol) return;

                if (deleteOnlyMode) {
                    if (deleteClickListener != null) deleteClickListener.onActionClick(row);
                    return;
                }

                boolean showEdit = actionClickListener != null;
                boolean showDelete = deleteClickListener != null;

                // Cuma edit yang aktif → seluruh cell = edit
                if (showEdit && !showDelete) {
                    actionClickListener.onActionClick(row);
                    return;
                }
                // Cuma delete yang aktif → seluruh cell = delete
                if (showDelete && !showEdit) {
                    deleteClickListener.onActionClick(row);
                    return;
                }
                // Dua-duanya aktif → split kiri/kanan
                if (showEdit && showDelete) {
                    Rectangle cellRect = t.getCellRect(row, col, false);
                    int clickX = e.getX() - cellRect.x;
                    int cellWidth = cellRect.width;
                    if (clickX < cellWidth / 2) {
                        actionClickListener.onActionClick(row);
                    } else {
                        deleteClickListener.onActionClick(row);
                    }
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!showActionColumn) return;
                int col = t.columnAtPoint(e.getPoint());
                int aksiCol = t.getColumnCount() - 1;
                t.setCursor(col == aksiCol
                        ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                        : Cursor.getDefaultCursor());
            }
        });


        return t;
    }

    private static class HeaderRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            JLabel lbl = new JLabel(value == null ? "" : value.toString());
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lbl.setForeground(HEADER_FG);
            lbl.setBackground(HEADER_BG);
            lbl.setOpaque(true);
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setPreferredSize(new Dimension(0, 42)); // <-- atur tinggi di sini
            lbl.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                    new Color(90, 80, 180)));
            return lbl;
        }
    }

    private static class ActionRenderer extends DefaultTableCellRenderer {
        private static ImageIcon editIcon;
        private static ImageIcon deleteIcon;

        static {
            java.net.URL editUrl = ActionRenderer.class.getResource("/image/edit.png");
            if (editUrl != null) {
                Image img = new ImageIcon(editUrl).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                editIcon = new ImageIcon(img);
            }
            java.net.URL deleteUrl = ActionRenderer.class.getResource("/image/delete.png");
            if (deleteUrl != null) {
                Image img = new ImageIcon(deleteUrl).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                deleteIcon = new ImageIcon(img);
            }
        }

        private final boolean deleteOnly;
        private final boolean showEdit;
        private final boolean showDelete;

        public ActionRenderer(boolean deleteOnly, boolean showEdit, boolean showDelete) {
            this.deleteOnly = deleteOnly;
            this.showEdit = showEdit;
            this.showDelete = showDelete;
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {

            Color bg = isSelected ? SEL_BG : (row % 2 == 0 ? ROW_ODD : ROW_EVEN);

            if (deleteOnly) {
                JLabel lbl = new JLabel(deleteIcon != null ? deleteIcon : null);
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setOpaque(true);
                lbl.setBackground(bg);
                if (deleteIcon == null) lbl.setText("Del");
                return lbl;
            }

            // Hitung berapa icon yang sebenarnya aktif
            int activeCount = (showEdit ? 1 : 0) + (showDelete ? 1 : 0);
            JPanel panel = new JPanel(new GridLayout(1, Math.max(activeCount, 1), 4, 0));
            panel.setOpaque(true);
            panel.setBackground(bg);

            if (showEdit) {
                JLabel editLbl = new JLabel(editIcon != null ? editIcon : null);
                editLbl.setHorizontalAlignment(SwingConstants.CENTER);
                if (editIcon == null) editLbl.setText("E");
                panel.add(editLbl);
            }

            if (showDelete) {
                JLabel deleteLbl = new JLabel(deleteIcon != null ? deleteIcon : null);
                deleteLbl.setHorizontalAlignment(SwingConstants.CENTER);
                if (deleteIcon == null) deleteLbl.setText("D");
                panel.add(deleteLbl);
            }

            return panel;
        }
    }



    public void setColumnEditor(int columnIndex, TableCellEditor editor) {
        if (table.getColumnCount() > columnIndex)
            table.getColumnModel().getColumn(columnIndex).setCellEditor(editor);
    }

    public JTable getTable() { return table; }
    public DefaultTableModel getModel() { return model; }

    public void setTableData(Object[][] data, Object[] columns) {
        // Kalau showActionColumn, tambah kolom "Aksi" otomatis
        Object[] cols = columns;
        Object[][] rows = data;

        if (showActionColumn) {
            // Tambah header "Aksi"
            cols = new Object[columns.length + 1];
            System.arraycopy(columns, 0, cols, 0, columns.length);
            cols[columns.length] = "Aksi";

            // Tambah null di setiap baris untuk kolom Aksi
            rows = new Object[data.length][columns.length + 1];
            for (int i = 0; i < data.length; i++) {
                System.arraycopy(data[i], 0, rows[i], 0, data[i].length);
                rows[i][columns.length] = null;
            }
        }

        model = buildModel(rows, cols);
        table.setModel(model);

        // Re-apply center renderer
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        // Kolom Aksi pakai ActionRenderer
        if (showActionColumn) {
            int aksiCol = table.getColumnCount() - 1;
            boolean showEdit = actionClickListener != null;
            boolean showDelete = deleteClickListener != null;
            table.getColumnModel().getColumn(aksiCol)
                 .setCellRenderer(new ActionRenderer(deleteOnlyMode, showEdit, showDelete));

            int width = deleteOnlyMode ? 60 : (showEdit && showDelete ? 90 : 60);
            table.getColumnModel().getColumn(aksiCol).setPreferredWidth(width);
            table.getColumnModel().getColumn(aksiCol).setMaxWidth(width);
            table.getColumnModel().getColumn(aksiCol).setMinWidth(width);
        }

        
        table.getTableHeader().resizeAndRepaint();
        table.getTableHeader().revalidate();
    }

    /**
     * Tambah baris + otomatis append null untuk kolom Aksi kalau aktif.
     */
    public void addRowData(Object[] rowData) {
        if (showActionColumn) {
            Object[] extended = new Object[rowData.length + 1];
            System.arraycopy(rowData, 0, extended, 0, rowData.length);
            extended[rowData.length] = null;
            model.addRow(extended);
        } else {
            model.addRow(rowData);
        }
    }

    public void addRow() {
        int rowCount = model.getRowCount();
        model.addRow(new Object[]{rowCount + 1, "", ""});
    }

    public void clearTable() { model.setRowCount(0); }

    public Object[][] getTableData() {
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        Object[][] data = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++)
            for (int j = 0; j < colCount; j++)
                data[i][j] = model.getValueAt(i, j);
        return data;
    }
}