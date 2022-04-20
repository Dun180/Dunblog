<template>
  <div>
    <el-container>
      <el-header><img class="mlogo" src="girl.png" alt=""></el-header>
      <el-main>
        <el-form
            :model="ruleForm"
            :rules="rules"
            ref="ruleFormRef"
            label-width="100px"
            class="demo-ruleForm"
        >
          <el-form-item label="用户名" prop="account">
            <el-input v-model="ruleForm.account"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>


          <el-form-item>
            <el-button type="primary" @click="submitForm(ruleFormRef)">登录</el-button>
            <el-button @click="resetForm(ruleFormRef)">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import {ElMessage } from "element-plus";
import {reactive, ref} from "vue";
import type { FormInstance } from 'element-plus'
import { useStore } from '@/store/index'
import { useRouter } from 'vue-router'
import {login} from "@/lib/api";

const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive({
  account: 'dun',
  password: '111'
})
const store = useStore()
const router = useRouter()

const rules = reactive({
  account: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
  ],
      password: [
    { required: true, message: '请输入密码', trigger: 'change' }
  ]
})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate(async (valid) => {
    if (valid) {
      //axios异步向后端请求数据验证
      console.log("logining....")
      const res = await login(ruleForm);
      console.log(res)
      //从后端传回来的数据中拿到jwt和用户的数据
      const jwt = res.headers['authorization']
      const userInfo = res.data.data

      //将jwt和用户数据放到全局中共享
      store.SET_TOKEN(jwt)
      store.SET_USERINFO(userInfo)

      //通过router跳转页面

      if (userInfo != null) {
        ElMessage({
          message: '登录成功',
          type: 'success',
        })
        await router.push("/backstage")
      } else {
        await router.push("/login")
        ElMessage({
          message: '登录失败',
          type: 'error',
        })
      }

    } else {
      console.log('error submit!!');
      ElMessage({
        message: '登录失败',
        type: 'error',
      })
      return false;
    }
  })
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}

</script>

<style scoped>
.el-header {

  color: #333;
  text-align: center;
  line-height: 60px;
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
