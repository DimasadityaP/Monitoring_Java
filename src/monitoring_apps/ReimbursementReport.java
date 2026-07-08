package monitoring_apps;

import static Utills.UserSession.getUserName;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.KoneksiDb;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

public class ReimbursementReport extends javax.swing.JFrame {

    private final Connection conn = new KoneksiDb().connect();
    private DefaultTableModel tabmode;

    public ReimbursementReport() {
        initComponents();
        getContentPane().setBackground(java.awt.Color.WHITE);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        initUi();
        initTable();
        setColumnWidths();
        Navigation.bind(sidebarMenu1, this);
    }

    private void initUi() {
        pageTitle1.setText("REPORT REIMBURSEMENT");

        btnGenerate.setText("Generate Report");
        btnGenerate.setIcon(loadIcon("/image/file.png", 16));
        btnGenerate.setButtonColor(components.RoundedColors.PRIMARY);
        btnGenerate.setForeground(java.awt.Color.WHITE);
        btnGenerate.addActionListener(e -> generateReport());

        btnExport.setText("Export Report");
        btnExport.setIcon(loadIcon("/image/export.png", 16));
        btnExport.setButtonColor(components.RoundedColors.PRIMARY);
        btnExport.setForeground(java.awt.Color.WHITE);
    }

    private void initTable() {
        Object[] columns = new Object[]{"#", "ID", "No. Reimbursement", "Project", "Hari", "Perihal", "PJ", "Jumlah Item", "Preview Uraian", "Total"};
        tblProjectList.setTableData(new Object[0][columns.length], columns);
        tabmode = (DefaultTableModel) tblProjectList.getModel();
    }

    private void generateReport() {
        loadReimbursement(txtPeriode.getText());
    }


    private void loadReimbursement(String periode) {
        Object[] columns = new Object[]{"#","ID","No. Reimbursement","Project","Hari","Perihal","PJ","Jumlah Item","Preview Uraian","Total"};
        tblProjectList.setTableData(new Object[0][columns.length], columns);
        tabmode = (DefaultTableModel) tblProjectList.getModel();

        String search = periode == null ? "" : periode.trim();
        String like = "%" + search + "%";

        String[] queries = new String[]{
            "SELECT r.id, COALESCE(r.reimbursement_no, CONCAT('REIM-', r.id)) AS reimbursement_no, p.nama AS project_name, "
                + "r.hari, r.hal, r.pj, COUNT(ri.id) AS jumlah_item, "
                + "COALESCE(GROUP_CONCAT(ri.uraian ORDER BY ri.id ASC SEPARATOR ', '), '') AS uraian_preview, "
                + "COALESCE(NULLIF(r.total_akhir, 0), SUM(ri.jumlah), 0) AS total_akhir "
                + "FROM reimbursement r LEFT JOIN project p ON p.id = r.project_id "
                + "LEFT JOIN reimbursement_items ri ON ri.reimbursement_id = r.id "
                + "WHERE (? = '' OR COALESCE(r.reimbursement_no, '') LIKE ? OR p.nama LIKE ? OR r.hari LIKE ? OR r.hal LIKE ? OR r.pj LIKE ? OR CAST(r.id AS CHAR) LIKE ? OR COALESCE(ri.uraian, '') LIKE ?) "
                + "GROUP BY r.id, r.reimbursement_no, p.nama, r.hari, r.hal, r.pj, r.total_akhir "
                + "ORDER BY r.id DESC",
            "SELECT r.id, COALESCE(r.reimbursement_no, CONCAT('REIM-', r.id)) AS reimbursement_no, p.project_name AS project_name, "
                + "r.hari, r.hal, r.pj, COUNT(ri.id) AS jumlah_item, "
                + "COALESCE(GROUP_CONCAT(ri.uraian ORDER BY ri.id ASC SEPARATOR ', '), '') AS uraian_preview, "
                + "COALESCE(NULLIF(r.total_akhir, 0), SUM(ri.jumlah), 0) AS total_akhir "
                + "FROM reimbursement r LEFT JOIN project p ON p.id = r.project_id "
                + "LEFT JOIN reimbursement_items ri ON ri.reimbursement_id = r.id "
                + "WHERE (? = '' OR COALESCE(r.reimbursement_no, '') LIKE ? OR p.project_name LIKE ? OR r.hari LIKE ? OR r.hal LIKE ? OR r.pj LIKE ? OR CAST(r.id AS CHAR) LIKE ? OR COALESCE(ri.uraian, '') LIKE ?) "
                + "GROUP BY r.id, r.reimbursement_no, p.project_name, r.hari, r.hal, r.pj, r.total_akhir "
                + "ORDER BY r.id DESC"
        };

        Exception lastError = null;

        for (String query : queries) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, search);
                ps.setString(2, like);
                ps.setString(3, like);
                ps.setString(4, like);
                ps.setString(5, like);
                ps.setString(6, like);
                ps.setString(7, like);
                ps.setString(8, like);

