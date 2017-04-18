package com.chinagreentown.dmp.Mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.ComInfoPojo.com;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by yun on 2017/4/18.
 */
public class UsrComInfoMapper implements RowMapper<com> {


    @Override
    public com mapRow(Result result, int rowNum) throws Exception {
        com o = (com) BeanUtil.mapRow(result, com.class);
        o.setRowName(Bytes.toString(result.getRow()));
        return o;
    }
}
