package monitoring_apps;

public class UserFormFrame extends javax.swing.JFrame {

    public UserFormFrame() {
        initComponents();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        lblNamaPengguna = new javax.swing.JLabel();
        txtNamaPengguna = new components.RoundedTextField();
        lblDivisi = new javax.swing.JLabel();
        cmbDivisi = new components.RoundedComboBox();
        lblJabatan = new javax.swing.JLabel();
        txtJabatan = new components.RoundedTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new components.RoundedTextField();
        lblNomorTelepon = new javax.swing.JLabel();
        txtNomorTelepon = new components.RoundedTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new components.RoundedTextField();
        lblAlamat = new javax.swing.JLabel();
        txtAlamat = new components.RoundedTextField();
        btnSave = new components.RoundedButton();
        btnClear = new components.RoundedButton();
        btnCreate = new components.RoundedButton();
        btnBack = new components.RoundedButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORM USER");
        setMinimumSize(new java.awt.Dimension(1200, 800));
        getContentPane().setBackground(components.RoundedColors.BACKGROUND);

        pageTitle1.setText("FORM USER");
        lblNamaPengguna.setText("Nama Pengguna");
        lblNamaPengguna.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblNamaPengguna.setForeground(components.RoundedColors.TEXT_DARK);
        lblDivisi.setText("Divisi");
        lblDivisi.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblDivisi.setForeground(components.RoundedColors.TEXT_DARK);
        lblJabatan.setText("Jabatan");
        lblJabatan.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblJabatan.setForeground(components.RoundedColors.TEXT_DARK);
        lblPassword.setText("Masukkan Password Baru");
        lblPassword.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblPassword.setForeground(components.RoundedColors.TEXT_DARK);
        lblNomorTelepon.setText("Nomor Telepon");
        lblNomorTelepon.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblNomorTelepon.setForeground(components.RoundedColors.TEXT_DARK);
        lblEmail.setText("Email");
        lblEmail.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblEmail.setForeground(components.RoundedColors.TEXT_DARK);
        lblAlamat.setText("Alamat");
        lblAlamat.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblAlamat.setForeground(components.RoundedColors.TEXT_DARK);
        btnSave.setText("Save");
        btnClear.setText("Clear");
        btnCreate.setText("Create");
        btnBack.setText("Back");
        btnBack.setButtonColor(components.RoundedColors.SOFT_GRAY);
        btnBack.setForeground(java.awt.Color.BLACK);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(UserFormFrame.this, new DashboardAdministrationFrame());
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNamaPengguna)
                            .addComponent(txtNamaPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDivisi)
                            .addComponent(cmbDivisi, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJabatan)
                            .addComponent(txtJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPassword)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomorTelepon)
                            .addComponent(txtNomorTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblAlamat)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(lblNamaPengguna)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNamaPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDivisi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDivisi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblJabatan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNomorTelepon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomorTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAlamat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.UserProfileCard userProfileCard1;
    private components.SidebarMenu sidebarMenu1;
    private components.PageTitle pageTitle1;
    private javax.swing.JLabel lblNamaPengguna;
    private components.RoundedTextField txtNamaPengguna;
    private javax.swing.JLabel lblDivisi;
    private components.RoundedComboBox cmbDivisi;
    private javax.swing.JLabel lblJabatan;
    private components.RoundedTextField txtJabatan;
    private javax.swing.JLabel lblPassword;
    private components.RoundedTextField txtPassword;
    private javax.swing.JLabel lblNomorTelepon;
    private components.RoundedTextField txtNomorTelepon;
    private javax.swing.JLabel lblEmail;
    private components.RoundedTextField txtEmail;
    private javax.swing.JLabel lblAlamat;
    private components.RoundedTextField txtAlamat;
    private components.RoundedButton btnSave;
    private components.RoundedButton btnClear;
    private components.RoundedButton btnCreate;
    private components.RoundedButton btnBack;
    // End of variables declaration//GEN-END:variables
}
