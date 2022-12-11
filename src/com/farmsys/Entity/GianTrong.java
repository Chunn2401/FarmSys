/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.Entity;

/**
 *
 * @author trieu
 */
public class GianTrong {

    private int maDan;
    private String tenDan;
    private boolean trangThai;

    public GianTrong() {
    }

    public GianTrong(int maDan, String tenDan, boolean trangThai) {
        this.maDan = maDan;
        this.tenDan = tenDan;
        this.trangThai = trangThai;
    }

    public int getMaDan() {
        return maDan;
    }

    public void setMaDan(int maDan) {
        this.maDan = maDan;
    }

    public String getTenDan() {
        return tenDan;
    }

    public void setTenDan(String tenDan) {
        this.tenDan = tenDan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenDan;
    }

}
