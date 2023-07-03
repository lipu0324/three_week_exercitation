package com.sifu.sfcc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.controller.CcNGDController;
import com.sifu.sfcc.dto.PolicyParam;
import com.sifu.sfcc.service.CcNGDService;
import com.sifu.sfcc.service.CcNGDZczxService;
import com.sifu.sfcc.service.XmkPolicyService;
import com.sifu.sfcc.util.NGDSendHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


@Service
public class CcNGDZczxServiceImpl implements CcNGDZczxService {

    @Autowired
    private XmkPolicyService xmkPolicyService;

    @Autowired
    private CcNGDService ccNGDService;


    @Override
    public Map getCountByLocation(JSONObject context,String session_id,String phone) {


        Map map = new HashMap();
        String sys_location = context.getString("sys_location");
        JSONObject dizhi = context.getJSONObject("dizhi");
        String city = dizhi.getString("city");
        JSONObject jsonParams = new JSONObject();
        if(!ObjectUtils.isEmpty(sys_location)) {
            if (sys_location.contains("黑龙江省")) {
                jsonParams.put("city", sys_location.replace("黑龙江省", ""));
            }
            jsonParams.put("city",city);
            if(!StringUtils.isEmpty(session_id)){
                ccNGDService.saveQ(phone,"2",city,session_id);
                System.out.println("保存政策成功");
            }
//            PolicyParam policyCount = xmkPolicyService.getPolicyCount(jsonParams.toString());
            PolicyParam policyRecommend = xmkPolicyService.getPolicyRecommend(jsonParams.toJSONString(), 1);
            //无标题查询
            if(policyRecommend.getCount()>0){
                map.put("res","您一共查询到了"+policyRecommend.getCount()+"个政策。为您推荐的政策有"+policyRecommend.getPolicyTitle()+"稍后会以短信的形式发送到您的手机，请问还有什么能够帮助您？" );
            }else{
                map.put("res",sys_location+"暂未查询到政策！请问还有什么能够帮您？");
            }

        }
        return map;
    }

}
