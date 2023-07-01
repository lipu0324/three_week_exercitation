<!--
 * @Author: Dyl
 * @Date: 2022-02-15 14:47:25
 * @LastEditTime: 2022-02-22 10:02:24
 * @LastEditors: Please set LastEditors
 * @Description: 回答列表
 * @FilePath: \sfcc\src\views\questionnaire\answers.vue
-->
<template>
  <div class="app-container" style="padding-top:10px">
    <div class="table-container">
      <el-table ref="adminTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading" border>
        <el-table-column label="ID" width="100" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="回答内容" align="center">
          <template slot-scope="scope">
          <el-popover
              placement="right"
              title=""
              width="400"
              trigger="hover"
              :content="scope.row.userAnswer">
              <p slot="reference" class="textOverflow">{{scope.row.userAnswer}}</p>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="用户电话" align="center">
          <template slot-scope="scope">{{scope.row.userPhone}}</template>
        </el-table-column>
        <el-table-column label="回答时间" align="center">
          <template slot-scope="scope">{{scope.row.time | formatDateTime}}</template>
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
import { getAnswersList } from '@/api/questionnaire.js'
import {formatDate} from '@/utils/date';
const defaultListQuery = {
  pageNum: 1,
  pageSize: 10
};
export default {
  data() {
    return {
      listQuery: Object.assign({}, defaultListQuery),
      list: null,
      total: null,
      listLoading: false,
      questionId:""
    }
  },
  created() {
    this.questionId = this.$route.query.id;
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
    // 获取问题列表
    getList() {
      this.listLoading = true;
      this.listQuery['questionId'] = this.questionId
      getAnswersList(this.listQuery).then(response => {
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

