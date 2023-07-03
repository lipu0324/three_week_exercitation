<template>
  <div>
    <div class="mainContainer">
      <div class="login-form-layout">
        <div class="mainBoxTitle">哈尔滨医科大学附属肿瘤医院智能客服系统</div>
        <el-form autoComplete="on"
                :model="loginForm"
                :rules="loginRules"
                ref="loginForm"
                label-width="60px"
                :hide-required-asterisk="true">
          <el-form-item prop="username" label="用户名">
            <el-input name="username"
                      type="text"
                      v-model="loginForm.username"
                      autoComplete="on"
                      placeholder="请输入用户名">
            </el-input>
          </el-form-item>
          <el-form-item prop="password" label="密码">
            <el-input name="password"
                      :type="pwdType"
                      @keyup.enter.native="handleLogin"
                      v-model="loginForm.password"
                      autoComplete="on"
                      placeholder="请输入密码">
              <span slot="suffix" @click="showPwd">
                <svg-icon icon-class="eye" class="color-main"></svg-icon>
              </span>
            </el-input>
          </el-form-item>
          <el-form-item style="margin:40px 0 30px;text-align: center" label-width="0px">
            <el-button style="width: 45%" type="primary" round :loading="loading" @click.native.prevent="handleLogin">
              登录
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="bgBox">
      <div class="topBg"></div>
      <div class="bottomBg"></div>
    </div>
    <el-dialog
      title="注册"
      :model="admin"
      :visible.sync="dialogVisible"
      :show-close="false"
      :center="false"
      width="30%">
      <div style="text-align: center">
        <!-- <span class="font-title-large"><span class="color-main font-extra-large">关注公众号</span>回复<span class="color-main font-extra-large">体验</span>获取体验账号</span> -->
         <!-- <el-dialog
      :title="isEdit?'编辑用户':'添加用户'"
      :visible.sync="dialogVisible"
      width="40%"> -->
      <el-form :model="admin"
               ref="adminForm"
               label-width="150px" size="small">
        <el-form-item label="昵称：">
          <el-input v-model="admin.username" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="用户名：">
          <el-input v-model="admin.nickName" style="width:  90%"></el-input>
        </el-form-item>
        <el-form-item label="邮箱：">
          <el-input v-model="admin.email" style="width:  90%"></el-input>
        </el-form-item>
        <el-form-item label="密码：">
          <el-input v-model="admin.password"  type="password" style="width:  90%"></el-input>
        </el-form-item>
        <!-- <el-form-item label="备注：">
          <el-input v-model="admin.note"
                    type="textarea"
                    :rows="5"
                    style="width: 250px"></el-input>
        </el-form-item> -->
        <el-form-item label="是否启用：">
          <el-radio-group v-model="admin.status">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false"  size="small">取 消</el-button>
        <el-button type="primary" @click="handleDialogConfirm('ruleForm')" size="small">确 定</el-button>
      </span>
    <!-- </el-dialog> -->
        <!-- <br>
        <img src="http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/banner/qrcode_for_macrozheng_258.jpg" width="160" height="160" style="margin-top: 10px"> -->
      </div>
      <!-- <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogConfirm">确定</el-button>
      </span> -->
    </el-dialog>
  </div>
</template>

