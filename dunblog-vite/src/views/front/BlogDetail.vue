<template>
<!--  <section id="posts" class="post-expand">-->
<!--    <article class="post">-->
<!--      <div class="post-block">-->
<!--        <header class="post-header">-->
<!--          <h1 class="post-title">-->
<!--            <span class="post-title-link">{{blog.title}}</span>-->
<!--          </h1>-->
<!--          <div class="post-meta">-->
<!--                    <span class="post-time">-->
<!--                      <span class="post-meta-item-icon">-->
<!--                        <i class="iconfont icon-calendar"></i>-->
<!--                      </span>-->
<!--                      <span class="post-meta-item-text">Posted on </span>-->
<!--                        <time title="Post created">-->
<!--                          {{ this.$moment(blog.createTime).format('YYYY-MM-DD') }}-->
<!--                        </time>-->
<!--                      </span>-->
<!--            <span class="post-category">-->
<!--                        <span class="post-meta-divider">|</span>-->
<!--                        <span class="post-meta-item-icon">-->
<!--                          <i class="iconfont icon-folder"></i>-->
<!--                        </span>-->
<!--                        <span class="post-meta-item-text">In </span>-->
<!--                        <span>-->
<!--                          <a href="">-->
<!--                            <span id="category" >{{ blog.categoryName }}</span>-->
<!--                          </a>-->
<!--                        </span>-->
<!--                      </span>-->
<!--            <div class="post-wordcount">-->
<!--                        <span class="post-meta-item-icon">-->
<!--                          <i class="iconfont icon-file"></i>-->
<!--                        </span>-->
<!--              <span class="post-meta-item-text">Words count in article: </span>-->
<!--              <span> {{ blog.count }} 字</span>-->
<!--              <span class="post-meta-divider">|</span>-->
<!--              <span class="post-meta-item-icon">-->
<!--                          <i class="iconfont icon-time"></i>-->
<!--                        </span>-->
<!--              <span class="post-meta-item-text">Reading time ≈ </span>-->
<!--              <span> {{ blog.readTime }} min </span>-->
<!--            </div>-->
<!--          </div>-->
<!--        </header>-->
<!--        <div class="post-body">-->
<!--          <v-md-preview :text="blog.content"></v-md-preview>-->
<!--        </div>-->
<!--      </div>-->
<!--    </article>-->
<!--  </section>-->
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {ref} from "vue";

const route = useRoute()
const blogId = ref(route.params.blogId)

if(blogId.value){
  // const resp = await this.$api.getBlogDetailById(blogId)
  // console.log(resp)
  // this.blog = resp
}
</script>

<style scoped>

</style>