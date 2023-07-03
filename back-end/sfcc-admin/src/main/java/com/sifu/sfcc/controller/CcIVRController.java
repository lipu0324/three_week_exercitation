package com.sifu.sfcc.controller;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.api.client.json.Json;
import com.sifu.sfcc.bo.ResponseDto;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.Text;

/**
 * @Author 逄博
 * @Function  webHook接口
 * @Date 2022.01.07
 */

@RestController
@Api(tags = "CcIVRController", description = "IVR相关接口应用")
@CrossOrigin
@RequestMapping(value = "/ngd/ivr")
public class CcIVRController {
    Logger log = LoggerFactory.getLogger(CcIVRController.class);

    @Autowired
    private CcNGDService ccNGDService;

    @ApiOperation(value = "注册挂机事件")
    @PostMapping(value = "/hangUp")
    public CommonResult hangUp(@RequestBody String ivr){
        JSONObject jsonObject = JSONObject.parseObject(ivr);

        //JSONObject attachDatas = JSON.parseObject(jsonObject.get("attachDatas").toString()).getJSONObject("ivr_dtmf_digit").getJSONObject("response").getJSONObject("body").getJSONObject("data");
        JSONObject attachDatasJson = JSON.parseObject(jsonObject.get("attachDatas").toString());


//        System.out.println("挂机事件进入");
        System.out.println("ivr==========="+ivr+"===============");


//        String campaignId = ivr.substring(ivr.indexOf("\\\\\\\"campaign_id\\\\\\\":\\\\\\\"")+24, ivr.indexOf("\\\\\\\"}"));
        String ivr_dtmf_digit = attachDatasJson.get("ivr_dtmf_digit").toString();
//        System.out.println("ivr_dtmf_digit = " + ivr_dtmf_digit);

        int startIndex = ivr_dtmf_digit.indexOf("\"sessionid\":\"");

//        System.out.println("startIndex = " + startIndex);

        String substring = ivr_dtmf_digit.substring(startIndex);

        String s2 = substring.substring(0, substring.indexOf(","));

//        System.out.println(s2);


        JSONObject j2 = JSONObject.parseObject("{" + s2 + "}");

//        System.out.println(j2.toString());
        String sessionId = j2.getString("sessionid");//ivr_dtmf_digit.substring(ivr_dtmf_digit.indexOf("\"sessionid\":\""), ivr_dtmf_digit.indexOf("\""));
        System.out.println("挂机事件进入："+sessionId);
//        log.info("ivr：{}", JSON.toJSONString(ivr));
//        log.info("ivr：{}", JSON.toJSONString(ivr));
//
        String result = ccNGDService.NGDSendSms(sessionId);
        System.out.println("挂机事件发送短信：");
        if("OK".equals(result)) return CommonResult.success("存储成功");
        else return CommonResult.failed();

    }
}
