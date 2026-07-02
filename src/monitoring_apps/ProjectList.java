package monitoring_apps;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.KoneksiDb;
import components.PlusIcon;

public class ProjectList extends javax.swing.JFrame {
    private Connection conn = new KoneksiDb().connect() ;
    private DefaultTableModel tabmode;
    private Timer searchTimer;



    public ProjectList() {
        initComponents();
        setLocationRelativeTo(null);
        initUi();
        dataTable();
        setColumnWidths();
        iniEvent();
        Navigation.bind(sidebarMenu1, this);
    }
    
    private void initUi(){
        btnNewAdministration.setText("Tambah");
        btnNewAdministration.setIcon(new PlusIcon(16));
        btnNewAdministration.setButtonColor(components.RoundedColors.PRIMARY);
        btnNewAdministration.setForeground(java.awt.Color.WHITE);

        tblProjectList.setEditAction("/image/edit.png", new components.RoundedTablePanel.ActionClickListener() {
                public void onActionClick(int row) {
                    String projectId = tabmode.getValueAt(row, 1).toString();
                    openEditForm(projectId);
                }
            });
 
        tblProjectList.setDeleteAction("/image/delete.png", new components.RoundedTablePanel.ActionClickListener() {
                public void onActionClick(int row) {
                    String projectId = tabmode.getValueAt(row, 1).toString();
                    String nama      = tabmode.getValueAt(row, 2).toString();
                    int confirm = JOptionPane.showConfirmDialog(
                        ProjectList.this,
                        "Yakin ingin menghapus project \"" + nama + "\"?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        deleteProject(projectId, row);
                    }
                }
            });
    }
 
    private void deleteProject(String projectId, int row) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM project WHERE id = ?");
            ps.setString(1, projectId);
            int result = ps.executeUpdate();
            if (result > 0) {
                tabmode.removeRow(row);
                JOptionPane.showMessageDialog(ProjectList.this,
                    "Project berhasil dihapus.", "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                showError("Project tidak ditemukan.");
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }
    
    private void iniEvent(){
        searchTimer = new Timer(300, e -> search());
        searchTimer.setRepeats(false);

        searchBox1.getTextField().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { searchTimer.restart(); }
            public void removeUpdate(DocumentEvent e) { searchTimer.restart(); }
            public void changedUpdate(DocumentEvent e) { searchTimer.restart(); }
        });
        
//         tblProjectList.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent e) {
//                int row = tblProjectList.getTable().rowAtPoint(e.getPoint());
//                int col = tblProjectList.getTable().columnAtPoint(e.getPoint());
//
//                // kolom "Aksi" adalah kolom terakhir (index 11)
//                if (row >= 0 && col == 11) {
//                    String projectId = tabmode.getValueAt(row, 1).toString(); // kolom ID
//                    openEditForm(projectId);
//                }
//            }
//        });
    }
    
    private void openEditForm(String projectId) {
        JFrame next = new ProjectFormFrame(projectId);
        next.pack();
        next.setLocationRelativeTo(this);
        next.setVisible(true);
        dispose();
    }
    
    private void search() {
        String keyword = searchBox1.getText();
        tabmode.setRowCount(0);

        String query =
                    "SELECT * FROM project " +
                    "WHERE id LIKE ? " +
                    "OR nama LIKE ? " +
                    "OR ta LIKE ? " +
                    "OR sub_perusahaan LIKE ? " +
                    "OR jenis LIKE ? " +
                    "OR instansi LIKE ? " +
                    "OR DATE_FORMAT(tgl_mulai,'%Y-%m-%d') LIKE ? " +
                    "OR DATE_FORMAT(tgl_selesai,'%Y-%m-%d') LIKE ? " +
                    "OR CAST(nominal AS CHAR) LIKE ? " +
                    "OR status LIKE ? " +
                    "ORDER BY created_at DESC";

        try{
            PreparedStatement ps = conn.prepareStatement(query);
            String likeKeyword = "%" + keyword + "%";

            for (int i = 1; i <= 10; i++) {
                ps.setString(i, likeKeyword);
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
                    rs.getString("status"),
                    null
                });
            }

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }
    
    private void dataTable(){
        Object[] columns = {"No","ID","Nama","TA","Sub Perusahaan","Jenis","Instansi","Tgl Mulai","Tgl Selesai","Nominal","Status"};
        tblProjectList.setTableData(new Object[0][columns.length], columns);
        tabmode = (DefaultTableModel) tblProjectList.getModel();

        String query = "SELECT id,nama,ta,sub_perusahaan,jenis,instansi,tgl_mulai,tgl_selesai,nominal,status FROM project ORDER BY created_at DESC";
        try{
            PreparedStatement ps = conn.prepareStatement(query);
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
                    rs.getString("status"),
                    null
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
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (Component comp : tblProjectList.getComponents()) {
            if (comp instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) comp;
                scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                break;
            }
        }

        int[] widths = {
            40, 130, 210, 55, 150, 100, 170, 100, 100, 140, 90, 70
        };

        for (int i = 0; i < widths.length && i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
            table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
            table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
        }

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        int aksiCol = table.getColumnCount() - 1;
        for (int i = 0; i < aksiCol; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        searchBox1 = new components.SearchBox();
        tblProjectList = new components.RoundedTablePanel();
        btnViewReport = new components.RoundedButton();
        btnNewAdministration = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monitoring Apps");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        btnViewReport.setText("View Report");

        btnNewAdministration.setText("+ New Administration");
        btnNewAdministration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewAdministrationActionPerformed(evt);
            }
        });

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
                    .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnViewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnNewAdministration, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tblProjectList, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userProfileCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sidebarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNewAdministration, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(tblProjectList, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewAdministrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewAdministrationActionPerformed
        JFrame next = new ProjectFormFrame();
        next.pack();
        next.setLocationRelativeTo(this);
        next.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnNewAdministrationActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ProjectList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedButton btnNewAdministration;
    private components.RoundedButton btnViewReport;
    private components.PageTitle pageTitle1;
    private components.SearchBox searchBox1;
    private components.SidebarMenu sidebarMenu1;
    private components.RoundedTablePanel tblProjectList;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
