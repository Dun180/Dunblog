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
            class="menu"
            background-color="#f0f2f7"
        >
          <router-link :to="{name:Pages.Dashboard}">
            <el-menu-item index="1" >
              <span>仪表盘</span>
            </el-menu-item>
          </router-link>
          <el-sub-menu index="2">
            <template #title>
              <span>博客</span>

            </template>
            <router-link :to="{name:Pages.BlogList_Admin}">
              <el-menu-item index="2-1">所有博客</el-menu-item>
            </router-link>
            <router-link :to="{name:Pages.BlogEdit_Admin}">
              <el-menu-item index="2-2">新建博客</el-menu-item>
            </router-link>
          </el-sub-menu>
          <router-link :to="{name:Pages.CategoryList_Admin}">
            <el-menu-item index="3" >
              <span>分类</span>
            </el-menu-item>
          </router-link>
          <router-link :to="{name:Pages.TagList_Admin}">
            <el-menu-item index="4" >
              <span>标签</span>
            </el-menu-item>
          </router-link>

          <el-sub-menu index="5">
            <template #title>
              <span>日记</span>

            </template>
            <router-link :to="{name:Pages.DiaryList_Admin}">
              <el-menu-item index="5-1">查看日志</el-menu-item>
            </router-link>

            <router-link :to="{name:Pages.DiaryEdit_Admin}">
              <el-menu-item index="5-2">新建日志</el-menu-item>
            </router-link>

          </el-sub-menu>

          <el-sub-menu index="6">
            <template #title>
              <span>评论</span>

            </template>
            <router-link :to="{name:Pages.CommentModeration_Admin}">
              <el-menu-item index="6-1">审核评论</el-menu-item>
            </router-link>

            <router-link :to="{name:Pages.CommentList_Admin}">
              <el-menu-item index="6-2">全部评论</el-menu-item>
            </router-link>

          </el-sub-menu>
        <el-sub-menu index="7">
          <template #title>
            <span>时间管理</span>

          </template>
          <router-link :to="{name:Pages.ToDoToday_Admin}">
            <el-menu-item index="7-1">今日待办</el-menu-item>
          </router-link>

          <router-link :to="{name:Pages.ToDoGlobal_Admin}">
            <el-menu-item index="7-2">全局待办</el-menu-item>
          </router-link>

        </el-sub-menu>
        </el-menu>

      </div>
      <div class="user-wrapper">
        <el-menu
            default-active="1"
            class="menu"
            background-color="#f0f2f7"
        >
          <router-link :to="{name:Pages.BlogList}">
            <el-menu-item index="1" >
              <span>Home</span>
            </el-menu-item>
          </router-link>

        </el-menu>

      </div>
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
.admin-sidebar-content {
  height: 100%;
  display: flex;
  flex-direction: column;
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
.user-wrapper{
  padding: 0 0 0 10px;
  margin-top: auto;
}
</style>
