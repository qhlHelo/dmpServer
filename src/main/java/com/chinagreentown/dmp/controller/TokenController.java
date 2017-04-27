package com.chinagreentown.dmp.controller;

import com.chinagreentown.dmp.Cache.SystemCache;
import com.chinagreentown.dmp.Constant.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yun on 2017/4/22.
 */
@Controller
@RequestMapping(value = "token")
public class TokenController {

    @RequestMapping(method = RequestMethod.GET, value = "/v1.0")
    public ResponseEntity<Object> getToken() {
        return ResponseEntity.ok(Result.Success(SystemCache.getInstance().getToken()));
    }
}
