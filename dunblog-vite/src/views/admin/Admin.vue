<template>
  <div class="admin-container">
    <div class="wrapper">
      <AdminSidebar></AdminSidebar>
      <div class="admin-page">
        <router-view v-if="isRouterAlive"/>

      </div>

    </div>
  </div>
</template>

<script setup lang="ts">
import AdminSidebar from '@/components/AdminSidebar.vue';
import {nextTick, provide, ref} from "vue";
const isRouterAlive = ref(true);
const reload = () => {
  isRouterAlive.value = false
  nextTick(function () {
    isRouterAlive.value = true
  })
}
provide("reload",reload)
</script>

<style scoped lang="scss">
.admin-container{
  height: 100vh;
  width: 100%;
  max-height: inherit;
}
.wrapper {
  //display: grid;
  //grid-template-columns: auto 1fr;
  display: flex;
  flex-direction: row;
  height: 100%;


}
.admin-page{
  padding: 0 15px;
  flex: 1;
  display: flex;
  flex-direction: column;
}
</style>