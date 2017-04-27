package com.chinagreentown.dmp.service;

import com.chinagreentown.dmp.Cache.SystemCache;
import com.chinagreentown.dmp.Mapper.PeopleRowMapper;
import com.chinagreentown.dmp.api.HbaseTemplate;
import com.chinagreentown.dmp.pojo.PeopleDto;
import com.chinagreentown.dmp.pojo.UserInfo;
import com.chinagreentown.dmp.pojo.datacenter.NetBehavior;
import com.chinagreentown.dmp.pojo.datacenter.UserBaseInfo;
import com.chinagreentown.dmp.util.FakeData;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 
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
        userInfo.setPhonenum(phonenum);
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
        userInfo.setPhonenum(phonenum);
        userInfo.setNetBehavior(this.getNetBehavior(phonenum));
        userInfo.setMarriage(FakeData.getmarriage(phonenum));
        userInfo.setHascar(FakeData.gethascar(phonenum));
        userInfo.setBear(FakeData.getBear(phonenum));
        userInfo.setPhoneModel("红米");
        userInfo.setPhonePrice("1234.23");
        userInfo.setPurchasepurpose("投资");
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

    /**
     * @return
     * @description 组织生活坐标数据
     */
    public Map<String, String> getLiveMaps(int code) {
        HashMap<String, String> map = Maps.newHashMap();
        for (int i = 0; i < (code % 3) + 1; i++) {
            String phonenum = FakeData.getPhoneList().get((code + i) % 5);
            String ma5Phone = FakeData.getMa5Phone(phonenum);
            String live = FakeData.getlive(phonenum);
            map.put(ma5Phone, live);
        }
        return map;
    }

    /**
     * @return
     * @description 组织生活坐标数据
     */
    public Map<String, String> getworkMaps(int code) {
        HashMap<String, String> map = Maps.newHashMap();
        for (int i = 0; i < (code % 3) + 2; i++) {
            String phonenum = FakeData.getPhoneList().get((code + i) % 5);
            String ma5Phone = FakeData.getMa5Phone(phonenum);
            String live = FakeData.getwork(phonenum);
            map.put(ma5Phone, live);
        }
        return map;
    }

    public Map<String, Object> getEsatUserLabelMaps(int code) {
        HashMap<String, Object> returnmap = Maps.newHashMap();
        HashMap<String, Object> map1 = Maps.newHashMap();
        ArrayList<Object> lists = Lists.newArrayList();
        for (int i = 0; i < (code % 3) + 2; i++) {
            String phonenum = FakeData.getPhoneList().get((code + i) % 5);
            String ma5Phone = FakeData.getMa5Phone(phonenum);
            if (i % 2 == 0) {
                map1.put(ma5Phone, this.getlabelUserinfo(phonenum));
            }
            lists.add(this.getlabelUserinfo(phonenum));
        }
        returnmap.put("owner", map1);
        returnmap.put("intentUser", lists);
        return returnmap;
    }

    public Map<String, Object> getlabelUserinfo(String phonenum) {
        HashMap<String, Object> returnMap = Maps.newLinkedHashMap();
        HashMap<String, Object> userMap = Maps.newLinkedHashMap();
        userMap.put(FakeData.LabelKey.BASENAME.toString(), FakeData.getname(phonenum));
        userMap.put(FakeData.LabelKey.BASEPHONE.toString(), FakeData.getMa5Phone(phonenum));
        userMap.put(FakeData.LabelKey.BASELIVEAREA.toString(), FakeData.getlive(phonenum));
        userMap.put(FakeData.LabelKey.BASEWORKAREA.toString(), FakeData.getwork(phonenum));
        HashMap<String, Object> arainfo = Maps.newLinkedHashMap();
        String arealabel = FakeData.getArealabel(phonenum);
        arainfo.put(arealabel, FakeData.getAreaname(arealabel));
        userMap.put(arealabel, arainfo);
        HashMap<String, Object> ageinfo = Maps.newLinkedHashMap();
        String ageLabel = FakeData.getAgeLabel(phonenum);
        String age = FakeData.getAge(phonenum);
        ageinfo.put(ageLabel, age);
        userMap.put(ageLabel, ageinfo);
        Map<String, Object> comMap = FakeData.getComMap(phonenum);
        LinkedHashMap<String, Object> con = Maps.newLinkedHashMap();
        String getconlabel = FakeData.getconlabel(phonenum);
        String getcon = FakeData.getcon(phonenum);
        con.put(getconlabel, getcon);
        con.put("GT10CON001", "最近一个月话费");
        LinkedHashMap<String, Object> phone = Maps.newLinkedHashMap();
        String phoneband = FakeData.getPhoneBand(phonenum);
        String phonelabel = FakeData.getPhoneBandLabel(phonenum);
        phone.put(phonelabel, phoneband);
        phone.put("GT04002001TER02", "移动");
        LinkedHashMap<String, Object> netmap = Maps.newLinkedHashMap();
        netmap.put("GT04001003GU001", FakeData.getAssertmap(phonenum));
        netmap.put("GT04002003CU002", FakeData.getSportmap(phonenum));
        returnMap.put(FakeData.LabelKey.BASE.toString(), userMap);
        returnMap.put("GT09COM", comMap);
        returnMap.put(getconlabel, con);
        returnMap.put(phonelabel, phone);
        returnMap.put("netBehavior", netmap);
        return returnMap;
    }

    public Map<String, Object> houseMacrography(int code) {
        HashMap<String, Object> map = Maps.newLinkedHashMap();
        map.put("developer", FakeData.getE(FakeData.devList, code));
        map.put("plate", FakeData.getE(FakeData.placeList, code));
        map.put("GT02001001013", FakeData.getE(FakeData.orientationList, code));
        map.put("location", FakeData.getE(FakeData.locationList, code));
        map.put("GT02001001004001", FakeData.getE(FakeData.unitList, code));
        map.put("GT02001001004002", FakeData.getE(FakeData.totalPrice, code));
        map.put("foucs", FakeData.getE(FakeData.focusList, code));
        map.put("GT02001001005", FakeData.getE(FakeData.vastList, code));
        map.put("GT02001001014", FakeData.getE(FakeData.redecoratList, code));
        map.put("GT02001001007", FakeData.getE(FakeData.floorList, code));
        map.put("GT02001001012", FakeData.getE(FakeData.HouseholdList, code));
        map.put("GT02001001001", FakeData.getE(FakeData.locationList, code));
        map.put("school", FakeData.getE(FakeData.schoolList, code));
        map.put("subway", FakeData.getE(FakeData.subwayList, code));
        ArrayList<String> list = Lists.newArrayList();
        for (int i = 0; i < code % FakeData.specialList.size(); i++) {
            list.add(FakeData.specialList.get((code + i) % FakeData.specialList.size()));
        }
        map.put("GT02001001016", list);
        if (code % 5 == 1) {
            map.put(FakeData.house.house1.getKey(), FakeData.house.house1.getValeu());
        } else if (code % 5 == 2) {
            map.put(FakeData.house.house2.getKey(), FakeData.house.house2.getValeu());
        } else if (code % 5 == 3) {
            map.put(FakeData.house.house3.getKey(), FakeData.house.house3.getValeu());
        } else if (code % 5 == 4) {
            map.put(FakeData.house.house4.getKey(), FakeData.house.house4.getValeu());
        } else {
            map.put(FakeData.house.house5.getKey(), FakeData.house.house5.getValeu());
        }
        return map;
    }


    public static void main(String[] args) {
//        SystemCache
//        QueryService queryService = new QueryService();
//        System.out.println(queryService.houseMacrography(2));
    }
}
