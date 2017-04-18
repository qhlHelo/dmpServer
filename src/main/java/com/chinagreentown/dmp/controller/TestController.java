package com.chinagreentown.dmp.controller;

import com.chinagreentown.dmp.service.HbaseTestService;
import com.chinagreentown.dmp.service.PrecisionMarketingService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by yun on 2017/4/15.
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private PrecisionMarketingService testservice;

    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public ResponseEntity<Object> test1() throws IOException, NoSuchFieldException, JSONException, IllegalAccessException {
        Object usrLabelInfo = testservice.getUsrLabelInfo("09223370");
        return ResponseEntity.ok(usrLabelInfo);

    }


}
