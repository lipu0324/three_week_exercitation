package com.sifu.sfcc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.sifu.sfcc.bo.SendUrl;
import com.sifu.sfcc.service.*;
import com.sifu.sfcc.util.NGDObjectToJson;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

/**
 * @Author 逄博
 * @Function  webHook接口
 * @Date 2022.01.07
 */
@RestController
@Api(tags = "CcNGDWebHookController", description = "webHook相关接口应用")
@CrossOrigin
@RequestMapping(value = "/ngd/webHook")
public class CcNGDWebHookController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    CcHandleTheBusinessService handleTheBusinessService;

    @Autowired
    CcCulturalBusinessService culturalBusinessService;


    @PostMapping("/webHook")
    public Object webHook(@RequestBody String webHook) {
        System.out.println("================== 输入参数：" + webHook + "===================");
        //获取出入的context和action名称跟据action名称判断进入哪个条件分支
        JSONObject webHookJson = JSONObject.parseObject(webHook);
        JSONObject context = webHookJson.getJSONObject("context");
        String action = webHookJson.getString("action");
        String session_id = webHookJson.getString("session_id");
        //发送短信接口
        SendUrl sendUrl = new SendUrl();
        //根据session_id获取电话号
        String phone = NGDObjectToJson.getPhone(restTemplate, session_id);
        //1.1 旅游业务
        if ("TourismBusiness".equals(action)) {
            Map tourismBusiness = handleTheBusinessService.tourismBusiness(context, session_id,phone);
            return NGDObjectToJson.returnJson(new JSONObject(), 200, "", tourismBusiness.get("res").toString());
        }
        //1.2 导游考试
        else if ("TourGuideExaminati".equals(action)) {
            Map tourGuideExaminati = handleTheBusinessService.tourGuideExaminati(context, session_id);
            return NGDObjectToJson.returnJson(new JSONObject(), 200, "", tourGuideExaminati.get("res").toString());
        }
        //1.3 导游证
        else if ("TourGuideCertifica".equals(action)) {
            Map tourGuideCertifica = handleTheBusinessService.tourGuideCertifica(context, session_id,phone);
            return NGDObjectToJson.returnJson(new JSONObject(), 200, "", tourGuideCertifica.get("res").toString());
        }
        //2.1 文化业务
        if ("CulturalBusiness".equals(action)) {
            Map culturalBusiness = culturalBusinessService.culturalBusiness(context, session_id,phone);
            return NGDObjectToJson.returnJson(new JSONObject(), 200, "", culturalBusiness.get("res").toString());
        }
        //2.2 证件办理
        else if ("CertificateHandling".equals(action)) {
            Map certificateHandling = culturalBusinessService.certificateHandling(context, session_id,phone);
            return NGDObjectToJson.returnJson(new JSONObject(), 200, "", certificateHandling.get("res").toString());
        }


        return null;
    }
}
