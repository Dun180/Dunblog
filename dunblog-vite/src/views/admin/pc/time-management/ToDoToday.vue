<template>
  <div class="page-title">
    <span>今日待办</span>
    <el-button class="card-button" @click="dialogFormVisible = true">添加待办</el-button>
  </div>
  <div class="page-content">
    <div class="box-card" v-for="(item,index) in todoList" :key="index">
      <div class="matter">
        {{item.matter}}
      </div>
      <el-button class="card-button" @click="handleComplete(item)">完成</el-button>
    </div>
  </div>


  <el-dialog v-model="dialogFormVisible" title="添加今日待办">
    <el-form :model="form">
      <el-form-item label="事项" >
        <el-input :rows="2" type="textarea" v-model="form.matter" autocomplete="off" />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm()"
        >提交</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import {inject, onMounted, ref} from "vue";
import moment from "moment";
import {ToDoListInfo} from "@/models/toDoList";
import {ElMessage} from "element-plus/es";
import {addToDoList, editToDoList, getToDoToday} from "@/lib/api";

const dialogFormVisible = ref(false)
const reload = inject("reload", Function)
const todoList = ref<ToDoListInfo[]>([])

const form = ref<ToDoListInfo>({
  matter: '',
  state: 0,
  type: 0,
  startTime: moment(new Date()).format('YYYY-MM-DD'),
  endTime: moment(new Date()).format('YYYY-MM-DD')
})
const submitForm = async () => {
  if (!form.value.matter){
    ElMessage({
      message: '事项不能为空',
      type: 'warning',
    })
    return
  }
  const resp = await addToDoList(form.value)
  if (resp.code == 200) {
    reload()
    ElMessage({
      message: '添加成功',
      type: 'success',
    })
    dialogFormVisible.value= false
  }else {
    ElMessage({
      message: '添加失败',
      type: 'error',
    })
  }
}

const handleComplete = async (todoList: ToDoListInfo) => {
  todoList.state = 1
  const resp = await editToDoList(todoList)
  if (resp.code == 200) {
    reload()
    ElMessage({
      message: '请求成功',
      type: 'success',
    })
  }else {
  ElMessage({
    message: '请求失败',
    type: 'error',
  })
}
}

onMounted(async () => {
  const resp = await getToDoToday();
  if (resp.code == 200) {
    todoList.value = resp.data
  }
})
</script>

<style scoped lang="scss">

.box-card {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: row;
  padding: 20px;
  border: 1px solid #e4e7ed;
  border-radius: 5px;
  box-shadow: 0px 0px 12px rgba(0,0,0,.12);
  margin-bottom: 10px;
}
.card-button {
  margin-left: auto;
}
</style>
