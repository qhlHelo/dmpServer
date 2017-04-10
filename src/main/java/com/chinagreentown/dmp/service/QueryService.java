package com.chinagreentown.dmp.service;

import com.chinagreentown.dmp.api.HbaseTemplate;
import com.chinagreentown.dmp.pojo.PeopleDto;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 2017/4/8.
 */

@Service
public class QueryService {
    @Autowired
    private HbaseTemplate hbaseTemplate;

    public List<PeopleDto> query(String startRow, String stopRow) {
        Scan scan = new Scan(Bytes.toBytes(startRow), Bytes.toBytes(stopRow));
        scan.setCaching(5000);
        List<PeopleDto> dtos = this.hbaseTemplate.find("t_user", scan, new PeopleRowMapper());
        return dtos;
    }

    public PeopleDto query(String row) {
       return this.hbaseTemplate.get("t_user", row, new PeopleRowMapper());
    }

//    public List<PeopleDto> search() {
////        return this.hbaseTemplate.get("t_user","liming5","info","age");
//        Scan scan = new Scan();
//        FilterList fl = new FilterList();
//
//        scan.setFilter();
//    }

    public void saveOrUpdates() {
        List<Mutation> puts = new ArrayList<>();
        // 设值
        this.hbaseTemplate.saveOrUpdates("t_user", puts);
    }

    public void saveOrUpdate() {
        Mutation delete = new Delete(Bytes.toBytes(""));
        this.hbaseTemplate.saveOrUpdate("t_user", delete);
    }
}
