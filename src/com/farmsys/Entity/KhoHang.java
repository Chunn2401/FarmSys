/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmsys.Entity;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class KhoHang {

    private int MaTH;
    private String TenGian;
    private String TenCay;
    private Float TrongLuong;
    private Date NgayTH;
    private Double GiaThanh;

    public KhoHang() {

    }

    public KhoHang(int MaTH, String TenDan, String TenCay, Float TrongLuong, Date NgayTH, Double GiaThanh) {
        this.MaTH = MaTH;
        this.TenGian = TenDan;
        this.TenCay = TenCay;
        this.TrongLuong = TrongLuong;
        this.NgayTH = NgayTH;
        this.GiaThanh = GiaThanh;
    }

    public int getMaTH() {
        return MaTH;
    }

    public void setMaTH(int MaTH) {
        this.MaTH = MaTH;
    }

    public String getTenGian() {
        return TenGian;
    }

    public void setTenGian(String TenDan) {
        this.TenGian = TenDan;
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

    public Date getNgayTH() {
        return NgayTH;
    }

    public void setNgayTH(Date NgayTH) {
        this.NgayTH = NgayTH;
    }

    public Double getGiaThanh() {
        return GiaThanh;
    }

    public void setGiaThanh(Double GiaThanh) {
        this.GiaThanh = GiaThanh;
    }

}
