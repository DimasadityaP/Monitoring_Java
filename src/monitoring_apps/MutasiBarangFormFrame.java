package monitoring_apps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Cursor;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import koneksi.KoneksiDb;

public class MutasiBarangFormFrame extends javax.swing.JFrame {

    private static class BarangItem {
        int id;
        String kode;
        String nama;
        int qty;

        public BarangItem(int id, String kode, String nama, int qty) {
            this.id = id;
            this.kode = kode;
            this.nama = nama;
            this.qty = qty;
        }
    }

    private static class ProjectItem {
        String id;
        String nama;

        public ProjectItem(String id, String nama) {
            this.id = id;
            this.nama = nama;
        }
    }

    private static class SearchIcon implements javax.swing.Icon {
        private final int size;

        public SearchIcon(int size) {
            this.size = size;
        }

        @Override
        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(
                java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(java.awt.Color.WHITE);
            g2.setStroke(new java.awt.BasicStroke(2.2f));

            int circleSize = size - 8;
            g2.drawOval(x + 2, y + 2, circleSize, circleSize);
            g2.drawLine(x + size - 7, y + size - 7, x + size - 2, y + size - 2);

            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }
    }

    private final List<BarangItem> barangList = new ArrayList<>();
    private final List<ProjectItem> projectList = new ArrayList<>();
    private BarangItem selectedBarang;
    private ProjectItem selectedProject;
    private int editMutasiId = -1;

    public MutasiBarangFormFrame() {
        initComponents();
        getContentPane().setBackground(components.RoundedColors.BACKGROUND);
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);

        // Configure read-only states for text fields
        txtBarang.setEditable(false);
        txtProject.setEditable(false);
        
        // Configure search buttons
        btnSearchBarang.setButtonColor(components.RoundedColors.PRIMARY);
        btnSearchBarang.setForeground(Color.WHITE);
        btnSearchBarang.setText("");
        btnSearchProject.setButtonColor(components.RoundedColors.PRIMARY);
        btnSearchProject.setForeground(Color.WHITE);
        btnSearchProject.setText("");
        
        btnSearchBarang.setIcon(new SearchIcon(18));
        btnSearchProject.setIcon(new SearchIcon(18));

        // Action & Click Listeners
        btnSearchBarang.addActionListener(e -> showBarangSelectionDialog());
        btnSearchProject.addActionListener(e -> showProjectSelectionDialog());
        
        txtBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showBarangSelectionDialog();
            }
        });
        txtProject.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showProjectSelectionDialog();
            }
        });

        // Hide unused elements programmatically to match design expectations cleanly
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);

        // Set up Tipe ComboBox
        cmbTipe.removeAllItems();
        cmbTipe.addItem("Masuk");
        cmbTipe.addItem("Keluar");

        // Load barang & project dropdown lists
        loadComboBoxData();

        // Register Action Listeners
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
    }

    public MutasiBarangFormFrame(int mutasiId) {
        this();
        this.editMutasiId = mutasiId;
        
        btnSave.setVisible(false);
        btnUpdate.setVisible(true);
        
        loadMutasiData(mutasiId);
    }

    private void loadComboBoxData() {
        barangList.clear();
        projectList.clear();

        String barangSql = "SELECT id, kode, nama, qty FROM barang ORDER BY nama ASC";
        String projectSql = "SELECT id, nama FROM project ORDER BY nama ASC";

        try (
            Connection conn = new KoneksiDb().connect();
            Statement stmt = conn.createStatement();
        ) {
            // Load Barang
            try (ResultSet rs = stmt.executeQuery(barangSql)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String kode = rs.getString("kode");
                    String nama = rs.getString("nama");
                    int qty = rs.getInt("qty");

                    barangList.add(new BarangItem(id, kode, nama, qty));
                }
            }

            // Load Project
            try (ResultSet rs = stmt.executeQuery(projectSql)) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String nama = rs.getString("nama");

                    projectList.add(new ProjectItem(id, nama));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data relasi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        String tipe = cmbTipe.getSelectedItem() != null ? cmbTipe.getSelectedItem().toString().toLowerCase() : "";
        String qtyText = txtQty.getText().trim();
        String keterangan = txtKeterangan.getText().trim();

        // Validate selections
        if (selectedBarang == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih barang!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedProject == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih project/tujuan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate qty
        int qtyVal;
        try {
            qtyVal = Integer.parseInt(qtyText);
            if (qtyVal <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Qty harus berupa angka bulat positif!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate stock boundary for keluar
        if (tipe.equals("keluar")) {
            if (qtyVal > selectedBarang.qty) {
                JOptionPane.showMessageDialog(this,
                    "Stok tidak mencukupi! Stok saat ini: " + selectedBarang.qty,
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Transaction Save & Stock Update
        Connection conn = null;
        try {
            conn = new KoneksiDb().connect();
            conn.setAutoCommit(false); // start transaction

            // Insert mutasi_stok
            String insertSql = "INSERT INTO mutasi_stok (barang_id, project_id, qty, tipe, keterangan) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
                insertPs.setInt(1, selectedBarang.id);
                insertPs.setString(2, selectedProject.id);
                insertPs.setInt(3, qtyVal);
                insertPs.setString(4, tipe);
                insertPs.setString(5, keterangan.isEmpty() ? null : keterangan);
                insertPs.executeUpdate();
            }

            // Update barang qty
            String updateSql;
            if (tipe.equals("keluar")) {
                updateSql = "UPDATE barang SET qty = qty - ? WHERE id = ?";
            } else {
                updateSql = "UPDATE barang SET qty = qty + ? WHERE id = ?";
            }

            try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                updatePs.setInt(1, qtyVal);
                updatePs.setInt(2, selectedBarang.id);
                updatePs.executeUpdate();
            }

            conn.commit(); // commit transaction
            JOptionPane.showMessageDialog(this, "Mutasi barang berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            Navigation.go(this, new MutasiBarangListFrame());

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("Rollback failed: " + ex.getMessage());
                }
            }
            JOptionPane.showMessageDialog(this, "Gagal menyimpan mutasi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Connection close failed: " + e.getMessage());
                }
            }
        }
    }

    private void loadMutasiData(int id) {
        String sql = "SELECT m.barang_id, m.project_id, m.qty, m.tipe, m.keterangan, " +
                     "b.kode AS barang_kode, b.nama AS barang_nama, b.qty AS barang_stok, p.nama AS project_nama " +
                     "FROM mutasi_stok m " +
                     "LEFT JOIN barang b ON m.barang_id = b.id " +
                     "LEFT JOIN project p ON m.project_id = p.id " +
                     "WHERE m.id = ?";
        
        try (
            Connection conn = new KoneksiDb().connect();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipe = rs.getString("tipe");
                    int barangId = rs.getInt("barang_id");
                    String barangKode = rs.getString("barang_kode");
                    String barangNama = rs.getString("barang_nama");
                    int barangStok = rs.getInt("barang_stok");
                    
                    String projectId = rs.getString("project_id");
                    String projectNama = rs.getString("project_nama");
                    int qty = rs.getInt("qty");
                    String keterangan = rs.getString("keterangan");
                    
                    // Populate Tipe
                    if ("keluar".equalsIgnoreCase(tipe)) {
                        cmbTipe.setSelectedItem("Keluar");
                    } else {
                        cmbTipe.setSelectedItem("Masuk");
                    }
                    
                    // Populate Barang
                    selectedBarang = new BarangItem(barangId, barangKode, barangNama, barangStok);
                    txtBarang.setText(selectedBarang.kode + " - " + selectedBarang.nama);
                    
                    // Populate Project
                    selectedProject = new ProjectItem(projectId, projectNama);
                    txtProject.setText(selectedProject.id + " - " + selectedProject.nama);
                    
                    // Populate qty and keterangan
                    txtQty.setText(String.valueOf(qty));
                    txtKeterangan.setText(keterangan != null ? keterangan : "");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data mutasi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        String tipe = cmbTipe.getSelectedItem() != null ? cmbTipe.getSelectedItem().toString().toLowerCase() : "";
        String qtyText = txtQty.getText().trim();
        String keterangan = txtKeterangan.getText().trim();

        // Validate selections
        if (selectedBarang == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih barang!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedProject == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih project/tujuan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate qty
        int newQty;
        try {
            newQty = Integer.parseInt(qtyText);
            if (newQty <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Qty harus berupa angka bulat positif!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 1. Get old mutasi details from DB
        int oldBarangId = -1;
        int oldQty = 0;
        String oldTipe = "";
        
        try (
            Connection conn = new KoneksiDb().connect();
            PreparedStatement ps = conn.prepareStatement("SELECT barang_id, qty, tipe FROM mutasi_stok WHERE id = ?");
        ) {
            ps.setInt(1, editMutasiId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    oldBarangId = rs.getInt("barang_id");
                    oldQty = rs.getInt("qty");
                    oldTipe = rs.getString("tipe");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mendapatkan data mutasi lama: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (oldBarangId == -1) {
            JOptionPane.showMessageDialog(this, "Data mutasi lama tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Validate stock boundary for new configuration
        int currentSelectedBarangStok = 0;
        try (
            Connection conn = new KoneksiDb().connect();
            PreparedStatement ps = conn.prepareStatement("SELECT qty FROM barang WHERE id = ?");
        ) {
            ps.setInt(1, selectedBarang.id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    currentSelectedBarangStok = rs.getInt("qty");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memverifikasi stok barang: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int effectiveStockBeforeMutation = currentSelectedBarangStok;
        if (selectedBarang.id == oldBarangId) {
            if ("keluar".equalsIgnoreCase(oldTipe)) {
                effectiveStockBeforeMutation += oldQty;
            } else {
                effectiveStockBeforeMutation -= oldQty;
            }
        }

        if ("keluar".equalsIgnoreCase(tipe)) {
            if (newQty > effectiveStockBeforeMutation) {
                JOptionPane.showMessageDialog(this,
                    "Stok tidak mencukupi! Stok maksimal yang bisa dikeluarkan: " + effectiveStockBeforeMutation,
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // 3. Execute Transaction
        Connection conn = null;
        try {
            conn = new KoneksiDb().connect();
            conn.setAutoCommit(false); // start transaction

            // a. Reverse old stock change
            String reverseStockSql;
            if ("keluar".equalsIgnoreCase(oldTipe)) {
                reverseStockSql = "UPDATE barang SET qty = qty + ? WHERE id = ?";
            } else {
                reverseStockSql = "UPDATE barang SET qty = qty - ? WHERE id = ?";
            }
            try (PreparedStatement psReverse = conn.prepareStatement(reverseStockSql)) {
                psReverse.setInt(1, oldQty);
                psReverse.setInt(2, oldBarangId);
                psReverse.executeUpdate();
            }

            // b. Apply new stock change
            String applyStockSql;
            if ("keluar".equalsIgnoreCase(tipe)) {
                applyStockSql = "UPDATE barang SET qty = qty - ? WHERE id = ?";
            } else {
                applyStockSql = "UPDATE barang SET qty = qty + ? WHERE id = ?";
            }
            try (PreparedStatement psApply = conn.prepareStatement(applyStockSql)) {
                psApply.setInt(1, newQty);
                psApply.setInt(2, selectedBarang.id);
                psApply.executeUpdate();
            }

            // c. Update mutasi_stok record
            String updateSql = "UPDATE mutasi_stok SET barang_id = ?, project_id = ?, qty = ?, tipe = ?, keterangan = ? WHERE id = ?";
            try (PreparedStatement psUpdate = conn.prepareStatement(updateSql)) {
                psUpdate.setInt(1, selectedBarang.id);
                psUpdate.setString(2, selectedProject.id);
                psUpdate.setInt(3, newQty);
                psUpdate.setString(4, tipe);
                psUpdate.setString(5, keterangan.isEmpty() ? null : keterangan);
                psUpdate.setInt(6, editMutasiId);
                psUpdate.executeUpdate();
            }

            conn.commit(); // commit transaction
            JOptionPane.showMessageDialog(this, "Mutasi barang berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            Navigation.go(this, new MutasiBarangListFrame());

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("Rollback failed: " + ex.getMessage());
                }
            }
            JOptionPane.showMessageDialog(this, "Gagal memperbarui mutasi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Connection close failed: " + e.getMessage());
                }
            }
        }
    }

    private void clearForm() {
        if (cmbTipe.getItemCount() > 0) cmbTipe.setSelectedIndex(0);
        txtBarang.setText("");
        txtProject.setText("");
        selectedBarang = null;
        selectedProject = null;
        txtQty.setText("");
        txtKeterangan.setText("");
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        clearForm();
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        Navigation.go(this, new MutasiBarangListFrame());
    }

    private void showBarangSelectionDialog() {
        loadComboBoxData();

        JDialog dialog = new JDialog(this, "Pilih Barang", true);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel rootPanel = new JPanel(new BorderLayout(12, 12));
        rootPanel.setBackground(new Color(245, 246, 250));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setOpaque(false);
        JLabel searchLabel = new JLabel("Cari Barang");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        searchLabel.setPreferredSize(new Dimension(90, 40));

        JTextField txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(420, 40));
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearch.setMargin(new Insets(0, 12, 0, 12));

        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(txtSearch, BorderLayout.CENTER);

        Object[] columns = {"Kode", "Nama Barang", "Stok"};
        DefaultTableModel dialogModel = new DefaultTableModel(null, columns) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        
        for (BarangItem item : barangList) {
            dialogModel.addRow(new Object[]{item.kode, item.nama, item.qty});
        }
        
        JTable table = new JTable(dialogModel);
        stylePopupTable(table);
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dialogModel);
        table.setRowSorter(sorter);
        
        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void filter() {
                String text = txtSearch.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }
        });

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 1));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        
        components.RoundedButton btnSelect = new components.RoundedButton("Pilih");
        components.RoundedButton btnCancel = new components.RoundedButton("Batal");
        styleDialogButton(btnSelect, true);
        styleDialogButton(btnCancel, false);
        
        buttonPanel.add(btnSelect);
        buttonPanel.add(btnCancel);

        rootPanel.add(searchPanel, BorderLayout.NORTH);
        rootPanel.add(scroll, BorderLayout.CENTER);
        rootPanel.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(rootPanel, BorderLayout.CENTER);

        java.awt.event.ActionListener selectAction = e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = table.convertRowIndexToModel(selectedRow);
                selectedBarang = barangList.get(modelRow);
                txtBarang.setText(selectedBarang.kode + " - " + selectedBarang.nama);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Silakan pilih barang terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        };

        btnSelect.addActionListener(selectAction);
        btnCancel.addActionListener(e -> dialog.dispose());
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    selectAction.actionPerformed(null);
                }
            }
        });

        dialog.setSize(950, 540);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showProjectSelectionDialog() {
        loadComboBoxData();

        JDialog dialog = new JDialog(this, "Pilih Project", true);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel rootPanel = new JPanel(new BorderLayout(12, 12));
        rootPanel.setBackground(new Color(245, 246, 250));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setOpaque(false);
        JLabel searchLabel = new JLabel("Cari Project");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        searchLabel.setPreferredSize(new Dimension(90, 40));

        JTextField txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(420, 40));
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearch.setMargin(new Insets(0, 12, 0, 12));

        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(txtSearch, BorderLayout.CENTER);

        Object[] columns = {"ID Project", "Nama Project"};
        DefaultTableModel dialogModel = new DefaultTableModel(null, columns) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        
        for (ProjectItem item : projectList) {
            dialogModel.addRow(new Object[]{item.id, item.nama});
        }
        
        JTable table = new JTable(dialogModel);
        stylePopupTable(table);
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dialogModel);
        table.setRowSorter(sorter);
        
        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void filter() {
                String text = txtSearch.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }
        });

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 1));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        
        components.RoundedButton btnSelect = new components.RoundedButton("Pilih");
        components.RoundedButton btnCancel = new components.RoundedButton("Batal");
        styleDialogButton(btnSelect, true);
        styleDialogButton(btnCancel, false);
        
        buttonPanel.add(btnSelect);
        buttonPanel.add(btnCancel);

        rootPanel.add(searchPanel, BorderLayout.NORTH);
        rootPanel.add(scroll, BorderLayout.CENTER);
        rootPanel.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(rootPanel, BorderLayout.CENTER);

        java.awt.event.ActionListener selectAction = e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = table.convertRowIndexToModel(selectedRow);
                selectedProject = projectList.get(modelRow);
                txtProject.setText(selectedProject.id + " - " + selectedProject.nama);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Silakan pilih project terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        };

        btnSelect.addActionListener(selectAction);
        btnCancel.addActionListener(e -> dialog.dispose());
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    selectAction.actionPerformed(null);
                }
            }
        });

        dialog.setSize(950, 540);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void stylePopupTable(javax.swing.JTable table) {
        table.setRowHeight(34);
        table.setShowGrid(true);
        table.setGridColor(new Color(225, 225, 225));
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(0, 42));
        table.getTableHeader().setBackground(new Color(70, 64, 150));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBackground(new Color(70, 64, 150));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setDefaultRenderer(headerRenderer);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void styleDialogButton(javax.swing.JButton button, boolean primary) {
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        
        Color bg = primary ? new Color(70, 74, 160) : new Color(217, 217, 217);
        Color fg = primary ? Color.WHITE : Color.BLACK;
        
        if (button instanceof components.RoundedButton) {
            components.RoundedButton rb = (components.RoundedButton) button;
            rb.setButtonColor(bg);
            rb.setRadius(15);
        } else {
            button.setBackground(bg);
        }
        button.setForeground(fg);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        lblTipe = new javax.swing.JLabel();
        cmbTipe = new components.RoundedComboBox();
        lblBarang = new javax.swing.JLabel();
        txtBarang = new components.RoundedTextField();
        btnSearchBarang = new components.RoundedButton();
        lblProject = new javax.swing.JLabel();
        txtProject = new components.RoundedTextField();
        btnSearchProject = new components.RoundedButton();
        lblQty = new javax.swing.JLabel();
        txtQty = new components.RoundedTextField();
        lblKeterangan = new javax.swing.JLabel();
        txtKeterangan = new components.RoundedTextArea();
        btnSave = new components.RoundedButton();
        btnUpdate = new components.RoundedButton();
        btnClear = new components.RoundedButton();
        btnDelete = new components.RoundedButton();
        btnBack = new components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORMULIR MUTASI BARANG");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("FORMULIR MUTASI BARANG");

        lblTipe.setText("Tipe");

        lblBarang.setText("Barang");

        lblProject.setText("Project / Tujuan");

        lblQty.setText("Qty");

        lblKeterangan.setText("Keterangan");

        btnSave.setText("Save");

        btnUpdate.setText("Update");

        btnClear.setText("Clear");

        btnDelete.setText("Delete");
        btnDelete.setButtonColor(new java.awt.Color(154, 61, 120));

        btnBack.setForeground(new java.awt.Color(0, 0, 0));
        btnBack.setText("Back");
        btnBack.setButtonColor(new java.awt.Color(217, 217, 217));

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipe)
                            .addComponent(cmbTipe, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblQty)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblBarang)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnSearchBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblProject)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtProject, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnSearchProject, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblKeterangan)
                    .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                                .addComponent(lblTipe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbTipe, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblQty)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lblBarang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblProject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProject, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchProject, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblKeterangan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
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
    private components.RoundedButton btnBack;
    private components.RoundedButton btnClear;
    private components.RoundedButton btnDelete;
    private components.RoundedButton btnSave;
    private components.RoundedButton btnUpdate;
    private components.RoundedButton btnSearchBarang;
    private components.RoundedButton btnSearchProject;
    private components.RoundedComboBox cmbTipe;
    private javax.swing.JLabel lblBarang;
    private javax.swing.JLabel lblKeterangan;
    private javax.swing.JLabel lblProject;
    private javax.swing.JLabel lblQty;
    private javax.swing.JLabel lblTipe;
    private components.PageTitle pageTitle1;
    private components.SidebarMenu sidebarMenu1;
    private components.RoundedTextField txtBarang;
    private components.RoundedTextArea txtKeterangan;
    private components.RoundedTextField txtProject;
    private components.RoundedTextField txtQty;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
