package com.sifu.sfcc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.config.XmkScheduleConfig;
import com.sifu.sfcc.util.NGDObjectToJson;
import com.sifu.sfcc.util.WebHookResult;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 逄博
 * @Function  人社一体机webHook接口
 * @Date 2022.01.07
 */
@RestController
@Api(tags = "CcNGDRSWebHookController", description = "人社一体机webHook相关接口应用")
@CrossOrigin
@RequestMapping(value = "/ngd/rsWebHook")
public class CcNGDRSWebHookController {

    private static final Logger log = LoggerFactory.getLogger(CcNGDRSWebHookController.class);

    @PostMapping("/webHook")
    public Object webHook(@RequestBody String webHook) {
        log.info("webHook传入参数：{}", JSON.toJSONString(webHook));
        //获取出入的context和action名称跟据action名称判断进入哪个条件分支决策树
        JSONObject webHookJson = JSONObject.parseObject(webHook);
        JSONObject context = webHookJson.getJSONObject("context");
        String action = webHookJson.getString("action");
        String session_id = webHookJson.getString("session_id");

        //识别人社业务的标识 1-银行卡 2-社保卡
        if ("tag".equals(action)) {
            System.out.println(context);
            int res = 1;
            if(res > 0){
                return WebHookResult.success(new JSONObject(), "办理成功！");
            } else{
                return WebHookResult.error(new JSONObject(), "办理失败，此业务系统正在维护，请联webhook开发人员！");

            }

        }







        return null;
    }
}
