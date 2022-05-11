<template>
  <div class="container">

    <Header></Header>
    <div class="main">
      <div class="main-inner">
        <div class="content-wrap">
          <div class="content">
            <RouterView/>
          </div>
        </div>
        <aside class="sidebar">
          <Sidebar v-if="false"></Sidebar>
        </aside>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script setup lang="ts">
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import {nextTick, provide, ref} from "vue";


setTimeout(() => {
  window.L2Dwidget.init({
    pluginRootPath: 'live2dw/',
    pluginJsPath: 'lib/',
    pluginModelPath: 'live2d-widget-model-wanko/assets/',
    tagMode: false,
    debug: false,
    model: { jsonPath: '/live2dw/live2d-widget-model-wanko/assets/wanko.model.json' },
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
  width: 239px;
  margin-top: 285px;
}


</style>