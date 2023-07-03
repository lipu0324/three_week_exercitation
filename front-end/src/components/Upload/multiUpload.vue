<template> 
  <div>
    <div v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading"
         style="width: 147px; height: 147px; float: left" v-show="isShow"></div>
    <el-upload
      :action="useOss?ossUploadUrl:minioUploadUrl"
      :data="useOss?dataObj:null"
      list-type="picture-card"
      :file-list="fileList"
      :before-upload="beforeUpload"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview"
      :limit="maxCount"
      :on-exceed="handleExceed"
      accept=".jpg,.jpeg,.png,.JPG,.JPEG"
    >
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>
<script>
  import {policy} from '@/api/oss'
  import { uploadFile } from '@/api/upload'
  import moment from 'moment';
  import {BASE_API}from '../../../config/dev.env';
  export default {
    name: 'multiUpload',
    props: {
      //图片属性数组
      value: Array,
      //最大上传图片数量
      maxCount:{
        type:Number,
        default:5
      }
    },
    data() {
      return {
        baseapi:BASE_API.slice(1,-1)+'/',
        dataObj: {
          policy: '',
          signature: '',
          key: '',
          ossaccessKeyId: '',
          dir: '',
          host: '',
          isShow:false, 
          loading: false,
        },
        dialogVisible: false,
        dialogImageUrl:null,
        useOss:true, //使用oss->true;使用MinIO->false
        ossUploadUrl:'http://macro-oss.oss-cn-shenzhen.aliyuncs.com',
        minioUploadUrl:'http://localhost:8080/minio/upload',
      };
    },
    computed: {
      fileList() {
        let fileList=[];
        for(let i=0;i<this.value.length;i++){
          fileList.push({url:this.value[i]});
        }
        return fileList;
      }
    },
    methods: {
      emitInput(fileList) {
        let value=[];
        for(let i=0;i<fileList.length;i++){
          value.push(fileList[i].url);
        }
        this.$emit('input', value)
      },
      handleRemove(file, fileList) {
        this.emitInput(fileList);
      },
      handlePreview(file) {
        this.dialogVisible = true;
        // console.log(file.url)
        this.dialogImageUrl=file.url;
      },
      // beforeUpload(file) {
      //   uploadFile(file).then(res => {
      //     console.log(res)
      //     this.fileList.push({
      //       name: file.name,
      //       // url: `http://47.98.224.222:8089${res.data}`
      //       url: `http://47.93.63.79:8089${res.data}`
      //     });
      //     console.log(this.fileList)
      //     this.emitInput(this.fileList);
      //   })
      //   return false
      // },

      // 上传文件之前的钩子，参数为上传的文件，若返回 false 或者返回 Promise 且被 reject，则停止上传。
      beforeUpload(file) {
        let now = moment().locale('zh-cn').format('YYYYMMDD');
        let one = `product/${now}`
        this.isShow = true
        this.loading = true;
        uploadFile(one,file).then(res => {
          this.fileList.push({
            name: file.name,
            url: this.baseapi+res.data
          });
          // console.log(this.fileList)
          this.emitInput(this.fileList);
          this.isShow = false
          this.loading = false
        })
        return false
      },

      handleUploadSuccess(res, file) {
        // let url = this.dataObj.host + '/' + this.dataObj.dir + '/' + file.name;
        // if(!this.useOss){
        //   //不使用oss直接获取图片路径
        //   url = res.data.url;
        // }
        // this.fileList.push({name: file.name,url:url});
        // this.emitInput(this.fileList);
      },
      handleExceed(files, fileList) {
        this.loading = false;
        this.isShow = false;
        this.$message({
          message: '最多只能上传'+this.maxCount+'张图片',
          type: 'warning',
          duration:1000
        });
      },
    }
  }
</script>
<style>

</style>


