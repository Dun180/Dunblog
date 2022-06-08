<template>
  <section id="posts" class="post-expand">
    <article class="post">
      <div class="search-block">
        <el-input
            v-model="searchContent"
            class="search-input"
            placeholder="Please Input"
        />
        <el-button @click="handleSearch">搜索</el-button>
      </div>

      <div
          class="post-block"
          v-for="(blog,index) in blogList"
      >
        <header class="post-header">
          <h1 class="post-title">
            <router-link class="post-title-link" :to="{name: Pages.BlogDetail,params: {blogId: blog.id}}">{{blog.title}}</router-link>
          </h1>
        </header>

        <div class="post-button text-center">
          <router-link class="btn" :to="{name: Pages.BlogDetail,params: {blogId: blog.id}}">Read more »</router-link>

        </div>
      </div>
    </article>

  </section>


</template>

<script setup lang="ts">
import {getBlogList, searchBlog} from "@/lib/api";
import {BlogProfile} from "@/models/blog";
import {Pages} from "@/router/pages";
import moment from "moment";
import {reactive, ref,Ref} from 'vue'
import {onMounted} from "vue";

const blogList = ref([] as BlogProfile[])

const searchContent = ref()

const handleSearch = async () => {
  if (searchContent.value != null && searchContent.value != '') {
    const resp = await searchBlog(searchContent.value)
    console.log(resp)
    if (resp.code == 200) {
      blogList.value = resp.data
    }
  }
}

onMounted(async () => {
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
  height: 90px;
  min-width: 200px;
}
.post-expand .post-block{
  display: flex;
  flex-direction: column;
  justify-content:space-between;
}
.post-body{
  overflow: hidden;
}
.search-block {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  margin: 20px 0;
}
.search-input {
  width: 80%;
}
</style>
