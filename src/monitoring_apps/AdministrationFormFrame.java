package monitoring_apps;

import static Utills.Helper.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.KoneksiDb;
import models.AdministrationData;

public class AdministrationFormFrame extends javax.swing.JFrame {
     private Connection conn;
     private int currentAdminId = -1;
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public AdministrationFormFrame() {
        conn = new KoneksiDb().connect();
        initComponents();
        initUi();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
    }

    public AdministrationFormFrame(int administrationId) {
        conn = new KoneksiDb().connect();
        initComponents();
        initUi();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
        loadAdministrationData(administrationId);
    }
    
    private void initUi(){
        btnLovProjek.setText("");
        btnLovProjek.setIcon(new AdministrationFormFrame.SearchIcon(20, Color.WHITE));
        btnLovProjek.setToolTipText("Cari dan pilih proyek / tujuan");
        btnLovProjek.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLovProjek.setFocusable(false);
        btnLovProjek.setContentAreaFilled(true);
        btnLovProjek.setOpaque(true);
        btnLovProjek.setBackground(new Color(70, 74, 160));
        btnLovProjek.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        btnLovProjek.setMargin(new Insets(0, 0, 0, 0));

        cmbTipeSurat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Pilih Tipe Surat",
            "Surat Tugas",
            "Surat Perintah",
            "Surat Jalan",
            "Surat Internal"
        }));
        btnUpdate.setEnabled(false);
    }
    private static class SearchIcon implements Icon {

        private final int size;
        private final Color color;

        SearchIcon(int size, Color color) {
            this.size = size;
            this.color = color;
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(2.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            int lensSize = Math.max(9, size - 8);
            int lensX = x + 2;
            int lensY = y + 2;
            g2.drawOval(lensX, lensY, lensSize, lensSize);

            int startX = lensX + lensSize - 1;
            int startY = lensY + lensSize - 1;
            int endX = x + size - 2;
            int endY = y + size - 2;
            g2.drawLine(startX, startY, endX, endY);
            g2.dispose();
        }
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
        btnSave = new components.RoundedButton();
        btnUpdate = new components.RoundedButton();
        btnClear = new components.RoundedButton();
        btnBack = new components.RoundedButton();
        dateSurat = new com.toedter.calendar.JDateChooser();
        txtProjectId = new components.AppTextField();
        btnLovProjek = new components.RoundedButton();
        lblKepada1 = new javax.swing.JLabel();
        lblKepada2 = new javax.swing.JLabel();
        txtProjectName = new components.AppTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORMULIR ADMINISTRASI");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("FORMULIR ADMINISTRASI");

        lblTipeSurat.setText("Tipe Surat");

        lblNamaSurat.setText("Nama Surat");

        lblNomorSurat.setText("Nomor Surat");

        lblDari.setText("Pengirim");

        lblKepada.setText("Penerima");

        lblTanggalSurat.setText("Tanggal Surat");

        btnSave.setText("Save");

        btnUpdate.setText("Update");

        btnClear.setText("Clear");

        btnBack.setForeground(new java.awt.Color(0, 0, 0));
        btnBack.setText("Back");
        btnBack.setButtonColor(new java.awt.Color(217, 217, 217));

        dateSurat.setPreferredSize(new java.awt.Dimension(420, 42));

        txtProjectId.setEnabled(false);

        btnLovProjek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLovProjekActionPerformed(evt);
            }
        });

        lblKepada1.setText("Project Id");

        lblKepada2.setText("Project Name");

        txtProjectName.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userProfileCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sidebarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTipeSurat)
                                    .addComponent(cmbTipeSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNomorSurat)
                                        .addComponent(txtNomorSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDari)
                                    .addComponent(txtDari, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNamaSurat)
                                    .addComponent(txtNamaSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTanggalSurat)
                                    .addComponent(dateSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblKepada)
                            .addComponent(txtKepada, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtProjectId, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLovProjek, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblKepada1))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(lblKepada2)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblNomorSurat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomorSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDari, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblKepada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKepada, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTanggalSurat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(lblKepada1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtProjectId, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLovProjek, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblKepada2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLovProjekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLovProjekActionPerformed
        try {
            showProjectChooserDialog();
        } catch (Exception e) {
          showError("Gagal load data: " + e.getMessage());
        }
    }//GEN-LAST:event_btnLovProjekActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            p_SaveData();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (currentAdminId <= 0) {
                showError("Tidak ada data administrasi untuk diupdate.");
                return;
            }
            p_SaveData();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        clearFields();
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        Navigation.go(AdministrationFormFrame.this, new Dashboard());
    }

    private void clearFields() {
        cmbTipeSurat.setSelectedIndex(0);
        txtNamaSurat.setText("");
        txtNomorSurat.setText("");
        txtDari.setText("");
        txtKepada.setText("");
        dateSurat.setDate(null);
        txtProjectId.setText("");
        txtProjectName.setText("");
        currentAdminId = -1;
        btnSave.setEnabled(true);
        btnUpdate.setEnabled(false);
    }

    private void p_ValidateData() {
        if (cmbTipeSurat.getSelectedIndex() <= 0) {
            throw new IllegalArgumentException("Pilih tipe surat terlebih dahulu.");
        }
        if (txtNamaSurat.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama surat tidak boleh kosong.");
        }
        if (txtNomorSurat.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor surat tidak boleh kosong.");
        }
        if (txtDari.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Pengirim tidak boleh kosong.");
        }
        if (txtKepada.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Penerima tidak boleh kosong.");
        }
        if (dateSurat.getDate() == null) {
            throw new IllegalArgumentException("Tanggal surat belum dipilih.");
        }
        if (txtProjectId.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Pilih proyek terlebih dahulu.");
        }
    }

    private AdministrationData uiToData() {
        AdministrationData dat = new AdministrationData();
        dat.id = currentAdminId;
        dat.tipe = cmbTipeSurat.getSelectedItem().toString();
        dat.nama = txtNamaSurat.getText().trim();
        dat.noSurat = txtNomorSurat.getText().trim();
        dat.pengirim = txtDari.getText().trim();
        dat.penerima = txtKepada.getText().trim();
        Date date = dateSurat.getDate();
        dat.tglSurat = date == null ? null : format.format(date);
        dat.projectId = txtProjectId.getText().trim();
        dat.projectName = txtProjectName.getText().trim();
        return dat;
    }

    private void p_SaveData() {
        p_ValidateData();
        AdministrationData dat = uiToData();
        if (dat.id <= 0) {
            createAdministration(dat);
            showInfo("Data administrasi berhasil disimpan.");
        } else {
            updateAdministration(dat);
            showInfo("Data administrasi berhasil diperbarui.");
        }
        Navigation.go(AdministrationFormFrame.this, new AdministarationList());
    }

    private void createAdministration(AdministrationData dat) {
        String query = "INSERT INTO administrasi (tipe, nama, no_surat, pengirim, penerima, tgl_surat, project_id, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, dat.tipe);
            ps.setString(2, dat.nama);
            ps.setString(3, dat.noSurat);
            ps.setString(4, dat.pengirim);
            ps.setString(5, dat.penerima);
            ps.setString(6, dat.tglSurat);
            ps.setString(7, dat.projectId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Gagal menyimpan data administrasi: " + e.getMessage());
        }
    }

    private void updateAdministration(AdministrationData dat) {
        String query = "UPDATE administrasi SET tipe = ?, nama = ?, no_surat = ?, pengirim = ?, penerima = ?, tgl_surat = ?, project_id = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, dat.tipe);
            ps.setString(2, dat.nama);
            ps.setString(3, dat.noSurat);
            ps.setString(4, dat.pengirim);
            ps.setString(5, dat.penerima);
            ps.setString(6, dat.tglSurat);
            ps.setString(7, dat.projectId);
            ps.setInt(8, dat.id);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new IllegalArgumentException("Data administrasi tidak ditemukan untuk diupdate.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Gagal mengupdate data administrasi: " + e.getMessage());
        }
    }

    private void loadAdministrationData(int administrationId) {
        String query = "SELECT a.id, a.tipe, a.nama, a.no_surat, a.pengirim, a.penerima, a.tgl_surat, a.project_id, COALESCE(p.nama, '') AS project_name "
                + "FROM administrasi a "
                + "LEFT JOIN project p ON a.project_id = p.id "
                + "WHERE a.id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, administrationId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    currentAdminId = rs.getInt("id");
                    cmbTipeSurat.setSelectedItem(rs.getString("tipe"));
                    txtNamaSurat.setText(rs.getString("nama"));
                    txtNomorSurat.setText(rs.getString("no_surat"));
                    txtDari.setText(rs.getString("pengirim"));
                    txtKepada.setText(rs.getString("penerima"));
                    String dateValue = rs.getString("tgl_surat");
                    if (dateValue != null && !dateValue.trim().isEmpty()) {
                        dateSurat.setDate(format.parse(dateValue));
                    } else {
                        dateSurat.setDate(null);
                    }
                    txtProjectId.setText(rs.getString("project_id"));
                    txtProjectName.setText(rs.getString("project_name"));
                    btnSave.setEnabled(false);
                    btnUpdate.setEnabled(true);
                }
            }
        } catch (Exception e) {
            showError("Gagal load data administrasi: " + e.getMessage());
        }
    }

    private String fetchProjectName(String projectId) {
        if (projectId == null || projectId.trim().isEmpty()) {
            return "";
        }
        String query = "SELECT nama FROM project WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, projectId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nama");
                }
            }
        } catch (SQLException e) {
            // ignore
        }
        return "";
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Gagal", JOptionPane.WARNING_MESSAGE);
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showProjectChooserDialog() {
        javax.swing.JDialog dialog = new javax.swing.JDialog(this, "Pilih Proyek / Tujuan", true);
        dialog.setLayout(new BorderLayout(10, 10));

        javax.swing.JPanel rootPanel = new javax.swing.JPanel(new BorderLayout(12, 12));
        rootPanel.setBackground(new Color(245, 246, 250));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        javax.swing.JPanel searchPanel = new javax.swing.JPanel(new BorderLayout(10, 0));
        searchPanel.setOpaque(false);
        javax.swing.JLabel searchLabel = new javax.swing.JLabel("Cari Proyek");
        javax.swing.JTextField txtSearchProject = new javax.swing.JTextField();
        txtSearchProject.setPreferredSize(new Dimension(420, 40));
        txtSearchProject.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearchProject.setMargin(new Insets(0, 12, 0, 12));
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        searchLabel.setPreferredSize(new Dimension(90, 40));
        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(txtSearchProject, BorderLayout.CENTER);

        DefaultTableModel projectTableModel = new DefaultTableModel(
                new Object[]{"ID", "Nama Proyek", "TA", "Sub Perusahaan", "Jenis", "Instansi"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        javax.swing.JTable tblProjectPopup = new javax.swing.JTable(projectTableModel);
        tblProjectPopup.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        styleProjectPopupTable(tblProjectPopup);
        setProjectPopupColumnWidths(tblProjectPopup);

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(tblProjectPopup);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 1));

        javax.swing.JPanel buttonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        javax.swing.JButton btnChoose = new javax.swing.JButton("Pilih");
        javax.swing.JButton btnCancel = new javax.swing.JButton("Batal");
        styleDialogButton(btnChoose, true);
        styleDialogButton(btnCancel, false);
        buttonPanel.add(btnChoose);
        buttonPanel.add(btnCancel);

        rootPanel.add(searchPanel, BorderLayout.NORTH);
        rootPanel.add(scrollPane, BorderLayout.CENTER);
        rootPanel.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(rootPanel, BorderLayout.CENTER);

        txtSearchProject.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                loadProjectPopupData(projectTableModel, txtSearchProject.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                loadProjectPopupData(projectTableModel, txtSearchProject.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                loadProjectPopupData(projectTableModel, txtSearchProject.getText());
            }
        });

        tblProjectPopup.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    chooseProjectFromDialog(tblProjectPopup, projectTableModel, dialog);
                }
            }
        });

        btnChoose.addActionListener(e -> chooseProjectFromDialog(tblProjectPopup, projectTableModel, dialog));
        btnCancel.addActionListener(e -> dialog.dispose());

        loadProjectPopupData(projectTableModel, "");

        dialog.setSize(950, 540);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }


    private void styleProjectPopupTable(javax.swing.JTable table) {
        table.setRowHeight(34);
        table.setShowGrid(true);
        table.setGridColor(new Color(225, 225, 225));
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
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

    private void setProjectPopupColumnWidths(javax.swing.JTable table) {
        int[] widths = {70, 260, 90, 160, 120, 210};
        for (int i = 0; i < widths.length && i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
            table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
        }
    }

    private void styleDialogButton(javax.swing.JButton button, boolean primary) {
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        if (primary) {
            button.setBackground(new Color(70, 74, 160));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(217, 217, 217));
            button.setForeground(Color.BLACK);
        }
    }

    private void loadProjectPopupData(DefaultTableModel model, String keyword) {
        model.setRowCount(0);

        String search = keyword == null ? "" : keyword.trim();
        String like = "%" + search + "%";

        String sql = "SELECT id, nama, ta, sub_perusahaan, jenis, instansi "
                + "FROM project "
                + "WHERE (? = '' "
                + "OR CAST(id AS CHAR) LIKE ? "
                + "OR COALESCE(nama, '') LIKE ? "
                + "OR COALESCE(ta, '') LIKE ? "
                + "OR COALESCE(sub_perusahaan, '') LIKE ? "
                + "OR COALESCE(jenis, '') LIKE ? "
                + "OR COALESCE(instansi, '') LIKE ?) "
                + "ORDER BY id DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, search);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setString(4, like);
            ps.setString(5, like);
            ps.setString(6, like);
            ps.setString(7, like);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("id"),
                        nullToEmpty(rs.getString("nama")),
                        nullToEmpty(rs.getString("ta")),
                        nullToEmpty(rs.getString("sub_perusahaan")),
                        nullToEmpty(rs.getString("jenis")),
                        nullToEmpty(rs.getString("instansi"))
                    });
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data proyek:\n" + e.getMessage());
        }
    }

    private void chooseProjectFromDialog(javax.swing.JTable table, DefaultTableModel model, javax.swing.JDialog dialog) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(dialog, "Pilih proyek terlebih dahulu.");
            return;
        }

        int modelRow = table.convertRowIndexToModel(selectedRow);
        String projectId = String.valueOf(model.getValueAt(modelRow, 0));
        String projectName = String.valueOf(model.getValueAt(modelRow, 1));

        txtProjectId.setText(projectId);
        txtProjectName.setText(projectName);
        dialog.dispose();
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrationFormFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedButton btnBack;
    private components.RoundedButton btnClear;
    private components.RoundedButton btnLovProjek;
    private components.RoundedButton btnSave;
    private components.RoundedButton btnUpdate;
    private components.RoundedComboBox cmbTipeSurat;
    private com.toedter.calendar.JDateChooser dateSurat;
    private javax.swing.JLabel lblDari;
    private javax.swing.JLabel lblKepada;
    private javax.swing.JLabel lblKepada1;
    private javax.swing.JLabel lblKepada2;
    private javax.swing.JLabel lblNamaSurat;
    private javax.swing.JLabel lblNomorSurat;
    private javax.swing.JLabel lblTanggalSurat;
    private javax.swing.JLabel lblTipeSurat;
    private components.PageTitle pageTitle1;
    private components.SidebarMenu sidebarMenu1;
    private components.RoundedTextField txtDari;
    private components.RoundedTextField txtKepada;
    private components.RoundedTextField txtNamaSurat;
    private components.RoundedTextField txtNomorSurat;
    private components.AppTextField txtProjectId;
    private components.AppTextField txtProjectName;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
