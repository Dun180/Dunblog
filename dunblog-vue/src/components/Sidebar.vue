<template>
  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
  >
    <el-menu-item index="1">
      <el-icon><setting /></el-icon>
      <span>Home</span>
    </el-menu-item>
        <el-menu-item index="2">
      <el-icon><setting /></el-icon>
      <span>Categories</span>
    </el-menu-item>
        <el-menu-item index="3">
      <el-icon><setting /></el-icon>
      <span>Archives</span>
    </el-menu-item>
    <el-menu-item index="4">
      <el-icon><setting /></el-icon>
      <span>Search</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "Sidebar"
}
</script>

<style scoped>

</style>