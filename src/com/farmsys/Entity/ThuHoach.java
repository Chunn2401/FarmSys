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
public class ThuHoach {
    private String TenGian;
    private String TenCay;
    private Date NgayTH;
    
    public ThuHoach (){
        
    }

    public ThuHoach(String TenGian, String TenCay, Date NgayTH) {
        this.TenGian = TenGian;
        this.TenCay = TenCay;
        this.NgayTH = NgayTH;
    }

    public String getTenGian() {
        return TenGian;
    }

    public void setTenGian(String TenGian) {
        this.TenGian = TenGian;
    }

    public String getTenCay() {
        return TenCay;
    }

    public void setTenCay(String TenCay) {
        this.TenCay = TenCay;
    }

    public Date getNgayTH() {
        return NgayTH;
    }

    public void setNgayTH(Date NgayTH) {
        this.NgayTH = NgayTH;
    }

    @Override
    public String toString() {
        return  TenGian ;
    }
            
    
}
