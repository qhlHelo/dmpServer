package com.chinagreentown.dmp.pojo.precisionmarketing;

/**
 * Created by admin on 2017/3/28.
 * 用户画像
 */
public class UserPortrait {
    // 用户基础信息
    private BasicInfo basicInfo;
    // C网上网行为
    private CNetwork cNetwork;
    // 固网上网行为
    private FixedNetwork fixedNetwork;
    // 购物行为
    private Shopping shopping;
    // 购房需求
    private Purchase purchase;

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public CNetwork getcNetwork() {
        return cNetwork;
    }

    public void setcNetwork(CNetwork cNetwork) {
        this.cNetwork = cNetwork;
    }

    public FixedNetwork getFixedNetwork() {
        return fixedNetwork;
    }

    public void setFixedNetwork(FixedNetwork fixedNetwork) {
        this.fixedNetwork = fixedNetwork;
    }

    public Shopping getShopping() {
        return shopping;
    }

    public void setShopping(Shopping shopping) {
        this.shopping = shopping;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
