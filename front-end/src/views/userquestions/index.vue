<template>
  <div :style="pageType=='pc' ? 'padding:20px' : 'padding:10px'">
    <el-tabs v-model="tabActive" @tab-click="tabChange" >
      <el-tab-pane label="项目" name="1" v-if="queryData.filter(m=>m.type == 1).length > 0"></el-tab-pane>
      <el-tab-pane label="政策" name="2" v-if="queryData.filter(m=>m.type == 2).length > 0"></el-tab-pane>
      <el-tab-pane label="电话" name="3" v-if="queryData.filter(m=>m.type == 3).length > 0"></el-tab-pane>
      <el-tab-pane label="知识库" name="4" v-if="queryData.filter(m=>m.type == 4).length > 0"></el-tab-pane>
    </el-tabs>
    <div class="searchBox" v-if="tabActive != 3" :style="pageType=='pc' ? 'padding: 0 0 20px 0' : ''">
      <el-input v-model="keywords" size="mini" style="width:200px" placeholder="请输入内容"></el-input>
      <el-button type="primary" size="mini" @click="search">筛选</el-button>
    </div>
    <el-table ref="adminTable"
              :data="data"
              style="width: 100%;"
              v-loading="listLoading" border
              v-if="tabActive == 1">
      <el-table-column label="项目名称" align="left">
        <template slot-scope="scope">
          <el-popover
            placement="right"
            title=""
            width="200"
            trigger="hover"
            :content="scope.row.sXmName">
            <p slot="reference" class="textOverflow">{{scope.row.sXmName}}</p>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="项目所属地" align="left">
        <template slot-scope="scope">{{scope.row.sXmOrganName}}</template>
      </el-table-column>
      <el-table-column label="投资方式" v-if="pageType=='pc'" align="left">
        <template slot-scope="scope">{{scope.row.sXmTzfs}}</template>
      </el-table-column>
      <el-table-column label="项目标识" v-if="pageType=='pc'" align="left">
        <template slot-scope="scope">{{scope.row.sXmFlag}}</template>
      </el-table-column>
      <el-table-column label="责任单位名称" v-if="pageType=='pc'" align="left">
        <template slot-scope="scope">{{scope.row.sXmZrdwName}}</template>
      </el-table-column>
      <el-table-column label="填报单位名称" v-if="pageType=='pc'" align="left">
        <template slot-scope="scope">{{scope.row.sXmTbdwName}}</template>
      </el-table-column>
      <el-table-column label="重点产业" v-if="pageType=='pc'" align="left">
        <template slot-scope="scope">{{scope.row.sXmZdcy}}</template>
      </el-table-column>
      <el-table-column label="园区" v-if="pageType=='pc'" align="left">
        <template slot-scope="scope">{{scope.row.sXmYq}}</template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="100">
        <template slot-scope="scope">
          <el-button size="mini"
                      type="danger"
                      round
                      @click="showDetail(scope.row)">查看详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-table ref="adminTable"
              :data="data"
              style="width: 100%;"
              v-loading="listLoading" border
              v-if="tabActive == 2">
      <el-table-column label="政策标题" align="left">
        <template slot-scope="scope">
          <el-popover
            placement="right"
            title=""
            width="200"
            trigger="hover"
            :content="scope.row.sTitle">
            <p slot="reference" class="textOverflow">{{scope.row.sTitle}}</p>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="创建用户名称" align="left">
        <template slot-scope="scope">{{scope.row.sCreateUser}}</template>
      </el-table-column>
      <el-table-column label="更新日期" v-if="pageType=='pc'" align="left">
        <template slot-scope="scope">{{scope.row.sUpdateDate}}</template>
      </el-table-column>
      <el-table-column label="附件名称" v-if="pageType=='pc'" align="left">
        <template slot-scope="scope">{{scope.row.sFileName}}</template>
      </el-table-column>
      <el-table-column label="部门类型名称" v-if="pageType=='pc'" align="left">
        <template slot-scope="scope">{{scope.row.sBmTypeName}}</template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="100">
        <template slot-scope="scope">
          <el-button size="mini"
                      type="danger"
                      round
                      @click="showDetail(scope.row)">查看详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-table ref="adminTable"
              :data="data"
              style="width: 100%;"
              v-loading="listLoading" border
              v-if="tabActive == 3">
      <el-table-column label="单位" align="left">
        <template slot-scope="scope">
          {{scope.row.company}}
        </template>
      </el-table-column>
      <el-table-column label="联系人名称" align="left">
        <template slot-scope="scope">
          {{scope.row.contacts}}
        </template>
      </el-table-column>
      <el-table-column label="联系人座机" align="left">
        <template slot-scope="scope">
          {{scope.row.contactTel}}
        </template>
      </el-table-column>
      <el-table-column label="联系人手机" align="left">
        <template slot-scope="scope">
          {{scope.row.contactPhone}}
        </template>
      </el-table-column>
      <el-table-column label="值班室电话" align="left">
        <template slot-scope="scope">
          {{scope.row.dutyRoom}}
        </template>
      </el-table-column>
      <el-table-column label="城市" align="left">
        <template slot-scope="scope">
          {{scope.row.territory}}
        </template>
      </el-table-column>
    </el-table>
    <el-table ref="adminTable"
        :data="data"
        style="width: 100%;"
        v-loading="listLoading" border
        v-if="tabActive == 4">
      <el-table-column label="ID" align="center" width="100">
        <template slot-scope="scope">{{scope.row.id}}</template>
      </el-table-column>
      <el-table-column  :label="pageType=='pc' ? '问题' : '问题(点击文案展示)'" align="left">
        <template slot-scope="scope">
          <el-popover
            placement="right"
            title=""
            width="200"
            :trigger="pageType=='pc' ? 'hover' : 'click'"
            :content="scope.row.question">
            <p slot="reference" class="textOverflow" :class="{'isInline':pageType=='mobile'}" >{{scope.row.question}}</p>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column :label="pageType=='pc' ? '答案' : '答案(点击文案展示)'" align="left">
        <template slot-scope="scope">
          <el-popover
            placement="right"
            title=""
            width="200"
            :trigger="pageType=='pc' ? 'hover' : 'click'"
            :content="scope.row.answer">
            <p slot="reference" class="textOverflow" :class="{'isInline':pageType=='mobile'}">{{scope.row.answer}}</p>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-container">
      <el-pagination
        background
        @current-change="handleCurrentChange"
        layout="prev, pager, next"
        :current-page.sync="pageNum"
        :page-size="pageSize"
        :page-sizes="[10,15,20]"
        :total="total"
        :pager-count="5"
        style="margin:auto">
      </el-pagination>
    </div>
    <el-dialog
      :title="(tabActive == '1' ? '项目' : tabActive == '2' ? '政策' :  '知识') +'详情'"
      :visible.sync="dialogVisible"
      :width="pageType=='pc' ? '50%' : '80%'"
      :before-close="handleClose" >
        <el-descriptions v-if="tabActive == '1'" class="margin-top" style="height:50vh;overflow:auto" :column="pageType=='pc' ? 2 : 1" size="mini" :labelStyle="{whiteSpace:'noWrap'}">
        <el-descriptions-item label="项目名称">
          {{dialogData.sXmName}}
        </el-descriptions-item>
        <el-descriptions-item label="所属市">
          {{dialogData.sXmArea}}
        </el-descriptions-item>
        <el-descriptions-item label="所属区县">
          {{dialogData.sXmOrgan}}
        </el-descriptions-item>
        <el-descriptions-item label="项目所属地">
          {{dialogData.sXmOrganName}}
        </el-descriptions-item>
        <el-descriptions-item label="所属行业">
          {{dialogData.sXmHydm}}
        </el-descriptions-item>
        <el-descriptions-item label="所属产业">
          {{dialogData.sXmCy}}
        </el-descriptions-item>
        <el-descriptions-item label="投资方式">
          {{dialogData.sXmTzfs}}
        </el-descriptions-item>
        <el-descriptions-item label="项目投资金额（万元）">
          {{dialogData.sXmTzje}}
        </el-descriptions-item>
        <el-descriptions-item label="项目销售收入（万元）">
          {{dialogData.sXmXssr}}
        </el-descriptions-item>
        <el-descriptions-item label="项目标识">
          {{dialogData.sXmFlag}}
        </el-descriptions-item>
        <el-descriptions-item label="预期投资回收期（年）">
          {{dialogData.sXmYqhsq}}
        </el-descriptions-item>
        <el-descriptions-item label="利润（万元）">
          {{dialogData.sXmLr}}
        </el-descriptions-item>
        <el-descriptions-item label="税收（万元）">
          {{dialogData.sXmSs}}
        </el-descriptions-item>
        <el-descriptions-item label="项目进展情况及所处阶段">
          {{dialogData.sXmScjd}}
        </el-descriptions-item>
        <el-descriptions-item label="是否有可研报告">
          {{dialogData.sXmKybg}}
        </el-descriptions-item>
        <el-descriptions-item label="责任单位名称">
          {{dialogData.sXmZrdwName}}
        </el-descriptions-item>
        <el-descriptions-item label="责任单位联系人">
          {{dialogData.sXmZrdwLxr}}
        </el-descriptions-item>
        <el-descriptions-item label="项目责任单位联系电话">
          {{dialogData.sXmZrdwLxdh}}
        </el-descriptions-item>
        <el-descriptions-item label="项目责任单位邮箱">
          {{dialogData.sXmZrdwEmail}}
        </el-descriptions-item>
        <el-descriptions-item label="填报单位名称">
          {{dialogData.sXmTbdwName}}
        </el-descriptions-item>
        <el-descriptions-item label="填报单位联系人">
          {{dialogData.sXmTbdwLxr}}
        </el-descriptions-item>
        <el-descriptions-item label="填报单位联系电话">
          {{dialogData.sXmTbdwLxdh}}
        </el-descriptions-item>
        <el-descriptions-item label="填报单位邮箱">
          {{dialogData.sXmTbdwEmail}}
        </el-descriptions-item>
        <el-descriptions-item label="就业人数（人）">
          {{dialogData.sXmJyrs}}
        </el-descriptions-item>
        <el-descriptions-item label="重点产业">
          {{dialogData.sXmZdcy}}
        </el-descriptions-item>
        <el-descriptions-item label="重点产业链">
          {{dialogData.sXmZdcylName}}
        </el-descriptions-item>
        <el-descriptions-item label="园区">
          {{dialogData.sXmYq}}
        </el-descriptions-item>
        <el-descriptions-item label="浏览量">
          {{dialogData.sXmLll}}
        </el-descriptions-item>
        <el-descriptions-item label="发布时间">
          {{dialogData.sPublishTime}}
        </el-descriptions-item>
        <el-descriptions-item label="项目附件">
          <a :href="dialogData.sFileUrl">{{dialogData.sFileUrl}}</a>
        </el-descriptions-item>
        <el-descriptions-item label="项目概况">
          {{dialogData.sXmInfo}}
        </el-descriptions-item>
      </el-descriptions>
      <el-descriptions v-if="tabActive == '2'" class="margin-top" style="height:50vh;overflow:auto" :column="pageType=='pc' ? 2 : 1" size="mini" :labelStyle="{whiteSpace:'noWrap'}">
        <el-descriptions-item label="类型">
          {{dialogData.sType}}
        </el-descriptions-item>
        <el-descriptions-item label="标题">
          {{dialogData.sTitle}}
        </el-descriptions-item>
        <el-descriptions-item label="附件名称">
          {{dialogData.sFileName}}
        </el-descriptions-item>
        <el-descriptions-item label="附件路径">
          {{dialogData.sFilePath}}
        </el-descriptions-item>
        <el-descriptions-item label="日期">
          {{dialogData.sSpDate}}
        </el-descriptions-item>
        <el-descriptions-item label="创建用户名称">
          {{dialogData.sCreateUser}}
        </el-descriptions-item>
        <el-descriptions-item label="部门类型">
          {{dialogData.sBmType}}
        </el-descriptions-item>
        <el-descriptions-item label="创建日期">
          {{dialogData.sCreateDate}}
        </el-descriptions-item>
        <el-descriptions-item label="更新日期">
          {{dialogData.sUpdateDate}}
        </el-descriptions-item>
        <el-descriptions-item label="园区代码/市代码">
          {{dialogData.sOrgan}}
        </el-descriptions-item>
        <el-descriptions-item label="更新用户">
          {{dialogData.sUpdateUser}}
        </el-descriptions-item>
        <el-descriptions-item label="优势资源访问路径">
          <a :href="dialogData.sYszy">{{dialogData.sYszy}}</a>
        </el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>
