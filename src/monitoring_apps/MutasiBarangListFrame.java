package monitoring_apps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import koneksi.KoneksiDb;
import components.PlusIcon;

public class MutasiBarangListFrame extends javax.swing.JFrame {

    private final java.util.List<Integer> mutasiIds = new java.util.ArrayList<>();
    private Timer searchTimer;



    public MutasiBarangListFrame() {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        btnNew.setText("Tambah");
        btnNew.setIcon(new PlusIcon(16));
        btnNew.setButtonColor(components.RoundedColors.PRIMARY);
        btnNew.setForeground(java.awt.Color.WHITE);
        pageTitle1.setText("MUTASI BARANG");
        getContentPane().setBackground(java.awt.Color.WHITE);
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
        


        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Navigation.go(MutasiBarangListFrame.this, new MutasiBarangFormFrame());
            }
        });

        appTablePanel1.setEditAction(
            "/image/edit.png",
            new components.RoundedTablePanel.ActionClickListener() {
                @Override
                public void onActionClick(int row) {
                    if (row < mutasiIds.size()) {
                        int id = mutasiIds.get(row);
                        Navigation.go(MutasiBarangListFrame.this, new MutasiBarangFormFrame(id));
                    }
                }
            }
        );
 
        appTablePanel1.setDeleteAction(
            "/image/delete.png",
            new components.RoundedTablePanel.ActionClickListener() {
                @Override
                public void onActionClick(int row) {
                    if (row >= mutasiIds.size()) return;
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(
                        MutasiBarangListFrame.this,
                        "Yakin ingin menghapus data mutasi ini?\nStok barang akan dikembalikan ke kondisi semula.",
                        "Konfirmasi Hapus",
                        javax.swing.JOptionPane.YES_NO_OPTION,
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        deleteMutasi(mutasiIds.get(row));
                    }
                }
            }
        );




        loadData();
        setColumnWidths();
        iniEvent();
    }

    private void loadData() {
        mutasiIds.clear();
        Object[] columns = {"#", "Barang", "Project/Tujuan", "Qty", "Tipe", "Keterangan", "Tanggal"};
        appTablePanel1.setTableData(new Object[0][columns.length], columns);
        javax.swing.table.DefaultTableModel model = appTablePanel1.getModel();

        String sql = "SELECT m.id, b.nama AS barang_nama, p.nama AS project_nama, m.qty, m.tipe, m.keterangan, m.created_at " +
                     "FROM mutasi_stok m " +
                     "LEFT JOIN barang b ON m.barang_id = b.id " +
                     "LEFT JOIN project p ON m.project_id = p.id " +
                     "ORDER BY m.created_at DESC";

        try (
            Connection conn = new KoneksiDb().connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            int no = 1;
            while (rs.next()) {
                mutasiIds.add(rs.getInt("id"));
                String barang = rs.getString("barang_nama");
                String project = rs.getString("project_nama");
                int qty = rs.getInt("qty");
                String tipe = rs.getString("tipe");
                String keterangan = rs.getString("keterangan");
                Timestamp tgl = rs.getTimestamp("created_at");
                
                model.addRow(new Object[]{
                    no++,
                    barang != null ? barang : "-",
                    project != null ? project : "-",
                    qty,
                    tipe != null ? tipe : "-",
                    keterangan != null ? keterangan : "-",
                    tgl != null ? tgl.toString() : "-",
                    null
                });
            }
        } catch (Exception e) {
            System.out.println("Gagal memuat data mutasi: " + e.getMessage());
        }
    }

    private void deleteMutasi(int mutasiId) {
        Connection conn = null;
        try {
            conn = new KoneksiDb().connect();
            conn.setAutoCommit(false); // Start transaction

            // 1. Get mutasi details
            String selectSql = "SELECT barang_id, qty, tipe FROM mutasi_stok WHERE id = ?";
            int barangId = -1;
            int qty = 0;
            String tipe = "";
            try (PreparedStatement psSelect = conn.prepareStatement(selectSql)) {
                psSelect.setInt(1, mutasiId);
                try (ResultSet rs = psSelect.executeQuery()) {
                    if (rs.next()) {
                        barangId = rs.getInt("barang_id");
                        qty = rs.getInt("qty");
                        tipe = rs.getString("tipe");
                    }
                }
            }

            if (barangId != -1) {
                // 2. Revert stock based on tipe
                String updateStockSql;
                if ("keluar".equalsIgnoreCase(tipe)) {
                    // Revert keluar: add qty back
                    updateStockSql = "UPDATE barang SET qty = qty + ? WHERE id = ?";
                } else {
                    // Revert masuk: subtract qty
                    updateStockSql = "UPDATE barang SET qty = qty - ? WHERE id = ?";
                }
                
                try (PreparedStatement psUpdate = conn.prepareStatement(updateStockSql)) {
                    psUpdate.setInt(1, qty);
                    psUpdate.setInt(2, barangId);
                    psUpdate.executeUpdate();
                }

                // 3. Delete mutation record
                String deleteSql = "DELETE FROM mutasi_stok WHERE id = ?";
                try (PreparedStatement psDelete = conn.prepareStatement(deleteSql)) {
                    psDelete.setInt(1, mutasiId);
                    psDelete.executeUpdate();
                }

                conn.commit();
                javax.swing.JOptionPane.showMessageDialog(this, "Data mutasi berhasil dihapus!", "Sukses", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                loadData();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Data mutasi tidak ditemukan!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                conn.rollback();
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (Exception ex) {
                    System.out.println("Rollback failed: " + ex.getMessage());
                }
            }
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal menghapus data mutasi: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println("Close failed: " + e.getMessage());
                }
            }
        }
    }

    private void iniEvent() {
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
        if (keyword.isEmpty()) {
            loadData();
            return;
        }

        mutasiIds.clear();
        Object[] columns = {"#", "Barang", "Project/Tujuan", "Qty", "Tipe", "Keterangan", "Tanggal"};
        appTablePanel1.setTableData(new Object[0][columns.length], columns);
        javax.swing.table.DefaultTableModel model = appTablePanel1.getModel();

        String sql = "SELECT m.id, b.nama AS barang_nama, p.nama AS project_nama, m.qty, m.tipe, m.keterangan, m.created_at " +
                     "FROM mutasi_stok m " +
                     "LEFT JOIN barang b ON m.barang_id = b.id " +
                     "LEFT JOIN project p ON m.project_id = p.id " +
                     "WHERE b.nama LIKE ? " +
                     "OR p.nama LIKE ? " +
                     "OR m.tipe LIKE ? " +
                     "OR m.keterangan LIKE ? " +
                     "OR DATE_FORMAT(m.created_at, '%Y-%m-%d') LIKE ? " +
                     "ORDER BY m.created_at DESC";

        try (
            Connection conn = new KoneksiDb().connect();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            String likeKeyword = "%" + keyword + "%";
            for (int i = 1; i <= 5; i++) {
                ps.setString(i, likeKeyword);
            }

            try (ResultSet rs = ps.executeQuery()) {
                int no = 1;
                while (rs.next()) {
                    mutasiIds.add(rs.getInt("id"));
                    String barang = rs.getString("barang_nama");
                    String project = rs.getString("project_nama");
                    int qty = rs.getInt("qty");
                    String tipe = rs.getString("tipe");
                    String keterangan = rs.getString("keterangan");
                    Timestamp tgl = rs.getTimestamp("created_at");
                    
                    model.addRow(new Object[]{
                        no++,
                        barang != null ? barang : "-",
                        project != null ? project : "-",
                        qty,
                        tipe != null ? tipe : "-",
                        keterangan != null ? keterangan : "-",
                        tgl != null ? tgl.toString() : "-",
                        null
                    });
                }
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pencarian gagal: " + e.getMessage(),
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
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
        btnViewReport = new components.RoundedButton();
        btnNew = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MUTASI BARANG");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        searchBox1.setText("");

        btnViewReport.setText("View Report");

        btnNew.setText("+ New Mutasi ");

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
                        .addComponent(btnViewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
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
                            .addComponent(btnViewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                new MutasiBarangListFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedTablePanel appTablePanel1;
    private components.RoundedButton btnNew;
    private components.RoundedButton btnViewReport;
    private components.PageTitle pageTitle1;
    private components.SearchBox searchBox1;
    private components.SidebarMenu sidebarMenu1;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
