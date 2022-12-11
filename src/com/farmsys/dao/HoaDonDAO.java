/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.dao;

import com.farmsys.Entity.HoaDon;
import com.farmsys.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class HoaDonDAO extends FarmSysDAO<HoaDon, String> {

    String INSERT_SQL = "INSERT INTO HoaDon (TenCay,TrongLuong,ThoiGian,ThanhTien) VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE HoaDon SET TenCay =?, TrongLuong =?, ThoiGian =? , ThanhTien =?  WHERE MaHD =?";
    String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHD =?";
    String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE MaHD =?";

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<HoaDon>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getInt("MaHD"));
                entity.setTenCay(rs.getString("TenCay"));
                entity.setThoigian(rs.getDate("ThoiGian"));
                entity.setTrongLuong(rs.getFloat("TrongLuong"));
                entity.setThanhTienFloat(rs.getFloat("ThanhTien"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(HoaDon entity) {
        try {
            JdbcHelper.update(INSERT_SQL, entity.getTenCay(), entity.getTrongLuong(), entity.getThoigian(), entity.getThanhTienFloat());
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(HoaDon entity) {
        try {
            JdbcHelper.update(UPDATE_SQL, entity.getTenCay(), entity.getTrongLuong(), entity.getThoigian(), entity.getThanhTienFloat(), entity.getMaHD());
        } catch (SQLException ex) {
            Logger.getLogger(GianTrongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String key) {
        try {
            JdbcHelper.update(DELETE_SQL, key);
        } catch (SQLException ex) {
            Logger.getLogger(GianTrongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<HoaDon> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDon selectById(String key) {
        List<HoaDon> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
