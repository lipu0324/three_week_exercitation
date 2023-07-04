<!--
 * @Descripttion: 标签列表
 * @Author: Dyl
 * @Date: 2021-10-22 15:27:45
-->
<template> 
  <div class="app-container">
    <div class="filter-container" shadow="never">
      <div style="margin-top: 15px">
        <el-form :inline="true" :model="listQuery" size="mini" label-width="75px">
          <el-form-item label="标签名称：">
            <el-input v-model="listQuery.name" class="input-width" placeholder="标签名称" clearable></el-input>
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
      <el-button size="mini" class="btn-add" @click="handleAdd()" type="warning" >添加标签</el-button>
      <el-button size="mini" class="btn-add" @click="batchDelete()" type="danger">批量删除</el-button>
    </div>
    <div class="table-container">
      <el-table ref="adminTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading" border>
        <el-table-column
          type="selection"
          width="55"
          align="center"
        >
        </el-table-column>
        <el-table-column label="ID" width="100" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="标签名称" align="center">
          <template slot-scope="scope">{{scope.row.name}}</template>
        </el-table-column>
        <el-table-column label="时间" align="center">
          <template slot-scope="scope">{{scope.row.createTime | formatDateTime}}</template>
        </el-table-column>
        <el-table-column label="是否显示" width="160" align="center">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.status"
              @change="(status)=>{changeTagsStatus(status,scope.row)}">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="primary"
                       round
                       @click="handleUpdate(scope.$index, scope.row)">
              编辑
            </el-button>
            <el-button size="mini"
                       type="danger"
                       round
                       @click="handleDelete(scope.$index, scope.row)">删除
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
      :title="isEdit?'编辑标签':'添加标签'"
      :visible.sync="dialogVisible"
      width="40%">
      <el-form :model="admin"
               ref="adminForm"
               label-width="150px"
               size="small"
              :rules="rules"
      >
        <el-form-item label="标签名称：" prop="name">
          <el-input v-model="admin.name" maxlength="10" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="标签备注：">
          <el-input v-model="admin.remark"
                    type="textarea"
                    :rows="5"
                    style="width: 250px"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="handleDialogConfirm()" size="small">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import {addTags,delTags,getList,changeTags,tagsStatusChange,batchDel} from '@/api/tags';
  import {formatDate} from '@/utils/date';

  const defaultListQuery = {
    pageNum: 1,
    pageSize: 10,
    name: null
  };
  const defaultAdmin = {
    name: '',
    status:0,
    remark:''
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
        admin: Object.assign({}, defaultAdmin),
        isEdit: false,
        rules:{
          name: [
            { required: true, message: '请输入活动名称', trigger: 'blur' },
          ]
        }
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
      // 获取标签列表
      getList() {
        getList(this.listQuery).then((result)=>{
          this.list=result.list;
          this.total=result.data.total;
        })
      },
      // 修改标签显示状态
      changeTagsStatus(status,data){

      },
      // 批量删除
      batchDelete(){
        if(this.$refs.adminTable.selection.length == 0){
          this.$message({
            message: '请选择标签！',
            type: 'error'
          });
          return false;
        }
        this.$confirm('是否要删除选中标签?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let chooseData = this.$refs.adminTable.selection;
          let ids = [];
          chooseData.map((i)=>{
            ids.push(`ids=${i.id}`)
          })
          batchDel(ids.join('&')).then(()=>{
            this.$message({
              message: '删除成功！',
              type: 'success'
            });
            this.getList();
          }).catch(()=>{

          })
        })
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
      handleAdd() {
        this.dialogVisible = true;
        this.isEdit = false;
        this.admin = Object.assign({},defaultAdmin);
      },
      handleDelete(index, row) {
        this.$confirm('是否要删除该标签?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

        });
      },
      handleUpdate(index, row) {
        this.dialogVisible = true;
        this.isEdit = true;
        this.admin = Object.assign({},row);
      },
      handleDialogConfirm() {
        this.$refs.adminForm.validate((valid) => {
          if (valid) {
            this.$confirm('是否要确认?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              if (this.isEdit) {
                let data = JSON.parse(JSON.stringify(this.admin));
                data['status'] = data.status ? 0 : 1;
                changeTags(data).then(response => {
                  this.$message({
                    message: '修改成功！',
                    type: 'success'
                  });
                  this.dialogVisible =false;
                  this.getList();
                })
              } else {
                addTags(this.admin).then(response => {
                  this.$message({
                    message: '添加成功！',
                    type: 'success'
                  });
                  this.dialogVisible =false;
                  this.getList();
                })
              }
            })
          }
        })
      },
    }
  }
</script>
<style lang="scss" scoped>
  /deep/ .el-form-item__content .el-button--mini,.operate-container .el-button--mini{
    width:80px;
  }
</style>


