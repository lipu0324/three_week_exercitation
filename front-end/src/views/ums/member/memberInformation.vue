<template>
  <div class="app-container">
    <div class="type">
      <span>问题类型:{{problemTypeName}}</span>

    </div>
    <el-card class="operate-container"
             shadow="never">
      <i class="el-icon-tickets"></i>
      <span>问题列表：</span>
      <el-button size="mini"
                 class="btn-add"
                 @click="handleAdd()"
                 style="margin-left: 20px">添加</el-button>
      <!-- 添加列表项函数 -->
    </el-card>
    <div class="table-container">
      <el-table ref="roleTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading"
                border>
        <el-table-column type="selection"
                         width="60"
                         align="center"></el-table-column>
        <el-table-column prop="name"
                         label="问题名称"
                         width="250"
                         align="center">
          <template slot-scope="scope">{{ scope.row.problemTitle }}</template>
        </el-table-column>
        <el-table-column prop="date"
                         label="创建时间"
                         width="250"
                         align="center">
          <template slot-scope="scope">{{ scope.row.createTime }}</template>
        </el-table-column>
        <el-table-column prop="solved"
                         label="已解决数"
                         width="180"
                         align="center">
          <template slot-scope="scope">{{ scope.row.solveCount }}</template>
        </el-table-column>
        <el-table-column prop="unsolved"
                         label="未解决数"
                         width="180"
                         align="center">
          <template slot-scope="scope">{{ scope.row.unSolveCount }}</template>
        </el-table-column>
        <!-- <el-table-column prop="detail" label="问题详细" width="180" align="center">
          <template slot-scope="scope">{{ scope.row.problemDetail }}</template>
        </el-table-column> -->
        <el-table-column label="常见问题"
                         width="135"
                         align="center">
          <template slot-scope="scope">
            <el-select v-model="scope.row.status"
                       @change="handleShowStatusChange(scope.$index, scope.row)"
                       placeholder="请选择">
              <el-option v-for="item in options"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value"></el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="操作"
                         align="center">
          <template slot-scope="scope">
            <el-dialog title="查看详情"
                       :visible.sync="showDialog"
                       width="50%">
              <p v-html="content">{{content}}</p>
              <span slot="footer"
                    class="dialog-footer">
                <el-button @click="handleShowDialog">取消</el-button>
              </span>
            </el-dialog>
            <el-button size="mini"
                       @click="checkDetail(scope.row.problemDetail)">查看详情</el-button>
            <el-button size="mini"
                       type="danger"
                       @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination-container">
      <el-pagination background
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     layout="total, sizes,prev, pager, next,jumper"
                     :current-page.sync="listQuery.pageNum"
                     :page-size="listQuery.pageSize"
                     :page-sizes="[5, 10, 15]"
                     :total="total">
        <!-- 改变页数函数；改变项数size函数 -->
      </el-pagination>
    </div>
    <el-dialog title="问题类型："
               :visible.sync="dialogVisible"
               width="60%">
      <el-form :model="result"
               ref="roleForm"
               label-width="150px"
               size="small">
        <el-form-item label="问题名称："
                      :label-width="formLabelWidth">
          <el-input v-model="result.name"
                    autocomplete="off"
                    style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="是否常见问题："
                      :label-width="formLabelWidth">
          <el-radio-group v-model="result.status">
            <el-radio :label="0">是</el-radio>
            <el-radio :label="1">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="已解决人数"
                      :label-width="formLabelWidth">
          <el-input v-model="result.solveCount"
                    autocomplete="off"
                    style="width: 250px"></el-input>
        </el-form-item>
        <el-form-item label="未解决人数"
                      :label-width="formLabelWidth">
          <el-input v-model="result.unSolveCount"
                    autocomplete="off"
                    style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="问题描述："
                      :label-width="formLabelWidth">
          <el-tabs type="card">
            <tinymce :width="715"
                     :height="800"
                     v-model="result.detail"></tinymce>
          </el-tabs>
        </el-form-item>
      </el-form>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="dialogVisible = false"
                   size="small">取 消</el-button>
        <el-button type="primary"
                   @click="handleDialogConfirm()"
                   size="small">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { detailProList, changeProStatus, deleteDetailPro } from "@/api/problem";
import { addPro } from "@/api/problem";
import { formatDate } from "@/utils/date";
import Tinymce from "@/components/Tinymce";

