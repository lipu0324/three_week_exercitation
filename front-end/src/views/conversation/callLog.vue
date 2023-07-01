<!--
 * @Descripttion: 通话记录
 * @Author: Dyl
 * @Date: 2021-10-26 14:16:02
-->
<template> 
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <el-form :inline="true" :model="listQuery" size="mini" label-width="75px">
        <el-form-item label="ID：">
          <el-input v-model="listQuery.ids" class="input-width" placeholder="ID编号" clearable></el-input>
        </el-form-item>
        <el-form-item label="电话：">
          <el-input v-model="listQuery.mobile" style="width:235px" class="input-width" placeholder="输入手机号，非哈尔滨号码前请加0" clearable></el-input>
        </el-form-item>
        <el-form-item label="接听状态：">
          <el-select v-model="listQuery.hangupCause" placeholder="请选择">
            <el-option
              v-for="item in answerStatus"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间：">
          <el-date-picker
            v-model="listQuery.creatTime"
            value-format="yyyy-MM-dd"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="呼叫状态：">
          <el-select v-model="listQuery.callType" placeholder="请选择">
            <el-option
              v-for="item in callStatus"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="通话时长（分）：" label-width="110px">
          <el-col :span="11">
            <el-input v-model="listQuery.second" placeholder="通话时长" clearable></el-input>
          </el-col> 
          <el-col :span="2" style="text-align:center"> - </el-col>
          <el-col :span="11">
            <el-input v-model="listQuery.secondMin" placeholder="通话时长" clearable></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="未接原因：">
          <el-select v-model="listQuery.isCdrInboundFail" placeholder="请选择">
            <el-option
              v-for="item in noAnswerReason"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item style="float:right;margin-right:0">
          <el-button
            type="primary"
            @click="handleSearchList()">
            查询搜索
          </el-button>
          <el-button
            @click="handleResetSearch()">
            重置
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <div class="table-container">
      <el-table ref="adminTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading" border>
        </el-table-column>
        <el-table-column label="记录ID" width="100" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="电话" align="center">
          <template slot-scope="scope">{{tel(scope.row.phoneNum)}}</template>
        </el-table-column>
        <el-table-column label="时间" align="center">
          <template slot-scope="scope">{{scope.row.startStamp | formatDateTime}}</template>
        </el-table-column>
        <el-table-column label="呼叫方式" width="160" align="center">
          <template slot-scope="scope">
            {{scope.row.ctiCallType == 1 ? '内部呼叫' : ( scope.row.ctiCallType == 2 ? '呼入' : '呼出')}}
          </template>
        </el-table-column>
        <el-table-column label="接听状态" width="160" align="center">
          <template slot-scope="scope">
            {{scope.row.hangupCause | getCallStatus}}
          </template>
        </el-table-column>
        <el-table-column label="通话时长(分)" align="center">
          <template slot-scope="scope">{{scope.row.billsec}}</template>
        </el-table-column>
        <el-table-column label="分机号" align="center">
          <template slot-scope="scope">{{scope.row.extension}}</template>
        </el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="primary"
                       round
                       @click="handleUpdate(scope.$index, scope.row)">
              呼叫
            </el-button>
            <el-button size="mini"
                       type="success"
                       round
                       @click="handleDetail(scope.$index, scope.row)">查看
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination-container">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, prev, pager, next,jumper"
        :current-page.sync="listQuery.currentPage"
        :page-size="listQuery.pageSize"
        :page-sizes="[10,15,20]"
        :total="total">
      </el-pagination>
    </div>
    <CallLogDetail v-if="dialogVisible" @close="dialogVisible = false" :dialogData="dialogData" :soundData="soundData"/>
  </div>
