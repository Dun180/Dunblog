<template>
  <div class="page-title">
    <span>博客列表</span>
  </div>
  <div class="page-content">
    <div class="blog-list-container">
      <el-table :data="blogList" style="width: 100%">
        <el-table-column label="Date" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ moment(scope.row.createTime).format('YYYY-MM-DD') }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="Title" prop="title" />
        <el-table-column align="right">

          <template #default="scope">
            <router-link :to="{name:Pages.BlogEdit_Admin,params: {blogId: scope.row.id}}" style="margin-right: 10px">
              <el-button size="small">Edit</el-button>
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
            :page-size="pageSize"
            :total="pageTotal"
            v-model:current-page="currentPage"
            @current-change=page
            :hide-on-single-page="true"
            layout="prev, pager, next"
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {ElMessage} from "element-plus";
import {onMounted, ref} from "vue";
import {BlogProfile} from "@/models/blog";
import {getBlogList,deleteBlog} from "@/lib/api";
import moment from "moment";
import { inject } from 'vue'
import {Pages} from "@/router/pages";

const reload = inject("reload", Function)

const blogList = ref([] as BlogProfile[])
const pageSize=ref(10);
const pageTotal=ref(5);
const currentPage=ref(1);


const handleDelete = async (index:any, row:any) => {
  const data = {
    id:row.id
  }
  const resp = await deleteBlog(data);
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

const page = async (currentPage: number): Promise<void> => {
  const res = await getBlogList(currentPage, pageSize.value)
  if (res.code == 200) {
    blogList.value = Object.values(res.data.records)
    // this.blogList = Object.values(resp.records)
    pageSize.value = res.data.size
    pageTotal.value = res.data.total
  }
}
onMounted(async () => {
  await page(1);
})

</script>

<style scoped lang="scss">

.blog-list-container{
  height: 100%;
}
.pagination-block{
  display: flex;
  justify-content: center;
}
</style>
