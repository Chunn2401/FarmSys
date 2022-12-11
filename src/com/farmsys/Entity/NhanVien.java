/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.Entity;

import com.farmsys.dao.KhoHangDAO;
import com.farmsys.dao.NhanVienDAO;
import com.farmsys.dao.NhatKyDAO;

/**
 *
 * @author trieu
 */
public class NhanVien {

    private String maNV;
    private String matKhau;
    private String hoTen;
    private boolean gioiTinh;
    private String email;
    private int luong;
    private String hinh;
    private boolean vaiTro;
    private String QRcodeString;
    private Float TongLuong;

    public NhanVien() {
    }

    public NhanVien(String maNV, String matKhau, String hoTen, boolean gioiTinh, String email, int luong, String hinh, boolean vaiTro, String QRcodeString) {
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.luong = luong;
        this.hinh = hinh;
        this.vaiTro = vaiTro;
        this.QRcodeString = QRcodeString;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getQRcodeString() {
        return QRcodeString;
    }

    public void setQRcodeString(String QRcodeString) {
        this.QRcodeString = QRcodeString;
    }

    @Override
    public String toString() {
        return maNV;
    }

    KhoHangDAO khDAO = new KhoHangDAO();
    NhanVienDAO nvDAO = new NhanVienDAO();
    NhatKyDAO nkDAO = new NhatKyDAO();

    public float getBonusMonth() {
        int tongTrongCay = nkDAO.selectDoneMonthByTrangThaiAndCongViecAndNhanVien(3, "Trồng cây", maNV).size();
        int tongChamSoc = nkDAO.selectDoneMonthByTrangThaiAndCongViecAndNhanVien(3, "Chăm sóc", maNV).size();
        int tongThuHoach = nkDAO.selectDoneMonthByTrangThaiAndCongViecAndNhanVien(3, "Thu hoạch", maNV).size();
        int tongCancel = nkDAO.selectDoneMonthByTrangThaiAndNhanVien(2, maNV).size();
        int tongHoaHong = nkDAO.selectDoneMonthByTrangThaiAndNhanVien(5, maNV).size();
        float bonusTrongCay, bonusChamSoc, bonusThuHoach, bonusCancel, bonusHoaHong, bonus;

        // sét KPI trồng cây và trả về tiền thưởng trồng cây
        if (tongTrongCay > 50) {
            bonusTrongCay = (float) (tongTrongCay * 1.5);
        } else if (tongTrongCay > 30) {
            bonusTrongCay = (float) (tongTrongCay * 1.25);
        } else {
            bonusTrongCay = tongTrongCay * 1;
        }

        // sét KPI chăm sóc và trả về tiền thưởng thu hoạch
        if (tongChamSoc > 150) {
            bonusChamSoc = (float) (tongChamSoc * 1.75);
        } else if (tongChamSoc > 100) {
            bonusChamSoc = (float) (tongChamSoc * 1.5);
        } else if (tongChamSoc > 50) {
            bonusChamSoc = (float) (tongChamSoc * 1.25);
        } else {
            bonusChamSoc = tongChamSoc * 1;
        }

        // sét KPI thu hoạch và trả về tiền thưởng thu hoạch
        if (tongThuHoach > 50) {
            bonusThuHoach = (float) (tongThuHoach * 1.5);
        } else if (tongThuHoach > 25) {
            bonusThuHoach = (float) (tongThuHoach * 1.25);
        } else {
            bonusThuHoach = (float) (tongThuHoach * 1);
        }

        //sét KPI cancel và trả về tiền phạt
        if (tongCancel > 10) {
            bonusCancel = tongCancel * -2;
        } else if (tongCancel > 5) {
            bonusCancel = (float) (tongCancel * -1.5);
        } else if (tongCancel > 3) {
            bonusCancel = (float) (tongCancel * -1.25);
        } else {
            bonusCancel = tongCancel * 0;
        }

        //tìm sản phẩm đã bán và trả về hoa hồng
        bonusHoaHong = tongHoaHong * 2;

        return bonus = bonusTrongCay + bonusChamSoc + bonusThuHoach + bonusCancel + bonusHoaHong;
    }

    public float getTongluong() {
        return getLuong() + getBonusMonth();
    }
}
