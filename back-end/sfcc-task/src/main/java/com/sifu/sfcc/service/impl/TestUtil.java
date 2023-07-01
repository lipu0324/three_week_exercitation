package com.sifu.sfcc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.utils.HttpClientUtil;

public class TestUtil {
	
	public static void main(String[] args) {
		String filter = "15004653965,18686750683,15004653962,13206532159";
		String str = "{\"name\":\"催办\",\"agent\":\"10000_50000\",\"tenantId\":\"10000\",\"isVgc\":\"1\",\"maxRingTime\":\"60\",\"callType\":1,\"ivrId\":\"5\",\"actionID\":\"\"}";
    	String result = HttpClientUtil.postX("http://123.56.85.102:8098/6.0.x/config/addOutboundInstance", str);
    	JSONObject res= JSON.parseObject(result);
    	int code = res.getIntValue("code");
    	if(code == 200) {
    		JSONObject data = res.getJSONObject("data");
        	String id = data.getString("id");
        	
        	String append = "{\"outboundId\":\""+ id +"  \",\"filter\":\""+ filter +"\",\"actionID\":\"\"}";
        	String appendResult = HttpClientUtil.postX("http://123.56.85.102:8098/6.0.x/config/appendOutboundInstance", append);
        	JSONObject appRes= JSON.parseObject(appendResult);
        	int appCode = appRes.getIntValue("code");
        	if(appCode == 200) {
        		 String start = "{\"outboundId\":\""+ id +"\",\"tenantId\":\"10000\",\"dialMode\":\"1\"}";
                 HttpClientUtil.httpPostHeadWithJSON("http://123.56.85.102:8099/config/StartOutbound", start,null);
        	}
    	}
	}
	
}
