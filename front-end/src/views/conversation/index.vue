<!--
 * @Descripttion: 会话管理
 * @Author: Dyl
 * @Date: 2021-11-16 13:56:03
-->
<template>
  <div class="conversation_container">
    <el-row :gutter="20">
      <el-col :span="15" class="infoBox">
        <div class="userInfoBox">
          <p class="userNameStyle">咨询者名称：{{userData.name}}</p>
          <div class="userTagsList">
            <el-tag effect="plain" style="margin-right:10px;margin-bottom:10px" size="small" v-for="(item,index) in userData.tips" :key="index">{{item}}</el-tag>
          </div>
          <div class="callTimesBox">
            <p>用户信息</p>
          </div>
        </div>
        <div class="archiveTabs">
          <div class="tabItem" @click="handleChangeTab(0)" :class="{'isActive':activeTab == 0}">客户与档案</div>
          <div class="tabItem" @click="handleChangeTab(1)" :class="{'isActive':activeTab == 1}">历史档案</div>
        </div>
        <div class="splitLine">档案基本信息</div>
        <el-descriptions direction="vertical" :column="4" border labelClassName="archiveInfoTitle" style="width: 95%;margin: auto;">
          <el-descriptions-item label="档案编号">{{userData.code}}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{userData.createdTime}}</el-descriptions-item>
          <el-descriptions-item label="部门">{{userData.deptName}}</el-descriptions-item>
          <el-descriptions-item label="处理座席">{{dealSeat}}</el-descriptions-item>
        </el-descriptions>
        <div class="splitLine">咨询者信息</div>
        <el-form  ref="form" :model="userData" class="userInfoDataBox" label-width="100px">
          <el-form-item label="咨询者名称">
            <el-input v-if="activeTab == 0" v-model="userData.name" size="mini"></el-input>
            <div v-else class="normalText">{{historyData.name}}</div>
          </el-form-item>
          <el-form-item label="联系人名称">
            <el-input v-if="activeTab == 0" v-model="memberData[0].username" size="mini"></el-input>
            <div v-else class="normalText">{{historyData.username}}</div>
          </el-form-item>
          <el-form-item label="联系人方式">
            <div v-if="activeTab == 0" style="display: flex;align-items: flex-start;">
              <div style="width: 200px;">
                <el-input v-for="(phones,index) in memberData" key="index" v-model="phones.phone" size="mini"></el-input>
              </div>
              <el-button icon="el-icon-plus" plain size="mini" @click="addNewUser" style="color:#ED6B7B;border-color: #ED6B7B;margin: 5px;">新增联系方式</el-button>
              <el-button icon="el-icon-plus" plain size="mini" @click="addNowUser" style="color:#0177D5;border-color: #0177D5;margin: 5px;">添加到现有用户</el-button>
            </div>
            <div v-else class="normalText">{{historyData.phone}}</div>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-if="activeTab == 0" v-model="userData.email" size="mini"></el-input>
            <div v-else class="normalText">{{historyData.email}}</div>
          </el-form-item>
          <el-form-item label="公司地址">
            <el-input v-if="activeTab == 0" v-model="userData.address" size="mini"></el-input>
            <div v-else class="normalText">{{historyData.address}}</div>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-if="activeTab == 0" v-model="userData.remark" type="textarea" size="mini"></el-input>
            <div v-else class="normalText">{{historyData.remark}}</div>
          </el-form-item>
          <div v-if="activeTab == 0">
            <div class="splitLine">档案内容</div>
            <el-form-item label="添加标签">
              <el-autocomplete 
                v-model="searchTipsText"
                size="mini"
                :fetch-suggestions="searchTips"
                :trigger-on-focus="false"
                @select="handleSelect"
                :maxlength="10"
              ></el-autocomplete>
              <el-button size="mini" @click="addTagsHandler" type="primary">添加</el-button>
              <div class="userTagsList">
                <el-tag effect="plain" @close="delTips(index)" closable style="margin-right:15px;" size="small" v-for="(item,index) in addContentData.tips" :key="index">{{item}}</el-tag>
              </div>
            </el-form-item>
            <el-form-item label="紧急程度">
              <el-select size="mini" v-model="addContentData.urgency" placeholder="请选择">
                <el-option
                  v-for="item in urgentList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="服务小结">
              <el-input v-model="addContentData.content" type="textarea" size="mini"></el-input>
            </el-form-item>
          </div>
        </el-form>
        <div class="historyBlock" v-if="activeTab == 1">
          <div class="splitLine">历史档案</div>
          <el-timeline style="padding-left: 20px;width: 90%;">
            <el-timeline-item v-for="(item,index) in userData.contents" :key="index" :timestamp="'创建 '+item.createdTime" placement="top">
              <el-card>
                <p class="historyText">{{item.content}}</p>
                <div class="historyInfoBox">
                  <div class="tagsBox">
                    <el-tag v-for="(tags,ind) in item.tips" size="mini" :key="ind" type="info">{{tags}}</el-tag>
                  </div>
                  <p>紧急程度：{{item.urgent}}</p>
                  <p>处理座席：{{item.sitDown}}</p>
                </div>
              </el-card>
            </el-timeline-item>
            <el-timeline-item v-for="(item,index) in mergeData.contents" :key="index" :timestamp="'创建 '+item.createdTime" placement="top">
              <el-card>
                <p class="historyText">{{item.content}}</p>
                <div class="historyInfoBox">
                  <div class="tagsBox">
                    <el-tag v-for="(tags,ind) in item.tips" size="mini" :key="ind" type="info">{{tags}}</el-tag>
                  </div>
                  <p>紧急程度：{{item.urgent}}</p>
                  <p>处理座席：{{item.sitDown}}</p>
                </div>
              </el-card>
            </el-timeline-item>
          </el-timeline>
        </div>
        <div style="text-align: center;padding: 30px 0 50px 0;">
          <el-button type="primary" style="width: 150px;" @click="saveDataHandler">保存</el-button>
        </div>
      </el-col>
      <el-col :span="9" class="callBox">
        <div class="callOperatBox">
          <div class="callInfo">
            <p class="callNumber">{{telNum}}</p>
            <p class="ownershipAdress">{{callData.address}}</p>
            <p class="talkTime" id="conversationTimes" :style="callStatus == 'end' ? 'color:#FF0005' : ''">{{callStatus == 'end' ? '通话结束' : getTime(callData.time)+'...'}}</p>
          </div>
          <div class="operations" id="conversationOperations">
            <div class="operationItem" v-if="callStatus != 'end'" @click="transferHandle">
              <img src="../../assets/images/call_zj.png"></img>
              <p>转接</p>
            </div>
            <div class="operationItem" @click="makeOrder">
              <img src="../../assets/images/call_xcgd.png"></img>
              <p>形成工单</p>
            </div>
            <div class="operationItem" v-if="callStatus != 'end'" @click="keepHandle">
              <img src="../../assets/images/keep.png"></img>
              <p>保持</p>
            </div>
            <div class="operationItem" v-if="callStatus != 'end'" @click="hangUp">
              <img src="../../assets/images/call_gd.png"></img>
              <p>挂断</p></p>
            </div>
          </div>
        </div>
        <div class="chatRecordBox">
          <div class="searchBox">
            <el-input placeholder="搜索库里的问题" v-model="searchKnoeledge" class="input-with-select">
              <el-button @click="queryKnowledge" slot="append" icon="el-icon-search">搜索</el-button>
            </el-input>
          </div>
          <div class="answerList">
            <div v-for="(item,index) in answerList">
              <el-popover
                :ref="'popover'+index"
                placement="right"
                title=""
                width="200"
                trigger="hover"
                :content="item.answer">
                <div slot="reference"  class="answerItem">{{item.answer}}</div>
              </el-popover>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
    <search-dialog v-if="showSearchDialog" @submit="chooseHandle" @close="closeSearchDialog" type="archives" title="添加现有客户"/>
    <Drewer v-if="showAddOrder" @close="closeAddOrder" type="creat" :archiveId="userData.id" :archiveData="workorderData"/>
  </div>
