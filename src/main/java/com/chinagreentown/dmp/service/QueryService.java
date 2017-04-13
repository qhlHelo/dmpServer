package com.chinagreentown.dmp.service;

import com.chinagreentown.dmp.api.HbaseTemplate;
import com.chinagreentown.dmp.pojo.UserInfo;
import com.chinagreentown.dmp.pojo.datacenter.NetBehavior;
import com.chinagreentown.dmp.pojo.PeopleDto;
import com.chinagreentown.dmp.pojo.datacenter.UserBaseInfo;
import com.chinagreentown.dmp.util.FakeData;
import com.google.common.collect.Lists;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    /**
     * @description 返回用户数据
     */
    public UserBaseInfo getUserBaseInfo(String phonenum) {
        //设置基本属性
        UserBaseInfo userInfo = new UserBaseInfo();
        userInfo.setAge(FakeData.getAge(phonenum));
        userInfo.setLive(FakeData.getlive(phonenum));
        userInfo.setPhoneBrand(FakeData.getPhoneBand(phonenum));
        userInfo.setSex(FakeData.getSex(phonenum));
        userInfo.setWork(FakeData.getwork(phonenum));
        userInfo.setPhonenum(FakeData.getMa5Phone(phonenum));
        userInfo.setNetBehavior(this.getNetBehavior(phonenum));
        return userInfo;
    }


    public UserInfo getuUerInfo(String phonenum) {
        //设置基本属性
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(FakeData.getAge(phonenum));
        userInfo.setLive(FakeData.getlive(phonenum));
        userInfo.setPhoneBrand(FakeData.getPhoneBand(phonenum));
        userInfo.setSex(FakeData.getSex(phonenum));
        userInfo.setWork(FakeData.getwork(phonenum));
        userInfo.setPhonenum(FakeData.getMa5Phone(phonenum));
        userInfo.setNetBehavior(this.getNetBehavior(phonenum));
        userInfo.setMarriage(FakeData.getmarriage(phonenum));
        userInfo.setHascar(FakeData.gethascar(phonenum));
        userInfo.setBear(FakeData.getBear(phonenum));
        return userInfo;
    }

    public NetBehavior getNetBehavior(String phonenum) {
        Long l = Long.valueOf(phonenum) % 1137;
        int i = l.intValue();
        NetBehavior netBehavior = new NetBehavior();
        netBehavior.setEducation(FakeData.getPv(i));
        netBehavior.setEntertainment(FakeData.getPv(i + 8));
        netBehavior.setFinance(FakeData.getPv(i + 4));
        netBehavior.setGame(FakeData.getPv(i + 3));
        netBehavior.setLife(FakeData.getPv(i + 2));
        netBehavior.setMusic(FakeData.getPv(i + i));
        netBehavior.setOther(FakeData.getPv(i + 7));
        netBehavior.setPolitical(FakeData.getPv(i + 11));
        netBehavior.setPortal(FakeData.getPv(i + 22));
        netBehavior.setRead(FakeData.getPv(i + 57));
        netBehavior.setShopping(FakeData.getPv(i + 123));
        netBehavior.setSports(FakeData.getPv(i + 45));
        netBehavior.setTechnology(FakeData.getPv(i + 45));
        netBehavior.setVideo(FakeData.getPv(i + 25));
        return netBehavior;
    }



}
