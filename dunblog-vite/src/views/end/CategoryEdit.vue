<template>
  <el-table :data="categoryList" style="width: 100%">

    <el-table-column label="Name" prop="name" />
    <el-table-column label="CreateTime" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ moment(scope.row.createTime).format('YYYY-MM-DD') }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column align="right">
      <template #header>
        <el-popover placement="bottom" :width="400" trigger="click" v-model:visible="addVisible">
          <template #reference>
            <button class="btn" @click="addVisible = true">
              添加分类
            </button>
          </template>
          <el-input v-model="input" placeholder="Please input" />

          <div style="text-align: right; margin: 10px 0 0;">
            <el-button size="small" type="text" @click="addVisible = false"
            >cancel</el-button
            >
            <el-button size="small" type="primary" @click="handleAdd()"
            >confirm</el-button
            >
          </div>
        </el-popover>

      </template>
      <template #default="scope">

        <el-popover placement="bottom" :width="400" trigger="click" :visible="editVisibleIndex === scope.$index">
          <template #reference>
            <el-button size="small" @click="editVisibleIndex = scope.$index">
              Edit
            </el-button>
          </template>
          <el-input v-model="input" placeholder="Please input" />

          <div style="text-align: right; margin: 10px 0 0;">
            <el-button size="small" type="text" @click="editVisibleIndex = -1"
            >cancel</el-button
            >
            <el-button size="small" type="primary" @click="handleEdit(scope.$index, scope.row)"
            >confirm</el-button
            >
          </div>
        </el-popover>
        <el-popconfirm
            title="Are you sure to delete this?"
            icon-color="red"
            @confirm="handleDelete(scope.$index, scope.row)"
        >
          <template #reference>
            <el-button
                size="small"
                type="danger"
            >Delete</el-button
            >
          </template>
        </el-popconfirm>

      </template>
    </el-table-column>
  </el-table>



</template>

<script setup lang="ts">
import {ElMessage} from "element-plus";
import {inject, onMounted, ref} from "vue";
import {CategoryInfo} from "@/models/category";
import {deleteCategory, getCategoryList, addCategory, categoryEdit} from "@/lib/api";
import moment from "moment";


const reload = inject("reload", Function)
let categoryList = ref([] as CategoryInfo[])
let addVisible = ref(false)
let editVisibleIndex = ref(-1)
let input = ref('')

const handleAdd = async () => {
  if (input.value == '') {
    ElMessage({
      message: '输入为空',
      type: 'error',
    })
    return
  }
  addVisible.value = false
  console.log(input)
  const data = {
    name: input.value
  }
  const resp = await addCategory(data);
  if (resp.code == 200) {
    reload()
    ElMessage({
      message: '添加成功',
      type: 'success',
    })
  } else {
    ElMessage({
      message: '添加失败',
      type: 'error',
    })

    input.value = ''
  }
}

const handleEdit = async (index:any, row:any) => {
  if (input.value == ''){
    ElMessage({
      message: '输入为空',
      type: 'error',
    })
    return
  }
  editVisibleIndex.value = -1
  const data = {
    id:row.id,
    name:input.value
  }
  const resp = await categoryEdit(data);
  if (resp.code == 200) {
    reload()
    ElMessage({
      message: '编辑成功',
      type: 'success',
    })
  }else {
    ElMessage({
      message: '编辑失败',
      type: 'error',
    })
  }
  input.value = ''
}

const handleDelete = async (index:any, row:any) => {

  const data = {
    id:row.id
  }

  const resp = await deleteCategory(data);
  if (resp.code == 200) {
    reload()
    ElMessage({
      message: '删除成功',
      type: 'success',
    })
  }else {
    ElMessage({
      message: '删除失败',
      type: 'error',
    })
  }
}
onMounted(async () => {
  const resp = await getCategoryList();
  if (resp.code == 200) {
    categoryList.value = Object.values(resp.data)
  }
})

</script>

<style scoped>

</style>