</template>
<script>
  import {addTags,delTags,getList,changeTags,tagsStatusChange,batchDel} from '@/api/tags';
  import {formatDate} from '@/utils/date';
  import request from '@/utils/request'
  import Api from '@/assets/js/api.js';
  import { mapGetters } from 'vuex';
  import CallLogDetail from "@/components/CallLogDetail"
  const defaultListQuery = {
    currentPage: 1,
    pageSize: 10,
    tenantId:targetTenantId.toString(),
    actionID:exten,
    mobile:"",
    ids:"",
    creatTime:"",
    second:"",
    secondMin:"",
    hangupCause:"",
    callType: "",
    isCdrInboundFail:"",
    queue:queue[0]
  };
  export default {
    name: 'adminList',
    data() {
      return {
        listQuery: Object.assign({}, defaultListQuery),
        list: null,
        total: null,
        listLoading: false,
        dialogVisible: false,
        dialogData:{},
        soundData:{},
        answerStatus:[
          {
            value: 'send_bye',
            label: '呼叫成功(主叫挂断)'
          },
          {
            value: 'recv_bye',
            label: '呼叫成功(被叫挂断)'
          },
          {
            value: 'call_failed',
            label: '呼叫失败'
          },
          {
            value: 'CALL_REJECTED',
            label: '被叫拒接'
          },
          {
            value: 'NO_ANSWER',
            label: '无应答'
          },
          {
            value: 'NO_USER_RESPONSE',
            label: '被叫无响应'
          },
          {
            value: 'USER_BUSY',
            label: '用户忙'
          },
          {
            value: 'UNALLOCATED_NUMBER',
            label: '空号'
          },
          {
            value: 'NORMAL_RELEASE',
            label: '正常释放'
          },
          {
            value: 'NORMAL_CLEARING',
            label: '双方都由运营商挂断'
          },
          {
            value: 'NORMAL_TEMPORARY_FAILURE',
            label: '呼叫线路超时'
          },
          {
            value: 'TIMEOUT',
            label: 'SIP超时'
          },
          {
            value: 'NO_ROUTE_DESTINATION',
            label: '呼叫不可达（空号/无法路由）'
          },
          {
            value: 'USER_NOT_REGISTERED',
            label: '网关/电话卡未注册'
          },
          {
            value: 'NORMAL_UNSPECIFIED',
            label: '线路不通, 可能盲区'
          },
          {
            value: 'RECOVERY_ON_TIMER_EXPIRE',
            label: '媒体超时, 异常'
          },
        ],
        callStatus:[
          {
            value: '2',
            label: '呼入'
          },
          {
            value: '3',
            label: '呼出'
          },
          {
            value: '1',
            label: '内部呼叫'
          },
        ],
        noAnswerReason:[
          {
            value: '1',
            label: '转接座席未接'
          },
          {
            value: '2',
            label: '进入ivr未接'
          },
          {
            value: '3',
            label: '进入ACD未接'
          },
          {
            value: '5',
            label: '所有未接'
          },
        ],
        msg_host:msg_host,
        sound_url:sound_url,
        http_urla:"http://" + msg_host + ":" + http_url
      }
    },
    components:{
      CallLogDetail
    },
    computed: {
      ...mapGetters([
        'signStatus'
      ])
    },
    created() {
      this.getList();
    },
    filters: {
      
      formatDateTime(time) {
        if (time == null || time === '') {
          return 'N/A';
        }
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm:ss')
      },
      getCallStatus(code){
        switch(code){
          case 'send_bye' :
            return '呼叫成功(主叫挂断)'
          break;
          case 'recv_bye' :
            return '呼叫成功(被叫挂断)'
          break;
          case 'call_failed' :
            return '呼叫失败'
          break;
          case 'CALL_REJECTED' :
            return '被叫拒接'
          break;
          case 'NO_ANSWER' :
            return '无应答'
          break;
          case 'NO_USER_RESPONSE' :
            return '被叫无响应'
          break;
          case 'USER_BUSY' :
            return '用户忙'
          break;
          case 'UNALLOCATED_NUMBER' :
            return '空号'
          break;
          case 'NORMAL_RELEASE' :
            return '正常释放'
          break;
          case 'NORMAL_CLEARING' :
            return '双方都由运营商挂断'
          break;
          case 'NORMAL_TEMPORARY_FAILURE' :
            return '呼叫线路超时'
          break;
          case 'TIMEOUT' :
            return 'SIP超时'
          break;
          case 'NO_ROUTE_DESTINATION' :
            return '呼叫不可达（空号/无法路由）'
          break;
          case 'USER_NOT_REGISTERED' :
            return '网关/电话卡未注册'
          break;
          case 'NORMAL_UNSPECIFIED' :
            return '线路不通, 可能盲区'
          break;
          case 'RECOVERY_ON_TIMER_EXPIRE' :
            return '媒体超时, 异常'
          break;
          default:
            return '呼叫成功'
          break
        }
      },
      calldirection(code){
        switch(code){
          case 'transfer' :
            return '转接呼叫'
          break;
          case 'conference' :
            return '三方呼叫'
          break;
          case 'monitor' :
            return '监听'
          break;
          case 'makeCall' :
            return '接口外呼'
          break;
          case 'holdCall' :
            return '保持外呼'
          break;
          case 'forward' :
            return '呼叫转移'
          break;
          case 'transferExternal' :
            return '分机呼叫转移'
          break;
          default:
            return '转接呼叫'
          break;
        }
      }
    },
    methods: {
      tel(tel){
        return tel.substring(0, 3)+"****"+tel.substr(tel.length-4);
      },
      // 获取标签列表
      getList() {
        this.listLoading = true;
        let data = JSON.parse(JSON.stringify(this.listQuery))
        data['startTime'] = data['creatTime'] && data['creatTime'].length > 0 ? data['creatTime'][0]+' 00:00:00' : '';
        data['endTime'] = data['creatTime'] && data['creatTime'].length > 0 ? data['creatTime'][1]+' 23:59:59' : '';
        data['second'] = data.second ? data.second*60 : null;
        data['secondMin'] = data.secondMin ? data.secondMin*60 : null;
        delete data.creatTime;
        request({
          url:`${this.http_urla}query/queryCdrs`,
          method:"post",
          data: data
        }).then(response => {
          this.listLoading = false;
          this.list = response.data;
          response.data.map((item)=>{
            item['billsec'] = item.billsec ? Math.floor((item.billsec/60) * 100) / 100 : 0;
            item['phoneNum'] = (item.ctiCallType == 1 || item.ctiCallType == 2) ? item.callerIdNumber : item.destinationNumber
          })
          this.total = response.totalCount;
        }).catch(()=>{
          this.listLoading = false;
        });
      },
      handleResetSearch() {
        this.listQuery = Object.assign({}, defaultListQuery);
        this.getList();
      },
      handleSearchList() {
        this.listQuery.currentPage = 1;
        this.getList();
      },
      handleSizeChange(val) {
        this.listQuery.currentPage = 1;
        this.listQuery.pageSize = val;
        this.getList();
      },
      handleCurrentChange(val) {
        this.listQuery.currentPage = val;
        this.getList();
      },
      handleDetail(index, row) {
        request({
          url:`${this.http_urla}query/queryRecord`,
          method:"post",
          data: {
            tenantId:targetTenantId.toString(),
            actionID:exten,
            id:row.ctiCallId
          }
        }).then(response => {
          this.soundData = response.data
        })
       this.dialogVisible = true;
       this.dialogData = row;
      },
      handleUpdate(index, row) {
        if(this.signStatus == 'in'){
          const phoneNumber = (row.ctiCallType == 1 || row.ctiCallType == 2) ? row.callerIdNumber : row.destinationNumber
          ws.send(JSON.stringify(Api.makeCallData(phoneNumber)));
        }else{
          this.$message.error('当前座席状态为签出，无法呼叫');
        }
      },
    }
  }
</script>
<style lang="scss" scoped>
  .dialogBody{
    height:60vh;
    overflow-y:auto;
  }
  /deep/ .el-form-item__content .el-button--mini{
    width:80px;
  }
</style>


