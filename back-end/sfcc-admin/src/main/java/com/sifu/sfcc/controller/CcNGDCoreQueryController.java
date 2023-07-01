package com.sifu.sfcc.controller;

import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.service.*;
import com.sifu.sfcc.util.SendHttpNLP;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 逄博
 * @Function  webHook接口
 * @Date 2022.01.07
 */
@RestController
@Api(tags = "CcCoreQueryController", description = "NGD会话中控")
@CrossOrigin
@RequestMapping(value = "/ngd")
public class CcNGDCoreQueryController {

    @Autowired
    private CcNGDXmzxService xmService;

    private String sessionId;

    @ApiOperation(value = "会话中控")
    @PostMapping(value = "/query")
    public Object coreQuery(@RequestParam("queryText") String queryText) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("queryText",queryText);
        jsonObject.put("sessionId",sessionId);
        String post = SendHttpNLP.sendHttpQuery(String.valueOf(jsonObject));
        return post;
    }

    @ApiOperation(value = "开始")
    @PostMapping(value = "/start")
    public Object start() {
        Map<String, String> startParams = new HashMap<>();
        startParams.put("type","welcome");
        String startRes = SendHttpNLP.sendHttpStart(startParams);
        JSONObject start = JSONObject.parseObject(startRes);
        sessionId = start.getJSONObject("data").getString("sessionId");
        return startRes;
    }

}
