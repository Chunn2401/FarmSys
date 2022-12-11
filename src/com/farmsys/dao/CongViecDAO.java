/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.dao;

import com.farmsys.Entity.CongViec;

import com.farmsys.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trieu
 */
public class CongViecDAO extends FarmSysDAO<CongViec, String> {

    String SELECT_ALL_SQL = "SELECT * FROM CongViec";
    String SELECT_BY_ID_SQL = "SELECT * FROM CongViec WHERE TenCV=?";
    

    @Override
    public List<CongViec> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }
    

    @Override
    public CongViec selectById(String key) {
        List<CongViec> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<CongViec> selectBySql(String sql, Object... args) {
        List<CongViec> list = new ArrayList<CongViec>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                CongViec entity = new CongViec();
                //entity.setMaCV(rs.getInt("MaCV"));
                entity.setTenCV(rs.getString("TenCV"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(CongViec entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(CongViec entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
