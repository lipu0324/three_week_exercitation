<!-- 知识库管理 -->
<template> 
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <div style="margin-top: 15px">
        <el-form :inline="true"  size="mini" label-width="75px">
          <el-form-item label="问题名称：">
            <el-input v-model="keyword" class="input-width" placeholder="请输入要搜索的问题" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              @click="handleSearchList()">
              查询搜索
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <div class="operate-container" shadow="never">
      <el-button size="mini" class="btn-add" icon="el-icon-first-aid-kit" @click="importVisible = true" type="primary">批量导入</el-button>
      <el-button size="mini" class="btn-add" icon="el-icon-printer" @click="handleExport()" type="success">批量导出</el-button>
      <a id="exportId" href="" style="display:none">测试导出</a>
    </div>
    <div class="table-container">
      <el-table ref="adminTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading" border>
        <el-table-column label="ID" align="center" width="100">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="问题" align="left">
          <template slot-scope="scope">
            <el-popover
              placement="right"
              title=""
              width="200"
              trigger="hover"
              :content="scope.row.question">
              <p slot="reference" class="textOverflow">{{scope.row.question}}</p>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="答案" align="left">
          <template slot-scope="scope">
            <el-popover
              placement="right"
              title=""
              width="200"
              trigger="hover"
              :content="scope.row.answer">
              <p slot="reference" class="textOverflow">{{scope.row.answer}}</p>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="100">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="danger"
                       round
                       @click="handleDelete(scope.row.id)">删除
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
    <el-dialog
      title="提示"
      :visible.sync="importVisible"
      width="30%">
      <span>是否需要下载导入模板</span>
      <span slot="footer" class="dialog-footer">
        <el-upload
          class="bank-cover-uploader"
          :action="fileAdress"
          :show-file-list="false"
          :headers = "headerConfig"
          :on-success="handleImport"
          style="float:right">
          <el-button size="mini" class="btn-add" style="margin-left: 20px">直接导入</el-button>
        </el-upload>
        <el-button size="mini" type="primary" @click="uploadTemplate">下载模板</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import { getKnowledgeList, exportKnowledge, importKnowledge, deleteKnowledge, uploadTemplateUrl } from '@/api/knowledge';
  import { getToken  } from '@/utils/auth'
  export default {
    data() {
      return {
        list: null,
        total: null,
        pageNum: 1,
        pageSize: 10,
        keyword: '', // 搜索
        listLoading: false,
        dialogVisible: false,
        fileAdress:`${process.env.IMAGE_API}/file/single?dir=sfcc`,
        headerConfig: {
          Authorization: 'Bearer ' + getToken()
        },
        queryText:"",
        importVisible:false
      }
    },
    created() {
      this.getList();
    },
    filters: {
    },
    methods: {
      // 搜索
      handleSearchList() {
        this.pageNum = 1;
        this.queryText = this.keyword;
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
      // 添加
      handleAdd() {
        this.dialogVisible = true;
      },
      // 删除
      handleDelete(id) {
        this.$confirm('是否要删除本条内容?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteKnowledge(id).then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });

            this.pageNum = 1
            this.getList();
          });
        });
      },
      // 批量导入
      handleImport(e) {
        this.importVisible = false;
        importKnowledge({
          fileName:e.data
        }).then((res)=>{
          this.$message({
            type: 'success',
            message: '导入成功!'
          });
          this.pageNum = 1
          this.getList();
        })
      },
      // 批量导出
      handleExport() {
        exportKnowledge({
          question:this.queryText
        }).then(res => {
          window.exportFlag = 1;
          const link = document.getElementById('exportId')
          link.href = process.env.BASE_API+res.data
          link.click()
          this.$message({
            message: '导出成功！',
            type: 'success'
          });
        })
      },
      // 获取知识库列表
      getList() {
        this.listLoading = true;
        getKnowledgeList({
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          question: this.keyword
        }).then(response => {
          this.listLoading = false;
          this.list = response.data.list;
          this.total = response.data.total;
        });
      },
      uploadTemplate(){
        this.importVisible = false;
        uploadTemplateUrl().then(res => {
          const link = document.createElement('a')
          link.href = process.env.IMAGE_API+res.data
          link.click()
          this.$message({
            message: '导出成功！',
            type: 'success'
          });
        })
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


