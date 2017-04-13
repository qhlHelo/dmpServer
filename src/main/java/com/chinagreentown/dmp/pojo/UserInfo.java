package com.chinagreentown.dmp.pojo;

import com.chinagreentown.dmp.pojo.datacenter.UserBaseInfo;

/**
 * Created by yun on 2017/4/11.
 */
public class UserInfo extends UserBaseInfo {

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

    public String getHascar() {
        return hascar;
    }

    public void setHascar(String hascar) {
        this.hascar = hascar;
    }

    //是否已婚
    private String marriage;
    //是否生育
    private String bear;
    //是否有车
    private String hascar;

}
