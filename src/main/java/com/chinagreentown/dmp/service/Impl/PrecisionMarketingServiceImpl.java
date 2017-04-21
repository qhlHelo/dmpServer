package com.chinagreentown.dmp.service.Impl;

import com.chinagreentown.dmp.Cache.SystemCache;
import com.chinagreentown.dmp.pojo.ComInfoPojo.com;
import com.chinagreentown.dmp.pojo.UsrBasAttrPojo.attr;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.bhvr;
import com.chinagreentown.dmp.pojo.UsrPoiInfoPojo.poi;
import com.chinagreentown.dmp.service.BaseQueryService;
import com.chinagreentown.dmp.service.PrecisionMarketingService;
import com.chinagreentown.dmp.util.BeanUtil;
import com.google.common.collect.Maps;
import org.apache.hadoop.hbase.filter.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public Object getUsrLabelInfo(String date, String phoneNum) throws NoSuchFieldException, JSONException, IllegalAccessException {
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(date));
        filterList.addFilter(rf);
        ByteArrayComparable comparator = new RegexStringComparator(BeanUtil.getRegexPhoneNums(phoneNum));
        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("attr".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, comparator);
        filterList.addFilter(singleColumnValueFilter);
        System.out.println("--------------start");
        List<attr> attrs = basequery.getUserAttr("attr", filterList);//获取用户基本信息列表
        System.out.println("--------------stop");
        //通信信息过滤器
        FilterList comFilters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        comFilters.addFilter(rf);
        SingleColumnValueFilter comColumnFilter = new SingleColumnValueFilter("com".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, comparator);
        comFilters.addFilter(comColumnFilter);
        List<com> usrComs = basequery.getUsrCom("com", comFilters);//获取通信信息数据
        //位置信息过滤器
        FilterList locationFlilters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        comFilters.addFilter(rf);
        SingleColumnValueFilter locationFilter = new SingleColumnValueFilter("poi".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, comparator);
        locationFlilters.addFilter(locationFilter);
        List<poi> pois = basequery.getUsrPoiInfo("poi", locationFlilters);//获取位置信息数据
        //应用偏好过滤器
        FilterList cNetFlilters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        comFilters.addFilter(rf);
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
            Map<String, Object> stringObjectMap = (Map<String, Object>) userAttrDTO.get(str);
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
            returnMap.put(str, usrMap);
            returnMap.put("netBehavior", usrCnetBehvr.get(str));
        }

        return returnMap;
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
    public Map<String, Object> getComMapDTO(List<com> comenitys) throws NoSuchFieldException, JSONException, IllegalAccessException {
        HashMap<String, Object> ReturnMap = Maps.newHashMap();
        for (com comenity : comenitys) {
            Field[] fields = com.class.getDeclaredFields();
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
                            String max = map.get("0" + size);
                            Map<String, Object> ObjectMap = Maps.newHashMap();
                            BeanUtil.json2Map(new JSONObject(max), ObjectMap);
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
    public Map<String, Object> getConMapDTO(List<com> comEnitys) throws JSONException {
        HashMap<String, Object> ReturnMap = Maps.newHashMap();
        for (com comEnity : comEnitys) {
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
        HashMap<String, Object> ReturnMap = Maps.newHashMap();
        for (attr attrEnity : attrEnitys) {
            HashMap<String, Object> returnMap = Maps.newHashMap();
            String age = attrEnity.getAge();
            String gender = attrEnity.getGender();
            String district = attrEnity.getDistrict();
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
                    if (!filed.getName().equals("encryption_tel")) {
                        Map<String, Map<String, String>> stringMapMap = BeanUtil.json2DoubleMap(new JSONObject(s), Maps.newHashMap());
                        Set<String> key1s = stringMapMap.keySet();
                        for (String times : key1s) {
                            Map<String, String> inmap = stringMapMap.get(times);
                            Set<String> keys2 = inmap.keySet();
                            String key = null;
                            Map<String, Object> stringObjectMap = Maps.newHashMap();
                            for (String times2 : keys2) {
                                String s1 = inmap.get(times2);
                                Map<String, String> usrCNetbhvr = SystemCache.getInstance().getUsrCNetbhvr(times2);
                                stringObjectMap = BeanUtil.jsonMap2map(usrCNetbhvr);
                                stringObjectMap.put("pv", s1);
                                if (key == null && null != usrCNetbhvr && !usrCNetbhvr.isEmpty()) {
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


}
