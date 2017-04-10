package com.chinagreentown.dmp.controller;

import com.chinagreentown.dmp.pojo.salescenter.BaseInfo;
import com.chinagreentown.dmp.pojo.salescenter.Phone;
import com.chinagreentown.dmp.pojo.salescenter.SalesLabel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2017/3/29.
 * 智慧案场相关接口
 */

@RestController
@RequestMapping("salescenter")
public class SalesCenterController {

    /**
     * wifi上报
     *
     * @param phonenum
     * @param token
     * @param phonebrand
     * @param macaddress
     * @param salescenterid
     * @param time
     * @return ResponseEntity<BaseInfo>
     */
    @RequestMapping(value = "/wifi", method = RequestMethod.POST)
    public ResponseEntity<BaseInfo> wifiReport(@RequestParam(value = "phonenum", required = false) String phonenum,
                                               @RequestParam(value = "token") String token,
                                               @RequestParam(value = "phonebrand") String phonebrand,
                                               @RequestParam(value = "macaddress") String macaddress,
                                               @RequestParam(value = "salescenterid") String salescenterid,
                                               @RequestParam(value = "time") String time) {
        try {
            BaseInfo baseInfo = new BaseInfo();
            baseInfo.setPhonenum("15079123333");
            baseInfo.setSalesnum("15079123563");
            // 对象创建成功，响应201
            return ResponseEntity.status(HttpStatus.CREATED).body(baseInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 查询出错，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 车牌上报
     *
     * @param phonenum
     * @param token
     * @param platenum
     * @param salescenterid
     * @param time
     * @return ResponseEntity<BaseInfo>
     */
    @RequestMapping(value = "/licencePlate", method = RequestMethod.POST)
    public ResponseEntity<BaseInfo> licencePlate(@RequestParam(value = "phonenum", required = false) String phonenum,
                                                 @RequestParam(value = "token") String token,
                                                 @RequestParam(value = "platenum") String platenum,
                                                 @RequestParam(value = "salescenterid") String salescenterid,
                                                 @RequestParam(value = "time") String time) {
        try {
            BaseInfo baseInfo = new BaseInfo();
            baseInfo.setPhonenum("15079123362");
            baseInfo.setSalesnum("15079123523");
            // 对象创建成功，响应201
            return ResponseEntity.status(HttpStatus.CREATED).body(baseInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 查询出错，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 人脸上报
     *
     * @param phonenum
     * @param token
     * @param faceurl
     * @param salescenterid
     * @param time
     * @return ResponseEntity<BaseInfo>
     */
    @RequestMapping(value = "/face", method = RequestMethod.POST)
    public ResponseEntity<BaseInfo> face(@RequestParam(value = "phonenum", required = false) String phonenum,
                                         @RequestParam(value = "token") String token,
                                         @RequestParam(value = "faceurl") String faceurl,
                                         @RequestParam(value = "salescenterid") String salescenterid,
                                         @RequestParam(value = "time") String time) {
        try {
            BaseInfo baseInfo = new BaseInfo();
            baseInfo.setPhonenum("15079121234");
            baseInfo.setSalesnum("15079156677");
            // 对象创建成功，响应201
            return ResponseEntity.status(HttpStatus.CREATED).body(baseInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 查询出错，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 查询标签
     *
     * @param phonenum
     * @param token
     * @return ResponseEntity<SalesLabel>
     */
    @RequestMapping(value = "/label", method = RequestMethod.GET)
    public ResponseEntity<SalesLabel> getSalesLabel(@RequestParam(value = "phonenum") String phonenum,
                                                    @RequestParam(value = "token") String token) {
        try {
            SalesLabel salesLabel = new SalesLabel();
            salesLabel.setSex("男");
            salesLabel.setAge("25-30");
            salesLabel.setApppreference("生育");
            salesLabel.setBear("生育");
            salesLabel.setHascar("有车");
            salesLabel.setLive("西湖区");
            salesLabel.setWork("滨江区");
            salesLabel.setPurchasepurpose("学区");
            salesLabel.setMarriage("已婚");
            Phone phone = new Phone();
            phone.setBrand("xiaomi");
            phone.setModel("hongmi222");
            phone.setPrice("3000-4000");
            salesLabel.setPhone(phone);
            // // 查询成功，响应200
            return ResponseEntity.ok(salesLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 查询出错，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
