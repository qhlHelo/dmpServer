package com.chinagreentown.dmp.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yun on 2017/4/12.
 */
public class FakeData {


    private static List<String> phoneList;

    private static List<String> phoneList2;

    private static Map<String, String> map;

    public static Map<String, String> getMap() {
        if (null == map) {
            map = setMap();
        }
        return map;
    }

    public static Map<String, String> setMap() {
        map = Maps.newHashMap();
        map.put("18968102733", "78b9bec0724938d0e311217930eac39e");
        map.put("15907829843", "895eb0a0670b94b3574bb6169cbb0390");
        map.put("18934572976", "fb066d1af17ae2555a5f5df7684bc7a0");
        map.put("18988888888", "a51e07d3ba9ae5adba80a523c8831c88");
        map.put("18966666666", "11ef49c43c553c459a5279f6b2e427d4");
        return map;
    }



    public static List<String> getPhoneList() {
        phoneList = setPhoneList();
        return phoneList;
    }

    private  static List<String> setPhoneList() {
        phoneList = Lists.newArrayList("18968102733", "15907829843", "18934572976", "18988888888", "18966666666");
        return phoneList;

    }

    public static List<String> getPhoneList2() {
        phoneList2 = setPhoneList2();
        return phoneList2;
    }

    private static List<String> setPhoneList2() {
        phoneList2 = Lists.newArrayList("18765431232", "18967851234", "18034578961", "15809876765", "15878906789");
        return phoneList2;

    }


    private final static ArrayList<String> age = Lists.newArrayList("0-18", "18-25", "25-50", "50-70", "70以上");

    public static String getAge(String phonenum) {
        Long yu = Long.parseLong(phonenum) % age.size();
        return age.get(yu.intValue());
    }

    private final static ArrayList<String> live = Lists.newArrayList("111.123,23.332", "167.123,23.332", "123.123,23.22", "111.123,23.332", "123,23.322");

    public static String getlive(String phonenum) {
        Long yu = Long.parseLong(phonenum) % live.size();
        return live.get(yu.intValue());
    }

    private final static ArrayList<String> work = Lists.newArrayList("21.123,23.332", "57.123,23.332", "473.123,23.22", "71.123,23.332", "13,23.322");

    public static String getwork(String phonenum) {
        Long yu = Long.parseLong(phonenum) % work.size();
        return work.get(yu.intValue());
    }

    private final static ArrayList<String> sex = Lists.newArrayList("男", "女", "未知");

    public static String getSex(String phonenum) {
        Long yu = Long.parseLong(phonenum) % sex.size();
        return sex.get(yu.intValue());
    }

    private final static ArrayList<String> phone = Lists.newArrayList("xiaomi", "vivo", "apple", "oppo", "meizhu");

    public static String getPhoneBand(String phonenum) {
        Long yu = Long.parseLong(phonenum) % phone.size();
        return phone.get(yu.intValue());
    }

    private final static ArrayList<String> car = Lists.newArrayList("有车", "没车", "未知");

    public static String gethascar(String phonenum) {
        Long yu = Long.parseLong(phonenum) % car.size();
        return car.get(yu.intValue());
    }

    private final static ArrayList<String> marriage = Lists.newArrayList("已婚", "未婚", "未知");

    public static String getmarriage(String phonenum) {
        Long yu = Long.parseLong(phonenum) % marriage.size();
        return marriage.get(yu.intValue());
    }

    private final static ArrayList<String> bear = Lists.newArrayList("已育", "未育", "未知");

    public static String getBear(String phonenum) {
        Long yu = Long.parseLong(phonenum) % bear.size();
        return bear.get(yu.intValue());
    }

    private final static ArrayList<String> pv = Lists.newArrayList("19", "13", "123", "45", "78", "244", "17", "231", "23", "2379", "23121", "21321", "213213", "213213", "1", "0");

    public static String getPv(int i) {
        int yu = i % pv.size();
        return pv.get(yu);
    }


    public static String getMa5Phone(String Phone) {
        boolean result = findPhone(Phone);
        if (result==false ) {
            return "";
        }
        String s = getMap().get(Phone);
        if (null == s) {
            s = "";
        }
        return s;

    }

    public static boolean findPhone(String phone) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

}
