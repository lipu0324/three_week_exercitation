<!--
 * @Descripttion: 
 * @Author: Dyl
 * @Date: 2021-10-20 09:24:12
-->
<template>
  <el-menu class="navbar" mode="horizontal">
    <div class="logoBox" :style="!this.sidebar.opened ? 'width:36px' : 'width:200px'">
      <img :src="getIcon(systemInfo.logo)" alt="" class="logo" />
    </div>
    <div class="navFlexBox" :style="!this.sidebar.opened ? 'width:calc(100vw - 36px)' : 'width:calc(100vw - 200px)'">
      <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>
      <div class="systemName">{{systemInfo.name}}</div>
      <el-input class="navItem" v-model="phone" style="width:220px" type="text" size="mini" id="phoneNumber" placeholder="输入手机号,非哈尔滨号码前请加0" ></el-input>
      <el-button class="callUp navButton" :disabled="phoneLogin == 1" @click="callHandle">
        <img src="../../../assets/images/callUp.png" alt="">
        外呼
      </el-button>
      <el-button class="hangUp navButton" :disabled="phoneLogin == 1" @click="hangUpHandle">
        <img src="../../../assets/images/callCut.png" alt="">
        挂机
      </el-button>
      <i class="el-icon-chat-dot-square" style="color: #22AC38;margin-left:1vw;"></i>
      <el-select class="navItem" @change="phoneStatusChange" v-model="phoneLogin" placeholder="请选择" size="mini">
        <el-option
          v-for="item in phoneLoginStatus"
          :key="item.value"
          :label="item.label"
          :value="item.value"
          >
        </el-option>
      </el-select>
      <div class="rightOperation">
        <div @click="noticeGo">
          <el-badge :value="noticeList.length" class="noticeBox">
            <i class="el-icon-message-solid"></i>
          </el-badge>
        </div>
        <el-popover
          placement="bottom"
          width="150"
          trigger="click"
          v-model="showQuit"
          >
          <div @click="logout" class="quitBox">
            <i class="el-icon-switch-button"></i>
            <span style="display:block;">退出</span>
          </div>
          <div slot="reference" class="userInfoBox">
            <el-avatar class="userLogo" :size="50" :src="getIcon(headimg)"></el-avatar>
            <p class="welcome_text">欢迎您 <span>{{name}}</span></p>
            <i class="el-icon-caret-bottom" style="color:#666666" v-if="!showQuit"></i>
            <i class="el-icon-caret-top" style="color:#666666" v-else></i>
          </div>
        </el-popover>
      </div>
    </div>
  </el-menu>
</template>

<script>
import "@/assets/js/jquery-1.8.3.js";
import { mapGetters } from 'vuex'
import ReconnectingWebSocket from "reconnecting-websocket";
import {getInfo} from '@/api/system.js'
import config from '@/assets/js/config.js'
import Api from '@/assets/js/api.js';
import handMessage from '@/assets/js/handlingMessage.js';
import Hamburger from '@/components/Hamburger'
import request from '@/utils/request';

