package com.sifu.sfcc.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Multimap;
import com.sifu.sfcc.bo.SendUrl;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public interface CcNGDMobileService {

    Map getTels(JSONObject context,String session_id,String phone);

    Map createScore(JSONObject context, String sessionId, String phone);
}
