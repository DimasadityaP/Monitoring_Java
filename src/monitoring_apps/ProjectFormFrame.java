package monitoring_apps;

import components.AppTextFieldEditor;
import components.RoundedTablePanel;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Year;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicReference;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.String.*;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.KoneksiDb;
import models.ProjectData;

public class ProjectFormFrame extends javax.swing.JFrame {

    private Connection conn;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    public ProjectFormFrame() {
        conn = new KoneksiDb().connect();
        initComponents();
        initUi();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
    }
    
    public ProjectFormFrame(String projectId) {
        conn = new KoneksiDb().connect();
        initComponents();
        initUi();
        setLocationRelativeTo(null);
        Navigation.bind(sidebarMenu1, this);
        loadProjectData(projectId);
    }
    
    private void loadProjectData(String projectId) {
    try {
        // Load data project
        String query = "SELECT * FROM project WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, projectId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            txtId.setText(rs.getString("id"));
            txtNamaPekerjaan.setText(rs.getString("nama"));
            txtTahunAnggaran.setText(rs.getString("ta"));
            txtPerusahaan.setText(rs.getString("sub_perusahaan"));
            txtInstansi.setText(rs.getString("instansi"));
            txtTglMulai.setText(rs.getString("tgl_mulai"));
            txtTglSelesai.setText(rs.getString("tgl_selesai"));
            txtNilaiPekerjaan.setText(rs.getBigDecimal("nominal").toPlainString());
            txtStatus.setText(rs.getString("status"));

            // Set combobox jenis
            String jenis = rs.getString("jenis");
            cmbJenisPekerjaan.setSelectedItem(jenis);
        }

        // Load items (PIC)
        String queryItem = "SELECT * FROM project_item WHERE project_id = ? ORDER BY item_id";
        PreparedStatement psItem = conn.prepareStatement(queryItem);
        psItem.setString(1, projectId);
        ResultSet rsItem = psItem.executeQuery();

        Object[] columns = {"No", "PIC"};
        tblItem.setTableData(new Object[0][2], columns);
        tblItem.setEditableColumns(1);
        tblItem.setColumnEditor(1, new components.AppTextFieldEditor());

        while (rsItem.next()) {
            DefaultTableModel model = (DefaultTableModel) tblItem.getModel();
            model.addRow(new Object[]{
                rsItem.getInt("item_id"),
                rsItem.getString("nama_pic"),
                null
            });
        }

    } catch (Exception e) {
        showError("Gagal load data: " + e.getMessage());
    }
}
    
    
    private void initUi() {
        String [] jenisProject = {"Pilih Jenis Projek", "Konstruksi", "Pengadaan"};
        cmbJenisPekerjaan.setModel(new javax.swing.DefaultComboBoxModel<>(jenisProject));
        txtTahunAnggaran.setText(Year.now().toString());
        txtTahunAnggaran.setEnabled(false);
        txtStatus.setText("New");
        
        tblItem.setDeleteOnlyColumn("/image/delete.png", new components.RoundedTablePanel.ActionClickListener() {
            public void onActionClick(int row) {
                int confirm = JOptionPane.showConfirmDialog(
                    ProjectFormFrame.this,
                    "Hapus item ini?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    DefaultTableModel m = (DefaultTableModel) tblItem.getModel();
                    m.removeRow(row);
                }
            }
        });
        tblItem.setEditableColumns(1);
        
        txtTglMulai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String d = showDatePicker();
                    if (d != null) txtTglMulai.setText(d);
                } catch (Exception ex) {
                }
            }
        });
        txtTglSelesai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String d = showDatePicker();
                    if (d != null) txtTglSelesai.setText(d);
                } catch (Exception ex) {
                }
            }
        });
    }
    
    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new ProjectFormFrame().setVisible(true);
        }
    });
}

    private void p_ResetField(){
        cmbJenisPekerjaan.setSelectedIndex(0);
        txtNamaPekerjaan.setText("");
        txtInstansi.setText("");
        txtPerusahaan.setText("");
        txtTglMulai.setText("");
        txtTglSelesai.setText("");
        txtNilaiPekerjaan.setText("");
        txtStatus.setText("New");
        tblItem.clearTable();
        tblItem.setEditableColumns(1);
    }
    
    private void p_ValidateData()
    {
        if (cmbJenisPekerjaan.getSelectedIndex() == 0) {
            throw new IllegalArgumentException("Jenis Project harus dipilih.");
        }

        if (txtNamaPekerjaan.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Project tidak boleh kosong.");
        }

        if (txtInstansi.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Instansi tidak boleh kosong.");
        }

        if (txtPerusahaan.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Perusahaan tidak boleh kosong.");
        }

        if (txtTglMulai.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Tanggal mulai tidak boleh kosong.");
        }

        if (txtTglSelesai.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Tanggal selesai tidak boleh kosong.");
        }

        String nominalText = txtNilaiPekerjaan.getText().trim();
        if (nominalText.isEmpty()) {
            throw new IllegalArgumentException("Nilai pekerjaan tidak boleh kosong.");
        }

        try {
            new BigDecimal(nominalText.replaceAll("[^0-9.]", ""));
        } catch (Exception e) {
            throw new IllegalArgumentException("Nilai pekerjaan harus berupa angka.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Gagal", JOptionPane.WARNING_MESSAGE) ;
    }
    
    private ProjectData p_UiToData(){
        ProjectData dat = new ProjectData();
        dat.id = txtId.getText().trim();
        dat.nama = txtNamaPekerjaan.getText().trim();
        dat.ta = txtTahunAnggaran.getText().trim();
        dat.subPerusahaan = txtPerusahaan.getText().trim();
        dat.jenis = cmbJenisPekerjaan.getSelectedItem().toString();
        dat.instansi = txtInstansi.getText().trim();
        dat.tglMulai = txtTglMulai.getText().trim();
        dat.tglSelesai = txtTglSelesai.getText().trim();
        dat.nominal = new BigDecimal(txtNilaiPekerjaan.getText().trim().replaceAll("[^0-9.]", ""));
        dat.status = txtStatus.getText().trim();

        if (tblItem.isEditing()) {
            tblItem.getTable().getCellEditor().stopCellEditing();
        }
        DefaultTableModel model = (DefaultTableModel) tblItem.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            Object picValue = model.getValueAt(i, 1);
            if (picValue != null && !picValue.toString().trim().isEmpty()) {
                ProjectData.ProjectItem item = new ProjectData.ProjectItem();
                item.projectId = dat.id;
                item.itemId = i + 1;
                item.namaPic = picValue.toString().trim();
                dat.addItem(item);
            }
        }

        return dat;
    }

    private boolean isExists(String id) {
        try {
            String query = "SELECT COUNT(*) FROM project WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return false;
    }

    private String getNextId() {
        String prefix = "PROJ";
        String yymm = Year.now().toString().substring(2);
        String nextId = String.format("%s/%s/%04d", prefix, yymm, 1);

        try {
            String query = "SELECT id FROM project WHERE id LIKE ? ORDER BY id DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, prefix + "/" + yymm + "/%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String lastId = rs.getString("id");
                String[] parts = lastId.split("/");
                if (parts.length == 3) {
                    int number = Integer.parseInt(parts[2]);
                    nextId = String.format("%s/%s/%04d", prefix, yymm, number + 1);
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return nextId;
    }

    private void createProject(ProjectData dat) {
        if (isExists(dat.id)) {
            throw new IllegalArgumentException("ID project sudah ada: " + dat.id);
        }

        String query = "INSERT INTO project (id, nama, ta, sub_perusahaan, jenis, instansi, tgl_mulai, tgl_selesai, nominal, status, user_id_created, approver_id, created_at, modify_at) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NULL)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, dat.id);
            ps.setString(2, dat.nama);
            ps.setString(3, dat.ta);
            ps.setString(4, dat.subPerusahaan);
            ps.setString(5, dat.jenis);
            ps.setString(6, dat.instansi);
            ps.setString(7, dat.tglMulai);
            ps.setString(8, dat.tglSelesai);
            ps.setBigDecimal(9, dat.nominal);
            ps.setString(10, dat.status);
            ps.setString(11, dat.userIdCreated);
            ps.setString(12, dat.approverId);
            ps.executeUpdate();

            String queryItem = "INSERT INTO project_item (project_id, item_id, nama_pic) VALUES (?, ?, ?)";
            PreparedStatement psItem = conn.prepareStatement(queryItem);
            for (ProjectData.ProjectItem item : dat.items) {
                psItem.setString(1, item.projectId);
                psItem.setInt(2, item.itemId);
                psItem.setString(3, item.namaPic);
                psItem.addBatch();
            }
            psItem.executeBatch();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void updateProject(ProjectData dat) {
        String query = "UPDATE project SET nama = ?, ta = ?, sub_perusahaan = ?, jenis = ?, instansi = ?, tgl_mulai = ?, tgl_selesai = ?, nominal = ?, status = ?, user_id_created = ?, approver_id = ?, modify_at = NOW() WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, dat.nama);
            ps.setString(2, dat.ta);
            ps.setString(3, dat.subPerusahaan);
            ps.setString(4, dat.jenis);
            ps.setString(5, dat.instansi);
            ps.setString(6, dat.tglMulai);
            ps.setString(7, dat.tglSelesai);
            ps.setBigDecimal(8, dat.nominal);
            ps.setString(9, dat.status);
            ps.setString(10, dat.userIdCreated);
            ps.setString(11, dat.approverId);
            ps.setString(12, dat.id);
            ps.executeUpdate();

            // refresh project items: delete old ones and insert new
            String del = "DELETE FROM project_item WHERE project_id = ?";
            PreparedStatement psDel = conn.prepareStatement(del);
            psDel.setString(1, dat.id);
            psDel.executeUpdate();

            String queryItem = "INSERT INTO project_item (project_id, item_id, nama_pic) VALUES (?, ?, ?)";
            PreparedStatement psItem = conn.prepareStatement(queryItem);
            for (ProjectData.ProjectItem item : dat.items) {
                psItem.setString(1, item.projectId);
                psItem.setInt(2, item.itemId);
                psItem.setString(3, item.namaPic);
                psItem.addBatch();
            }
            psItem.executeBatch();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfileCard1 = new components.UserProfileCard();
        sidebarMenu1 = new components.SidebarMenu();
        pageTitle1 = new components.PageTitle();
        lblJenisPekerjaan = new javax.swing.JLabel();
        cmbJenisPekerjaan = new components.RoundedComboBox();
        lblTahunAnggaran = new javax.swing.JLabel();
        lblNamaPekerjaan = new javax.swing.JLabel();
        txtNamaPekerjaan = new components.RoundedTextArea();
        lblPerusahaanInfo = new javax.swing.JLabel();
        txtInstansi = new components.RoundedTextArea();
        lblPerusahaan = new javax.swing.JLabel();
        txtPerusahaan = new components.RoundedTextField();
        lblStatus = new javax.swing.JLabel();
        lblTglMulai = new javax.swing.JLabel();
        txtTglMulai = new components.RoundedTextField();
        lblTglSelesai = new javax.swing.JLabel();
        txtTglSelesai = new components.RoundedTextField();
        lblNilaiPekerjaan = new javax.swing.JLabel();
        txtNilaiPekerjaan = new components.RoundedTextField();
        btnDraft = new components.RoundedButton();
        btnSubmit = new components.RoundedButton();
        btnClear = new components.RoundedButton();
        btnDelete = new components.RoundedButton();
        btnBack = new components.RoundedButton();
        txtStatus = new components.RoundedTextField();
        txtTahunAnggaran = new components.RoundedTextField();
        tblItem = new components.RoundedTablePanel();
        btnAddItem = new components.RoundedButton();
        lblPerusahaan1 = new javax.swing.JLabel();
        txtId = new components.RoundedTextField();
        btnNew = new components.RoundedButton();
        dtCStart = new com.toedter.calendar.JDateChooser();
        dtCEnd = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORMULIR PROJECT");
        setMinimumSize(new java.awt.Dimension(1200, 800));

        pageTitle1.setText("FORMULIR PROJECT");

        lblJenisPekerjaan.setText("Jenis Project");

        lblTahunAnggaran.setText("Tahun Anggaran");

        lblNamaPekerjaan.setText("Nama Project");

        lblPerusahaanInfo.setText("Instansi");

        lblPerusahaan.setText("Perusahaan");

        lblStatus.setText("Status");

        lblTglMulai.setText("Tgl Mulai");

        txtTglMulai.setEnabled(false);

        lblTglSelesai.setText("Tgl Selesai");

        txtTglSelesai.setEnabled(false);

        lblNilaiPekerjaan.setText("Nilai Pekerjaan");

        btnDraft.setText("Draft");
        btnDraft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDraftActionPerformed(evt);
            }
        });

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");

        btnDelete.setText("Delete");
        btnDelete.setButtonColor(new java.awt.Color(154, 61, 120));

        btnBack.setForeground(new java.awt.Color(0, 0, 0));
        btnBack.setText("Back");
        btnBack.setButtonColor(new java.awt.Color(217, 217, 217));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnAddItem.setText("Add Item +");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });

        lblPerusahaan1.setText("Id Project");

        txtId.setEnabled(false);

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        dtCStart.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtCStartPropertyChange(evt);
            }
        });

        dtCEnd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtCEndPropertyChange(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(pageTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(314, 314, 314)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDraft, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNamaPekerjaan)
                                    .addComponent(txtNamaPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tblItem, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblPerusahaan)
                                                .addComponent(txtPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(lblNilaiPekerjaan)
                                                    .addGap(315, 315, 315))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(lblTglMulai)
                                                    .addGap(355, 355, 355))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(txtTglMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(12, 12, 12)
                                                    .addComponent(dtCStart, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtNilaiPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblPerusahaan1)
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblTahunAnggaran)
                                                    .addComponent(txtTahunAnggaran, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblPerusahaanInfo)
                                                    .addComponent(txtInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblJenisPekerjaan)
                                                    .addComponent(cmbJenisPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblStatus)
                                                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lblTglSelesai)
                                                            .addComponent(txtTglSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(dtCEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                        .addGap(20, 20, 20)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sidebarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(423, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPerusahaan1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNamaPekerjaan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNamaPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPerusahaan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblJenisPekerjaan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbJenisPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPerusahaanInfo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTahunAnggaran)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTahunAnggaran, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTglMulai)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTglMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dtCStart, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTglSelesai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTglSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtCEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNilaiPekerjaan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNilaiPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(tblItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDraft, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        if (tblItem.getModel().getRowCount() == 0) {
            Object[] columns = {"No", "PIC"};
            tblItem.setTableData(new Object[0][2], columns);
            tblItem.setEditableColumns(1);  // Hanya PIC yang bisa di-edit
            tblItem.setColumnEditor(1, new AppTextFieldEditor());
        }
        tblItem.addRow();
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void btnDraftActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            txtStatus.setText("Draft");
            p_SaveData();
            JOptionPane.showMessageDialog(this, "Project disimpan sebagai Draft.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            navigateBackToProjectList();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {                                          
        try {
            txtStatus.setText("On Progress");
            p_SaveData();
            JOptionPane.showMessageDialog(this, "Project berhasil disubmit.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            navigateBackToProjectList();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void navigateBackToProjectList() {
        JFrame next = new ProjectList();
        next.pack();
        next.setLocationRelativeTo(this);
        next.setVisible(true);
        dispose();
    }

    private void p_SaveData(){
        p_ValidateData();
            ProjectData dat = p_UiToData();
            if (isExists(dat.id)) {
                updateProject(dat);
            } else {
                createProject(dat);
            }
    }

    private String showDatePicker() {
        final AtomicReference<String> result = new AtomicReference<>(null);
        final JDialog dialog = new JDialog(this, "Pilih Tanggal", true);
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        JSpinner spinner = new JSpinner(model);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy-MM-dd"));

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Batal");

        ok.addActionListener(ae -> {
            Date date = (Date) spinner.getValue();
            LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            result.set(ld.format(DateTimeFormatter.ISO_LOCAL_DATE));
            dialog.dispose();
        });
        cancel.addActionListener(ae -> dialog.dispose());

        JPanel p = new JPanel();
        p.add(spinner);
        p.add(ok);
        p.add(cancel);

        dialog.getContentPane().add(p);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        return result.get();
    }

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        p_ResetField();
        txtId.setText(getNextId());

    }//GEN-LAST:event_btnNewActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        navigateBackToProjectList();
    }//GEN-LAST:event_btnBackActionPerformed

    private void dtCStartPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtCStartPropertyChange
        // TODO add your handling code here:
        if (dtCStart.getDate() != null){
           String tanggalperiksa = format.format(dtCStart.getDate());
           txtTglMulai.setText(tanggalperiksa);
        }
    }//GEN-LAST:event_dtCStartPropertyChange

    private void dtCEndPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtCEndPropertyChange
        // TODO add your handling code here:
        if (dtCEnd.getDate() != null){
           String tanggalperiksa = format.format(dtCEnd.getDate());
           txtTglSelesai.setText(tanggalperiksa);
        }
    }//GEN-LAST:event_dtCEndPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.RoundedButton btnAddItem;
    private components.RoundedButton btnBack;
    private components.RoundedButton btnClear;
    private components.RoundedButton btnDelete;
    private components.RoundedButton btnDraft;
    private components.RoundedButton btnNew;
    private components.RoundedButton btnSubmit;
    private components.RoundedComboBox cmbJenisPekerjaan;
    private com.toedter.calendar.JDateChooser dtCEnd;
    private com.toedter.calendar.JDateChooser dtCStart;
    private javax.swing.JLabel lblJenisPekerjaan;
    private javax.swing.JLabel lblNamaPekerjaan;
    private javax.swing.JLabel lblNilaiPekerjaan;
    private javax.swing.JLabel lblPerusahaan;
    private javax.swing.JLabel lblPerusahaan1;
    private javax.swing.JLabel lblPerusahaanInfo;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTahunAnggaran;
    private javax.swing.JLabel lblTglMulai;
    private javax.swing.JLabel lblTglSelesai;
    private components.PageTitle pageTitle1;
    private components.SidebarMenu sidebarMenu1;
    private components.RoundedTablePanel tblItem;
    private components.RoundedTextField txtId;
    private components.RoundedTextArea txtInstansi;
    private components.RoundedTextArea txtNamaPekerjaan;
    private components.RoundedTextField txtNilaiPekerjaan;
    private components.RoundedTextField txtPerusahaan;
    private components.RoundedTextField txtStatus;
    private components.RoundedTextField txtTahunAnggaran;
    private components.RoundedTextField txtTglMulai;
    private components.RoundedTextField txtTglSelesai;
    private components.UserProfileCard userProfileCard1;
    // End of variables declaration//GEN-END:variables
}
