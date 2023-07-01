<!--
 * @Author: DYL
 * @Date: 2021-11-22 16:22:59
 * @LastEditTime: 2021-12-09 11:47:16
-->
<template>
  <div>
    <el-dialog
      :title="title"
      :visible="dialogVisible"
      width="40%"
      :before-close="handleClose"
      :close-on-click-modal="false">
      <div class="dialogContent">
        <div class="searchBox">
          <el-input placeholder="请输入咨询者名称或档案编号" v-model="searchText" class="input-with-select">
            <el-button @click="queryButton" slot="append" icon="el-icon-search">搜索</el-button>
          </el-input>
        </div>
        <div class="answerList" @scroll="scrollEvent($event)">
          <div v-for="item in dataList" @click="pickerItem(item.id)" class="dataItem" :class="{'chooseStyle' : item.choose == 1}">
            <div>档案编号：{{item.code}}</div>
            <div>{{item.name}}</div>
          </div>
        </div>
      </div>
      <div class="dialog-footer">
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary" @click="submitHandler">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import {getArchiveList,getArchiveDetail} from "@/api/archives.js"
  export default {
    data() {
      return {
        dialogVisible:true,
        searchText:"",
        dataList:[],
        pageSize:6,
        pageNum:1,
        loadingSatus: 'loadmore', //是否加载更多
      }
    },
    props: {
      type: {
        default: null
      },
      title: {
        default: null
      }
    },
    methods: {
      // 点击搜索
      queryHandler(){
        let _this = this;
        if(_this.searchText == ''){
          _this.$message.error('请输入咨询者名称或档案编号');
          return false;
        }
        if(_this.loadingSatus == 'nomore' || _this.loadingSatus == 'loading'){
          return false;
        }else if(_this.loadingSatus == 'loadmore'){
          _this.loadingSatus = 'loading';
        }
        getArchiveList({
          nameOrUrgency:_this.searchText,
          pageSize:_this.pageSize,
          pageNum:_this.pageNum
        }).then((res)=>{
          res.data.list.map((item)=>{
            item['choose'] = 0
          })
          _this.dataList = _this.dataList.concat(res.data.list);
          if(_this.pageNum >= res.data.totalPage){
            _this.loadingSatus = 'nomore'
          }else{
            _this.pageNum = _this.pageNum+1;
            _this.loadingSatus = 'loadmore'
          }
        }).catch(()=>{
          _this.loadingSatus = 'loadmore';
        })
      },
      queryButton(){
        this.loadingSatus = this.loadingSatus == 'nomore' ? 'loadmore' : this.loadingSatus;
        this.pageNum = 1;
        this.dataList = [];
        this.queryHandler();
      },
      // 选择列表
      pickerItem(id){
        this.dataList.map((item)=>{
          if(item.id == id){
            item['choose'] = 1
          }else{
            item['choose'] = 0
          }
        })
      },
      // 点击确定
      submitHandler(){
        let data = this.dataList.filter(m=>m.choose == 1);
        if(data.length == 0){
          this.$message.error('请选择档案');
          return false;
        }
        getArchiveDetail({
          id:data[0].id
        }).then((res)=>{
          this.$emit("submit",res.data)
        })
      },
      // 点击取消/关闭
      handleClose(){
        this.$emit("close")
      },
      // 列表滚动事件
      scrollEvent(e) {
        if (e instanceof Event) {
          let el = e.target;
          let scrollTop = el.scrollTop;
          // 获取可视区的高度
          let clientHeight = el.clientHeight;
          // 获取滚动条的总高度
          let scrollHeight = el.scrollHeight;
          let height = 50;
          //到底了
          // console.log(this.deviceListIsLoad, this.deviceListIsFinish);
          // console.log(scrollTop, clientHeight, scrollHeight);
          //是否继续加载且已完成加载
          if (
            scrollTop + clientHeight + height >= scrollHeight &&
            this.loadingSatus == 'loadmore'
          ) {
            // 把距离顶部的距离加上可视区域的高度 等于或者大于滚动条的总高度就是到达底部
            console.log("到底了");
            setTimeout(() => {
              this.queryHandler();
            }, 1000);
          }
        }
      }
    },
  }
</script>
<style lang="scss" scoped>
  .dialog-footer{
    display:flex;
    align-items:center;
    justify-content:center;
    margin-top:30px;
  }
  .dialogContent{
    width:90%;
    max-width:400px;
    margin:auto;
    border:1px solid #D9D9D9;
    border-radius:4px;
    .searchBox{
      width: 100%;
      /deep/ .el-input-group{
        border-radius:4px;
        .el-input__inner{
          border:none;
          border-bottom:1px solid #D9D9D9;
          border-radius:0;
        }
      }
      /deep/ .el-input-group__append{
        border:none;
        border-radius:0;
        border-bottom:1px solid #D9D9D9;
        .el-button{
          border-radius:0;
          border:none;
          border-left:1px solid #D9D9D9;
          background:#fff;
        }
      }
    }
    .answerList{
      width: 100%;
      height: 40vh;
      overflow-y: auto;
      .dataItem{
        width: 100%;
        display:flex;
        align-items:center;
        padding: 0 15px;
        box-sizing:border-box;
        justify-content: space-between;
        border-bottom:1px solid #E5E5E5;
        font-size:12px;
        line-height:1.0;
        height: 42px;
        color:#999999;cursor: pointer;
      }
      .dataItem:last-child{
        border-bottom:none
      }
      .chooseStyle{
        background:#0177D5;
        color:#fff;
      }
    }
  }
</style>