package monitoring_apps;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import koneksi.KoneksiDb;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReimbursementFormFrame extends javax.swing.JFrame {

    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "monitoring_dev";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private Connection conn= new KoneksiDb().connect();

    private Integer selectedReimbursementId = null;
    private JTable tblItems;
private JScrollPane scrollItems;
private components.RoundedButton btnTambahItem;
private components.RoundedButton btnHapusItem;
private DefaultTableModel itemTableModel;
private String selectedReimbursementNo = null;

    public ReimbursementFormFrame() {
        initComponents();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
        setupReimbursementForm();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
    }// </editor-fold>                        

   

   private void setupReimbursementForm() {
    txtTotal1.setEditable(false);
    txtTotalAkhir.setEditable(false);

   setupHariCombo();
   setupTanggalPicker();

    btnSave.addActionListener(e -> saveReimbursement());
    btnUpdate.addActionListener(e -> updateReimbursement());
    btnDelete.addActionListener(e -> deleteReimbursement());
    btnClear.addActionListener(e -> clearReimbursementForm());

    DocumentListener totalListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            calculateTotal();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            calculateTotal();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            calculateTotal();
        }
    };

    txtKuantitas1.getDocument().addDocumentListener(totalListener);
    txtNominal1.getDocument().addDocumentListener(totalListener);

    txtProyekTujuan.addActionListener(e -> loadLatestByProjectId(false));
    txtProyekTujuan.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            loadLatestByProjectId(false);
        }
    });
}

   private void saveReimbursement() {
    if (!validateReimbursementForm()) {
        return;
    }

    String sql = "INSERT INTO reimbursement "
            + "(project_id, hari, tanggal, hal, uraian, qty, satuan, harga_satuan, jumlah, pj) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        int projectId = parseInteger(txtProyekTujuan.getText());
        String hari = getSelectedHari();
        String tanggal = normalizeTanggalForDb(txtTanggal.getText().trim());
        String hal = txtPerihal.getText().trim();

        String uraian = txtUraian1.getText().trim();
        int qty = parseInteger(txtKuantitas1.getText());
        String satuan = txtSatuan1.getText().trim();
        BigDecimal hargaSatuan = parseDecimal(txtNominal1.getText());
        BigDecimal jumlah = new BigDecimal(qty).multiply(hargaSatuan);

        String pj = getCurrentAdminAccount();

        ps.setInt(1, projectId);
        ps.setString(2, hari);
        ps.setString(3, tanggal);
        ps.setString(4, hal);
        ps.setString(5, uraian);
        ps.setInt(6, qty);
        ps.setString(7, satuan);
        ps.setBigDecimal(8, hargaSatuan);
        ps.setBigDecimal(9, jumlah);
        ps.setString(10, pj);

        ps.executeUpdate();

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                selectedReimbursementId = generatedKeys.getInt(1);
            }
        }

        JOptionPane.showMessageDialog(this, "Data reimbursement berhasil disimpan.");
        clearReimbursementForm();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage());
    } catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Input tidak valid: " + e.getClass().getSimpleName() + " - " + e.getMessage());
}
    
}
 
   private void setupTanggalPicker() {
    txtTanggal.setEditable(false);
    txtTanggal.setToolTipText("Klik untuk pilih tanggal");

    txtTanggal.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            openTanggalPicker();
        }
    });
}

private void openTanggalPicker() {
    Date initialDate = new Date();

    try {
        String currentText = txtTanggal.getText().trim();
        if (!currentText.isEmpty()) {
            initialDate = parseTanggalInput(currentText);
        }
    } catch (Exception ignored) {
        initialDate = new Date();
    }

    SpinnerDateModel model = new SpinnerDateModel(
            initialDate,
            null,
            null,
            Calendar.DAY_OF_MONTH
    );

    JSpinner spinner = new JSpinner(model);
    spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));

    int option = JOptionPane.showConfirmDialog(
            this,
            spinner,
            "Pilih Tanggal",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    if (option == JOptionPane.OK_OPTION) {
        Date selectedDate = (Date) spinner.getValue();
        txtTanggal.setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedDate));
    }
}

