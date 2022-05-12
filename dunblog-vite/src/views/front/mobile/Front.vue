<template>
  <div class="mobile-container">
    <van-config-provider :theme-vars="themeVars">
      <van-nav-bar
          title="DunBlog"
          left-text="菜单"
          @click-left="onClickLeft"
          class="mobile-bar"
      />
    </van-config-provider>
    <div class="mobile-main">
      <RouterView/>
    </div>
  </div>

  <van-popup v-model:show="showPopup" position="left" style="height: 100%;">
    <Sidebar></Sidebar>
  </van-popup>
</template>

<script setup lang="ts">

import {ref} from "vue";

const showPopup = ref(false);


const onClickLeft = () => {
  showPopup.value = true
}

const themeVars = {
  navBarTitleTextColor:'#ffffff',
  navBarTextColor:'#ffffff',
};
</script>

<style scoped lang="scss">
.mobile-container{
  height: 100vh;
}
.mobile-main{
  padding: 20px;
}
.mobile-bar{
  background: #1b1f23;
}

</style>
