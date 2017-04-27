package com.chinagreentown.dmp.controller;

import com.chinagreentown.dmp.pojo.UsrPoiInfoPojo.poi;
import com.chinagreentown.dmp.service.BaseQueryService;
import com.chinagreentown.dmp.service.HbaseTestService;
import com.chinagreentown.dmp.service.PrecisionMarketingService;

import org.apache.hadoop.hbase.filter.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by yun on 2017/4/15.
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private PrecisionMarketingService testservice;
    @Autowired
    private BaseQueryService q;

    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public ResponseEntity<Object> test1() throws IOException, NoSuchFieldException, JSONException, IllegalAccessException {
        Map<String, Object> esateUserPOILive = testservice.getEsateUserPOILive("0328", "21321");
        return ResponseEntity.ok(esateUserPOILive);
    }


}
