<template>
  <div class="page-title">
    <span>博客列表</span>
  </div>
  <div class="page-content">
    <div class="blog-list-container">
      <el-table :data="commentList" style="width: inherit;">
        <el-table-column label="时间" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ moment(scope.row.createTime).format('YYYY-MM-DD') }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="评论人" prop="commentatorName" />
        <el-table-column label="评论内容" prop="content" />
        <el-table-column label="状态">
          <template #default="scope">
            <span>{{scope.row.state == 0 ? "未审核" : scope.row.state == 1 ? "已审核" : "审核失败"}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="right">

          <template #default="scope">

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


    </div>
  </div>
</template>

<script setup lang="ts">
import {ElMessage} from "element-plus";
import {onMounted, ref} from "vue";
import {deleteComment, queryComment} from "@/lib/api";
import moment from "moment";
import { inject } from 'vue'
import {CommentInfo} from "@/models/comment";

const reload = inject("reload", Function)

const commentList = ref([] as CommentInfo[])



const handleDelete = async (index:any, row:any) => {
  const data = {
    id:row.id
  }
  const resp = await deleteComment(data);
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
  const resp = await queryComment({});
  if (resp.code == 200){
    commentList.value = resp.data;
  }
})

</script>

<style scoped lang="scss">

.blog-list-container{
  height: 100%;
}
.page-content{

}
.pagination-block{
  display: flex;
  justify-content: center;
}

</style>
