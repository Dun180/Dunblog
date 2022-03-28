<template>

  <div class="main">
    <div class="main-inner">
      <div class="content-wrap">
        <div class="content">
          <section id="posts" class="post-expand">
            <article class="post">
              <div
                  class="post-block"
                  v-for="blog in blogList"
              >
                <header class="post-header">
                  <h1 class="post-title">
                    <a class="post-title-link" href="">{{blog.title}}</a>
                  </h1>
                  <div class="post-meta">
                    <span class="post-time">
                      <span class="post-meta-item-icon">
                        <i class="iconfont icon-calendar"></i>
                      </span>
                      <span class="post-meta-item-text">Posted on </span>
                      <time title="Post created">
                {{ this.$moment(blog.createTime).format('YYYY-MM-DD') }}
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
                          <span>{{blog.categoryName}}</span>
                        </a>
                      </span>
                    </span>
                    <div class="post-wordcount">
                      <span class="post-meta-item-icon">
                        <i class="iconfont icon-file"></i>
                      </span>
                      <span class="post-meta-item-text">Words count in article:</span>
                      <span> 2.2k 字</span>
                      <span class="post-meta-divider">|</span>
                      <span class="post-meta-item-icon">
                        <i class="iconfont icon-time"></i>
                      </span>
                      <span class="post-meta-item-text">Reading time ≈</span>
                      <span> 9 min </span>
                    </div>
                  </div>
                </header>
                <div class="post-body">
                  {{blog.content}}
                  ...
                </div>
                <div class="post-button text-center">
                  <router-link class="btn" :to="{name: 'BlogDetail',params: {blogId: blog.id}}">Read more »</router-link>
                </div>
              </div>
            </article>
            <div class="pagination-block">
              <el-pagination
                  :page-size="4"
                  :total="5"
                  v-model:current-page="this.currentPage"
                  @current-change=page
                  layout="prev, pager, next"
              />
            </div>
          </section>
        </div>
      </div>
    </div>
</div>
<div style="position: fixed;
    top: 0px;
    left: 0px;
    z-index: -1;
    opacity: 0.5;
    background: #e3e1e1"
>
  <canvas width="1920" height="969" style="width: 1920px; height: 969px;"></canvas>
</div>
</template>

<script>
import Sidebar from "@/components/Sidebar";
export default {
  name: "Index",
  components: {Sidebar},
  data() {
    return {
      blogList: {},
      currentPage:1,
      pageCount:5,
      pageSize:4,

    }
  },
  methods:{
    //分页
    async page(currentPage) {
      const resp = await this.$api.getBlogList(currentPage)
      console.log(resp)
      this.blogList = resp

    }
  },
  async created() {
    this.page(1)
  }
}
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
  border-bottom: 1px solid #999;
  word-wrap: break-word;
}
a:hover{
  color: black;
}
.main {
  background-color: #eee;

}
.el-container{
  width: 75%;
  margin: 0 auto;
}
.main-inner{
  margin: 0 auto;
  width: 75%;
}
.content-wrap{
  float: right;
  width: calc(100% - 252px);
  box-sizing: border-box;

}
.post-expand .post-block{
  margin-bottom: 12px;
  padding: 40px;
  background: #fff;
  box-shadow: 0 2px 2px 0 rgb(0 0 0 / 12%), 0 3px 1px -2px rgb(0 0 0 / 6%), 0 1px 5px 0 rgb(0 0 0 / 12%), 0 -1px 0.5px 0 rgb(0 0 0 / 9%);
}
.post-expand .post-title{
  text-align: center;
  word-break: break-word;
  font-weight: 400;
  font-size: 1.7em;
  line-height: 1.5;
  font-family: sans-serif;

}
.post-expand .post-title-link{
  display: inline-block;
  position: relative;
  color: #555;
  border-bottom: none;
  vertical-align: top;
  line-height: inherit;
}
.post-expand .post-meta{
  margin: 3px 0 60px 0;
  color: #999;
  font-family: sans-serif;
  font-size: 12px;
  text-align: center;
  line-height: 2;
}
.post-meta-item-icon{
  margin-right: 3px;
}
.post-meta-divider {
  margin: 0 0.5em;
}
.text-center{
  text-align: center;
}
.post-body{
  font-family: sans-serif;
  text-align: justify;
  line-height: 2;
}
.post-button{
  margin-top: 40px;
}
.btn{
  display: inline-block;
  padding: 0 20px;
  font-size: 14px;
  color: #555;
  background: #fff;
  border: 2px solid #555;
  text-decoration: none;
  border-radius: 2px;
  transition-property: background-color;
  transition-duration: 0.2s;
  transition-timing-function: ease-in-out;
  transition-delay: 0s;
  line-height: 2;
}
.btn:hover{
  color: #fff;
  background: #000000;
  border: 2px solid #000000;

}
</style>