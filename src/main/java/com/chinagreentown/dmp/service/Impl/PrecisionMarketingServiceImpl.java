package com.chinagreentown.dmp.service.Impl;

import com.chinagreentown.dmp.Cache.SystemCache;
import com.chinagreentown.dmp.pojo.ComInfoPojo.com;
import com.chinagreentown.dmp.pojo.UsrPoiInfoPojo.poi;
import com.chinagreentown.dmp.service.BaseQueryService;
import com.chinagreentown.dmp.service.PrecisionMarketingService;
import com.chinagreentown.dmp.util.BeanUtil;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.hadoop.hbase.filter.*;
import org.codehaus.jackson.map.Serializers;
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
    public Object getUsrLabelInfo(String date) throws NoSuchFieldException, JSONException, IllegalAccessException {
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(date));
        filterList.addFilter(rf);
        com com = basequery.getUsrCom("com", filterList).get(0);
        return getConMapDTO(com);
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


}
