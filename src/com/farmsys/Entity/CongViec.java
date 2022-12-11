/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.Entity;

/**
 *
 * @author NguyenTrung
 */
public class CongViec {
    int MaCV;
    String TenCV;

    public CongViec(int MaCV, String TenCV) {
        this.MaCV = MaCV;
        this.TenCV = TenCV;
    }

    public CongViec() {
    }

    public int getMaCV() {
        return MaCV;
    }

    public void setMaCV(int MaCV) {
        this.MaCV = MaCV;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String TenCV) {
        this.TenCV = TenCV;
    }

    @Override
    public String toString() {
        return TenCV ;
    }
    
}
