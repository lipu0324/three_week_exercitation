<template>
  <div>
    <el-button type="text"
               @click="dialogFormVisible = true">添加</el-button>
    <el-dialog title="常见问题"
               :visible.sync="dialogFormVisible"
               center>
      <el-form :model="form">
        <el-form-item label="常见问题类型"
                      :label-width="formLabelWidth">
          <el-input v-model="form.name"
                    autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="图标"
                      :label-width="formLabelWidth">
          <el-upload action="https://jsonplaceholder.typicode.com/posts/"
                     list-type="picture-card"
                     :on-preview="handlePictureCardPreview"
                     :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%"
                 :src="dialogImageUrl"
                 alt="">
          </el-dialog>
        </el-form-item>

      </el-form>

      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary"
                   @click="handleDialogConfirm()">确 定</el-button>
      </div>

    </el-dialog>
  </div>

</template>

<script>
import { createRole, updateRole } from '@/api/role';
import { formatDate } from '@/utils/date';

export default {
  data () {
    return {
      dialogFormVisible: false,
      dialogImageUrl: '',
      dialogVisible: false,
      form: {
        name: '',
        delivery: false,
        type: [],
      },
      formLabelWidth: '120px'
    };
  },

  methods: {
    handleRemove (file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },

    handleDialogConfirm () {
      this.$confirm('是否要确认?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.isEdit) {
          updateRole(this.role.id, this.role).then(response => {
            this.$message({
              message: '修改成功！',
              type: 'success'
            });
            this.dialogVisible = false;
            this.getList();
          })
        } else {
          createRole(this.role).then(response => {
            this.$message({
              message: '添加成功！',
              type: 'success'
            });
            this.dialogVisible = false;
            this.getList();
          })
        }
      })
    }
  }

}
</script>
<style>
</style>
