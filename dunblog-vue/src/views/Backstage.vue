<template>

  <router-link class="btn" :to="{name:'BlogEdit'}">
    新建博客
  </router-link>

  <el-table :data="blogList" style="width: 100%">
    <el-table-column label="Date" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ this.$moment(scope.row.createTime).format('YYYY-MM-DD') }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="Title" prop="title" />
    <el-table-column align="right">

      <template #default="scope">
        <router-link :to="{name:'BlogEdit',params: {blogId: scope.row.id}}" style="margin-right: 10px">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
            Edit
          </el-button>
        </router-link>

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


  <div class="pagination-block">
    <el-pagination
        :page-size="this.pageSize"
        :total="this.pageTotal"
        v-model:current-page="this.currentPage"
        @current-change=page
        layout="prev, pager, next"
    />
  </div>

</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "Backstage",
  inject: ["reload"],
  data() {
    return {
      blogList:[],
      currentPage:1,
      pageTotal:5,
      pageSize:10,

    }
  },
  methods:{
    async handleEdit(index, row){



    },
    async handleDelete(index, row){

      const data = {
        id:row.id
      }
      const resp = await this.$api.deleteBlog(data);
      this.reload()

      ElMessage({
        message: resp,
        type: 'success',
      })
    },
    //分页
    async page(currentPage) {
      const resp = await this.$api.getBlogList(currentPage,this.pageSize)
      this.blogList = Object.values(resp.records)
      this.pageSize = resp.size
      this.pageTotal = resp.total
    }
  },
  async created() {
    await this.page(1)
  }
}
</script>

<style scoped>

</style>