const defaultListQuery = {
  pageNum: 1,
  pageSize: 5,
  id: ""
};
const defaultRole = {
  id: null,
  name: null,
  description: null,
  adminCount: 0,
  status: 1
};
export default {
  name: "information",
  components: { Tinymce },
  // props: {
  //   value: Object,
  //   isEdit: {
  //     type: Boolean,
  //     default: false
  //   }
  // },
  data () {
    return {
      listQuery: Object.assign({}, defaultListQuery),
      list: null,
      total: null,
      result: {
        name: null,
        problem_type_id: null,
        problem_detail: null,
        solve_count: null,
        unsolve_count: null,
        status: null
      },
      id: "",
      problemTypeName: "",
      showDialog: false,
      content: "",
      listLoading: false,
      dialogVisible: false,
      role: Object.assign({}, defaultRole),
      // isEdit: false,
      activeHtmlName: "pc",
      activeHtmlName: "pc",
      options: [
        {
          value: 0,
          label: "是"
        },
        {
          value: 1,
          label: "否"
        }
      ]
    };
  },
  created () {
    defaultListQuery.id = this.$route.query.id;
    this.problemTypeName = this.$route.query.problemTypeName;
    this.listQuery = defaultListQuery;
    this.getDetailProList();
  },
  filters: {
    formatDateTime (time) {
      if (time == null || time === "") {
        return "N/A";
      }
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    }
  },
  methods: {
    // removeHTMLTag(str) {
    //   str = str.replace(/<\/?[^>]*>/g, ""); //去除HTML tag
    //   str = str.replace(/[ | ]*\n/g, "\n"); //去除行尾空白
    //   //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
    //   str = str.replace(/ /gi, ""); //去掉
    //   return str;
    // },
    // //转意符换成普通字符
    // escape2Html(str) {
    //   var arrEntities = { lt: "<", gt: ">", nbsp: " ", amp: "&", quot: '"' };
    //   return str.replace(/&(lt|gt|nbsp|amp|quot);/gi, function(all, t) {
    //     return arrEntities[t];
    //   });
    // },
    getDetail (str) {
      str = this.removeHTMLTag(str);
      str = this.escape2Html(str);
      return str;
    },
    handleDialogConfirm () {
      const formData = new FormData();
      formData.append("problem_type_id", this.listQuery.id);
      formData.append("problem_title", this.result.name);
      formData.append("problem_detail", this.result.detail);
      formData.append("solve_count", this.result.solveCount);
      formData.append("unsolve_count", this.result.unSolveCount);
      formData.append("status", this.result.status);
      addPro(formData)
        .then(response => {
          console.log("上传成功");

          this.dialogVisible = false;
          this.$message({
            type: "success",
            message: "添加成功!"
          });
          // 但是我们上传成功了图片， fileList 里面的值却没有改变，还好有on-change指令可以使用
        })
        .catch(response => {
          console.log("上传失败");
        });
    },
    handleShowDialog () {
      this.showDialog = !this.showDialog;
    },
    checkDetail (content) {
      this.showDialog = true;
      this.content = content;
    },
    handleResetSearch () {
      this.listQuery = Object.assign({}, defaultListQuery);
    },
    handleSearchList () {
      this.listQuery.pageNum = 1;
      this.getDetailProList();
    },
    handleSizeChange (val) {
      this.listQuery.pageNum = 1;
      this.listQuery.pageSize = val;
      this.getDetailProList();
    },
    handleCheck () {
      this.$router.push({ path: "/ads/detail", query: { id: row.id } });
    },
    handleCurrentChange (val) {
      this.listQuery.pageNum = val;
      this.getDetailProList();
    },
    handleShowStatusChange (index, row) {
      const formData = new FormData();
      formData.append("status", row.status);
      formData.append("id", row.id);
      changeProStatus(formData).then(response => {
        console.log("statusResponse:", response);
      });
    },
    handleAdd () {
      this.dialogVisible = true;
      // this.isEdit = false;
      this.role = Object.assign({}, defaultRole);
    },
    handleDelete (index, row) {
      this.$confirm("是否要删除该类型?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        let params = new Object();
        params.id = row.id.toString();
        console.log("params:", params);
        deleteDetailPro(params).then(response => {
          console.log("deleteDeResponse:", response);
          this.$message({
            type: "success",
            message: "删除成功!"
          });
          this.getDetailProList();
        });
      });
    },
    // handleDialogConfirm () {
    //   this.$confirm("是否要确认?", "提示", {
    //     confirmButtonText: "确定",
    //     cancelButtonText: "取消",
    //     type: "warning"
    //   }).then(() => {
    //     if (this.isEdit) {
    //       updateRole(this.role.id, this.role).then(response => {
    //         this.$message({
    //           message: "修改成功！",
    //           type: "success"
    //         });
    //         this.dialogVisible = false;
    //         this.getList();
    //       });
    //     } else {
    //       createRole(this.role).then(response => {
    //         this.$message({
    //           message: "添加成功！",
    //           type: "success"
    //         });
    //         this.dialogVisible = false;
    //         this.getList();
    //       });
    //     }
    //   });
    // },
    getDetailProList () {
      this.listLoading = true;
      detailProList(this.listQuery).then(response => {
        this.listLoading = false;
        this.list = response.data.list;
        this.total = response.data.total;
        console.log("DetailProList:", response);
      });
    }
  }
};
</script>
<style></style>
