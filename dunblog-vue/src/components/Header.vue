<template>
  <el-header height="3px" style="background-color: black">
  </el-header>
  <div class="header">
    <div class="header-inner">
      <div class="site-brand-wrapper">
        <div class="site-meta">
          <div class="custom-logo-site-title">
            <a href="">
              <span class="site-title">Dun Blogs</span>
            </a>
          </div>
          <p class="site-subtitle">老街橘杂</p>
        </div>
      </div>
      <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
      >
        <el-sub-menu index="1">
          <template #title>
            <el-icon><location /></el-icon>
            <span>Navigator One</span>
          </template>
          <el-menu-item-group title="Group One">
            <el-menu-item index="1-1">item one</el-menu-item>
            <el-menu-item index="1-2">item one</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="Group Two">
            <el-menu-item index="1-3">item three</el-menu-item>
          </el-menu-item-group>
          <el-sub-menu index="1-4">
            <template #title>item four</template>
            <el-menu-item index="1-4-1">item one</el-menu-item>
          </el-sub-menu>
        </el-sub-menu>
        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <span>Navigator Two</span>
        </el-menu-item>
        <el-menu-item index="3" disabled>
          <el-icon><document /></el-icon>
          <span>Navigator Three</span>
        </el-menu-item>
        <el-menu-item index="4">
          <el-icon><setting /></el-icon>
          <span>Navigator Four</span>
        </el-menu-item>
      </el-menu>
    </div>

  </div>

</template>

<script>
export default {
  name: "Header"
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