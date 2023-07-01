package com.sifu.sfcc.util;

import com.google.gson.Gson;
import com.sifu.sfcc.entity.ws.MsgPackage;
import com.sifu.sfcc.enums.ApproveStatusEnum;
import com.sifu.sfcc.enums.ws.MsgTypeEnum;
import com.sifu.sfcc.model.CcWorkOrderTrace;

public class MsgUtils {
    public static Gson gson = new Gson();

    /***
     * 构建消息包
     * @param trace
     * @param type
     * @param business
     * @param msg
     * @return
     */
    public static MsgPackage buildMsg(CcWorkOrderTrace trace, MsgTypeEnum type, MsgTypeEnum business, ApproveStatusEnum statusEnum, Object msg) {
        MsgPackage msgPkg = new MsgPackage();
        msgPkg.setChannelFrom(trace.getFromId().toString());
        msgPkg.setChannelTo(trace.getToId().toString());
        msgPkg.setChnnelType(type.getType());
        msgPkg.setMsgType(business.getType());
        msgPkg.setMsg(MsgUtils.gson.toJson(msg));
        msgPkg.setAction(statusEnum.getCode());
        return msgPkg;
    }
}
