<!--
 * @Author: Dyl
 * @Date: 2022-02-16 14:57:58
 * @LastEditTime: 2022-03-03 16:51:09
 * @LastEditors: Please set LastEditors
 * @Description: 群呼列表
 * @FilePath: \sfcc\src\views\groupCall\index.vue
-->
<template>
  <div class="app-container">
    <div class="filter-container" shadow="never">
      <div style="margin-top: 15px">
        <el-form :inline="true" :model="listQuery" size="mini" label-width="100px">
          <el-form-item label="任务名称：">
            <el-input v-model="listQuery.title" class="input-width" clearable></el-input>
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
          <el-form-item label="任务状态：">
            <el-select v-model="listQuery.status" placeholder="请选择">
              <el-option
                v-for="item in callStatus"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
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
      </div>
    </div>
    <div class="operate-container" shadow="never">
      <el-button size="mini" class="btn-add" @click="handleAdd()" type="warning" >新建任务</el-button>
    </div>
    <div class="table-container">
      <el-table ref="adminTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading" border>
        <el-table-column label="任务名称" align="center">
          <template slot-scope="scope">{{scope.row.title}}</template>
        </el-table-column>
        <el-table-column label="创建时间" align="center">
          <template slot-scope="scope">{{scope.row.createdTime | formatDateTime}}</template>
        </el-table-column>
        <el-table-column label="创建人" align="center">
          <template slot-scope="scope">{{scope.row.username}}</template>
        </el-table-column>
        <el-table-column label="问卷模板" align="center">
          <template slot-scope="scope">{{scope.row.naireTitle}}</template>
        </el-table-column>
        <el-table-column label="最大振铃时长（秒）" align="center">
          <template slot-scope="scope">{{scope.row.maxRingTime}}</template>
        </el-table-column>
        <el-table-column label="外呼号" align="center">
          <template slot-scope="scope">
            <el-popover
              placement="right"
              title=""
              width="400"
              trigger="hover"
              :content="scope.row.filter">
              <p slot="reference" class="textOverflow">{{scope.row.filter}}</p>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="任务状态" align="center">
          <template slot-scope="scope">{{scope.row.status | getStatus}}</template>
        </el-table-column>
        <el-table-column label="备注" align="center">
          <template slot-scope="scope">
            <el-popover
              placement="right"
              title=""
              width="400"
              trigger="hover"
              :content="scope.row.remark">
              <p slot="reference" class="textOverflow">{{scope.row.remark}}</p>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="primary"
                       round
                       @click="handleUpdate(scope.$index, scope.row)"
                       v-if="scope.row.status == 0">
              编辑
            </el-button>
            <el-button size="mini"
                       type="danger"
                       round
                       @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
            <el-button size="mini"
                type="success"
                round
                @click="changeTaskStatus(scope.row,1)" v-if="scope.row.status == 0">
              开始任务
            </el-button>
            <el-button size="mini"
                type="success"
                round
                @click="changeTaskStatus(scope.row,2)" v-if="scope.row.status == 1">
              结束任务
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
        :current-page.sync="listQuery.pageNum"
        :page-size="listQuery.pageSize"
        :page-sizes="[10,15,20]"
        :total="total">
      </el-pagination>
    </div>
    <el-dialog
      :title="isEdit?'编辑任务':'添加任务'"
      :visible.sync="dialogVisible"
      width="40%"
      @close="clearError"
      >
      <div @click="hideChooseUser">
        <el-form :model="admin"
                ref="adminForm"
                label-width="150px"
                size="small"
                :rules="rules"
        >
          <el-form-item label="任务名称：" prop="title">
            <el-input v-model="admin.title" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item label="群呼号码：" prop="filter">
            <ChooseUser ref="chooseUserComponent" v-if="dialogVisible" :data="admin.filter"/>
          </el-form-item>
          <el-form-item label="最大振铃时长(秒)：" prop="maxRingTime">
            <el-input v-model="admin.maxRingTime" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item label="问卷模板：" prop="naireId">
            <el-select v-model="admin.naireId" v-if="dialogVisible" placeholder="请选择" style="width: 250px">
              <el-option
                v-for="item in naireList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注：">
            <el-input v-model="admin.remark"
                      type="textarea"
                      :rows="5"
                      style="width: 250px"
                      maxlength="120"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer" style="text-align:center">
          <el-button @click="dialogVisible = false" size="small">取 消</el-button>
          <el-button type="primary" @click="handleDialogConfirm()" size="small">确 定</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {getList} from '@/api/questionnaire.js'
