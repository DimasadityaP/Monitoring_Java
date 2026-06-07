package monitoring_apps;

public class DashboardAdministrationFrame extends javax.swing.JFrame {

    public DashboardAdministrationFrame() {
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
        heroPanel = new components.RoundedPanel();
        lblHeroTitle = new javax.swing.JLabel();
        lblHeroSubtitle = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        btnProjectAdmin = new components.RoundedButton();
        btnNonProjectAdmin = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DASHBOARD ADMINISTRATION");
        setMinimumSize(new java.awt.Dimension(1200, 800));
        getContentPane().setBackground(new java.awt.Color(248, 249, 253));

        pageTitle1.setText("DASHBOARD ADMINISTRATION");
        heroPanel.setBackground(new java.awt.Color(65, 71, 150));
        heroPanel.setRadius(24);

        lblHeroTitle.setFont(new java.awt.Font("Segoe UI", 1, 28));
        lblHeroTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblHeroTitle.setText("Hello, Dimas Aditya Pratama!");

        lblHeroSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lblHeroSubtitle.setForeground(new java.awt.Color(255, 255, 255));
        lblHeroSubtitle.setText("Good Morning, ready to start your day?");

        lblDesc.setFont(new java.awt.Font("Segoe UI", 0, 18));
        lblDesc.setForeground(new java.awt.Color(41, 45, 86));
        lblDesc.setText("Selamat datang di sistem manajemen perusahaan. Silakan pilih menu yang akan dikelola.");

        btnProjectAdmin.setText("Project Administration");
        btnProjectAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(DashboardAdministrationFrame.this, new ProjectAdministrationListFrame());
            }
        });
        btnNonProjectAdmin.setText("Non Project Administration");

        javax.swing.GroupLayout heroPanelLayout = new javax.swing.GroupLayout(heroPanel);
        heroPanel.setLayout(heroPanelLayout);
        heroPanelLayout.setHorizontalGroup(
            heroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(heroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHeroTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHeroSubtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        heroPanelLayout.setVerticalGroup(
            heroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroPanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lblHeroTitle)
                .addGap(14, 14, 14)
                .addComponent(lblHeroSubtitle)
                .addContainerGap(58, Short.MAX_VALUE))
        );

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
                    .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(heroPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnProjectAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnNonProjectAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
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
                        .addComponent(heroPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(lblDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProjectAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNonProjectAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardAdministrationFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedButton btnNonProjectAdmin;
    private components.RoundedButton btnProjectAdmin;
    private components.RoundedPanel heroPanel;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblHeroSubtitle;
    private javax.swing.JLabel lblHeroTitle;
    private components.PageTitle pageTitle1;
    private components.SidebarMenu sidebarMenu1;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
