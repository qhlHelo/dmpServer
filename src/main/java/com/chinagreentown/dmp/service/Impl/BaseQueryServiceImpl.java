package com.chinagreentown.dmp.service.Impl;

import com.chinagreentown.dmp.Mapper.*;
import com.chinagreentown.dmp.api.HbaseTemplate;
import com.chinagreentown.dmp.pojo.ComInfoPojo.com;
import com.chinagreentown.dmp.pojo.UsrBasAttrPojo.attr;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.bhvr;
import com.chinagreentown.dmp.pojo.UsrPoiInfoPojo.poi;
import com.chinagreentown.dmp.pojo.este_info.assc;
import com.chinagreentown.dmp.service.BaseQueryService;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yun on 2017/4/18.
 */
@Service
public class BaseQueryServiceImpl implements BaseQueryService {


    @Autowired
    private HbaseTemplate hbaseservice;
    //通信信息表明
    private final static String COMINFO = "com_info";
    //usr_poi_info 用户位置信息基础表
    private final static String USRPOI = "usr_poi_info";
    //attr  表
    private final static String USERATTR = "usr_bas_attr";
    //net爱好
    private final static String usr_c_net_bhvr = "usr_c_net_bhvr";
    //楼盘表
    private final static String este_info = "este_info";

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

    @Override
    public List<poi> getUsrPoiInfo(String family, String column, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        scan.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
        List<poi> pois = hbaseservice.find(USRPOI, scan, new UsrPoiInfoMapper());
        return pois;
    }

    @Override
    public List<attr> getUserAttr(String startrow, String family, FilterList list) {
        Scan scan = new Scan(startrow.getBytes());
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        List<attr> attrs = hbaseservice.find(USERATTR, scan, new UsrBasAttrMapper());
        return attrs;
    }

    @Override
    public List<attr> getUserAttr(String family, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        List<attr> attrs = hbaseservice.find(USERATTR, scan, new UsrBasAttrMapper());
        return attrs;
    }


    @Override
    public List<bhvr> getUsrBhvr(String family, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes(family));
        List<bhvr> bhvrs = hbaseservice.find(usr_c_net_bhvr, scan, new UsrCNetBhvrMapper());
        return bhvrs;
    }

    @Override
    public String getFirstData(String tableName) {
        PageFilter pageFilter = new PageFilter(1);
        Scan scan = new Scan();
        scan.setFilter(pageFilter);
        List<String> strings = hbaseservice.find(tableName, scan, new RowNameMapper());
        if (null != strings) {
            return strings.get(0);
        }
        return "";
    }

    @Override
    public List<assc> getEsateAssc(String esateCode, FilterList list) {
        Scan scan = new Scan();
        scan.setFilter(list);
        scan.addFamily(Bytes.toBytes("assc"));
        List<assc> asscs = hbaseservice.find(este_info, scan, new esateInfoAsscMapper());
        return asscs;
    }


}
