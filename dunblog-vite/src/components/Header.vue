<template>
  <el-header height="3px" class="black-bar">
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
        <router-link :to="{name:Pages.BlogList}">
          <el-menu-item index="1">
            <i class="iconfont icon-rcd-home"></i>
            <span>&nbsp;&nbsp;Home</span>
          </el-menu-item>
        </router-link>
        <router-link :to="{name:Pages.CategoryList}">
          <el-menu-item index="2">
            <i class="iconfont icon-rcd-list"></i>
            <span>&nbsp;&nbsp;Categories</span>
          </el-menu-item>
        </router-link>
        <router-link :to="{name:Pages.Archives}">
          <el-menu-item index="3" ref="pos">
            <i class="iconfont icon-rcd-reconciliation"></i>
            <span>&nbsp;&nbsp;Archives</span>
          </el-menu-item>
        </router-link>
        <router-link :to="{name:Pages.Archives}">
          <el-menu-item index="3" ref="pos">
            <i class="iconfont icon-rcd-search"></i>
            <span>&nbsp;&nbsp;Search</span>
          </el-menu-item>
        </router-link>
      </el-menu>
    </div>
    <div class="return-external">
      <a href="#" class="return-top" title="点击返回顶部"></a>
    </div>

  </div>
</template>

<script setup lang="ts">
import {getCurrentInstance, onMounted, onUnmounted} from "vue";
import {Pages} from "@/router/pages";


onMounted(async () => {
  window.addEventListener("scroll", handleScroll); // 监听（绑定）滚轮滚动事件

})
onUnmounted(async ()=>{
  window.removeEventListener("scroll", handleScroll);

})

const handleScroll = () => {
  let scrollHeight = document.documentElement.scrollTop || document.body.scrollTop; //滚动高度
  let element = document.querySelector(".return-top") as HTMLElement

  if (scrollHeight>400){
    element.style.transform = "translateY(0px)"
  }else {
    element.style.transform = "translateY(-100%)"

  }
}


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
.black-bar{
  position: fixed;
  background-color: black;
  width: 100%;
  z-index: 1000;
}
.header .header-inner{
  position: absolute;
  width: 240px;
  box-shadow: 0 2px 2px 0 rgb(0 0 0 / 12%), 0 3px 1px -2px rgb(0 0 0 / 6%), 0 1px 5px 0 rgb(0 0 0 / 12%);
  border-radius: initial;
  margin: 0 auto;
}
.header .return-external{
  float: right;
  right: 180px;
  position: fixed;
  height: 0px;
  /*transform: translateY(100vh);*/
  transform: translateX(100px);
}
.return-top{
  display: block;
  width: 70px;
  height: 90vh;
  background: url("@/assets/img/hanging_rope.png");
  transition: all .6s;
  cursor: pointer;
  background-size: 100% 100%;
  transform: translateY(-100%);

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
  display: flex;
  justify-content: center;
}
.site-subtitle{
  font-size: 13px;
  margin: 10px 10px 0;
  font-weight: initial;
  color: #ddd;
  display: flex;
  justify-content: center;
}
div {
  display: block;
}
.el-menu{

}
</style>
