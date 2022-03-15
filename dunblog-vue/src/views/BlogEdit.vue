<template>
  <div class="main">
    <div class="main-inner">
      <div class="content-wrap">
        <div class="content">
          <el-form :model="blogForm" :rules="rules" ref="blogForm" >

            <el-form-item label="标题" style="margin-top: 20px">
              <el-input v-model="blogForm.title"></el-input>
            </el-form-item>

            <el-form-item>
              <v-md-editor v-model="blogForm.content" height="600px"></v-md-editor>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="submitForm('blogForm')">提交</el-button>
              <el-button @click="resetForm('blogForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>

</template>

<script>



import {ElMessage} from "element-plus";

export default {
  name: "BlogEdit",
  data(){
    return{
      blogForm:{
        title:'',
        content:'text content'
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {

            console.log("submit")
            //axios异步向后端请求数据验证
            console.log(this.blogForm)
            const resp = await this.$api.blogEdit(this.blogForm);

            //console.log(response.data)
            if(resp){
              console.log('提交成功')
              ElMessage({
                message: '提交成功',
                type: 'success',
              })
              await this.$router.push("/backstage")

            }else{
              ElMessage.error('提交失败')
            }
          } else {
          ElMessage.error('提交失败')
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  }

}
</script>

<style scoped>
a {
  color: #555;
  text-decoration: none;
  outline: none;
  border-bottom: 1px solid #999;
  word-wrap: break-word;
}
a:hover{
  color: black;
}
</style>