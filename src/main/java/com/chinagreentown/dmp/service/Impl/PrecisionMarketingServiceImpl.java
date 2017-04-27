package com.chinagreentown.dmp.service.Impl;

import com.chinagreentown.dmp.Cache.SystemCache;
import com.chinagreentown.dmp.pojo.UsrBasAttrPojo.attr;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.bhvr;
import com.chinagreentown.dmp.pojo.UsrPoiInfoPojo.poi;
import com.chinagreentown.dmp.pojo.este_info.assc;
import com.chinagreentown.dmp.service.BaseQueryService;
import com.chinagreentown.dmp.service.PrecisionMarketingService;
import com.chinagreentown.dmp.util.BeanUtil;
import com.chinagreentown.dmp.util.FakeData;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by yun on 2017/4/17.
 *
 * @description
 */
@Service
public class PrecisionMarketingServiceImpl implements PrecisionMarketingService {

    @Autowired
    private BaseQueryService basequery;

    @Override
    public Map<String, Object> getUsrLabelInfo(String date, String phoneNum) throws NoSuchFieldException, JSONException, IllegalAccessException {
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(date));
        filterList.addFilter(rf);
        ByteArrayComparable comparator = new RegexStringComparator(phoneNum);
        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("attr".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, comparator);
        filterList.addFilter(singleColumnValueFilter);
        List<attr> attrs = basequery.getUserAttr("attr", filterList);//获取用户基本信息列表
        //通信信息过滤器
        FilterList comFilters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        comFilters.addFilter(rf);
        SingleColumnValueFilter comColumnFilter = new SingleColumnValueFilter("com".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, comparator);
        comFilters.addFilter(comColumnFilter);
        List<com.chinagreentown.dmp.pojo.ComInfoPojo.com> usrComs = basequery.getUsrCom("com", comFilters);//获取通信信息数据
        //位置信息过滤器
        FilterList locationFlilters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        locationFlilters.addFilter(rf);
        SingleColumnValueFilter locationFilter = new SingleColumnValueFilter("poi".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, comparator);
        locationFlilters.addFilter(locationFilter);
        List<poi> pois = basequery.getUsrPoiInfo("poi", locationFlilters);//获取位置信息数据
        //应用偏好过滤器
        FilterList cNetFlilters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        cNetFlilters.addFilter(rf);
        SingleColumnValueFilter cNetFilter = new SingleColumnValueFilter("bhvr".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, comparator);
        cNetFlilters.addFilter(cNetFilter);
        List<bhvr> bhvr = basequery.getUsrBhvr("bhvr", cNetFlilters);
        Map<String, Object> usrCnetBehvr = this.getUsrCnetBehvr(bhvr);
        //用户信息转换为dto
        Map<String, Object> userAttrDTO = this.getUserAttrDTO(attrs);
        Map<String, Object> conMapDTO = this.getConMapDTO(usrComs);
        Map<String, Object> comMapDTO = this.getComMapDTO(usrComs);
        Map<String, Object> usrPoiInfoLive = this.getUsrPoiInfoLive(pois);
        Map<String, Object> usrPoiInfoWork = this.getUsrPoiInfoWork(pois);
        Set<String> tels = userAttrDTO.keySet();
        HashMap<String, Object> returnMap = Maps.newHashMap();
        for (String str : tels) {
            HashMap<String, Object> usrMap = Maps.newHashMap();
//            Map<String, Object> stringObjectMap = (Map<String, Object>) userAttrDTO.get(str);
            Object com = comMapDTO.get(str);
            if (null != com) {
                usrMap.put("COM", com);
            }
            Object con = conMapDTO.get(str);
            if (null != con) {
                usrMap.put("CON", con);
            }
            Map<String, Object> usrBasMap = (Map<String, Object>) userAttrDTO.get(str);
            Map<? extends String, ?> livemap = (Map<? extends String, ?>) usrPoiInfoLive.get(str);
            if (null != livemap && livemap.isEmpty()) {
                usrBasMap.putAll((Map<? extends String, ?>) livemap);
            }
            Map<? extends String, ?> workmap = (Map<? extends String, ?>) usrPoiInfoWork.get(str);
            if (null != workmap) {
                usrBasMap.putAll((Map<? extends String, ?>) workmap);
            }
            usrMap.put("BAS", usrBasMap);
            usrMap.put("netBehavior", usrCnetBehvr.get(str));
            returnMap.put(str, usrMap);
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> getLivePlace(String date, String phoneNum, String bdaccount) {
        RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(date));
        FilterList locationFlilters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        locationFlilters.addFilter(rf);
        if (phoneNum != null) {
            ByteArrayComparable comparator = new RegexStringComparator(phoneNum);
            SingleColumnValueFilter locationFilter = new SingleColumnValueFilter("poi".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, comparator);
            locationFlilters.addFilter(locationFilter);
        }
        if (bdaccount != null) {
            ByteArrayComparable comparator = new RegexStringComparator(bdaccount);
            SingleColumnValueFilter locationFilter = new SingleColumnValueFilter("poi".getBytes(), "bd_account".getBytes(), CompareFilter.CompareOp.EQUAL, comparator);
            locationFlilters.addFilter(locationFilter);
        }

        List<poi> pois = basequery.getUsrPoiInfo("poi", "p_live", locationFlilters);//获取位置信息数据
        return this.getUsrPoiInfoLive(pois);
    }

