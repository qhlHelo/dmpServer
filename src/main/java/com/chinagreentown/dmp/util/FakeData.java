package com.chinagreentown.dmp.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import sun.rmi.log.LogInputStream;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yun on 2017/4/12.
 */
public class FakeData {


    public enum HttpStr {
        PARAMETERERROR("parameter error"), PHONEERROR("");

        private String value;

        private HttpStr(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public enum LabelKey {
        BASE("GT01"), BASENAME("GT01001B"), BASEPHONE("GT01006001"), BASELIVEAREA("GT03002"), BASEWORKAREA("GT03003"),
        COM("GT09COM"), NETBEHAVE("netBehavior");

        private String value;

        private LabelKey(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    private static List<String> phoneList;

    private static List<String> phoneList2;

    private static Map<String, String> map;

    private static Map<String, String> areamap;

    private static Map<String, String> netAssert;

    private static Map<String, String> netSport;


    public static final List<String> comList = Lists.newArrayList("近一月手机通话次数", "近一月手机通话总时长", "近一月国内长途次数", "近一月国内长途时长", "近一月国际长途次数", "近一月国际长途时长", "近一月手机上网次数", "近一月手机上网流量", "近一月手机上网时长", "近一月白天上网次数", "近一月白天上网流量", "近一月白天上网时长", "进一月晚上上网次数", "近一月晚上上网时长", "近一月晚上上网流量");

    public static final List<String> comListlabel = Lists.newArrayList("GT09COM01", "GT09COM02", "GT09COM03", "GT09COM04", "GT09COM05", "GT09COM06", "GT09COM07", "GT09COM8", "GT09COM09", "GT09COM010", "GT09COM011", "GT09COM012", "GT09COM013", "GT09COM014", "GT09COM015");

    public static final List<String> numList = Lists.newArrayList("0-30", "30-90", "90-150", "150-210", "210-300", "300-450", "450以上");

    public static final List<String> timeList = Lists.newArrayList("0-200分钟", "200-500分钟", "500-700分钟", "700-1000分钟", "1000-1500分钟", "1500分钟以上");

    public static final List<String> trafficList = Lists.newArrayList("0-3G", "3-5G", "5-7G", "7-10G", "10-20G", "20-50G", "50G以上");

    public static final List<String> conList = Lists.newArrayList("0-50", "50-100", "100-150", "150-200", "200-250", "250以上");

    public static final List<String> conlabelList = Lists.newArrayList("GT10CON00101", "GT10CON00102", "GT10CON00103", "GT10CON00104", "GT10CON001015", "GT10CON00106");

    private static List<String> arealist = Lists.newArrayList("GT01008003001", "GT01008003002", "GT01008003003", "GT01008003004", "GT01008003005", "GT01008003006");

    private final static ArrayList<String> ageKey = Lists.newArrayList("GT01004CON0101", "GT01004CON0102", "GT01004CON0103", "GT01004CON0104", "GT01004CON0105");

    private final static ArrayList<String> live = Lists.newArrayList("111.123,23.332", "167.123,23.332", "123.123,23.22", "111.123,23.332", "123,23.322");

    private final static ArrayList<String> work = Lists.newArrayList("21.123,23.332", "57.123,23.332", "473.123,23.22", "71.123,23.332", "13,23.322");

    private final static ArrayList<String> sex = Lists.newArrayList("男", "女", "未知");

    private final static ArrayList<String> phone = Lists.newArrayList("xiaomi", "vivo", "apple", "oppo", "meizhu");

    private final static ArrayList<String> phonelabel = Lists.newArrayList("TER020104", "TER020618", "TER020243", "TER020638", "TER020301");

    private final static ArrayList<String> car = Lists.newArrayList("有车", "没车", "未知");

    private final static ArrayList<String> marriage = Lists.newArrayList("已婚", "未婚", "未知");

    private final static ArrayList<String> name = Lists.newArrayList("laowang", "laogao", "xiaoming", "xiaoxiao", "shisandian");

    private final static ArrayList<String> pv = Lists.newArrayList("19", "13", "123", "45", "78", "244", "17", "231", "23", "2379", "23121", "21321", "213213", "213213", "1", "0");

    public final static ArrayList<String> devList = Lists.newArrayList("绿城", "万达", "万科", "保利", "中海", "华润置地");

    public final static ArrayList<String> placeList = Lists.newArrayList("滨江", "下沙", "三墩", "钱江新城", "临平");

    public final static ArrayList<String> orientationList = Lists.newArrayList("朝南", " 朝北", "朝东", " 朝西");

    public final static ArrayList<String> focusList = Lists.newArrayList("十万", "二十万", " 三十万", "四十万", "五十万");

    public final static ArrayList<String> locationList = Lists.newArrayList("滨江", "下沙", "三墩", "钱江新城", "临平");

    public final static ArrayList<String> unitList = Lists.newArrayList("10000", "20000", "30000", "40000", "50000");

    public final static ArrayList<String> vastList = Lists.newArrayList("一百平米");

    public final static ArrayList<String> totalPrice = Lists.newArrayList("一百万", "两百万", "三百万", "四百万", "五百万");

    public final static ArrayList<String> schoolList = Lists.newArrayList("学军", "浙大", "杭电", "理工", "工业大学");

    public final static ArrayList<String> HouseholdList = Lists.newArrayList("一居", "两居", "三居", "四居");

    public final static ArrayList<String> redecoratList = Lists.newArrayList("简装", "中装", "精装");

    public final static ArrayList<String> subwayList = Lists.newArrayList("一线", "二线", "三线", "四线", "五线");

    public final static ArrayList<String> floorList = Lists.newArrayList("底层", "中层", "中高层");

    public final static ArrayList<String> specialList = Lists.newArrayList("近地铁", "学区", "高端住宅", "交通便利", "现房");


    public enum house {
        house1("GU0040030101130001", "越秀星汇城住宅"), house2("GU0040030101130002", "美丽湾住宅"), house3("GU0040030101130003", "德信早安住宅"), house4("GU0040030101130004", "中港罗兰小镇住宅"), house5("GU0040030101130005", "雅居乐国际花园住宅");

        private String key;

        private String value;

        private house(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return this.key;
        }

        public String getValeu() {
            return this.value;
        }
    }


    public static <E> E getE(List<E> list, int code) {
        return list.get(code%list.size());
    }


    public static Map<String, String> getMap() {
        if (null == map) {
            map = setMap();
        }
        return map;
    }

    private static Map<String, String> setMap() {
        map = Maps.newHashMap();
        map.put("18968102733", "78b9bec0724938d0e311217930eac39e");
        map.put("15907829843", "895eb0a0670b94b3574bb6169cbb0390");
        map.put("18934572976", "fb066d1af17ae2555a5f5df7684bc7a0");
        map.put("18988888888", "a51e07d3ba9ae5adba80a523c8831c88");
        map.put("18966666666", "11ef49c43c553c459a5279f6b2e427d4");
        return map;
    }


    public static String getArealabel(String phonenum) {
        Long yu = Long.parseLong(phonenum) % arealist.size();
        return arealist.get(yu.intValue());
    }

    public static String getAreaname(String arealabel) {
        if (areamap == null) {
            setareamap();
        }
        return areamap.get(arealabel);
    }

    private static void setareamap() {
        areamap = Maps.newHashMap();
        areamap.put("GT01008003001", "杭州");
        areamap.put("GT01008003002", "湖州");
        areamap.put("GT01008003003", "嘉兴");
        areamap.put("GT01008003004", "宁波");
        areamap.put("GT01008003005", "绍兴");
        areamap.put("GT01008003006", "舟山");
    }


    public static List<String> getPhoneList() {
        phoneList = setPhoneList();
        return phoneList;
    }

    private static List<String> setPhoneList() {
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


    public static String getAgeLabel(String phonenum) {
        Long yu = Long.parseLong(phonenum) % ageKey.size();
        return ageKey.get(yu.intValue());
    }

    //与agelist对应 也不写map 对应了

    private final static ArrayList<String> age = Lists.newArrayList("0-18", "18-25", "25-50", "50-70", "70以上");


    public static String getAge(String phonenum) {
        Long yu = Long.parseLong(phonenum) % age.size();
        return age.get(yu.intValue());
    }


    public static String getlive(String phonenum) {
        Long yu = Long.parseLong(phonenum) % live.size();
        return live.get(yu.intValue());
    }

    public static String getwork(String phonenum) {
        Long yu = Long.parseLong(phonenum) % work.size();
        return work.get(yu.intValue());
    }

    public static String getSex(String phonenum) {
        Long yu = Long.parseLong(phonenum) % sex.size();
        return sex.get(yu.intValue());
    }

    public static String getPhoneBand(String phonenum) {
        Long yu = Long.parseLong(phonenum) % phone.size();
        return phone.get(yu.intValue());
    }

    public static String getPhoneBandLabel(String phonenum) {
        Long yu = Long.parseLong(phonenum) % phonelabel.size();
        return phonelabel.get(yu.intValue());
    }

    public static String gethascar(String phonenum) {
        Long yu = Long.parseLong(phonenum) % car.size();
        return car.get(yu.intValue());
    }

    public static String getmarriage(String phonenum) {
        Long yu = Long.parseLong(phonenum) % marriage.size();
        return marriage.get(yu.intValue());
    }

    public static String getname(String phonenum) {
        Long yu = Long.parseLong(phonenum) % name.size();
        return name.get(yu.intValue());
    }

    private final static ArrayList<String> bear = Lists.newArrayList("已育", "未育", "未知");

    public static String getBear(String phonenum) {
        Long yu = Long.parseLong(phonenum) % bear.size();
        return bear.get(yu.intValue());
    }

    public static String getPv(int i) {
        int yu = i % pv.size();
        return pv.get(yu);
    }


    public static String getMa5Phone(String Phone) {
        boolean result = findPhone(Phone);
        if (!result) {
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


    public static boolean isNum(String args) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(args);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static Map<String, Object> getComMap(String phonenum) {
        Map<String, Object> map = Maps.newLinkedHashMap();
        for (int i = 0; i < 15; i++) {
            HashMap<String, Object> cominfo = Maps.newHashMap();
            String label = comListlabel.get(i);
            String labelname = comList.get(i);
            cominfo.put(label, labelname);
            Long aLong = Long.valueOf(phonenum);
            if (labelname.contains("次数")) {
                Long l = (aLong + i) % numList.size();
                cominfo.put(label + l, numList.get(l.intValue()));
            } else if (labelname.contains("时长")) {
                Long l = (aLong * 3 + i) % timeList.size();
                cominfo.put(label + l, timeList.get(l.intValue()));
            } else {
                Long l = (aLong * 2 + i) % trafficList.size();
                cominfo.put(label + l, trafficList.get(l.intValue()));
            }

            map.put(label, cominfo);
        }
        return map;
    }


    public static String getcon(String phonenum) {
        Long yu = Long.parseLong(phonenum) % conList.size();
        return conList.get(yu.intValue());
    }

    public static String getconlabel(String phonenum) {
        Long yu = Long.parseLong(phonenum) % conlabelList.size();
        return conlabelList.get(yu.intValue());
    }

    //G网模拟数据
    private static Map<String, String> setNetbehaviorAsset() {
        LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
        map.put("GT04001003GU001002001", "股票");
        map.put("GT04001003GU001002002", "基金");
        map.put("GT04001003GU001002003", "期货");
        map.put("GT04001003GU001002004", "外汇");
        map.put("GT04001003GU001002005", "黄金");
        map.put("GT04001003GU001002006", "理财");
        map.put("GT04001003GU001002007", "保险");
        map.put("GT04001003GU001002008", "股指");
        map.put("GT04001003GU001002009", "期指");
        return map;
    }

    public static Map<String, Object> getAssertmap(String phonenum) {
        if (netAssert == null) {
            netAssert = setNetbehaviorAsset();
        }
        List<String> strings = Lists.newArrayList(netAssert.keySet());
        LinkedHashMap<String, Object> returnMap = Maps.newLinkedHashMap();
        Long phone = Long.valueOf(phonenum);
        for (int i = 0; i < (phone % strings.size()); i++) {
            LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
            Long i1 = phone % (strings.size());
            String s = strings.get((i1.intValue() + i) % strings.size());
            map.put(s, netAssert.get(s));
            map.put("pv", getPv(i));
            returnMap.put(s, map);
        }
        return returnMap;
    }

    public static Map<String, Object> getSportmap(String phonenum) {
        if (netSport == null) {
            netSport = setNetbehaviorSport();
        }
        List<String> strings = Lists.newArrayList(netSport.keySet());
        LinkedHashMap<String, Object> returnMap = Maps.newLinkedHashMap();
        Long phone = Long.valueOf(phonenum);
        for (int i = 0; i < (phone % strings.size()); i++) {
            LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
            Long i1 = phone % (strings.size());
            String s = strings.get((i1.intValue() + i) % strings.size());
            map.put(s, netSport.get(s));
            map.put("pv", getPv(i));
            returnMap.put(s, map);
        }
        return returnMap;
    }

    //c网模拟数据
    private static Map<String, String> setNetbehaviorSport() {
        LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
        map.put("GT04002003CU002002006", "棒球");
        map.put("GT04002003CU002002006", "乒乓球");
        map.put("GT04002003CU002002007", "羽毛球");
        map.put("GT04002003CU002002008", "高尔夫");
        map.put("GT04002003CU002002009", "台球");
        map.put("GT04002003CU002002010", "曲棍球");
        map.put("GT04002003CU002002011", "球类运动");
        map.put("GT04002003CU002002012", "橄榄球");

        return map;
    }
}