<!--
 * @Author: Dyl
 * @Date: 2022-02-17 16:03:22
 * @LastEditTime: 2022-02-25 15:48:03
 * @LastEditors: Please set LastEditors
 * @Description: 选择用户组件
 * @FilePath: \sfcc\src\views\groupCall\components\chooseUser.vue
-->
<template>
  <div class="chooseBox" @click.stop="showChooseList = true">
    <el-input v-model="inputValue" type="textarea" ref="phoneInput" @input="inputChoose" placeholder="输入电话号码 以逗号分隔"></el-input>
    <div class="infinite-list userBox" :infinite-scroll-disabled="loadStatus == 'nomore'" v-infinite-scroll="getUserList('more')" :infinite-scroll-distance="20" v-if="showChooseList">
      <div v-for="(item,index) in userList" :key="index" class="userItem" @click="chooseHandle(item)" :class="{'activeClass': phoneArray.indexOf(item.phone) != -1}">
        <p>{{item.phone}}</p>
        <p>{{item.username}}</p>
      </div>
    </div>
  </div>
</template>
<script>
import { memberList } from '@/api/member';
export default {
  name:"ChooseUser",
  data() {
    return {
      inputValue:"",
      phoneArray:[],
      userList:[],
      showChooseList:false,
      pageNum:1,
      loadStatus:'loadmore'
    }
  },
  props:{
    data:{
      default:null
    }
  },
  created() {
    this.inputValue = this.data
    this.phoneArray = this.data.split(",")
  },
  methods: {
    getUserList(type){
      let _this = this;
      if(_this.loadStatus == 'loadmore'){
        _this.loadStatus = 'loading'
      }else{
        return false
      }
      memberList({
        pageNum:_this.pageNum,
        pageSize:10,
        label:-1
      }).then((res)=>{
        _this.userList = _this.userList.concat(res.data.list);
        _this.pageNum += 1
        if(res.data.totalPage < _this.pageNum){
          _this.loadStatus = 'nomore'
        }else{
          _this.loadStatus = 'loadmore'
        }
      })
    },
    chooseHandle(data){
      if(this.phoneArray.indexOf(data.phone) != -1){ // 如果包含选中的电话 则取消选中
        this.phoneArray.splice(this.phoneArray.indexOf(data.phone), 1); 
      }else{
        // 反之则选中
        this.phoneArray.push(data.phone)
      }
      // 整理成字符串
      let inputValue = this.phoneArray.join(",");
      if(inputValue.substr(0,1) == ','){ // 如果第一位是逗号 删除逗号
        inputValue = inputValue.substr(1)
      }else if(inputValue.slice(inputValue.length - 1) == ','){ // 如果最后一位是逗号
        inputValue = inputValue.slice(0,inputValue.length-1) // 删除最后一位
      }
      this.inputValue = inputValue;
    },
    hideChooseBox(){
      this.showChooseList = false;
    },
    inputChoose(){
      this.phoneArray = this.inputValue.replace(/，/ig,',').split(",")
    }
  },
}
</script>
<style lang="scss" scoped>
.chooseBox{width:250px}
  .userBox{
    width:250px;
    height:200px;
    border:1px solid #f1f1f1;
    overflow-y:auto;
    .userItem{
      display:flex;
      align-items:center;
      justify-content:space-between;
      height:30px;
      padding: 0 10px;
      cursor:pointer;
    }
    .activeClass{
      background:#8BCEFF
    }
  }
</style>