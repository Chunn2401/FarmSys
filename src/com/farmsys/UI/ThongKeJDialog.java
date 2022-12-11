/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.UI;

import com.farmsys.Entity.KhoHang;
import com.farmsys.Entity.NhanVien;
import com.farmsys.Helper.Auth;
import com.farmsys.Helper.MsgBox;
import com.farmsys.dao.KhoHangDAO;
import com.farmsys.dao.NhanVienDAO;
import com.farmsys.dao.NhatKyDAO;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author NguyenTrung
 */
public class ThongKeJDialog extends javax.swing.JDialog {

    /**
     * Creates new form ThongKeJDialog
     * @param parent
     * @param modal
     */
    public ThongKeJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();

    }

    void init() {
        setLocationRelativeTo(null);
        this.fillTableKhoHangAll();
        this.fillTableLuongNhanVien();
        if (!Auth.isManager()) {
            tabs.setEnabledAt(1, false);
        }
        Date now = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("MM/yyyy");
        String text = formater.format(now);
        lblDATE.setText(text);

    }

    KhoHangDAO khDAO = new KhoHangDAO();
    NhanVienDAO nvDAO = new NhanVienDAO();
    NhatKyDAO nkDAO = new NhatKyDAO();
    ArrayList<KhoHang> list = new ArrayList<>();
    ArrayList<NhanVien> listnv = new ArrayList<>();

    private void fillTableKhoHangAll() {
        DefaultTableModel model = (DefaultTableModel) tblKhoHang.getModel();
        model.setRowCount(0);
        KhoHang khoHang = new KhoHang();
        list = (ArrayList<KhoHang>) khDAO.selectAll();
        for (KhoHang kh : list) {
            model.addRow(new Object[]{
                kh.getTenGian(),
                kh.getTenCay(),
                kh.getTrongLuong(),
                kh.getNgayTH(),
                kh.getGiaThanh()
            });
        }
    }

    private void fillTableLuongNhanVien() {
        DefaultTableModel model = (DefaultTableModel) tblluongnhanvien.getModel();
        model.setRowCount(0);
        listnv = (ArrayList<NhanVien>) nvDAO.selectAll();
        for (NhanVien nv : listnv) {

            model.addRow(new Object[]{
                nv.getHoTen(),
                nv.getLuong(),
                nv.getBonusMonth(),
                nv.getTongluong()
            });
        }
    }

    private void xuatExcelThongke() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Thongke");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Tên giàn");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Tên cây");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Trọng lượng");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Ngày thu hoạch");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Thành tiền");

            for (int i = 0; i < list.size(); i++) {
                // Modelbook book = arr.get(i);
                row = sheet.createRow(4 + i);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(list.get(i).getTenGian());

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(list.get(i).getTenCay());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(list.get(i).getTrongLuong());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(list.get(i).getNgayTH() + "");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(list.get(i).getGiaThanh());
            }
            File file = new File("src\\Excel\\ThongKeDT.xlsx");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                MsgBox.alert(this, "loimofile");
            }
            MsgBox.alert(this, "Đã xuất ra file Excel");

        } catch (IOException e) {
            MsgBox.alert(this, "loimofile");
        }
    }

    private void xuatExcelLuongNV() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("LuongNV");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Tên nhân viên");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Lương cố định");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Thưởng");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Tổng lương");

            for (int i = 0; i < listnv.size(); i++) {
                row = sheet.createRow(4 + i);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(listnv.get(i).getHoTen());

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(listnv.get(i).getLuong());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(listnv.get(i).getBonusMonth());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(listnv.get(i).getTongluong() + "");
            }
            File file = new File("src\\Excel\\LuongNV.xlsx");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                MsgBox.alert(this, "loimofile");
            }
            MsgBox.alert(this, "Đã xuất ra file Excel");

        } catch (IOException e) {
            MsgBox.alert(this, "loimofile");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        pnlKhohang = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhoHang = new javax.swing.JTable();
        btnXuatPDF = new javax.swing.JButton();
        btnXuatExcel = new javax.swing.JButton();
        pnlLuongNV = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblluongnhanvien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblDATE = new javax.swing.JLabel();
        btnXuatPDF2 = new javax.swing.JButton();
        btnXuatExcel2 = new javax.swing.JButton();
        pnlBackground = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thống kê");
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabs.setBackground(new java.awt.Color(255, 255, 255));

        pnlKhohang.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblKhoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên giàn", "Tên cây", "Trọng lượng", "Ngày thu hoạch", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblKhoHang);

        btnXuatPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/pdf_30px.png"))); // NOI18N
        btnXuatPDF.setText("Xuất PDF");
        btnXuatPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatPDFActionPerformed(evt);
            }
        });

        btnXuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/Microsoft Excel 2019_30px.png"))); // NOI18N
        btnXuatExcel.setText("Xuất Excel");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnXuatPDF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuatExcel)
                        .addGap(0, 626, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnXuatExcel, btnXuatPDF});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatPDF)
                    .addComponent(btnXuatExcel))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnXuatExcel, btnXuatPDF});

        javax.swing.GroupLayout pnlKhohangLayout = new javax.swing.GroupLayout(pnlKhohang);
        pnlKhohang.setLayout(pnlKhohangLayout);
        pnlKhohangLayout.setHorizontalGroup(
            pnlKhohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlKhohangLayout.setVerticalGroup(
            pnlKhohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabs.addTab("Kho hàng", pnlKhohang);

        pnlLuongNV.setBackground(new java.awt.Color(255, 255, 255));

        tblluongnhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên nhân viên", "Lương cố định", "Thưởng", "Tổng lương"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblluongnhanvien);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Bảng lương tháng:");

        lblDATE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnXuatPDF2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/pdf_30px.png"))); // NOI18N
        btnXuatPDF2.setText("Xuất PDF");
        btnXuatPDF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatPDF2ActionPerformed(evt);
            }
        });

        btnXuatExcel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/Microsoft Excel 2019_30px.png"))); // NOI18N
        btnXuatExcel2.setText("Xuất Excel");
        btnXuatExcel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLuongNVLayout = new javax.swing.GroupLayout(pnlLuongNV);
        pnlLuongNV.setLayout(pnlLuongNVLayout);
        pnlLuongNVLayout.setHorizontalGroup(
            pnlLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLuongNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                    .addGroup(pnlLuongNVLayout.createSequentialGroup()
                        .addGroup(pnlLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlLuongNVLayout.createSequentialGroup()
                                .addComponent(btnXuatPDF2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXuatExcel2))
                            .addGroup(pnlLuongNVLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlLuongNVLayout.setVerticalGroup(
            pnlLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLuongNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDATE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatPDF2)
                    .addComponent(btnXuatExcel2))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tabs.addTab("Lương nhân viên", pnlLuongNV);

        getContentPane().add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 470));

        pnlBackground.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );

        getContentPane().add(pnlBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        this.xuatExcelThongke();
        Runtime run = Runtime.getRuntime();
        String url = "src\\Excel\\ThongKeDT.xlsx";
        try {
            run.exec("rundll32 url.dll, FileProtocolHandler " + url);
        } catch (IOException ex) {
            Logger.getLogger(ThongKeJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    private void btnXuatPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPDFActionPerformed
        try {
            tblKhoHang.print();
        } catch (PrinterException ex) {
            Logger.getLogger(ThongKeJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXuatPDFActionPerformed

    private void btnXuatPDF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPDF2ActionPerformed
        try {
            tblluongnhanvien.print();
        } catch (PrinterException e) {
        }
    }//GEN-LAST:event_btnXuatPDF2ActionPerformed

    private void btnXuatExcel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcel2ActionPerformed
        this.xuatExcelLuongNV();
        Runtime run = Runtime.getRuntime();
        String url = "src\\Excel\\LuongNV.xlsx";
        try {
            run.exec("rundll32 url.dll, FileProtocolHandler " + url);
        } catch (IOException ex) {
            Logger.getLogger(ThongKeJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXuatExcel2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            ThongKeJDialog dialog = new ThongKeJDialog(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.JButton btnXuatExcel2;
    private javax.swing.JButton btnXuatPDF;
    private javax.swing.JButton btnXuatPDF2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDATE;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlKhohang;
    private javax.swing.JPanel pnlLuongNV;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblKhoHang;
    private javax.swing.JTable tblluongnhanvien;
    // End of variables declaration//GEN-END:variables

}
