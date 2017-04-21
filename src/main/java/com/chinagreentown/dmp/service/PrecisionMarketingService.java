package com.chinagreentown.dmp.service;

import java.util.List;
import java.util.Map;

import com.chinagreentown.dmp.pojo.ComInfoPojo.com;
import com.chinagreentown.dmp.pojo.UserInfo;
import com.chinagreentown.dmp.pojo.UsrBasAttrPojo.attr;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.bhvr;
import com.chinagreentown.dmp.pojo.UsrPoiInfoPojo.poi;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
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
    Object getUsrLabelInfo(String date, String phoneNum) throws NoSuchFieldException, JSONException, IllegalAccessException;


    Object  getEsateMicro(String date,String esateCode);

    /**
     * 根据通信对象获取 通信信息
     *
     * @param comenitys
     * @return key 为用户加密手机号  hbase 中取出的 通信信息对象
     * @throws NoSuchFieldException
     * @throws JSONException
     * @throws IllegalAccessException
     */
    Map<String, Object> getComMapDTO(List<com> comenitys) throws NoSuchFieldException, JSONException, IllegalAccessException;

    /**
     * 根据哦通信对象 获取消费信息
     *
     * @param comenitys hbase 中取出的 通信信息对象
     * @return key 为用户加密手机号
     * @throws JSONException
     */
    Map<String, Object> getConMapDTO(List<com> comenitys) throws JSONException;

    /**
     * 获取用户基本信息
     *
     * @param attrEnitys hbase 中取出的 用户基本信息对象
     * @return key 为用户加密手机号
     */
    Map<String, Object> getUserAttrDTO(List<attr> attrEnitys) throws JSONException;

    /**
     * 生活位置信息 转
     *
     * @param poiEnitys hbase 中取出的 位置信息对象
     * @return key 为用户加密手机号
     */
    Map<String, Object> getUsrPoiInfoLive(List<poi> poiEnitys);

    /**
     * 工作坐标  转 dto
     *
     * @param poiEnitys hbase 中取出的 位置信息对象
     * @return key 为用户加密手机号
     */
    Map<String, Object> getUsrPoiInfoWork(List<poi> poiEnitys);


    /**
     * c 网爱好  转前台dto
     * @param bhvrs
     * @return
     */
    Map<String,Object>  getUsrCnetBehvr(List<bhvr>  bhvrs) throws IllegalAccessException, JSONException;





}
