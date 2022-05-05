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
                      <router-link class="category-router" :to="{name: Pages.CategoryDetail,params: {categoryId: blog.categoryId}}">{{blog.categoryName}}</router-link>

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
            <div class="post-tags">
              <el-tag type="info" v-for="tag in blog.tagNameList">{{tag}}</el-tag>
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
          v-model:page-size="pageSize"
          v-model:total="pageTotal"
          v-model:current-page="currentPage"
          @current-change="page"
          :hide-on-single-page="true"
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

const blogList = ref([] as BlogProfile[])
const pageSize=ref(4);
const pageTotal=ref(1);
const currentPage=ref(1);

const page = async (currentPage: number): Promise<void> => {
  const res = await getBlogList(currentPage, pageSize.value)
  if (res.code == 200) {
    blogList.value = res.data.records
    for (let i = 0; i < blogList.value.length; i++) {
      if (blogList.value[i].tagName!=undefined){
        blogList.value[i].tagNameList = blogList.value[i].tagName.split(',')
      }
    }
    pageSize.value = res.data.size
    pageTotal.value = res.data.total
  }
  document.documentElement.scrollTop = 0;
}

onMounted(async () => {
  await page(1);
})


</script>

<style scoped lang="scss">
body {
  font-size: 16px;
}
::selection {
  background-color: palevioletred;
  color: white;
}
.category-router {
  color: #555;
  text-decoration: underline;
  outline: none;
  word-wrap: break-word;
}
.category-router:hover{
  color: #000000;
}
.pagination-block{
  display: flex;
  justify-content: center;
}
.post-tags .el-tag{
  margin-right: 10px;
}
.post-block{
  height: 280px;
}
</style>
