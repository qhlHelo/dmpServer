package com.chinagreentown.dmp.service;

import java.util.Map;

import com.chinagreentown.dmp.pojo.ComInfoPojo.com;
import org.json.JSONException;

/**
 * Created by yun on 2017/4/17.
 */
public interface PrecisionMarketingService {

    /**
     * 根据手机号和日期  获取 用户画像
     *
     * @param date
     * @return
     */
    Object getUsrLabelInfo(String date) throws NoSuchFieldException, JSONException, IllegalAccessException;


    Map<String, Object> getComMapDTO(com comEnity) throws NoSuchFieldException, JSONException, IllegalAccessException;

    Map<String, Object>  getConMapDTO(com comEnity) throws JSONException;

}
