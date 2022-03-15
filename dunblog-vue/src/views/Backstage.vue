<template>
  <div class="main">
    <div class="main-inner">
      <div class="content-wrap">
        <div class="content">
          <router-link :to="{name:'BlogEdit'}">
            <a href="">新建博客</a>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Backstage"
}
</script>

<style scoped>

</style>