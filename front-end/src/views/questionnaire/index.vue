<!--
 * @Author: Dyl
 * @Date: 2022-02-14 14:16:33
 * @LastEditTime: 2022-03-01 17:00:21
 * @LastEditors: Please set LastEditors
 * @Description: 问卷列表
 * @FilePath: \sfcc\src\views\questionnaire\index.vue
-->
<template>
  <div class="app-container">
    <div class="filter-container" shadow="never">
      <div style="margin-top: 15px">
        <el-form :inline="true" :model="listQuery" size="mini" label-width="100px">
          <el-form-item label="问卷名称：">
            <el-input v-model="listQuery.question" class="input-width" placeholder="问卷名称" clearable></el-input>
          </el-form-item>
          <el-form-item label="问卷配置情况：">
            <el-select v-model="listQuery.status" placeholder="请选择">
              <el-option
                v-for="item in configStatus"
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
      <el-button size="mini" class="btn-add" @click="handleAdd()" type="warning" >添加问卷</el-button>
    </div>
    <div class="table-container">
      <el-table ref="adminTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading" border>
        <el-table-column label="ID" width="100" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="问卷名称" align="center">
          <template slot-scope="scope">{{scope.row.title}}</template>
        </el-table-column>
        <el-table-column label="创建时间" align="center">
          <template slot-scope="scope">{{scope.row.createdTime | formatDateTime}}</template>
        </el-table-column>
        <el-table-column label="配置情况" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.status == 1">已完成</span>
            <el-popover
              v-else
              title=""
              width="300"
              trigger="hover"
              content="您需要设置此问卷的初始问题，并按顺序设置每个问题的下一个问题">
              <p slot="reference" class="textOverflow" style="text-align:center">未完成</p>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="开始语" align="center">
          <template slot-scope="scope">
            <el-popover
              placement="right"
              title=""
              width="200"
              trigger="hover"
              :content="scope.row.welText">
              <p slot="reference" class="textOverflow">{{scope.row.welText}}</p>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="结束语" align="center">
          <template slot-scope="scope">
            <el-popover
              placement="right"
              title=""
              width="200"
              trigger="hover"
              :content="scope.row.endText">
              <p slot="reference" class="textOverflow">{{scope.row.endText}}</p>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center">
          <template slot-scope="scope">
            <el-popover
              placement="right"
              title=""
              width="200"
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
                       @click="handleUpdate(scope.$index, scope.row)">
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
                @click="showDetail(scope.$index, scope.row)">查看问卷内容
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
      :title="isEdit?'编辑问卷':'添加问卷'"
      :visible.sync="dialogVisible"
      width="40%"
      @close="clearError">
      <el-form :model="admin"
               ref="adminForm"
               label-width="150px"
               size="small"
              :rules="rules"
      >
        <el-form-item label="问卷名称：" prop="title">
          <el-input v-model="admin.title" style="width: 250px" maxlength="25"></el-input>
        </el-form-item>
        <el-form-item label="开始语：">
          <el-input v-model="admin.welText"
                    type="textarea"
                    :rows="5"
                    style="width: 250px"
                    maxlength="120"></el-input>
        </el-form-item>
        <el-form-item label="结束语：">
          <el-input v-model="admin.endText"
                    type="textarea"
                    :rows="5"
                    style="width: 250px"
                    maxlength="120"></el-input>
        </el-form-item>
        <el-form-item label="问卷备注：">
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
import {addQuestionnaire,delQuestionnaire,getList,changeQuestionnaire} from '@/api/questionnaire.js'
import {formatDate} from '@/utils/date';
const defaultListQuery = {
  pageNum: 1,
  pageSize: 10,
  question: null,
  status:null
};
const defaultAdmin = {
  title: '',
  status:0,
  remark:'',
  endText:'',
  welText:'',
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
        ]
      },
      configStatus:[
        {
          value: '0',
          label: '未完成'
        },{
          value: '1',
          label: '已完成'
        }
      ],
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
      return formatDate(date, 'yyyy-MM-dd hh:mm:ss')
    }
  },
  methods: {
    // 获取问卷列表
    getList() {
      this.listLoading = true;
      getList(this.listQuery).then(response => {
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
    handleAdd() {
      this.dialogVisible = true;
      this.isEdit = false;
      this.admin = Object.assign({},defaultAdmin);
    },
    handleDelete(index, row) {
      this.$confirm('是否要删除该问卷?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delQuestionnaire({id:row.id}).then(response => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.getList();
        });
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
              changeQuestionnaire(data).then(response => {
                this.$message({
                  message: '修改成功！',
                  type: 'success'
                });
                this.dialogVisible =false;
                this.getList();
              })
            } else {
              addQuestionnaire(this.admin).then(response => {
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
    // 查看问卷内容
    showDetail(index,row){
      this.$router.push({
        path:`/questionnaire/questions?id=${row.id}`
      })
    },
    // 弹窗关闭清空错误
    clearError(){
      this.$refs.adminForm.clearValidate();
    },
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
