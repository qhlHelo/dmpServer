package com.chinagreentown.dmp.sample;

import com.chinagreentown.dmp.api.HbaseTemplate;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.hadoop.hbase.client.Mutation;

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
        List<PeopleDto> dtos = this.hbaseTemplate.find("people_table", scan, new PeopleRowMapper());
        return dtos;
    }

    public PeopleDto query(String row) {
        PeopleDto dto = this.hbaseTemplate.get("people_table", row, new PeopleRowMapper());
        return dto;
    }

    public void saveOrUpdates() {
        List<Mutation> puts = new ArrayList<>();
        // 设值
        this.hbaseTemplate.saveOrUpdates("people_table", puts);
    }

    public void saveOrUpdate() {
        Mutation delete = new Delete(Bytes.toBytes(""));
        this.hbaseTemplate.saveOrUpdate("people_table", delete);
    }
}
