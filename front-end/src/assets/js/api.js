/**
 * Created by ronny, 2017/08/09
 * modified by ronny, 2019/04/19
 */
    // 通话定时器
var hour = 0, minute = 0, second = 0;
var t = 0;
var flag;
window.timeInt = function(_ele) {
    hour = Math.floor(t / 60 / 60);
    minute = Math.floor(t / 60 % 60);
    second = Math.floor(t % 60);
    if (hour < 10) {
        hour = "0" + hour;
    }
    if (minute < 10) {
        minute = "0" + minute;
    }
    if (second < 10) {
        second = "0" + second;
    }
    $('#' + _ele).text(hour + ":" + minute + ":" + second);
    t = t + 1;
    flag = setTimeout("timeInt('time')", 1000);
}

/*全局座席;(注意：此处处理方法只是方便演示接口使用方法，若是涉及到代码执行效率等问题，还请自行处理)*/
window.agentObject = {
    thisDN: "",//座席号
    otherDN: "",//对端号码
    thirdNumber: "",//第三方号码
    line1CallId: "",//当前callID
    line2CallId: "",//第三方callID
    agentState: "",//座席状态
    holdCallId: "",//保持CallId
    holdDN: ""//保持号码
};
window.agentDNs = [];//该企业全部座席

/*websocket心跳包*/
var heartCheck = {
    timeout: 10000,
    timeoutObj: null,
    reset: function () {
        clearTimeout(this.timeoutObj);
        this.start();
    },
    start: function () {
        this.timeoutObj = setTimeout(function () {
            ws.send(JSON.stringify({"thisDN": thisDN, "type": "ping", "message": "abs"}));
        }, this.timeout)
    }
}

/*接口数据*/
//1.1 座席首次签入
var firstLoginData = {
    thisDN: window.thisDN,
    type: "login",
    message: JSON.stringify({
        messageId: 100,
        thisDN: window.thisDN,
        extension: exten,
        thisQueues: queue,
        attachDatas: {"typeCode": "20"}
    })
}
//1.2 座席签入
var loginData = {
    thisDN: window.thisDN,
    type: "login",
    message: JSON.stringify({
        messageId: 100,
        thisDN: window.thisDN,
        extension: exten,
        thisQueues: queue,
        attachDatas: {"typeCode": "20"}
    })
}
//2.座席签出
var logoutData = {
    thisDN: window.thisDN,
    type: "request",
    message: JSON.stringify(
        {
            messageId: 103,
            thisDN: window.thisDN,
            extension: exten
        })
}
//3.座席就绪
var agentReadyData = {
    thisDN: window.thisDN,
    type: "request",
    message: JSON.stringify(
        {
            messageId: 101,
            thisDN: window.thisDN,
            extension: exten
        })
}
//4.座席示忙
var agentNotReadyData = function (_reasonCode) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 102,
                thisDN: window.thisDN,
                extension: exten,
                reasonCode: _reasonCode	//可以是可选值中的任意一个,可选值有：
                //0(整理);1(通话);2(设备不可用);3(忙碌);4(离开);5(休息);6(拨号中);7(外线振铃);8:(呼入振铃)
            })
    }
}
//5.呼叫
var makeCallData = function (_phoneNumber) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 200,
                thisDN: window.thisDN,
                otherDN: _phoneNumber,
                // attachDatas: {
                //     "cti-tenant-id": targetTenantId,
                //     "voice_path": "/sharedfs/webapps/sounds/10000/hold-on.wav"
                // }
                attachDatas: {"cti-tenant-id": targetTenantId}
            })
    }
}
//5.保持呼叫
var holdMakeCallData = function (_phoneNumber) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 200,
                thisDN: window.thisDN,
                otherDN: _phoneNumber,
                attachDatas: {"cti-tenant-id": targetTenantId, "holdCall": "1", callID: agentObject.line1CallId}
            })
    }
}
//6. 应答
var answerCallData = function (_ctiCallId) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 201,
                thisDN: window.thisDN,
                callID: _ctiCallId
            })
    }
}
// 7. 呼叫保持
var holdCallData = function (_ctiCallId) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 204,
                thisDN: window.thisDN,
                otherDN: agentObject.otherDN,
                callID: _ctiCallId
            })
    }
}
//8. 呼叫取回
var retrieveCallData = function (_ctiCallId) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 217,
                thisDN: window.thisDN,
                otherDN: agentObject.otherDN,
                callID: _ctiCallId
            })
    }
}

