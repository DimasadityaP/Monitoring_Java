package monitoring_apps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import koneksi.KoneksiDb;

public class MutasiBarangFormFrame extends javax.swing.JFrame {

    private static class BarangItem {
        int id;
        String nama;
        int qty;

        public BarangItem(int id, String nama, int qty) {
            this.id = id;
            this.nama = nama;
            this.qty = qty;
        }
    }

    private static class ProjectItem {
        int id;
        String nama;

        public ProjectItem(int id, String nama) {
            this.id = id;
            this.nama = nama;
        }
    }

    private final Map<String, BarangItem> barangMap = new HashMap<>();
    private final Map<String, ProjectItem> projectMap = new HashMap<>();

    public MutasiBarangFormFrame() {
        initComponents();
        getContentPane().setBackground(components.RoundedColors.BACKGROUND);
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);

        // Hide unused elements programmatically to match design expectations cleanly
        lblTanggalSurat.setVisible(false);
        txtTanggalSurat.setVisible(false);
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);

        // Set up Tipe ComboBox
        cmbTipe.removeAllItems();
        cmbTipe.addItem("masuk");
        cmbTipe.addItem("keluar");

        // Load barang & project dropdown lists
        loadComboBoxData();

        // Register Action Listeners
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
    }

    private void loadComboBoxData() {
        cmbBarang.removeAllItems();
        cmbProject.removeAllItems();

        cmbBarang.addItem("Pilih Barang");
        cmbProject.addItem("Pilih Project");

        barangMap.clear();
        projectMap.clear();

        String barangSql = "SELECT id, nama, qty FROM barang ORDER BY nama ASC";
        String projectSql = "SELECT id, nama FROM project ORDER BY nama ASC";

        try (
            Connection conn = new KoneksiDb().connect();
            Statement stmt = conn.createStatement();
        ) {
            // Load Barang
            try (ResultSet rs = stmt.executeQuery(barangSql)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nama = rs.getString("nama");
                    int qty = rs.getInt("qty");

                    String displayName = nama + " (Stok: " + qty + ")";
                    cmbBarang.addItem(displayName);
                    barangMap.put(displayName, new BarangItem(id, nama, qty));
                }
            }

            // Load Project
            try (ResultSet rs = stmt.executeQuery(projectSql)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nama = rs.getString("nama");

                    cmbProject.addItem(nama);
                    projectMap.put(nama, new ProjectItem(id, nama));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data relasi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedBarangKey = cmbBarang.getSelectedItem() != null ? cmbBarang.getSelectedItem().toString() : "";
        String selectedProjectKey = cmbProject.getSelectedItem() != null ? cmbProject.getSelectedItem().toString() : "";
        String tipe = cmbTipe.getSelectedItem() != null ? cmbTipe.getSelectedItem().toString() : "";
        String qtyText = txtQty.getText().trim();
        String keterangan = txtKeterangan.getText().trim();

        // Validate selections
        if (selectedBarangKey.equals("Pilih Barang") || selectedBarangKey.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan pilih barang!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedProjectKey.equals("Pilih Project") || selectedProjectKey.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan pilih project/tujuan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        BarangItem barangItem = barangMap.get(selectedBarangKey);
        ProjectItem projectItem = projectMap.get(selectedProjectKey);

        if (barangItem == null || projectItem == null) {
            JOptionPane.showMessageDialog(this, "Data barang atau project tidak valid!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate qty
        int qtyVal;
        try {
            qtyVal = Integer.parseInt(qtyText);
            if (qtyVal <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Qty harus berupa angka bulat positif!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate stock boundary for keluar
        if (tipe.equals("keluar")) {
            if (qtyVal > barangItem.qty) {
                JOptionPane.showMessageDialog(this,
                    "Stok tidak mencukupi! Stok saat ini: " + barangItem.qty,
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Transaction Save & Stock Update
        Connection conn = null;
        try {
            conn = new KoneksiDb().connect();
            conn.setAutoCommit(false); // start transaction

            // Insert mutasi_stok
            String insertSql = "INSERT INTO mutasi_stok (barang_id, project_id, qty, tipe, keterangan) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
                insertPs.setInt(1, barangItem.id);
                insertPs.setInt(2, projectItem.id);
                insertPs.setInt(3, qtyVal);
                insertPs.setString(4, tipe);
                insertPs.setString(5, keterangan.isEmpty() ? null : keterangan);
                insertPs.executeUpdate();
            }

            // Update barang qty
            String updateSql;
            if (tipe.equals("keluar")) {
                updateSql = "UPDATE barang SET qty = qty - ? WHERE id = ?";
            } else {
                updateSql = "UPDATE barang SET qty = qty + ? WHERE id = ?";
            }

            try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                updatePs.setInt(1, qtyVal);
                updatePs.setInt(2, barangItem.id);
                updatePs.executeUpdate();
            }

            conn.commit(); // commit transaction
            JOptionPane.showMessageDialog(this, "Mutasi barang berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            Navigation.go(this, new MutasiBarangListFrame());

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("Rollback failed: " + ex.getMessage());
                }
            }
            JOptionPane.showMessageDialog(this, "Gagal menyimpan mutasi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Connection close failed: " + e.getMessage());
                }
            }
        }
    }

    private void clearForm() {
        if (cmbTipe.getItemCount() > 0) cmbTipe.setSelectedIndex(0);
        if (cmbBarang.getItemCount() > 0) cmbBarang.setSelectedIndex(0);
        if (cmbProject.getItemCount() > 0) cmbProject.setSelectedIndex(0);
        txtQty.setText("");
        txtKeterangan.setText("");
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        clearForm();
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        Navigation.go(this, new MutasiBarangListFrame());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        lblTipe = new javax.swing.JLabel();
        cmbTipe = new components.RoundedComboBox();
        lblBarang = new javax.swing.JLabel();
        cmbBarang = new components.RoundedComboBox();
        lblProject = new javax.swing.JLabel();
        cmbProject = new components.RoundedComboBox();
        lblQty = new javax.swing.JLabel();
        txtQty = new components.RoundedTextField();
        lblKeterangan = new javax.swing.JLabel();
        txtKeterangan = new components.RoundedTextField();
        lblTanggalSurat = new javax.swing.JLabel();
        txtTanggalSurat = new components.RoundedTextField();
        btnSave = new components.RoundedButton();
        btnUpdate = new components.RoundedButton();
        btnClear = new components.RoundedButton();
        btnDelete = new components.RoundedButton();
        btnBack = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORMULIR MUTASI BARANG");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("FORMULIR MUTASI BARANG");

        lblTipe.setText("Tipe");

        lblBarang.setText("Barang");

        lblProject.setText("Project / Tujuan");

        lblQty.setText("Qty");

        lblKeterangan.setText("Keterangan");

        lblTanggalSurat.setText("Tanggal Surat");

        btnSave.setText("Save");

        btnUpdate.setText("Update");

        btnClear.setText("Clear");

        btnDelete.setText("Delete");
        btnDelete.setButtonColor(new java.awt.Color(154, 61, 120));

        btnBack.setForeground(new java.awt.Color(0, 0, 0));
        btnBack.setText("Back");
        btnBack.setButtonColor(new java.awt.Color(217, 217, 217));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userProfileCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sidebarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipe)
                            .addComponent(cmbTipe, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBarang)
                            .addComponent(cmbBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblProject, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbProject, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblQty)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKeterangan)
                            .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTanggalSurat, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTanggalSurat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTipe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbTipe, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBarang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lblProject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProject, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblQty)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblKeterangan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lblTanggalSurat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTanggalSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedButton btnBack;
    private components.RoundedButton btnClear;
    private components.RoundedButton btnDelete;
    private components.RoundedButton btnSave;
    private components.RoundedButton btnUpdate;
    private components.RoundedComboBox cmbBarang;
    private components.RoundedComboBox cmbProject;
    private components.RoundedComboBox cmbTipe;
    private javax.swing.JLabel lblBarang;
    private javax.swing.JLabel lblKeterangan;
    private javax.swing.JLabel lblProject;
    private javax.swing.JLabel lblQty;
    private javax.swing.JLabel lblTanggalSurat;
    private javax.swing.JLabel lblTipe;
    private components.PageTitle pageTitle1;
    private components.SidebarMenu sidebarMenu1;
    private components.RoundedTextField txtKeterangan;
    private components.RoundedTextField txtQty;
    private components.RoundedTextField txtTanggalSurat;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
