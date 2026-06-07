package monitoring_apps;

public class LogisticFormFrame extends javax.swing.JFrame {

    public LogisticFormFrame() {
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
        txtSatuan = new components.RoundedTextField();
        btnSave = new components.RoundedButton();
        btnUpdate = new components.RoundedButton();
        btnClear = new components.RoundedButton();
        btnDelete = new components.RoundedButton();
        btnBack = new components.RoundedButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORMULIR LOGISTIK");
        setMinimumSize(new java.awt.Dimension(1200, 800));
        getContentPane().setBackground(components.RoundedColors.BACKGROUND);

        pageTitle1.setText("FORMULIR LOGISTIK");
        lblKode.setText("Kode");
        lblKode.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblKode.setForeground(components.RoundedColors.TEXT_DARK);
        lblJenis.setText("Jenis");
        lblJenis.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblJenis.setForeground(components.RoundedColors.TEXT_DARK);
        lblNamaBarang.setText("Nama Barang");
        lblNamaBarang.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblNamaBarang.setForeground(components.RoundedColors.TEXT_DARK);
        lblJenisSub.setText("Jenis Sub");
        lblJenisSub.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblJenisSub.setForeground(components.RoundedColors.TEXT_DARK);
        lblSpesifikasi.setText("Spesifikasi");
        lblSpesifikasi.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblSpesifikasi.setForeground(components.RoundedColors.TEXT_DARK);
        txtSpesifikasi.setLineWrap(true);
        txtSpesifikasi.setWrapStyleWord(true);
        lblKondisi.setText("Kondisi");
        lblKondisi.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblKondisi.setForeground(components.RoundedColors.TEXT_DARK);
        lblKuantitas.setText("Kuantitas");
        lblKuantitas.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblKuantitas.setForeground(components.RoundedColors.TEXT_DARK);
        lblSatuan.setText("Satuan");
        lblSatuan.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblSatuan.setForeground(components.RoundedColors.TEXT_DARK);
        btnSave.setText("Save");
        btnUpdate.setText("Update");
        btnClear.setText("Clear");
        btnDelete.setText("Delete");
        btnDelete.setButtonColor(components.RoundedColors.DELETE);
        btnBack.setText("Back");
        btnBack.setButtonColor(components.RoundedColors.SOFT_GRAY);
        btnBack.setForeground(java.awt.Color.BLACK);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(LogisticFormFrame.this, new ProjectAdministrationListFrame());
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
                            .addComponent(lblKode)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJenis)
                            .addComponent(cmbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNamaBarang)
                            .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJenisSub)
                            .addComponent(txtJenisSub, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblSpesifikasi)
                        .addComponent(txtSpesifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblKondisi)
                        .addComponent(txtKondisi, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKuantitas)
                            .addComponent(txtKuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSatuan)
                            .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
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
                                .addComponent(lblKode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblJenis)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNamaBarang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblJenisSub)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtJenisSub, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSpesifikasi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSpesifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblKondisi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKondisi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblKuantitas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSatuan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
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
    private components.UserProfileCard userProfileCard1;
    private components.SidebarMenu sidebarMenu1;
    private components.PageTitle pageTitle1;
    private javax.swing.JLabel lblKode;
    private components.RoundedTextField txtKode;
    private javax.swing.JLabel lblJenis;
    private components.RoundedComboBox cmbJenis;
    private javax.swing.JLabel lblNamaBarang;
    private components.RoundedTextField txtNamaBarang;
    private javax.swing.JLabel lblJenisSub;
    private components.RoundedTextField txtJenisSub;
    private javax.swing.JLabel lblSpesifikasi;
    private components.RoundedTextArea txtSpesifikasi;
    private javax.swing.JLabel lblKondisi;
    private components.RoundedTextField txtKondisi;
    private javax.swing.JLabel lblKuantitas;
    private components.RoundedTextField txtKuantitas;
    private javax.swing.JLabel lblSatuan;
    private components.RoundedTextField txtSatuan;
    private components.RoundedButton btnSave;
    private components.RoundedButton btnUpdate;
    private components.RoundedButton btnClear;
    private components.RoundedButton btnDelete;
    private components.RoundedButton btnBack;
    // End of variables declaration//GEN-END:variables
}
