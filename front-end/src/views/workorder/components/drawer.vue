<template>
  <div>
    <el-drawer
      :visible.sync="visivle"
      direction="rtl"
      :before-close="handleClose"
      :close-on-press-escape="false"
      :size="pageWidth"
      custom-class="order_drawer"
      :wrapperClosable="false">
      <div class="order_container">
        <div class="topCodeBox">
          <span style="margin-right: 10px;">工单编号{{orderData.code}}</span>
          <span v-if="orderData.archivesCode">档案编号{{orderData.archivesCode}}</span>
          <el-button size="mini" v-if="!orderData.archivesCode && pageStatus != 'examine'" type="primary" icon="el-icon-zoom-in" @click="addArchive">添加档案</el-button>
        </div>
        <div class="topButtonsBox">
          <el-button size="mini" @click="isLocked = !isLocked" v-if="pageStatus == 'examine'" style="z-index:1000;" icon="el-icon-lock">{{isLocked ? '解锁' : '锁定'}}</el-button>
          <el-button size="mini" v-if="pageStatus != 'creat'" @click="getWorkOrderDetail" icon="el-icon-refresh">刷新</el-button>
          <el-button size="mini" @click="changeWindowSize" icon="el-icon-rank">{{pageWidth == '100%' ? '取消' : ''}}全屏</el-button>
          <el-button size="mini" v-if="pageStatus == 'examine'" @click="viewCallLog" icon="el-icon-phone">查看通话记录</el-button>
        </div>
        <div class="mainContainer">
          <div class="tagBox">
            <div v-if="pageStatus != 'examine'">
              <el-autocomplete 
                v-model="searchTipsText"
                size="mini"
                :fetch-suggestions="searchTips"
                :trigger-on-focus="false"
                @select="handleSelect"
                placeholder="请输入标签名称"
                style="width: 300px;"
                :maxlength="10"
              ></el-autocomplete>
              <el-button size="mini" @click="addTagsHandler" icon="el-icon-plus" type="primary">添加</el-button>
            </div>
            <div class="userTagsList">
              <el-tag v-for="(item,index) in orderData.tips" :key="index" effect="plain" @close="delTips(index)" :closable="pageStatus != 'examine'" style="margin-right:5px;" size="small">{{item}}</el-tag>
            </div>
          </div>
          <el-row>
            <el-col :span="16">
              <div class="makerInfo">
                <el-descriptions :column="2">
                  <el-descriptions-item label="发起人">{{orderData.creator}} 座席{{orderData.createCode}}</el-descriptions-item>
                  <el-descriptions-item label="发起人所属部门">{{orderData.createDept}}</el-descriptions-item>
                  <el-descriptions-item label="最后修改时间">{{orderData.updateTime}}</el-descriptions-item>
                  <el-descriptions-item label="业务类型">工单流转</el-descriptions-item>
                </el-descriptions>
              </div>
              <div class="orderDataBox">
                <div class="archiveTabs">
                  <div class="tabItem" v-for="item in tabList" @click="handleChangeTab(item.id)" :class="{'isActive':activeTab == item.id}">{{item.name}}</div>
                </div>
                <div v-if="activeTab == 0" class="detailBox">
                  <p class="titleText">基本信息</p>
                  <el-form ref="form" :inline="true" size="mini" :model="orderData" label-width="100px">
                    <el-form-item label="企业名称">
                      <el-input v-if="pageStatus != 'examine'" v-model="orderData.name"></el-input>
                      <div v-else style="width: 100%;">{{orderData.name}}</div>
                    </el-form-item>
                    <el-form-item label="企业电话">
                      <el-input :disabled="pageStatus == 'examine'" v-model="orderData.phone"></el-input>
                    </el-form-item>
                    <el-form-item label="档案编号">
                      <div>{{orderData.archivesCode || '暂无'}}</div>
                    </el-form-item>
                    <el-form-item label="企业联系人">
                      <el-input :disabled="pageStatus == 'examine'" v-model="orderData.concat"></el-input>
                    </el-form-item>
                    <el-form-item label="工单编号">
                      <div style="width: 100%;">{{orderData.code || '暂无'}}</div>
                    </el-form-item>
                    <el-form-item label="企业邮箱">
                      <el-input :disabled="pageStatus == 'examine'" v-model="orderData.email"></el-input>
                    </el-form-item>
                    <el-form-item label="工单创建日期">
                      <div style="width: 100%;">{{orderData.createTime}}</div>
                    </el-form-item>
                    <el-form-item label="截止日期">
                      <el-date-picker
                        value-format="yyyy-MM-dd HH:mm:ss"
                        :disabled="pageStatus == 'examine'"
                        v-model="orderData.submitTime"
                        type="datetime"
                        placeholder="选择日期"
                        :picker-options="pickerOptions"
                        style="width:100%">
                      </el-date-picker>
                    </el-form-item>
                    <el-form-item label="企业地址">
                      <el-input v-if="pageStatus != 'examine'" v-model="orderData.addr"></el-input>
                      <div v-else style="width: 100%;">{{orderData.addr}}</div>
                    </el-form-item>
                    <el-form-item label="工单备注信息">
                      <el-input :disabled="pageStatus == 'examine'" v-model="orderData.remark" resize="none" type="textarea"></el-input>
                    </el-form-item>
                    <el-form-item label="档案备注信息">
                      <el-input :disabled="pageStatus == 'examine'" v-model="orderData.archivesRemark" resize="none" type="textarea"></el-input>
                    </el-form-item>
                  </el-form>
                  <div class="systemInfo">
                    <p class="titleText">系统信息</p>
                    <el-descriptions :column="2">
                      <el-descriptions-item label="负责人">{{orderData.dealer}} 座席{{orderData.dealerCode}}</el-descriptions-item>
                      <el-descriptions-item label="发起人所属部门">{{orderData.createDept}}</el-descriptions-item>
                      <el-descriptions-item label="工单状态">{{orderData.statusText}}</el-descriptions-item>
                      <el-descriptions-item label="最后修改时间">{{orderData.updateTime}}</el-descriptions-item>
                    </el-descriptions>
                  </div>
                </div>
                <div v-if="activeTab == 1" class="editHistoryList">
                  <div class="editHistoryItem" v-for="item in orderData.operateLogs">
                    <div class="editTime">{{item.createTime}}</div>
                    <img :src="getIcon(item.headImg)" class="editIcon">
                    <p class="normalText">{{item.memberName}} {{item.operateType}}了{{item.module}} {{item.operateContent}}</p>
                  </div>
                  <p v-if="!orderData.operateLogs || orderData.operateLogs.length == 0" class="noDataText">暂无修改记录</p>
                </div>
                <div v-if="activeTab == 2" class="editHistoryList">
                  <div class="editHistoryItem" v-for="item in orderData.approveList">
                    <div class="editTime">{{item.updateTime || item.createdTime}}</div>
                    <img :src="getIcon(item.approveStatus != 5 ? item.toHeadImg : item.fromHeadImg)" class="editIcon">
                    <p class="normalText">{{item.approveStatus != 5 ? item.toName : item.fromName }} {{item.approveStatusText}} <span v-if="item.approveText && item.approveText !=''" style="word-break:break-all">备注：{{item.approveText}}</span></p>
                  </div>
                  <p v-if="!orderData.approveList || orderData.approveList.length == 0" class="noDataText">暂无审批流程</p>
                </div>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="orderProcessBox">
                <div class="orderStatusBox">{{orderData.statusText}}</div>
                <div class="dealInfo">
                  <el-avatar size="large" v-if="orderData.approveList && orderData.approveList.length != 0" :src="getIcon(orderData.approveList[0].toHeadImg)"></el-avatar>
                  <el-avatar size="large" v-else :src="getIcon(headimg)"></el-avatar>
                  <div>
                    <p>{{orderData.dealer}}</p>
                    <p>{{orderData.updateTime}}</p>
                  </div>
                </div>
                <p class="titleText">工单流转</p>
                <p class="normalText">新建 工单编号：{{orderData.code}}</p>
                <p class="titleText">工单流程</p>
                <div class="operaList">
                  <div class="operaItem" v-for="(item,index) in orderData.approveList">
                    <div class="dealUserInfo">
                      <img :src="getIcon(item.approveStatus > 3 ? item.fromHeadImg : item.toHeadImg)" alt="" />
                      <div>
                        <span>{{item.approveStatus > 3 ? item.fromName :item.toName}}</span>
                        <span>{{item.approveStatus > 3 ? item.fromDept :item.toDept}}</span>
                        <span>{{item.approveStatusText}}</span>
                        <span v-if="item.approveStatus == 4">到{{item.toName}}</span>
                      </div>
                    </div>
                    <div class="operatButtons" v-if="index == 0  && (orderData.status < 2 || orderData.status > 3)">
                      <el-button size="mini" v-if="item.approveStatus == 0 && item.toId == userId" @click="dealOrderHandle(item,'1')">接收</el-button>
                      <el-button size="mini" v-if="item.approveStatus == 0 && item.toId == userId" @click="dealOrderHandle(item,'2')">拒绝接收</el-button>
                      <el-button size="mini" v-if="item.toId == userId && item.approveStatus == 1" @click="dealOrderHandle(item,'3')">结单</el-button>
                      <el-button size="mini" v-if="item.fromId == userId && item.toId != userId && item.approveStatus < 1" @click="dealOrderHandle(item,'6')">催办</el-button>
                      <el-button size="mini" v-if="item.toId == userId && item.approveStatus != 3" @click="showOperatInput(item.approveText)">{{showInput ? '取消' : ''}}打备注</el-button>
                    </div>
                    <div class="dealBox">
                      <p class="normalText">
                        {{item.approveStatusText}} {{item.approveStatus > 3 ? item.fromDept :item.toDept}} {{item.updateTime || item.createdTime}}
                      </p>
                      <p v-if="(item.toId != userId || !showInput || index != 0) && item.approveText && item.approveText !=''" class="remark">备注：{{item.approveText}}</p>
                      <el-input v-if="showInput && index == 0" v-model="remarkText" resize="none" type="textarea" maxlength="100" placeholder="请输入您的操作备注"></el-input>
                    </div>
                  </div>
                </div>
                <el-button v-if="
                  orderData.approveList && // 有流程处理数据
                  orderData.approveList.length > 1 &&  // 流程数据多于一条才可以撤回
                  orderData.approveList[0].fromId == userId && // 只有指派人才可以撤回
                  orderData.approveList[0].approveStatus == 0 && // 最新一条数据状态不能为处理完毕
                  orderData.approveList[1].approveStatus != 2 && // 上一次处理不能为拒绝接收
                  orderData.approveList[1].approveStatus != 5 && // 上一次处理不能为撤回审批 工单不能为已完成/作废
                  (orderData.status < 2 || orderData.status > 3)"
                  @click="dealOrderHandle(null,'5')"
                >撤回审批</el-button>
              </div>
            </el-col>
          </el-row>
          <div class="bottomButtonBox" v-if="orderData.status != 3 && orderData.status != 2">
            <el-button @click="doEdit" v-if="pageStatus == 'examine' && !isLocked">编辑</el-button>
            <el-button @click="saveInfo" v-if="pageStatus != 'examine' && !isLocked" >保存</el-button>
            <el-button @click="delOrder" v-if="pageStatus != 'creat'">作废</el-button>
            <el-button @click="changeDealer"  v-if="((orderData.approveList && orderData.approveList.length != 0 && orderData.approveList[0].toId == userId) || (orderData.createId == userId && pageStatus == 'creat'))">
              {{pageStatus == 'creat' ?  '指派' : '更换'}}负责人
            </el-button>
          </div>
        </div>
      </div>
    </el-drawer>
    <search-dialog v-if="showSearchDialog" @submit="chooseHandle" @close="closeSearchDialog" type="archives" title="添加档案"/>
    <el-dialog
      title="提示"
      :visible.sync="showPersonDialog"
      width="40%"
      :before-close="closePersonnDialog">
      <el-cascader :props="dealerList" ref="dealerValue" :options="deptList"></el-cascader>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closePersonnDialog">取 消</el-button>
        <el-button type="primary" @click="dealOrderHandle(null,'4')">确 定</el-button>
      </span>
    </el-dialog>
    <CallLogDetail v-if="dialogVisible" @close="dialogVisible = false" :dialogData="dialogData" :soundData="soundData"/>
  </div>
