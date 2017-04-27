package com.chinagreentown.dmp.Mapper;

import com.chinagreentown.dmp.api.RowMapper;
import com.chinagreentown.dmp.pojo.este_info.assc;
import com.chinagreentown.dmp.pojo.este_info.bas;
import com.chinagreentown.dmp.util.BeanUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by yun on 2017/4/25.
 */
public class esateInfoAsscMapper implements RowMapper<assc> {
    @Override
    public assc mapRow(Result result, int rowNum) throws Exception {
        assc o = (assc) BeanUtil.mapRow(result, bas.class);
        o.setRowName(Bytes.toString(result.getRow()));
        return o;
    }
}