                try (ResultSet rs = ps.executeQuery()) {
                    int no = 1;
                    while (rs.next()) {
                        tabmode.addRow(new Object[]{
                            no++,
                            safe(rs.getObject("id")),
                            safe(rs.getObject("reimbursement_no")),
                            safe(rs.getObject("project_name")),
                            safe(rs.getObject("hari")),
                            safe(rs.getObject("hal")),
                            safe(rs.getObject("pj")),
                            safe(rs.getObject("jumlah_item")),
                            safe(rs.getObject("uraian_preview")),
                            rs.getBigDecimal("total_akhir")
                        });
                    }
                }
                return;
            } catch (Exception e) {
                lastError = e;
            }
        }

        showError("Gagal mengambil data reimbursement. Sesuaikan nama tabel/kolom reimbursement.\n" + (lastError == null ? "" : lastError.getMessage()));
    }


    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Gagal", JOptionPane.WARNING_MESSAGE);
    }

    private void setColumnWidths() {
        JTable table = tblProjectList.getTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        for (Component comp : tblProjectList.getComponents()) {
            if (comp instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) comp;
                scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                break;
            }
        }

        int[] widths = {35, 55, 125, 120, 65, 115, 85, 70, 250, 90};

        for (int i = 0; i < widths.length && i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        tblProjectList = new components.RoundedTablePanel();
        btnGenerate = new components.RoundedButton();
        btnExport = new components.RoundedButton();
        jLabel1 = new javax.swing.JLabel();
        txtPeriode = new components.AppTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monitoring Apps");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("REPORT REIMBURSEMENT");

        btnGenerate.setText("Generate Report");

        btnExport.setText("Export Report");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jLabel1.setText("Periode");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userProfileCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sidebarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tblProjectList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userProfileCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sidebarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txtPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(tblProjectList, javax.swing.GroupLayout.PREFERRED_SIZE, 549, Short.MAX_VALUE)))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        exportReport();
    }//GEN-LAST:event_btnExportActionPerformed

    private void exportReport() {
        if (tabmode == null || tabmode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                "Tidak ada data. Silakan klik Generate Report terlebih dahulu.",
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Simpan REPORT REIMBURSEMENT");
        chooser.setSelectedFile(new File("ReportReimbursement.pdf"));
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "PDF Files (*.pdf)", "pdf"));

        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File outFile = chooser.getSelectedFile();
        if (!outFile.getName().toLowerCase().endsWith(".pdf")) {
            outFile = new File(outFile.getAbsolutePath() + ".pdf");
        }

        try {
            InputStream reportStream = getClass().getResourceAsStream("/report/ReimbursementReport.jrxml");
            if (reportStream == null) {
                throw new IllegalStateException("File /report/ReimbursementReport.jrxml tidak ditemukan di folder src/report.");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("tanggalCetak", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            parameters.put("pencetak", getUserName());
            parameters.put("periode", txtPeriode.getText() == null ? "" : txtPeriode.getText().trim());
            BigDecimal totalNominal = BigDecimal.ZERO;
            for (int r = 0; r < tabmode.getRowCount(); r++) {
                totalNominal = totalNominal.add(toBigDecimal(tabmode.getValueAt(r, 9)));
            }
            parameters.put("summary1", "Total Reimbursement : " + tabmode.getRowCount());
            parameters.put("summary2", "Total Nominal : " + totalNominal.toPlainString());

            java.util.LinkedHashMap<String, BigDecimal> projectTotals = new java.util.LinkedHashMap<>();
            for (int r = 0; r < tabmode.getRowCount(); r++) {
                String projectName = safe(tabmode.getValueAt(r, 3));
                if (projectName.isEmpty()) {
                    projectName = "-";
                }
                BigDecimal rowTotal = toBigDecimal(tabmode.getValueAt(r, 9));
                projectTotals.merge(projectName, rowTotal, BigDecimal::add);
            }

            java.text.DecimalFormat rupiahFormat = new java.text.DecimalFormat(
                "#,##0", new java.text.DecimalFormatSymbols(new java.util.Locale("id", "ID")));
            StringBuilder projectSummary = new StringBuilder();
            for (Map.Entry<String, BigDecimal> entry : projectTotals.entrySet()) {
                if (projectSummary.length() > 0) {
                    projectSummary.append("\n");
                }
                projectSummary.append("- ")
                    .append(entry.getKey())
                    .append(" = ")
                    .append(rupiahFormat.format(entry.getValue()));
            }
            parameters.put("projectSummary", projectSummary.toString());

            List<Map<String, Object>> dataList = new ArrayList<>();
            for (int r = 0; r < tabmode.getRowCount(); r++) {
                Map<String, Object> row = new HashMap<>();
                row.put("no", safe(tabmode.getValueAt(r, 0)));
                row.put("id", safe(tabmode.getValueAt(r, 1)));
                row.put("reimbursement_no", safe(tabmode.getValueAt(r, 2)));
                row.put("project", safe(tabmode.getValueAt(r, 3)));
                row.put("hari", safe(tabmode.getValueAt(r, 4)));
                row.put("perihal", safe(tabmode.getValueAt(r, 5)));
                row.put("pj", safe(tabmode.getValueAt(r, 6)));
                row.put("jumlah_item", safe(tabmode.getValueAt(r, 7)));
                row.put("uraian_preview", safe(tabmode.getValueAt(r, 8)));
                row.put("total", safe(tabmode.getValueAt(r, 9)));
                dataList.add(row);
            }

            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource((Collection) dataList);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, outFile.getAbsolutePath());

            JOptionPane.showMessageDialog(this,
                "Export PDF berhasil!\n" + outFile.getAbsolutePath(),
                "Sukses", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            showError("Export gagal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String safe(Object value) {
        return value == null ? "" : value.toString();
    }

    private BigDecimal toBigDecimal(Object value) {
        if (value == null) return BigDecimal.ZERO;
        if (value instanceof BigDecimal) return (BigDecimal) value;
        try {
            return new BigDecimal(value.toString().replaceAll("[^0-9.-]", ""));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private ImageIcon loadIcon(String path, int size) {
        try {
            java.net.URL url = getClass().getResource(path);
            if (url == null) return null;
            Image image = new ImageIcon(url).getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ReimbursementReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReimbursementReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedButton btnExport;
    private components.RoundedButton btnGenerate;
    private javax.swing.JLabel jLabel1;
    private components.PageTitle pageTitle1;
    private components.SidebarMenu sidebarMenu1;
    private components.RoundedTablePanel tblProjectList;
    private components.AppTextField txtPeriode;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}