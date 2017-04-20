package com.chinagreentown.dmp.service;

import com.chinagreentown.dmp.pojo.ComInfoPojo.com;
import com.chinagreentown.dmp.pojo.UsrBasAttrPojo.attr;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.bhvr;
import com.chinagreentown.dmp.pojo.UsrPoiInfoPojo.poi;
import org.apache.hadoop.hbase.filter.FilterList;

import java.util.List;

/**
 * Created by yun on 2017/4/17.
 *
 * @description 基础逻辑，获取habse 应用偏好表数据
 */
public interface BaseQueryService {

    /**
     * 返回   通信信息
     *
     * @param familly 列族
     * @param list    过滤器
     * @return 返回对象
     */
    List<com> getUsrCom(String familly, FilterList list);

    /**
     * 返回用户位置信息
     *
     * @param familly
     * @param list
     * @return
     */
    List<poi> getUsrPoiInfo(String familly, FilterList list);

    /**
     * 获取用户基本信息
     *
     * @param familly
     * @param list
     * @return
     */
    List<attr> getUserAttr(String familly, FilterList list);


    /**
     * @param family 获取列族
     * @param list   返回应用偏好
     * @return
     */
    List<bhvr> getUsrBhvr(String family, FilterList list);


}
