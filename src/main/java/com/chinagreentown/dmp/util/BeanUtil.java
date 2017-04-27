package com.chinagreentown.dmp.util;

import com.chinagreentown.dmp.HbaseDemo;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String[] split = beanClass.getName().split("\\.");
        byte[] name = split[split.length - 1].getBytes();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            //据说能提升性能
            field.setAccessible(true);
            //最后一个类名就是他的family 列名
//            if(field.getName().equals("age")){
//                byte[] value = result.getValue(name, "age".getBytes());
//                System.out.println(Bytes.toString(value));
//            }
            String value = Bytes.toString(result.getValue(name, field.getName().getBytes()));
            if (null != value && !value.equals("NULL")) {
                field.set(obj, value);
            }
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

    //value 为json 的转换为一个map,取最低和最高的
    public static Map<String, Object> jsonMap2map(Map<String, String> map) throws JSONException {
        if (map == null) {
            return Maps.newHashMap();
        }
        Map<String, Object> ObjectMap = Maps.newHashMap();
        Set<String> strings = map.keySet();
        int size = strings.size();
        String max = map.get("0" + size);
        BeanUtil.json2Map(new JSONObject(max), ObjectMap);
        String min = map.get("0" + 1);
        BeanUtil.json2Map(new JSONObject(min), ObjectMap);
        return ObjectMap;
    }

    //判断手机号
    public static boolean findPhone(java.lang.String phone) {
        java.lang.String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.matches();
    }


    public static String getRegexPhoneNums(String phoneNums) {
        String[] split = phoneNums.split(",");
        String sb = "";
        for (String str : split) {
            if (findPhone(str)) {
                String s = HttpUtil.phoneEncrypt(str);
                if (null != s && !s.isEmpty()) {
                    sb = s + "|" + sb;

                }

            }
        }
        if (!sb.isEmpty()) {
            return sb.substring(0, sb.length() - 1);
        }
        return "";
    }


    public static void main(String[] args) throws Exception {
        System.out.println(getRegexPhoneNums("18968102733,18967543422"));
    }

}
