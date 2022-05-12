<template>

  <div class="mobile-container">
    <van-nav-bar
        title="Admin"
        left-text="菜单"
        @click-left="onClickLeft"
    />
    <div class="mobile-main">
      <router-view v-if="isRouterAlive"/>
    </div>
  </div>

  <van-popup v-model:show="showPopup" position="left" style="height: 100%;">
    <AdminSidebar></AdminSidebar>
  </van-popup>
</template>

<script setup lang="ts">
import AdminSidebar from '@/components/AdminSidebar.vue';

import {nextTick, provide, ref} from "vue";
const isRouterAlive = ref(true);
const showPopup = ref(false);



const reload = () => {
  isRouterAlive.value = false
  nextTick(function () {
    isRouterAlive.value = true
  })
}
provide("reload",reload)

const onClickLeft = () => {
  showPopup.value = true
}

</script>

<style scoped lang="scss">
.mobile-container{
  height: 100vh;
}
.mobile-main{
  padding: 0 20px 20px 20px;
}
</style>
