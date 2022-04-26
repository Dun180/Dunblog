<template>
  <section id="posts" class="post-expand">
    <article class="post">
      <div class="post-block">
        <header class="post-header">
          <h1 class="post-title">
            <span class="post-title-link">Categories</span>
            <span class="category-all">13 categories in total</span>
          </h1>

        </header>
        <div class="post-body">
          <div class="category-list" v-for="(item,index) in categoryList" :key="index">
            <div class="category-item">{{item.name}}</div>
          </div>

        </div>
      </div>
    </article>
  </section>
</template>

<script setup lang="ts">

import { onMounted, ref} from "vue";
import {CategoryInfo} from "@/models/category";
import { getCategoryList} from "@/lib/api";

let categoryList = ref([] as CategoryInfo[])

onMounted(async () => {
  const resp = await getCategoryList();
  if (resp.code == 200) {
    categoryList.value = Object.values(resp.data)
    console.log(categoryList.value)
  }
})

</script>

<style scoped>
.post-title{
  display: flex;
  flex-direction: column;
}
.category-all{
  font-size: 14px;
  line-height: 2;
  color: #555;
}
.category-item{
  text-decoration:underline;
}
</style>
