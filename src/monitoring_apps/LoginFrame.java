package monitoring_apps;
import koneksi.KoneksiDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class LoginFrame extends javax.swing.JFrame {

    public LoginFrame() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {

        String email = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Email dan Password wajib diisi!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (
            Connection conn = new KoneksiDb().connect();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Simpan informasi pengguna yang login ke UserSession
                    UserSession.setUserId(rs.getInt("id"));
                    UserSession.setUserName(rs.getString("nama"));
                    UserSession.setJabatan(rs.getString("jabatan"));
                    UserSession.setDivisi(rs.getString("divisi"));
                    UserSession.setNoTelp(rs.getString("no_telp"));
                    UserSession.setAlamat(rs.getString("alamat"));
                    UserSession.setEmail(rs.getString("email"));
                    
                    String role = rs.getString("jabatan");
                    String divisi = rs.getString("divisi");
                    if (role != null && !role.trim().isEmpty()) {
                        if (divisi != null && !divisi.trim().isEmpty()) {
                            UserSession.setRoleName(role.trim() + " - " + divisi.trim());
                        } else {
                            UserSession.setRoleName(role.trim());
                        }
                    } else if (divisi != null && !divisi.trim().isEmpty()) {
                        UserSession.setRoleName(divisi.trim());
                    }

                    JOptionPane.showMessageDialog(
                        this,
                        "Login Berhasil",
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    Dashboard dashboard = new Dashboard();
                    dashboard.setLocationRelativeTo(null);
                    dashboard.setVisible(true);
                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "Email atau Password salah!",
                        "Login Gagal",
                        JOptionPane.ERROR_MESSAGE
                    );

                    txtPassword.setText("");
                    txtUsername.requestFocus();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Terjadi kesalahan:\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel = new components.RoundedPanel();
        leftPanel = new components.RoundedPanel();
        lblLogo = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new components.RoundedTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new components.RoundedPasswordField();
        chkShowPassword = new javax.swing.JCheckBox();
        btnLogin = new components.RoundedButton();
        btnUserBaru = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setMinimumSize(new java.awt.Dimension(1200, 800));
        getContentPane().setBackground(components.RoundedColors.PRIMARY_DARK);

        cardPanel.setBackground(new java.awt.Color(248, 249, 253));
        cardPanel.setRadius(42);
        leftPanel.setBackground(new java.awt.Color(235, 235, 235));
        leftPanel.setRadius(42);

        lblLogo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 30));
        lblLogo.setForeground(components.RoundedColors.PRIMARY);
        lblLogo.setText("MK | MEUHASE KUMITA");
        lblWelcome.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28));
        lblWelcome.setForeground(components.RoundedColors.TEXT_DARK);
        lblWelcome.setText("Selamat Datang!");
        lblDesc.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));
        lblDesc.setForeground(components.RoundedColors.TEXT_DARK);
        lblDesc.setText("<html>pengelola proyek dan sumber daya PT. Meuhase Kumita Indonesia</html>");
        lblTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 30));
        lblTitle.setForeground(components.RoundedColors.TEXT_DARK);
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("LOGIN");
        lblUsername.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));
        lblUsername.setForeground(components.RoundedColors.TEXT_DARK);
        lblUsername.setText("Email");
        lblPassword.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));
        lblPassword.setForeground(components.RoundedColors.TEXT_DARK);
        lblPassword.setText("Password");
        
        chkShowPassword.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        chkShowPassword.setForeground(components.RoundedColors.TEXT_DARK);
        chkShowPassword.setText("Tampilkan Password");
        chkShowPassword.setOpaque(false);
        chkShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowPasswordActionPerformed(evt);
            }
        });

        btnLogin.setText("Login");
        btnUserBaru.setText("User Baru");
        btnUserBaru.setButtonColor(components.RoundedColors.SOFT_GRAY);
        btnUserBaru.setForeground(components.RoundedColors.TEXT_DARK);

        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnLoginActionPerformed(evt);
    }
        });

        javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cardLayout = new javax.swing.GroupLayout(cardPanel);
        cardPanel.setLayout(cardLayout);
        cardLayout.setHorizontalGroup(
            cardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(cardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsername)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkShowPassword)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        cardLayout.setVerticalGroup(
            cardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(cardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cardLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(lblUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lblPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(chkShowPassword)
                        .addGap(28, 28, 28)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnUserBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
    //     // TODO add your handling code here:
    //     txtUsername.setText("Test");
    // }//GEN-LAST:event_btnLoginActionPerformed
    private void chkShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        if (chkShowPassword.isSelected()) {
            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar('•');
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedPanel cardPanel;
    private components.RoundedPanel leftPanel;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private components.RoundedTextField txtUsername;
    private javax.swing.JLabel lblPassword;
    private components.RoundedPasswordField txtPassword;
    private javax.swing.JCheckBox chkShowPassword;
    private components.RoundedButton btnLogin;
    private components.RoundedButton btnUserBaru;
    // End of variables declaration//GEN-END:variables
}
