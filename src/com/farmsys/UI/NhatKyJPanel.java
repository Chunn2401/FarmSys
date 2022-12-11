/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.UI;

import com.farmsys.Entity.NhanVien;
import com.farmsys.Entity.NhatKy;
import com.farmsys.Helper.MsgBox;
import com.farmsys.Helper.XImage;
import com.farmsys.dao.CongViecDAO;
import com.farmsys.dao.NhanVienDAO;
import com.farmsys.dao.NhatKyDAO;
import java.awt.Image;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author admin
 */
public class NhatKyJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NhatKyJPanel
     */
    public NhatKyJPanel() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        TheNVJDialog = new javax.swing.JDialog();
        pnlTong = new javax.swing.JPanel();
        imageAvatar = new com.farmsys.Helper.ImageAvatar();
        lblTenNV = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtVaiTro = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtTenNV = new javax.swing.JTextField();
        buttonGroup3 = new javax.swing.ButtonGroup();
        pnlNhatKy = new javax.swing.JPanel();
        txttimkiem = new javax.swing.JTextField();
        lbltimkiem = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhatKy = new javax.swing.JTable();
        btnxuapdf = new javax.swing.JButton();
        Datengaybatdau = new com.toedter.calendar.JDateChooser();
        Datengayketthuc = new com.toedter.calendar.JDateChooser();
        lbltungay = new javax.swing.JLabel();
        lblden = new javax.swing.JLabel();
        btnloc = new javax.swing.JButton();
        btnxuatexcel = new javax.swing.JButton();
        lblloadagain = new javax.swing.JLabel();

        TheNVJDialog.setTitle("Thẻ nhân viên");
        TheNVJDialog.setMinimumSize(new java.awt.Dimension(472, 243));
        TheNVJDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTong.setBackground(new java.awt.Color(153, 255, 153));
        pnlTong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTongMouseClicked(evt);
            }
        });
        pnlTong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageAvatar.setBackground(new java.awt.Color(255, 255, 255));
        imageAvatar.setForeground(new java.awt.Color(255, 255, 255));
        pnlTong.add(imageAvatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 124, 124));

        lblTenNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTenNV.setForeground(new java.awt.Color(255, 255, 255));
        lblTenNV.setText("Tên :");
        pnlTong.add(lblTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 52, 27));

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("Email :");
        pnlTong.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 52, 50));

        lblVaiTro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblVaiTro.setForeground(new java.awt.Color(255, 255, 255));
        lblVaiTro.setText("Vai Trò :");
        pnlTong.add(lblVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 52, 40));

        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(255, 255, 255));
        lblGioiTinh.setText("Giới Tính : ");
        pnlTong.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 68, 20));

        txtEmail.setEditable(false);
        txtEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlTong.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 76, 243, -1));

        txtVaiTro.setEditable(false);
        txtVaiTro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlTong.add(txtVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 121, 243, -1));

        rdoNam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoNam.setText("Nam");
        rdoNam.setEnabled(false);
        pnlTong.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 60, -1));

        rdoNu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoNu.setText("Nữ");
        rdoNu.setEnabled(false);
        pnlTong.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 60, -1));

        txtTenNV.setEditable(false);
        txtTenNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlTong.add(txtTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 32, 243, -1));

        TheNVJDialog.getContentPane().add(pnlTong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 220));

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1083, 750));
        setPreferredSize(new java.awt.Dimension(1083, 750));

        pnlNhatKy.setBackground(new java.awt.Color(255, 255, 255));
        pnlNhatKy.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NHẬT KÝ CÔNG VIỆC", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        pnlNhatKy.setMinimumSize(new java.awt.Dimension(1083, 749));
        pnlNhatKy.setName(""); // NOI18N
        pnlNhatKy.setPreferredSize(new java.awt.Dimension(1083, 749));
        pnlNhatKy.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttimkiemKeyPressed(evt);
            }
        });
        pnlNhatKy.add(txttimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 970, 30));

        lbltimkiem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbltimkiem.setText("Tìm kiếm:");
        pnlNhatKy.add(lbltimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 60, 30));

        tblNhatKy.setAutoCreateRowSorter(true);
        tblNhatKy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên công việc", "Tên Cây", "Tên Giàn", "Chi tiết công việc", "Người tạo", "Nhân viên", "Bắt đầu", "Kết thúc", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhatKy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblNhatKy.setMinimumSize(new java.awt.Dimension(750, 480));
        tblNhatKy.setRowHeight(40);
        tblNhatKy.setShowGrid(false);
        tblNhatKy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhatKyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhatKy);

        pnlNhatKy.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 1040, 510));

        btnxuapdf.setBackground(new java.awt.Color(255, 255, 255));
        btnxuapdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/pdf_30px.png"))); // NOI18N
        btnxuapdf.setText("Xuất PDF");
        btnxuapdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxuapdfActionPerformed(evt);
            }
        });
        pnlNhatKy.add(btnxuapdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 670, 140, -1));

        Datengaybatdau.setBackground(new java.awt.Color(255, 255, 255));
        pnlNhatKy.add(Datengaybatdau, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 170, -1));

        Datengayketthuc.setBackground(new java.awt.Color(255, 255, 255));
        Datengayketthuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DatengayketthucMouseClicked(evt);
            }
        });
        pnlNhatKy.add(Datengayketthuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 170, -1));

        lbltungay.setText("Từ ngày");
        pnlNhatKy.add(lbltungay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 20));

        lblden.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblden.setText("Đến");
        pnlNhatKy.add(lblden, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 50, 20));

        btnloc.setText("Lọc");
        btnloc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlocActionPerformed(evt);
            }
        });
        pnlNhatKy.add(btnloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, -1, -1));

        btnxuatexcel.setBackground(new java.awt.Color(255, 255, 255));
        btnxuatexcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/Microsoft Excel 2019_30px.png"))); // NOI18N
        btnxuatexcel.setText("Xuất Excel");
        btnxuatexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxuatexcelActionPerformed(evt);
            }
        });
        pnlNhatKy.add(btnxuatexcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 670, 140, -1));

        lblloadagain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/icons8_synchronize_25px.png"))); // NOI18N
        lblloadagain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblloadagainMouseClicked(evt);
            }
        });
        pnlNhatKy.add(lblloadagain, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 120, 30, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNhatKy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlNhatKy, javax.swing.GroupLayout.PREFERRED_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txttimkiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyPressed

        this.fillTableNhatKyByTenNV();


    }//GEN-LAST:event_txttimkiemKeyPressed

    private void tblNhatKyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhatKyMouseClicked
        if (evt.getClickCount() == 2) {
            TheNVJDialog.setVisible(true);
            this.row = tblNhatKy.getSelectedRow();
            this.edit();
            this.editimage();
        }
    }//GEN-LAST:event_tblNhatKyMouseClicked

    private void btnxuapdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxuapdfActionPerformed
        this.print();
    }//GEN-LAST:event_btnxuapdfActionPerformed

    private void DatengayketthucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatengayketthucMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DatengayketthucMouseClicked

    private void btnlocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlocActionPerformed
        this.loadToTableDate();
    }//GEN-LAST:event_btnlocActionPerformed

    private void btnxuatexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxuatexcelActionPerformed
        xuatExcel();
        Runtime run = Runtime.getRuntime();
        String url = "src\\Excel\\Nhatky.xlsx";
        try {
            run.exec("rundll32 url.dll, FileProtocolHandler " + url);
        } catch (IOException ex) {
            Logger.getLogger(NhatKyJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnxuatexcelActionPerformed

    private void lblloadagainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblloadagainMouseClicked
        this.fillTableNhatALL();
        txttimkiem.setText("");
        Datengaybatdau.setCalendar(null);
        Datengayketthuc.setCalendar(null);
    }//GEN-LAST:event_lblloadagainMouseClicked

    private void pnlTongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTongMouseClicked
        TheNVJDialog.setVisible(false);
    }//GEN-LAST:event_pnlTongMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Datengaybatdau;
    private com.toedter.calendar.JDateChooser Datengayketthuc;
    private javax.swing.JDialog TheNVJDialog;
    private javax.swing.JButton btnloc;
    private javax.swing.JButton btnxuapdf;
    private javax.swing.JButton btnxuatexcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private com.farmsys.Helper.ImageAvatar imageAvatar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JLabel lblden;
    private javax.swing.JLabel lblloadagain;
    private javax.swing.JLabel lbltimkiem;
    private javax.swing.JLabel lbltungay;
    private javax.swing.JPanel pnlNhatKy;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNhatKy;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtVaiTro;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
    int row = -1;
    NhatKyDAO nkdao = new NhatKyDAO();
    CongViecDAO cvdao = new CongViecDAO();
    NhanVienDAO nvdao = new NhanVienDAO();
    ArrayList<NhatKy> list = new ArrayList<>();

    private void init() {
        this.fillTableNhatALL();
        this.TheNVJDialog.setLocationRelativeTo(null);
    }

    private void fillTableNhatALL() {
        DefaultTableModel model = (DefaultTableModel) tblNhatKy.getModel();
        model.setRowCount(0);
        list = (ArrayList<NhatKy>) nkdao.selectAllMonth();
        for (NhatKy nk : list) {
            model.addRow(new Object[]{
                nk.getTenCV(),
                nk.getTenCay(),
                nk.getTenGian(),
                nk.getChiTiet(),
                nk.getNguoiTao(),
                nk.getNhanVien(),
                nk.getNgayBatDau(),
                nk.getNgayKetThuc(),
                nk.toString()
            });
        }
    }

    private void fillTableNhatKyByTenNV() {
        DefaultTableModel model = (DefaultTableModel) tblNhatKy.getModel();
        model.setRowCount(0);
        String TimKiem = txttimkiem.getText();
        list = (ArrayList<NhatKy>) nkdao.selectByTenNV(TimKiem);
        for (NhatKy nk : list) {

            model.addRow(new Object[]{
                nk.getTenCV(),
                nk.getTenCay(),
                nk.getTenGian(),
                nk.getChiTiet(),
                nk.getNguoiTao(),
                nk.getNhanVien(),
                nk.getNgayBatDau(),
                nk.getNgayKetThuc(),
                nk.toString()
            });
        }
    }

    void print() {
        try {
            Boolean print = tblNhatKy.print();
            if (print) {
                MsgBox.alert(this, "Đã xuất file PDF");
            } else {

            }
        } catch (PrinterException ex) {
            Logger.getLogger(NhatKyJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    JFileChooser fileChooser = new JFileChooser();

    private void edit() {
        String manv = (String) tblNhatKy.getValueAt(this.row, 5);
        NhanVien nv = nvdao.selectById(manv);
        this.setForm(nv);

    }

    void editimage() {
        String name = txtTenNV.getText();
        NhanVien nv = nvdao.selectHinh(name);
        this.setAvatar(nv);
    }

    private void setForm(NhanVien nv) {
        txtTenNV.setText(nv.getHoTen());
        txtEmail.setText(nv.getEmail());
        if (nv.isVaiTro() == true) {
            txtVaiTro.setText("Trưởng phòng");
        } else {
            txtVaiTro.setText("Nhân viên");
        }
        if (nv.isGioiTinh() == true) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
    }

    void setAvatar(NhanVien nv) {
        if (nv.getHinh() != null) {
            imageAvatar.setToolTipText(nv.getHinh());
            ImageIcon icon = XImage.read(nv.getHinh()); // Lấy địa chỉ của file Icon
            //Chuyển Icon sang image và điều chỉnh kích thước
            Image scaleIcon = icon.getImage().getScaledInstance(imageAvatar.getWidth(), imageAvatar.getHeight(), Image.SCALE_DEFAULT);
            imageAvatar.setImage(new javax.swing.ImageIcon(scaleIcon));
        }
    }

    private void loadToTableDate() {
        DefaultTableModel model = (DefaultTableModel) tblNhatKy.getModel();
        model.setRowCount(0);
        Date tungay = Datengaybatdau.getDate();
        Date denngay = Datengayketthuc.getDate();
        list = (ArrayList<NhatKy>) nkdao.selectByTime(tungay, denngay);
        for (NhatKy nk : list) {
            model.addRow(new Object[]{
                nk.getTenCV(),
                nk.getTenCay(),
                nk.getTenGian(),
                nk.getChiTiet(),
                nk.getNguoiTao(),
                nk.getNhanVien(),
                nk.getNgayBatDau(),
                nk.getNgayKetThuc(),
                nk.toString()
            });
        }
    }

    private void xuatExcel() {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Nhatky");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Tên công việc");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Tên cây");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên giàn");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Chi tiết công việc");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Người tạo");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Nhân viên");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Bắt đầu");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Kết thúc");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Trang thái");

            for (int i = 0; i < list.size(); i++) {
                // Modelbook book = arr.get(i);
                row = sheet.createRow(4 + i);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(list.get(i).getTenCV());

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(list.get(i).getTenCay());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(list.get(i).getTenGian());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(list.get(i).getChiTiet());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(list.get(i).getNguoiTao());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(list.get(i).getNhanVien());

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(list.get(i).getNgayBatDau() + "");

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(list.get(i).getNgayKetThuc() + "");

                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(list.get(i).toString());
            }
            File file = new File("src\\Excel\\Nhatky.xlsx");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                MsgBox.alert(this, "loimofile");
            }
            MsgBox.alert(this, "Đã xuất ra file Excel");

        } catch (Exception e) {
            MsgBox.alert(this, "loimofile");
        }

    }
}