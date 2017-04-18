package com.chinagreentown.dmp.service.Impl;

import com.chinagreentown.dmp.Mapper.UsrCNetBhvrMapper;
import com.chinagreentown.dmp.Mapper.UsrComInfoMapper;
import com.chinagreentown.dmp.Mapper.UsrPoiInfoMapper;
import com.chinagreentown.dmp.api.HbaseTemplate;
import com.chinagreentown.dmp.pojo.ComInfoPojo.com;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.bhvr;
import com.chinagreentown.dmp.pojo.UsrPoiInfoPojo.poi;
import com.chinagreentown.dmp.service.BaseQueryService;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.util.Bytes;
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
    //通信信息表明
    private final static String COMINFO = "com_info";
    //usr_poi_info 用户位置信息基础表
    private final static String USRPOI = "com_info";

    @Override
    public List<com> getUsrCom(String family, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        List<com> com_info = hbaseservice.find(COMINFO, scan, new UsrComInfoMapper());
        return com_info;
    }

    @Override
    public List<poi> getUsrPoiInfo(String family, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        List<poi> pois = hbaseservice.find(USRPOI, scan, new UsrPoiInfoMapper());
        return pois;
    }


}
