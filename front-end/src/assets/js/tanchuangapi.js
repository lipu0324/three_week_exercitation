/**
 * Created by ronny, 2017/08/09
 * modified by ronny, 2019/04/19
 */
    // 通话定时器
var hour = 0, minute = 0, second = 0;
var t = 0;
var flag;

function timeInt(_ele) {
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
window.popupAgentObject = {
    thisDN: "",//座席号
    otherDN: "",//对端号码
    thirdNumber: "",//第三方号码
    line1CallId: "",//当前callID
    line2CallId: "",//第三方callID
    agentState: ""//座席状态
};

/*接口数据*/
//1.1 座席首次签入
var popupfirstLoginData = {
    thisDN: window.thisDN,
    type: "login",
    message: JSON.stringify({
        messageId: 100,
        thisDN: popupAgentObject.thisDN,
        extension: popupAgentObject.exten,
        thisQueues: queue
    })
}
//1.2 座席签入
var popuploginData = function (exten) {
    return {
        thisDN: window.thisDN,
        type: "login",
        message: JSON.stringify({
            messageId: 100,
            thisDN: popupAgentObject.thisDN,
            extension: popupAgentObject.exten,
            thisQueues: queue
        })
    }

}
//2.座席签出
var popuplogoutData = function (extenL) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 103,
                thisDN: popupAgentObject.thisDN,
                extension: popupAgentObject.exten
            })
    }
}
//3.座席就绪
var popupagentReadyData = function (extenL) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 101,
                thisDN: popupAgentObject.thisDN,
                extension: popupAgentObject.exten
            })
    }

}
//4.座席示忙
var popupagentNotReadyData = function (_reasonCode, exten) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 102,
                thisDN: popupAgentObject.thisDN,
                extension: popupAgentObject.exten,
                reasonCode: _reasonCode	//可以是可选值中的任意一个,可选值有：0(整理);1(通话);2(设备不可用);3(忙碌);4(离开);5(休息)
            })
    }
}

//9.呼叫释放---挂机
var popupreleaseCallData = function (_ctiCallId, exten) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 203,
                thisDN: popupAgentObject.thisDN,
                callID: _ctiCallId
            })
    }
}
//10. 呼叫转接
var popuptransferCallData = function (_ctiCallId, _targetDN, _phoneNumber, exten) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 215,
                thisDN: popupAgentObject.thisDN,
                callID: _ctiCallId,
                otherDN: _targetDN,//转接目标的号码
                phoneNumber: _phoneNumber,//本次通话的号码
                //phoneNumber: window.thisDN,//本次通话的号码
                attachDatas: {isHold: "1"}
            })
    }
}

//11. 三方通话
var popupThirdPartyCallData = function (_ctiCallId, _thirdPartyNumber, exten) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 214,
                thisDN: popupAgentObject.thisDN,
                callID: _ctiCallId,//通话ID，从通话事件中进行获取
                otherDN: _thirdPartyNumber//第三方的号码
            })
    }
}

//16.呼叫监听
var popupCallMonitor = function (exten, callID, otherDN) {
    return {
        thisDN: window.thisDN,
        type: "request",
        message: JSON.stringify(
            {
                messageId: 265,
                thisDN: popupAgentObject.thisDN,//座席号,例如："10001_1000"监听座席号
                callID: callID,//通话ID，从通话事件中进行获取
                otherDN: otherDN//监听目标的号码
            })
    }
}
