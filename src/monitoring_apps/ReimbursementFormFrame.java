package monitoring_apps;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class ReimbursementFormFrame extends javax.swing.JFrame {

    private final Connection conn = new KoneksiDb().connect();

    private Integer selectedReimbursementId = null;
    private String selectedProjectId = null;
    private DefaultTableModel itemTableModel;

    public ReimbursementFormFrame() {
        setContentPane(new components.ScrollablePanel());
        initComponents();
        getContentPane().setBackground(java.awt.Color.WHITE);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        wrapInScrollPane();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
        setupReimbursementForm();
    }

    public ReimbursementFormFrame(Integer reimbursementId) {
        setContentPane(new components.ScrollablePanel());
        initComponents();
        getContentPane().setBackground(java.awt.Color.WHITE);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        wrapInScrollPane();
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
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
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
        cmbSatuan = new components.AppComboBox();

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
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTanggal)
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
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(cmbSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Nominal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jbtambah, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProyekTujuan)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPerihal)
                            .addComponent(txtPerihal, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHari)
                            .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                    .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblUraian)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtUraian1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

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

    private void setupReimbursementForm() {
        txtTotal.setEditable(false);
        setupMainInputSizing();

        setupHariCombo();
        setupSatuanCombo();
        setupDateChooser();
        setupProjectChooser();
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

    private void setupMainInputSizing() {
        Dimension inputSize = new Dimension(420, 44);
        Dimension projectTextSize = new Dimension(362, 44);
        Dimension searchButtonSize = new Dimension(52, 44);

        jTextField1.setPreferredSize(projectTextSize);
        jTextField1.setMinimumSize(projectTextSize);
        jTextField1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jTextField1.setMargin(new Insets(0, 12, 0, 12));

        jButton1.setPreferredSize(searchButtonSize);
        jButton1.setMinimumSize(searchButtonSize);

        cmbHari.setPreferredSize(inputSize);
        jDateChooser1.setPreferredSize(inputSize);
        txtPerihal.setPreferredSize(new Dimension(420, 80));
        txtUraian1.setPreferredSize(new Dimension(240, 44));
        txtKuantitas.setPreferredSize(new Dimension(135, 44));
        cmbSatuan.setPreferredSize(new Dimension(135, 44));
        cmbSatuan.setMinimumSize(new Dimension(135, 44));
        Nominal.setPreferredSize(new Dimension(135, 44));
        txtTotal.setPreferredSize(new Dimension(135, 44));
        jbtambah.setPreferredSize(new Dimension(56, 44));
    }

    private void setupProjectChooser() {
        selectedProjectId = null;

        jTextField1.setEditable(false);
        jTextField1.setFocusable(false);
        jTextField1.setText("");
        jTextField1.setBackground(new Color(250, 250, 250));
        jTextField1.setForeground(new Color(20, 20, 20));
        jTextField1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 190, 190), 1),
                BorderFactory.createEmptyBorder(0, 12, 0, 12)
        ));
        jTextField1.setToolTipText("Klik tombol search untuk memilih proyek");

        jButton1.setText("");
        jButton1.setIcon(new SearchIcon(20, Color.WHITE));
        jButton1.setToolTipText("Cari dan pilih proyek / tujuan");
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton1.setFocusable(false);
        jButton1.setContentAreaFilled(true);
        jButton1.setOpaque(true);
        jButton1.setBackground(new Color(70, 74, 160));
        jButton1.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        jButton1.setMargin(new Insets(0, 0, 0, 0));
        jButton1.addActionListener(e -> showProjectChooserDialog());
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
        components.RoundedButton btnChoose = new components.RoundedButton("Pilih");
        components.RoundedButton btnCancel = new components.RoundedButton("Batal");
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

        selectedProjectId = projectId;
        jTextField1.setText(projectId + " - " + projectName);
        dialog.dispose();
    }

    private String getSelectedProjectId() {
        return selectedProjectId;
    }

    private void setSelectedProjectById(String projectId) {
        if (projectId == "") {
            selectedProjectId = null;
            jTextField1.setText("");
            return;
        }

        String sql = "SELECT id, nama FROM project WHERE id = ? LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, projectId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    selectedProjectId = rs.getString("id");
                    jTextField1.setText(selectedProjectId + " - " + nullToEmpty(rs.getString("nama")));
                    return;
                }
            }
        } catch (SQLException ignored) {
        }

        selectedProjectId = projectId;
        jTextField1.setText(projectId + " - Project ID " + projectId);
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
        String satuan = getSelectedSatuan();
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
            JOptionPane.showMessageDialog(this, "Satuan wajib dipilih.");
            cmbSatuan.requestFocus();
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
        cmbSatuan.setSelectedIndex(0);
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
                + "(id, reimbursement_id, uraian, qty, satuan, harga_satuan, jumlah) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);

            int reimbursementId;

            try (PreparedStatement ps = conn.prepareStatement(insertHeaderSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, generateReimbursementNo());
                ps.setString(2, getSelectedProjectId());
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
                + "(id, reimbursement_id, uraian, qty, satuan, harga_satuan, jumlah) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(updateHeaderSql)) {
                ps.setString(1, getSelectedProjectId());
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
                psItem.setInt(1, i + 1);
                psItem.setInt(2, reimbursementId);
                psItem.setString(3, String.valueOf(itemTableModel.getValueAt(i, 0)));
                psItem.setInt(4, parseInteger(String.valueOf(itemTableModel.getValueAt(i, 1))));
                psItem.setString(5, String.valueOf(itemTableModel.getValueAt(i, 2)));
                psItem.setBigDecimal(6, parseDecimal(String.valueOf(itemTableModel.getValueAt(i, 3))));
                psItem.setBigDecimal(7, parseDecimal(String.valueOf(itemTableModel.getValueAt(i, 4))));
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

                    setSelectedProjectById(rs.getString("project_id"));
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

        selectedProjectId = null;
        jTextField1.setText("");

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

        jButton1.requestFocus();
    }

    private boolean validateReimbursementForm() {
        String selectedProjectId = getSelectedProjectId();

        if (selectedProjectId == null) {
            JOptionPane.showMessageDialog(this, "Proyek / Tujuan wajib dipilih.");
            jButton1.requestFocus();
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

    private void setupSatuanCombo() {
        cmbSatuan.removeAllItems();
        cmbSatuan.addItem("Pilih");
        cmbSatuan.addItem("Pcs");
        cmbSatuan.addItem("Unit");
        cmbSatuan.addItem("Box");
        cmbSatuan.addItem("Lembar");
        cmbSatuan.addItem("Hari");
        cmbSatuan.setSelectedIndex(0);
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

    private String getSelectedSatuan() {
        Object item = cmbSatuan.getSelectedItem();

        if (item == null) {
            return "";
        }

        String satuan = item.toString().trim();

        if (satuan.equalsIgnoreCase("Pilih")) {
            return "";
        }

        return satuan;
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
    private components.AppComboBox cmbSatuan;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextField1;
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
    private components.RoundedTextField txtTotal;
    private components.RoundedTextField txtUraian1;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables

    private void wrapInScrollPane() {
        components.ScrollablePanel originalPane = (components.ScrollablePanel) getContentPane();
        originalPane.setBackground(java.awt.Color.WHITE);
        originalPane.setBackground(java.awt.Color.WHITE);
        setContentPane(new javax.swing.JPanel());
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(originalPane);
        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setBackground(java.awt.Color.WHITE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(java.awt.Color.WHITE);
        scrollPane.setBackground(java.awt.Color.WHITE);
        setContentPane(scrollPane);
    }

}