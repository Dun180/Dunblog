<template>
  <div class="page-title">
    <span>评论审核</span>
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

            <el-button
                size="small"
                type="primary"
                @click="handleReview(scope.$index, scope.row, 1)"
            >通过</el-button
            >
            <el-button
                size="small"
                type="danger"
                @click="handleReview(scope.$index, scope.row, 2)"
            >退回</el-button
            >

          </template>
        </el-table-column>
      </el-table>


    </div>
  </div>
</template>

<script setup lang="ts">
import {ElMessage} from "element-plus";
import {onMounted, ref} from "vue";
import {deleteComment, queryComment, reviewComment} from "@/lib/api";
import moment from "moment";
import { inject } from 'vue'
import {CommentInfo} from "@/models/comment";

const reload = inject("reload", Function)

const commentList = ref([] as CommentInfo[])



const handleReview = async (index:any, row:any, state: number) => {
  const data = {
    id:row.id,
    state:state
  }
  const resp = await reviewComment(data);
  if (resp.code == 200) {
    reload()
    ElMessage({
      message: '审核成功',
      type: 'success',
    })
  }else {
    console.log(resp)
    ElMessage({
      message: '审核失败',
      type: 'error',
    })
  }


}

onMounted(async () => {
  const resp = await queryComment({state:0});
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
