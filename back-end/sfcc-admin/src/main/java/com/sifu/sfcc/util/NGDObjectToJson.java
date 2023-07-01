package com.sifu.sfcc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;


public  class NGDObjectToJson {
    /**
     *
     * @param params 想要转换成ReturnData类型的对象
     * @param errno 错误码
     * @param msg 错误信息
     * @param value 机器人回复的信息
     * @return webHook返回参数类型
     */
    public static JSON returnJson(JSON params, int errno, String msg, String value) {
        JSONObject data = new JSONObject();
        data.put("context", params);
        data.put("value", value);
        data.put("valueType", 1);
        JSONObject returnVal = new JSONObject();
        returnVal.put("errno", errno);
        returnVal.put("msg", msg);
        returnVal.put("data", data);
        System.out.println("retrun结果:"+returnVal);
        return returnVal;
    }



    public static String getPhone(RestTemplate restTemplate,String sessionId){
        LinkedMultiValueMap sessionid = new LinkedMultiValueMap();
        NGDSendHttp sendHttp = new NGDSendHttp();
        sessionid.add("sessionid", sessionId);
        JSONObject tels = sendHttp.send(restTemplate,"http://192.168.38.12:8222/ngd/getPhone",sessionid);
//        JSONObject tels = sendHttp.send(restTemplate,"http://111.40.37.223:8222/ngd/getPhone",sessionid);
        String phone = "";
        if(tels.getString("code").equals("0")){
            phone = tels.getJSONObject("data").getString("phone");
        }
        return phone;
    }
}
