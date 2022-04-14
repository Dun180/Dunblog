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

<script setup lang="ts">
import {ElMessage} from "element-plus";
import {reactive, ref} from "vue";
import type { FormInstance } from 'element-plus'

const ruleForm = reactive({
  account: 'dun',
  password: '111'
})

const rules = reactive({
  account: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
  ],
      password: [
    { required: true, message: '请输入密码', trigger: 'change' }
  ]
})

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      console.log('submit!')
    } else {
      console.log('error submit!')
      return false
    }
  })
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
const submitForm = async (formName) => {
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
</script>

<style scoped>

</style>