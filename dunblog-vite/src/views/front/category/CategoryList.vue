<template>
  <section id="posts" class="post-expand">
    <article class="post">
      <div class="post-block">
        <header class="post-header">
          <h1 class="post-title">
            <span class="post-title-link">Categories</span>
            <span class="category-all">{{ categoryList.length }} categories in total</span>
          </h1>
        </header>
        <div class="post-body">
            <ul class="category-list">
              <li v-for="(item,index) in categoryList" :key="index">
                <router-link class="category-item" :to="{name: Pages.CategoryDetail,params: {categoryId: item.id}}">{{item.name}}</router-link>
              </li>
            </ul>
        </div>
      </div>
    </article>
  </section>
</template>

<script setup lang="ts">

import { onMounted, ref} from "vue";
import {CategoryInfo} from "@/models/category";
import { getCategoryList} from "@/lib/api";
import {Pages} from "@/router/pages";

let categoryList = ref([] as CategoryInfo[])

onMounted(async () => {
  const resp = await getCategoryList();
  if (resp.code == 200) {
    categoryList.value = Object.values(resp.data)
  }
})

</script>

<style scoped lang="scss">
.post-title{
  display: flex;
  flex-direction: column;
}
.category-all{
  font-size: 14px;
  line-height: 2;
  color: #555;
}
.category-list{
  margin-top: 20px;
}
.category-item{
  text-decoration:underline;
}
li::marker {
  content: 'o  ';

}
</style>
