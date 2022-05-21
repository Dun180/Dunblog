<template>
  <div class="post-expand side" v-if="isActive">
    <div class="post-block" style="height: 200px">
      <div
          v-for="anchor in titles"
          :style="{ padding: `10px 0 10px ${anchor.indent * 20}px` }"
          @click="handleAnchorClick(anchor)"
      >
        <a style="cursor: pointer">{{ anchor.title }}</a>
      </div>
    </div>

  </div>

</template>

<script setup lang="ts">
import {onBeforeUnmount, onMounted, onUnmounted, ref, watchEffect} from "vue";
import bus from '@/lib/bus'
import {useRouter} from "vue-router";
const titles = ref()
const preview = ref()
const isActive = ref(false)
const router = useRouter()

// 定义初始化方法
const initializationTitle = (titleData:any) => {
  titles.value = titleData
  isActive.value = true
}

const initializationPreview = (previewData:any) => {
  preview.value = previewData
}
function handleAnchorClick(anchor: any) {
  const { lineIndex } = anchor
  const heading = preview.value?.$el.querySelector(`[data-v-md-line="${lineIndex}"]`)
  if (heading) {
    preview.value?.scrollToTarget({
      target: heading,
      scrollContainer: window,
      top: 60
    })
  }
}



const handleScroll = () => {
  let scrollHeight = document.documentElement.scrollTop || document.body.scrollTop; //滚动高度
  let side = document.querySelector('.side')

  if (scrollHeight > 285){
    side.style.position = 'fixed'
    side.style.top = '0px'

  }else {
    side.style.position = 'static'


  }
}


watchEffect(() => {
  console.log("currentRoute:"+router.currentRoute.value)
  console.log(router.currentRoute.value.name)
  if (router.currentRoute.value.name != "BlogDetail"){
    isActive.value = false;
    titles.value = null;
    preview.value = null;
  }
})
onMounted(() => {
  window.addEventListener("scroll", handleScroll); // 监听（绑定）滚轮滚动事件


  // 启用监听
  bus.on('initializationTitle', initializationTitle);
  bus.on('initializationPreview', initializationPreview);
})

onUnmounted(async ()=>{
  window.removeEventListener("scroll", handleScroll);

})

onBeforeUnmount( () => {
  // 在组件卸载之前移除监听
  bus.off('initializationTitle', initializationTitle);
  bus.off('initializationPreview', initializationPreview);
  isActive.value = false
})
</script>

<style scoped>
.side{
  width: 240px;
}
</style>
