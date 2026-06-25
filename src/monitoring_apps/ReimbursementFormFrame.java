package monitoring_apps;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import koneksi.KoneksiDb;

public class ReimbursementFormFrame extends javax.swing.JFrame {

    private final Connection conn = new KoneksiDb().connect();

    private Integer selectedReimbursementId = null;
    private DefaultTableModel itemTableModel;
    private final List<ProjectComboItem> projectItems = new ArrayList<>();
    private boolean loadingProjectCombo = false;

    public ReimbursementFormFrame() {
        initComponents();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
        setupReimbursementForm();
    }

    public ReimbursementFormFrame(Integer reimbursementId) {
        initComponents();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
        setupReimbursementForm();
        loadReimbursementById(reimbursementId);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSave = new components.RoundedButton();
        btnUpdate = new components.RoundedButton();
        btnClear = new components.RoundedButton();
        btnDelete = new components.RoundedButton();
        btnBack = new components.RoundedButton();
        lblPerihal = new javax.swing.JLabel();
        txtPerihal = new components.RoundedTextArea();
        lblUraian = new javax.swing.JLabel();
        txtUraian1 = new components.RoundedTextField();
        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        lblProyekTujuan = new javax.swing.JLabel();
        lblHari = new javax.swing.JLabel();
        cmbHari = new components.RoundedComboBox();
        lblTanggal = new javax.swing.JLabel();
        cmbproyek = new components.RoundedComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtSatuan = new components.RoundedTextField();
        txtKuantitas = new components.RoundedTextField();
        jLabel2 = new javax.swing.JLabel();
        Nominal = new components.RoundedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new components.RoundedTextField();
        jLabel4 = new javax.swing.JLabel();
        jbtambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSave.setText("Save");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");

        btnDelete.setText("Delete");
        btnDelete.setButtonColor(new java.awt.Color(154, 61, 120));

        btnBack.setText("Back");
        btnBack.setButtonColor(new java.awt.Color(217, 217, 217));

        lblPerihal.setText("Perihal");

        lblUraian.setText("Masukan Uraian");

        pageTitle1.setText("FORMULIR REIMBURSEMENT");

        lblProyekTujuan.setText("Proyek / Tujuan");

        lblHari.setText("Hari");

        cmbHari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHariActionPerformed(evt);
            }
        });

        lblTanggal.setText("Tanggal");

        cmbproyek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbproyekActionPerformed(evt);
            }
        });

        jLabel1.setText("Satuan");

        jLabel2.setText("Kuantitas");

        Nominal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NominalActionPerformed(evt);
            }
        });

        jLabel3.setText("Nominal");

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel4.setText("Total");

        jbtambah.setText("+");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProyekTujuan)
                            .addComponent(cmbproyek, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHari)
                            .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTanggal)
                    .addComponent(lblPerihal)
                    .addComponent(txtPerihal, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(49, 49, 49)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(58, 58, 58)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUraian1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblUraian))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Nominal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jbtambah, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userProfileCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sidebarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbproyek, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblProyekTujuan))
                        .addGap(18, 18, 18)
                        .addComponent(lblTanggal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPerihal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPerihal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUraian)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUraian1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Nominal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtambah, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cmbHariActionPerformed(java.awt.event.ActionEvent evt) {
        // No action needed
    }

    private void cmbproyekActionPerformed(java.awt.event.ActionEvent evt) {
        // No action needed
    }

    private void NominalActionPerformed(java.awt.event.ActionEvent evt) {
        // No action needed
    }

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {
        // No action needed
    }

    private static class ProjectComboItem {

        private final int id;
        private final String name;

        public ProjectComboItem(int id, String name) {
            this.id = id;
            this.name = name == null ? "" : name;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            if (id <= 0) {
                return name;
            }
            return id + " - " + name;
        }
    }

    private void setupReimbursementForm() {
        txtTotal.setEditable(false);

        setupHariCombo();
        setupDateChooser();
        setupProjectCombo();
        setupItemTable();

        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);

        jbtambah.addActionListener(e -> addItemToTable());

        btnSave.addActionListener(e -> saveReimbursement());
        btnUpdate.addActionListener(e -> updateReimbursement());
        btnDelete.addActionListener(e -> deleteReimbursement());
        btnClear.addActionListener(e -> clearReimbursementForm());
        btnBack.addActionListener(e -> {
            new ReimbursementListFrame().setVisible(true);
            dispose();
        });

        DocumentListener totalListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateCurrentItemTotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateCurrentItemTotal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateCurrentItemTotal();
            }
        };

        txtKuantitas.getDocument().addDocumentListener(totalListener);
        Nominal.getDocument().addDocumentListener(totalListener);
    }

    private void setupProjectCombo() {
        cmbproyek.setEditable(true);
        loadProjectCombo();

        Object editorComponent = cmbproyek.getEditor().getEditorComponent();
        if (editorComponent instanceof JTextComponent) {
            JTextComponent editor = (JTextComponent) editorComponent;
            editor.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP
                            || e.getKeyCode() == KeyEvent.VK_DOWN
                            || e.getKeyCode() == KeyEvent.VK_ENTER
                            || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        return;
                    }

                    filterProjectCombo(editor.getText());
                }
            });
        }
    }

    private void loadProjectCombo() {
        projectItems.clear();

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement(new ProjectComboItem(0, "Pilih"));

        String[] queries = new String[]{
            "SELECT id, nama AS project_name FROM project ORDER BY id DESC",
            "SELECT id, project_name AS project_name FROM project ORDER BY id DESC",
            "SELECT id, nama_project AS project_name FROM project ORDER BY id DESC",
            "SELECT id, nama_proyek AS project_name FROM project ORDER BY id DESC",
            "SELECT id, title AS project_name FROM project ORDER BY id DESC",
            "SELECT id, name AS project_name FROM project ORDER BY id DESC",
            "SELECT id, nama AS project_name FROM projects ORDER BY id DESC",
            "SELECT id, project_name AS project_name FROM projects ORDER BY id DESC",
            "SELECT id, nama_project AS project_name FROM projects ORDER BY id DESC",
            "SELECT id, nama_proyek AS project_name FROM projects ORDER BY id DESC",
            "SELECT id, title AS project_name FROM projects ORDER BY id DESC",
            "SELECT id, name AS project_name FROM projects ORDER BY id DESC",
            "SELECT id, nama AS project_name FROM project_administration ORDER BY id DESC",
            "SELECT id, project_name AS project_name FROM project_administration ORDER BY id DESC",
            "SELECT id, nama_project AS project_name FROM project_administration ORDER BY id DESC",
            "SELECT id, nama_proyek AS project_name FROM project_administration ORDER BY id DESC",
            "SELECT id, title AS project_name FROM project_administration ORDER BY id DESC",
            "SELECT id, name AS project_name FROM project_administration ORDER BY id DESC",
            "SELECT id, nama AS project_name FROM project_administrations ORDER BY id DESC",
            "SELECT id, project_name AS project_name FROM project_administrations ORDER BY id DESC",
            "SELECT id, nama_project AS project_name FROM project_administrations ORDER BY id DESC",
            "SELECT id, nama_proyek AS project_name FROM project_administrations ORDER BY id DESC",
            "SELECT id, title AS project_name FROM project_administrations ORDER BY id DESC",
            "SELECT id, name AS project_name FROM project_administrations ORDER BY id DESC"
        };

        for (String sql : queries) {
            if (tryLoadProjectsWithSql(sql, model)) {
                break;
            }
        }

        loadingProjectCombo = true;
        cmbproyek.setModel(model);
        cmbproyek.setSelectedIndex(0);
        loadingProjectCombo = false;
    }

    private boolean tryLoadProjectsWithSql(String sql, DefaultComboBoxModel model) {
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                ProjectComboItem item = new ProjectComboItem(rs.getInt("id"), rs.getString("project_name"));
                projectItems.add(item);
                model.addElement(item);
            }

            return hasData;
        } catch (SQLException ignored) {
            return false;
        }
    }

    private void filterProjectCombo(String keyword) {
        if (loadingProjectCombo) {
            return;
        }

        String search = keyword == null ? "" : keyword.trim().toLowerCase();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement(new ProjectComboItem(0, "Pilih"));

        for (ProjectComboItem item : projectItems) {
            String text = item.toString().toLowerCase();
            if (search.isEmpty() || text.contains(search)) {
                model.addElement(item);
            }
        }

        loadingProjectCombo = true;
        cmbproyek.setModel(model);
        cmbproyek.setSelectedItem(keyword);

        Object editorComponent = cmbproyek.getEditor().getEditorComponent();
        if (editorComponent instanceof JTextComponent) {
            ((JTextComponent) editorComponent).setText(keyword);
        }

        cmbproyek.showPopup();
        loadingProjectCombo = false;
    }

    private Integer getSelectedProjectId() {
        Object selected = cmbproyek.getSelectedItem();

        if (selected instanceof ProjectComboItem) {
            ProjectComboItem item = (ProjectComboItem) selected;
            return item.getId() <= 0 ? null : item.getId();
        }

        if (selected != null) {
            String text = selected.toString().trim();

            if (text.matches("^\\d+.*")) {
                try {
                    return Integer.parseInt(text.replaceAll("[^0-9].*$", ""));
                } catch (Exception ignored) {
                }
            }
        }

        return null;
    }

    private void setSelectedProjectById(int projectId) {
        for (ProjectComboItem item : projectItems) {
            if (item.getId() == projectId) {
                cmbproyek.setSelectedItem(item);
                return;
            }
        }

        cmbproyek.setSelectedItem(projectId + " - Project ID " + projectId);
    }

    private void setupDateChooser() {
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setDate(new Date());
    }

    private String getTanggalForDb() {
        Date selectedDate = jDateChooser1.getDate();

        if (selectedDate == null) {
            return "";
        }

        return new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
    }

    private void setTanggalFromDb(String value) {
        if (value == null || value.trim().isEmpty()) {
            jDateChooser1.setDate(null);
            return;
        }

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value.trim());
            jDateChooser1.setDate(date);
        } catch (Exception e) {
            jDateChooser1.setDate(null);
        }
    }

    private void setupItemTable() {
        itemTableModel = new DefaultTableModel(
                new Object[]{"Uraian", "Qty", "Satuan", "Nominal", "Total"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTable1.setModel(itemTableModel);
        jTable1.setRowHeight(30);
        jTable1.setShowGrid(true);
        jTable1.setFillsViewportHeight(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    removeSelectedItem();
                }
            }
        });

        updateTableTitle(BigDecimal.ZERO);
    }

    private void addItemToTable() {
        String uraian = txtUraian1.getText().trim();
        String qtyText = txtKuantitas.getText().trim();
        String satuan = txtSatuan.getText().trim();
        String nominalText = Nominal.getText().trim();

        if (uraian.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Uraian wajib diisi.");
            txtUraian1.requestFocus();
            return;
        }

        if (qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kuantitas wajib diisi.");
            txtKuantitas.requestFocus();
            return;
        }

        if (satuan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Satuan wajib diisi.");
            txtSatuan.requestFocus();
            return;
        }

        if (nominalText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nominal wajib diisi.");
            Nominal.requestFocus();
            return;
        }

        try {
            int qty = parseInteger(qtyText);
            BigDecimal nominal = parseDecimal(nominalText);
            BigDecimal total = new BigDecimal(qty).multiply(nominal);

            itemTableModel.addRow(new Object[]{
                uraian,
                qty,
                satuan,
                nominal.toPlainString(),
                total.toPlainString()
            });

            clearItemInput();
            calculateTotalAkhir();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Item tidak valid: " + e.getMessage());
        }
    }

    private void removeSelectedItem() {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        itemTableModel.removeRow(selectedRow);
        calculateTotalAkhir();
    }

    private void clearItemInput() {
        txtUraian1.setText("");
        txtKuantitas.setText("");
        txtSatuan.setText("");
        Nominal.setText("");
        txtTotal.setText("");
        txtUraian1.requestFocus();
    }

    private void calculateCurrentItemTotal() {
        try {
            String qtyText = txtKuantitas.getText().trim();
            String nominalText = Nominal.getText().trim();

            if (qtyText.isEmpty() || nominalText.isEmpty()) {
                txtTotal.setText("");
                return;
            }

            int qty = parseInteger(qtyText);
            BigDecimal nominal = parseDecimal(nominalText);
            BigDecimal total = new BigDecimal(qty).multiply(nominal);
            txtTotal.setText(total.toPlainString());
        } catch (Exception e) {
            txtTotal.setText("");
        }
    }

    private BigDecimal calculateTotalAkhir() {
        BigDecimal totalAkhir = BigDecimal.ZERO;

        for (int i = 0; i < itemTableModel.getRowCount(); i++) {
            BigDecimal totalItem = parseDecimal(String.valueOf(itemTableModel.getValueAt(i, 4)));
            totalAkhir = totalAkhir.add(totalItem);
        }

        updateTableTitle(totalAkhir);
        return totalAkhir;
    }

    private void updateTableTitle(BigDecimal totalAkhir) {
        jScrollPane1.setBorder(BorderFactory.createTitledBorder(
                "Daftar Item Reimbursement | Total Akhir: " + totalAkhir.toPlainString()
        ));
    }

    private void saveReimbursement() {
        if (!validateReimbursementForm()) {
            return;
        }

        String insertHeaderSql = "INSERT INTO reimbursement "
                + "(reimbursement_no, project_id, hari, tanggal, hal, pj, total_akhir) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String insertItemSql = "INSERT INTO reimbursement_items "
                + "(reimbursement_id, uraian, qty, satuan, harga_satuan, jumlah) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);

            int reimbursementId;

            try (PreparedStatement ps = conn.prepareStatement(insertHeaderSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, generateReimbursementNo());
                ps.setInt(2, getSelectedProjectId());
                ps.setString(3, getSelectedHari());
                ps.setString(4, getTanggalForDb());
                ps.setString(5, txtPerihal.getText().trim());
                ps.setString(6, getCurrentAdminAccount());
                ps.setBigDecimal(7, calculateTotalAkhir());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) {
                        throw new SQLException("Gagal mendapatkan ID reimbursement.");
                    }

                    reimbursementId = rs.getInt(1);
                }
            }

            saveItems(reimbursementId, insertItemSql);
            conn.commit();

            JOptionPane.showMessageDialog(this, "Data reimbursement berhasil disimpan.");
            clearReimbursementForm();

        } catch (Exception e) {
            rollbackQuietly();
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage());
        } finally {
            restoreAutoCommitQuietly();
        }
    }

    private void updateReimbursement() {
        if (!validateReimbursementForm()) {
            return;
        }

        if (selectedReimbursementId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data reimbursement dari list terlebih dahulu untuk update.");
            return;
        }

        String updateHeaderSql = "UPDATE reimbursement SET "
                + "project_id = ?, hari = ?, tanggal = ?, hal = ?, pj = ?, total_akhir = ? "
                + "WHERE id = ?";

        String deleteItemsSql = "DELETE FROM reimbursement_items WHERE reimbursement_id = ?";

        String insertItemSql = "INSERT INTO reimbursement_items "
                + "(reimbursement_id, uraian, qty, satuan, harga_satuan, jumlah) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(updateHeaderSql)) {
                ps.setInt(1, getSelectedProjectId());
                ps.setString(2, getSelectedHari());
                ps.setString(3, getTanggalForDb());
                ps.setString(4, txtPerihal.getText().trim());
                ps.setString(5, getCurrentAdminAccount());
                ps.setBigDecimal(6, calculateTotalAkhir());
                ps.setInt(7, selectedReimbursementId);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = conn.prepareStatement(deleteItemsSql)) {
                ps.setInt(1, selectedReimbursementId);
                ps.executeUpdate();
            }

            saveItems(selectedReimbursementId, insertItemSql);
            conn.commit();

            JOptionPane.showMessageDialog(this, "Data reimbursement berhasil diupdate.");
            clearReimbursementForm();

        } catch (Exception e) {
            rollbackQuietly();
            JOptionPane.showMessageDialog(this, "Gagal update data: " + e.getMessage());
        } finally {
            restoreAutoCommitQuietly();
        }
    }

    private void saveItems(int reimbursementId, String insertItemSql) throws SQLException {
        try (PreparedStatement psItem = conn.prepareStatement(insertItemSql)) {
            for (int i = 0; i < itemTableModel.getRowCount(); i++) {
                psItem.setInt(1, reimbursementId);
                psItem.setString(2, String.valueOf(itemTableModel.getValueAt(i, 0)));
                psItem.setInt(3, parseInteger(String.valueOf(itemTableModel.getValueAt(i, 1))));
                psItem.setString(4, String.valueOf(itemTableModel.getValueAt(i, 2)));
                psItem.setBigDecimal(5, parseDecimal(String.valueOf(itemTableModel.getValueAt(i, 3))));
                psItem.setBigDecimal(6, parseDecimal(String.valueOf(itemTableModel.getValueAt(i, 4))));
                psItem.addBatch();
            }

            psItem.executeBatch();
        }
    }

    private void deleteReimbursement() {
        if (selectedReimbursementId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data reimbursement dari list terlebih dahulu untuk delete.");
            return;
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

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
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

    public void loadReimbursementById(Integer reimbursementId) {
        if (reimbursementId == null) {
            return;
        }

        String sql = "SELECT id, project_id, hari, tanggal, hal, pj, total_akhir "
                + "FROM reimbursement "
                + "WHERE id = ? "
                + "LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reimbursementId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    selectedReimbursementId = rs.getInt("id");

                    setSelectedProjectById(rs.getInt("project_id"));
                    setSelectedHari(nullToEmpty(rs.getString("hari")));
                    setTanggalFromDb(rs.getString("tanggal"));
                    txtPerihal.setText(nullToEmpty(rs.getString("hal")));

                    loadReimbursementItems(selectedReimbursementId);

                    btnSave.setEnabled(false);
                    btnUpdate.setEnabled(true);
                    btnDelete.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Data reimbursement tidak ditemukan.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data reimbursement: " + e.getMessage());
        }
    }

    private void loadReimbursementItems(int reimbursementId) {
        itemTableModel.setRowCount(0);

        String sql = "SELECT uraian, qty, satuan, harga_satuan, jumlah "
                + "FROM reimbursement_items "
                + "WHERE reimbursement_id = ? "
                + "ORDER BY id ASC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reimbursementId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    itemTableModel.addRow(new Object[]{
                        nullToEmpty(rs.getString("uraian")),
                        rs.getInt("qty"),
                        nullToEmpty(rs.getString("satuan")),
                        bigDecimalToString(rs.getBigDecimal("harga_satuan")),
                        bigDecimalToString(rs.getBigDecimal("jumlah"))
                    });
                }
            }

            calculateTotalAkhir();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil item reimbursement: " + e.getMessage());
        }
    }

    private void clearReimbursementForm() {
        selectedReimbursementId = null;

        if (cmbproyek.getItemCount() > 0) {
            cmbproyek.setSelectedIndex(0);
        }

        if (cmbHari.getItemCount() > 0) {
            cmbHari.setSelectedIndex(0);
        }

        jDateChooser1.setDate(new Date());
        txtPerihal.setText("");
        clearItemInput();

        if (itemTableModel != null) {
            itemTableModel.setRowCount(0);
        }

        updateTableTitle(BigDecimal.ZERO);

        btnSave.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);

        cmbproyek.requestFocus();
    }

    private boolean validateReimbursementForm() {
        Integer selectedProjectId = getSelectedProjectId();

        if (selectedProjectId == null) {
            JOptionPane.showMessageDialog(this, "Proyek / Tujuan wajib dipilih.");
            cmbproyek.requestFocus();
            return false;
        }

        if (getSelectedHari().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hari wajib dipilih.");
            cmbHari.requestFocus();
            return false;
        }

        if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Tanggal wajib dipilih.");
            jDateChooser1.requestFocus();
            return false;
        }

        if (txtPerihal.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Perihal wajib diisi.");
            txtPerihal.requestFocus();
            return false;
        }

        if (itemTableModel.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(this, "Minimal harus ada 1 item reimbursement. Isi item lalu klik tombol +.");
            txtUraian1.requestFocus();
            return false;
        }

        calculateTotalAkhir();
        return true;
    }

    private void setupHariCombo() {
        cmbHari.removeAllItems();
        cmbHari.addItem("Pilih");
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

        cmbHari.setSelectedIndex(0);
    }

    private String generateReimbursementNo() {
        String prefix = "REIM-" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "-";
        String sql = "SELECT COUNT(*) + 1 AS next_no FROM reimbursement WHERE reimbursement_no LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, prefix + "%");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return prefix + String.format("%04d", rs.getInt("next_no"));
                }
            }
        } catch (SQLException ignored) {
        }

        return prefix + System.currentTimeMillis();
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

    private void rollbackQuietly() {
        try {
            conn.rollback();
        } catch (SQLException ignored) {
        }
    }

    private void restoreAutoCommitQuietly() {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException ignored) {
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ReimbursementFormFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedTextField Nominal;
    private components.RoundedButton btnBack;
    private components.RoundedButton btnClear;
    private components.RoundedButton btnDelete;
    private components.RoundedButton btnSave;
    private components.RoundedButton btnUpdate;
    private components.RoundedComboBox cmbHari;
    private components.RoundedComboBox cmbproyek;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtambah;
    private javax.swing.JLabel lblHari;
    private javax.swing.JLabel lblPerihal;
    private javax.swing.JLabel lblProyekTujuan;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblUraian;
    private components.PageTitle pageTitle1;
    private components.SidebarMenu sidebarMenu1;
    private components.RoundedTextField txtKuantitas;
    private components.RoundedTextArea txtPerihal;
    private components.RoundedTextField txtSatuan;
    private components.RoundedTextField txtTotal;
    private components.RoundedTextField txtUraian1;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
