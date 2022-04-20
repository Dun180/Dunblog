<template>
  <el-form
      :model="blogForm"
      :rules="rules"
      ref="blogFormRef"
  >

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
      <el-button type="primary" @click="submitForm(blogFormRef)">提交</el-button>
    </el-form-item>
  </el-form>

</template>

<script setup lang="ts">

import {useRoute, useRouter} from "vue-router";
import {onMounted, reactive, ref} from "vue";
import {getBlogDetailById, uploadImg,blogEdit} from "@/lib/api";
import {ElMessage,FormInstance} from "element-plus";
import axios from "axios";

const blogFormRef = ref<FormInstance>()


const blogForm = reactive({
  id:'',
  title: '',
  content: 'text content'
})

const rules = reactive(
    {
      title: [
        {required: true, message: '请输入标题', trigger: 'blur'},
        {min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur'}
      ]
    }
)

const router = useRouter()

const route = useRoute()
const blogId = ref(route.params.blogId)

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate(async (valid) => {
    if (valid) {
      console.log("submit")
      //axios异步向后端请求数据验证
      const resp = await blogEdit(blogForm);
      if (resp.code == 200) {
        console.log('提交成功')
        ElMessage({
          message: '提交成功',
          type: 'success',
        })
        await router.push("/backstage")

      }else {
      ElMessage.error('提交失败')
    }
    } else {
      ElMessage.error('提交失败')
      return false;
    }
  })
}

const handleUploadImage = async (event:any, insertImage:any, files:any) => {
  //拿到 files 之后上传到文件服务器，然后向编辑框中插入对应的内容
  console.log(files);
  let file = files[0]
  let param = new FormData()  // 创建form对象
  param.append('file', file)  // 通过append向form对象添加数据
  param.append('chunk', '0') // 添加form表单中其他数据
  console.log(param.get('file')) // FormData私有类对象，访问不到，可以通过get判断值是否传进去
  let config = {
    headers: {'Content-Type': 'multipart/form-data'}
  }
  const resp = await uploadImg(param,config)

  //图片回显
  insertImage({
    url:
        axios.defaults.baseURL+'/viewphoto/'+resp,
    desc: '图片',
    width: 'auto',
    height: 'auto',
  });

}

onMounted(async () => {
  if(blogId.value){
    blogForm.id = blogId.value as string
    const res = await getBlogDetailById(Number(blogId.value))
    if (res.code == 200) {
      blogForm.title = res.data.title
      blogForm.content = res.data.content

    }
  }
})

</script>

<style scoped>

</style>
