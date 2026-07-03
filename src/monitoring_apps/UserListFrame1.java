package monitoring_apps;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.KoneksiDb;
import components.PlusIcon;

public class UserListFrame1 extends javax.swing.JFrame {
    
    private Connection conn = new KoneksiDb().connect() ;
    private DefaultTableModel tabmode;
    
    private Timer searchTimer;
    private String selectedNama;
    private String selectedJabatan;
    private String selectedDivisi;
    private String selectedEmail;
    private String selectedTelepon;
    private String selectedAlamat;
    private String selectedPassword;



    public UserListFrame1() {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        initUi();

        pageTitle1.setText("DAFTAR USER");
        getContentPane().setBackground(java.awt.Color.WHITE);
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);



        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(UserListFrame1.this, new UserFormFrame());
            }
        });

        loadData();
        setColumnWidths();
        iniEvent(); // PENTING AGAR SEARCH BERJALAN
    }
    
    private void initUi() {
        btnNew.setText("Tambah");
        btnNew.setIcon(new PlusIcon(16));
        btnNew.setButtonColor(components.RoundedColors.PRIMARY);
        btnNew.setForeground(java.awt.Color.WHITE);

        roundedTablePanel1.setEditAction(
            "/image/edit.png",
            new components.RoundedTablePanel.ActionClickListener() {
                @Override
                public void onActionClick(int row) {
                    String nama     = tabmode.getValueAt(row, 0).toString();
                    String jabatan  = tabmode.getValueAt(row, 1).toString();
                    String divisi   = tabmode.getValueAt(row, 2).toString();
                    String email    = tabmode.getValueAt(row, 3).toString();
                    String telepon  = tabmode.getValueAt(row, 4).toString();
                    String alamat   = tabmode.getValueAt(row, 5).toString();
                    String password = tabmode.getValueAt(row, 6).toString();
                    openEditForm(nama, jabatan, divisi, telepon, alamat, email, password);
                }
            }
        );
 
        roundedTablePanel1.setDeleteAction(
            "/image/delete.png",
            new components.RoundedTablePanel.ActionClickListener() {
                @Override
                public void onActionClick(int row) {
                    String nama = tabmode.getValueAt(row, 0).toString();
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(
                        UserListFrame1.this,
                        "Yakin ingin menghapus user \"" + nama + "\"?",
                        "Konfirmasi Hapus",
                        javax.swing.JOptionPane.YES_NO_OPTION,
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        deleteUser(nama, row);
                    }
                }
            }
        );
    }
 
    private void deleteUser(String nama, int row) {
        try {
            java.sql.Connection c = new koneksi.KoneksiDb().connect();
            java.sql.PreparedStatement ps = c.prepareStatement("DELETE FROM user WHERE nama = ?");
            ps.setString(1, nama);
            int result = ps.executeUpdate();
            if (result > 0) {
                tabmode.removeRow(row);
                javax.swing.JOptionPane.showMessageDialog(UserListFrame1.this,
                    "User berhasil dihapus.", "Sukses", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(UserListFrame1.this,
                    "User tidak ditemukan.", "Gagal", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(UserListFrame1.this,
                "Gagal menghapus: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void setColumnWidths() {
        JTable table = roundedTablePanel1.getTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (Component comp : roundedTablePanel1.getComponents()) {
            if (comp instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) comp;
                scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                break;
            }
        }

        int[] widths = {
            210, 130, 150, 100, 170, 100, 100, 70
        };

        for (int i = 0; i < widths.length && i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
            table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
            table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
        }

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        int aksiCol = table.getColumnCount() - 1;
        for (int i = 0; i < aksiCol; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
    }
    
    private void openEditForm(
            String nama,
            String jabatan,
            String divisi,
            String telepon,
            String alamat,
            String email,
            String password) {

        JFrame next = new UserFormFrame(
                nama,
                jabatan,
                divisi,
                telepon,
                alamat,
                email,
                password
        );

        next.pack();
        next.setLocationRelativeTo(this);
        next.setVisible(true);
        dispose();
    }
    
    private void iniEvent() {
        searchTimer = new Timer(300, e -> search());
        searchTimer.setRepeats(false);

        searchBox1.getTextField().getDocument().addDocumentListener(
            new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    searchTimer.restart();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    searchTimer.restart();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    searchTimer.restart();
                }
            }
        );
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Gagal", JOptionPane.WARNING_MESSAGE) ;
    }
    
    private void search() {
        String keyword = searchBox1.getText().trim();

        tabmode.setRowCount(0);

        String sql =
                "SELECT nama, jabatan, divisi, email, no_telp, alamat, password " +
                "FROM user " +
                "WHERE nama LIKE ? " +
                "OR jabatan LIKE ? " +
                "OR divisi LIKE ? " +
                "OR email LIKE ? " +
                "OR no_telp LIKE ? " +
                "OR alamat LIKE ? " +
                "OR password LIKE ? " +
                "ORDER BY nama ASC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            String key = "%" + keyword + "%";

            for (int i = 1; i <= 7; i++) {
                ps.setString(i, key);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tabmode.addRow(new Object[]{
        rs.getString("nama"),
        rs.getString("jabatan"),
        rs.getString("divisi"),
        rs.getString("email"),
        rs.getString("no_telp"),
        rs.getString("alamat"),
        rs.getString("password"),
        null
    });
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal mencari data : " + e.getMessage()
            );
        }
    }
    
    private void loadData() {
            Object[] columns = {
            "Nama Pengguna",
            "Jabatan",
            "Divisi",
            "Email",
            "No. Telepon",
            "Alamat",
            "Password"
        };
        roundedTablePanel1.setTableData(new Object[0][columns.length], columns);
        tabmode = roundedTablePanel1.getModel();

        String sql =
                "SELECT nama, jabatan, divisi, email, no_telp, alamat, password " +
                "FROM user ORDER BY nama ASC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tabmode.addRow(new Object[]{
                    rs.getString("nama"),
                    rs.getString("jabatan"),
                    rs.getString("divisi"),
                    rs.getString("email"),
                    rs.getString("no_telp"),
                    rs.getString("alamat"),
                    rs.getString("password"),
                    null
                });
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal memuat data user : " + e.getMessage()
            );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        searchBox1 = new components.SearchBox();
        btnViewReport = new components.RoundedButton();
        btnNew = new components.RoundedButton();
        roundedTablePanel1 = new components.RoundedTablePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PROJECT ADMINISTRATION");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("USER");

        btnViewReport.setText("View Report");

        btnNew.setText("+ New Administration");



        roundedTablePanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundedTablePanel1MouseClicked(evt);
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
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnViewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundedTablePanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(roundedTablePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roundedTablePanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundedTablePanel1MouseClicked
        // TODO add your handling code here:
        int row = roundedTablePanel1.getTable().getSelectedRow();

    if (row != -1) {
        selectedNama    = roundedTablePanel1.getTable().getValueAt(row, 0).toString();
        selectedJabatan = roundedTablePanel1.getTable().getValueAt(row, 1).toString();
        selectedDivisi  = roundedTablePanel1.getTable().getValueAt(row, 2).toString();
        selectedEmail   = roundedTablePanel1.getTable().getValueAt(row, 3).toString();
        selectedTelepon = roundedTablePanel1.getTable().getValueAt(row, 4).toString();
        selectedAlamat  = roundedTablePanel1.getTable().getValueAt(row, 5).toString();
        selectedPassword  = roundedTablePanel1.getTable().getValueAt(row, 6).toString();
    }
    }//GEN-LAST:event_roundedTablePanel1MouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserListFrame1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedButton btnNew;
    private components.RoundedButton btnViewReport;
    private components.PageTitle pageTitle1;
    private components.RoundedTablePanel roundedTablePanel1;
    private components.SearchBox searchBox1;
    private components.SidebarMenu sidebarMenu1;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
