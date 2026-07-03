package monitoring_apps;

import Utills.UserSession;

public class Dashboard extends javax.swing.JFrame {

    private components.DashboardCard cardProject;
    private components.DashboardCard cardBarang;
    private components.DashboardCard cardAdministrasi;
    private components.DashboardCard cardReimbursement;

    public Dashboard() {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(java.awt.Color.WHITE);
        initCards();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
        lblHeroTitle.setText("Hello, " + UserSession.getUserName() + "!");
        java.util.Calendar cal = java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("Asia/Jakarta"));
        int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
        String greeting;
        if (hour >= 5 && hour < 12) {
            greeting = "Good Morning, ready to start your day?";
        } else if (hour >= 12 && hour < 18) {
            greeting = "Good Afternoon, hope your day is going well!";
        } else {
            greeting = "Good Evening, ready to wrap up your day?";
        }
        lblHeroSubtitle.setText(greeting);
        loadCounts();
    }

    private void initCards() {
        cardProject = new components.DashboardCard("Project", "0", components.RoundedColors.PRIMARY, "project");
        cardBarang = new components.DashboardCard("Barang", "0", components.RoundedColors.GOLD, "barang");
        cardAdministrasi = new components.DashboardCard("Administrasi", "0", new java.awt.Color(40, 167, 69), "administrasi");
        cardReimbursement = new components.DashboardCard("Reimbursement", "0", new java.awt.Color(111, 66, 193), "reimbursement");
        
        cardContainer.add(cardProject);
        cardContainer.add(cardBarang);
        cardContainer.add(cardAdministrasi);
        cardContainer.add(cardReimbursement);
    }

    private void loadCounts() {
        try {
            java.sql.Connection conn = new koneksi.KoneksiDb().connect();
            
            int projectCount = 0;
            try (java.sql.PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM project");
                 java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) projectCount = rs.getInt(1);
            }
            
            int barangCount = 0;
            try (java.sql.PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM barang");
                 java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) barangCount = rs.getInt(1);
            }
            
            int adminCount = 0;
            try (java.sql.PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM administrasi");
                 java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) adminCount = rs.getInt(1);
            }
            
            int reimburseCount = 0;
            try (java.sql.PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM reimbursement");
                 java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) reimburseCount = rs.getInt(1);
            }
            
            conn.close();
            
            cardProject.setCount(String.valueOf(projectCount));
            cardBarang.setCount(String.valueOf(barangCount));
            cardAdministrasi.setCount(String.valueOf(adminCount));
            cardReimbursement.setCount(String.valueOf(reimburseCount));
            
        } catch (Exception e) {
            System.err.println("Gagal memuat count dashboard: " + e.getMessage());
        }
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
        cardContainer = new javax.swing.JPanel();
        btnBack = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DASHBOARD ADMINISTRATION");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("DASHBOARD");

        heroPanel.setBackground(new java.awt.Color(65, 71, 150));
        heroPanel.setRadius(24);

        lblHeroTitle.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblHeroTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblHeroTitle.setText("Hello, Dimas Aditya Pratama!");

        lblHeroSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHeroSubtitle.setForeground(new java.awt.Color(255, 255, 255));
        lblHeroSubtitle.setText("Good Morning, ready to start your day?");

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

        cardContainer.setOpaque(false);
        cardContainer.setLayout(new java.awt.GridLayout(2, 2, 20, 20));

        btnBack.setButtonColor(new java.awt.Color(217, 217, 217));
        btnBack.setForeground(new java.awt.Color(41, 45, 86));
        btnBack.setText("Selamat datang di sistem manajemen perusahaan. Silakan pilih menu yang akan dikelola.");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(heroPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70))
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
                        .addComponent(heroPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cardContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedButton btnBack;
    private javax.swing.JPanel cardContainer;
    private components.RoundedPanel heroPanel;
    private javax.swing.JLabel lblHeroSubtitle;
    private javax.swing.JLabel lblHeroTitle;
    private components.PageTitle pageTitle1;
    private components.SidebarMenu sidebarMenu1;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
