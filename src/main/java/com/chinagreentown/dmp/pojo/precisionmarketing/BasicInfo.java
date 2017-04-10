package com.chinagreentown.dmp.pojo.precisionmarketing;

/**
 * Created by admin on 2017/3/28.
 * 基础信息
 */
public class BasicInfo {
    // 姓名
    private String name;
    // 性别
    private String sex;
    // 身份证
    private String id;
    // 年龄段
    private String age;
    // 资产级别
    private String asset;

    public BasicInfo() {
    }

    public BasicInfo(String name, String sex, String id, String age, String asset) {
        this.name = name;
        this.sex = sex;
        this.id = id;
        this.age = age;
        this.asset = asset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

}