export default {
  data() {
    return {
      phoneLoginStatus:[
        {
          value:0,
          label:'签入',
        },{
          value:1,
          label:'签出',
        }
      ],
      phoneLogin:0,
      phone:'',
      showQuit:false,
      msg_host:msg_host,
      http_urla:"http://" + msg_host + ":8096/6.0.x/",
    }
  },
  components: { Hamburger },
  computed: {
    ...mapGetters([
      'sidebar',
      'name',
      'systemInfo',
      'headimg',
      'noticeList',
      'signStatus'
    ]),
    visitedViews() {
      return this.$store.state.tagsView.visitedViews
    }
  },
  created() {
    this.phoneLogin = this.signStatus == 'in' ? 0 : 1;
    this.init();
  },
  mounted() {
    this.bindHandler(this);
    // 获取通知
    this.$store.dispatch("GetNoticeList",{})
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('ToggleSideBar')
    },
    getIcon(logo){
      return logo !== '' ? process.env.IMAGE_API+logo : ''
    },
    // 更改签入签出状态
    phoneStatusChange(value){
      if(value == 0){ // 签入
        ws.send(JSON.stringify(Api.loginData));
        this.$store.dispatch("ChangeSignStatus",'in')
      }else{ // 签出
        ws.send(JSON.stringify(Api.logoutData));
        this.$store.dispatch("ChangeSignStatus",'out')
      }
    },
    logout() {
      this.$store.dispatch('LogOut').then(() => {
        location.reload() // 为了重新实例化vue-router对象 避免bug
      })
    },
    // 外呼
    callHandle(){
      const phoneReg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/
      const phoneNumber = this.phone;
      const testPhont = phoneNumber.substring(0,1) == '0' ? phoneNumber.substring(1,phoneNumber.length) : phoneNumber
      console.log(phoneNumber,testPhont)
      if (phoneNumber && phoneReg.test(testPhont)) {//此处应做号码的正则检查
        ws.send(JSON.stringify(Api.makeCallData(phoneNumber)));
      } else {
        this.$message.error('请输入正确的被叫手机号');
      }
    },
    // 挂机
    hangUpHandle(){
      var callId = agentObject.line1CallId;
      if (callId) {//此处的callid一定是对应通话的callID，若没有对应上，则一定会挂机失败。
        ws.send(JSON.stringify(Api.releaseCallData(callId)));
      } else {
        this.$message.error("当前座席不在通话中");
      }
    },
    init() {
      // let _this = this;
      // window.ws = new ReconnectingWebSocket(
      //   "ws://" + msg_host + ":" + msg_port + "/websocket",
      //   null,
      //   {
      //     debug: true,
      //     reconnectInterval: 5000,
      //   }
      // );

      // var http_url = "http://" + msg_host + ":" + http_url;

      // ws.onmessage = function (_msg) {
      //   Api.heartCheck.reset();
      //   var data = $.parseJSON(_msg.data); //回复事件的数据
      //   handMessage.callback(data);
      //   if(data.messageId == 581){ 
      //     // 监听座席签出了
      //     _this.$store.dispatch("ChangeSignStatus",'out')
      //     _this.phoneLogin = 1
      //   }else if(data.messageId == 580){ 
      //     // 监听座席签出了
      //     _this.$store.dispatch("ChangeSignStatus",'in')
      //     _this.phoneLogin = 0
      //   }
      //   if(window.turnFlag == 'turning'){ // 转接状态
      //     if(data.messageId == 515){
      //       // 如果呼叫释放 并且当前是转接状态 代表转接成功 关闭当前页面
      //       window.turnFlag = null
      //       let pageTags = _this.visitedViews.filter(m=>m.name == 'conversationInfo');
      //       if(pageTags.length > 0){
      //         _this.$store.dispatch('tagsView/delView', pageTags[0])
      //       }
      //       window.history.go(-1)
      //     }else if(data.messageId == 9999){
      //       // 转接失败
      //       window.turnFlag = null;
      //       _this.$message.error("转接目标座席为离线状态");
      //     }
      //   }
      //   if((data.messageId == 580 || data.messageId == 581) && _this.$route.path == '/home'){
          // 如果是首页签入或者签出更改页面
          // request({
          //   url:`${_this.http_urla}callcenter/queryAgentStateNum`,
          //   method:"post",
          //   data: {
          //     tenantId:targetTenantId.toString(),
          //     actionID:exten
          //   }
          // }).then(response => {
          //   // 查询座席总量
          //   $(".secondDataItem").eq(3).find('p:nth-child(3)').text(response.data[0].agentNum || 0);
          //   // 座席在线数量
          //   $(".secondDataItem").eq(4).find('p:nth-child(3)').text(response.data[0].loginNum || 0);
          //   // 座席离线数量
          //   $(".secondDataItem").eq(5).find('p:nth-child(3)').text(response.data[0].logoutNum || 0);
          // })
        // }
      //};
      // ws.onopen = function () {
      //   ws.send(JSON.stringify(Api.logoutData)); //签出

      //   setTimeout(function () {
      //     /*座席首次签入*/ console.log(JSON.stringify(Api.firstLoginData));
      //     ws.send(JSON.stringify(Api.firstLoginData));
      //   }, 1000);
      // };
      // ws.onerror = function (e) {
      //   //发生错误
      //   console.error("发生错误啦。。。", e);
      // };

      // ws.onclose = function () {
      //   //关闭连接
      //   console.warn("websocket.onclose");
      // };

      getInfo({
        pageSize:1,
        pageNum:1
      }).then((res)=>{
        if(res.data.list.length > 0){
          let data = res.data.list[0];
          this.$store.dispatch("SetSystemInfo",{
            name: data.enterpriseName,
            logo: data.enterpriseLogo
          })
        }
      })
    },
    bindHandler(_this) {
      //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = function () {
        ws.send(JSON.stringify(Api.stopMornitorData(agentDNs)));
        ws.send(JSON.stringify(Api.logoutData));
        ws.close();
      };

      $(window).bind("beforeunload", function () {
        ws.send(JSON.stringify(Api.logoutData));
        if(window.exportFlag == 1){
          setTimeout(function () {
            ws.send(JSON.stringify(Api.firstLoginData));
          }, 1000);
          window.exportFlag = null
        }
      });
    },
    // 查看通知
    noticeGo(){
      this.$router.push({
        path:"/workorder/workOrderList"
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  @media (max-width: 1300px) {
    .systemName{
      font-size: 16px!important;
      line-height:25px!important;
      max-width:13vw;
    }
  }
  @media (max-width: 1200px) {
    .systemName{
      font-size: 16px!important;
      line-height:25px!important;
      max-width:18vw;
    }
  }
  @media (max-width: 1100px) {
    .systemName{
      font-size: 16px!important;
      line-height:25px!important;
      max-width:15vw;
    }
  }
  @media (max-width: 1050px) {
    .systemName{
      font-size: 14px!important;
      line-height:20px!important;
      max-width:10vw!important;
    }
  }
  .navbar {
    height: 70px;
    line-height: 70px;
    border-radius: 0px !important;
    z-index:1002;
    position:fixed;
    top:0;
    left:0;
    width:100vw;
    display:flex;
    .hamburger-container {
      line-height: 80px;
      height: 70px;
      float: left;
      padding: 0 10px;
    }
    .logoBox{
      display: flex;
      align-items:center;
      justify-content:space-evenly;
      height: 70px;
      background:#0177D5;
      padding: 0 10px;
      flex-wrap: wrap;
      .logo{
        max-height:60px;
        max-width:100%;
      }
    }
    .navFlexBox{
      display: flex;
      align-items: center;
      justify-content: flex-start;
      flex-wrap:wrap;
      .systemName{
        line-height: 1.0;
        font-size:20px;
        color: #333333;
        font-weight:500;
        margin-right:1vw;
      }
      .userInfoBox{
        display:flex;
        align-items:center;
        padding:0 100px 0 1.5vw;
        cursor:pointer;
        .userLogo{
          margin: 0 0.5vw 0 1vw;
        }
        .welcome_text{
          margin: 0;
          font-size: 16px;
          color: #333333;
          white-space: nowrap;
          span{
            color: #333333;
            margin-left: 5px;
            font-weight: bold;
          }
        }
      }
      
      .navItem{
        margin-right:10px
      }
      .el-select--mini{
        width:65px;
        /deep/ .el-input__inner{
          border: none;
          color:#22AC38;
          font-weight:bold;
          font-size:14px;
          padding-left:8px;
        }
        /deep/ .el-select__caret{
          color: #22AC38;
        }
      }
      .navButton{
        max-width: 100px;
        width:7vw;
        min-width:65px;
        height: 30px;
        border-radius: 4px;
        color: #fff;
        padding: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        font-weight: bold;
        img{
          width: 10px;
          margin-right:5px
        }
      }
      .callUp{
        background: linear-gradient(#40B783,#92CE4D);
      }
      .hangUp{
        background: linear-gradient(#ED6380,#F09B5F);
      }
      .avatar-container {
        height: 70px;
        display: inline-block;
        position: absolute;
        right: 35px;
      }
      .rightOperation{
        position: absolute;
        top: 0;
        right: 0;
        height: 70px;
        display: flex;
        align-items: center;
        justify-content: space-evenly;
        width: 200px;
        line-height: normal;
        .noticeBox{
          i{
            font-size: 22px;
            color:#999999;
          }
        }
      }
    }
  }
  .quitBox{
    cursor:pointer;
    display: flex;
    align-items: center;
    font-size: 16px;
    color: #999999;
    font-weight: bold;
    justify-content:center;
    i{
      margin-right: 5px;
      font-weight: bold;
    }
  }
</style>

