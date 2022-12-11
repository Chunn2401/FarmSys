/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.Entity;

import java.util.Date;

/**
 *
 * @author trieu
 */
public class NhatKy {

    private int stt;
    private String tenCV;
    private String tenCay;
    private String tenGian;
    private String chiTiet;
    private String nguoiTao;
    private String nhanVien;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int trangThai; // 0: to do , 1:doing , 2:Từ chối , 3:Hoàn thành , 4:Hoàn thành + trễ

    public NhatKy() {
    }

    public NhatKy(int stt, String tenCV, String tenCay, String tenGian, String chiTiet, String nguoiTao, String nhanVien, Date ngayBatDau, Date ngayKetThuc, int trangThai) {
        this.stt = stt;
        this.tenCV = tenCV;
        this.tenCay = tenCay;
        this.tenGian = tenGian;
        this.chiTiet = chiTiet;
        this.nguoiTao = nguoiTao;
        this.nhanVien = nhanVien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getTenCay() {
        return tenCay;
    }

    public void setTenCay(String tenCay) {
        this.tenCay = tenCay;
    }

    public String getTenGian() {
        return tenGian;
    }

    public void setTenGian(String tenGian) {
        this.tenGian = tenGian;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        String status;
        status = switch (getTrangThai()) {
            case 0 ->
                "Chưa nhận";
            case 1 ->
                "Đang làm";
            case 2 ->
                "Từ chối";
            case 3 ->
                "Hoàn thành";
            case 4 ->
                "Hoàn thành muộn";
            default ->
                "Đang bán";
        };
        return status;
    }

}