<script>
  import {isvalidUsername} from '@/utils/validate';
  import {setSupport,getSupport,setCookie,getCookie} from '@/utils/support';
  import login_center_bg from '@/assets/images/login_center_bg.png';
  import { addList } from '@/api/login';
  import {createAdmin} from '@/api/login';
   const defaultAdmin = {
    id: null,
    username: null,
    password: null,
    nickName: null,
    email: null,
    // note: null,
    status: 1
  };
  export default {
    name: 'login',
    data() {
      const validateUsername = (rule, value, callback) => {
        if (!isvalidUsername(value)) {
          callback(new Error('请输入正确的用户名'))
        } else {
          callback()
        }
      };
      const validatePass = (rule, value, callback) => {
        if (value.length < 3) {
          callback(new Error('密码不能小于3位'))
        } else {
          callback()
        }
      };
      return {
        admin: Object.assign({}, defaultAdmin),
        ruleForm: {
          username: '',
        password:'',
        nickName:'',
        email: '',
        status: 1
        },
        // admin: Object.assign({}, defaultAdmin),
        loginForm: {
          username: '',
          password: '',
        },
        loginRules: {
          username: [{required: true, trigger: 'blur', validator: validateUsername}],
          password: [{required: true, trigger: 'blur', validator: validatePass}]
        },
        loading: false,
        pwdType: 'password',
        login_center_bg,
        dialogVisible:false,
        supportDialogVisible:false
      }
    },
    created() {
      this.loginForm.username = getCookie("username");
      this.loginForm.password = getCookie("password");
      if(this.loginForm.username === undefined||this.loginForm.username==null||this.loginForm.username===''){
        this.loginForm.username = 'admin';
      }
      if(this.loginForm.password === undefined||this.loginForm.password==null){
        this.loginForm.password = '';
      }
    },
    methods: {
      showPwd() {
        if (this.pwdType === 'password') {
          this.pwdType = ''
        } else {
          this.pwdType = 'password'
        }
      },
       handleDialogConfirm(formName){
         addList({ ...this.ruleForm }).then((res) => {
        console.log(res)
      })
        this.$confirm('是否要确认?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (this.isEdit) {
            updateAdmin(this.admin.id,this.admin).then(response => {
              this.$message({
                message: '修改成功！',
                type: 'success'
              });
              this.dialogVisible =false;
              this.getList();
            })
          } else {
            createAdmin(this.admin).then(response => {
              this.$message({
                message: '添加成功！',
                type: 'success'
              });
              this.dialogVisible =false;
              this.getList();
            })
          }
        })
      },
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            // let isSupport = getSupport();
            // if(isSupport===undefined||isSupport==null){
            //   this.dialogVisible =true;
            //   return;
            // }
            this.loading = true;
            this.$store.dispatch('Login', this.loginForm).then(() => {
              this.loading = false;
              setCookie("username",this.loginForm.username,15);
              setCookie("password",this.loginForm.password,15);
              this.$router.push({path: '/',query:{reload:1}})
            }).catch(() => {
              this.loading = false
            })
          } else {
            console.log('参数验证不合法！');
            return false
          }
        })
      },
      handleTry(){
        this.dialogVisible =true
      },
      dialogConfirm(){
        this.dialogVisible =false;
        setSupport(true);
      },
      dialogCancel(){
        this.dialogVisible = false;
        setSupport(false);
      }
    }
  }
</script>

<style lang="scss" scoped>
  .bgBox{
    position:fixed;
    top: 0;
    left: 0;
    z-index: 0;
    width: 100vw;
    height: 100vh;
    .topBg{
      position: relative;
      width:100vw;
      height:32.6vw;
      background:url('../../assets/images/loginTopBg.png') no-repeat;
      background-size: cover;
      max-height:65vh;
    }
    .bottomBg{
      position: absolute;
      bottom:0;
      left: 0;
      width:100vw;
      height:56.25vw;
      background:url('../../assets/images/loginBottombg.png') no-repeat;
      background-size: 100% 100%;
      max-height:100vh;
      z-index: -1;
    }
  }
  .mainContainer{
    width: 100vw;
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    z-index: 2;
  }
  .login-form-layout {
    position: relative;
    width: 450px;
    background: linear-gradient(#DBF1FF,#FCFEFF);
    border-radius: 16px;
    padding: 70px 70px 0;
    box-shadow: 0px 0px 4px rgba(29,34,41,0.15);
    .mainBoxTitle{
      width: 390px;
      height: 52.88px;
      background:url('../../assets/images/loginBoxTop.png') no-repeat;
      background-size: 100% 100%;
      position: absolute;
      top:-14px;
      left:30px;
      font-size:16px;
      font-weight:600;
      color:#FFFFFF;
      line-height:45px;
      text-align:center;
    }
    /deep/ .el-form-item__label{
      color: #585858;
      font-size: 14px;
      font-weight: 600;

    }
    /deep/ .el-input__inner{
      border: 1.4px solid #61B7EE;
      font-size: 12px;
    }
  }

  .login-title {
    text-align: center;
  }

  .login-center-layout {
    background: #409EFF;
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
    margin-top: 200px;
  }
</style>
