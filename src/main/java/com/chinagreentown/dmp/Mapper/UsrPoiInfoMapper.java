package com.chinagreentown.dmp.Mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.UsrPoiInfoPojo.poi;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by yun on 2017/4/18.
 */
public class UsrPoiInfoMapper implements RowMapper<poi> {


    @Override
    public poi mapRow(Result result, int rowNum) throws Exception {
        poi o = (poi) BeanUtil.mapRow(result, poi.class);
        o.setRowName(Bytes.toString(result.getRow()));
        return o;
    }


}
