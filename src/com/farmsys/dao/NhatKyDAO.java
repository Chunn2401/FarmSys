/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.dao;

import com.farmsys.Entity.NhatKy;
import com.farmsys.Helper.JdbcHelper;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class NhatKyDAO extends FarmSysDAO<NhatKy, String> {

    String INSERT_SQL = "INSERT INTO NhatKy (TenCV, TenCay, TenGian, ChiTiet, NguoiTao, Nhanvien, NgayBatDau, NgayKetThuc, TrangThai) VALUES(?,?,?,?,?,?,?,?,?)";
    String select_all_sql = "select*from NhatKy";
    String select_by_ten_sql = "select * from NhatKy where TenCV = ?";
    String select_by_trangthai_sql = "select * from NhatKy where TrangThai = ?";
    String select_by_trangthaivaten_sql = "select * from NhatKy where TrangThai = ? and NhanVien like ?";
    String select_formtodoanddoing_sql = "select * from NhatKy where STT = ?";
    String UPDATE_Done_SQL = "UPDATE NhatKy SET TrangThai = 3 where STT = ?";
    String UPDATE_Buying_SQL = "UPDATE NhatKy SET TrangThai = 5 where STT = ?";
    String UPDATE_SQL = "UPDATE NhatKy SET TrangThai = 1 where STT = ?";
    String UPDATE_TuChoi_SQL = "UPDATE NhatKy SET TrangThai = 2 where STT = ?";
    String select_by_Done_Month_SQL = "select *from NhatKy where TrangThai = 3 and NhanVien like ? and  NgayKetThuc between (select CONVERT(varchar,dateadd(d,-(day(getdate()-1)),getdate()),106)) and (select CONVERT(varchar,dateadd(d,-(day(dateadd(m,1,getdate()))),dateadd(m,1,getdate())),106))";
    String UPDATE_TrangThaiTH_SQL = "UPDATE NhatKy SET TrangThai = 5 where TenGian = ? ";
    String select_by_all_month_SQL = "select *from NhatKy where NgayKetThuc between (select CONVERT(varchar,dateadd(d,-(day(getdate()-1)),getdate()),106)) and (select CONVERT(varchar,dateadd(d,-(day(dateadd(m,1,getdate()))),dateadd(m,1,getdate())),106))";
    String select_by_time_sql = "SELECT * FROM NhatKy WHERE NgayBatDau BETWEEN ? AND ? ";
    String select_by_trangthaibymonth_sql = "select * from NhatKy where TrangThai = ? and NgayBatDau BETWEEN ? AND ? ";
    String select_done_month_by_trangthai_and_congviec_and_nhanvien_sql = "select *from NhatKy where TrangThai = ? and TenCV = ? and NhanVien like ? and  NgayKetThuc between (select CONVERT(varchar,dateadd(d,-(day(getdate()-1)),getdate()),106)) and (select CONVERT(varchar,dateadd(d,-(day(dateadd(m,1,getdate()))),dateadd(m,1,getdate())),106))";
    String select_monthe_by_trangthai_and_nhanvien = "select *from NhatKy where TrangThai = ? and NhanVien like ? and  NgayKetThuc between (select CONVERT(varchar,dateadd(d,-(day(getdate()-1)),getdate()),106)) and (select CONVERT(varchar,dateadd(d,-(day(dateadd(m,1,getdate()))),dateadd(m,1,getdate())),106))";

    @Override
    public void insert(NhatKy entity) {
        try {
            JdbcHelper.update(INSERT_SQL, entity.getTenCV(), entity.getTenCay(), entity.getTenGian(), entity.getChiTiet(), entity.getNguoiTao(), entity.getNhanVien(), entity.getNgayBatDau(), entity.getNgayKetThuc(), entity.getTrangThai());
        } catch (SQLException ex) {
            Logger.getLogger(CayTrongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(int entity) {
        try {
            JdbcHelper.update(UPDATE_SQL, entity);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTuChoi(int entity) {
        try {
            JdbcHelper.update(UPDATE_TuChoi_SQL, entity);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public List<NhatKy> selectAll() {
        return selectBySql(select_all_sql);
    }

    public void updateTrangThai(int stt) {
        try {
            JdbcHelper.update(UPDATE_Done_SQL, stt);
        } catch (SQLException ex) {
            Logger.getLogger(NhatKyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTrangThaiBuy(int stt) {
        try {
            JdbcHelper.update(UPDATE_Buying_SQL, stt);
        } catch (SQLException ex) {
            Logger.getLogger(NhatKyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public NhatKy selectById(String macv) {
        List<NhatKy> list = this.selectBySql(select_by_ten_sql, macv);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public NhatKy selectformtodoanddoing(Integer macv) {
        List<NhatKy> list = this.selectBySql(select_formtodoanddoing_sql, macv);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public NhatKy selectById(Integer macv) {
        List<NhatKy> list = this.selectBySql(select_by_ten_sql, macv);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<NhatKy> selectByTen(String tencv) {
        return selectBySql(select_by_ten_sql, tencv);
    }

    public List<NhatKy> selectByTrangThai(int trangthai) {
        return selectBySql(select_by_trangthai_sql, trangthai);
    }

    public List<NhatKy> selectByTrangThaivaTennv(int trangthai, String tennv) {
        return selectBySql(select_by_trangthaivaten_sql, trangthai, tennv);
    }

    @Override
    protected List<NhatKy> selectBySql(String sql, Object... args) {
        List<NhatKy> list = new ArrayList<NhatKy>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhatKy entity = new NhatKy();//Tạo đối tượng NhatKy
                entity.setStt(rs.getInt("STT"));
                entity.setTenCV(rs.getString("TenCV"));
                entity.setTenCay(rs.getString("TenCay"));
                entity.setTenGian(rs.getString("TenGian"));
                entity.setChiTiet(rs.getString("ChiTiet"));
                entity.setNguoiTao(rs.getString("NguoiTao"));
                entity.setNhanVien(rs.getString("NhanVien"));
                entity.setNgayBatDau(rs.getDate("NgayBatDau"));
                entity.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                entity.setTrangThai(rs.getInt("TrangThai"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(NhatKy entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<NhatKy> selectDoneByMonth(String MaNV) {
        return selectBySql(select_by_Done_Month_SQL, MaNV);
    }

    public List<NhatKy> selectDoneMonthByTrangThaiAndCongViecAndNhanVien(int TrangThai, String CongViec, String MaNV) {
        return selectBySql(select_done_month_by_trangthai_and_congviec_and_nhanvien_sql, TrangThai, CongViec, MaNV);
    }

    public List<NhatKy> selectDoneMonthByTrangThaiAndNhanVien(int TrangThai, String MaNV) {
        return selectBySql(select_monthe_by_trangthai_and_nhanvien, TrangThai, MaNV);
    }

    public void updateTrangThaiTH(String TenGian) {
        try {
            JdbcHelper.update(UPDATE_TrangThaiTH_SQL, TenGian);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<NhatKy> selectByTenCay(String keyword) {
        String SELECT_BY_NAME_SQL = "select*from NhatKy WHERE TenCay LIKE ?";
        return this.selectBySql(SELECT_BY_NAME_SQL, "%" + keyword + "%");
    }

    public List<NhatKy> selectByTenCV(String keyword) {
        String SELECT_BY_NAME_SQL = "select*from NhatKy WHERE TenCV LIKE ?";
        return this.selectBySql(SELECT_BY_NAME_SQL, "%" + keyword + "%");
    }

    public List<NhatKy> selectByTenNV(String keyword) {
        String SELECT_BY_NAME_SQL = "select*from NhatKy WHERE NhanVien LIKE ?";
        return this.selectBySql(SELECT_BY_NAME_SQL, "%" + keyword + "%");
    }

    public List<NhatKy> selectAllMonth() {
        return selectBySql(select_by_all_month_SQL);
    }

    public List<NhatKy> selectByTime(Date ngaybatdau, Date ngayketthuc) {
        return selectBySql(select_by_time_sql, ngaybatdau, ngayketthuc);
    }

    public List<NhatKy> selectTrangThaiByMonth(int trangthai, Date NBD, Date NKT) {
        return selectBySql(select_by_trangthaibymonth_sql, trangthai, NBD, NKT);
    }
}
