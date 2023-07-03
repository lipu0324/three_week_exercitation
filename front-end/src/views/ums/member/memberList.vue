<!-- 用户列表 -->
<template> 
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <div style="margin-top: 15px">
        <el-form :inline="true" size="mini" label-width="75px">
          <el-form-item label="姓名：">
            <el-input v-model="searchData.name" class="input-width" clearable></el-input>
          </el-form-item>
          <el-form-item label="电话：">
            <el-input v-model="searchData.phone" class="input-width" clearable></el-input>
          </el-form-item>
          <el-form-item label="状态：">
            <el-select v-model="searchData.status" >
              <el-option label="请选择" :value="-1"></el-option>
              <el-option label="启用" :value="1"></el-option>
              <el-option label="禁用" :value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="创建时间：">
            <el-date-picker
              v-model="chooseTime"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              @change="handelSelectTime">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="会话次数：">
            <el-col :span="11">
              <el-input v-model="searchData.startCount" type="number" class="spacleInput" min=0></el-input>
            </el-col> 
            <el-col :span="2" style="text-align:center"> - </el-col>
            <el-col :span="11">
              <el-input v-model="searchData.endCount" type="number" class="spacleInput" :min="0"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item style="float:right;margin-right:0">
            <el-button
              style="float:right;margin-left: 15px"
              @click="handleResetSearch()">
              重置
            </el-button>
            <el-button
              type="primary"
              @click="handleSearchList()">
              查询搜索
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <div class="table-container">
      <el-table ref="adminTable"
                :data="list"
                style="width: 100%;"
                @selection-change="handleSelectionChange"
                v-loading="listLoading" border>
        <el-table-column label="编号" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="姓名" align="center">
          <template slot-scope="scope">{{scope.row.username}}</template>
        </el-table-column>
        <el-table-column label="电话" align="center">
          <template slot-scope="scope">{{scope.row.phone}}</template>
        </el-table-column>
        <el-table-column label="创建日期" align="center">
          <template slot-scope="scope">{{scope.row.createTime}}</template>
        </el-table-column>
        <el-table-column label="会话次数" align="center">
          <template slot-scope="scope">{{scope.row.count}}</template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="primary"
                       round
                       @click="watch(scope.$index, scope.row)">
              查看档案
            </el-button>
            <el-button v-if="scope.row.status*1===1" round size="mini" type="danger" @click="handleStatusChange(scope.row, 0, '封禁')">封禁</el-button>
            <el-button v-else size="mini" type="success" round @click="handleStatusChange(scope.row, 1, '解封')">解封
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
        :current-page.sync="pageNum"
        :page-size="pageSize"
        :page-sizes="[10,15,20]"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
  import { memberList, changeMemberStatus, memberBan } from '@/api/member';
  import { labelList } from '@/api/label';
  import { BASE_API} from '../../../../config/dev.env';
  import {formatDate} from '@/utils/date';
  import { getSitNum } from '@/utils/auth';
  import request from '@/utils/request'
  export default {
    name: 'adminList',
    data() {
      return {
        list: null,
        total: null,
        listLoading: false,
        pageNum: 1,
        pageSize: 10,
        multipleSelection: [], // 多选列表
        chooseTime: [],
        // 搜索字段
        searchData: {
          name: '',
          phone: '',
          startTime: '',
          endTime: '',
          label: -1,
          startCount: '',
          endCount: '',
          status: -1,
          salesman: ''
        },
        defaultSearch: {
          name: '',
          phone: '',
          startTime: '',
          endTime: '',
          label: -1,
          startCount: '',
          endCount: '',
          status: -1,
          salesman: ''
        },
        http_urla:"http://" + msg_host + ":" + http_url,
      }
    },
    filters: {
    },
    created() {
      this.getList();
    },
    methods: {
      // 设置是否可以勾选封禁
      selectable(row ) {
        if (row.status * 1 === 1) {
          return true
        } else {
          return  false
        }
      },
      handleResetSearch() {
        this.searchData = Object.assign({}, this.defaultSearch);
        this.chooseTime = [];
        this.getList();
      },
      handleSearchList() {
        this.pageNum = 1;
        this.getList();
      },
      handleSizeChange(val) {
        this.pageNum = 1;
        this.pageSize = val;
        this.getList();
      },
      handleCurrentChange(val) {
        this.pageNum = val;
        this.getList();
      },
      // 修改用户状态
      handleStatusChange(data, status, tips) {
        let sit = getSitNum()
        this.$confirm(`是否要${tips}该用户?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(status == 0){ // 拉黑
            request({
              url:`${this.http_urla}config/addBlacklist`,
              method:"post",
              data: {
                name:data.id,
                tenantId:targetTenantId.toString(),
                agent:sit,
                mobile:data.phone,
                type:2,
                actionID:exten,
                status:1
              }
            }).then(response => {})
          }else{
            request({
              url:`${this.http_urla}query/queryBlacklists`,
              method:"post",
              data: {
                tenantId:targetTenantId.toString(),
                actionID:exten,
                number:data.phone,
                callType:'2',
                thisDN:window.thisDN,
                currentPage:1,
                pageSize:100
              }
            }).then(response => {
              if(response.data && response.data.length > 0){
                request({
                  url:`${this.http_urla}config/delBlacklist`,
                  method:"post",
                  data: {
                    id:response.data[0].id,
                    actionID:exten,
                  }
                }).then(response => {})
              }
            })
          }
          changeMemberStatus(data.id, {status}).then(res => {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            this.pageNum = 1;
            this.getList();
          });
        }).catch(() => {});
      },
      // 多选
      handleSelectionChange(val) {
        this.multipleSelection = val;
        this.multipleSelection = val.map(item => {
          return `ids=${item.id}`
        })
      },
      // 批量封禁
      handleBan() {
        let data = this.multipleSelection.join('&')
        memberBan(data).then(res => {
          this.$message({
            type: "success",
            message: "禁用成功!",
          });
          this.pageNum = 1;
          this.getList();
        })
      },
      watch(index,row){
        this.$router.push({path:'/archives/archivesDetail',query:{id:row.archivesId}});
        console.log(row.id)
      },
      // 选择日期
      handelSelectTime() {
        if(this.chooseTime){
          this.searchData.startTime = formatDate(this.chooseTime[0], 'yyyy-MM-dd')
          this.searchData.endTime = formatDate(this.chooseTime[1], 'yyyy-MM-dd')
        }else{
          this.searchData.startTime = ''
          this.searchData.endTime = ''
        }
      },
      // 获取用户列表
      getList() {
        this.listLoading = true;
        memberList({
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          ...this.searchData
        }).then(res => {
          res.data.list.map((item)=>{
            item['createTime'] = item.createTime ? item.createTime.split(' ')[0] : '';
          })
          this.listLoading = false;
          this.list = res.data.list;
          this.total = res.data.total;
        });
      },
    }
  }
</script>
<style lang="scss" scoped>
  /deep/ .el-form-item__content .el-button--mini{
    width:80px;
  }
  .spacleInput .el-input__inner{
    padding-right:0;
  }
</style>


