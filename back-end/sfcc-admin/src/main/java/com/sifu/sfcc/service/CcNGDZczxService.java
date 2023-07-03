package com.sifu.sfcc.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public interface CcNGDZczxService {

    Map getCountByLocation(JSONObject context,String session_id,String phone);

}
