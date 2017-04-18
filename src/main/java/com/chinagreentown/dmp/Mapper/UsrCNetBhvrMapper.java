package com.chinagreentown.dmp.Mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.UsrCNetBhvrPojo;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.bhvr;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.lang.reflect.Field;

/**
 * Created by yun on 2017/4/17.
 */
public class UsrCNetBhvrMapper implements RowMapper<bhvr> {


    @Override
    public bhvr mapRow(Result result, int rowNum) throws Exception {
        bhvr o = (bhvr) BeanUtil.mapRow(result, bhvr.class);
        return o;
    }
}
