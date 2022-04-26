<template>
  <section id="posts" class="post-expand">
    <article class="post">
      <div class="post-block">
        <header class="post-header">
          <h1 class="post-title">
            <span class="post-title-link">Archives</span>
          </h1>

        </header>
        <div class="post-body">
          <el-timeline>
            <el-timeline-item
                v-for="(item, index) in blogList"
                :key="index"
                :timestamp="moment(item.createTime).format('YYYY-MM-DD')"
            >
              {{ item.title }}
            </el-timeline-item>
          </el-timeline>
          <div class="pagination-block">
            <el-pagination
                v-model:page-size="this.pageSize"
                :total="this.pageTotal"
                v-model:current-page="this.currentPage"
                @current-change="page"
                layout="prev, pager, next"
            />
          </div>
        </div>
      </div>
    </article>
  </section>
</template>

<script setup lang="ts">
import {getBlogDetailById, getBlogList} from "@/lib/api";
import {BlogProfile} from "@/models/blog";
import {Pages} from "@/router/pages";
import moment from "moment";
import {reactive, ref,Ref} from 'vue'
import {onMounted} from "vue";
import {useRoute} from "vue-router";


const route = useRoute()

const categoryId = ref(route.params.categoryId)
const blog = ref({} as BlogProfile)

onMounted(async () => {

})


let blogList = ref([] as BlogProfile[])
let pageSize=ref(10);
let pageTotal=ref(1);
let currentPage=ref(1);

const page = async (currentPage: number): Promise<void> => {
  const res = await getBlogList(currentPage, pageSize.value)
  if (res.code == 200) {
    blogList.value = res.data.records
    pageSize.value = res.data.size
    pageTotal.value = res.data.total
    console.log(res.data)
  }
}

onMounted(async () => {
  await page(1);
})
</script>

<style scoped>

</style>