/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmsys.dao;

import com.farmsys.Entity.ThuHoach;
import com.farmsys.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author ASUS
 */
public class ThuHoachDAO extends FarmSysDAO<ThuHoach, String> {
    
    String SELECT_ThuHoach_SQL = "select NhatKy.TenGian,LoaiCay.TenCay,(SELECT DATEADD(day, +(LoaiCay.ThoiGianThuHoach) , NhatKy.NgayKetThuc)) as 'NgayTH' from LoaiCay inner join NhatKy on LoaiCay.TenCay = NhatKy.TenCay where TenCV like N'Trồng cây' and GETDATE() >= (SELECT DATEADD(day, +(LoaiCay.ThoiGianThuHoach) , NhatKy.NgayKetThuc))and TrangThai = 3";
    
    
    
    @Override
    public void insert(ThuHoach entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ThuHoach entity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<ThuHoach> selectAll() {
        return this.selectBySql(SELECT_ThuHoach_SQL); 
    }
    
    @Override
    public ThuHoach selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    protected List<ThuHoach> selectBySql(String sql, Object... args) {
        List<ThuHoach> list = new ArrayList<ThuHoach>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                ThuHoach entity = new ThuHoach();
                entity.setTenGian(rs.getString("TenGian"));
                entity.setTenCay(rs.getString("TenCay"));
                entity.setNgayTH(rs.getDate("NgayTH"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