private Date parseTanggalInput(String value) throws ParseException {
    String clean = value.trim();

    SimpleDateFormat[] formats = new SimpleDateFormat[]{
        new SimpleDateFormat("yyyy-MM-dd"),
        new SimpleDateFormat("dd/MM/yyyy"),
        new SimpleDateFormat("dd-MM-yyyy"),
        new SimpleDateFormat("ddMMyyyy")
    };

    for (SimpleDateFormat format : formats) {
        format.setLenient(false);
        try {
            return format.parse(clean);
        } catch (ParseException ignored) {
        }
    }

    throw new ParseException("Format tanggal tidak valid", 0);
}

private String normalizeTanggalForDb(String value) throws ParseException {
    Date date = parseTanggalInput(value);
    return new SimpleDateFormat("yyyy-MM-dd").format(date);
}

 private void updateReimbursement() {
    if (!validateReimbursementForm()) {
        return;
    }

    if (selectedReimbursementId == null) {
        boolean found = loadLatestByProjectId(true);
        if (!found || selectedReimbursementId == null) {
            JOptionPane.showMessageDialog(this, "Data belum ditemukan. Isi ID Proyek/Tujuan yang sudah tersimpan, lalu tekan Enter atau klik keluar field.");
            return;
        }
    }

    String sql = "UPDATE reimbursement SET "
            + "project_id = ?, "
            + "hari = ?, "
            + "tanggal = ?, "
            + "hal = ?, "
            + "uraian = ?, "
            + "qty = ?, "
            + "satuan = ?, "
            + "harga_satuan = ?, "
            + "jumlah = ?, "
            + "pj = ? "
            + "WHERE id = ?";

    try (
         PreparedStatement ps = conn.prepareStatement(sql)) {

        int projectId = parseInteger(txtProyekTujuan.getText());
        String hari = getSelectedHari();
    String tanggal = normalizeTanggalForDb(txtTanggal.getText().trim());
        String hal = txtPerihal.getText().trim();

        String uraian = txtUraian1.getText().trim();
        int qty = parseInteger(txtKuantitas1.getText());
        String satuan = txtSatuan1.getText().trim();
        BigDecimal hargaSatuan = parseDecimal(txtNominal1.getText());
        BigDecimal jumlah = new BigDecimal(qty).multiply(hargaSatuan);

        String pj = getCurrentAdminAccount();

        ps.setInt(1, projectId);
        ps.setString(2, hari);
        ps.setString(3, tanggal);
        ps.setString(4, hal);
        ps.setString(5, uraian);
        ps.setInt(6, qty);
        ps.setString(7, satuan);
        ps.setBigDecimal(8, hargaSatuan);
        ps.setBigDecimal(9, jumlah);
        ps.setString(10, pj);
        ps.setInt(11, selectedReimbursementId);

        int affectedRows = ps.executeUpdate();

        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(this, "Data reimbursement berhasil diupdate.");
            clearReimbursementForm();
        } else {
            JOptionPane.showMessageDialog(this, "Data reimbursement tidak ditemukan.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal update data: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Input tidak valid: " + e.getMessage());
    }
}

    private void deleteReimbursement() {
        if (selectedReimbursementId == null) {
            boolean found = loadLatestByProjectId(true);
            if (!found || selectedReimbursementId == null) {
                JOptionPane.showMessageDialog(this, "Data belum ditemukan. Isi ID Proyek/Tujuan yang sudah tersimpan, lalu tekan Enter atau klik keluar field.");
                return;
            }
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data reimbursement ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM reimbursement WHERE id = ?";

        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, selectedReimbursementId);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Data reimbursement berhasil dihapus.");
                clearReimbursementForm();
            } else {
                JOptionPane.showMessageDialog(this, "Data reimbursement tidak ditemukan.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal hapus data: " + e.getMessage());
        }
    }

    private boolean loadLatestByProjectId(boolean showMessageIfNotFound) {
    String projectText = txtProyekTujuan.getText().trim();

    if (projectText.isEmpty()) {
        selectedReimbursementId = null;
        return false;
    }

    int projectId;
    try {
        projectId = parseInteger(projectText);
    } catch (Exception e) {
        selectedReimbursementId = null;
        return false;
    }

    String sql = "SELECT id, project_id, hari, tanggal, hal, uraian, qty, satuan, harga_satuan, jumlah, pj "
            + "FROM reimbursement "
            + "WHERE project_id = ? "
            + "ORDER BY id DESC "
            + "LIMIT 1";

    try (
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, projectId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                selectedReimbursementId = rs.getInt("id");

                txtProyekTujuan.setText(String.valueOf(rs.getInt("project_id")));
                setSelectedHari(nullToEmpty(rs.getString("hari")));
                txtTanggal.setText(nullToEmpty(rs.getString("tanggal")));
                txtPerihal.setText(nullToEmpty(rs.getString("hal")));

                txtUraian1.setText(nullToEmpty(rs.getString("uraian")));
                txtKuantitas1.setText(String.valueOf(rs.getInt("qty")));
                txtSatuan1.setText(nullToEmpty(rs.getString("satuan")));
                txtNominal1.setText(bigDecimalToString(rs.getBigDecimal("harga_satuan")));
                txtTotal1.setText(bigDecimalToString(rs.getBigDecimal("jumlah")));
                txtTotalAkhir.setText(bigDecimalToString(rs.getBigDecimal("jumlah")));

                return true;
            }
        }

        selectedReimbursementId = null;

        if (showMessageIfNotFound) {
            JOptionPane.showMessageDialog(this, "Data reimbursement dengan ID Proyek/Tujuan tersebut tidak ditemukan.");
        }

        return false;

    } catch (SQLException e) {
        selectedReimbursementId = null;
        JOptionPane.showMessageDialog(this, "Gagal mengambil data: " + e.getMessage());
        return false;
    }
}

