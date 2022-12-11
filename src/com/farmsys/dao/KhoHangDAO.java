/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmsys.dao;

import com.farmsys.Entity.KhoHang;
import com.farmsys.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class KhoHangDAO {

    String INSERT_SQL = "INSERT INTO KhoHang (TenGian, TenCay, TrongLuong, ThoiGianThuHoach) VALUES(?,?,?,?)";
    String SELECT_ALL_SQL = "select*from KhoHang where TrongLuong > 0";
    String SELECT_BY_TEN_SQL = "select * from KhoHang where TenGian = ?";
    String DELETE_SQL = "DELETE FROM KhoHang where TenDan = ?";
    String UPDATE_SQL = "UPDATE KhoHang SET TrongLuong =? WHERE MaTH =?";
    String SELECT_SLBan_SQL = "UPDATE KhoHang set TrongLuong = ? , Coin = ?  where MaTH = ?";

    public List<KhoHang> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public void insert(KhoHang entity) {
        try {
            JdbcHelper.update(INSERT_SQL, entity.getTenGian(), entity.getTenCay(), entity.getTrongLuong(), entity.getNgayTH());
        } catch (SQLException ex) {
            Logger.getLogger(CayTrongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(KhoHang entity) {
        try {
            JdbcHelper.update(UPDATE_SQL, entity.getTrongLuong(), entity.getMaTH());
        } catch (SQLException ex) {
            Logger.getLogger(KhoHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Float TrongLuong, Double Coin, int MaTH) {
        try {
            JdbcHelper.update(SELECT_SLBan_SQL, TrongLuong, Coin, MaTH);
        } catch (SQLException ex) {
            Logger.getLogger(KhoHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String key) {
        try {
            JdbcHelper.update(DELETE_SQL, key);
        } catch (SQLException ex) {
            Logger.getLogger(KhoHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public KhoHang selectById(String key) {
        List<KhoHang> list = this.selectBySql(SELECT_BY_TEN_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<KhoHang> selectBySql(String sql, Object... args) {
        List<KhoHang> list = new ArrayList<KhoHang>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                KhoHang entity = new KhoHang();
                entity.setMaTH(rs.getInt("MaTH"));
                entity.setTenGian(rs.getString("TenGian"));
                entity.setTenCay(rs.getString("TenCay"));
                entity.setTrongLuong(rs.getFloat("TrongLuong"));
                entity.setNgayTH(rs.getDate("ThoiGianThuHoach"));
                entity.setGiaThanh(rs.getDouble("Coin"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<KhoHang> selectByKeyword(String keyword) {
        String SELECT_BY_NAME_SQL = "SELECT * FROM KhoHang WHERE TenCay LIKE ? and TrongLuong > 0";
        return this.selectBySql(SELECT_BY_NAME_SQL, "%" + keyword + "%");
    }
}
