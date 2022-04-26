<template>
  <el-header height="3px" style="background-color: black">
  </el-header>
  <div class="header">
    <div class="header-inner">
      <div class="site-brand-wrapper">
        <div class="site-meta">
          <div class="custom-logo-site-title">
            <router-link :to="{name:'BlogList'}">
              <a href="">
                <span class="site-title">Dun Blogs</span>
              </a>
            </router-link>
          </div>
          <p class="site-subtitle">老橘牛杂</p>
        </div>
      </div>
      <el-menu
          default-active="1"
          class="el-menu-vertical-demo"
      >
        <router-link :to="{name:'BlogList'}">
          <el-menu-item index="1">
            <span>Home</span>
          </el-menu-item>
        </router-link>
        <router-link :to="{name:'CategoryList'}">
          <el-menu-item index="2">
            <span>Categories</span>
          </el-menu-item>
        </router-link>
        <router-link :to="{name:'Archives'}">
          <el-menu-item index="3">
            <span>Archives</span>
          </el-menu-item>
        </router-link>
        <el-menu-item index="4">
          <span>Search</span>
        </el-menu-item>
      </el-menu>
    </div>

  </div>
</template>

<script setup lang="ts">

</script>

<style scoped>
::selection {
  background-color: palevioletred;
  color: white;
}
.el-header{
  background-color: #b3c0d1;
  color: var(--el-text-color-primary);
}
.header{
  position: relative;
  margin: 0 auto;
  width: 75%;
}
.header .header-inner{
  position: absolute;
  width: 240px;
  box-shadow: 0 2px 2px 0 rgb(0 0 0 / 12%), 0 3px 1px -2px rgb(0 0 0 / 6%), 0 1px 5px 0 rgb(0 0 0 / 12%);
  border-radius: initial;
  margin: 0 auto;
}
.site-meta {
  padding: 20px 0;
  font-size: 16px;
  background: black;
  color: white;
}
.site-title {
  line-height: 36px;
  font-size: 20px;
  font-weight: normal;
}
.site-subtitle{
  font-size: 13px;
  margin: 10px 10px 0;
  font-weight: initial;
  color: #ddd;
}
div {
  display: block;
}
.el-menu{

}
</style>
