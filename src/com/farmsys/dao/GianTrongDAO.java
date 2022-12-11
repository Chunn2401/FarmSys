/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.dao;

import com.farmsys.Entity.GianTrong;
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
public class GianTrongDAO extends FarmSysDAO<GianTrong, String> {

    String INSERT_SQL = "INSERT INTO GianTrong (TenGian, TrangThai) VALUES(?,0)";
    String UPDATE_SQL = "UPDATE GianTrong SET TenGian =?, TrangThai =? WHERE MaGian =?";
    String DELETE_SQL = "DELETE FROM GianTrong WHERE MaGian =?";
    String SELECT_ALL_SQL = "SELECT * FROM GianTrong order by MaGian";
    String SELECT_BY_ID_SQL = "SELECT * FROM GianTrong WHERE MaGian =?";
    String SELECT_BY_ID_TT = "SELECT * FROM GianTrong WHERE TrangThai = ?";
    String SELECT_BY_ID_TenGian = "SELECT * FROM GianTrong WHERE TenGian =?";
    String UPDATE_TrangThai_SQL = "UPDATE GianTrong SET  TrangThai = 1 WHERE TenGian = ?";
    String UPDATE_TraVeCbo_SQL = "UPDATE GianTrong SET  TrangThai = 0 WHERE TenGian = ?";

    @Override
    public void insert(GianTrong entity) {
        try {
            JdbcHelper.update(INSERT_SQL, entity.getTenDan());
        } catch (SQLException ex) {
            Logger.getLogger(GianTrongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(GianTrong entity) {
        try {
            JdbcHelper.update(UPDATE_SQL, entity.getTenDan(), entity.isTrangThai(), entity.getMaDan());
        } catch (SQLException ex) {
            Logger.getLogger(GianTrongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTrangThai(String TenGian) {
        try {
            JdbcHelper.update(UPDATE_TrangThai_SQL, TenGian);
        } catch (SQLException ex) {
            Logger.getLogger(GianTrongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateAgain(String TenGian) {
        try {
            JdbcHelper.update(UPDATE_TraVeCbo_SQL, TenGian);
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
    public List<GianTrong> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<GianTrong> selectByTT(Integer key) {
        return this.selectBySql(SELECT_BY_ID_TT, key);
    }

    public GianTrong selectById(Integer key) {
        List<GianTrong> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public GianTrong selectByTenGian(String key) {
        List<GianTrong> list = this.selectBySql(SELECT_BY_ID_TenGian, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<GianTrong> selectBySql(String sql, Object... args) {
        List<GianTrong> list = new ArrayList<GianTrong>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                GianTrong entity = new GianTrong();
                entity.setMaDan(rs.getInt("MaGian"));
                entity.setTenDan(rs.getString("TenGian"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GianTrong selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
