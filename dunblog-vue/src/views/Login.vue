<template>
  <div>
    <el-container>
      <el-header><img class="mlogo" src="girl.png" alt=""></el-header>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="account">
            <el-input v-model="ruleForm.account"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>


          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        account: 'dun',
        password: '111'
      },
      rules: {
        account: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' }
        ]
      }
    };
  },
  methods: {
    async submitForm(formName) {
      this.$refs[formName].validate(async valid => {
        if (valid) {
          //axios异步向后端请求数据验证
          console.log("logining....")
          const response = await this.$api.login(this.ruleForm);
            console.log(response.data)
            //从后端传回来的数据中拿到jwt和用户的数据
            const jwt = response.headers['authorization']
            const userInfo = response.data.data

            //将jwt和用户数据放到全局中共享
            this.$store.commit("SET_TOKEN",jwt)
            this.$store.commit("SET_USERINFO",userInfo)

            //通过router跳转页面
            if(userInfo!=null){
              ElMessage({
                message: '登录成功',
                type: 'success',
              })
              this.$router.push("/backstage")
            } else {
              this.$router.push("/login")
            }



        } else {
          console.log('error submit!!');
          ElMessage({
            message: '登录失败',
            type: 'error',
          })
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  /*background-color: #E9EEF3;*/
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.mlogo {
  height: 60%;
  margin-top: 10px;
}

.demo-ruleForm {
  max-width: 500px;
  margin: 0 auto;
}
</style>