</template>
<script>
import {getList,addTags} from "@/api/tags.js"
import { mapGetters } from 'vuex'
import { getSitNum } from '@/utils/auth';
import { getOrderDetail, creatOrder, changeOrderDealer, deleteOrder, editOrder, dealOrder, bindlabel } from '@/api/workorder.js'
import { formatDate } from '@/utils/date.js'
import SearchDialog from '@/components/SearchDialog/index.vue';
import { getDeptList  } from '@/api/department'
import { fetchList } from '@/api/login';
import {getArchiveDetail,getArchiveDetailByCode} from "@/api/archives.js"
import CallLogDetail from "@/components/CallLogDetail";
import request from '@/utils/request'
export default {
  data() {
    let _this = this;
    return {
      pageWidth: "80%",
      pageStatus:'',
      activeTab:0,
      sitNum:getSitNum(),
      searchTipsText:'',
      orderData:{
        tips:[],
        addr:'',
        archivesCode:'',
        archivesRemark:'',
        concat:'',
        content:'',
        createId:'',
        dealerId:'',
        email:'',
        name:'',
        phone:'',
        remark:'',
        title:'',
        type:0,
        submitTime:''
      },
      visivle:true,
      tabList:[
        {
          name:"详细信息",
          id:0
        },
        {
          name:"修改记录",
          id:1
        },
        {
          name:"审批流程",
          id:2
        }
      ],
      showSearchDialog:false,
      showPersonDialog:false,
      deptList:[],
      dealerList:{
        placeholder:'请选择负责人',
        separator:'-',
        lazy: true,
        lazyLoad (node, resolve) {
          _this.getDealersList(node.value).then((res)=>{
            resolve(res)
          })
        }
      },
      isLocked:false,
      showInput:false,
      remarkText:"",
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() < new Date(new Date().toLocaleDateString()).getTime();
        },
      },
      pageLoading:false,
      archiveCreatTime:"",
      msg_host:msg_host,
      sound_url:sound_url,
      http_urla:"http://" + msg_host + ":" + http_url,
      dialogVisible: false,
      dialogData:{},
      soundData:{},
    }
  },
  components:{
    SearchDialog,
    CallLogDetail
  },
  props:{
    type:{
      default:null,
    },
    code:{
      default:null,
    },
    archiveId:{
      default:null,
    },
    archiveData:{
      default:null
    }
  },
  created() {
    this.pageStatus = this.type;
    if(this.pageStatus == 'examine'){
      this.getWorkOrderDetail();
    }else if(this.pageStatus == 'creat'){ // 创建工单
      let date = formatDate(new Date(),'yyyy-MM-dd');
      this.orderData['createTime'] = date
      this.orderData['updateTime'] = date
      this.orderData['createId'] = this.userId
      this.orderData['createDept'] = this.deptMent
      this.orderData['creator'] = this.name
      this.orderData['createCode'] = this.sitNum
      this.orderData['statusText'] = '未处理'
    }
    this.getDeptList();
    if(this.archiveId){
      this.bindArchives()
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'deptMent',
      'userId',
      'headimg'
    ])
  },
  methods: {
    // 直接绑定已有档案
    bindArchives(){
      let _this = this;
      getArchiveDetail({
        id:_this.archiveId
      }).then((res)=>{
        _this.chooseHandle(res.data)
        if(_this.archiveData){ // 覆盖档案数据
          _this.chooseHandle(_this.archiveData)
        }
      })
    },
    // 获取部门列表
    getDeptList() {
      getDeptList({
        pageNum: 1,
        pageSize: 100,
        getDeptList:0
      }).then(response => {
        response.data.list.map((item)=>{
          item['value'] = item.deptId
          item['label'] = item.deptName
        })
        this.deptList = response.data.list;
      });
    },
    // 获取部门下员工列表
    getDealersList(id) {
      return new Promise((resolve,reject)=>{
        fetchList({
          pageNum:1,
          pageSize:100,
          deptId:id,
          status:-1,
        }).then(response => {
          response.data.list.map((item)=>{
            item['value'] = item.code
            item['label'] = item.username
            item['leaf'] = true
          })
          resolve(response.data.list);
        });
      })
    },
    // 获取工单详情
    getWorkOrderDetail(){
      let _this = this;
      _this.showInput = false;
      _this.remarkText = '';
      getOrderDetail({
        worderCode:_this.code
      }).then((res)=>{
        res.data['tips'] = res.data.label ? res.data.label.split(",") : [];
        _this.orderData = res.data;
        let status = _this.orderData.status;
        _this.orderData['statusText'] = status == '0' ? '未处理' : (status == '1' ? '处理中' : (status == '2' ? '已完成' : (status == '3' ? '作废' : '超时未处理')))
        // 插入最后一条创建的数据
        _this.orderData.approveList.push({
          approveStatus:'init',
          toHeadImg:_this.orderData.approveList[_this.orderData.approveList.length-1].fromHeadImg,
          toName:_this.orderData.approveList[_this.orderData.approveList.length-1].fromName,
          toDept:_this.orderData.approveList[_this.orderData.approveList.length-1].fromDept,
          toId:_this.orderData.approveList[_this.orderData.approveList.length-1].fromId,
          updateTime:_this.orderData.approveList[_this.orderData.approveList.length-1].updateTime,
          createdTime:_this.orderData.approveList[_this.orderData.approveList.length-1].createdTime,
        })
        // 循环流程数据 往流程树内添加叶子
        for(let i=0;i<_this.orderData.approveList.length;i++){
          let item = _this.orderData.approveList[i];
          // 添加状态描述字段
          item['approveStatusText'] = item.approveStatus == 'init' ? '创建了工单' : item.approveStatus == '0' ? '待接收' : (item.approveStatus == '1' ? '已接收' : (item.approveStatus == '2' ? '拒绝接收' : (item.approveStatus == '3' ? '处理完毕' : (item.approveStatus == '4' ? '更换负责人' : (item.approveStatus == '5' ? '撤回审批' : '作废工单')))))
          if(item.approveText == '作废产生'){
            // 此种情况代表作废产生的多余数据 删除此条数据 并把下一条数据的作废人变成此条数据的处理人
            _this.orderData.approveList[i+1]['fromHeadImg'] = _this.orderData.approveList[i].fromHeadImg,
            _this.orderData.approveList[i+1]['fromDept'] = _this.orderData.approveList[i].fromDept,
            _this.orderData.approveList[i+1]['fromId'] = _this.orderData.approveList[i].fromId,
            _this.orderData.approveList[i+1]['fromName'] = _this.orderData.approveList[i].fromName,
            _this.orderData.approveList.splice(i,1);
            i--;
          }  
          // 本条数据不是转办数据 添加此条数据的中间处理过程
          if(item.approveStatus != 4 && i!=_this.orderData.approveList.length -1){
            // 本条数据处理人不是下一条数据的处理人 下一条数据不是拒绝接收不是撤回审批的情况 新增中间转办数据
            if(item.toId != _this.orderData.approveList[i+1].toId && _this.orderData.approveList[i+1].approveStatus != 2 && _this.orderData.approveList[i+1].approveStatus != 5){
              _this.orderData.approveList.splice(i+1,0,{
                approveStatus:4,
                approveStatusText:'转办',
                fromHeadImg:item.fromHeadImg,
                fromDept:item.fromDept,
                fromId:item.fromId,
                fromName:item.fromName,
                toHeadImg:item.toHeadImg,
                toDept:item.toDept,
                toId:item.toId,
                toName:item.toName,
                updateTime:item.updateTime,
                createdTime:item.createdTime
              })
            }
          }else if(item.approveStatus == 4 && item.fromId == item.toId){
            // 如果本条数据发起人和处理人相同并且状态为转办 删除数据 多余数据
            _this.orderData.approveList.splice(i,1);
            i--;
          }
                  
        }
        _this.orderData['updateTime'] = res.data.updateTime || formatDate(new Date(),'yyyy-MM-dd');
      })
    },
    // 关闭此弹窗
    handleClose(){
      this.$emit("close")
      if(!this.archiveData){
        let path = this.$route.path; //先获取路由路径
        this.$router.push(path);
      }
    },
    getIcon(logo){
      return logo !== '' ? process.env.IMAGE_API+logo : ''
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
      if(this.orderData.tips.indexOf(this.searchTipsText) != -1){
        this.$message.error('请勿重复添加相同的标签');
        this.searchTipsText = ''
        return false;
      }
      this.searchTipsText = '';
      this.orderData.tips.push(item.name)
    },
    // 删除已选标签
    delTips(index){
      this.orderData.tips.splice(index,1);
    },
    // 新增标签
    addTagsHandler(){
      if(this.orderData.tips.indexOf(this.searchTipsText) != -1){
        this.$message.error('请勿重复添加相同的标签');
        this.searchTipsText = ''
        return false;
      }
      addTags({
        name:this.searchTipsText,
        status:0
      }).then(()=>{
        this.orderData.tips.push(this.searchTipsText)
        this.searchTipsText = ''
      })
    },
    // 切换tab
    handleChangeTab(index){
      this.activeTab = index
    },
    // 添加档案
    addArchive(){
      this.showSearchDialog = true
    },
    // 关闭搜索弹窗
    closeSearchDialog(){
      this.showSearchDialog = false
    },
    // 搜索弹窗选择完毕
    chooseHandle(data){
      this.showSearchDialog = false;
      this.orderData['name'] = data.name;
      this.orderData['phone'] = data.calls.length > 0 ? data.calls[0].phone : data.phone;
      this.orderData['concat'] = data.calls.length > 0 ? data.calls[0].username : '';
      this.orderData['email'] = data.email;
      this.orderData['addr'] = data.address;
      this.orderData['archivesCode'] = data.code;
      this.orderData['archivesRemark'] = data.remark;
    },
    // 编辑工单信息
    doEdit(){
      this.pageStatus = 'editing'
    },
    // 添加/编辑工单信息
    saveInfo(){
      if(this.pageLoading){
        return false
      }else{
        this.pageLoading = true
      }
      let data = JSON.parse(JSON.stringify(this.orderData))
      data['label'] = data.tips.join(",")
      if(this.pageStatus == 'editing'){ // 编辑
        delete data['operateLogs'];
        delete data['approveList'];
        delete data['tips'];
        data['updateTime'] = '';
        data['type'] = data.type || 0;
        data['content'] = data.content || 0;
        data['title'] = data.title || 0;
        editOrder(data).then(()=>{
          this.pageLoading = false
          this.$message({
            message: '修改工单成功',
            type: 'success'
          });
          this.getWorkOrderDetail();
          this.pageStatus = 'examine'
          if(data.label != ''){
            bindlabel({
              label:data.label,
              worderNo:this.orderData.code
            })
          }
        }).catch(()=>{
          this.pageLoading = false
        })
      }else{ // 新增
        let data = {
          addr: this.orderData.addr,
          archivesCode: this.orderData.archivesCode,
          archivesRemark: this.orderData.archivesRemark,
          concat: this.orderData.concat,
          content: '1',
          createId:this.orderData.createId,
          dealerId:this.orderData.dealerId,
          email: this.orderData.email,
          submitTime: this.orderData.submitTime,
          name: this.orderData.name,
          phone: this.orderData.phone,
          remark: this.orderData.remark,
          title: '1',
          type: this.orderData.type,
          label:this.orderData.tips.join(",")
        }
        if(!data.dealerId || data.dealerId == ''){
          this.$message.error('请指派负责人后保存');
          this.pageLoading = false
          return false;
        }
        if(!data.archivesCode || data.archivesCode == ''){
          this.$message.error('请关联档案后创建工单');
          this.pageLoading = false
          return false;
        }
        creatOrder(data).then((res)=>{
          this.pageLoading = false
          this.$message({
            message: '创建工单成功',
            type: 'success'
          });
          this.$emit("close",{restart:1})
          if(data.label != ''){
            bindlabel({
              label:data.label,
              worderNo:res.data.code
            })
          }
        }).catch(()=>{
          this.pageLoading = false
        })
      }
    },
    // 更换负责人
    changeDealer(){
      if(this.isLocked){
        this.$message.error('请解锁后操作');
        return false;
      }
      this.showPersonDialog = true;
    },
    // 取消更换负责人
    closePersonnDialog(){
      this.showPersonDialog = false;
    },
    // 确定更换负责人
    submitDealer(){
      let checkedNodes = this.$refs.dealerValue.getCheckedNodes();
      this.orderData['dealerId'] = checkedNodes[0].data.id;
      this.orderData['dealerCode'] = checkedNodes[0].value;
      this.orderData['dealer'] = checkedNodes[0].label;
      this.showPersonDialog = false;
      if(this.pageStatus != 'creat'){
        changeOrderDealer({
          dealerCode:checkedNodes[0]['value'],
          worderNo:this.orderData.code
        }).then(()=>{
          this.$message({
            message: '更换负责人成功',
            type: 'success'
          });
          this.getWorkOrderDetail();
        })
      }
    },
    // 作废工单
    delOrder(){
      if(this.isLocked){
        this.$message.error('请解锁后操作');
        return false;
      }
      this.$confirm('是否确认作废工单', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteOrder({
          worderCode:this.orderData.code
        }).then(()=>{
          this.$message({
            message: '作废工单成功',
            type: 'success'
          });
          this.$emit("close",{restart:1})
        })
      }).catch(() => {});
    },
    // 处理工单
    dealOrderHandle(data,code){
      if(this.isLocked){
        this.$message.error('请解锁后操作');
        return false;
      }
      let toCode = "";
      if(code == 4){
        let checkedNodes = this.$refs.dealerValue.getCheckedNodes();
        if(checkedNodes[0].data.id == this.userId){
          this.$message.error('不能更换工单负责人到自己');
          return false;
        }
        this.orderData['dealerId'] = checkedNodes[0].data.id;
        this.orderData['dealerCode'] = checkedNodes[0].value;
        this.orderData['dealer'] = checkedNodes[0].label;
        this.showPersonDialog = false;
        toCode = checkedNodes[0].value;
        if(this.pageStatus == 'creat'){ // 如果还没创建好工单 先存储处理人id 不发请求
          return false;
        }
      }
      if(code == 5){
        toCode = this.sitNum
      }else if(code == 6){
        toCode = data.toCode
      }
      dealOrder({
        approveAction:code,
        approveText:this.remarkText,
        wOrderNO:this.orderData.code,
        toCode,
      }).then((res)=>{
        this.$message({
          message: '工单操作成功',
          type: 'success'
        });
        this.getWorkOrderDetail();
      })
    },
    // 打备注
    showOperatInput(data){
      this.showInput = !this.showInput;
      if(this.showInput){
        this.remarkText = data;
      }else{
        this.remarkText = '';
      }
    },
    // 更改窗口大小
    changeWindowSize(){
      if(this.pageWidth == '80%'){
        this.pageWidth = '100%'
      }else{
        this.pageWidth = '80%'
      }
    },
    // 查看通话记录
    viewCallLog(){
      let archiveCode = this.orderData.archivesCode;
      let _this = this;
      getArchiveDetailByCode({
        code:this.orderData.archivesCode
      }).then((archiveData)=>{
        let endTime = _this.orderData.createTime; // 获取工单创建时间为结束时间
        let startTime = formatDate(new Date(new Date(endTime).getTime() - (60*5*1000)),'yyyy-MM-dd hh:mm:ss'); // 获取工单创建前五分钟为开始时间
        let calls = archiveData.data.calls
        // 获取开始时间到结束时间中间的最近通话
        request({
          url:`${_this.http_urla}query/queryCdrs`,
          method:"post",
          data: {
            tenantId:targetTenantId.toString(),
            actionID:exten,
            startTime,
            endTime
          }
        }).then(response => {
          let callLogList = response.data;
          let archiveCallLog = [];
          callLogList.map((item)=>{
            item['phoneNum'] = (item.ctiCallType == 1 || item.ctiCallType == 2) ? item.callerIdNumber : item.destinationNumber
            if(calls.filter(m=>m.phone == item.phoneNum).length > 0){
              archiveCallLog.push(item)
            }
          })
          if(archiveCallLog.length > 0){
            request({
              url:`${_this.http_urla}query/queryRecord`,
              method:"post",
              data: {
                tenantId:targetTenantId.toString(),
                actionID:exten,
                id:archiveCallLog[0].ctiCallId
              }
            }).then(response => {
              _this.soundData = response.data
            })
            _this.dialogVisible = true;
            _this.dialogData = archiveCallLog[0];
          }else{
            this.$message({
              message: '暂无通话',
              type: 'success'
            });
          }
        })
      })
    }
  },
}
</script>
<style lang="scss" scoped>
  .noDataText{
    font-size: 14px;
    color: #666666;
    text-align: center;
  }
  /deep/ .order_drawer{
    background: #F4F4F4;
    .el-drawer__body{
      overflow: initial;
    }
  }
  .order_container{
    position: relative;
    padding: 0 20px;
    .titleText{
      font-size: 12px;
      color:#666666;
      margin:20px 0;
      font-weight:bold;
    }
    .normalText{
      font-size: 12px;
      color:#666666;
    }
    .mainContainer{
      height: calc(100vh - 77px);
      overflow-y: auto;
    }
    .topCodeBox{
      position: absolute;
      top:-50px;
      left:30px;
      display:flex;
      align-items:center;
      justify-content:flex-start;
      font-size:14px;
      color:#666666;
      font-weight:bold;
    }
    .topButtonsBox{
      position: absolute;
      top:-55px;
      right:60px;
      display:flex;
      align-items:center;
      justify-content:flex-end;
      .el-button{
        margin-left:10px;
      }
    }
    .addArchiveBox{
      margin-bottom: 20px;
      .el-input{
        width:300px;
      }
    }
    .tagBox{
      margin-bottom: 20px;
      display: flex;
      align-items: center;
      justify-content: flex-start;
      .el-button{
        margin-left:5px;
      }
      .userTagsList{
        display: flex;
        align-items: center;
        margin-left:5px;
      }
    }
    .makerInfo{
      background: #fff;
      padding: 20px 30px 8px;
      /deep/ .el-descriptions{
        color: #666666!important;
        font-size: 12px!important;
      }
    }
    .orderDataBox{
      margin-top: 18px;
      padding: 10px 15px 30px;
      background: #fff;
      .archiveTabs{
        display: flex;
        align-items: center;
        justify-content: flex-start;
        margin-bottom:20px;
        margin-left:-20px;
        .tabItem{
          font-size: 12px;
          padding:5px 2px;
          color:#999999;
          margin: 0 20px;
          cursor: pointer;
          font-weight: bold;
          border-bottom: 4px solid #fff;
        }
        .tabItem.isActive{
          border-bottom: 4px solid #0177D5;
          color: #0177D5;
        }
      }
      .detailBox{
        color: #666666;
        font-size: 12px;
        /deep/ .el-form-item{
          width: 45%;
        }
        /deep/ .el-input.is-disabled .el-input__inner,/deep/ .el-textarea.is-disabled .el-textarea__inner{
          background: transparent;
          border: none;
          color: #666666;
          font-size: 12px;
          min-height: 28px;
          height: 28px;
        }
        /deep/ .el-form-item__label{
          font-size: 12px;
        }
        /deep/ .el-form-item__content{
          width: calc(100% - 100px);
          font-size: 12px;
        }
        .systemInfo{
          border-top: 1px dashed #DCDCDC;
          /deep/ .el-descriptions{
            color: #666666!important;
            font-size: 12px!important;
          }
        }
      }
      .editHistoryList{
        .editHistoryItem{
          border-bottom: 1px solid #DCDCDC;
          padding:15px;
          display:flex;
          align-items:center;
          .editTime{
            padding:12px 10px;
            background: #EEEEEE;
            border-radius: 4px;
            font-size: 12px;
            color: #999999;
            margin-right: 30px;
            white-space:noWrap;
          }
          .editIcon{
            width: 65px;
            height: 65px;
            border-radius: 50%;
            margin-right: 15px;
          }
          p{
            font-weight: bold;
          }
        }
      }
    }
    .orderProcessBox{
      background: #fff;
      position: relative;
      padding: 15px 20px;
      margin-left: 10px;
      .orderStatusBox{
        position:absolute;
        top:0;
        right:30px;
        background:#EC6941;
        border-radius:0px 0px 20px 20px;
        padding: 10px 20px;
        color:#fff;
        font-size:13px;
        font-weight:bold;
      }
      .dealInfo{
        display: flex;
        align-items:center;
        justify-content:flex-start;
        p{
          font-size: 12px;
          color: #666666;
          margin: 0;
          margin-left: 10px;
        }
        p:first-child{
          font-weight: bold;
          margin-bottom: 5px;
        }
      }
      .operaItem:last-child{
        border-left: none;
      }
      .operaItem{
        border-left:1px solid #DCDCDC;
        padding-left: 20px;
        position: relative;
        padding-bottom: 20px;
        .dealUserInfo{
          display: flex;
          align-items:flex-start;
          justify-content:flex-start;
          position: relative;
          left: -30px;
          margin-bottom: 10px;
          img{
            width: 20px;
            height: 20px;
            border-radius: 50%;
            margin-right: 15px;
          }
          span{
            font-weight: bold;
            color: #999999;
            font-size: 12px;
            margin-right: 10px;
          }
        }
        .operatButtons{
          .el-button{
            margin-bottom: 10px;
          }
        }
        .dealBox{
          background: #EEEEEE;
          padding: 13px 25px;
          p.remark{
            font-size:12px;
            color:#EC6941;
            margin-top:20px;
            word-break:break-all;
          }
        }
      }
    }
    .bottomButtonBox{
      height: 100px;
      width: 500px;
      margin: auto;
      display: flex;
      align-items: center;
      justify-content: space-around;
    }
  }
</style>