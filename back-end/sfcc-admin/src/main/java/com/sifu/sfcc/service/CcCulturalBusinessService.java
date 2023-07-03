package com.sifu.sfcc.service;


import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface CcCulturalBusinessService {


    Map culturalBusiness(JSONObject context, String session_id,String phone);

    Map certificateHandling(JSONObject context, String session_id,String phone);
}
