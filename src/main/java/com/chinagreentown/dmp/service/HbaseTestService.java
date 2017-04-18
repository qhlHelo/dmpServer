package com.chinagreentown.dmp.service;

import com.chinagreentown.dmp.Mapper.PeopleRowMapper;
import com.chinagreentown.dmp.api.HbaseTemplate;
import com.chinagreentown.dmp.pojo.PeopleDto;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yun on 2017/4/15.
 */
@Service
public class HbaseTestService {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    public Object test() {
        Scan scan = new Scan("asd".getBytes(), "zyy1".getBytes());
        scan.setCaching(1);
//        scan.addFamily("score".getBytes());
////        scan.addColumn("chinese".getBytes());

        RowFilter rf = null;
        FamilyFilter ff=null;
        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("info".getBytes(), "age".getBytes(), CompareFilter.CompareOp.EQUAL, "33".getBytes());
//        ByteArrayComparable rowComparator = new BinaryComparator(Bytes.toBytes("liming5"));
//        RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, rowComparator);
//        Filter rf = new PrefixFilter(Bytes.toBytes("liming"));//不区分大小写
//        rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("li"));
        scan.setFilter(singleColumnValueFilter);
        List<PeopleDto> t_user = this.hbaseTemplate.find("t_user", scan, new PeopleRowMapper());
//       this.hbaseTemplate.find("t_user","",new PeopleRowMapper())
        return t_user;


    }

}
