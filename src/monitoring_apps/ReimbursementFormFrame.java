package monitoring_apps;

public class ReimbursementFormFrame extends javax.swing.JFrame {

    public ReimbursementFormFrame() {
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
        lblProyekTujuan = new javax.swing.JLabel();
        txtProyekTujuan = new components.RoundedTextField();
        lblHari = new javax.swing.JLabel();
        cmbHari = new components.RoundedComboBox();
        lblTanggal = new javax.swing.JLabel();
        txtTanggal = new components.RoundedTextField();
        lblPerihal = new javax.swing.JLabel();
        txtPerihal = new components.RoundedTextArea();
        lblUraian = new javax.swing.JLabel();
        txtUraian1 = new components.RoundedTextField();
        lblKuantitas = new javax.swing.JLabel();
        txtKuantitas1 = new components.RoundedTextField();
        lblSatuan = new javax.swing.JLabel();
        txtSatuan1 = new components.RoundedTextField();
        lblNominal = new javax.swing.JLabel();
        txtNominal1 = new components.RoundedTextField();
        lblTotal = new javax.swing.JLabel();
        txtTotal1 = new components.RoundedTextField();
        lblTotalAkhir = new javax.swing.JLabel();
        txtTotalAkhir = new components.RoundedTextField();
        btnSave = new components.RoundedButton();
        btnUpdate = new components.RoundedButton();
        btnClear = new components.RoundedButton();
        btnDelete = new components.RoundedButton();
        btnBack = new components.RoundedButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORMULIR REIMBURSEMEN");
        setMinimumSize(new java.awt.Dimension(1200, 800));
        getContentPane().setBackground(components.RoundedColors.BACKGROUND);

        pageTitle1.setText("FORMULIR REIMBURSEMEN");
        lblProyekTujuan.setText("Proyek / Tujuan");
        lblProyekTujuan.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblProyekTujuan.setForeground(components.RoundedColors.TEXT_DARK);
        lblHari.setText("Hari");
        lblHari.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblHari.setForeground(components.RoundedColors.TEXT_DARK);
        lblTanggal.setText("Tanggal");
        lblTanggal.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblTanggal.setForeground(components.RoundedColors.TEXT_DARK);
        lblPerihal.setText("Perihal");
        lblPerihal.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblPerihal.setForeground(components.RoundedColors.TEXT_DARK);
        txtPerihal.setLineWrap(true);
        txtPerihal.setWrapStyleWord(true);
        lblUraian.setText("Masukan Uraian");
        lblUraian.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblUraian.setForeground(components.RoundedColors.TEXT_DARK);
        lblKuantitas.setText("Kuantitas");
        lblKuantitas.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblKuantitas.setForeground(components.RoundedColors.TEXT_DARK);
        lblSatuan.setText("Satuan");
        lblSatuan.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblSatuan.setForeground(components.RoundedColors.TEXT_DARK);
        lblNominal.setText("Nominal");
        lblNominal.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblNominal.setForeground(components.RoundedColors.TEXT_DARK);
        lblTotal.setText("Total");
        lblTotal.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblTotal.setForeground(components.RoundedColors.TEXT_DARK);
        lblTotalAkhir.setText("Total Akhir");
        lblTotalAkhir.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblTotalAkhir.setForeground(components.RoundedColors.TEXT_DARK);
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
                Navigation.go(ReimbursementFormFrame.this, new ProjectAdministrationListFrame());
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
                            .addComponent(lblProyekTujuan)
                            .addComponent(txtProyekTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHari)
                            .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTanggal)
                        .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblPerihal)
                        .addComponent(txtPerihal, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUraian)
                            .addComponent(txtUraian1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKuantitas)
                            .addComponent(txtKuantitas1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSatuan)
                            .addComponent(txtSatuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNominal)
                            .addComponent(txtNominal1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotal)
                            .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalAkhir)
                            .addComponent(txtTotalAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)) )
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
                                .addComponent(lblProyekTujuan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProyekTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTanggal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPerihal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPerihal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUraian)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUraian1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblKuantitas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKuantitas1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSatuan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSatuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNominal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNominal1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTotalAkhir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
    private javax.swing.JLabel lblProyekTujuan;
    private components.RoundedTextField txtProyekTujuan;
    private javax.swing.JLabel lblHari;
    private components.RoundedComboBox cmbHari;
    private javax.swing.JLabel lblTanggal;
    private components.RoundedTextField txtTanggal;
    private javax.swing.JLabel lblPerihal;
    private components.RoundedTextArea txtPerihal;
    private javax.swing.JLabel lblUraian;
    private components.RoundedTextField txtUraian1;
    private javax.swing.JLabel lblKuantitas;
    private components.RoundedTextField txtKuantitas1;
    private javax.swing.JLabel lblSatuan;
    private components.RoundedTextField txtSatuan1;
    private javax.swing.JLabel lblNominal;
    private components.RoundedTextField txtNominal1;
    private javax.swing.JLabel lblTotal;
    private components.RoundedTextField txtTotal1;
    private javax.swing.JLabel lblTotalAkhir;
    private components.RoundedTextField txtTotalAkhir;
    private components.RoundedButton btnSave;
    private components.RoundedButton btnUpdate;
    private components.RoundedButton btnClear;
    private components.RoundedButton btnDelete;
    private components.RoundedButton btnBack;
    // End of variables declaration//GEN-END:variables
}
