package com.sifu.sfcc.service;


import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface CcHandleTheBusinessService {


    Map tourismBusiness(JSONObject context, String session_id,String phone);

    Map tourGuideExaminati(JSONObject context, String session_id);

    Map tourGuideCertifica(JSONObject context, String session_id,String phone);

}
