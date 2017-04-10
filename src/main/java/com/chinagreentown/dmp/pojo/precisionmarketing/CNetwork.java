package com.chinagreentown.dmp.pojo.precisionmarketing;

import java.util.Map;

/**
 * Created by admin on 2017/3/28.
 * C网上网行为
 */
public class CNetwork {
    // 浏览类别的PV
    private Map<String, Integer> category;
    // 浏览APP的种类及频次
    private Map<String, Integer> app;

    public Map<String, Integer> getCategory() {
        return category;
    }

    public void setCategory(Map<String, Integer> category) {
        this.category = category;
    }

    public Map<String, Integer> getApp() {
        return app;
    }

    public void setApp(Map<String, Integer> app) {
        this.app = app;
    }

}
