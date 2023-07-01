<!--
 * @Descripttion: 档案详情
 * @Author: Dyl
 * @Date: 2021-11-12 15:43:52
-->
<template>
  <div class="archivesDetail_container">
    
    <el-row class="detailInfoBox" v-if="Object.keys(detailData).length > 0">
      <el-col :span="16">
        <div class="archiveName">{{detailData.name}}</div>
        <div class="tagsBox">
          <el-tag effect="plain" v-for="(item,index) in detailData.tags" :key="index" style="margin-right:10px">{{item}}</el-tag>
        </div>
        <div class="callLogBox">
          <div class="callLogTitle">来电历史：{{detailData.calls.length}}次</div>
          <div v-for="item in detailData.calls" class="historyBox" @click="callHandle(item.phone)">
            <div class="telBox">{{item.phone}}</div>
            <div class="contactName">联系人姓名：{{item.username}}</div>
          </div>
        </div>
        </el-col>
        <el-col :span="8">
          <div class="buttonBox">
              <el-button @click="creatOrder" size="mini" style="background:#EC6941;color:#fff">创建工单</el-button>
              <el-button type="primary" @click="mergeArchives" size="mini">合并档案</el-button>
          </div>
          <el-descriptions :column="1" class="archiveInfoBox" :labelStyle="{fontSize:'14px',fontWeight:'bold',color:'#666666',whiteSpace:'noWrap'}" :contentStyle="{fontSize:'14px',fontWeight:'bold',color:'#666666'}">
            <el-descriptions-item label="创建时间">{{detailData.createdTime}}</el-descriptions-item>
            <el-descriptions-item label="档案编号">{{detailData.code}}</el-descriptions-item>
            <el-descriptions-item label="邮箱">{{detailData.email}}</el-descriptions-item>
            <el-descriptions-item label="部门">{{detailData.deptName}}</el-descriptions-item>
            <el-descriptions-item label="公司地址">{{detailData.address}}</el-descriptions-item>
            <el-descriptions-item label="备注" :labelStyle="{fontSize:'14px',fontWeight:'bold',color:'#ED687D'}" :contentStyle="{fontSize:'14px',fontWeight:'bold',color:'#ED687D'}">{{detailData.remark}}</el-descriptions-item>
          </el-descriptions>
        </el-col>
      </el-row>
    <div class="historyDataBox" v-if="Object.keys(detailData).length > 0">
      <div class="historyTitle">
        历史档案
      </div>
      <div class="historyBlock">
        <el-timeline>
          <el-timeline-item v-for="(item,index) in detailData.contents" icon="el-icon-time" color="#0177D5" :key="index" :timestamp="'创建 '+item.createdTime" placement="top">
            <el-card>
              <p class="historyText">{{item.content}}</p>
              <div class="historyInfoBox">
                <div class="tagsBox">
                  <el-tag v-for="(tags,ind) in item.labels" size="mini" :key="ind" type="info">{{tags}}</el-tag>
                </div>
                <p>紧急程度：{{item.urgent}}</p>
                <p>处理座席：{{item.sitDown}}</p>
              </div>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>
    </div>
    <search-dialog v-if="showSearchDialog" @submit="chooseHandle" @close="closeSearchDialog" type="archives" title="合并档案"/>
  </div>