//8. 保持呼叫取回
var retrieveHoldCallData = function () {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 217,
                thisDN: window.thisDN,
                otherDN: agentObject.holdDN,
                callID: agentObject.holdCallId
            })
    }
}

//9.呼叫释放
var releaseCallData = function (_ctiCallId) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 203,
                thisDN: window.thisDN,
                callID: _ctiCallId
            })
    }
}
//10. 呼叫转接
var transferCallData = function (_ctiCallId, _targetDN, _phoneNumber) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 215,
                thisDN: window.thisDN,
                callID: _ctiCallId,
                otherDN: _targetDN,//转接目标的号码
                phoneNumber: _phoneNumber,//本次通话的号码
                // phoneNumber: window.thisDN,//本次通话的号码
                attachDatas: {isHold: "0"}
            })
    }
}

// 连接保持
var connectHoldCall = function (_ctiCallId, _targetDN, _phoneNumber) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 289,
                thisDN: _targetDN,//连接目标的号码
                callID: _ctiCallId,
                otherDN: _phoneNumber,//连接目标的号码
                phoneNumber: window.thisDN//保持通话的号码
            })
    }
}

// 两方求助
var negotiationCall = function (_ctiCallId, _targetDN, _phoneNumber) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 215,
                thisDN: window.thisDN,//求助方号码
                callID: _ctiCallId,
                otherDN: _targetDN,//被求助方号码
                phoneNumber: _phoneNumber,//本次通话号码
                attachDatas: {isHold: "1"}
            })
    }
}

//呼叫转IVR
var makeCallChangeIVR = function (_ctiCallId, _phoneNumber) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 290,
                thisDN: window.thisDN,
                phoneNumber: _phoneNumber,
                callID: _ctiCallId,
                attachDatas: {"ivrID": "6", "isHold": "1"}
            })
    }
}

//11. 三方通话
var thirdPartyCallData = function (_ctiCallId, _thirdPartyNumber) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 214,
                thisDN: window.thisDN,
                callID: _ctiCallId,
                otherDN: _thirdPartyNumber,
                phoneNumber: agentObject.otherDN
            })
    }
}
//13. 开始座席监控
var startMornitorData = function (_agentDNs) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 266,
                thisDN: window.thisDN,
                agentDNS: _agentDNs
            })
    }
}
//14. 停止座席监控
var stopMornitorData = function (_agentDNs) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 267,
                thisDN: window.thisDN,
                agentDNS: _agentDNs
            })
    }
}

//15. 获取座席列表
var queryAgentListData = {
    type: "request",
    message: JSON.stringify(
        {
            messageId: 4001,
            tenantId: targetTenantId,
            currentPage: "",
            pageSize: "",
            name: ""
        })
}
//3.15 静音
var muteNumberSeats = function (_ctiCallId) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 292,
                thisDN: window.thisDN, //座席号，例如："10001_1000"
                callID: _ctiCallId, //通话ID，从通话事件中进行获取
                otherDN: agentObject.otherDN //静音目标（座席号或手机号）
            })
    }
}
// 3.16.停止静音
var stopMuteNumberStats = function (_ctiCallId) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 293,
                thisDN: window.thisDN, //座席号，例如："10001_1000"
                callID: _ctiCallId, //通话ID，从通话事件中进行获取
                otherDN: agentObject.otherDN//本次通话号码 //静音目标（座席号或手机号）
            })
    }
}


// 3.17发送按键
var sendDigit = function (_ctiCallId, _targetDN, _digit) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 250,
                thisDN: window.thisDN,//通话座席
                callID: _ctiCallId,
                otherDN: _targetDN,//发送按键号码
                dtmfDigit: _digit//按键
            })
    }
}

