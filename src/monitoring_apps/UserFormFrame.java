package monitoring_apps;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.KoneksiDb;
import java.sql.Connection;


public class UserFormFrame extends javax.swing.JFrame {
    
     private String oldEmail = "";

    public UserFormFrame() {
        initComponents();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
    }

    public UserFormFrame(
            String nama,
            String jabatan,
            String divisi,
            String telepon,
            String alamat,
            String email,
            String password) {

        initComponents();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);

        this.oldEmail = email;

        txtNama.setText(nama);
        txtJabatan.setText(jabatan);
        cmbDivisi.setSelectedItem(divisi);
        txtTelepon.setText(telepon);
        txtAlamat.setText(alamat);
        txtEmail.setText(email);
        txtPassword.setText(password);
    

    btnSave.setEnabled(false); // mode edit
    }

    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {

        }
    });
}
    protected void kosong(){
    txtNama.setText("");
    txtJabatan.setText("");
    txtTelepon.setText("");
    txtAlamat.setText("");
    txtEmail.setText("");
    txtPassword.setText("");
    cmbDivisi.setSelectedIndex(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        lblNamaPengguna = new javax.swing.JLabel();
        txtNama = new components.RoundedTextField();
        lblDivisi = new javax.swing.JLabel();
        cmbDivisi = new components.RoundedComboBox();
        lblJabatan = new javax.swing.JLabel();
        txtJabatan = new components.RoundedTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new components.RoundedTextField();
        lblNomorTelepon = new javax.swing.JLabel();
        txtTelepon = new components.RoundedTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new components.RoundedTextField();
        lblAlamat = new javax.swing.JLabel();
        txtAlamat = new components.RoundedTextField();
        btnSave = new components.RoundedButton();
        btnClear = new components.RoundedButton();
        btnUpdate = new components.RoundedButton();
        btnBack = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORM USER");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("FORM USER");

        lblNamaPengguna.setText("Nama Pengguna");

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(UserFormFrame.this, new Dashboard());
                txtNamaActionPerformed(evt);
            }
        });

        lblDivisi.setText("Divisi");

        lblJabatan.setText("Jabatan");

        lblPassword.setText("Masukkan Password Baru");

        lblNomorTelepon.setText("Nomor Telepon");

        lblEmail.setText("Email");

        lblAlamat.setText("Alamat");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userProfileCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sidebarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJabatan)
                            .addComponent(txtJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNomorTelepon)
                            .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAlamat)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNamaPengguna)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDivisi)
                            .addComponent(cmbDivisi, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPassword)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNamaPengguna)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblJabatan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNomorTelepon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDivisi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDivisi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lblAlamat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String nama = txtNama.getText().trim();
    String jabatan = txtJabatan.getText().trim();
    String divisi = cmbDivisi.getSelectedItem().toString();
    String telepon = txtTelepon.getText().trim();
    String alamat = txtAlamat.getText().trim();
    String email = txtEmail.getText().trim();
    String password = txtPassword.getText().trim();

    // Validasi form
    if (nama.isEmpty() ||
        jabatan.isEmpty() ||
        telepon.isEmpty() ||
        alamat.isEmpty() ||
        email.isEmpty() ||
        password.isEmpty() ||
        divisi.equals("Pilih")) {

        JOptionPane.showMessageDialog(
                this,
                "Semua data wajib diisi!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);

        return; // Hentikan proses simpan
    }

    String sql = "INSERT INTO user (nama, jabatan, divisi, no_telp, alamat, email, password) VALUES (?,?,?,?,?,?,?)";

    try {
        Connection conn = new KoneksiDb().connect();
        PreparedStatement stat = conn.prepareStatement(sql);

        stat.setString(1, nama);
        stat.setString(2, jabatan);
        stat.setString(3, divisi);
        stat.setString(4, telepon);
        stat.setString(5, alamat);
        stat.setString(6, email);
        stat.setString(7, password);

        stat.executeUpdate();

        JOptionPane.showMessageDialog(
                this,
                "Data berhasil disimpan!",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);

        kosong();
        txtNama.requestFocus();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
    txtNama.setText("");
    txtJabatan.setText("");
    txtTelepon.setText("");
    txtAlamat.setText("");
    txtEmail.setText("");
    txtPassword.setText("");

    cmbDivisi.setSelectedIndex(0);

    txtNama.requestFocus();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
    Dashboard dashboard = new Dashboard();
    dashboard.setVisible(true);

    this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        String sql = "UPDATE user SET "
           + "nama=?, "
           + "jabatan=?, "
           + "divisi=?, "
           + "no_telp=?, "
           + "alamat=?, "
           + "email=?, "
           + "password=? "
           + "WHERE email=?";
    String nama = txtNama.getText();
    String jabatan = txtJabatan.getText();
    String divisi = cmbDivisi.getSelectedItem().toString();
    String telepon = txtTelepon.getText();
    String alamat = txtAlamat.getText();
    String email = txtEmail.getText();
    String password = txtPassword.getText();
        try {
            Connection conn = new KoneksiDb().connect();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, nama);
            stat.setString(2, jabatan);
            stat.setString(3, divisi);
            stat.setString(4, telepon);
            stat.setString(5, alamat);
            stat.setString(6, email);
            stat.setString(7, password);
            stat.setString(8, oldEmail);

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            txtNama.requestFocus();

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    

    if(nama.isEmpty()){
        JOptionPane.showMessageDialog(this,
                "Data belum lengkap!");
    }else{
        JOptionPane.showMessageDialog(this,
                "Data berhasil disimpan");
                
        // Tambahkan query INSERT database di sini
    }
    
    }//GEN-LAST:event_btnUpdateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedButton btnBack;
    private components.RoundedButton btnClear;
    private components.RoundedButton btnSave;
    private components.RoundedButton btnUpdate;
    private components.RoundedComboBox cmbDivisi;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblDivisi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblJabatan;
    private javax.swing.JLabel lblNamaPengguna;
    private javax.swing.JLabel lblNomorTelepon;
    private javax.swing.JLabel lblPassword;
    private components.PageTitle pageTitle1;
    private components.SidebarMenu sidebarMenu1;
    private components.RoundedTextField txtAlamat;
    private components.RoundedTextField txtEmail;
    private components.RoundedTextField txtJabatan;
    private components.RoundedTextField txtNama;
    private components.RoundedTextField txtPassword;
    private components.RoundedTextField txtTelepon;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
