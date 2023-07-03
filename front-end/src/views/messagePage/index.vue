<!--
 * @Author: Dyl
 * @Date: 2022-11-21 16:28:10
 * @Description: 新的短信页面
-->
<template>
  <div :style="pageType=='pc' ? 'padding:20px' : 'padding:10px'">
    <div v-for="item in textData" class="content">{{item}}</div>
  </div>
</template>
<script>
import request from '@/utils/request'
import { parseTime } from '@/utils/index'
export default {
  data() {
    return {
      data:[],
      listLoading:false,
      queryData:[],
      textData:[],
      pageType:document.documentElement.clientWidth > 415 ? 'pc' : 'mobile',
    }
  },
  provide(){
    return{
      reload:this.reload
    }
  },
  watch: {
    '$route' (to, from) {
      // 对路由变化作出响应...
      // this.routerParms.list=to.query.list
      this.reload()
    },
  },
  created() {
    if(this.$route.query.type && this.$route.query.type !== ''){
      this.queryData = {
        type:this.$route.query.type
      }
    }
    this.getList()
  },
  methods: {
    getList(){
      request({
        url:"/admin-api/ngd/getSendInfo",
        method:"post",
        params:{
          type:this.queryData.type,
        }
      }).then((res)=>{
        this.textData = res.data;
      })
    },
    reload(){
      window.location.reload();
    }
  },
}
</script>
<style lang="scss" scoped>
  .isInline{
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    overflow:hidden;
    -webkit-box-orient: vertical;
  }
  .searchBox{
    padding-bottom: 20px;
    width: 100%;
    box-sizing:border-box;
  }
  .content{
    font-size:14px;
    line-height: 24px;
    color:#666666;
  }
</style>