<template>
  <div class="upload-container">
    <el-button icon='el-icon-upload' size="mini" :style="{background:color,borderColor:color}"
               @click=" dialogVisible=true" type="primary">上传图片
    </el-button>
    <el-dialog append-to-body :visible.sync="dialogVisible">
      <el-upload class="editor-slide-upload"
                 :action='baseapi + "file/uploadFile?dir=product/20200827"'
                 :multiple="true"
                 :show-file-list="true"
                 :file-list="elFileList"
                 :headers="header"
                 list-type="picture-card"
                 :on-remove="handleRemove"
                 :on-success="handleSuccess"
                 :before-upload="beforeUpload">
        <el-button size="small" type="primary">点击上传</el-button>
      </el-upload>
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="handleSubmit">确 定</el-button>
    </el-dialog>
  </div>
</template>

<script>
  import {policy} from '@/api/oss'
  import { getToken } from '@/utils/auth'
  import {BASE_API}from '../../../../config/dev.env';

  export default {
    name: 'editorSlideUpload',
    props: {
      color: {
        type: String,
        default: '#1890ff'
      }
    },
    data() {
      return {
        dialogVisible: false,
        listObj: {},
        fileList: [],
        elFileList: [],
        dataObj: {
        },
        header: {
          "Authorization": getToken()
        },
        baseapi:BASE_API.slice(1,-1)+'/',
      }
    },
    methods: {
      handleSubmit() {
        this.$emit('successCBK', this.fileList);
        this.listObj = {};
        this.fileList = [];
        this.elFileList = [];
        this.dialogVisible = false;
      },
      handleSuccess(response, file) {
        this.fileList.push(response.data);
      },
      handleRemove(file) {
        console.log(file);
      },
      beforeUpload(file) {
        console.info("这里什么都不用做")
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .upload-container .editor-slide-upload{
    margin-bottom: 20px;
  }
</style>
