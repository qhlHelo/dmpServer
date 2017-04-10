package com.chinagreentown.dmp.sample;

import com.chinagreentown.dmp.api.RowMapper;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by admin on 2017/4/8.
 */
public class PeopleRowMapper  implements RowMapper<PeopleDto> {
    private static byte[] COLUMNFAMILY = "f".getBytes();
    private static byte[] PHONE = "phone".getBytes();
    private static byte[] AGE = "age".getBytes();

    @Override
    public PeopleDto mapRow(Result result, int rowNum) throws Exception {
        PeopleDto dto = new PeopleDto();
        // TODO: 设置相关的属性值
        String phone = Bytes.toString(result.getValue(COLUMNFAMILY, PHONE));
        int age = Bytes.toInt(result.getValue(COLUMNFAMILY, AGE));
        dto.setPhone(phone);
        dto.setAge(age);
        return dto;
    }
}
