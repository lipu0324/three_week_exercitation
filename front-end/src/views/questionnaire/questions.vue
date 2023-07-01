<!--
 * @Author: Dyl
 * @Date: 2022-02-14 16:15:42
 * @LastEditTime: 2022-02-28 16:54:12
 * @LastEditors: Please set LastEditors
 * @Description: 问卷问题页面
 * @FilePath: \sfcc\src\views\questionnaire\questions.vue
-->
<template>
  <div class="app-container">
    <div class="operate-container" shadow="never" style="padding-top:15px">
      <el-button size="mini" class="btn-add" @click="handleAdd()" type="warning" >添加问题</el-button>
    </div>
    <div class="table-container">
      <el-table ref="adminTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading" border>
        <el-table-column label="ID" width="100" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="问题名称" align="center">
          <template slot-scope="scope">{{scope.row.title}}</template>
        </el-table-column>
        <el-table-column label="问题回答类型" align="center">
          <template slot-scope="scope">{{scope.row.type | getTypes}}</template>
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
                @click="showDetail(scope.$index, scope.row)">查看回答
            </el-button>
            <el-button size="mini"
              type="primary"
              round
              @click="setHandle(scope.$index, scope.row)">
              配置问题
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
      :title="isEdit?'编辑问题':'添加问题'"
      :visible.sync="dialogVisible"
      width="40%"
      @close="clearError('default')">
      <el-form :model="admin"
               ref="adminForm"
               label-width="150px"
               size="small"
              :rules="rules"
      >
        <el-form-item label="问题名称：" prop="title">
          <el-input v-model="admin.title" style="width: 250px" maxlength="50"></el-input>
        </el-form-item>
        <el-form-item label="问题回答类型：">
          <el-select v-model="admin.type" placeholder="请选择">
            <el-option
              v-for="item in questionType"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="handleDialogConfirm()" size="small">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog 
      title="配置问卷"
      :visible.sync="setDialogVisible"
      width="40%"
      @close="clearError('set')">
      <el-form :model="admin"
               ref="setFormB"
               label-width="200px"
               size="small"
              :rules="rulesB"
      >
        <el-form-item label="是否是初始问题：" prop="isFirst">
          <el-switch
            :active-value="1"
            :inactive-value="0"
            v-model="admin.isFirst">
          </el-switch>
        </el-form-item>
        <el-form-item label="肯定回答的下一个问题：" v-if="admin.type == 0" prop="isTrueId">
          <el-select v-model="admin.isTrueId" placeholder="请选择">
            <el-option
              v-for="item in noChooseList"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="否定回答的下一个问题：" v-if="admin.type == 0" prop="isFalseId">
          <el-select v-model="admin.isFalseId" placeholder="请选择">
            <el-option
              v-for="item in noChooseList"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="下一个问题：" v-if="admin.type == 1" prop="nextId">
          <el-select v-model="admin.nextId" placeholder="请选择">
            <el-option
              v-for="item in noChooseList"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="setDialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="handleDialogConfirm('set')" size="small">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import {addQuestions,delQuestions,getQuestionsList,changeQuestions,getNoChooseQuse} from '@/api/questionnaire.js'
import {formatDate} from '@/utils/date';
const defaultListQuery = {
  pageNum: 1,
  pageSize: 10
};
const defaultAdmin = {
  title: '',
  type:0
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
          { required: true, message: '请输入问题名称', trigger: 'blur' },
        ]
      },
      rulesB:{
        isFirst: [
          { required: true },
        ],
        // isTrueId: [
        //   { required: true, message: '请选择肯定回答的下一个问题', trigger: 'change' },
        // ],
        // isFalseId: [
        //   { required: true, message: '请选择否定回答的下一个问题', trigger: 'change' },
        // ],
        // nextId: [
        //   { required: true, message: '请选择下一个问题', trigger: 'change' },
        // ]
      },
      naireId:"",
      questionType:[
        {
          value: 0,
          label: '是否'
        },{
          value: 1,
          label: '文本'
        }
      ],
      setDialogVisible:false,
      noChooseList:[]
    }
  },
  created() {
    this.naireId = this.$route.query.id;
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
    getTypes(type){
      return type == 0 ? '是否' : '文本'
    }
  },
  methods: {
    // 获取问题列表
    getList() {
      this.listLoading = true;
      this.listQuery['naireId'] = this.naireId
      getQuestionsList(this.listQuery).then(response => {
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
      this.$confirm('是否要删除该问题?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delQuestions({id:row.id}).then(response => {
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
    // 弹窗关闭清空错误
    clearError(type){
      let from = type == 'set' ? this.$refs.setFormB : this.$refs.adminForm
      from.clearValidate();
    },
    handleDialogConfirm(type) {
      let from = type ? this.$refs.setFormB : this.$refs.adminForm
      from.validate((valid) => {
        if (valid) {
          this.$confirm('是否要确认?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            if (this.isEdit || type == 'set') {
              let data = JSON.parse(JSON.stringify(this.admin));
              data['naireId'] = this.naireId
              changeQuestions(data).then(response => {
                this.$message({
                  message: '修改成功！',
                  type: 'success'
                });
                this.dialogVisible =false;
                this.setDialogVisible =false;
                this.getList();
              })
            } else {
              let data = JSON.parse(JSON.stringify(this.admin));
              data['naireId'] = this.naireId
              addQuestions(data).then(response => {
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
    // 查看问题内容
    showDetail(index,row){
      this.$router.push({
        path:`/questionnaire/answers?id=${row.id}`
      })
    },
    // 配置问题
    setHandle(index,row){
      let _this = this;
      this.noChooseList = []
      this.setDialogVisible = true;
      let admin = Object.assign({},row);
      admin['isFirst'] = row.isFirst || 0; //是否是初始问题，1是 0否
      if(row.type == 0){ // 是否问题类型
        admin['isTrueId'] = row.isTrueId; //肯定的下一个问题id
        admin['isFalseId'] = row.isFalseId; //否定的下一个问题id
      }else{
        admin['nextId'] = row.nextId; //文本的下一个问题id
      }
      this.admin = admin;
      getNoChooseQuse({
        naireId:this.naireId,
        questionId:row.id
      }).then((res)=>{
        res.data.map((item)=>{
          if(item.id != row.id){
            _this.noChooseList.push({
              value: item.id,
              label: item.title
            })
          }
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  
</style>
