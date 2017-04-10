package com.chinagreentown.dmp.pojo.precisionmarketing;

/**
 * Created by admin on 2017/3/28.
 * 购房需求
 */
public class Purchase {
    // 购房目的
    private String purpose;
    // 购房意向度
    private String intension;
    // 购房意向价格（单价）
    private String unitPrice;
    // 购房意向价格（总价）
    private String totalPrice;
    // 购房意向板块
    private String plate;
    // 购房意向城市
    private String city;

    public Purchase(String purpose, String intension, String unitPrice, String totalPrice, String plate, String city) {
        this.purpose = purpose;
        this.intension = intension;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.plate = plate;
        this.city = city;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getIntension() {
        return intension;
    }

    public void setIntension(String intension) {
        this.intension = intension;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
