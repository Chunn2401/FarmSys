/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmsys.dao;

import com.farmsys.Entity.Chart;
import com.farmsys.Entity.ThuHoach;
import com.farmsys.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ChartDAO extends FarmSysDAO<Chart, String> {

    String SELECT_ALL_SQL = "select distinct (select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/01/01' and" + "'" + Year.now() + "/01/31') as 'TongTien01',\n"
            + "		(select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/02/01' and" + "'" + Year.now() + "/02/28') as 'TongTien02',\n"
            + "		(select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/03/01' and" + "'" + Year.now() + "/03/31') as 'TongTien03',\n"
            + "		(select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/04/01' and" + "'" + Year.now() + "/04/30') as 'TongTien04',\n"
            + "		(select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/05/01' and" + "'" + Year.now() + "/05/31') as 'TongTien05',\n"
            + "		(select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/06/01' and" + "'" + Year.now() + "/06/30') as 'TongTien06',\n"
            + "		(select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/07/01' and" + "'" + Year.now() + "/07/31') as 'TongTien07',\n"
            + "		(select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/08/01' and" + "'" + Year.now() + "/08/31') as 'TongTien08',\n"
            + "		(select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/09/01' and" + "'" + Year.now() + "/09/30') as 'TongTien09',\n"
            + "		(select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/10/01' and" + "'" + Year.now() + "/10/31') as 'TongTien10',\n"
            + "		(select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/11/01' and" + "'" + Year.now() + "/11/30') as 'TongTien11',\n"
            + "		(select SUM(coin) from KhoHang where ThoiGianThuHoach between" + "'" + Year.now() + "/12/01' and" + "'" + Year.now() + "/12/31') as 'TongTien12'\n"
            + "		from KhoHang";


    @Override
    public void insert(Chart entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Chart entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chart> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Chart selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<Chart> selectBySql(String sql, Object... args) {
        List<Chart> list = new ArrayList<Chart>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                Chart entity = new Chart();
                entity.setThang1(rs.getDouble("TongTien01"));
                entity.setThang2(rs.getDouble("TongTien02"));
                entity.setThang3(rs.getDouble("TongTien03"));
                entity.setThang4(rs.getDouble("TongTien04"));
                entity.setThang5(rs.getDouble("TongTien05"));
                entity.setThang6(rs.getDouble("TongTien06"));
                entity.setThang7(rs.getDouble("TongTien07"));
                entity.setThang8(rs.getDouble("TongTien08"));
                entity.setThang9(rs.getDouble("TongTien09"));
                entity.setThang10(rs.getDouble("TongTien10"));
                entity.setThang11(rs.getDouble("TongTien11"));
                entity.setThang12(rs.getDouble("TongTien12"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
