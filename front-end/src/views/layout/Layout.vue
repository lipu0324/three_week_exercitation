<!--
 * @Descripttion: 
 * @Author: Dyl
 * @Date: 2021-10-20 09:24:12
-->
<template>
  <div class="app-wrapper" :class="classObj">
    <navbar></navbar>
    <div class="main-container">
      <sidebar class="sidebar-container"></sidebar>
      <tags-view></tags-view>
      <app-main v-if="isRouterAlive"></app-main>
      <div class="recordNo">黑ICP备2020006065号</div>
    </div>
  </div>
</template>

<script>
import { Navbar, Sidebar, AppMain, TagsView } from './components'
import ResizeMixin from './mixin/ResizeHandler'
import ReconnectingWebSocket from "reconnecting-websocket";
import { mapGetters } from 'vuex';
import { getInfoByAdmin } from '@/api/login.js'

export default {
  name: 'layout',
  data(){
    return {
      isRouterAlive:true
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
  components: {
    Navbar,
    Sidebar,
    AppMain,
    TagsView
  },
  mixins: [ResizeMixin],
  computed: {
    sidebar() {
      return this.$store.state.app.sidebar
    },
    device() {
      return this.$store.state.app.device
    },
    classObj() {
      return {
        hideSidebar: !this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation,
        mobile: this.device === 'mobile'
      }
    },
    ...mapGetters([
      'userId'
    ])
  },
  created() {
    //this.webSocketinit();
  },
  methods: {
    // 消息ws处理
    webSocketinit() {
      let _this = this
      window.noticeWs = new ReconnectingWebSocket(`ws:${process.env.WS_BASE_API}/cc/${this.userId}`);
      noticeWs.onmessage = function (_msg) {
        var data = $.parseJSON(_msg.data); //通知事件的数据
        getInfoByAdmin(data.channelFrom).then((res)=>{
          let text = '';
          if(data.msgType == 1003){
            if(data.action == 0 ){
              text = '创建了新的工单并指派给您，请及时处理！' 
            }else if(data.action == 2){
              text = '拒收了您的工单派发，请重新操作派发工单！' 
            }else if(data.action == 4){
              text = '给您指派了工单，请及时处理！' 
            }else if(data.action == 5){
              text = '撤回了指派给您的工单，请知晓！' 
            }
          }else if(data.msgType == 1004){
            text = '催了您一下，请及时处理工单！'
          }
          _this.$notify({
            title: '您有一条新的待办消息',
            dangerouslyUseHTMLString: true,
            message: `<strong>${res.data.nickName}${text}</strong>`,
            duration: 0,
          });
          // 获取通知
          _this.$store.dispatch("GetNoticeList",{})
        })
        console.log(data,'有消息啦')
      };
    },
    reload(){
      this.isRouterAlive=false;
      this.$nextTick(()=>{
        this.isRouterAlive=true
      })
    }
  },
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/mixin.scss";
  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
  }
  .main-container{
    padding-top:70px;
    background: #F0F2F5;
  }
  .recordNo{
    font-size:12px;
    margin:70px auto 0;
    padding-bottom:30px;
    text-align:center;
    color:#666666;
  }
</style>
