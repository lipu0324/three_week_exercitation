<template> 
  <div>
    <el-upload
      :action="useOss?ossUploadUrl:minioUploadUrl"
      list-type="picture"
      :multiple="false"
      :before-upload="beforeUpload"
      :on-remove="handleRemove"
      :show-file-list="false"
      :on-preview="handlePreview">
      <el-button class="upload" v-if="!imageUrl" type="primary">上传</el-button>
      <img v-else :src="imageUrl" alt="" style="width:40px;height:40px;">
    </el-upload>
  </div>
</template>
<script>
  import {policy} from '@/api/oss'
  import { uploadFile } from '@/api/upload'
  import moment from 'moment';
  import {BASE_API}from '../../../config/dev.env';
  export default {
    name: 'tableUpload',
    props: {
      value: String
    },
    computed: {
      imageUrl() {
        const { value } = this
        return value ? this.baseapi+value : null
      },
      showFileList: {
        get: function () {
          return this.value !== null && this.value !== ''&& this.value!==undefined;
        },
        set: function (newValue) {
        }
      }
    },
    data() {
      return {
        baseapi:BASE_API.slice(1,-1)+'/',
        dialogVisible: false,
        useOss:true, //使用oss->true;使用MinIO->false
        ossUploadUrl:'http://macro-oss.oss-cn-shenzhen.aliyuncs.com',
        minioUploadUrl:'http://localhost:8080/minio/upload',
      };
    },
    methods: {
      emitInput(val) {
        this.$emit('input', val)
      },
      handleRemove(file) {
        this.emitInput('');
      },
      handlePreview(file) {
        this.dialogVisible = true;
      },
      beforeUpload(file) {
        let now = moment().locale('zh-cn').format('YYYYMMDD');
        let one = `product/${now}`
        uploadFile(one,file).then(res => {
          this.emitInput(res.data);
        })
        return false
      }
    }
  }
</script>
<style>
  .upload{
    margin-top: 24px;
    margin-left: -20px;
  }
</style>