</template>
<script>
import { getArchiveDetail, addArchiveContent, mergeArc , delArchive } from "@/api/archives"
import SearchDialog from '@/components/SearchDialog/index.vue';
import { addMember, mergeMember } from "@/api/member.js"
import Api from '@/assets/js/api.js';
import { mapGetters } from 'vuex'
export default {
  data(){
    return {
      detailData:{
      },
      showSearchDialog:false
    }
  },
  components:{
    SearchDialog
  },
  computed: {
    ...mapGetters([
      'signStatus'
    ])
  },
  created() {
    this.getDetail()
  },
  methods: {
    getDetail(){
      getArchiveDetail({
        id:this.$route.query.id
      }).then((res)=>{
        let data = res.data;
        let tags = [];
        data['tags'] = [];
        data.contents.map((item)=>{
          item['labels'] = !item.labels || item.labels == '' ? [] : item.labels.split(",")
          item['urgent'] = item.urgency == 0 ? '普通' : (item.urgency == 1 ? '紧急' : '非常紧急')
          tags = tags.concat(item.labels)
        })
        for(let i=0;i<tags.length;i++){
          if(tags[i] != '' && tags.indexOf(tags[i]) === i){
            data['tags'].push(tags[i])
          }
        }
        this.detailData = data;
      }).catch(()=>{

      })
    },
    callHandle(phoneNumber){
      if(this.signStatus == 'in'){
        ws.send(JSON.stringify(Api.makeCallData(phoneNumber)));
      }else{
        this.$message.error('当前座席状态为签出，无法呼叫');
      }
    },
    creatOrder(){
      this.$router.push({
        path:`/workorder/workOrderList?creat=1&archiveId=${this.$route.query.id}`
      })
    },
    // 合并档案 展示搜索弹窗
    mergeArchives(){
      this.showSearchDialog = true
    },
    // 关闭搜索弹窗
    closeSearchDialog(){
      this.showSearchDialog = false
    },
    // 搜索弹窗选择完毕
    chooseHandle(data){
      Promise.all([
        new Promise((resolve,reject)=>{
          if(data.calls.length > 0){
            data.calls.map((item)=>{
              item['archivesId'] = this.$route.query.id
            })
            mergeMember(data.calls).then(()=>{
              resolve()
            }).catch(()=>{
              reject()
            })
          }else{
            resolve()
          }
        }),
        new Promise((resolve,reject)=>{
          if(data.contents.length > 0){
            data.contents.map((item)=>{
              item['archivesId'] = this.$route.query.id
            })
            mergeArc(data.contents).then(()=>{
              resolve()
            }).catch(()=>{
              reject()
            })
          }else{
            resolve()
          }
        })
      ]).then(()=>{
        this.getDetail()
        this.showSearchDialog = false
        this.$message({
          message: '合并档案成功',
          type: 'success'
        });
        // 删除选择合并的档案
        delArchive({
          id:data.id
        }).then(()=>{

        })
      }).catch(()=>{
        this.getDetail()
        this.showSearchDialog = false
      })
    }
  },
}
</script>
<style lang="scss" scoped>
  .archivesDetail_container{
    position:relative;
    padding: 10px;
    background:#F0F2F5;
    .detailInfoBox{
      background:#fff;
      padding:15px;
    }
    .buttonBox{
      position:absolute;
      top:20px;
      right:20px;
      width:200px;
      display:flex;
      align-items:center;
      justify-content:space-between;
    }
    .archiveName{
      font-size:16px;
      font-weight:bold;
      color:#262626;
    }
    .tagsBox{
      margin: 10px 0;
    }
    .callLogBox{
      width:100%;
      margin-bottom: 20px;
      padding:20px 25px;
      box-sizing:border-box;
      border-radius:4px;
      background:#FFE8DF;
      .callLogTitle{
        font-size:16px;
        font-weight:bold;
        color:#EC6941;
        margin-bottom:20px;
      }
      .historyBox{
        display:flex;
        align-items:center;
        justify-content:flex-start;
        margin:15px 0;
        cursor:pointer;
        i{
          font-size:18px;
          color: #333333;
        }
        .telBox{
          color:#EC6941;
          font-size:18px;
          line-height:30px;
          font-weight:bold;
        }
        .contactName{
          font-size:14px;
          color:#666666;
          margin-left:15px;
        }
      }
    }
    .archiveInfoBox{
      margin:70px 0 0 20px;
      span{
        font-size:12px;
        font-weight:bold;
        color:#666666;
      }
    }
    .historyDataBox{
      background:#fff;
      .historyTitle{
        width:100%;
        background:#E3F2FE;
        line-height: 40px;
        height:40px;
        font-weight:bold;
        font-size:13px;
        color:#0177D5;
        padding-left:30px;
      }
      .historyBlock{
        padding-left:30px;
        width:95%;
        ul{
          margin:20px 0;
          padding-left:0;
        }
        /deep/ .el-timeline-item__timestamp{
          color:#0177D5;
          font-weight:bold;
        }
        /deep/ .el-timeline-item__tail{
          border-left: 1px dashed #0177D5;
        }
        .historyText{
          line-height:24px;
          font-size:13px;
          color:#666666;
          margin-bottom:20px;
        }
        .historyInfoBox{
          display:flex;
          align-items:center;
          justify-content:flex-start;
          .tagsBox{
            margin-right:30px;
            .el-tag{
              margin-right:10px;
            }
          }
          p{
            margin-right:30px;
            font-size:12px;
            color:#666666;
            font-weight:bold;
          }
        }
      }
    }
  }
</style>