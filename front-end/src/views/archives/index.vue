<!--
 * @Descripttion: 档案信息
 * @Author: Dyl
 * @Date: 2021-11-12 09:53:22
-->
<template>
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <div style="margin-top: 15px">
        <el-form :inline="true" :model="listQuery" size="mini" label-width="75px">
          <el-form-item label="名称：" label-width="50px">
            <el-input v-model="listQuery.name" class="input-width" placeholder="名称" clearable></el-input>
          </el-form-item>
          <el-form-item label="紧急情况：">
            <el-select v-model="listQuery.urgency" placeholder="请选择">
              <el-option
                v-for="item in emergencyList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="档案编号：">
            <el-input v-model="listQuery.code" class="input-width" placeholder="档案编号" clearable></el-input>
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
      </div>
    </el-card>
    <div class="operate-container" shadow="never">
      <el-button size="mini" class="btn-add" @click="exportExcel()" type="primary">导出</el-button>
      <a id="exportId" href="" style="display:none">测试导出</a>
    </div>
    <div class="table-container">
      <el-table ref="adminTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading" border>
        <el-table-column label="ID" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="名称" align="center">
          <template slot-scope="scope">{{scope.row.name}}</template>
        </el-table-column>
        <el-table-column label="电话" align="center">
          <template slot-scope="scope">{{scope.row.phone}}</template>
        </el-table-column>
        <el-table-column label="标签" align="center">
          <template slot-scope="scope">{{scope.row.label}}</template>
        </el-table-column>
        <el-table-column label="创建日期" align="center">
          <template slot-scope="scope">{{scope.row.createdTime | formatDateTime}}</template>
        </el-table-column>
        <el-table-column label="档案编号" align="center">
          <template slot-scope="scope">{{scope.row.code}}</template>
        </el-table-column>
        <el-table-column label="紧急情况" align="center">
          <template slot-scope="scope">{{scope.row.urgencyText}}</template>
        </el-table-column>
        <el-table-column label="业务员" align="center">
          <template slot-scope="scope">{{scope.row.sitDown}}</template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="primary"
                       round
                       @click="goDetail(scope.$index, scope.row)">
              查看
            </el-button>
            <el-button size="mini"
                       type="warning"
                       round
                       @click="goEdit(scope.$index, scope.row)">
              编辑
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
  </div>
</template>
<script>
  import { getArchiveList,exportArchive } from "@/api/archives"
  import { formatDate } from '@/utils/date';
  const defaultListQuery = {
    pageNum: 1,
    pageSize: 10,
    name: '',
    code:'',
    urgency:''
  };
  export default {
    data(){
      return {
        exportData:{},
        emergencyList:[
          {
            value:0,
            label:"普通"
          },{
            value:1,
            label:"紧急"
          },{
            value:2,
            label:"非常紧急"
          }
        ],
        listQuery: Object.assign({}, defaultListQuery),
        list: null,
        total: null,
        listLoading: false,
      }
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
        return formatDate(date, 'yyyy-MM-dd')
      }
    },
    methods: {
      // 重置
      handleResetSearch() {
        this.listQuery = Object.assign({}, defaultListQuery);
        this.getList();
      },
      // 查询搜索
      handleSearchList() {
        this.listQuery.pageNum = 1;
        this.getList();
        this.exportData = this.listQuery
      },
      // 改变页码
      handleSizeChange(val) {
        this.listQuery.pageNum = 1;
        this.listQuery.pageSize = val;
        this.getList();
      },
      // 切页
      handleCurrentChange(val) {
        this.listQuery.pageNum = val;
        this.getList();
      },
      // 查看详情
      goDetail(index,row) {
        this.$router.push({
          path:`/archives/archivesDetail?id=${row.id}`
        })
      },
      // 编辑
      goEdit(index,row) {
        this.$router.push({
          path:`/conversation/conversationInfo?number=${row.phone}&sit=${row.sitDown}&pageFlag=${Math.floor(Math.random() * 10) + 1}`
        })
      },
      // 获取列表
      getList() {
        this.listLoading = true;
        let data = JSON.parse(JSON.stringify(this.listQuery))
        getArchiveList(data).then(response => {
          console.log(response)
          response.data.list.map((item)=>{
            item['urgencyText'] = item.urgency == 0 ? '普通' : (item.urgency == 1 ? '紧急' : '非常紧急')
          })
          this.listLoading = false;
          this.list = response.data.list;
          this.total = response.data.total;
        });
      },
      // 导出列表
      exportExcel(){
        exportArchive({
          name: this.exportData.name,
          code: this.exportData.code,
          urgency: this.exportData.urgency
        }).then((res)=>{
          window.exportFlag = 1;
          const link = document.getElementById('exportId')
          link.href = process.env.IMAGE_API+res.data
          link.click()
          this.$message({
            message: '导出成功！',
            type: 'success'
          });
        })
      },
      // 封禁部门不可勾选
      selectStatus(row) {
        if (row.status == 0) {
          return true
        } else {
          return  false
        }
      },
    }
  }
</script>
<style lang="scss" scoped>
  /deep/ .el-form-item__content .el-button--mini,.operate-container .el-button--mini{
    width:80px;
  }
</style>