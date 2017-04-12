package com.chinagreentown.dmp.pojo.datacenter;

/**
 * Created by yun on 2017/4/11.
 */
public class UserBaseInfo {
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public NetBehavior getNetBehavior() {
        return netBehavior;
    }

    public void setNetBehavior(NetBehavior netBehavior) {
        this.netBehavior = netBehavior;
    }

    private NetBehavior netBehavior;




    //性别
    private String sex;
    //电话好吗
    private String phonenum;
    //年龄
    private String age;
    //居住地
    private String live;
    //工作地
    private String work;
    //手机类型
    private String phoneBrand;

}
