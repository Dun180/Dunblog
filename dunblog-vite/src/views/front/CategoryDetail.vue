<template>
  <section id="posts" class="post-expand">
    <article class="post">
      <div class="post-block">
        <header class="post-header">
          <h1 class="post-title">
            <span class="post-title-link">{{ categoryName }}</span>
          </h1>

        </header>
        <div class="post-body">
          <el-timeline>
            <el-timeline-item
                v-for="(item, index) in blogList"
                :key="index"
                :timestamp="moment(item.createTime).format('YYYY-MM-DD')"
            >
              <router-link :to="{name: Pages.BlogDetail,params: {blogId: item.id}}">{{ item.title }}</router-link>
            </el-timeline-item>
          </el-timeline>
          <div class="pagination-block">
            <el-pagination
                v-model:page-size="pageSize"
                :total="pageTotal"
                v-model:current-page="currentPage"
                @current-change="page"
                :hide-on-single-page="true"
                layout="prev, pager, next"
            />
          </div>
        </div>
      </div>
    </article>
  </section>
</template>

<script setup lang="ts">
import {getBlogListByCategoryId, getCategoryInfoById} from "@/lib/api";
import {BlogProfile} from "@/models/blog";
import {Pages} from "@/router/pages";
import moment from "moment";
import {ref} from 'vue'
import {onMounted} from "vue";
import {useRoute} from "vue-router";


const route = useRoute()

const categoryId = ref(route.params.categoryId)
const categoryName = ref('')
let blogList = ref([] as BlogProfile[])
let pageSize=ref(10);
let pageTotal=ref(1);
let currentPage=ref(1);


const page = async (currentPage: number): Promise<void> => {
  const res = await getBlogListByCategoryId(Number(categoryId.value),currentPage, pageSize.value)
  if (res.code == 200) {
    blogList.value = res.data.records
    pageSize.value = res.data.size
    pageTotal.value = res.data.total
  }
}

onMounted(async () => {
  if (categoryId.value){
    await page(1);
    const res = await getCategoryInfoById(Number(categoryId.value))
    if (res.code == 200) {
      categoryName.value = res.data.name
    }
  }
})
</script>

<style scoped>
.pagination-block{
  display: flex;
  justify-content: center;
}
</style>
