<template>
  <div class="admin-sidebar">
    <div class="admin-sidebar-container">
      <div class="admin-sidebar-content">
        <div class="logo-wrapper">
          <div class="wrapper">
            <div class="logo"></div>
            <div class="title">DunBlog</div>
          </div>
        </div>
        <div class="menu-wrapper">
          <el-menu
              default-active="1"
              class="el-menu-vertical-demo"
          >
            <router-link :to="{name:Pages.BlogList}">
              <el-menu-item index="1">
                <span>Home</span>
              </el-menu-item>
            </router-link>
            <router-link :to="{name:Pages.CategoryList}">
              <el-menu-item index="2">
                <span>Categories</span>
              </el-menu-item>
            </router-link>
            <router-link :to="{name:Pages.Archives}">
              <el-menu-item index="3" ref="pos">
                <span>Archives</span>
              </el-menu-item>
            </router-link>
          </el-menu>


        </div>
        <div class="user-wrapper"></div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {Pages} from "@/router/pages";

</script>

<style scoped lang="scss">
.admin-sidebar{

  width: 13vw;
  min-width: 160px;
  transition: .35s;
  height: 100%;
  border-right: 1px solid #E5E9F2;
  box-shadow: 1px 0 rgba(255,255,255,0.5);
  overflow: hidden;
}
.admin-sidebar-container{
  height: 100%;
}
.logo-wrapper {
  border-bottom: 1px solid #E5E9F2;
  box-shadow: 0 1px rgba(255,255,255,0.5);
  padding: 15px 11px;
  margin: 0 10px 10px;
}
.wrapper {
  display: flex;
  align-items: center;
}
.logo{
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background: url("@/assets/img/girl.png");
  background-size: 100% 100%;
}
.title{
  margin-left: 10px;
  font-size: 18px;
  color: #324057;
  text-shadow: 2px 2px 3px rgba(31,45,61,0.2),-1px -1px #ffffff;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}
.menu:not(.el-menu--collapse){
  background: inherit;
}
.menu-wrapper{
  padding: 0 0 0 10px;
}
</style>
