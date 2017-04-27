package com.chinagreentown.dmp.pojo.este_info;

/**
 * Created by yun on 2017/4/22.
 */
public class assc {

    //行名字
    private String rowName;
    //关联的成交用户
    private String buy_cus;
    //关联的意向用户手机号
    private String intention_tel;
    //关联的音箱用户 宽带账号
    private String intention_bd;
    //关联的竞品楼盘
    private String compatible_pro;

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getBuy_cus() {
        return buy_cus;
    }

    public void setBuy_cus(String buy_cus) {
        this.buy_cus = buy_cus;
    }

    public String getIntention_tel() {
        return intention_tel;
    }

    public void setIntention_tel(String intention_tel) {
        this.intention_tel = intention_tel;
    }

    public String getIntention_bd() {
        return intention_bd;
    }

    public void setIntention_bd(String intention_bd) {
        this.intention_bd = intention_bd;
    }

    public String getCompatible_pro() {
        return compatible_pro;
    }

    public void setCompatible_pro(String compatible_pro) {
        this.compatible_pro = compatible_pro;
    }
}
