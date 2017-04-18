package com.chinagreentown.dmp.service;

import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.bhvr;
import org.apache.hadoop.hbase.filter.FilterList;

import java.util.List;

/**
 * Created by yun on 2017/4/17.
 *
 * @description 基础逻辑，获取habse 数据
 */
public interface BaseQueryService {

    List<bhvr> getBhvr(String family);

    List<bhvr> getBhvr(String family, String qualifier);

    List<bhvr> getBhvr(String family, FilterList list );
}
