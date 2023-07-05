<template>
  <div class="workOrder_container">
    <el-card class="filter-container" style="padding:20px;" shadow="never">
      <el-input v-model="searchText" size="mini" style="width:200px;" class="input-width" placeholder="搜索当前处理人" clearable></el-input>
      <el-button
        type="primary"
        @click="handleSearchList()"
        size="mini"
        style="margin-left:10px">
        查询
      </el-button>
      <el-button
        @click="creatOrder()"
        size="mini">
        创建工单
      </el-button>
    </el-card>
    <div class="tabBoxStyle">
      <div v-for="(item,index) in tabList" @click="changeTab(item.value)" :key="index" class="tabItem" :class="{'activeItem':item.choose == 1}">{{item.label}}</div>
    </div>
    <div class="table-box">
      <el-table ref="adminTable"
                :data="dataList"
                style="width: 100%;margin-bottom: 20px;"
                v-loading="listLoading" border>
        <el-table-column label="ID" width="80px" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="企业名称" align="center">
          <template slot-scope="scope">{{scope.row.name}}</template>
        </el-table-column>
        <el-table-column label="档案编号" align="center">
          <template slot-scope="scope">{{scope.row.archivesCode}}</template>
        </el-table-column>
        <el-table-column label="工单编号" align="center">
          <template slot-scope="scope">{{scope.row.code}}</template>
        </el-table-column>
        <el-table-column label="工单状态" align="center">
          <template slot-scope="scope">{{scope.row.statusText}}</template>
        </el-table-column>
        <el-table-column label="任务类型" align="center">
          <template slot-scope="scope">{{scope.row.type == 0 ? '新建' : ''}}</template>
        </el-table-column>
        <el-table-column label="流程开始时间" align="center">
          <template slot-scope="scope">{{scope.row.createTime}}</template>
        </el-table-column>
        <el-table-column label="流程结束时间" align="center" v-if="tabList.filter(m=>m.choose == 1)[0].value  >1 && tabList.filter(m=>m.choose == 1)[0].value  < 4 ">
          <template slot-scope="scope">{{scope.row.updateTime || '-'}}</template>
        </el-table-column>
        <el-table-column label="当前处理人" align="center">
          <template slot-scope="scope">{{scope.row.currentDealer}}</template>
        </el-table-column>
        <el-table-column label="工单发起人" align="center">
          <template slot-scope="scope">{{scope.row.creator}}</template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini"
              type="text"
              @click="goDetail(scope.$index, scope.row)">查看
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        @current-change="handleCurrentChange"
        layout="total,prev, pager, next,jumper"
        :current-page.sync="pageNum"
        :page-size="pageSize"
        :page-sizes="[10,15,20]"
        :total="total">
      </el-pagination>
    </div>
    <Drewer v-if="showAddOrder" @close="closeAddOrder" :type="drewerType" :code="orderCode" :archiveId="archiveId"/>
  </div>
</template>
<script>
import Drewer from './components/drawer.vue';
import { fetchList } from '@/api/workorder.js'
export default {
  data() {
    return {
      showAddOrder:false,
      searchText:"",
      tabList:[
        {
          label:'未处理',
          value:0,
          choose:0,
        },
        {
          label:'处理中',
          value:1,
          choose:0,
        },
        {
          label:'结单',
          value:2,
          choose:0,
        },
        {
          label:'作废',
          value:3,
          choose:0,
        },
        {
          label:'超时未处理',
          value:4,
          choose:0,
        }
      ],
      dataList:[],
      listLoading:false,
      pageNum:1,
      pageSize:10,
      total:0,
      drewerType:'',
      orderCode:'',
      archiveId:null
    }
  },
  components:{
    Drewer
  },
  created() {
    this.changeTab(0);
    if(this.$route.query.creat == 1){ // 跳转创建工单
      this.creatOrder()
    }
    if(this.$route.query.archiveId){
      this.archiveId = this.$route.query.archiveId
    }
  },
  methods: {
    // 获取列表
    getList(){

      fetchList({
        pageNum:this.pageNum,
        pageSize:this.pageSize,
        wstatus:this.tabList[0].value,
        toDoText:this.searchText,
      }).then(r=>{
        this.dataList = r.data;
      })

    },
    // 切换选项卡
    changeTab(value){
      this.tabList.map((item)=>{
        if(item.value == value){
          item['choose'] = 1
        }else{
          item['choose'] = 0
        }
      })
      this.getList();
    },
    // 切换页码
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getList();
    },
    // 搜索列表
    handleSearchList(){
      this.pageNum = 1;
      this.getList();
    },
    // 创建工单
    creatOrder(){
      this.drewerType = 'creat'
      this.showAddOrder = true;
    },
    // 关闭创建工单
    closeAddOrder(data){
      this.showAddOrder = false;
      let path = this.$route.path; //先获取路由路径
      this.$router.push(path);
      if(data && data.restart && data.restart == 1){ // 重新加载列表
        this.getList();
      }
    },
    // 查看工单详情
    goDetail(index,row){
      this.showAddOrder = true;
      this.drewerType = 'examine';
      this.orderCode = row.code
    }
  },
}
</script>
<style lang="scss" scoped>
  /deep/ .filter-container .el-button--mini{
    width:80px;
  }
  .workOrder_container{
    background:#F0F2F5;
    padding:10px;
    box-sizing: border-box;
    .tabBoxStyle{
      margin-top:10px;
      display:flex;
      align-items:center;
      justify-content:flex-start;
      .tabItem{
        margin-right:10px;
        border-radius: 10px 10px 0px 0px;
        width: 120px;
        text-align: center;
        height: 40px;
        line-height: 40px;
        color: #999999;
        font-size: 12px;
        background: #fff;
        cursor: pointer;
      }
      .activeItem{
        background: #0177D5;
        color: #fff;
      }
    }
    .table-box{
      text-align: center;
      width: 100%;
      background: #fff;
      padding: 50px 60px;
      box-sizing: border-box;
    }
  }
</style>