    @Override
    public Map<String, Object> getWorkPlace(String date, String phoneNum, String bdaccount) {
        RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(date));
        FilterList locationFlilters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        locationFlilters.addFilter(rf);
        if (phoneNum != null) {
            ByteArrayComparable comparator = new RegexStringComparator(phoneNum);
            SingleColumnValueFilter locationFilter = new SingleColumnValueFilter("poi".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, comparator);
            locationFlilters.addFilter(locationFilter);
        }
        if (bdaccount != null) {
            ByteArrayComparable comparator = new RegexStringComparator(bdaccount);
            SingleColumnValueFilter locationFilter = new SingleColumnValueFilter("poi".getBytes(), "bd_account".getBytes(), CompareFilter.CompareOp.EQUAL, comparator);
            locationFlilters.addFilter(locationFilter);
        }
        List<poi> pois = basequery.getUsrPoiInfo("poi", "p_work", locationFlilters);//获取位置信息数据
        return this.getUsrPoiInfoWork(pois);
    }


    @Override
    public Object getEsateMicro(String date, String esateCode) {
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(date));
        filterList.addFilter(rf);
        SubstringComparator cpmparator = new SubstringComparator("wu");
        new SingleColumnValueExcludeFilter("attr".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, cpmparator);
        return null;
    }

    @Override
    //遍历里面的所有属性,获取通信信息对象,以手机号为key返回出去，便于存取
    public Map<String, Object> getComMapDTO(List<com.chinagreentown.dmp.pojo.ComInfoPojo.com> comenitys) throws NoSuchFieldException, JSONException, IllegalAccessException {
        HashMap<String, Object> ReturnMap = Maps.newHashMap();
        for (com.chinagreentown.dmp.pojo.ComInfoPojo.com comenity : comenitys) {
            Field[] fields = com.chinagreentown.dmp.pojo.ComInfoPojo.com.class.getDeclaredFields();
            Map<String, Object> objectObjectHashMap = Maps.newHashMap();
            for (Field filed : fields) {
                filed.setAccessible(true);
                Object f = filed.get(comenity);
                if (f != null) {
                    String s = String.valueOf(f);
                    if (null != s) {
                        Map<String, String> map = SystemCache.getInstance().getComMap(s);
                        if (null != map && !map.isEmpty()) {
                            Set<String> strs = map.keySet();
                            int size = strs.size();
                            //取出第一个
                            String max = map.get("0" + size);
                            Map<String, Object> ObjectMap = Maps.newHashMap();
                            BeanUtil.json2Map(new JSONObject(max), ObjectMap);
                            //取出最后一个
                            String min = map.get("0" + 1);
                            BeanUtil.json2Map(new JSONObject(min), ObjectMap);
                            objectObjectHashMap.put(s, ObjectMap);
                        }
                    }
                }
            }
            ReturnMap.put(comenity.getEncryption_tel(), objectObjectHashMap);
        }
        return ReturnMap;

    }

    @Override
    public Map<String, Object> getConMapDTO(List<com.chinagreentown.dmp.pojo.ComInfoPojo.com> comEnitys) throws JSONException {
        HashMap<String, Object> ReturnMap = Maps.newHashMap();
        for (com.chinagreentown.dmp.pojo.ComInfoPojo.com comEnity : comEnitys) {
            Map<String, Object> returnMap = Maps.newHashMap();
            if (null != comEnity) {
                String tel_cost = comEnity.getTel_cost();
                if (comEnity != null) {
                    Map<String, String> map = SystemCache.getInstance().getConMap(tel_cost);
                    Set<String> strings = map.keySet();
                    int size = strings.size();
                    Map<String, Object> ObjectMap = Maps.newHashMap();
                    String max = map.get("0" + size);
                    BeanUtil.json2Map(new JSONObject(max), ObjectMap);
                    String min = map.get("0" + 1);
                    BeanUtil.json2Map(new JSONObject(min), ObjectMap);
                    returnMap.put(tel_cost, ObjectMap);
                }
            }
            ReturnMap.put(comEnity.getEncryption_tel(), returnMap);
        }
        return ReturnMap;
    }

    @Override
    public Map<String, Object> getUserAttrDTO(List<attr> attrEnitys) throws JSONException {
        Map<String, Object> ReturnMap = Maps.newLinkedHashMap();
        for (attr attrEnity : attrEnitys) {
            HashMap<String, Object> returnMap = Maps.newHashMap();
            String age = attrEnity.getAge();
            String gender = attrEnity.getGender();
            String district = attrEnity.getDistrict();
            String rowName = attrEnity.getRowName();
            if (age != null) {
                Map<String, String> ageMap = SystemCache.getInstance().getUsrBasMap(age);
                returnMap.put(age, BeanUtil.jsonMap2map(ageMap));
            }
            if (gender != null) {
                Map<String, String> genderMap = SystemCache.getInstance().getUsrBasMap(gender);
                returnMap.put(gender, BeanUtil.jsonMap2map(genderMap));
            }
            if (district != null) {
                Map<String, String> districtMap = SystemCache.getInstance().getUsrBasMap(district);
                returnMap.put(district, BeanUtil.jsonMap2map(districtMap));
            }
            if (rowName != null) {
                returnMap.put("rowName", rowName);
            }
            ReturnMap.put(attrEnity.getEncryption_tel(), returnMap);
        }
        return ReturnMap;
    }

    @Override
    public Map<String, Object> getUsrPoiInfoLive(List<poi> poiEnitys) {
        HashMap<String, Object> ReturnMap = Maps.newHashMap();
        for (poi poiEnity : poiEnitys) {
            String p_live = poiEnity.getP_live();
            HashMap<String, Object> map = Maps.newHashMap();
            map.put("p_live", p_live);
            ReturnMap.put(poiEnity.getEncryption_tel(), map);
        }
        return ReturnMap;
    }

    @Override
    public Map<String, Object> getUsrPoiInfoWork(List<poi> poiEnitys) {
        HashMap<String, Object> ReturnMap = Maps.newHashMap();
        for (poi poiEnity : poiEnitys) {
            String p_live = poiEnity.getP_work();
            HashMap<String, Object> map = Maps.newHashMap();
            map.put("p_work", p_live);
            ReturnMap.put(poiEnity.getEncryption_tel(), map);
        }
        return ReturnMap;
    }

    @Override
    public Map<String, Object> getUsrCnetBehvr(List<bhvr> bhvrs) throws IllegalAccessException, JSONException {
        HashMap<String, Object> ReturnMap = Maps.newHashMap();
        for (bhvr bhvrEnity : bhvrs) {
            HashMap<String, Object> cNetMap = Maps.newHashMap();
            Field[] fields = bhvr.class.getDeclaredFields();
            for (Field filed : fields) {
                filed.setAccessible(true);
                Object f = filed.get(bhvrEnity);
                if (null != f) {
                    String s = f.toString();
                    //手机号就不解析
                    if (!filed.getName().equals("encryption_tel")) {
                        Map<String, Map<String, String>> stringMapMap = BeanUtil.json2DoubleMap(new JSONObject(s), Maps.newHashMap());
                        Set<String> key1s = stringMapMap.keySet();
                        //次数 为key 解析 出里面的 编码
                        for (String times : key1s) {
                            Map<String, String> inmap = stringMapMap.get(times);
                            Set<String> keys2 = inmap.keySet();
                            //定义编码key
                            String key = null;
                            Map<String, Object> stringObjectMap = Maps.newHashMap();
                            for (String times2 : keys2) {
                                String s1 = inmap.get(times2);
                                //解析出第一个 候最后一个编码
                                Map<String, String> usrCNetbhvr = SystemCache.getInstance().getUsrCNetbhvr(times2);
                                stringObjectMap = BeanUtil.jsonMap2map(usrCNetbhvr);
                                stringObjectMap.put("pv", s1);
                                if (key == null && null != usrCNetbhvr && !usrCNetbhvr.isEmpty()) {
                                    //01 位  第一个编码  ，从里面取出来他的json数据作为key
                                    if (usrCNetbhvr.containsKey("01")) {
                                        key = new JSONObject(usrCNetbhvr.get("01")).keys().next().toString();
                                    }
                                }
                            }
                            if (null != key) {
                                cNetMap.put(key, stringObjectMap);
                            }
                        }
                    }
                }
            }
            ReturnMap.put(bhvrEnity.getEncryption_tel(), cNetMap);
        }
        return ReturnMap;
    }

    @Override
    public Map<String, Object> getUsrFixBhvr(List<com.chinagreentown.dmp.pojo.UsrFixNetBhvr.bhvr> Gbhvrs) throws IllegalAccessException, JSONException {
        HashMap<String, Object> ReturnMap = Maps.newHashMap();
        for (com.chinagreentown.dmp.pojo.UsrFixNetBhvr.bhvr gbhvrEnity : Gbhvrs) {
            HashMap<String, Object> cNetMap = Maps.newHashMap();
            Field[] fields = bhvr.class.getDeclaredFields();
            for (Field filed : fields) {
                filed.setAccessible(true);
                Object f = filed.get(gbhvrEnity);
                if (null != f) {
                    String s = f.toString();
                    //手机号就不解析
                    if (!filed.getName().equals("encryption_tel")) {
                        Map<String, Map<String, String>> stringMapMap = BeanUtil.json2DoubleMap(new JSONObject(s), Maps.newHashMap());
                        Set<String> key1s = stringMapMap.keySet();
                        //次数 为key 解析 出里面的 编码
                        for (String times : key1s) {
                            Map<String, String> inmap = stringMapMap.get(times);
                            Set<String> keys2 = inmap.keySet();
                            //定义编码key
                            String key = null;
                            Map<String, Object> stringObjectMap = Maps.newHashMap();
                            for (String times2 : keys2) {
                                String s1 = inmap.get(times2);
                                //解析出第一个 候最后一个编码
                                Map<String, String> usrGNetbhvr = SystemCache.getInstance().getUsrFixNetbhvr(times2);
                                stringObjectMap = BeanUtil.jsonMap2map(usrGNetbhvr);
                                stringObjectMap.put("pv", s1);
                                if (key == null && null != usrGNetbhvr && !usrGNetbhvr.isEmpty()) {
                                    //01 位  第一个编码  ，从里面取出来他的json数据作为key
                                    if (usrGNetbhvr.containsKey("01")) {
                                        key = new JSONObject(usrGNetbhvr.get("01")).keys().next().toString();
                                    }
                                }
                            }
                            if (null != key) {
                                cNetMap.put(key, stringObjectMap);
                            }
                        }
                    }
                }
            }
            ReturnMap.put(gbhvrEnity.getEncryption_tel(), cNetMap);
        }
        return ReturnMap;
    }

    @Override
    public Map<String, Object> getEsateUsersPOIWork(String date, String esateCode) {
        Map<String, Object> returnMap = Maps.newHashMap();
        String houseKey = FakeData.getHouseKey(esateCode);
        if (houseKey == null) {
            return Maps.newHashMap();
        }
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(date));
        filterList.addFilter(rf);
        List<assc> esateAssc = basequery.getEsateAssc(esateCode, filterList);
        Map<String, Object> ownerpoimap = Maps.newHashMap();
        Map<String, Object> internmap = Maps.newHashMap();
        for (assc assc : esateAssc) {
            if (assc.getBuy_cus() != null) {
                String buy_cus = assc.getBuy_cus();
                Map<String, Object> workPlace = this.getWorkPlace(date, buy_cus, null);
                ownerpoimap.putAll(workPlace);
                continue;
            } else if (assc.getIntention_tel() != null) {
                String intention_tel = assc.getIntention_tel();
                Map<String, Object> workPlace = this.getWorkPlace(date, intention_tel, null);
                internmap.putAll(workPlace);
                continue;
            } else if (assc.getIntention_bd() != null) {
                String intention_bd = assc.getIntention_bd();
                Map<String, Object> workPlace = this.getWorkPlace(date, null, intention_bd);
                internmap.putAll(workPlace);
                continue;
            }
        }
        returnMap.put("owner", ownerpoimap);
        returnMap.put("intentUser", internmap);
        return returnMap;
    }

    @Override
    public Map<String, Object> getEsateUserPOILive(String date, String esateCode) {
        Map<String, Object> returnMap = Maps.newHashMap();
        String houseKey = FakeData.getHouseKey(esateCode);
        if (houseKey == null) {
            return Maps.newHashMap();
        }
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(date));
        filterList.addFilter(rf);
        List<assc> esateAssc = basequery.getEsateAssc(esateCode, filterList);
        Map<String, Object> ownerpoimap = Maps.newHashMap();
        Map<String, Object> internmap = Maps.newHashMap();
        for (assc assc : esateAssc) {
            if (assc.getBuy_cus() != null) {
                //如果是成交用户跳出
                String buy_cus = assc.getBuy_cus();
                Map<String, Object> workPlace = this.getLivePlace(date, buy_cus, null);
                ownerpoimap.putAll(workPlace);
                continue;
            } else if (assc.getIntention_tel() != null) {
                //如果是意向用户手机号跳出
                String intention_tel = assc.getIntention_tel();
                Map<String, Object> workPlace = this.getLivePlace(date, intention_tel, null);
                internmap.putAll(workPlace);
                continue;
            } else if (assc.getIntention_bd() != null) {
                //如果是意向用户宽带账号 跳出
                String intention_bd = assc.getIntention_bd();
                Map<String, Object> workPlace = this.getLivePlace(date, null, intention_bd);
                internmap.putAll(workPlace);
                continue;
            }
        }
        returnMap.put("owner", ownerpoimap);
        returnMap.put("intentUser", internmap);
        return returnMap;
    }

    @Override
    public Map<String, Object> getRecentlyUserLabel(int pageSize, int pageIndex, String Date, String lastKey) throws IllegalAccessException, NoSuchFieldException, JSONException {
        Map<String, Object> objectObjectHashMap = Maps.newHashMap();
        if (Date == null) {
            String usr_bas_attr = basequery.getFirstData("usr_bas_attr");
            if (usr_bas_attr.isEmpty()) {
                return objectObjectHashMap;
            }
            Date = usr_bas_attr.substring(usr_bas_attr.length() - 8);
        }
        if (lastKey != null) {
            //就从情面一页的最后一行开始
            pageSize++;
        }
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        PageFilter pageFilter = new PageFilter(pageSize);
        RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(Date));
        filterList.addFilter(rf);
        filterList.addFilter(pageFilter);
        List<attr> attrs = null;
        if (lastKey != null) {
            //如果多出来的就会多出一行
            attrs = basequery.getUserAttr(lastKey, "attr", filterList);
            if (null != attrs && attrs.size() >= 1) {
                attrs.remove(0);
            }
        } else {
            attrs = basequery.getUserAttr("attr", filterList);
        }
        if (attrs != null) {
            String phones = "";
            for (attr attr : attrs) {
                phones = phones + attr.getEncryption_tel() + "|";

            }
            if (!phones.isEmpty()) {
                phones = phones.substring(0, phones.length() - 1);
                objectObjectHashMap = (HashMap<String, Object>) this.getUsrLabelInfo(Date, phones);
            }
        }
        return objectObjectHashMap;
    }


}
