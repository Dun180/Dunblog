<template>
  <footer id="footer" class="footer">
    <div class="footer-inner">

      <div class="copyright"> <span itemprop="copyrightYear">2022 </span>

        <span class="author" itemprop="copyrightHolder">Dun</span>
      </div>
      <div class="powered-by">Powered by <a class="theme-link" target="_blank" href="https://v3.cn.vuejs.org/">Vue</a></div>

    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
.footer {
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  min-height: 50px;
  width: 100%;
  font-size: 14px;
  color: #999;
}
.footer-inner{
  width: 75%;
  padding-left: 260px;
  text-align: center;
  box-sizing: border-box;
  margin: 20px auto;
}
</style>