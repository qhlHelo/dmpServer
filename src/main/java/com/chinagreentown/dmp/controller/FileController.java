package com.chinagreentown.dmp.controller;

import com.chinagreentown.dmp.Constant.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yun on 2017/4/21.
 */
@RequestMapping(value = "file")
public class FileController {


    @RequestMapping(method = RequestMethod.POST, value = "/download")
    public ResponseEntity<Result> fileDownload(@RequestParam(value = "token") String token) {


        return null;
    }


}
