<template>
  <section id="posts" class="post-expand">
    <article class="post">
      <div
          class="post-block"
          v-for="(blog,index) in blogList"
      >
        <header class="post-header">
          <h1 class="post-title">
              <router-link class="post-title-link" :to="{name: Pages.BlogDetail,params: {blogId: blog.id}}">{{blog.title}}</router-link>
          </h1>
          <div class="post-meta">
                    <span class="post-time">
                      <span class="post-meta-item-icon">
                        <i class="iconfont icon-calendar"></i>
                      </span>
                      <span class="post-meta-item-text">Posted on </span>
                      <time title="Post created">
                        {{ moment(blog.createTime).format('YYYY-MM-DD') }}
                      </time>
                    </span>
            <span class="post-category">
                      <span class="post-meta-divider">|</span>
                      <span class="post-meta-item-icon">
                        <i class="iconfont icon-folder"></i>
                      </span>
                      <span class="post-meta-item-text">In </span>
                      <span>
                        <a href="">
                          <span id="category" >{{blog.categoryName}}</span>
                        </a>
                      </span>
                    </span>
            <div class="post-wordcount">
                      <span class="post-meta-item-icon">
                        <i class="iconfont icon-file"></i>
                      </span>
              <span class="post-meta-item-text">Words count in article: </span>
              <span> {{ blog.count }} 字</span>
              <span class="post-meta-divider">|</span>
              <span class="post-meta-item-icon">
                        <i class="iconfont icon-time"></i>
                      </span>
              <span class="post-meta-item-text">Reading time ≈ </span>
              <span> {{ blog.readTime }} min </span>
            </div>
          </div>
        </header>
        <div class="post-body">
          {{blog.content}}
          ...
        </div>
        <div class="post-button text-center">
          <router-link class="btn" :to="{name: Pages.BlogDetail,params: {blogId: blog.id}}">Read more »</router-link>

        </div>
      </div>
    </article>
    <div class="pagination-block">
      <el-pagination
          v-model:page-size="this.pageSize"
          :total="this.pageTotal"
          v-model:current-page="this.currentPage"
          @current-change="page"
          layout="prev, pager, next"
      />
    </div>
  </section>


</template>

<script setup lang="ts">
import {getBlogList} from "@/lib/api";
import {BlogProfile} from "@/models/blog";
import {Pages} from "@/router/pages";
import moment from "moment";
import {reactive, ref,Ref} from 'vue'
import {onMounted} from "vue";

let blogList = ref([] as BlogProfile[])
let pageSize=ref(4);
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
body {
  font-size: 16px;
}
::selection {
  background-color: palevioletred;
  color: white;
}
a {
  color: #555;
  text-decoration: none;
  outline: none;
  word-wrap: break-word;
}
.pagination-block{
  display: flex;
  justify-content: center;
}
</style>
