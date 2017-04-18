package com.chinagreentown.dmp.util;

import com.chinagreentown.dmp.HbaseDemo;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.UsrCNetBhvrPojo;
import com.chinagreentown.dmp.pojo.UsrCNetBhvrPojo.bhvr;
import com.google.common.collect.Maps;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yun on 2017/4/17.
 *
 * @description 对象实例转换的方法
 */
public class BeanUtil {

    public static Object mapRow(Result result, Class<?> beanClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Object obj = UsrCNetBhvrPojo.class.getConstructor(UsrCNetBhvrPojo.class).newInstance(beanClass);
        Object obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            //据说能提升性能
            field.setAccessible(true);
            String[] split = beanClass.getName().split("\\.");
            //最后一个类名就是他的family 列名
            field.set(obj, Bytes.toString(result.getValue(split[split.length - 1].getBytes(), field.getName().getBytes())));
        }
        return obj;
    }

    //一层map
    public static Map<String, Object> json2Map(JSONObject jsonMap, Map<String, Object> returnMap) throws JSONException {
        Iterator<String> it = jsonMap.keys();
        while (it.hasNext()) {
            String inkey = (String) it.next();
            returnMap.put(inkey, jsonMap.getString(inkey));
        }
        return returnMap;
    }

    //2层map
    public static Map<String, Map<String, String>> json2DoubleMap(JSONObject jsonMap, Map<String, Map<String, String>> map) throws JSONException {
        Iterator<String> it = jsonMap.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            JSONObject injson = jsonMap.getJSONObject(key);
            Iterator<String> init = injson.keys();
            Map<String, String> inmap = Maps.newHashMap();
            while (init.hasNext()) {
                String inkey = (String) init.next();
                inmap.put(inkey, injson.getString(inkey));
            }
            map.put(key, inmap);
        }
        return map;
    }


    public static void main(String[] args) throws Exception {
        HTable table = HbaseDemo.getHTableByTableName("usr_c_net_bhvr");
        Get get = new Get("092233705444412596430a1220170328".getBytes());
        Result res = table.get(get);
        bhvr o = (bhvr) mapRow(res, bhvr.class);
        System.out.println(o.getPortal());

    }

}
