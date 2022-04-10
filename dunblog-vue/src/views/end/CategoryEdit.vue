<template>
  <el-table :data="categoryList" style="width: 100%">

    <el-table-column label="Name" prop="name" />
    <el-table-column label="CreateTime" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ this.$moment(scope.row.createTime).format('YYYY-MM-DD') }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column align="right">
      <template #default="scope">
        <el-popover placement="right" :width="400" trigger="click" v-model:visible="visible">
          <template #reference>
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
              Edit
            </el-button>
          </template>
          <el-input v-model="input" placeholder="Please input" />

          <div style="text-align: right; margin: 0">
            <el-button size="small" type="text" @click="visible = false"
            >cancel</el-button
            >
            <el-button size="small" type="primary" @click="visible = false"
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

<script>
import {ElMessage} from "element-plus";

export default {
  name: "CategoryEdit",
  data(){
    return{
      categoryList: '',
      visible: false,
      input:'',
    }
  },
  methods:{
    async handleEdit(index, row){

    },
    async handleDelete(index, row){

      const data = {
        id:row.id
      }

      this.reload()

      ElMessage({
        message: '',
        type: 'success',
      })
    }
  },
  async created(){
    const resp = await this.$api.getCategoryList();
    this.categoryList = Object.values(resp)
  }
}
</script>

<style scoped>

</style>
