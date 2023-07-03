<!--
 * @Descripttion: 修改企业信息
 * @Author: Dyl
 * @Date: 2021-11-10 15:55:50
-->
<template>
  <el-card class="enterprise-container">
    <el-form size="small" label-width="140px">
      <el-form-item label="系统名称：">
        <el-input v-model="name" maxlength="15" class="input-width" placeholder="系统名称" clearable></el-input>
      </el-form-item>
      <el-form-item label="系统logo：">
        <el-upload
          class="bank-cover-uploader"
          :action="fileAdress"
          :show-file-list="false"
          :headers = "headerConfig"
          :on-success="handleImport"
        >
          <img v-if="logo" :src="getIcon(logo)" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>  
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="submitSysInfo()"
          size="small">
          保存
        </el-button>
        <el-button
          @click="getSysInfo()"
          size="small">
          取消
        </el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
  import { getToken  } from '@/utils/auth'
  import {getInfo,addInfo,editInfo} from '@/api/system.js'
  export default {
    data(){
      return{
        logo: "",
        name: "",
        fileAdress:`${process.env.IMAGE_API}/file/single?dir=sfcc`,
        headerConfig: {
          Authorization: 'Bearer ' + getToken()
        },
        id:null
      }
    },
    created() {
      this.getSysInfo()
    },
    methods: {
      getSysInfo(){
        getInfo({
          pageSize:1,
          pageNum:1
        }).then((res)=>{
          if(res.data.list.length > 0){
            let data = res.data.list[0];
            this.logo = data.enterpriseLogo;
            this.name = data.enterpriseName;
            this.id = data.id
          }
        })
      },
      submitSysInfo(){
        if(this.name == '' || this.logo == ''){
          this.$message.error('请完整填写企业信息');
          return false;
        }
        if(this.id){
          editInfo({
            id:this.id,
            enterpriseLogo:this.logo,
            enterpriseName:this.name
          }).then((res)=>{
            this.$message({
              message: '修改成功',
              type: 'success'
            });
            this.$store.dispatch("SetSystemInfo",{
              name: this.name,
              logo: this.logo
            })
          })
        }else{
          addInfo({
            enterpriseLogo:this.logo,
            enterpriseName:this.name
          }).then((res)=>{
            this.$message({
              message: '保存成功',
              type: 'success'
            });
            this.$store.dispatch("SetSystemInfo",{
              name: this.name,
              logo: this.logo
            })
          })
        }
        
      },
      // 上传
      handleImport(e) {
        console.log(e,'-------------')
        this.logo = e.data;
      },
      getIcon(logo){
        return process.env.IMAGE_API+logo
      },
    },
  }
</script>
<style lang="scss" scoped>
  .enterprise-container{
    padding:0 10vw 10vh 0;
    width:100%;
    height:calc(100vh - 84px);
    display:flex;
    align-items:center;
    justify-content:center;
    /deep/ .bank-cover-uploader .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    /deep/ .bank-cover-uploader .el-upload:hover {
      border-color: #409EFF;
    }
    /deep/ .avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 120px;
      height: 120px;
      line-height: 120px;
      text-align: center;
    }
    .avatar {
      width: 120px;
      height: 120px;
      display: block;
    }
  }
</style>