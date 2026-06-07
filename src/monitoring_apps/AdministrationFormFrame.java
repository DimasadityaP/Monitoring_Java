package monitoring_apps;

public class AdministrationFormFrame extends javax.swing.JFrame {

    public AdministrationFormFrame() {
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
        lblTipeSurat = new javax.swing.JLabel();
        cmbTipeSurat = new components.RoundedComboBox();
        lblNamaSurat = new javax.swing.JLabel();
        txtNamaSurat = new components.RoundedTextField();
        lblNomorSurat = new javax.swing.JLabel();
        txtNomorSurat = new components.RoundedTextField();
        lblDari = new javax.swing.JLabel();
        txtDari = new components.RoundedTextField();
        lblKepada = new javax.swing.JLabel();
        txtKepada = new components.RoundedTextField();
        lblTanggalSurat = new javax.swing.JLabel();
        txtTanggalSurat = new components.RoundedTextField();
        btnSave = new components.RoundedButton();
        btnUpdate = new components.RoundedButton();
        btnClear = new components.RoundedButton();
        btnDelete = new components.RoundedButton();
        btnBack = new components.RoundedButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORMULIR ADMINISTRASI");
        setMinimumSize(new java.awt.Dimension(1200, 800));
        getContentPane().setBackground(components.RoundedColors.BACKGROUND);

        pageTitle1.setText("FORMULIR ADMINISTRASI");
        lblTipeSurat.setText("Tipe Surat");
        lblTipeSurat.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblTipeSurat.setForeground(components.RoundedColors.TEXT_DARK);
        lblNamaSurat.setText("Nama Surat");
        lblNamaSurat.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblNamaSurat.setForeground(components.RoundedColors.TEXT_DARK);
        lblNomorSurat.setText("Nomor Surat");
        lblNomorSurat.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblNomorSurat.setForeground(components.RoundedColors.TEXT_DARK);
        lblDari.setText("Dari");
        lblDari.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblDari.setForeground(components.RoundedColors.TEXT_DARK);
        lblKepada.setText("Kepada");
        lblKepada.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblKepada.setForeground(components.RoundedColors.TEXT_DARK);
        lblTanggalSurat.setText("Tanggal Surat");
        lblTanggalSurat.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblTanggalSurat.setForeground(components.RoundedColors.TEXT_DARK);
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
                Navigation.go(AdministrationFormFrame.this, new ProjectAdministrationListFrame());
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
                            .addComponent(lblTipeSurat)
                            .addComponent(cmbTipeSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNamaSurat)
                            .addComponent(txtNamaSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblNomorSurat)
                        .addComponent(txtNomorSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDari)
                            .addComponent(txtDari, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKepada)
                            .addComponent(txtKepada, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTanggalSurat)
                        .addComponent(txtTanggalSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(lblTipeSurat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbTipeSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNamaSurat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNamaSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNomorSurat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomorSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDari, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblKepada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKepada, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTanggalSurat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTanggalSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
    private javax.swing.JLabel lblTipeSurat;
    private components.RoundedComboBox cmbTipeSurat;
    private javax.swing.JLabel lblNamaSurat;
    private components.RoundedTextField txtNamaSurat;
    private javax.swing.JLabel lblNomorSurat;
    private components.RoundedTextField txtNomorSurat;
    private javax.swing.JLabel lblDari;
    private components.RoundedTextField txtDari;
    private javax.swing.JLabel lblKepada;
    private components.RoundedTextField txtKepada;
    private javax.swing.JLabel lblTanggalSurat;
    private components.RoundedTextField txtTanggalSurat;
    private components.RoundedButton btnSave;
    private components.RoundedButton btnUpdate;
    private components.RoundedButton btnClear;
    private components.RoundedButton btnDelete;
    private components.RoundedButton btnBack;
    // End of variables declaration//GEN-END:variables
}