<script>
import request from '@/utils/request'
import { parseTime } from '@/utils/index'
export default {
  data() {
    return {
      tabActive:'',
      data:[],
      pageNum: 1,
      pageSize: 10,
      total: null,
      listLoading:false,
      queryData:[],
      pageType:document.documentElement.clientWidth > 415 ? 'pc' : 'mobile',
      dialogVisible:false,
      dialogData:{},
      keywords:'',
    }
  },
  provide(){
    return{
      reload:this.reload
    }
  },
  watch: {
    '$route' (to, from) {
      // 对路由变化作出响应...
      // this.routerParms.list=to.query.list
      this.reload()
    },
  },
  created() {
    if(this.$route.query.type && this.$route.query.type !== ''){
      let types = this.$route.query.type.split(",");
      let keywords = this.$route.query.keyword.split(",");
      for(let i=0;i<types.length;i++){
        if(this.queryData.filter(m=>m.type == types[i]).length == 0){
          this.queryData.push({
            type:types[i],
            keyword:keywords[i]
          })
        }else{
          let index = this.queryData.indexOf(this.queryData.filter(m=>m.type == types[i])[0]);
          this.queryData[index].keyword += ','+keywords[i]
        }
      }
    }
    this.tabActive = this.queryData[0].type;
    this.getList()
  },
  methods: {
    tabChange(){
      this.pageNum = 1;
      this.keywords = '';
      this.getList();
    },
    search(){
      this.pageNum = 1;
      this.getList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getList();
    },
    getList(){
      let queryData = this.queryData.filter(m=>m.type == this.tabActive)[0];
      request({
        url:"/search-api/esXmkProject/search",
        method:"get",
        params:{
          keyword:queryData.keyword,
          type:queryData.type,
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          keywords:this.keywords,
        }
      }).then((res)=>{
        res.data.list.map((item)=>{
          if(queryData.type == 1){
            item['sPublishTime'] = parseTime(new Date(item.sPublishTime).getTime())
          }else if(queryData.type == 2){
            item['sCreateDate'] = parseTime(new Date(item.sCreateDate).getTime())
            item['sUpdateDate'] = parseTime(new Date(item.sUpdateDate).getTime())
            item['sSpDate'] = parseTime(new Date(item.sSpDate).getTime())
          }
        })
        this.data = res.data.list;
        this.total = res.data.total
      })
    },
    showDetail(data){
      this.dialogVisible = true;
      this.dialogData = data
    },
    handleClose(){
      this.dialogVisible = false;
      this.dialogData = {}
    },
    reload(){
      window.location.reload();
    }
  },
}
</script>
<style lang="scss" scoped>
  .isInline{
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    overflow:hidden;
    -webkit-box-orient: vertical;
  }
  .searchBox{
    padding-bottom: 20px;
    width: 100%;
    box-sizing:border-box;
  }
</style>