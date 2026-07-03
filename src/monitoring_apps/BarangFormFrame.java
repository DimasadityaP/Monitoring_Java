package monitoring_apps;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.KoneksiDb;
import java.sql.Connection;

public class BarangFormFrame extends javax.swing.JFrame {
    private Connection conn = new KoneksiDb().connect() ;
    public BarangFormFrame(){
        setContentPane(new components.ScrollablePanel());
    initComponents();
        getContentPane().setBackground(java.awt.Color.WHITE);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        wrapInScrollPane();
    setLocationRelativeTo(null);
    Navigation.bind(sidebarMenu1, this);

    kosong();
    btnSave.setEnabled(true);
    btnUpdate.setEnabled(false);
    }
    
    public BarangFormFrame(
        String kode,
        String nama,
        String spek,
        String jenis,
        int qty,
        String satuan,
        String kondisi) {
        setContentPane(new components.ScrollablePanel());
        initComponents();
        getContentPane().setBackground(java.awt.Color.WHITE);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        wrapInScrollPane();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);

    txtKode.setText(kode);
    txtNamaBarang.setText(nama);
    txtSpesifikasi.setText(spek);
    cmbJenis.setSelectedItem(jenis);
    txtKuantitas.setText(String.valueOf(qty));
    cmbSatuan.setSelectedItem(satuan);
    txtKondisi.setText(kondisi);

