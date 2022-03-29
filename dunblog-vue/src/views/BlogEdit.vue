<template>

          <el-form :model="blogForm" :rules="rules" ref="blogForm">

            <el-form-item label="标题" style="margin-top: 20px">
              <el-input v-model="blogForm.title"></el-input>
            </el-form-item>

            <el-form-item>
              <v-md-editor
                  v-model="blogForm.content"
                  height="600px"
                  @upload-image="handleUploadImage"
                  :disabled-menus="[]"

              ></v-md-editor>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="submitForm('blogForm')">提交</el-button>
            </el-form-item>
          </el-form>


</template>

<script>


import {ElMessage} from "element-plus";

export default {
  name: "BlogEdit",
  data() {
    return {
      blogForm: {
        id:'',
        title: '',
        content: 'text content'
      },
      rules: {
        title: [
          {required: true, message: '请输入标题', trigger: 'blur'},
          {min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {

          console.log("submit")
          //axios异步向后端请求数据验证


          const resp = await this.$api.blogEdit(this.blogForm);

          if (resp) {
            console.log('提交成功')
            ElMessage({
              message: '提交成功',
              type: 'success',
            })
            await this.$router.push("/backstage")

          } else {
            ElMessage.error('提交失败')
          }
        } else {
          ElMessage.error('提交失败')
          return false;
        }
      });
    },

    async handleUploadImage(event, insertImage, files) {
      // 拿到 files 之后上传到文件服务器，然后向编辑框中插入对应的内容
      console.log(files);
      let file = files[0]
      let param = new FormData()  // 创建form对象
      param.append('file', file)  // 通过append向form对象添加数据
      param.append('chunk', '0') // 添加form表单中其他数据
      console.log(param.get('file')) // FormData私有类对象，访问不到，可以通过get判断值是否传进去
      let config = {
        headers: {'Content-Type': 'multipart/form-data'}
      }
      const resp = await this.$api.uploadImg(param,config)

      //图片回显
      insertImage({
        url:
            this.$api.server_url+'/viewphoto/'+resp,
        desc: '图片',
        width: 'auto',
        height: 'auto',
      });

    },
  },
  async created() {
    const blogId = this.$route.params.blogId

    if(blogId){

      this.blogForm.id = blogId
      const resp = await this.$api.getBlogDetailById(blogId)

      this.blogForm.title = resp.title
      this.blogForm.content = resp.content

    }
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

a:hover {
  color: black;
}
</style>