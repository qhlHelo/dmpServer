package com.chinagreentown.dmp.pojo.salescenter;

/**
 * Created by admin on 2017/3/29.
 * 案场标签模型对象
 */
public class SalesLabel {
    // 性别(男、女)
    private String sex;
    // 婚否(已婚、未婚、未知)
    private String marriage;
    // 生育(生育、未育、未知)
    private String bear;
    // 年龄段
    private String age;
    // 是否有车(有车、无车、未知)
    private String hascar;
    // 应用偏好(教育、网购、房产、工作等)
    private String apppreference;
    // 居住地
    private String live;
    // 工作地
    private String work;
    // 购房用途(婚房、学区、改善、养老、投资)
    private String purchasepurpose;
    // 手机对象
    private Phone phone;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getBear() {
        return bear;
    }

    public void setBear(String bear) {
        this.bear = bear;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHascar() {
        return hascar;
    }

    public void setHascar(String hascar) {
        this.hascar = hascar;
    }

    public String getApppreference() {
        return apppreference;
    }

    public void setApppreference(String apppreference) {
        this.apppreference = apppreference;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPurchasepurpose() {
        return purchasepurpose;
    }

    public void setPurchasepurpose(String purchasepurpose) {
        this.purchasepurpose = purchasepurpose;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
