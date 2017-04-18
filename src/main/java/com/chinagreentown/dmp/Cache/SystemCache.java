package com.chinagreentown.dmp.Cache;

import com.chinagreentown.dmp.util.BeanUtil;
import com.chinagreentown.dmp.util.FileUtil;
import com.google.common.collect.Maps;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yun on 2017/4/18.
 *
 * @Description 处理全局缓存
 */
public class SystemCache {

    /* 防止被实例化 */
    private SystemCache() {

    }

    private static SystemCache instance;


    //单例双重锁
    public static synchronized SystemCache getInstance() {
        if (instance == null) {
            synchronized (SystemCache.class) {
                if (instance == null) {
                    instance = new SystemCache();
                }
            }
        }
        return instance;
    }

    //通信信息map  映射
    private static Map<String, Map<String, String>> commap = null;
    //消费表 映射
    private static Map<String, Map<String, String>> conmap = null;
    //用户基本信息映射
    private static Map<String, Map<String, String>> UsrBasMap = null;

    private Map<String, Map<String, String>> setUsrBasMap() throws JSONException {
        Map<String, Map<String, String>> map = Maps.newHashMap();
        String s = FileUtil.ReadFile("C:\\Users\\yun\\Desktop\\json\\COM.json");
        BeanUtil.json2DoubleMap(new JSONObject(s), map);
        UsrBasMap = map;
        return UsrBasMap;
    }

    public Map<String, String> getUsrBasMap(String key) throws JSONException {
        if (null == UsrBasMap) {
            UsrBasMap = setConMap();
        }
        return UsrBasMap.get(key);
    }

    //放置缓存
    private Map<String, Map<String, String>> setConMap() throws JSONException {
        Map<String, Map<String, String>> map = Maps.newHashMap();
        String s = FileUtil.ReadFile("C:\\Users\\yun\\Desktop\\json\\Consume.json");
        JSONObject jsonMap = new JSONObject(s);
        BeanUtil.json2DoubleMap(jsonMap, map);
        conmap = map;
        return conmap;
    }

    //通过 编码 获取  这个编码的所有父节点
    public Map<String, String> getConMap(String key) throws JSONException {
        if (null == conmap) {
            conmap = setConMap();
        }
        return conmap.get(key);
    }

    //通过编码获取他的所有信息 ，从一级 到最高级
    public Map<String, String> getComMap(String key) throws JSONException {
        if (null == commap) {
            commap = setComMap();
        }
        return commap.get(key);
    }

    //存放通信信息到cache
    private Map<String, Map<String, String>> setComMap() throws JSONException {
        Map<String, Map<String, String>> map = Maps.newHashMap();
        String s = FileUtil.ReadFile("C:\\Users\\yun\\Desktop\\json\\COM.json");
        JSONObject jsonMap = new JSONObject(s);
        BeanUtil.json2DoubleMap(jsonMap, map);
        commap = map;
        return commap;
    }


}
