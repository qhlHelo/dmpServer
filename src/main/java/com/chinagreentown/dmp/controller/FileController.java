package com.chinagreentown.dmp.controller;

import com.chinagreentown.dmp.Cache.SystemCache;
import com.chinagreentown.dmp.Constant.Result;
import com.chinagreentown.dmp.pojo.PeopleDto;
import com.chinagreentown.dmp.util.HttpUtil;
import com.google.common.collect.Maps;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

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
