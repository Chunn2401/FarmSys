/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.dao;

import com.farmsys.Entity.NhanVien;
import com.farmsys.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trieu
 */
public class NhanVienDAO extends FarmSysDAO<NhanVien, String> {

    String INSERT_SQL = "INSERT INTO NhanVien(MaNV, MatKhau, HoTen, GioiTinh, Email, Luong, VaiTro, Hinh, QRcode) VALUES(?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NhanVien SET MatKhau=?,HoTen=?,GioiTinh=?,Email=?, Luong=?, VaiTro=?, Hinh=?,QRcode=? WHERE MaNV=?";
    String UPDATEQRcode_SQL = "UPDATE NhanVien SET QRcode = ? WHERE QRcode = ?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
    String SELECT_ALL_SQL = "SELECT *FROM NhanVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV=?";
    String SELECT_BY_QR_SQL = "SELECT * FROM NhanVien WHERE QRcode=?";
    String SELECT_BY_Email_SQL = "SELECT * FROM NhanVien WHERE Email=?";
    String RESET_PASS_SQL = "UPDATE NhanVien SET MatKhau=? WHERE MaNV=?";
    String SELECT_NhanVien_SQL = "SELECT *FROM NhanVien WHERE VaiTro= 0";
    String SELECT_NhanVienQRcode_SQL = "SELECT *FROM NhanVien WHERE QRcode = ?";
    String SELECT_BY_img_SQL = "SELECT * FROM NhanVien WHERE HoTen=?";

    @Override
    public void insert(NhanVien entity) {
        try {
            JdbcHelper.update(INSERT_SQL, entity.getMaNV(), entity.getMatKhau(), entity.getHoTen(), entity.isGioiTinh(), entity.getEmail(), entity.getLuong(), entity.isVaiTro(), entity.getHinh(), entity.getQRcodeString());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(NhanVien entity) {
        try {
            JdbcHelper.update(UPDATE_SQL, entity.getMatKhau(), entity.getHoTen(), entity.isGioiTinh(), entity.getEmail(), entity.getLuong(), entity.isVaiTro(), entity.getHinh(), entity.getQRcodeString(), entity.getMaNV());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateQRcode(String entity) {
        try {
            JdbcHelper.update(UPDATE_SQL, entity);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String key) {
        try {
            JdbcHelper.update(DELETE_SQL, key);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<NhanVien> selectNhanVien() {
        return this.selectBySql(SELECT_NhanVien_SQL);
    }

    @Override
    public NhanVien selectById(String key) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public NhanVien selectByQR(String key) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_QR_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public NhanVien selectByEmail(String key) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_Email_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public NhanVien selectByQRcodeFormNV(String key) {
        List<NhanVien> list = this.selectBySql(SELECT_NhanVienQRcode_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setEmail(rs.getString("Email"));
                entity.setLuong(rs.getInt("Luong"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setHinh(rs.getString("Hinh"));
                entity.setQRcodeString(rs.getString("QRcode"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetPass(NhanVien entity) {
        try {
            JdbcHelper.update(RESET_PASS_SQL, entity.getMatKhau(), entity.getMaNV());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public NhanVien selectHinh(String hoten) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_img_SQL, hoten);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<NhanVien> selectByTenNV(String keyword) {
        String SELECT_BY_NAME_SQL = "select*from NhanVien WHERE HoTen LIKE ?";
        return this.selectBySql(SELECT_BY_NAME_SQL, "%" + keyword + "%");
    }
}