private void setupHariCombo() {
    cmbHari.removeAllItems();

    cmbHari.addItem("Pilih Hari");
    cmbHari.addItem("Senin");
    cmbHari.addItem("Selasa");
    cmbHari.addItem("Rabu");
    cmbHari.addItem("Kamis");
    cmbHari.addItem("Jumat");
    cmbHari.addItem("Sabtu");
    cmbHari.addItem("Minggu");

    cmbHari.setSelectedIndex(0);
}

private String getSelectedHari() {
    Object item = cmbHari.getSelectedItem();

    if (item == null) {
        return "";
    }

    String hari = item.toString().trim();

    if (hari.equalsIgnoreCase("Pilih") || hari.equalsIgnoreCase("Pilih Hari")) {
        return "";
    }

    return hari;
}

private void setSelectedHari(String hari) {
    if (hari == null || hari.trim().isEmpty()) {
        cmbHari.setSelectedIndex(0);
        return;
    }

    String value = hari.trim();

    for (int i = 0; i < cmbHari.getItemCount(); i++) {
        Object item = cmbHari.getItemAt(i);

        if (item != null && item.toString().equalsIgnoreCase(value)) {
            cmbHari.setSelectedIndex(i);
            return;
        }
    }

    // Kalau data lama isinya Pengadaan/Konstruksi/Administrasi,
    // jangan dimasukkan lagi ke combo Hari.
    cmbHari.setSelectedIndex(0);
}



private String getCurrentAdminAccount() {
    String userFromCard = findUserTextFromContainer(userProfileCard1);

    if (!userFromCard.isEmpty()) {
        return userFromCard;
    }

    String osUser = System.getProperty("user.name");
    if (osUser != null && !osUser.trim().isEmpty()) {
        return osUser.trim();
    }

    return "ADMIN";
}

