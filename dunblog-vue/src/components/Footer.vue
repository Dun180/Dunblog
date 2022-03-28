<template>
  <footer id="footer" class="footer">
    <div class="footer-inner">

      <div class="copyright"> <span itemprop="copyrightYear">2021</span>
        <span class="with-love">
          <i class="fa fa-user"></i>
        </span>
        <span class="author" itemprop="copyrightHolder">Haonan Jia</span>
      </div>
      <div class="powered-by">Powered by <a class="theme-link" target="_blank" href="https://hexo.io">Hexo</a></div>
      <span class="post-meta-divider">|</span>
      <div class="theme-info">Theme  <a class="theme-link" target="_blank" href="https://github.com/iissnan/hexo-theme-next">NexT.Gemini</a> v5.1.4</div>
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
  bottom: auto;
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  min-height: 50px;
  font-size: 14px;
  color: #999;
}
</style>