package com.sifu.sfcc.entity.ws;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

public class MsgPackage {

    @ApiModelProperty(value = "通道发送人")
    private String channelFrom;
    @ApiModelProperty(value = "通道接收人")
    private String channelTo;
    @ApiModelProperty(value = "通道类型 1001（广播)、1002 (单通道)")
    private Integer chnnelType;
    @ApiModelProperty(value = "消息内容")
    private String msg;
    @ApiModelProperty(value = "业务类型 1003（待办）、1004（催报）")
    private Integer msgType;

    @ApiModelProperty(value = "产生的原因）")
    private Integer action;


    public String getChannelFrom() {
        return channelFrom;
    }

    public void setChannelFrom(String channelFrom) {
        this.channelFrom = channelFrom;
    }

    public String getChannelTo() {
        return channelTo;
    }

    public void setChannelTo(String channelTo) {
        this.channelTo = channelTo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getChnnelType() {
        return chnnelType;
    }

    public void setChnnelType(Integer chnnelType) {
        this.chnnelType = chnnelType;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }
}
