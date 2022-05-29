<template>
  <section id="posts" class="post-expand">
    <article class="post">
      <div class="post-block main-block">
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
                          {{ moment(blog.createTime).format('YYYY-MM-DD') }}
                        </time>
                      </span>
            <span class="post-category">
                        <span class="post-meta-divider">|</span>
                        <span class="post-meta-item-icon">
                          <i class="iconfont icon-folder"></i>
                        </span>
                        <span class="post-meta-item-text">In </span>
                        <router-link class="category-router"
                                     v-if="typeof blog.categoryId !== 'undefined'"
                                     :to="{name: Pages.CategoryDetail,params:{categoryId: blog.categoryId}}">{{blog.categoryName}}</router-link>

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
          <v-md-preview :text="blog.content" ref="preview"></v-md-preview>

        </div>
      </div>
      <div class="post-block comment-block">
        <Comment :blogId="Number.parseInt(blogId)"></Comment>
      </div>
    </article>
  </section>

</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {onMounted, onUpdated, ref} from "vue";
import {getBlogDetailById} from "@/lib/api";
import {BlogProfile} from "@/models/blog";
import moment from "moment";
import {Pages} from "@/router/pages";
import bus from '@/lib/bus'
import Comment from '@/components/Comment.vue'



const route = useRoute()

const preview = ref(null)

const blogId = ref(route.params.blogId)
const blog = ref({} as BlogProfile)
const titles = ref()


onMounted(async () => {
  if(blogId.value){
    const res = await getBlogDetailById(Number(blogId.value))
    if (res.code == 200) {
      blog.value = res.data
      if (blog.value.tagName!=undefined){
        blog.value.tagNameList = blog.value.tagName.split(',')
      }
    }
  }
})

onUpdated(() => {
  const anchors = preview.value?.$el.querySelectorAll('h1,h2,h3,h4,h5,h6')
  titles.value = Array.from(anchors).filter((title:any) => !!title.innerText.trim())
  if (!titles.value.length) {
    titles.value = []
    return
  }
  const hTags = Array.from(new Set(titles.value.map((title:any) => title.tagName))).sort()
  titles.value = titles.value.map((el:any) => ({
    title: el.innerText,
    lineIndex: el.getAttribute('data-v-md-line'),
    indent: hTags.indexOf(el.tagName)
  }))
  bus.emit('initializationTitle', titles.value);
  bus.emit('initializationPreview', preview.value);
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
.post-block {
  min-width: 200px;
}
.category-router {
  color: #555;
  text-decoration: underline;
  outline: none;
  word-wrap: break-word;
}
.category-router:hover {
  color: #000000;
}
.post-tags .el-tag {
  margin-right: 10px;
}
.post-body {
  margin-top: 30px;
}
.main-block {
  min-height: 660px;
}
.comment-block {
  display: flex;
  flex-direction: column;
}
</style>
