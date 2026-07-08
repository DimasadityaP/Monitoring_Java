package monitoring_apps;

import static Utills.UserSession.getUserName;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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

public class ProjectReport extends javax.swing.JFrame {
    private Connection conn = new KoneksiDb().connect() ;
    private DefaultTableModel tabmode;

    public ProjectReport() {
        initComponents();
        getContentPane().setBackground(java.awt.Color.WHITE);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        initUi();
        initTable();
        setColumnWidths();
        Navigation.bind(sidebarMenu1, this);
    }
    
    private void initUi(){
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
    
    
    private void initTable(){
        Object[] columns = {"#","ID","Nama","TA","Sub Perusahaan","Jenis","Instansi","Tgl Mulai","Tgl Selesai","Nominal","Status"};
        tblProjectList.setTableData(new Object[0][columns.length], columns);
        tabmode = (DefaultTableModel) tblProjectList.getModel();
    }

    private void generateReport() {
        loadProjects(txtPeriode.getText());
    }

    private void loadProjects(String periode) {
        Object[] columns = {"#","ID","Nama","TA","Sub Perusahaan","Jenis","Instansi","Tgl Mulai","Tgl Selesai","Nominal","Status"};
        tblProjectList.setTableData(new Object[0][columns.length], columns);
        tabmode = (DefaultTableModel) tblProjectList.getModel();

        String query = "SELECT id,nama,ta,sub_perusahaan,jenis,instansi,tgl_mulai,tgl_selesai,nominal,status FROM project";
        if (periode != null && !periode.trim().isEmpty()) {
            query += " WHERE ta LIKE ?";
        }
        query += " ORDER BY created_at DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            if (periode != null && !periode.trim().isEmpty()) {
                ps.setString(1, "%" + periode.trim() + "%");
            }
            ResultSet rs = ps.executeQuery();
            int no = 1;
            while (rs.next()) {
                tabmode.addRow(new Object[]{
                    no++,
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("ta"),
                    rs.getString("sub_perusahaan"),
                    rs.getString("jenis"),
                    rs.getString("instansi"),
                    rs.getString("tgl_mulai"),
                    rs.getString("tgl_selesai"),
                    rs.getBigDecimal("nominal"),
                    rs.getString("status")
                });
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Gagal", JOptionPane.WARNING_MESSAGE) ;
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

        int[] widths = {
            50, 150, 240, 70, 180, 140, 180, 120, 120, 150, 110
        };

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

        pageTitle1.setText("REPORT PROJECT ");

        btnGenerate.setText("Generate Report");

        btnExport.setText("Export Report");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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
        chooser.setDialogTitle("Simpan Report Project");
        chooser.setSelectedFile(new File("ReportProject.pdf"));
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "PDF Files (*.pdf)", "pdf"));
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

        File outFile = chooser.getSelectedFile();
        if (!outFile.getName().toLowerCase().endsWith(".pdf")) {
            outFile = new File(outFile.getAbsolutePath() + ".pdf");
        }

        try {
            // Load template
            InputStream reportStream = getClass().getResourceAsStream("/report/ProjectReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Hitung summary
            int selesaiCount = 0, progressCount = 0;
            BigDecimal selesaiNominal = BigDecimal.ZERO;
            BigDecimal progressNominal = BigDecimal.ZERO;

            for (int r = 0; r < tabmode.getRowCount(); r++) {
                String status = tabmode.getValueAt(r, 10) == null ? "" : tabmode.getValueAt(r, 10).toString().trim();
                BigDecimal nom = toBigDecimal(tabmode.getValueAt(r, 9));
                if ("Selesai".equalsIgnoreCase(status)) {
                    selesaiCount++;
                    selesaiNominal = selesaiNominal.add(nom);
                } else {
                    progressCount++;
                    progressNominal = progressNominal.add(nom);
                }
            }

            String tanggal  = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String pencetak = getUserName();
            String periode  = txtPeriode.getText().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // Set parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("tanggalCetak", tanggal);
            parameters.put("pencetak", pencetak);
            parameters.put("periode", periode);
            parameters.put("countSelesai", selesaiCount);
            parameters.put("countProgress", progressCount);
            parameters.put("nominalSelesai", selesaiNominal);
            parameters.put("nominalProgress", progressNominal);

            // Data source dari table
            List<Map<String, Object>> dataList = new ArrayList<>();
            for (int r = 0; r < tabmode.getRowCount(); r++) {
                Map<String, Object> row = new HashMap<>();
                row.put("no", r + 1);
                row.put("id", tabmode.getValueAt(r, 1) == null ? "" : tabmode.getValueAt(r, 1).toString());
                row.put("nama", tabmode.getValueAt(r, 2) == null ? "" : tabmode.getValueAt(r, 2).toString());
                row.put("ta", tabmode.getValueAt(r, 3) == null ? "" : tabmode.getValueAt(r, 3).toString());
                row.put("sub_perusahaan", tabmode.getValueAt(r, 4) == null ? "" : tabmode.getValueAt(r, 4).toString());
                row.put("jenis", tabmode.getValueAt(r, 5) == null ? "" : tabmode.getValueAt(r, 5).toString());
                row.put("instansi", tabmode.getValueAt(r, 6) == null ? "" : tabmode.getValueAt(r, 6).toString());
                row.put("tgl_mulai",sdf.parse(tabmode.getValueAt(r, 7).toString()));
                row.put("tgl_selesai",sdf.parse(tabmode.getValueAt(r, 8).toString()));
                row.put("nominal", toBigDecimal(tabmode.getValueAt(r, 9)));
                row.put("status", tabmode.getValueAt(r, 10) == null ? "" : tabmode.getValueAt(r, 10).toString());
                dataList.add(row);
            }

            // Generate PDF
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



    private BigDecimal toBigDecimal(Object value) {
        if (value == null) return BigDecimal.ZERO;
        if (value instanceof BigDecimal) return (BigDecimal) value;
        try {
            return new BigDecimal(value.toString().replaceAll("[^0-9.-]", ""));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private String formatCurrency(BigDecimal value) {
        DecimalFormatSymbols sym = new DecimalFormatSymbols();
        sym.setGroupingSeparator('.');
        return new DecimalFormat("#,##0", sym).format(value == null ? BigDecimal.ZERO : value);
    }

    private String formatDateForExport(String rawDate) {
        if (rawDate == null || rawDate.trim().isEmpty()) return "";
        for (String pattern : new String[]{"yyyy-MM-dd", "yyyy/MM/dd"}) {
            try {
                return LocalDate.parse(rawDate.trim(), DateTimeFormatter.ofPattern(pattern))
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (Exception ignored) {}
        }
        return rawDate;
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
            java.util.logging.Logger.getLogger(ProjectReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectReport().setVisible(true);
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