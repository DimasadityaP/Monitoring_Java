package monitoring_apps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import koneksi.KoneksiDb;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.SQLException;

public class BarangList extends javax.swing.JFrame {

    public BarangList() {
        initComponents();
        pageTitle1.setText("List Barang");
        getContentPane().setBackground(components.RoundedColors.BACKGROUND);
        setLocationRelativeTo(null);
        
        
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(BarangList.this, new Dashboard());
            }
        });

        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(BarangList.this, new BarangFormFrame());
            }
        });

        loadData();
    }
   


    private void loadData() {
        javax.swing.table.DefaultTableModel model = appTablePanel1.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"Kode", "Nama", "Spek", "Jenis", "Qty", "Satuan", "Kondisi", "Tgl"});

        String sql = "SELECT * FROM barang";

        try (
            Connection conn = new KoneksiDb().connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                String kode = rs.getString("kode");
                String nama = rs.getString("nama");
                String spek = rs.getString("spek");
                String jenis = rs.getString("jenis");
                int qty = rs.getInt("qty");
                String satuan = rs.getString("satuan");
                String kondisi = rs.getString("kondisi");
                Timestamp tgl = rs.getTimestamp("created_at");
                
                model.addRow(new Object[]{
                    kode != null ? kode : "-",
                    nama != null ? nama : "-",
                    spek != null ? spek : "-",
                    jenis != null ? jenis : "-",
                    qty,
                    satuan != null ? satuan : "-",
                    kondisi != null ? kondisi : "-",
                    tgl != null ? tgl.toString() : "-"
                });
            }
        } catch (Exception e) {
            System.out.println("Gagal memuat data barang: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        searchBox1 = new components.SearchBox();
        appTablePanel1 = new components.RoundedTablePanel();
        btnNew = new components.RoundedButton();
        btnBack = new components.RoundedButton();
        btnUpdate = new components.RoundedButton();
        btnDelete = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MUTASI BARANG");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("MUTASI BARANG");

        searchBox1.setText("Cari...");

        btnNew.setText("Tambah Barang");

        btnBack.setForeground(new java.awt.Color(0, 0, 0));
        btnBack.setText("Back");
        btnBack.setButtonColor(new java.awt.Color(217, 217, 217));

        btnUpdate.setText("Edit");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.setButtonColor(new java.awt.Color(154, 61, 120));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sidebarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(searchBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(appTablePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userProfileCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
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
                        .addGap(30, 30, 30)
                        .addComponent(appTablePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

    javax.swing.JTable table = appTablePanel1.getTable();

    int row = table.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(this,
                "Pilih data yang akan diedit!");
        return;
    }

    String kode = table.getValueAt(row, 0).toString();
    String nama = table.getValueAt(row, 1).toString();
    String spek = table.getValueAt(row, 2).toString();
    String jenis = table.getValueAt(row, 3).toString();
    int qty = Integer.parseInt(table.getValueAt(row, 4).toString());
    String satuan = table.getValueAt(row, 5).toString();
    String kondisi = table.getValueAt(row, 6).toString();

    Navigation.go(this,
        new BarangFormFrame(
            kode,
            nama,
            spek,
            jenis,
            qty,
            satuan,
            kondisi
        )
    );
    

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    JTable table = appTablePanel1.getTable();

    int row = table.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Pilih data yang akan dihapus!");
        return;
    }

    String kode = table.getValueAt(row, 0).toString();

    int jawab = JOptionPane.showConfirmDialog(
            this,
            "Yakin ingin menghapus barang dengan kode " + kode + "?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION);

    if (jawab == JOptionPane.YES_OPTION) {

        try {
            Connection conn = new KoneksiDb().connect();

            String sql = "DELETE FROM barang WHERE kode=?";

            PreparedStatement stat =
                    conn.prepareStatement(sql);

            stat.setString(1, kode);

            int hasil = stat.executeUpdate();

            if (hasil > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Data berhasil dihapus");

                loadData(); // refresh tabel

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Data tidak ditemukan");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage());
        }
    }

    }//GEN-LAST:event_btnDeleteActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BarangList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedTablePanel appTablePanel1;
    private components.RoundedButton btnBack;
    private components.RoundedButton btnDelete;
    private components.RoundedButton btnNew;
    private components.RoundedButton btnUpdate;
    private components.PageTitle pageTitle1;
    private components.SearchBox searchBox1;
    private components.SidebarMenu sidebarMenu1;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
