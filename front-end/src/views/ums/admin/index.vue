<!-- 员工列表 -->
<template> 
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
        <el-form :inline="true" :model="listQuery" size="mini" label-width="75px">
          <el-form-item label="姓名：">
            <el-input v-model="listQuery.name" class="input-width" placeholder="姓名" clearable></el-input>
          </el-form-item>
          <el-form-item label="电话：">
            <el-input v-model="listQuery.phone" class="input-width" placeholder="电话号码" clearable></el-input>
          </el-form-item>
          <el-form-item label="所属部门：">
            <el-select v-model="listQuery.deptId" clearable placeholder="请选择">
            <el-option
              v-for="item in deptList"
              :key="item.deptId"
              :label="item.deptName"
              :value="item.deptId">
            </el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="状态：">
            <el-select v-model="listQuery.status" placeholder="请选择">
              <el-option
                key="0"
                label="封禁"
                value="0">
              </el-option>
              <el-option
                key="1"
                label="激活"
                value="1">
              </el-option>
            </el-select>
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
          <el-form-item label="服务次数：">
            <el-row style="width:300px">
              <el-col :span="11">
                <el-input v-model="listQuery.startCount" clearable></el-input>
              </el-col> 
              <el-col :span="2" style="text-align:center"> - </el-col>
              <el-col :span="11">
                <el-input v-model="listQuery.endCount" clearable></el-input>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item style="float:right;margin-right:0">
            <el-button
              type="primary"
              @click="handleSearchList()"
              size="small">
              查询搜索
            </el-button>
            <el-button
              @click="handleResetSearch()"
              size="small">
              重置
            </el-button>
          </el-form-item>
        </el-form>
    </el-card>
    <div class="operate-container" shadow="never">
      <el-button size="mini" class="btn-add" @click="handleAdd()" type="danger" >
        添加员工
      </el-button>
      <el-button size="mini" class="btn-add" @click="batchBan()" type="primary">批量封禁</el-button>
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
        >
        </el-table-column>
        <el-table-column label="ID" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="账号" align="center">
          <template slot-scope="scope">{{scope.row.username}}</template>
        </el-table-column>
        <el-table-column label="姓名" align="center">
          <template slot-scope="scope">{{scope.row.nickName}}</template>
        </el-table-column>
        <el-table-column label="电话" align="center">
          <template slot-scope="scope">{{scope.row.phone}}</template>
        </el-table-column>
        <el-table-column label="注册时间" align="center">
          <template slot-scope="scope">{{scope.row.createTime | formatDateTime}}</template>
        </el-table-column>
        <el-table-column label="服务次数" align="center">
          <template slot-scope="scope">{{scope.row.count || '0'}}</template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="success"
                       round
                       @click="handleSelectRole(scope.$index, scope.row)">分配角色
            </el-button>
            <el-button size="mini"
                       type="primary"
                       round
                       @click="handleUpdate(scope.$index, scope.row)">
              编辑
            </el-button>
            <el-button size="mini"
                       type="danger"
                       round
                       @click="handleStatusChange(scope.$index, scope.row)" v-if="scope.row.status == 1">封禁
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
        layout="total,prev, pager, next,jumper"
        :current-page.sync="listQuery.pageNum"
        :page-size="listQuery.pageSize"
        :page-sizes="[10,15,20]"
        :total="total">
      </el-pagination>
    </div>
    <el-dialog
      :title="isEdit?'编辑用户':'添加用户'"
      :visible.sync="dialogVisible"
      width="40%">
      <el-form :model="admin"
               ref="adminForm"
               label-width="150px" size="small">
        <el-form-item label="账号头像：">
          <el-upload
            class="bank-cover-uploader"
            :action="uploadUrl"
            :show-file-list="false"
            :headers = headerConfig
            :on-success="handleSuccess"
            :data = getDate>
            <img v-if="admin.headImg" :src="admin.headImg | ImgUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        </el-form-item>
        <el-form-item label="账号：">
          <el-input v-model="admin.username" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="姓名：">
          <el-input v-model="admin.nickName" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="电话：">
          <el-input v-model="admin.phone" placeholder="输入手机号，非哈尔滨号码前请加0" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="密码：">
          <el-input v-model="admin.password"  type="password" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="座席号：">
          <el-input v-model="admin.code"  type="text" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="分机号：">
          <el-input v-model="admin.exNum"  type="text" style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="所属部门：">
          <el-select v-model="admin.deptId" placeholder="请选择">
            <el-option
              v-for="item in deptList"
              :key="item.deptId"
              :label="item.deptName"
              :value="item.deptId">
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
      title="分配角色"
      :visible.sync="allocDialogVisible"
      width="30%">
      <el-select v-model="allocRoleIds" multiple placeholder="请选择" size="small" style="width: 80%">
        <el-option
          v-for="item in allRoleList"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
      <span slot="footer" class="dialog-footer">
        <el-button @click="allocDialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="handleAllocDialogConfirm()" size="small">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import {fetchList,createAdmin,updateAdmin,updateStatus,deleteAdmin,getRoleByAdmin,allocRole,batchBan} from '@/api/login';
  import {fetchAllRoleList} from '@/api/role';
  import {formatDate} from '@/utils/date';
  import { getDeptList  } from '@/api/department'
  import { getToken  } from '@/utils/auth'

  const defaultListQuery = {
    pageNum: 1,
    pageSize: 10,
    name: '',
    phone:'',
    creatTime:'',
    startCount:'',
    endCount:'',
    status:'',
    deptId: ''
  };
  const defaultAdmin = {
    username:null,
    nickName: null,
    password: null,
    phone: null,
    code:null,
    exNum:null,
    deptId:null,
    headImg:null
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
        allocDialogVisible: false,
        allocRoleIds:[],
        allRoleList:[],
        allocAdminId:null,
        deptList:[],
        uploadUrl: `${process.env.IMAGE_API}/file/single`,
        headerConfig: {
          Authorization: 'Bearer ' + getToken()
        },
      }
    },
    computed: {
      // 设置图片上传路径参数
      getDate: function() {
        let date = new Date()
        let filedir = `businessVerify/${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()}`
        return {dir: filedir}
      },
    },
    created() {
      this.getList();
      this.getAllRoleList();
      this.getDeptList();
    },
    filters: {
      formatDateTime(time) {
        if (time == null || time === '') {
          return 'N/A';
        }
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd')
      },
      ImgUrl(url) {
        console.log(url)
        return `${process.env.IMAGE_API}/${url}`
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
      // 获取部门列表
      getDeptList() {
        getDeptList({
          pageNum: 1,
          pageSize: 100,
          status:0
        }).then(response => {
          this.deptList = response.data.list;
        });
      },
      // 封禁/解封用户
      handleStatusChange(index, row) {
        this.$confirm(`是否要${row.status == 0 ? '激活' : '封禁'}该用户?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          updateStatus(row.id, {status: row.status == 0 ? 1 : 0}).then(response => {
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
      // 删除用户
      handleDelete(index, row) {
        this.$confirm('是否要删除该用户?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteAdmin(row.id).then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getList();
          });
        });
      },
      // 编辑用户
      handleUpdate(index, row) {
        this.dialogVisible = true;
        this.isEdit = true;
        this.admin = Object.assign({},row);
      },
      // 编辑/添加用户
      handleDialogConfirm() {
        this.$confirm('是否要确认?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (this.isEdit) {
            updateAdmin(this.admin.id,this.admin).then(response => {
              this.$message({
                message: '修改成功！',
                type: 'success'
              });
              this.dialogVisible =false;
              this.getList();
            })
          } else {
            createAdmin(this.admin).then(response => {
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
      // 分配角色确认
      handleAllocDialogConfirm(){
        this.$confirm('是否要确认?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let params = new URLSearchParams();
          params.append("adminId", this.allocAdminId);
          params.append("roleIds", this.allocRoleIds);
          allocRole(params).then(response => {
            this.$message({
              message: '分配成功！',
              type: 'success'
            });
            this.allocDialogVisible = false;
          })
        })
      },
      // 分配角色显示弹窗
      handleSelectRole(index,row){
        this.allocAdminId = row.id;
        this.allocDialogVisible = true;
        this.getRoleListByAdmin(row.id);
      },
      // 获取列表
      getList() {
        this.listLoading = true;
        let data = JSON.parse(JSON.stringify(this.listQuery))
        console.log(data['creatTime'])
        data['startTime'] = data['creatTime'] && data['creatTime'].length > 0 ? data['creatTime'][0] : '';
        data['endTime'] = data['creatTime'] && data['creatTime'].length > 0 ? data['creatTime'][1] : '';
        delete data.creatTime;
        data['status'] = data.status || '-1';
        data['deptId'] = data.deptId || 0;
        fetchList(data).then(response => {
          this.listLoading = false;
          this.list = response.data.list;
          this.total = response.data.total;
        });
      },
      // 角色列表
      getAllRoleList() {
        fetchAllRoleList().then(response => {
          this.allRoleList = response.data;
        });
      },
      getRoleListByAdmin(adminId) {
        getRoleByAdmin(adminId).then(response => {
          let allocRoleList = response.data;
          this.allocRoleIds=[];
          if(allocRoleList!=null&&allocRoleList.length>0){
            for(let i=0;i<allocRoleList.length;i++){
              this.allocRoleIds.push(allocRoleList[i].id);
            }
          }
        });
      },
      // 批量封禁
      batchBan(){
        let chooseData = this.$refs.adminTable.selection;
        if(chooseData.length == 0){
          this.$message({
            message: '请勾选用户！',
            type: 'error'
          });
          return false;
        }
        this.$confirm('是否封禁选中用户?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let ids = [];
          chooseData.map((i)=>{
            ids.push(`ids=${i.id}`)
          })
          batchBan(ids.join('&')).then(()=>{
            this.$message({
              message: '封禁成功！',
              type: 'success'
            });
            this.getList();
          }).catch(()=>{

          })
        })
      },
      // 封禁用户不可勾选
      selectStatus(row) {
        if (row.status == 1) {
          return true
        } else {
          return  false
        }
      },
      // 图片上传成功回调
      handleSuccess(res) {
        this.listLoading = false
        if (res.code * 1 === 200) {
          this.admin.headImg = res.data
        } else {
          this.$alert(res.message,'提示',{
            type: "error"
          });
        }
      },
    }
  }
</script>
<style lang="scss" scoped>
  /deep/ .el-button--small{
    width:80px;
  }
  /deep/ .el-form-item__content .el-button+.el-button{
    margin-left:6px;
  }
  .avatar {
    width: 100px;
    height: 100px;
    display: block;
  }
</style>


