<!--
 * @Descripttion: 部门管理
 * @Author: Dyl
 * @Date: 2021-11-10 10:07:23
-->
<template>
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <div style="margin-top: 15px">
        <el-form :inline="true" :model="listQuery" size="mini" label-width="75px">
          <el-form-item label="部门名称：">
            <el-input v-model="listQuery.deptName" class="input-width" placeholder="部门名称" clearable></el-input>
          </el-form-item>
          <el-form-item label="部门电话：">
            <el-input v-model="listQuery.deptPhone" class="input-width" placeholder="部门电话" clearable></el-input>
          </el-form-item>
          <el-form-item label="部门负责人：" label-width="100px">
            <el-input v-model="listQuery.leader" class="input-width" placeholder="部门负责人" clearable></el-input>
          </el-form-item>
          <el-form-item label="部门负责人电话：" label-width="110px">
            <el-input v-model="listQuery.phone" class="input-width" placeholder="部门负责人电话" clearable></el-input>
          </el-form-item>
          <el-form-item label="所属城市：">
            <el-input v-model="listQuery.city" class="input-width" placeholder="所属城市" clearable></el-input>
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
      <el-button size="mini" class="btn-add" @click="handleAdd()" type="warning">添加</el-button>
      <el-button size="mini" class="btn-add" @click="batchBan()" type="danger" >批量封禁</el-button>
    </div>
    <div class="table-container">
      <el-table ref="adminTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading" border>
        <el-table-column
          type="selection"
          width="55"
          :selectable="selectStatus"
           align="center"
        >
        </el-table-column>
        <el-table-column label="ID" align="center">
          <template slot-scope="scope">{{scope.row.deptId}}</template>
        </el-table-column>
        <el-table-column label="部门名称" align="center">
          <template slot-scope="scope">{{scope.row.deptName}}</template>
        </el-table-column>
        <el-table-column label="部门负责人" align="center">
          <template slot-scope="scope">{{scope.row.leader}}</template>
        </el-table-column>
        <el-table-column label="部门负责人电话" align="center">
          <template slot-scope="scope">{{scope.row.phone}}</template>
        </el-table-column>
        <el-table-column label="创建日期" align="center">
          <template slot-scope="scope">{{scope.row.createTime | formatDateTime}}</template>
        </el-table-column>
        <el-table-column label="部门电话" align="center">
          <template slot-scope="scope">{{scope.row.deptPhone}}</template>
        </el-table-column>
        <el-table-column label="所属城市" align="center">
          <template slot-scope="scope">{{scope.row.city}}</template>
        </el-table-column>
        <el-table-column label="操作" align="center">
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
                       @click="handleStatusChange(scope.$index, scope.row)" v-if="scope.row.status == 0">封禁
            </el-button>
            <el-button size="mini"
                       type="success"
                       round
                       @click="handleStatusChange(scope.$index, scope.row)" v-else>激活
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
      :title="isEdit?'编辑部门':'添加部门'"
      :visible.sync="dialogVisible"
      width="40%">
      <el-form :model="admin"
               ref="adminForm"
               label-width="150px" size="small">
        <el-form-item label="部门名称：">
          <el-input v-model="admin.deptName" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="部门电话：">
          <el-input v-model="admin.deptPhone" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="负责人：">
          <el-input v-model="admin.leader" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="负责人电话：">
          <el-input v-model="admin.phone" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="所属城市：">
          <el-input v-model="admin.city" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="部门备注：">
          <el-input v-model="admin.remark"  type="textarea" style="width: 250px"></el-input>
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
  import { getDeptList, addDept, editDept, batchBanDept  } from '@/api/department'
  import { formatDate } from '@/utils/date';
  const defaultListQuery = {
    pageNum: 1,
    pageSize: 10,
    deptName: '',
    deptPhone:'',
    leader:'',
    city:'',
    phone:''
  };
  const defaultAdmin = {
    deptName:null,
    city:null,
    leader:null,
    deptPhone: null,
    phone: null,
    remark:null
  };
  export default {
    data(){
      return {
        listQuery: Object.assign({}, defaultListQuery),
        list: null,
        total: null,
        listLoading: false,
        dialogVisible: false,
        admin: Object.assign({}, defaultAdmin),
        isEdit: false
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
      // 添加员工
      handleAdd() {
        this.dialogVisible = true;
        this.isEdit = false;
        this.admin = Object.assign({},defaultAdmin);
      },
      // 封禁/解封部门
      handleStatusChange(index, row) {
        this.$confirm(`是否要${row.status == 1 ? '激活' : '封禁'}该部门?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          editDept({deptId:row.deptId,status: row.status == 0 ? 1 : 0 }).then(response => {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            this.getList();
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消修改'
          });
          this.getList();
        });
      },
      // 编辑部门
      handleUpdate(index, row) {
        this.dialogVisible = true;
        this.isEdit = true;
        this.admin = Object.assign({},row);
      },
      // 编辑/添加部门
      handleDialogConfirm() {
        this.$confirm('是否要确认?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (this.isEdit) {
            editDept(this.admin).then(response => {
              this.$message({
                message: '修改成功！',
                type: 'success'
              });
              this.dialogVisible =false;
              this.getList();
            })
          } else {
            addDept(this.admin).then(response => {
              this.$message({
                message: '添加成功！',
                type: 'success'
              });
              this.dialogVisible =false;
              this.getList();
            })
          }
        })
      },
      // 获取列表
      getList() {
        this.listLoading = true;
        let data = JSON.parse(JSON.stringify(this.listQuery))
        // console.log(data['creatTime'])
        // data['startTime'] = data['creatTime'] && data['creatTime'].length > 0 ? data['creatTime'][0] : '';
        // data['endTime'] = data['creatTime'] && data['creatTime'].length > 0 ? data['creatTime'][1] : '';
        // delete data.creatTime;
        // data['status'] = data.status || '-1';
        getDeptList(data).then(response => {
          console.log(response)
          this.listLoading = false;
          this.list = response.data.list;
          this.total = response.data.total;
        });
      },
      // 批量封禁
      batchBan(){
        let chooseData = this.$refs.adminTable.selection;
        if(chooseData.length == 0){
          this.$message({
            message: '请勾选部门！',
            type: 'error'
          });
          return false;
        }
        this.$confirm('是否要确认封禁选中部门?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let ids = [];
          chooseData.map((i)=>{
            ids.push(`ids=${i.deptId}`)
          })
          batchBanDept(ids.join('&')).then(()=>{
            this.$message({
              message: '封禁成功！',
              type: 'success'
            });
            this.getList();
          }).catch(()=>{

          })
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
  /deep/ .el-form-item__content .el-button+.el-button{
    margin-left:6px;
  }
</style>