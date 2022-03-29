<template>

    <div class="container">

      <Header></Header>
      <div class="main">
        <div class="main-inner">
          <div class="content-wrap">
            <div class="content">
              <router-view v-if="isRouterAlive"/>

            </div>
          </div>
        </div>
      </div>
      <Footer></Footer>
    </div>
<!--  <div style="position: fixed;-->
<!--    top: 0px;-->
<!--    left: 0px;-->
<!--    z-index: -1;-->
<!--    opacity: 0.5;-->
<!--    background: #e3e1e1"-->
<!--  >-->
<!--    <canvas width="1920" height="969" style="width: 1920px; height: 969px;"></canvas>-->
<!--  </div>-->
</template>
<script>

import Header from "@/components/Header";
import Footer from "@/components/Footer";

export default {
  name: 'App',
  components: {
    Header,
    Footer
  },
  provide () {
    return {
      reload: this.reload
    }
  },
  data () {
    return {
      isRouterAlive: true
    }
  },
  methods: {
    reload () {
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    }
  }

}
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
body{
  margin: 0;
  padding: 0;
  border: 0;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

</style>
