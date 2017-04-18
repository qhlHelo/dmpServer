package com.chinagreentown.dmp.pojo.UsrPoiInfoPojo;

/**
 * Created by yun on 2017/4/18.
 */
public class poi {

    public String getRowName() {
        return RowName;
    }

    public void setRowName(String rowName) {
        RowName = rowName;
    }

    //行名
    private String RowName;

    //加密手机号
    private String encryption_tel;
    //宽带账号
    private String bd_account;
    //工作欧标
    private String p_work;
    //生活坐标
    private String p_live;
    //工作板块
    private String work_area;
    //生活板块
    private String live_area;

    public String getEncryption_tel() {
        return encryption_tel;
    }

    public void setEncryption_tel(String encryption_tel) {
        this.encryption_tel = encryption_tel;
    }

    public String getBd_account() {
        return bd_account;
    }

    public void setBd_account(String bd_account) {
        this.bd_account = bd_account;
    }

    public String getP_work() {
        return p_work;
    }

    public void setP_work(String p_work) {
        this.p_work = p_work;
    }

    public String getP_live() {
        return p_live;
    }

    public void setP_live(String p_live) {
        this.p_live = p_live;
    }

    public String getWork_area() {
        return work_area;
    }

    public void setWork_area(String work_area) {
        this.work_area = work_area;
    }

    public String getLive_area() {
        return live_area;
    }

    public void setLive_area(String live_area) {
        this.live_area = live_area;
    }


}
