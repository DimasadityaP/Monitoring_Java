package monitoring_apps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import koneksi.KoneksiDb;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.SQLException;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import components.PlusIcon;

public class BarangList extends javax.swing.JFrame {
private Connection conn = new KoneksiDb().connect() ;
    private DefaultTableModel tabmode;
    private Timer searchTimer;



    public BarangList() {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        pageTitle1.setText("LIST LOGISTIC");
        getContentPane().setBackground(java.awt.Color.WHITE);
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);



        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(BarangList.this, new BarangFormFrame());
            }
        });

        initUi();   // setActionColumn DULU sebelum loadData
        loadData();
        setColumnWidths();
        iniEvent();
    }

    private void initUi() {
        btnNew.setText("Tambah");
        btnNew.setIcon(new PlusIcon(16));
        btnNew.setButtonColor(components.RoundedColors.PRIMARY);
        btnNew.setForeground(java.awt.Color.WHITE);

        appTablePanel1.setEditAction(
            "/image/edit.png",
            new components.RoundedTablePanel.ActionClickListener() {
                public void onActionClick(int row) {
                    openEditForm(row);
                }
            }
        );
 
        appTablePanel1.setDeleteAction(
            "/image/delete.png",
            new components.RoundedTablePanel.ActionClickListener() {
                public void onActionClick(int row) {
                    String kode = tabmode.getValueAt(row, 0).toString();
                    String nama = tabmode.getValueAt(row, 1).toString();
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(
                        BarangList.this,
                        "Yakin ingin menghapus barang \"" + nama + "\" (" + kode + ")?",
                        "Konfirmasi Hapus",
                        javax.swing.JOptionPane.YES_NO_OPTION,
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        deleteBarang(kode, row);
                    }
                }
            }
        );
    }
 
    private void deleteBarang(String kode, int row) {
        try {
            java.sql.Connection c = new KoneksiDb().connect();
            java.sql.PreparedStatement ps = c.prepareStatement("DELETE FROM barang WHERE kode = ?");
            ps.setString(1, kode);
            int result = ps.executeUpdate();
            if (result > 0) {
                tabmode.removeRow(row);
                javax.swing.JOptionPane.showMessageDialog(BarangList.this,
                    "Barang berhasil dihapus.", "Sukses",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(BarangList.this,
                    "Barang tidak ditemukan.", "Gagal",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(BarangList.this,
                "Gagal menghapus: " + e.getMessage(), "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }


    private void openEditForm(int row) {
        String kode    = tabmode.getValueAt(row, 0).toString();
        String nama    = tabmode.getValueAt(row, 1).toString();
        String spek    = tabmode.getValueAt(row, 2).toString();
        String jenis   = tabmode.getValueAt(row, 3).toString();
        int    qty     = Integer.parseInt(tabmode.getValueAt(row, 4).toString());
        String satuan  = tabmode.getValueAt(row, 5).toString();
        String kondisi = tabmode.getValueAt(row, 6).toString();

        Navigation.go(BarangList.this,
            new BarangFormFrame(kode, nama, spek, jenis, qty, satuan, kondisi)
        );
    }
    
    private void iniEvent(){
        searchTimer = new Timer(300, e -> search());
        searchTimer.setRepeats(false);

        searchBox1.getTextField().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { searchTimer.restart(); }
            public void removeUpdate(DocumentEvent e) { searchTimer.restart(); }
            public void changedUpdate(DocumentEvent e) { searchTimer.restart(); }
        });
    }
    
    private void search() {
        String keyword = searchBox1.getText().trim();
        tabmode.setRowCount(0);

        String query =
                "SELECT * FROM barang " +
                "WHERE kode    LIKE ? " +
                "OR nama       LIKE ? " +
                "OR spek       LIKE ? " +
                "OR jenis      LIKE ? " +
                "OR satuan     LIKE ? " +
                "OR kondisi    LIKE ? " +
                "ORDER BY created_at DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            String like = "%" + keyword + "%";
            for (int i = 1; i <= 6; i++) ps.setString(i, like);

            ResultSet rs = ps.executeQuery();
            int no = 1;
            while (rs.next()) {
                String kode    = rs.getString("kode");
                String nama    = rs.getString("nama");
                String spek    = rs.getString("spek");
                String jenis   = rs.getString("jenis");
                int    qty     = rs.getInt("qty");
                String satuan  = rs.getString("satuan");
                String kondisi = rs.getString("kondisi");
                Timestamp tgl  = rs.getTimestamp("created_at");

                tabmode.addRow(new Object[]{
                    no++,
                    kode    != null ? kode    : "-",
                    nama    != null ? nama    : "-",
                    spek    != null ? spek    : "-",
                    jenis   != null ? jenis   : "-",
                    qty,
                    satuan  != null ? satuan  : "-",
                    kondisi != null ? kondisi : "-",
                    tgl     != null ? tgl.toString() : "-",
                    null  // slot kolom Aksi
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   


    private void loadData() {
        Object[] columns = {"#", "Kode", "Nama", "Spek", "Jenis", "Qty", "Satuan", "Kondisi", "Tgl"};
        appTablePanel1.setTableData(new Object[0][columns.length], columns);
        tabmode = appTablePanel1.getModel();

        String sql = "SELECT * FROM barang";

        try (
            Connection conn = new KoneksiDb().connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            int no = 1;
            while (rs.next()) {
                String kode    = rs.getString("kode");
                String nama    = rs.getString("nama");
                String spek    = rs.getString("spek");
                String jenis   = rs.getString("jenis");
                int    qty     = rs.getInt("qty");
                String satuan  = rs.getString("satuan");
                String kondisi = rs.getString("kondisi");
                Timestamp tgl  = rs.getTimestamp("created_at");

                tabmode.addRow(new Object[]{
                    no++,
                    kode    != null ? kode    : "-",
                    nama    != null ? nama    : "-",
                    spek    != null ? spek    : "-",
                    jenis   != null ? jenis   : "-",
                    qty,
                    satuan  != null ? satuan  : "-",
                    kondisi != null ? kondisi : "-",
                    tgl     != null ? tgl.toString() : "-",
                    null  // slot kolom Aksi
                });
            }
        } catch (Exception e) {
            System.out.println("Gagal memuat data barang: " + e.getMessage());
        }
    }

    private void setColumnWidths() {
        javax.swing.JTable table = appTablePanel1.getTable();
        if (table.getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(40);
            table.getColumnModel().getColumn(0).setMinWidth(40);
            table.getColumnModel().getColumn(0).setMaxWidth(40);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        searchBox1 = new components.SearchBox();
        appTablePanel1 = new components.RoundedTablePanel();
        btnNew = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MUTASI BARANG");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("LIST LOGISTIC");

        btnNew.setText("Tambah Barang");





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
                    .addComponent(pageTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(appTablePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(appTablePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BarangList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedTablePanel appTablePanel1;
    private components.RoundedButton btnNew;
    private components.PageTitle pageTitle1;
    private components.SearchBox searchBox1;
    private components.SidebarMenu sidebarMenu1;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}