    txtKode.setEditable(true);
    btnSave.setEnabled(false);
    btnUpdate.setEnabled(true);
}
       
    protected void kosong() {
    txtKode.setText("");    
    txtNamaBarang.setText("");
    txtSpesifikasi.setText("");
    txtKondisi.setText("");
    txtKuantitas.setText("");
    txtJenisSub.setText("");
    cmbSatuan.setSelectedIndex(0);
    cmbJenis.setSelectedIndex(0);
    txtKode.setEditable(true);
}
    

 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        lblKode = new javax.swing.JLabel();
        txtKode = new components.RoundedTextField();
        lblJenis = new javax.swing.JLabel();
        cmbJenis = new components.RoundedComboBox();
        lblNamaBarang = new javax.swing.JLabel();
        txtNamaBarang = new components.RoundedTextField();
        lblJenisSub = new javax.swing.JLabel();
        txtJenisSub = new components.RoundedTextField();
        lblSpesifikasi = new javax.swing.JLabel();
        txtSpesifikasi = new components.RoundedTextArea();
        lblKondisi = new javax.swing.JLabel();
        txtKondisi = new components.RoundedTextField();
        lblKuantitas = new javax.swing.JLabel();
        txtKuantitas = new components.RoundedTextField();
        lblSatuan = new javax.swing.JLabel();
        btnSave = new components.RoundedButton();
        btnUpdate = new components.RoundedButton();
        btnClear = new components.RoundedButton();
        btnBack = new components.RoundedButton();
        cmbSatuan = new components.AppComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORMULIR LOGISTIK");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText(" FORMULIR LOGISTIK");

        lblKode.setText("Kode");

        lblJenis.setText("Jenis");

        cmbJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenisActionPerformed(evt);
            }
        });

        lblNamaBarang.setText("Nama Barang");

        lblJenisSub.setText("Jenis Sub");

        lblSpesifikasi.setText("Spesifikasi");

        lblKondisi.setText("Kondisi");

        lblKuantitas.setText("Kuantitas");

        lblSatuan.setText("Satuan");
        cmbSatuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Pilih Satuan", "Unit", "Pcs", "Box", "Meter", "Set"}));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnBack.setForeground(new java.awt.Color(0, 0, 0));
        btnBack.setText("Back");
        btnBack.setButtonColor(new java.awt.Color(217, 217, 217));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userProfileCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sidebarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSpesifikasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSpesifikasi)
                            .addComponent(txtNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtKode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNamaBarang)
                            .addComponent(lblKode)
                            .addComponent(lblKuantitas)
                            .addComponent(txtKuantitas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblJenis)
                                .addComponent(cmbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtJenisSub, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblJenisSub))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblKondisi)
                                .addComponent(txtKondisi, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblSatuan)
                            .addComponent(cmbSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
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
                                .addComponent(lblKode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(lblNamaBarang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSpesifikasi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSpesifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblKuantitas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblJenis)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblJenisSub)
                                .addGap(1, 1, 1)
                                .addComponent(txtJenisSub, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(lblKondisi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKondisi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSatuan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

    String kode = txtKode.getText().trim();
    String namabarang = txtNamaBarang.getText().trim();
    String spesifikasi = txtSpesifikasi.getText().trim();
    String kondisi = txtKondisi.getText().trim();
    String kuantitas = txtKuantitas.getText().trim();
    String jenissub = txtJenisSub.getText().trim();
    String satuan = cmbSatuan.getSelectedItem().toString();
    String jenis = cmbJenis.getSelectedItem().toString();

    if (kode.isEmpty() ||
        namabarang.isEmpty() ||
        spesifikasi.isEmpty() ||
        kondisi.isEmpty() ||
        kuantitas.isEmpty() ||
        jenissub.isEmpty() ||
        satuan.equals("Pilih Satuan") ||
        jenis.equals("Pilih") ) {

        JOptionPane.showMessageDialog(
                this,
                "Semua data wajib diisi!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);

        return;
    }

    String sql =
    "INSERT INTO barang (kode, nama, spek, kondisi, qty, satuan, jenis) " +
    "VALUES (?,?,?,?,?,?,?)";

    try {
        Connection conn = new KoneksiDb().connect();
        PreparedStatement stat = conn.prepareStatement(sql);

        stat.setString(1, kode);
        stat.setString(2, namabarang);
        stat.setString(3, spesifikasi);
        stat.setString(4, kondisi);
        stat.setInt(5, Integer.parseInt(kuantitas));
        stat.setString(6, satuan);
        stat.setString(7, jenis);

        stat.executeUpdate();

        JOptionPane.showMessageDialog(this,
                "Data berhasil disimpan!");

        Navigation.go(this, new BarangList());

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
                "Kuantitas harus berupa angka!");
    } catch (Exception e) {
        e.printStackTrace();

        JOptionPane.showMessageDialog(
            this,
            "ERROR:\n" + e.getMessage()
        );
    }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    String sql =
    "UPDATE barang SET " +
    "nama=?, " +
    "spek=?, " +
    "jenis=?, " +
    "qty=?, " +
    "satuan=?, " +
    "kondisi=? " +
    "WHERE kode=?";
        String kode = txtKode.getText().trim();
        String namabarang = txtNamaBarang.getText().trim();
        String spesifikasi = txtSpesifikasi.getText().trim();
        String kondisi = txtKondisi.getText().trim();
        String kuantitas = txtKuantitas.getText().trim();
        String satuan = cmbSatuan.getSelectedItem().toString();
        String jenis = cmbJenis.getSelectedItem().toString();
        if (kode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan 'Kode' barang yang ingin diubah!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Connection conn = new KoneksiDb().connect();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, namabarang);
            stat.setString(2, spesifikasi);
            stat.setString(3, jenis);
            stat.setInt(4, Integer.parseInt(kuantitas));
            stat.setString(5, satuan);
            stat.setString(6, kondisi);
            stat.setString(7, kode);    

            int rows = stat.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Diubah", "Sukses", JOptionPane.INFORMATION_MESSAGE);
              
            Navigation.go(this, new BarangList());
            } else {
                JOptionPane.showMessageDialog(this, "Kode barang '" + kode + "' tidak ditemukan.", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Kolom Kuantitas harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
    kosong();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
      Navigation.go(this, new BarangList());
    }//GEN-LAST:event_btnBackActionPerformed

    private void cmbJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJenisActionPerformed
    public static void main(String[] args) {
                java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BarangFormFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedButton btnBack;
    private components.RoundedButton btnClear;
    private components.RoundedButton btnSave;
    private components.RoundedButton btnUpdate;
    private components.RoundedComboBox cmbJenis;
    private components.AppComboBox cmbSatuan;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblJenisSub;
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblKondisi;
    private javax.swing.JLabel lblKuantitas;
    private javax.swing.JLabel lblNamaBarang;
    private javax.swing.JLabel lblSatuan;
    private javax.swing.JLabel lblSpesifikasi;
    private components.PageTitle pageTitle1;
    private components.SidebarMenu sidebarMenu1;
    private components.RoundedTextField txtJenisSub;
    private components.RoundedTextField txtKode;
    private components.RoundedTextField txtKondisi;
    private components.RoundedTextField txtKuantitas;
    private components.RoundedTextField txtNamaBarang;
    private components.RoundedTextArea txtSpesifikasi;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables

    private void wrapInScrollPane() {
        components.ScrollablePanel originalPane = (components.ScrollablePanel) getContentPane();
        originalPane.setBackground(java.awt.Color.WHITE);
        originalPane.setBackground(java.awt.Color.WHITE);
        setContentPane(new javax.swing.JPanel());
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(originalPane);
        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setBackground(java.awt.Color.WHITE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(java.awt.Color.WHITE);
        scrollPane.setBackground(java.awt.Color.WHITE);
        setContentPane(scrollPane);
    }

}
