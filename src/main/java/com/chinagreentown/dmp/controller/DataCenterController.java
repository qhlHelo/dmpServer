package com.chinagreentown.dmp.controller;

import com.chinagreentown.dmp.Constant.Result;
import com.chinagreentown.dmp.service.QueryService;
import com.chinagreentown.dmp.util.FakeData;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yun on 2017/4/11.
 */
@RestController
@RequestMapping(value = "datacenter")
public class DataCenterController {
    @Autowired
    private QueryService query;

    private static final Logger LOGGER = LoggerFactory.getLogger(PrecisionMarketing.class);

    @RequestMapping(method = RequestMethod.POST, value = "portrait/v1.0")
    /**
     * @description 画像获取
     * @version 1.0 版本号
     * @author zyy
     */
    public ResponseEntity<Object> getportrait(@RequestParam(value = "phonenum") String phonenums,
                                              @RequestParam(value = "date") String date,
                                              @RequestParam(value = "token") String token) {
        try {
            ArrayList<Object> lists = Lists.newArrayList();
            if (token.equals("test") && null != date && null != date) {
                String[] phones = phonenums.split(",");
                for (String str : phones) {
                    if (str.length() == 11) {
                        String ma5Phone = FakeData.getMa5Phone(str);
                        if (!ma5Phone.isEmpty()) {
                            lists.add(query.getuUerInfo(str));
                        }
                    } else if (str.length() == 32) {
                        lists.add(query.getuUerInfo("18968102733"));
                    }
                }
                if (!lists.isEmpty()) {
                    return ResponseEntity.ok(Result.Success(lists));
                } else {
                    return ResponseEntity.ok(Result.SuccessEmpty());
                }
            }
            HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();
            objectObjectHashMap.put("Reason", "parameter error");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(objectObjectHashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 查询出错，响应500
        LOGGER.info("查询出错");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