private String findUserTextFromContainer(java.awt.Container container) {
    if (container == null) {
        return "";
    }

    java.awt.Component[] components = container.getComponents();

    for (java.awt.Component component : components) {
        if (component instanceof javax.swing.JLabel) {
            String text = ((javax.swing.JLabel) component).getText();

            if (text != null && text.trim().toLowerCase().startsWith("user")) {
                return text.trim();
            }
        }

        if (component instanceof java.awt.Container) {
            String result = findUserTextFromContainer((java.awt.Container) component);

            if (!result.isEmpty()) {
                return result;
            }
        }
    }

    return "";
}

    private void clearReimbursementForm() {
        selectedReimbursementId = null;

        txtProyekTujuan.setText("");
        txtTanggal.setText("");
        txtPerihal.setText("");
        txtUraian1.setText("");
        txtKuantitas1.setText("");
        txtSatuan1.setText("");
        txtNominal1.setText("");
        txtTotal1.setText("");
        txtTotalAkhir.setText("");

        if (cmbHari.getItemCount() > 0) {
            cmbHari.setSelectedIndex(0);
        }

        txtProyekTujuan.requestFocus();
    }

    private void calculateTotal() {
        try {
            String qtyText = txtKuantitas1.getText().trim();
            String nominalText = txtNominal1.getText().trim();

            if (qtyText.isEmpty() || nominalText.isEmpty()) {
                txtTotal1.setText("");
                txtTotalAkhir.setText("");
                return;
            }

            int qty = parseInteger(qtyText);
            BigDecimal hargaSatuan = parseDecimal(nominalText);
            BigDecimal total = new BigDecimal(qty).multiply(hargaSatuan);

            txtTotal1.setText(total.toPlainString());
            txtTotalAkhir.setText(total.toPlainString());

        } catch (Exception e) {
            txtTotal1.setText("");
            txtTotalAkhir.setText("");
        }
    }

    private boolean validateReimbursementForm() {
    if (txtProyekTujuan.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Proyek / Tujuan wajib diisi dengan ID Project.");
        txtProyekTujuan.requestFocus();
        return false;
    }

    try {
        parseInteger(txtProyekTujuan.getText());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Proyek / Tujuan harus berupa angka ID Project.");
        txtProyekTujuan.requestFocus();
        return false;
    }

 if (getSelectedHari().isEmpty()) {
    JOptionPane.showMessageDialog(this, "Hari wajib dipilih.");
    cmbHari.requestFocus();
    return false;
}

if (txtTanggal.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(this, "Tanggal wajib diisi.");
    txtTanggal.requestFocus();
    return false;
}

try {
    normalizeTanggalForDb(txtTanggal.getText().trim());
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Format tanggal tidak valid. Klik field tanggal lalu pilih tanggal.");
    txtTanggal.requestFocus();
    return false;
}

    if (txtPerihal.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Perihal wajib diisi.");
        txtPerihal.requestFocus();
        return false;
    }

    if (txtUraian1.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Uraian wajib diisi.");
        txtUraian1.requestFocus();
        return false;
    }

    if (txtKuantitas1.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Kuantitas wajib diisi.");
        txtKuantitas1.requestFocus();
        return false;
    }

    try {
        parseInteger(txtKuantitas1.getText());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Kuantitas harus angka.");
        txtKuantitas1.requestFocus();
        return false;
    }

    if (txtSatuan1.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Satuan wajib diisi.");
        txtSatuan1.requestFocus();
        return false;
    }

    if (txtNominal1.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nominal wajib diisi.");
        txtNominal1.requestFocus();
        return false;
    }

    try {
        parseDecimal(txtNominal1.getText());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Nominal harus angka.");
        txtNominal1.requestFocus();
        return false;
    }

    calculateTotal();

    if (txtTotal1.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Total gagal dihitung. Periksa kuantitas dan nominal.");
        return false;
    }

    return true;
}

    private int parseInteger(String value) {
        String cleanValue = value.trim().replaceAll("[^0-9]", "");

        if (cleanValue.isEmpty()) {
            throw new NumberFormatException("Angka kosong.");
        }

        return Integer.parseInt(cleanValue);
    }

    private BigDecimal parseDecimal(String value) {
        String cleanValue = value.trim()
                .replace("Rp", "")
                .replace("rp", "")
                .replace(" ", "");

        if (cleanValue.contains(",") && cleanValue.matches(".*,[0-9]{1,2}$")) {
            cleanValue = cleanValue.replace(".", "");
            cleanValue = cleanValue.replace(",", ".");
        } else {
            cleanValue = cleanValue.replace(".", "");
            cleanValue = cleanValue.replace(",", "");
        }

        if (cleanValue.isEmpty()) {
            throw new NumberFormatException("Angka kosong.");
        }

        return new BigDecimal(cleanValue);
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }

    private String bigDecimalToString(BigDecimal value) {
        return value == null ? "" : value.toPlainString();
    }

   public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReimbursementFormFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
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
    // End of variables declaration                   
}
