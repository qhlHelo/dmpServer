package com.chinagreentown.dmp.Mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.UsrBasAttrPojo.attr;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.bhvr;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by yun on 2017/4/18.
 */
public class UsrBasAttrMapper implements RowMapper<attr> {


    @Override
    public attr mapRow(Result result, int rowNum) throws Exception {
        attr o = (attr) BeanUtil.mapRow(result, attr.class);
        o.setRowName(Bytes.toString(result.getRow()));
        return o;
    }
}
