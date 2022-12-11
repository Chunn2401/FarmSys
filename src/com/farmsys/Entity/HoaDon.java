/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.Entity;

import java.util.Date;

/**
 *
 * @author NguyenTrung
 */
public class HoaDon {

    private int MaHD;
    private String TenCay;
    private Float TrongLuong;
    private Date Thoigian;
    private Float ThanhTienFloat;

    public HoaDon() {
    }

    public HoaDon(int MaHD, String TenCay, Float TrongLuong, Date Thoigian, Float ThanhTienFloat) {
        this.MaHD = MaHD;
        this.TenCay = TenCay;
        this.TrongLuong = TrongLuong;
        this.Thoigian = Thoigian;
        this.ThanhTienFloat = ThanhTienFloat;
    }

    public Float getThanhTienFloat() {
        return ThanhTienFloat;
    }

    public void setThanhTienFloat(Float ThanhTienFloat) {
        this.ThanhTienFloat = ThanhTienFloat;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public String getTenCay() {
        return TenCay;
    }

    public void setTenCay(String TenCay) {
        this.TenCay = TenCay;
    }

    public Float getTrongLuong() {
        return TrongLuong;
    }

    public void setTrongLuong(Float TrongLuong) {
        this.TrongLuong = TrongLuong;
    }

    public Date getThoigian() {
        return Thoigian;
    }

    public void setThoigian(Date Thoigian) {
        this.Thoigian = Thoigian;
    }

}
