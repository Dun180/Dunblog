<template>
  <div class="page-title">
    <span>日记编辑</span>
  </div>
  <div class="page-content">
    <div class="diary-edit-container">
      <el-form
          :model="diaryForm"
          :rules="rules"
          ref="diaryFormRef"
          style="display: flex;flex-direction: column;height: 100%"
      >

        <el-form-item style="flex: 1">
          <v-md-editor
              height="100%"
              v-model="diaryForm.content"
              @upload-image="handleUploadImage"
              :disabled-menus="[]"
          ></v-md-editor>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm(diaryFormRef)">提交</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import {useRoute, useRouter} from "vue-router";
import {onMounted, reactive, ref} from "vue";
import {uploadImg, editDiary, getDiary} from "@/lib/api";
import {ElMessage,FormInstance} from "element-plus";
import axios from "axios";
import {CategoryInfo} from "@/models/category";
import {TagInfo} from "@/models/tag";

const diaryFormRef = ref<FormInstance>()
const categoryList = ref([] as CategoryInfo[])
const tagList = ref([] as TagInfo[])
const diaryForm = reactive({
  id:'',
  content: 'text content',
})
const rules = reactive(
    {
    }
)
const router = useRouter()
const route = useRoute()
const diaryId = ref(route.params.diaryId)
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate(async (valid) => {
    if (valid) {
      //axios异步向后端请求数据验证
      const resp = await editDiary(diaryForm);
      if (resp.code == 200) {

          ElMessage({
            message: '提交成功',
            type: 'success',
          })
          await router.push("/admin/diary/list")
      }else {
        ElMessage.error('提交失败')
        console.log(resp)
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
  if(diaryId.value){
    diaryForm.id = diaryId.value as string
    const res = await getDiary(Number(diaryId.value))
    console.log(res)

    if (res.code == 200) {
      diaryForm.content = res.data.content
    }
  }

})

</script>

<style scoped lang="scss">
.diary-edit-container {
  height: 100%;
}
.form-inline{
  display: flex;
  flex-direction: row;
}
</style>
