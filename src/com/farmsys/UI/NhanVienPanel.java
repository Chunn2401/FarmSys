/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.UI;

import com.farmsys.Entity.NhanVien;
import com.farmsys.Helper.Auth;
import com.farmsys.Helper.MailHelper;
import com.farmsys.Helper.MsgBox;
import com.farmsys.Helper.XImage;
import com.farmsys.dao.NhanVienDAO;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Image;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author NguyenTrung
 */
public class NhanVienPanel extends javax.swing.JPanel {

    private String manv;
    private String emailNV;
    String QRcoderandomString;
    String QRnow;

    public NhanVienPanel() {
        initComponents();
        init();
        this.fillTable();
        this.row = -1;
        this.updateStatus();

    }
    JFileChooser fileChooser = new JFileChooser();

    NhanVienDAO dao = new NhanVienDAO();
    int row = -1;

    void init() {
        if (!Auth.isLogin()) {
            MsgBox.alert(this, "Chưa đăng nhập, vô cái loz");
            System.exit(0);
        }
        this.updateStatus();
    }

    void insert() {
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Không có quyền xóa nhân viên!");
        } else {
            if (timKiemNhanVien() == -1) {
                NhanVien nv = getForm();
                String mk1 = txtMatKhau.getText();
                String mk2 = txtMatKhau2.getText();
                if (!mk2.equals(mk1)) {
                    MsgBox.alert(this, "Xác nhận mật khẩu không đúng");
                } else {
                    try {
                        this.createQRcode();
                        dao.insert(nv);
                        this.fillTable();
                        MsgBox.alert(this, "Thêm mới thành công!");
                        this.Sendmail();
                    } catch (Exception e) {
                        MsgBox.alert(this, "Thêm thất bại!");
                        System.out.println(e);
                    }
                }
            }
        }
    }

    void update() {
        NhanVien nv = getForm();
        String mk1 = new String(txtMatKhau.getPassword());
        String mk2 = new String(txtMatKhau2.getPassword());

        if (!mk2.equals(mk1)) {
            MsgBox.alert(this, "Xác nhận mật khẩu không đúng!");
        } else {
            try {
                this.createQRcode();
                dao.update(nv);
                this.fillTable();
                MsgBox.alert(this, "Cập nhật thành công!");
                this.Sendmail();
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại!");
                System.out.println(e);
            }
        }
    }

    void delete() {
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Không có quyền xóa nhân viên!");
        } else {
            String manv = txtMaNV.getText();
            System.out.println(Auth.user.getMaNV());
            System.out.println(manv);
            if (manv.equals(Auth.user.getMaNV())) {
                MsgBox.alert(this, "Bạn không được xóa chính bạn!");
            } else if (MsgBox.confirm(this, "bạn thực sự muốn xóa nhân viên này?")) {
                try {
                    dao.delete(manv);
                    this.fillTable();
                    this.clearForm();
                    MsgBox.alert(this, "Xóa thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xóa thất bại!");
                    System.out.println(e);
                }
            }
        }
    }

    void clearForm() {
        NhanVien nv = new NhanVien();
        this.setForm(nv);
        this.row = -1;
        lblHinh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/avatar.png")));
        this.updateStatus();
    }

    void edit() {
        String manv = (String) tblNhanVien.getValueAt(this.row, 0);
        NhanVien nv = dao.selectById(manv);
        this.setForm(nv);
        this.updateStatus();
    }

    void first() {
        this.row = 0;
        this.edit();
    }

    void prev() {
        if (this.row > 0) {
            this.row--;
            this.edit();
        }
    }

    void next() {
        if (this.row < tblNhanVien.getRowCount() - 1) {
            this.row++;
            this.edit();
        }
    }

    void last() {
        this.row = tblNhanVien.getColumnCount() - 1;
        this.edit();
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = dao.selectAll();
            for (NhanVien nv : list) {

                Object[] row = {
                    nv.getMaNV(), "***********", nv.getHoTen(),
                    nv.isVaiTro() ? "Trưởng phòng" : "Nhân viên",
                    nv.getEmail(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setForm(NhanVien nv) {
        txtMaNV.setText(nv.getMaNV());
        txtHoTen.setText(nv.getHoTen());
        txtMatKhau.setText(nv.getMatKhau());
        txtMatKhau2.setText(nv.getMatKhau());
        rdoTruongPhong.setSelected(nv.isVaiTro());
        rdoNhanVien.setSelected(!nv.isVaiTro());

        rdoNam.setSelected(nv.isGioiTinh());
        rdoNu.setSelected(!nv.isGioiTinh());
        txtEmail.setText(nv.getEmail());
        txtLuong.setText(nv.getLuong() + "");
        txtqrcode.setText(nv.getQRcodeString() + "");

        if (nv.getHinh() != null) {
            lblHinh1.setToolTipText(nv.getHinh());
            ImageIcon icon = XImage.read(nv.getHinh()); // Lấy địa chỉ của file Icon

            //Chuyển Icon sang image và điều chỉnh kích thước
            Image scaleIcon = icon.getImage().getScaledInstance(lblHinh1.getWidth(), lblHinh1.getHeight(), Image.SCALE_DEFAULT);
            lblHinh1.setIcon(new javax.swing.ImageIcon(scaleIcon));
        } else {
            lblHinh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/avatar.png")));
        }
        this.createQRcode1();

    }

    NhanVien getForm() {
        if (validation()) {
            NhanVien nv = new NhanVien();
            nv.setMaNV(txtMaNV.getText());
            nv.setHoTen(txtHoTen.getText());
            nv.setMatKhau(new String(txtMatKhau.getPassword()));
            nv.setVaiTro(rdoTruongPhong.isSelected());
            //xét giới tính

            nv.setGioiTinh(rdoNam.isSelected());
            nv.setEmail(txtEmail.getText());
            nv.setLuong(Integer.parseInt(txtLuong.getText()));
            nv.setQRcodeString(txtqrcode.getText());
            nv.setHinh(lblHinh1.getToolTipText());
            return nv;
        }
        return null;
    }

    void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblNhanVien.getColumnCount() - 1);
        //Trạng thái form
        txtMaNV.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);

    }

    private void randomString() {
        QRcoderandomString = UUID.randomUUID().toString();
        txtqrcode.setText(QRcoderandomString);
    }

    private void createQRcode() {
        try {
            String qrCodeData = QRcoderandomString;
            String filePath = "src\\QRcode\\a.png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (WriterException | IOException e) {
            System.err.println(e);
        }
    }

    private void createQRcode1() {
        try {
            String qrCodeData = txtqrcode.getText();
            String filePath = "src\\QRcode\\b.png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code đã tạo thành công !");

        } catch (WriterException | IOException e) {
            System.err.println(e);
        }
    }

    void Sendmail() {
        MsgBox.alert(this, "Đang gửi mail...");
        String body = "Đây là mã QR code cá nhân. Vui lòng không để cho người khác có được mã này !";
        try {
            manv = txtMaNV.getText();
            NhanVien nhanVien = dao.selectById(manv);
            if (nhanVien != null) {//check tk có tồn tại không
                emailNV = dao.selectById(manv).getEmail();//check mail nv
                if (emailNV == null) {
                    MsgBox.alert(this, "Tài khoản này chưa có email");
                } else {//tài khoản có mail --> gửi mail -->check otp
                    MailHelper.sendFile(emailNV, "QRCode to FarmSys", body, "src\\QRCODE\\a.png");
                    MsgBox.alert(this, "Gửi mail thành công !");

                }
            } else {
                MsgBox.alert(this, "Tài khoản không tồn tại");
            }
        } catch (Exception e) {
        }
    }

    void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file); // Lưu hình vào thư mục logos
            ImageIcon icon = XImage.read(file.getName()); // Đọc hình từ logos
            Image scaleIcon = icon.getImage().getScaledInstance(lblHinh1.getWidth(), lblHinh1.getHeight(), Image.SCALE_DEFAULT);
            lblHinh1.setIcon(new javax.swing.ImageIcon(scaleIcon));
            lblHinh1.setToolTipText(file.getName()); //Giữ tên hình trong toolTip
        }
    }

    private boolean validation() {
        if (txtMaNV.getText().isEmpty()) {
            MsgBox.alert(this, "Bạn chưa nhập mã nhân viên!");
            txtMaNV.requestFocus();
            return false;
        }
        if (Arrays.toString(txtMatKhau.getPassword()).isEmpty()) {
            MsgBox.alert(this, "Bạn chưa nhập mật khẩu!");
            txtMatKhau.requestFocus();
            return false;
        }
            if (Arrays.toString(txtMatKhau2.getPassword()).isEmpty()) {
            MsgBox.alert(this, "Bạn chưa nhập xác nhận mật khẩu!");
            txtMatKhau2.requestFocus();
            return false;
        }
        if (txtHoTen.getText().isEmpty()) {
            MsgBox.alert(this, "Bạn chưa nhập họ tên nhân viên!");
            txtHoTen.requestFocus();
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            MsgBox.alert(this, "Bạn chưa nhập email!");
            txtEmail.requestFocus();
            return false;
        }
        if (txtLuong.getText().isEmpty()) {
            MsgBox.alert(this, "Bạn chưa nhập lương!");
            txtLuong.requestFocus();
            return false;
        }

        if (!txtMatKhau2.getText().equals(txtMatKhau.getText())) {
            txtMatKhau2.requestFocus();
            return false;
        }

        try {
            Integer.parseInt(txtLuong.getText());
        } catch (Exception e) {
            MsgBox.alert(this, "Lương phải là số!");
            txtLuong.requestFocus();
            return false;
        }

        if (Integer.parseInt(txtLuong.getText()) < 0) {
            MsgBox.alert(this, "Lương không được âm!");
            txtLuong.requestFocus();
            return false;
        }

        //kiểm tra tính hợp lệ đữ liệu bằng biểu thức chính quy
        if (!(txtEmail.getText().matches("\\w+@\\w+\\.\\w+"))) {
            MsgBox.alert(this, "Bạn nhập sai định dạng email!");
            txtEmail.requestFocus();
            return false;
        }

        return true;
    }

    //trả về vị trí tìm thấy sinh viên
    public int timKiemNhanVien() {
        for (int i = 0; i < dao.selectAll().size(); i++) {
            if (txtMaNV.getText().equals(dao.selectAll().get(i).getMaNV())) {
                MsgBox.alert(this, "Mã nhân viên đã tồn tại!");
                txtMaNV.requestFocus();
                return i;
            }
            if (txtEmail.getText().equals(dao.selectAll().get(i).getEmail())) {
                MsgBox.alert(this, "Email đã tồn tại!");
                txtEmail.requestFocus();
                return i;
            }
        }
        return -1;
    }
    
    public void FillTimKiem(){
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);      
        String keywords = txtTimKiem.getText();
        try {
            List<NhanVien> list = dao.selectByTenNV(keywords);
            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getMaNV(), "***********", nv.getHoTen(),
                    nv.isVaiTro() ? "Trưởng phòng" : "Nhân viên",
                    nv.getEmail(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pnlTong = new javax.swing.JPanel();
        pnlList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        pnlEdit = new javax.swing.JPanel();
        lblMaNV = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        lblMatKhau2 = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        txtMatKhau2 = new javax.swing.JPasswordField();
        txtHoTen = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        rdoTruongPhong = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        lblGioiTinh = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblLuongCoban = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        lblHinh1 = new javax.swing.JLabel();
        lblloadagain = new javax.swing.JLabel();
        txtqrcode = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(1083, 750));
        setPreferredSize(new java.awt.Dimension(1083, 750));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTong.setBackground(new java.awt.Color(255, 255, 255));
        pnlTong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NHÂN VIÊN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(102, 255, 102))); // NOI18N
        pnlTong.setMinimumSize(new java.awt.Dimension(1083, 750));
        pnlTong.setPreferredSize(new java.awt.Dimension(1083, 750));
        pnlTong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblNhanVien.setAutoCreateRowSorter(true);
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TÀI KHOẢN", "MẬT KHẨU", "HỌ VÀ TÊN", "VAI TRÒ", "EMAIL", "GIỚI TÍNH"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblNhanVien.setShowGrid(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        pnlList.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 240));

        pnlTong.add(pnlList, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1050, 240));

        pnlEdit.setBackground(new java.awt.Color(255, 255, 255));
        pnlEdit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMaNV.setText("Tài khoản");
        pnlEdit.add(lblMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        lblMatKhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMatKhau.setText("Mật khẩu");
        pnlEdit.add(lblMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        lblMatKhau2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMatKhau2.setText("Xác nhận mật khẩu");
        pnlEdit.add(lblMatKhau2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        lblHoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblHoTen.setText("Họ và tên");
        pnlEdit.add(lblHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

        lblVaiTro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblVaiTro.setText("Vai trò");
        pnlEdit.add(lblVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, -1, -1));
        pnlEdit.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 231, 29));
        pnlEdit.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 231, 29));
        pnlEdit.add(txtMatKhau2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 231, 29));
        pnlEdit.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 231, 29));

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/Add.png"))); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlEdit.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, -1, -1));

        btnSua.setBackground(new java.awt.Color(255, 255, 255));
        btnSua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/Refresh.png"))); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlEdit.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, -1, -1));

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/Delete.png"))); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlEdit.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 300, -1, -1));

        btnMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnMoi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/new.png"))); // NOI18N
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        pnlEdit.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 300, -1, -1));

        buttonGroup2.add(rdoTruongPhong);
        rdoTruongPhong.setSelected(true);
        rdoTruongPhong.setText("Trưởng phòng");
        pnlEdit.add(rdoTruongPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, -1, -1));

        buttonGroup2.add(rdoNhanVien);
        rdoNhanVien.setText("Nhân viên");
        pnlEdit.add(rdoNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, -1, -1));

        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGioiTinh.setText("Giới tính");
        pnlEdit.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 12));

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEmail.setText("Email");
        pnlEdit.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, 29));
        pnlEdit.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 231, 29));

        lblLuongCoban.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblLuongCoban.setText("Lương cơ bản");
        pnlEdit.add(lblLuongCoban, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, 29));
        pnlEdit.add(txtLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 231, 29));

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        pnlEdit.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        pnlEdit.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, -1));

        lblHinh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/avatar.png"))); // NOI18N
        lblHinh1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinh1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblHinh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinh1MouseClicked(evt);
            }
        });
        pnlEdit.add(lblHinh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 304, 279));

        lblloadagain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/farmsys/icons/icons8_synchronize_25px.png"))); // NOI18N
        lblloadagain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblloadagainMouseClicked(evt);
            }
        });
        pnlEdit.add(lblloadagain, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 30, -1));

        pnlTong.add(pnlEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 1050, 380));

        txtqrcode.setText("jPasswordField1");
        pnlTong.add(txtqrcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 1030, -1));

        pnlTong.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1050, 60));

        add(pnlTong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 750));
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.randomString();
        this.insert();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        this.randomString();
        this.update();

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        this.clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.row = tblNhanVien.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void lblHinh1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinh1MouseClicked
        // TODO add your handling code here:
        this.chonAnh();
    }//GEN-LAST:event_lblHinh1MouseClicked

    private void lblloadagainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblloadagainMouseClicked
        this.fillTable();
        txtTimKiem.setText("");
    }//GEN-LAST:event_lblloadagainMouseClicked

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        this.FillTimKiem();
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHinh1;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblLuongCoban;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblMatKhau2;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JLabel lblloadagain;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlList;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoTruongPhong;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JPasswordField txtMatKhau2;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JPasswordField txtqrcode;
    // End of variables declaration//GEN-END:variables
}
