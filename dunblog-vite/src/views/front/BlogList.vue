<template>
  <section id="posts" class="post-expand">
    <article class="post">
      <div
          class="post-block"
          v-for="(blog,index) in blogList"
      >
        <header class="post-header">
          <h1 class="post-title">
            <span class="post-title-link">{{blog.title}}</span>
          </h1>
          <div class="post-meta">
                    <span class="post-time">
                      <span class="post-meta-item-icon">
                        <i class="iconfont icon-calendar"></i>
                      </span>
                      <span class="post-meta-item-text">Posted on </span>
                      <time title="Post created">

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
          <span class="btn" >Read more »</span>
        </div>
      </div>
    </article>

  </section>


</template>

<script setup lang="ts">
import {getBlogList} from "@/lib/api";
import {BlogProfile} from "@/models/blog";

import {reactive, ref,Ref} from 'vue'
import {onMounted} from "vue";
const haha:number = 1;
let blogList = ref([] as BlogProfile[])
let pageSize:number=4;
let pageTotal:number;
let currentPage:number=1;

const page = async (currentPage: number): Promise<void> => {
  const res = await getBlogList(currentPage, pageSize)
  if (res.code == 200) {
    blogList.value = res.data.records
    pageSize = res.data.size
    pageTotal = res.data.total
    console.log(blogList)
  }
}
onMounted(async () => {
  await page(1);
})


</script>

<style scoped>

</style>
