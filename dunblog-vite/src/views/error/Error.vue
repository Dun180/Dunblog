<template>
  <el-dialog v-if="false"></el-dialog>
  <el-button v-if="false"></el-button>
</template>

<script setup lang="ts">
  import {onMounted} from "vue";
  import {ElMessageBox} from 'element-plus'
  import type { Action } from 'element-plus'
  import {useStore} from "@/store";
  import {useRouter} from "vue-router";

  const store = useStore()
  const router = useRouter()

  onMounted( ()=>{
    ElMessageBox.alert('登录信息失效', 'Title', {
      confirmButtonText: 'OK',
      callback: (action: Action) => {
        store.REMOVE_INFO()
        router.push("/")
      },
    })
  })

</script>

<style scoped>

</style>