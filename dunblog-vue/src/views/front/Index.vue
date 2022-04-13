<template>


          <section id="posts" class="post-expand">
            <article class="post">
              <div
                  class="post-block"
                  v-for="blog in blogList"
              >
                <header class="post-header">
                  <h1 class="post-title">
                    <router-link class="post-title-link" :to="{name: 'BlogDetail',params: {blogId: blog.id}}">{{blog.title}}</router-link>
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
                  <router-link class="btn" :to="{name: 'BlogDetail',params: {blogId: blog.id}}">Read more »</router-link>
                </div>
              </div>
            </article>
            <div class="pagination-block">
              <el-pagination
                  :page-size="this.pageSize"
                  :total="this.pageTotal"
                  v-model:current-page="this.currentPage"
                  @current-change=page
                  layout="prev, pager, next"
              />
            </div>
          </section>


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
      pageTotal:5,
      pageSize:3,

    }
  },
  methods:{
    //分页
    async page(currentPage) {
      const resp = await this.$api.getBlogList(currentPage,this.pageSize)
      this.blogList = resp.records
      this.pageSize = resp.size
      this.pageTotal = resp.total
    }
  },
  async created() {
    await this.page(1)
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
  word-wrap: break-word;
}



</style>
