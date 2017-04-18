package com.chinagreentown.dmp.service;

import java.util.Map;

import com.chinagreentown.dmp.pojo.ComInfoPojo.com;
import com.chinagreentown.dmp.pojo.UserInfo;
import com.chinagreentown.dmp.pojo.UsrBasAttrPojo.attr;
import com.chinagreentown.dmp.pojo.UsrPoiInfoPojo.poi;
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

    /**
     * 根据通信对象获取 通信信息
     *
     * @param comEnity
     * @return
     * @throws NoSuchFieldException
     * @throws JSONException
     * @throws IllegalAccessException
     */
    Map<String, Object> getComMapDTO(com comEnity) throws NoSuchFieldException, JSONException, IllegalAccessException;

    /**
     * 根据哦通信对象 获取消费信息
     *
     * @param comEnity
     * @return
     * @throws JSONException
     */
    Map<String, Object> getConMapDTO(com comEnity) throws JSONException;

    /**
     * 获取用户基本信息
     *
     * @param attrEnity
     * @return
     */
    Map<String, Object> getUserAttrDTO(attr attrEnity) throws JSONException;

    /**
     * 生活位置信息 转
     *
     * @param poiEnity
     * @return
     */
    Map<String, Object> getUsrPoiInfoLive(poi poiEnity);

    /**
     * 工作坐标  转 dto
     *
     * @param poiEnity
     * @return
     */
    Map<String, Object> getUsrPoiInfoWork(poi poiEnity);


}
