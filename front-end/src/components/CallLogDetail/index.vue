<!--
 * @Author: Dyl
 * @Date: 2022-02-14 11:06:51
 * @LastEditTime: 2022-02-14 11:20:33
 * @LastEditors: Please set LastEditors
 * @Description: 通话记录详情
 * @FilePath: \sfcc\src\components\CallLogDetail\index.vue
-->
<template>
  <div>
    <el-dialog
      :visible.sync="dialogVisible"
      width="80%"
      @close="closeDialog">
      <div class="dialogBody">
        <el-descriptions class="margin-top" title="通话记录详情" :column="3" border>
          <el-descriptions-item>
            <template slot="label">
              记录ID
            </template>
            {{dialogData.id}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              呼叫标识
            </template>
            {{dialogData.ctiCallId}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              主叫号码
            </template>
            {{tel(dialogData.callerIdNumber)}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              原主叫号码
            </template>
            {{tel(dialogData.originalAni)}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              被叫号码
            </template>
            {{tel(dialogData.destinationNumber)}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              原被叫号码
            </template>
            {{tel(dialogData.originalDnis)}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              呼叫方向
            </template>
            {{dialogData.ctiCallType == 1 ? '内部呼叫' : ( dialogData.ctiCallType == 2 ? '呼入' : '呼出')}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              呼叫属性
            </template>
            {{dialogData.channelAttr == 1 | calldirection}}
          </el-descriptions-item> 
          <el-descriptions-item>
            <template slot="label">
              呼叫结果
            </template>
            {{dialogData.hangupCause | getCallStatus}}
          </el-descriptions-item> 
          <el-descriptions-item>
            <template slot="label">
              开始时间
            </template>
            {{dialogData.startStamp}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              应答时间
            </template>
            {{dialogData.answerStamp}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              结束时间
            </template>
            {{dialogData.endStamp}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              通话时长/分
            </template>
            {{dialogData.billsec}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              满意度调查按键
            </template>
            {{dialogData.ivrName}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              满意度调查结果
            </template>
            {{dialogData.ivrDtmfDigit}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              IVR ID
            </template>
            {{dialogData.ivrID}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              挂断编码
            </template>
            {{dialogData.ituCode}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              Sip编码
            </template>
            {{dialogData.sipCode}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              挂机原因
            </template>
            {{dialogData.enumeration}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              通话录音
            </template>
            <audio :src="`http://${msg_host}:${sound_url}${dialogData.filename}`" controls="controls"></audio>
          </el-descriptions-item>
        </el-descriptions>
        <el-descriptions class="margin-top" v-if="soundData" title="录音信息"  :column="3" border style="margin-top:20px">
          <el-descriptions-item>
            <template slot="label">
              录音ID
            </template>
            {{soundData.id}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              呼叫标识
            </template>
            {{soundData.callId}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              联系号码
            </template>
            {{soundData.mobile}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              联络座席
            </template>
            {{soundData.agent}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              呼叫方向
            </template>
            {{soundData.ctiCallType == 1 ? '内部呼叫' : ( soundData.ctiCallType == 2 ? '呼入' : '呼出')}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              录音时长/分
            </template>
            {{Math.floor((soundData.duration/60) * 100) / 100}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              创建时间
            </template>
            {{soundData.createTime}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              自动外呼ID
            </template>
            {{soundData.campaignId}}
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name:"CallLogDetail",
  data() {
    return {
      dialogVisible:true,
      msg_host:msg_host,
      sound_url:sound_url,
    }
  },
  props:{
    soundData:{
      default:null
    },
    dialogData:{
      default:null
    },
  },
    filters: {
      getCallStatus(code){
        switch(code){
          case 'send_bye' :
            return '呼叫成功(主叫挂断)'
          break;
          case 'recv_bye' :
            return '呼叫成功(被叫挂断)'
          break;
          case 'call_failed' :
            return '呼叫失败'
          break;
          case 'CALL_REJECTED' :
            return '被叫拒接'
          break;
          case 'NO_ANSWER' :
            return '无应答'
          break;
          case 'NO_USER_RESPONSE' :
            return '被叫无响应'
          break;
          case 'USER_BUSY' :
            return '用户忙'
          break;
          case 'UNALLOCATED_NUMBER' :
            return '空号'
          break;
          case 'NORMAL_RELEASE' :
            return '正常释放'
          break;
          case 'NORMAL_CLEARING' :
            return '双方都由运营商挂断'
          break;
          case 'NORMAL_TEMPORARY_FAILURE' :
            return '呼叫线路超时'
          break;
          case 'TIMEOUT' :
            return 'SIP超时'
          break;
          case 'NO_ROUTE_DESTINATION' :
            return '呼叫不可达（空号/无法路由）'
          break;
          case 'USER_NOT_REGISTERED' :
            return '网关/电话卡未注册'
          break;
          case 'NORMAL_UNSPECIFIED' :
            return '线路不通, 可能盲区'
          break;
          case 'RECOVERY_ON_TIMER_EXPIRE' :
            return '媒体超时, 异常'
          break;
          default:
            return '呼叫成功'
          break
        }
      },
      calldirection(code){
        switch(code){
          case 'transfer' :
            return '转接呼叫'
          break;
          case 'conference' :
            return '三方呼叫'
          break;
          case 'monitor' :
            return '监听'
          break;
          case 'makeCall' :
            return '接口外呼'
          break;
          case 'holdCall' :
            return '保持外呼'
          break;
          case 'forward' :
            return '呼叫转移'
          break;
          case 'transferExternal' :
            return '分机呼叫转移'
          break;
          default:
            return '转接呼叫'
          break;
        }
      }
    },
  methods: {
    tel(tel){
        return tel.substring(0, 3)+"****"+tel.substr(tel.length-4);
      },
    closeDialog(){
      this.$emit("close")
    }
  },
}
</script>
<style lang="scss" scoped>
  .dialogBody{
    height:60vh;
    overflow-y:auto;
  }
</style>