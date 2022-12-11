/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.data;

/**
 *
 * @author Administrator
 */
public class JsonResult {
    private String cod;
    private int message;
    private int cnt;
    private DailyWeather[] list;
    private City city;

    public JsonResult() {
    }

    public String getCod() {
        return cod;
    }

    public int getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public DailyWeather[] getList() {
        return list;
    }

    public City getCity() {
        return city;
    }
    
}
