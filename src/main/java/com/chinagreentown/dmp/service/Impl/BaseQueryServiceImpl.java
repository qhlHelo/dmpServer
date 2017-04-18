package com.chinagreentown.dmp.service.Impl;

import com.chinagreentown.dmp.Mapper.UsrCNetBhvrMapper;
import com.chinagreentown.dmp.api.HbaseTemplate;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.bhvr;
import com.chinagreentown.dmp.service.BaseQueryService;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yun on 2017/4/18.
 */
@Service
public class BaseQueryServiceImpl implements BaseQueryService {

    private final static String usr_c_net_bhvr = "usr_c_net_bhvr";

    @Autowired
    private HbaseTemplate hbaseservice;

    @Override
    public List<bhvr> getBhvr(String family) {
        Scan scan = new Scan();
        List<bhvr> bhvrs = hbaseservice.find(usr_c_net_bhvr, family, new UsrCNetBhvrMapper());
        return bhvrs;
    }

    @Override
    public List<bhvr> getBhvr(String family, String qualifier) {
        Scan scan = new Scan();
        List<bhvr> bhvrs = hbaseservice.find(usr_c_net_bhvr, family, qualifier, new UsrCNetBhvrMapper());
        return bhvrs;
    }

    @Override
    public List<bhvr> getBhvr(String family, FilterList list) {
        //过滤器
        Scan scan = new Scan();
        scan.setFilter(list);
        return hbaseservice.find(usr_c_net_bhvr, family, new UsrCNetBhvrMapper());

    }
}
