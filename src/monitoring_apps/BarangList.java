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

public class BarangList extends javax.swing.JFrame {
private Connection conn = new KoneksiDb().connect() ;
    private DefaultTableModel tabmode;
    private Timer searchTimer;
    public BarangList() {
        initComponents();
        pageTitle1.setText("LIST LOGISTIC");
        getContentPane().setBackground(components.RoundedColors.BACKGROUND);
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);

        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(BarangList.this, new Dashboard());
            }
        });

        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(BarangList.this, new BarangFormFrame());
            }
        });

        initUi();   // setActionColumn DULU sebelum loadData
        loadData();
        iniEvent();
    }

    private void initUi() {
        appTablePanel1.setActionColumn(
            "/image/edit.png",
            new components.RoundedTablePanel.ActionClickListener() {
                public void onActionClick(int row) {
                    openEditForm(row);
                }
            }
        );
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
        Object[] columns = {"Kode", "Nama", "Spek", "Jenis", "Qty", "Satuan", "Kondisi", "Tgl"};
        appTablePanel1.setTableData(new Object[0][columns.length], columns);
        tabmode = appTablePanel1.getModel();

        String sql = "SELECT * FROM barang";

        try (
            Connection conn = new KoneksiDb().connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        searchBox1 = new components.SearchBox();
        appTablePanel1 = new components.RoundedTablePanel();
        btnNew = new components.RoundedButton();
        btnBack = new components.RoundedButton();
        btnDelete = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MUTASI BARANG");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("LIST LOGISTIC");

        btnNew.setText("Tambah Barang");

        btnBack.setForeground(new java.awt.Color(0, 0, 0));
        btnBack.setText("Back");
        btnBack.setButtonColor(new java.awt.Color(217, 217, 217));

        btnDelete.setText("Delete");
        btnDelete.setButtonColor(new java.awt.Color(154, 61, 120));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sidebarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(searchBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(appTablePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(307, 307, 307)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userProfileCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
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
                        .addGap(12, 12, 12)
                        .addComponent(searchBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(appTablePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    JTable table = appTablePanel1.getTable();

    int row = table.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Pilih data yang akan dihapus!");
        return;
    }

    String kode = table.getValueAt(row, 0).toString();

    int jawab = JOptionPane.showConfirmDialog(
            this,
            "Yakin ingin menghapus barang dengan kode " + kode + "?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION);

    if (jawab == JOptionPane.YES_OPTION) {

        try {
            Connection conn = new KoneksiDb().connect();

            String sql = "DELETE FROM barang WHERE kode=?";

            PreparedStatement stat =
                    conn.prepareStatement(sql);

            stat.setString(1, kode);

            int hasil = stat.executeUpdate();

            if (hasil > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Data berhasil dihapus");

                loadData(); // refresh tabel

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Data tidak ditemukan");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage());
        }
    }

    }//GEN-LAST:event_btnDeleteActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BarangList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedTablePanel appTablePanel1;
    private components.RoundedButton btnBack;
    private components.RoundedButton btnDelete;
    private components.RoundedButton btnNew;
    private components.PageTitle pageTitle1;
    private components.SearchBox searchBox1;
    private components.SidebarMenu sidebarMenu1;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}