// 3.18 通话放音
var playVoice = function (_ctiCallId) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 274,
                thisDN: window.thisDN,//通话座席
                callID: _ctiCallId,
                //path: "/opt/test-tts-05.wav",//音频文件路径
                path: "/opt/tet.wav",//音频文件路径
                flag: "4"//按键
            })
    }
}

// 3.19 创建会议
var createConference = function (calleeNumbers) {
    window.conferenceNumber = window.targetTenantId + "_7001";
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 280,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumber: window.conferenceNumber,//会议室号(全局唯一)
                calleeNumbers: calleeNumbers //会议参与人
            })
    }
}

// 3.20 加入会议
var addConference = function (conferenceNumber) {
    window.conferenceNumber = conferenceNumber;
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 281,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumber: window.conferenceNumber,//会议室号(全局唯一)
                calleeNumbers: window.thisDN //会议参与人
            })
    }
}

// 3.21 退出会议
var exitConference = function (conferenceNumber) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 282,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumber: window.conferenceNumber,//会议室号(全局唯一)
                calleeNumbers: conferenceNumber //会议参与人
            })
    }
}

// 3.22 邀请会议成员
var inviteConference = function (calleeNumbers) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 281,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumber: window.conferenceNumber,//会议室号(全局唯一)
                calleeNumbers: calleeNumbers //会议参与人
            })
    }
}

// 3.23 移除会议成员
var outConference = function (calleeNumbers) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 282,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumber: window.conferenceNumber,//会议室号(全局唯一)
                calleeNumbers: calleeNumbers //会议参与人
            })
    }
}

// 3.24 结束会议
var endConference = function () {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 284,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumber: window.conferenceNumber//会议室号(全局唯一)
            })
    }
}

// 3.25 会议禁言成员
var muteConference = function (calleeNumbers) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 285,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumber: window.conferenceNumber,//会议室号(全局唯一)
                calleeNumbers: calleeNumbers //会议禁言人员
            })
    }
}

// 3.26 会议取消禁言成员
var unmuteConference = function (calleeNumbers) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 286,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumber: window.conferenceNumber,//会议室号(全局唯一)
                calleeNumbers: calleeNumbers //会议取消禁言人员
            })
    }
}

// 3.27 会议无声成员
var deafConference = function (calleeNumbers) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 287,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumber: window.conferenceNumber,//会议室号(全局唯一)
                calleeNumbers: calleeNumbers //会议禁言人员
            })
    }
}

// 3.28 会议取消无声成员
var undeafConference = function (calleeNumbers) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 288,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumber: window.conferenceNumber,//会议室号(全局唯一)
                calleeNumbers: calleeNumbers //会议取消禁言人员
            })
    }
}

// 3.29 开始会议监控
var startConferenceMonitor = function () {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 303,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumbers: [window.targetTenantId + "_7001"]//不传则监控当前资源组下所有会议
            })
    }
}

// 3.30 停止会议监控
var stopConferenceMonitor = function () {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 304,
                thisDN: window.thisDN,//操作人员座席
                conferenceNumbers: [window.targetTenantId + "_7001"]//不传则监控当前资源组下所有会议
            })
    }
}

export default {
    firstLoginData,
    loginData,
    logoutData,
    agentReadyData,
    agentNotReadyData,
    makeCallData,
    holdMakeCallData,
    answerCallData,
    holdCallData,
    retrieveCallData,
    retrieveHoldCallData,
    releaseCallData,
    transferCallData,
    connectHoldCall,
    negotiationCall,
    makeCallChangeIVR,
    thirdPartyCallData,
    startMornitorData,
    stopMornitorData,
    queryAgentListData,
    muteNumberSeats,
    stopMuteNumberStats,
    sendDigit,
    playVoice,
    createConference,
    addConference,
    exitConference,
    inviteConference,
    outConference,
    endConference,
    muteConference,
    unmuteConference,
    deafConference,
    undeafConference,
    startConferenceMonitor,
    stopConferenceMonitor,
    heartCheck,
    flag,
    t
}