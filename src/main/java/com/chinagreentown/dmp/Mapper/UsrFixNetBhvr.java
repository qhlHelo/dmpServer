package com.chinagreentown.dmp.Mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.UsrBasAttrPojo.attr;
import com.chinagreentown.dmp.pojo.UsrFixNetBhvr.bhvr;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;

/**
 * Created by yun on 2017/4/21.
 * 解析固网的数据
 */
public class UsrFixNetBhvr implements RowMapper<bhvr> {


    @Override
    public bhvr mapRow(Result result, int rowNum) throws Exception {
        bhvr o = (bhvr) BeanUtil.mapRow(result, bhvr.class);
        return o;
    }
}
