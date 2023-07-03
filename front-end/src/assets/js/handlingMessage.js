/**
 * Created by ronny, 2017/08/09
 * modified by ronny, 2019/04/19
 */
import Api from '@/assets/js/api.js';
import Router from '../../router';
let flag = ''
let callback = function (data) {/*事件处理，data为事件返回的数据*/
    /*
     注意：1.此处的座席状态显示部分的处理应该视具体的业务需求而定，接口所设置的状态在接口文档中已有说明；
     2.座席状态涉及到界面主动改变的部分可调用接口，但是通话期间的状态是由接口直接做好的，不能在通话期间手动设置状态，建议界面设置提示功能。
     3.demo部分的界面显示和部分处理只是方便演示接口的调用方法，具体细节处理部分还请查看接口文档自行处理。
     */
    if (data == null) return;
    // console.log(data)
    // if (data.deviceState == 2 && data.messageId != 580 && data.messageId != 588) {
    //     alert("软电话未能成功加载，请检查是否开启软电话或者软电话注册号码是否与登录座席一致。");
    // }
    if (data.messageId == 580) {//座席签入
        Api.heartCheck.start();
        // console.log("收到EventAgentLogin事件:====:座席号：" + data.thisDN + "班组号：" + data.thisQueues + "对应终端状态(0:未知，1:可用，2:不可用)：" + data.deviceState);
        $("h4[data-index='agentDN']").html("当前座席号： " + data.thisDN);
        agentObject.thisDN = data.thisDN;
        agentObject.agentState = "login";
        $('#currentStatus').text('签入');
        $('#showStatus>button').siblings('.activeStatus').removeClass('activeStatus');
        window.clearTimeout(Api.flag);
        Api.t = 0;
        timeInt('time');
        //ws.send(JSON.stringify(agentReadyData));//座席就绪, 此处根据业务而定，如果需要在座席签入后忙碌，此处可调用忙碌接口。

    } else if (data.messageId == 583) {//座席就绪
        // console.log("收到EventAgentReady事件:===:座席号：" + data.thisDN + "班组号：" + data.thisQueues + "座席状态(0:登出，1:就绪，2:忙碌)：" + data.state + "对应终端状态(0:未知，1:可用，2:不可用)：" + data.deviceState);
        $('#currentStatus').text('就绪');
        agentObject.agentState = "ready";
        $('#showStatus>button[data-status="ready"]').addClass('activeStatus').siblings('.activeStatus').removeClass('activeStatus');
        window.clearTimeout(Api.flag);
        Api.t = 0;
        timeInt('time');
    } else if (data.messageId == 581) {//座席签出
        // console.log("收到EventAgentLogout事件:===:座席号：" + data.thisDN + "班组号：" + data.thisQueues);
        $('#currentStatus').text('签出');
        agentObject.agentState = "logout";
        $('#showStatus>button').siblings('.activeStatus').removeClass('activeStatus');
        window.clearTimeout(Api.flag);
        Api.t = 0;
        timeInt('time');


    } else if (data.messageId == 582) {//座席示忙
        // console.log("收到EventAgentNotReady事件:===:座席号：" + data.thisDN + "班组号：" + data.thisQueues + "座席状态(0:登出，1:就绪，2:忙碌)：" + data.state + "对应终端状态(0:未知，1:可用，2:不可用)：" + data.deviceState);
        agentObject.agentState = "busy";
        if (data.reasonCode == 0) {//整理
            $('#currentStatus').text('整理');
            window.clearTimeout(Api.flag);
            Api.t = 0;
            timeInt('time');
            $('#showStatus>button[data-status="arrangement"]').addClass('activeStatus').siblings('.activeStatus').removeClass('activeStatus');
        } else if (data.reasonCode == 1) {//通话
            $('#currentStatus').text('示忙通话中');
            $('#showStatus>button[data-status="busy"]').addClass('activeStatus').siblings('.activeStatus').removeClass('activeStatus');
            window.clearTimeout(Api.flag);
            Api.t = 0;
            timeInt('time');
        } else if (data.reasonCode == 2) {//设备不可用
            $('#currentStatus').text('设备不可用');
            $('#currentStatus').text('可能由于网络原因，无法连接到软电话');
        } else if (data.reasonCode == 3) {//示忙
            $('#currentStatus').text('忙碌');
            $('#showStatus>button[data-status="busy"]').addClass('activeStatus').siblings('.activeStatus').removeClass('activeStatus');
            window.clearTimeout(Api.flag);
            Api.t = 0;
            timeInt('time');
        } else if (data.reasonCode == 4) {//离开
            $('#currentStatus').text('离开');
            window.clearTimeout(Api.flag);
            Api.t = 0;
            timeInt('time');
        } else if (data.reasonCode == 5) {//休息
            $('#currentStatus').text('休息');
            $('#showStatus>button[data-status="rest"]').addClass('activeStatus').siblings('.activeStatus').removeClass('activeStatus');
            window.clearTimeout(Api.flag);
            Api.t = 0;
            timeInt('time');
        }

    } else if (data.messageId == 503) {//座席来电
        // console.log("收到EventRinging事件:===:座席号：" + data.thisDN + "主叫号码：" + data.otherDN + "callId：" + data.callID);
        $('#currentStatus').text('来电：' + data.otherDN);
        agentObject.otherDN = data.otherDN;
        agentObject.agentState = "ring";
        agentObject.line1CallId = data.callID;
        flag = 'callStatus' // 来电存储变量
    } else if (data.messageId == 513) {//呼叫取回
        $('#currentStatus').text('通话中');
        // console.log("收到EventRetrieved事件:===:座席号：" + data.thisDN + "对端号码：" + data.otherDN + "callId：" + data.callID);
        // $('#pause').show();
        // $('#retrieve').hide();
        agentObject.line1CallId = data.callID;
        $('#showStatus>button[data-status="busy"]').addClass('activeStatus').siblings('.activeStatus').removeClass('activeStatus');

    } else if (data.messageId == 505) {//座席拨号
        $('#currentStatus').text('拨号中');
        // console.log("收到EventDialing事件:===:座席号：" + data.thisDN + "被叫号码：" + data.otherDN + "callId：" + data.callID + "呼叫类型(0:未知；1:内部呼叫；2:呼入；3:呼出)：" + data.callType);
        window.clearTimeout(Api.flag);
        Api.t = 0;
        timeInt('time');
        agentObject.otherDN = data.otherDN;
        agentObject.line1CallId = data.callID;
        agentObject.agentState = "dialling";
    } else if (data.messageId == 506) {//呼叫建立
        $('#currentStatus').text('通话中');
        // console.log("收到EventEstablished事件:===:座席号：" + data.thisDN + "对端号码：" + data.otherDN + "callId：" + data.callID);
        window.clearTimeout(Api.flag);
        Api.t = 0;
        timeInt('time');
        agentObject.otherDN = data.otherDN;
        agentObject.line1CallId = data.callID;
        agentObject.agentState = "established";
        if(flag == 'callStatus' && data.otherDN.indexOf("_") == -1){
            flag = '';
            Router.push(`/conversation/conversationInfo?number=${data.otherDN}&sit=${data.thisDN}&pageFlag=${Math.floor(Math.random() * 10) + 1}`);
        }
    } else if (data.messageId == 509) {//呼叫保持
        $('#currentStatus').text('呼叫保持中');
        // console.log("收到EventHeld事件:===:座席号：" + data.thisDN + "对端号码：" + data.otherDN + "callId：" + data.callID);
        /*$('#pause').hide();
         $('#retrieve').show();*/
        agentObject.line1CallId = data.callID;
    } else if (data.messageId == 515) { //呼叫释放
        // $.ajax({
        //     url:`http://39.103.236.61:8899/ngd/userquestions?tel=${data.otherDN}`,
        //     type:"post",
        //     data:{}
        // })
        $('#currentStatus').text('通话结束');
        console.log("收到EventReleased事件:===:座席号：" + data.thisDN + "对端号码：" + data.otherDN + "callId：" + data.callID);
        $('#phoneNumber').show();
        $('#transferNumber,#confirmTransfer').hide();
        $('#negotiationNumber,#confirmNegotiation').hide();
        window.clearTimeout(Api.flag);
        Api.t = 0;
        timeInt('time');
        agentObject.otherDN = "";
        agentObject.line1CallId = "";
        agentObject.agentState = "release";
        if(window.timer){
            clearInterval(window.timer)
            $("#conversationTimes").text("通话结束").css("color","#FF0005")
            if($("#conversationOperations .operationItem").length == 4){
                for(var i = 0;i<$("#conversationOperations .operationItem").length;i++){
                    if(i!=1){
                        $("#conversationOperations .operationItem").eq(i).hide();
                    }
                }
            }
        }
    } else if (data.messageId == 588) {//开始座席监控
        // console.log("收到座席监控事件:===:当前座席号：" + data.thisDN + "被监控座席：" + data.agentDN + "座席状态：agentState	座席状态。0:登出；1:就绪；2:忙碌" + data.agentState + "callId：" + data.callID);
        var agentDN = "L" + data.agentDN + "L";
        window.all[agentDN] = data.callID;//获取对应的callID
        // console.log("开始监控座席+" + data.agentDN)
        $(".agents").find("li[data-agent=" + data.agentDN + "]>span").show();
        if (data.agentState == 0) {
            $(".agents").find("li[data-agent=" + data.agentDN + "]").css("backgroundColor", "#5C5F57");
        } else if (data.agentState == 1) {
            $(".agents").find("li[data-agent=" + data.agentDN + "]").css("backgroundColor", "#2DAA48");
        } else if (data.agentState == 2) {
            $(".agents").find("li[data-agent=" + data.agentDN + "]").css("backgroundColor", "#E4A248");
        }
        // $(".agents").find("li[data-agent=" + data.agentDN + "]>span>b").html("(座席状态:" + data.agentState + ")");
    } else if (data.messageId == 591) {
        // console.log("会议创建事件  会议室号:" + data.conferenceNumber + "会议室创建人:" + data.thisDN + "会议室主持人:" + data.thisDN)
    } else if (data.messageId == 592) {
        // console.log("加入会议事件  会议室号:" + data.conferenceNumber + "会议室创建人:" + data.thisDN + "目前成员:" + data.attachDatas)
    } else if (data.messageId == 594) {
        // console.log("移除会议事件  会议室号:" + data.conferenceNumber + "会议室创建人:" + data.thisDN + "目前成员:" + data.attachDatas)
    } else if (data.messageId == 596) {
        // console.log("会议静音事件  会议室号:" + data.conferenceNumber + "会议室创建人:" + data.thisDN + "目前成员:" + data.attachDatas)
    } else if (data.messageId == 597) {
        // console.log("会议取消静音事件  会议室号:" + data.conferenceNumber + "会议室创建人:" + data.thisDN + "目前成员:" + data.attachDatas)
    } else if (data.messageId == 598) {
        // console.log("会议无声事件  会议室号:" + data.conferenceNumber + "会议室创建人:" + data.thisDN + "目前成员:" + data.attachDatas)
    } else if (data.messageId == 599) {
        // console.log("会议取消无声事件  会议室号:" + data.conferenceNumber + "会议室创建人:" + data.thisDN + "目前成员:" + data.attachDatas)
    } else if (data.messageId == 595) {
        // console.log("会议结束事件  会议室号:" + data.conferenceNumber + "会议室创建人:" + data.thisDN + "会议室主持人:" + data.thisDN)
    } else if (data.messageId == 606) {
        // console.log("会议监控事件  会议室号:" + data.conferenceNumber + "会议室创建人:" + data.thisDN + "会议室主持人:" + data.thisDN)
        var html = "";
        $.each(data.attachDatas, function (k, v) {
            var json = JSON.parse(v)
            var isDeaf = json.isDeaf == 1 ? "是" : "否"
            var isMute = json.isMute == 1 ? "是" : "否"
            html += '<li  data-agent=' + k + '>' +
                '<h4>座席号:</h4>' + '<p>' + json.conferenceMemberThisDN + '</p>' +
                '<h4>分机号:</h4>' + '<p>' + json.conferenceMemberExtension + '</p>' +
                '<h4>静言:' + isMute + '</h4>' +
                '<h4>无声:' + isDeaf + '</h4>' +
                ' <span style="display: none; color: red"><b></b></span></li>';
        })
        $(".conferenceMembers").html(html);
    } else if (data.messageId == 4500) {
        $('#currentStatus').text('被迫离线(座席号在其他地方登陆)');
        window.clearTimeout(Api.flag);
        $('#showStatus button.activeStatus').removeClass('activeStatus');
        // alert("座席已从其他地方登陆，可刷新界面重新登录");
    } else if (data.messageId == 8888) {
        // console.log("**********接口成功提示*********");
        // console.log(data);
    } else if (data.messageId == 9999) {
        console.log("**********接口失败提示*********");
        // console.log(data);
        if (data.errorMessageId == 250) {
            // alert("发送按键失败:" + data.errorMessage);
        } else if (data.errorMessageId == 274) {
            // alert("播放音频失败" + data.errorMessage);
        }
    } else {
        // console.warn("收到其他座席事件：messageId===>" + data.messageId, "；data==》", data);
    }


};

export default {
    callback
};
