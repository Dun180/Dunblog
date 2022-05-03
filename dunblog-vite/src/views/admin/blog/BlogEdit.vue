<template>
  <div class="page-title">
    <span>博客编辑</span>
  </div>
  <div class="page-content">
    <div class="blog-edit-container">
    <el-form
        :model="blogForm"
        :rules="rules"
        ref="blogFormRef"
        style="display: flex;flex-direction: column;height: 100%"
    >

      <el-form-item label="标题" style="margin-top: 20px">
        <el-input v-model="blogForm.title"></el-input>
      </el-form-item>
      <el-form-item label="分类">
        <el-select v-model="blogForm.categoryId" class="m-2" placeholder="Select" size="large">
          <el-option
              v-for="(item,index) in categoryList"
              :key="index"
              :label="item.name"
              :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item style="flex: 1">
        <v-md-editor
            height="100%"
            v-model="blogForm.content"
            @upload-image="handleUploadImage"
            :disabled-menus="[]"

        ></v-md-editor>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm(blogFormRef)">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
  </div>
</template>

<script setup lang="ts">
import {useRoute, useRouter} from "vue-router";
import {onMounted, reactive, ref} from "vue";
import {getBlogDetailById, uploadImg, blogEdit, getCategoryList} from "@/lib/api";
import {ElMessage,FormInstance} from "element-plus";
import axios from "axios";
import {CategoryInfo} from "@/models/category";

const blogFormRef = ref<FormInstance>()
let categoryList = ref([] as CategoryInfo[])

const blogForm = reactive({
  id:'',
  title: '',
  content: 'text content',
  categoryId: 1,
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
      //axios异步向后端请求数据验证
      const resp = await blogEdit(blogForm);
      if (resp.code == 200) {
        ElMessage({
          message: '提交成功',
          type: 'success',
        })
        await router.push("/admin/blog/list")

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
  let file = files[0]
  let param = new FormData()  // 创建form对象
  param.append('file', file)  // 通过append向form对象添加数据
  param.append('chunk', '0') // 添加form表单中其他数据
  let config = {
    headers: {'Content-Type': 'multipart/form-data'}
  }
  const resp = await uploadImg(param,config)

  //图片回显
  insertImage({
    url:
        axios.defaults.baseURL+'/viewphoto/'+resp.data,
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
      blogForm.categoryId = res.data.categoryId
    }
  }
  const resp = await getCategoryList();
  if (resp.code == 200) {
    categoryList.value = Object.values(resp.data)
  }
})

</script>

<style scoped lang="scss">
.blog-edit-container {
  height: 100%;
}
</style>