import {addGroupCall,delGroupCall,getGroupCallList,updateGroupCall,changeGroupCall} from '@/api/groupCall.js'
import {formatDate} from '@/utils/date';
import ChooseUser from './components/chooseUser';
import request from '@/utils/request';
import { getSitNum } from '@/utils/auth';
const defaultListQuery = {
  pageNum: 1,
  pageSize: 10,
  title: null,
  creatTime:"",
  status:null,
};
const defaultAdmin = {
  agent:"10000_1000",
  filter:"",
  ivrId:"10",
  naireId:"",
  maxRingTime:"60",
  title: '',
  status:0,
  remark:''
};
export default {
  data() {
    return {
      listQuery: Object.assign({}, defaultListQuery),
      list: null,
      total: null,
      listLoading: false,
      dialogVisible: false,
      admin: Object.assign({}, defaultAdmin),
      isEdit: false,
      rules:{
        title: [
          { required: true, message: '请输入问卷名称', trigger: 'blur' },
        ],
        // filter: [
        //   { required: true, message: '请输入群呼号码', trigger: 'submit' },
        // ],
        maxRingTime: [
          { required: true, message: '请输入最大振铃时长', trigger: 'blur' },
        ],
        naireId: [
          { required: true, message: '请选择问卷模板', trigger: 'change' },
        ]
      },
      callStatus:[
        {
          value: '0',
          label: '未开始'
        },{
          value: '1',
          label: '进行中'
        },{
          value: '2',
          label: '已完成'
        }
      ],
      naireList:[],
      http_urla:"http://" + msg_host + ":" + http_url,
    }
  },
  created() {
    this.getList();
  },
  components:{
    ChooseUser
  },
  filters: {
    formatDateTime(time) {
      if (time == null || time === '') {
        return 'N/A';
      }
      let date = new Date(time);
      return formatDate(date, 'yyyy-MM-dd hh:mm:ss')
    },
    whether(boole){
      return boole == 0 ? '是' : '否'
    },
    getStatus(status){
      return status == 0 ? '未开始' : status == 1 ? '进行中' : '已完成'
    }
  },
  methods: {
    // 获取问卷列表
    getList() {
      request({ // 删除任务
        url:`${this.http_urla}query/queryOutboundInstances`,
        method:"post",
        data: {
          tenantId:targetTenantId.toString(), //
          actionID:exten, // 唯一值 
          currentPage:1,
          pageSize:100
        }
      }).then(resa => {
      })
      this.listLoading = true;
      let data = JSON.parse(JSON.stringify(this.listQuery))
      data['startTime'] = data['creatTime'] && data['creatTime'].length > 0 ? data['creatTime'][0]+' 00:00:00' : null;
      data['endTime'] = data['creatTime'] && data['creatTime'].length > 0 ? data['creatTime'][1]+' 23:59:59' : null;
      delete data['creatTime']
      getGroupCallList(data).then(response => {
        this.listLoading = false;
        this.list = response.data.list;
        this.total = response.data.total;
      }).catch(()=>{
        this.listLoading = false;
      });
    },
    handleResetSearch() {
      this.listQuery = Object.assign({}, defaultListQuery);
      this.getList();
    },
    handleSearchList() {
      this.listQuery.pageNum = 1;
      this.getList();
    },
    handleSizeChange(val) {
      this.listQuery.pageNum = 1;
      this.listQuery.pageSize = val;
      this.getList();
    },
    handleCurrentChange(val) {
      this.listQuery.pageNum = val;
      this.getList();
    },
    // 新增任务
    handleAdd() {
      getList({
        pageNum:'1',
        pageSize:'100',
        status:'1'
      }).then((response)=>{
        this.naireList = [];
        response.data.list.map((item)=>{
          this.naireList.push({
            value:item.id,
            label:item.title
          });
        })
      })
      this.isEdit = false;
      this.admin = Object.assign({},defaultAdmin);
      this.dialogVisible = true;
    },
    // 删除任务
    handleDelete(index, row) {
      let _this = this;
      this.$confirm('是否要删除该问卷?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 如果包含任务id说明已经添加任务 需要先删除任务
        if(row.actionId){
          request({ // 结束呼叫任务
            url:`http://${msg_host}:${http_urlB}config/StopOutbound`,
            method:"post",
            data: JSON.stringify({
              outboundId:row.actionId, // 任务id
              tenantId:targetTenantId.toString(), // 资源组号
              dialMode:"1", // 拨号模式:1预测试 2预览试
            }),
            noTokenSet: true,
          }).then(response => {
            request({ // 删除任务
              url:`${_this.http_urla}config/delOutboundInstance`,
              method:"post",
              data: {
                id:row.actionId, // 任务id
                actionID:exten, // 唯一值 
              }
            }).then(resa => {
              delGroupCall({id:row.id}).then(response => {
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                this.getList();
              });
            })
          })
          
        }else{
          delGroupCall({id:row.id}).then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getList();
          });
        }
      });
    },
    // 编辑任务
    handleUpdate(index, row) {
      if(this.naireList.length == 0){
        getList({
          pageNum:'1',
          pageSize:'100',
          status:'1'
        }).then((response)=>{
          this.naireList = [];
          response.data.list.map((item)=>{
            this.naireList.push({
              value:item.id,
              label:item.title
            });
          })
        })
      }
      this.dialogVisible = true;
      this.isEdit = true;
      this.admin = Object.assign({},row);
    },
    // 弹窗确认编辑/添加
    handleDialogConfirm() {
      let _this = this;
      if(_this.admin.filter == ''){
        this.$message.error("请输入群呼号吗");
        return false
      }
      _this.$refs.adminForm.validate((valid) => {
        if (valid) {
          _this.$confirm('是否要确认?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            if (_this.isEdit) {
              let data = JSON.parse(JSON.stringify(_this.admin));
              updateGroupCall,changeGroupCall(data).then(response => {
                _this.$message({
                  message: '修改成功！',
                  type: 'success'
                });
                _this.dialogVisible =false;
                _this.getList();
              })
            } else {
              // 数据库接口新增
              addGroupCall(_this.admin).then(response => {
                _this.$message({
                  message: '添加成功！',
                  type: 'success'
                });
                _this.dialogVisible =false;
                _this.getList();
              })
            }
          })
        }
      })
    },
    // 弹窗关闭清空错误
    clearError(){
      this.$refs.adminForm.clearValidate();
    },
    // 修改群呼任务状态
    changeTaskStatus(row,status){
      let data = JSON.parse(JSON.stringify(row)),_this = this;
      data['status'] = status;
      if(status == 1){ // 启动任务
        let sit = getSitNum()
        if(row.actionId){ // 如果存在任务id 直接启动任务 无需新创建任务
          changeGroupCall(data).then(()=>{
            request({ // 启动呼叫任务
              url:`http://${msg_host}:${http_urlB}config/StartOutbound`,
              method:"post",
              data: JSON.stringify({
                outboundId:row.actionId.toString(), // 任务id
                tenantId:targetTenantId.toString(), // 资源组号
                dialMode:"1", // 拨号模式:1预测试 2预览试
              }),
              noTokenSet: true,
            }).then(() => {
              _this.getList();
            })
          })
        }else{ // 不存在任务id 先创建在追加号码 在启动任务
          request({ // 呼叫中心接口新增任务
            url:`${_this.http_urla}config/addOutboundInstance`,
            method:"post",
            data: {
              name:row.title, // 项目名称
              agent:sit,  // 坐席号
              tenantId:targetTenantId.toString(), // 资源组号
              actionID:exten, // 唯一值 
              isVgc:"1", //  是否启用语音群呼 0否 1启动 默认为0
              ivrId: row.ivrId.toString(), // ivrId
              maxRingTime: row.maxRingTime, //最大振铃时长
              callType:"1", 
            }
          }).then(response => {
            request({ // 呼叫任务追加号码
              url:`${_this.http_urla}config/appendOutboundInstance`,
              method:"post",
              data: {
                outboundId:response.data.id.toString(), // 任务id
                filter:row.filter,  // 呼叫号码
                actionID:exten, // 唯一值 
              }
            }).then(resa => {
              data['actionId'] = response.data.id;
              changeGroupCall(data).then(()=>{
                request({ // 启动呼叫任务
                  url:`http://${msg_host}:${http_urlB}config/StartOutbound`,
                  method:"post",
                  data: JSON.stringify({
                    outboundId:response.data.id.toString(), // 任务id
                    tenantId:targetTenantId.toString(), // 资源组号
                    dialMode:"1", // 拨号模式:1预测试 2预览试
                  }),
                  noTokenSet: true,
                }).then(() => {
                  _this.getList();
                })
              })
              // $.ajax({
              //   url:`http://${msg_host}:${http_urlB}config/StartOutbound`,
              //   type:"post",
              //   data:JSON.stringify({
              //     outboundId:response.data.id.toString(), // 任务id
              //     tenantId:targetTenantId.toString(), // 资源组号
              //     dialMode:"1", // 拨号模式:1预测试 2预览试
              //   }),
              //   dataType:"json",
              //   success:(res)=>{
              //     data['actionId'] = response.data.id;
              //     changeGroupCall(data).then(()=>{
              //       _this.getList();
              //     })
              //   }
              // })
            })
          })
        }
      }else{
        request({ // 结束呼叫任务
          url:`http://${msg_host}:${http_urlB}config/StopOutbound`,
          method:"post",
          data: JSON.stringify({
            outboundId:row.actionId, // 任务id
            tenantId:targetTenantId.toString(), // 资源组号
            dialMode:"1", // 拨号模式:1预测试 2预览试
          }),
          noTokenSet: true,
        }).then(response => {
          changeGroupCall(data).then(()=>{
            _this.getList();
          })
        })
      }
    },
    // 隐藏选择用户
    hideChooseUser(){
      this.$refs.chooseUserComponent.hideChooseBox();
      this.admin['filter'] = this.$refs.chooseUserComponent.inputValue
    }
  }
}
</script>
<style lang="scss" scoped>
  .textOverflow{
    text-overflow: -o-ellipsis-lastline;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    line-clamp: 2;
    -webkit-box-orient: vertical;
  }
</style>
