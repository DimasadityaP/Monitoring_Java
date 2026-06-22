package monitoring_apps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import koneksi.KoneksiDb;

public class UserListFrame1 extends javax.swing.JFrame {
    
    private String selectedNama;
    private String selectedJabatan;
    private String selectedDivisi;
    private String selectedEmail;
    private String selectedTelepon;
    private String selectedAlamat;
    private String selectedPassword;

    public UserListFrame1() {
       initComponents();
       
       roundedTablePanel1.getTable().addMouseListener(
        new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                int row = roundedTablePanel1.getTable().getSelectedRow();

                if (row != -1) {
                    selectedNama    = roundedTablePanel1.getTable().getValueAt(row, 0).toString();
                    selectedJabatan = roundedTablePanel1.getTable().getValueAt(row, 1).toString();
                    selectedDivisi  = roundedTablePanel1.getTable().getValueAt(row, 2).toString();
                    selectedEmail   = roundedTablePanel1.getTable().getValueAt(row, 3).toString();
                    selectedTelepon = roundedTablePanel1.getTable().getValueAt(row, 4).toString();
                    selectedAlamat  = roundedTablePanel1.getTable().getValueAt(row, 5).toString();
                    selectedPassword  = roundedTablePanel1.getTable().getValueAt(row, 6).toString();
                }
            }
        }
    );
        pageTitle1.setText("DAFTAR USER");
        getContentPane().setBackground(components.RoundedColors.BACKGROUND);
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
        
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(UserListFrame1.this, new Dashboard());
            }
        });

        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(UserListFrame1.this, new UserFormFrame());
            }
        });

        loadData();
    }
    
    private void loadData() {
        javax.swing.table.DefaultTableModel model = roundedTablePanel1.getModel();
    model.setRowCount(0);

    model.setColumnIdentifiers(new Object[]{
        "Nama Pengguna",
        "Jabatan",
        "Divisi",
        "Email",
        "No. Telepon",
        "Alamat",
        "Password"
    });

    String sql = "SELECT nama, jabatan, divisi, email, no_telp, alamat, password " +
                 "FROM user ORDER BY nama ASC";

    try (
        Connection conn = new KoneksiDb().connect();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
    ) {

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("nama"),
                rs.getString("jabatan"),
                rs.getString("divisi"),
                rs.getString("email"),
                rs.getString("no_telp"),
                rs.getString("alamat"),
                rs.getString("password")
            });
        }

    } catch (Exception e) {
        System.out.println("Gagal memuat data user: " + e.getMessage());
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        searchBox1 = new components.SearchBox();
        btnViewReport = new components.RoundedButton();
        btnNew = new components.RoundedButton();
        btnBack = new components.RoundedButton();
        roundedTablePanel1 = new components.RoundedTablePanel();
        btnEdit = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PROJECT ADMINISTRATION");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("USER");

        searchBox1.setText("Cari...");

        btnViewReport.setText("View Report");

        btnNew.setText("+ New Administration");

        btnBack.setForeground(new java.awt.Color(0, 0, 0));
        btnBack.setText("Back");
        btnBack.setButtonColor(new java.awt.Color(217, 217, 217));

        roundedTablePanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundedTablePanel1MouseClicked(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                    .addComponent(searchBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnViewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundedTablePanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(searchBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(roundedTablePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnViewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int row = roundedTablePanel1.getTable().getSelectedRow();

    if (row == -1) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Pilih data user terlebih dahulu!");
        return;
    }

    UserFormFrame form = new UserFormFrame(
            selectedNama,
            selectedJabatan,
            selectedDivisi,
            selectedTelepon,
            selectedAlamat,
            selectedEmail,
            selectedPassword
    );

    form.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnEditActionPerformed

    private void roundedTablePanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundedTablePanel1MouseClicked
        // TODO add your handling code here:
        int row = roundedTablePanel1.getTable().getSelectedRow();

    if (row != -1) {
        selectedNama    = roundedTablePanel1.getTable().getValueAt(row, 0).toString();
        selectedJabatan = roundedTablePanel1.getTable().getValueAt(row, 1).toString();
        selectedDivisi  = roundedTablePanel1.getTable().getValueAt(row, 2).toString();
        selectedEmail   = roundedTablePanel1.getTable().getValueAt(row, 3).toString();
        selectedTelepon = roundedTablePanel1.getTable().getValueAt(row, 4).toString();
        selectedAlamat  = roundedTablePanel1.getTable().getValueAt(row, 5).toString();
        selectedPassword  = roundedTablePanel1.getTable().getValueAt(row, 6).toString();
    }
    }//GEN-LAST:event_roundedTablePanel1MouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserListFrame1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedButton btnBack;
    private components.RoundedButton btnEdit;
    private components.RoundedButton btnNew;
    private components.RoundedButton btnViewReport;
    private components.PageTitle pageTitle1;
    private components.RoundedTablePanel roundedTablePanel1;
    private components.SearchBox searchBox1;
    private components.SidebarMenu sidebarMenu1;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