</template>
<script>
import Drewer from '../workorder/components/drawer.vue';
import {getArchiveByNum, editArchive, mergeArc , addArchiveContent, delArchive} from "@/api/archives.js"
import {getList,addTags} from "@/api/tags.js"
import { addMember, mergeMember, batchAddMember} from "@/api/member.js"
import { getKnowledgeList } from "@/api/knowledge.js"
import request from '@/utils/request'
import Api from '@/assets/js/api.js';
import SearchDialog from '@/components/SearchDialog/index.vue';
export default {
  data() {
    return {
      historyData:{},
      telNum:"13888888888",
      dealSeat:"1001",
      userData:{},
      memberData:[{
        username:"",
        phone:"",
      }],
      addContentData:{
        content:"",
        tips:[],
        urgency:0,
      },
      callData:{
        address:"黑龙江哈尔滨",
        time: 0,
        times:""
      },
      talkDetail:{
        time:"2019-02-28 02:16"
      },
      activeTab:0,
      searchTipsText:"",
      answerList:[],
      urgentList:[
        {
          value:0,
          label:"普通"
        },
        {
          value:1,
          label:"紧急"
        },
        {
          value:2,
          label:"非常紧急"
        }
      ],
      searchKnoeledge:"",
      http_urla:"http://" + msg_host + ":" + http_url,
      showSearchDialog:false,
      callStatus:"calling",
      showAddOrder:false,
      workorderData:{},
      mergeData:{}
    }
  },
  components:{
    SearchDialog,
    Drewer
  },
  computed: {
    visitedViews() {
      return this.$store.state.tagsView.visitedViews
    }
  },
  created() {
    this.telNum = this.$route.query.number;
    this.dealSeat = this.$route.query.sit;
    this.memberData[0]['phone'] = this.$route.query.number;
    // 获取档案信息
    this.getArchiveData();
    // 获取来电信息
    this.getCallData();
    if(!agentObject.line1CallId){
      this.callStatus = "end"
    }
  },
  beforeDestroy() {
    if(window.timer){
      clearInterval(window.timer)
    }
  }, 
  methods: {
    // 获取来电信息
    getCallData(){
      if(agentObject.line1CallId){
        window.timer = setInterval(()=>{
          this.callData['time'] = this.callData.time+1
        },1000)
      }
      // 获取来电历史次数
      request({
        url:`${this.http_urla}query/queryMobileNum`,
        method:"post",
        data: {
          tenantId:targetTenantId.toString(),
          actionID:exten,
          mobile:this.telNum,
          callType:2,
        }
      }).then(response => {
        this.callData['times'] = response.data
      })
      // 获取来电归属地
      request({
        url:`${this.http_urla}query/queryMobileAttribution`,
        method:"post",
        data: {
          actionID:exten,
          mobile:this.telNum,
        }
      }).then(response => {
        this.callData['address'] = response.data
      })
    },
    // 获取档案信息
    getArchiveData(){
      let _this = this;
      getArchiveByNum({
        phone:_this.telNum
      }).then((res)=>{
        let tags = [];
        _this.userData = res.data;
        _this.historyData = JSON.parse(JSON.stringify(res.data))
        _this.userData['tips'] = [];
        if(res.data.calls && res.data.calls.length > 0){
          let concatPhone = res.data.calls.filter(m=>m.phone == _this.memberData[0].phone)
          _this.memberData = concatPhone.length > 0 ? [concatPhone[0]] : _this.memberData;
          _this.historyData['username'] = res.data.calls[0].username;
          _this.historyData['phone'] = res.data.calls[0].phone;
        }
        _this.userData.contents.map((item)=>{
          item['tips'] = item.labels == '' ? [] : item.labels.split(",");
          item['urgent'] = item.urgency == 0 ? '普通' : (item.urgency == 1 ? '紧急' : '非常紧急')
          tags = tags.concat(item.tips)
        })
        for(let i=0;i<tags.length;i++){
          if(tags[i] != '' && tags.indexOf(tags[i]) === i){
            _this.userData['tips'].push(tags[i])
          }
        }
      }).catch(()=>{

      })
    },
    // 搜索标签
    searchTips(queryString, cb){
      getList({
        name:queryString,
        pageSize:50,
        pageNum:1,
        status:0
      }).then((res)=>{
        let data = res.data.list;
        data.map((item)=>{
          item['value'] = item.name
        })
        cb(data)
      })
    },
    // 选中标签
    handleSelect(item) {
      if(this.addContentData.tips.indexOf(this.searchTipsText) != -1){
        this.$message.error('请勿重复添加相同的标签');
        this.searchTipsText = ''
        return false;
      }
      this.searchTipsText = '';
      this.addContentData.tips.push(item.name)
    },
    // 删除已选标签
    delTips(index){
      this.addContentData.tips.splice(index,1)
    },
    // 新增标签
    addTagsHandler(){
      if(this.addContentData.tips.indexOf(this.searchTipsText) != -1){
        this.$message.error('请勿重复添加相同的标签');
        this.searchTipsText = ''
        return false;
      }
      addTags({
        name:this.searchTipsText,
        status:0
      }).then(()=>{
        this.addContentData.tips.push(this.searchTipsText)
        this.searchTipsText = ''
      })
    },
    // 保存档案信息
    saveDataHandler(){
      let _this = this;
      let data = {
        id:_this.userData.id,
        address:_this.userData.address,
        code:_this.userData.code,
        email:_this.userData.email,
        name:_this.userData.name,
        remark:_this.userData.remark
      }
      let memberData = _this.memberData;
      memberData.map(item=>{
        item['archivesId'] = _this.userData.id
        if(item.id){
          delete item.id
        }
      })
      let archiveContent = {
        sitDown:_this.dealSeat,
        type:1,// 呼叫类型：呼入
        archivesId:_this.userData.id,
        content:_this.addContentData.content,
        urgency:_this.addContentData.urgency,
        labels:_this.addContentData.tips.join(",")
      }
      if((archiveContent.urgency === '' || archiveContent.content === '') && (archiveContent.urgency !== '' || archiveContent.content !== '') ){
        this.$message.error("请完整添加服务小结信息");
      }else{
        if(this.mergeData.id){
          this.mergeArchive(this.mergeData)
        }
        Promise.all([batchAddMember(memberData),editArchive(data),addArchiveContent(archiveContent)]).then((res)=>{
          this.$message({
            type: 'success',
            message: '保存成功!'
          });
        })
        // let pageTags = this.visitedViews.filter(m=>m.name == 'conversationInfo');
        // if(pageTags.length > 0){
        //   this.$store.dispatch('tagsView/delView', pageTags[0])
        // }
        // window.history.go(-1)
      }
    },
    // 切换档案tab
    handleChangeTab(index){
      this.activeTab = index
    },
    // s设置时间格式
    getTime(time){
      let h,m,s;
      let timers = ''
      h = Math.floor(time/60/60%24);  
      m = Math.floor(time/60%60);  
      s = Math.floor(time%60);   
      if(h != ''){
        timers = h+'时'
        timers += m+'分'
      }else if(m != ''){
        timers += m+'分'
      }
      timers += s+'秒'
      return timers;
    },
    // 转接
    transferHandle(){
      var callId = agentObject.line1CallId;
      if (callId) {//此处的callid一定是对应通话的callID，若没有对应上，则一定会挂机失败。
        this.$prompt('请输入转接目的座席号', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          window.turnFlag = 'turning'
          if (value) {//此处应做号码的正则检查
            ws.send(JSON.stringify(Api.transferCallData(callId, value, this.telNum)))
          }else{
            window.turnFlag = null
          }
        }).catch(() => {});
      } else {
        this.$message.error("当前座席不在通话中");
      }
    },
    // 形成工单
    makeOrder(){
      this.workorderData = this.userData;
      this.workorderData['call'] = [
        {
          phone:this.memberData[0].phone,
          username:this.memberData[0].username
        }
      ]
      this.showAddOrder = true;
    },
    // 保持
    keepHandle(){
      var callId = agentObject.line1CallId;
      if (callId) {//此处的callid一定是对应通话的callID，若没有对应上，则一定会挂机失败。
          if(this.callStatus == "calling"){
            agentObject.holdDN = agentObject.otherDN
            agentObject.holdCallId = callId
            ws.send(JSON.stringify(Api.holdCallData(callId)));
            this.callStatus = "keeping"
          }else{
            ws.send(JSON.stringify(Api.retrieveCallData(callId)));
            this.callStatus = "calling"
          }
      } else {
        this.$message.error("当前座席不在通话中");
      }
    },
    // 挂断
    hangUp(){
      var callId = agentObject.line1CallId;
      if (callId) {//此处的callid一定是对应通话的callID，若没有对应上，则一定会挂机失败。
          ws.send(JSON.stringify(Api.releaseCallData(callId)));
          if(window.timer){
            clearInterval(window.timer)
          }
      } else {
        this.$message.error("当前座席不在通话中");
      }
    },
    // 搜索知识库
    queryKnowledge(){
      if(this.searchKnoeledge == ''){
        this.$message.error("请输入要搜索的问题！");
        return false;
      }
      getKnowledgeList({
        question:this.searchKnoeledge,
        pageSize:10,
        pageNum:1
      }).then((res)=>{
        this.answerList = res.data.list
      })
    },
    // 添加到现有联系人
    addNowUser(){
      this.showSearchDialog = true
    },
    // 新增联系人
    addNewUser(){
      if(this.memberData.filter(m=>m.phone == '').length > 0){
        this.$message.error("请完整输入联系方式后再次新增！");
        return false;
      }else{
        this.memberData.push({
          phone:''
        })
      }
    },
    // 关闭搜索弹窗
    closeSearchDialog(){
      this.showSearchDialog = false
    },
    // 搜索弹窗选择完毕
    chooseHandle(data){
      this.showSearchDialog = false;
      this.memberData.splice(0,1,{
        phone : data.calls.length > 0 ? data.calls[0].phone : '',
        username : data.calls.length > 0 ? data.calls[0].username : ''
      })
      this.userData['address'] = data.address;
      this.userData['email'] = data.email;
      this.userData['name'] = data.name;
      this.userData['remark'] = data.remark;
      this.historyData = data;
      this.mergeData = data
    },
    // 关闭创建工单
    closeAddOrder(data){
      this.showAddOrder = false;
    },
    // 合并档案
    mergeArchive(data){
      Promise.all([
        new Promise((resolve,reject)=>{
          if(data.calls.length > 0){
            data.calls.map((item)=>{
              item['archivesId'] = this.userData.id
            })
            mergeMember(data.calls).then(()=>{
              resolve()
            }).catch(()=>{
              reject()
            })
          }else{
            resolve()
          }
        }),
        new Promise((resolve,reject)=>{
          if(data.contents.length > 0){
            data.contents.map((item)=>{
              item['archivesId'] = this.userData.id
            })
            mergeArc(data.contents).then(()=>{
              resolve()
            }).catch(()=>{
              reject()
            })
          }else{
            resolve()
          }
        })
      ]).then(()=>{
        // 删除选择合并的档案
        delArchive({
          id:data.id
        }).then(()=>{})
      }).catch(()=>{
        
      })
    }
  },
}
</script>
<style lang="scss" scoped>
  .conversation_container{
    background: #F0F2F5;
    padding: 10px;
    .normalText{
      font-size:12px;
      color:#666666;
    }
    .infoBox {
      background: #fff;
      border-radius:4px;
      .userInfoBox{
        padding:0 30px 30px 20px;
        box-sizing: border-box;
        .userNameStyle{
          font-size: 14px;
          color:#262626;
          font-weight:bold;
          line-height:1.0;
          margin-top:12px
        }
        .userTagsList{
          display: flex;
          align-items: center;
          margin: 13px 0;
          flex-wrap:wrap;
        }
        .callTimesBox{
          width: 100%;
          height: 40px;
          display: flex;
          align-items: center;
          justify-content: space-between;
          background: #FCD8C9;
          border-radius: 4px;
          color: #EC6941;
          padding: 0 32px 0 12px;
          box-sizing: border-box;
          font-size: 14px;
          p{
            font-size: 16px;
            font-weight:bold;
          }
        }
      }
    }
    .archiveTabs{
      display: flex;
      align-items: center;
      justify-content: flex-start;
      padding: 0 5px;
      .tabItem{
        font-size: 14px;
        padding:10px 2px;
        color:#666666;
        margin: 0 20px;
        cursor: pointer;
        border-bottom: 4px solid #fff;
      }
      .tabItem.isActive{
        border-bottom: 4px solid #0177D5;
        color: #0177D5;
        font-weight: bold;
      }
    }
    .splitLine{
      width: 100%;
      height: 40px;
      box-sizing: border-box;
      padding: 0 28px;
      color: #0177D5;
      font-size: 12px;
      font-weight: bold;
      margin: 20px 0; 
      background: #E3F2FE;
      display: flex;
      align-items: center;
    }
    /deep/ .archiveInfoTitle{
      background: #E3F2FE;
      color: #0177D5!important;
    }
    /deep/ .el-descriptions .is-bordered .el-descriptions-item__cell{
      border: 1px solid #0177D5;
      font-size: 12px;
      text-align: center;
      color: #666666;
    }
    .userInfoDataBox{
      /deep/ .el-form-item__label{
        font-size: 12px;
        color:#666666;
      }
      /deep/ .el-input{
        width: 200px;
      }
      /deep/ .el-textarea{
        width: 400px;
      }
    }
    .historyBlock{
      ul{
        margin:20px 0;
        padding-left:0;
      }
      .historyText{
        line-height:24px;
        font-size:14px;
        color:#767676;
        margin-bottom:20px;
      }
      .historyInfoBox{
        display:flex;
        align-items:center;
        justify-content:flex-start;
        .tagsBox{
          margin-right:30px;
          .el-tag{
            margin-right:10px;
          }
        }
        p{
          margin-right:30px;
          font-size:12px;
          color:#505050;
        }
      }
    }
    .callOperatBox{
      padding:25px;
      box-sizing: border-box;
      width: 100%;
      background: url("../../assets/images/call_bg.png") no-repeat;
      background-size: cover;
      .waittingNum{
        font-size: 14px;
        color:#89CB53;
      }
      .callInfo{
        display: flex;
        justify-content:center;
        flex-wrap: wrap;
        p{
          width: 100%;
          text-align:center;
          margin:auto;
        }
        .callNumber{
          margin-top: 20px;
          font-size: 36px;
          font-weight: bold;
          line-height: 2.0;
          color: #fff;
        }
        .ownershipAdress{
          font-size: 18px;
          color:#fff;
          line-height: 2.0;
        }
        .talkTime{
          font-size: 18px;
          color: #89CB53;
          line-height: 2.0;
        }
      }
      .operations{
        display: flex;
        align-items: space-around;
        justify-content: space-around;
        flex-wrap: wrap;
        margin: 20px 0;
        .operationItem{
          text-align: center;
          margin:15px 10px;
          img{
            width: 60px;
            height: 60px;
            margin-bottom:7px
          }
          p{
            font-size: 12px;
            color: #fff;
            line-height: 1.0;
          }
        }
      }
    }
    .chatRecordBox{
      border-radius: 4px;
      background: #fff;
      margin-top: 20px;
      overflow: hidden;
      .timeBox{
        width: fit-content;
        padding:10px 13px;
        background:#C1C1C1;
        font-size:12px;
        color:#fff;
        margin:20px auto;

      }
      .searchBox{
        width: 90%;
        margin: 0 auto;
        padding: 20px 0 0;
        /deep/ .el-input-group{
          box-shadow: 0px 0px 10px rgba(1,119,213,0.43);
          border-radius:4px
        }
        /deep/ .el-input-group__append{
          background: #0177D5;
          color:#fff;
        }
      }
      .answerList{
        width: 90%;
        margin: 10px auto;
        max-height: 300px;
        overflow-y: auto;
        .answerItem{
          width: 100%;
          border-bottom:1px solid #E5E5E5;
          padding:15px 0 25px;
          font-size:12px;
          line-height:1.0;
          white-space: nowrap;
          height: 42px;
          overflow: hidden;
          color:#999999;
          text-overflow: ellipsis;
        }
      }
    }
  }
</style>