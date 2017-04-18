package com.chinagreentown.dmp.service.Impl;

import com.chinagreentown.dmp.Cache.SystemCache;
import com.chinagreentown.dmp.pojo.ComInfoPojo.com;
import com.chinagreentown.dmp.pojo.UserInfo;
import com.chinagreentown.dmp.pojo.UsrBasAttrPojo.attr;
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
    public Object getUsrLabelInfo(String date) throws NoSuchFieldException, JSONException, IllegalAccessException {
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(date));
        filterList.addFilter(rf);
        FilterList filterList1 = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("attr".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, "83dbaf4d37d7e9befa22fa7ec4f76b8e".getBytes());
        SingleColumnValueFilter singleColumnValueFilter1 = new SingleColumnValueFilter("attr".getBytes(), "encryption_tel".getBytes(), CompareFilter.CompareOp.EQUAL, "410032cd76e051b0faf1d1342076335a".getBytes());
        filterList1.addFilter(singleColumnValueFilter);
        filterList1.addFilter(singleColumnValueFilter1);
        filterList.addFilter(filterList1);
        return basequery.getUserAttr("attr", filterList1);
    }

    @Override
    //遍历里面的所有属性,获取通信信息对象
    public Map<String, Object> getComMapDTO(com comenity) throws NoSuchFieldException, JSONException, IllegalAccessException {
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
        return objectObjectHashMap;

    }

    @Override
    public Map<String, Object> getConMapDTO(com comEnity) throws JSONException {
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
        return returnMap;
    }

    @Override
    public Map<String, Object> getUserAttrDTO(attr attrEnity) throws JSONException {
        HashMap<String, Object> returnMap = Maps.newHashMap();
        String age = attrEnity.getAge();
        String gender = attrEnity.getGender();
        String district = attrEnity.getDistrict();
        Map<String, String> ageMap = SystemCache.getInstance().getUsrBasMap(age);
        returnMap.put(age, BeanUtil.jsonMap2map(ageMap));
        Map<String, String> genderMap = SystemCache.getInstance().getUsrBasMap(gender);
        returnMap.put(gender, BeanUtil.jsonMap2map(genderMap));
        Map<String, String> districtMap = SystemCache.getInstance().getUsrBasMap(district);
        returnMap.put(district, BeanUtil.jsonMap2map(districtMap));
        return returnMap;
    }

    @Override
    public Map<String, Object> getUsrPoiInfoLive(poi poiEnity) {
        String p_live = poiEnity.getP_live();
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("p_live", p_live);
        return map;
    }

    @Override
    public Map<String, Object> getUsrPoiInfoWork(poi poiEnity) {
        String p_live = poiEnity.getP_work();
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("p_work", p_live);
        return map;
    }


}
