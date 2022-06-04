<template>
  <div class="container">

    <Header></Header>
    <div class="main">
      <div class="main-inner">
        <div class="content-wrap">
          <div class="content">
            <RouterView v-if="isRouterAlive"/>
          </div>
        </div>
        <aside class="sidebar">
          <Sidebar></Sidebar>
        </aside>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script setup lang="ts">
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import Sidebar from '@/components/Sidebar.vue'
const isRouterAlive = ref(true);

import {nextTick, provide, ref} from "vue";
const frontReload = () => {
  isRouterAlive.value = false
  nextTick(function () {
    isRouterAlive.value = true
  })
}
provide("frontReload",frontReload)

setTimeout(() => {
  window.L2Dwidget.init({
    pluginRootPath: 'live2dw/',
    pluginJsPath: 'lib/',
    pluginModelPath: 'live2d-widget-model-nito/assets/',
    tagMode: false,
    debug: false,
    model: { jsonPath: '/live2dw/live2d-widget-model-nito/assets/nito.model.json' },
    display: { position: 'left', width: 150, height: 300 },
    mobile: { show: true },
    log: false
  })
}, 3000)


</script>

<style scoped lang="scss">

.sidebar{
  position: static;
  float: left;
  margin-top: 341px;
}

</style>
