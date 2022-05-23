<template>
  <div class="comment-container">
      <el-input
          v-model="comment"
          :rows="3"
          type="textarea"
          placeholder="Please input"
          style="margin-bottom: 10px"
      />
      <el-button type="default" @click="onSubmit">Create</el-button>
  </div>
</template>

<script setup>

const comment = ref("说点什么吧")
const onSubmit = () => {

}
</script>

<style scoped lang="scss">

</style>
