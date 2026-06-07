package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class RoundedTablePanel extends RoundedPanel {
    private JTable table;
    private DefaultTableModel model;

    public RoundedTablePanel() {
        super(18);
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(780, 300));

        model = new DefaultTableModel(
                new Object[][] {
                    {"01/SPH/MKI/2026", "Kementerian Dalam Negeri", "11/4/26", "keluar", "Edit"},
                    {"019.21/pl.dkn./26", "BPMSP", "13/4/26", "masuk", "Edit"},
                    {"09/ADM/MKI/2026", "Kementerian PUPR", "05/4/26", "keluar", "Edit"}
                },
                new Object[] {"No. Surat", "Instansi", "Tanggal", "Tipe", "Aksi"}) {
            public boolean isCellEditable(int row, int column) {
                return column == 4;
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

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setTableData(Object[][] data, Object[] columns) {
        model = new DefaultTableModel(data, columns);
        table.setModel(model);
    }
}
