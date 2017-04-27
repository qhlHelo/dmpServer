package com.chinagreentown.dmp.Mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.ComInfoPojo.com;
import com.chinagreentown.dmp.pojo.PeopleDto;
import com.chinagreentown.dmp.pojo.este_info.bas;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by yun on 2017/4/25.
 */
public class esteInfoBasMapper implements RowMapper<bas> {
    @Override
    public bas mapRow(Result result, int rowNum) throws Exception {
        bas o = (bas) BeanUtil.mapRow(result, bas.class);
        o.setRowName(Bytes.toString(result.getRow()));
        return o;
    }
}
