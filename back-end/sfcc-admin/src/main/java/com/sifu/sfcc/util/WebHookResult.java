package com.sifu.sfcc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.common.api.ResultCode;


/**
 * @author 逄博
 */
public  class WebHookResult {
    /**
     *操作成功
     * @param params 返回参数，ngd中接口的变量
     * @param value 机器人回复的信息
     * @return webHook返回参数类型
     */
    public static JSON success(JSON params, String value) {
        JSONObject data = new JSONObject();
        data.put("context", params);
        data.put("value", value);
        data.put("valueType", 1);
        JSONObject returnVal = new JSONObject();
        returnVal.put("errno", ResultCode.SUCCESS.getCode());
        returnVal.put("msg", ResultCode.SUCCESS.getMessage());
        returnVal.put("data", data);
        return returnVal;
    }


    /**
     *操作成功
     * @param params 返回参数，ngd中接口的变量
     * @param value 机器人回复的信息
     * @return webHook返回参数类型
     */
    public static JSON error(JSON params, String value) {
        JSONObject data = new JSONObject();
        data.put("context", params);
        data.put("value", value);
        data.put("valueType", 1);
        JSONObject returnVal = new JSONObject();
        returnVal.put("errno", ResultCode.FAILED.getCode());
        returnVal.put("msg", ResultCode.FAILED.getMessage());
        returnVal.put("data", data);
        return returnVal;
    }

}
