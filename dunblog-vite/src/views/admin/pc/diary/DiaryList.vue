<template>
  <div class="page-title">
    <span>日记列表</span>
  </div>
  <div class="page-content">
    <div class="diary-list-container">
      <el-timeline>

        <el-timeline-item
            v-for="(item, index) in diaryList"
            :key="index"
            :timestamp="moment(item.createTime).format('YYYY-MM-DD')"
        >
          <span>{{ item.content }}</span>


        </el-timeline-item>
      </el-timeline>

    </div>
  </div>
</template>

<script setup lang="ts">
import {ElMessage} from "element-plus";
import {onMounted, ref} from "vue";
import {getDiaryList} from "@/lib/api";
import moment from "moment";
import { inject } from 'vue'
import {Pages} from "@/router/pages";
import {DiaryInfo} from "@/models/diary";

const reload = inject("reload", Function)

const diaryList = ref([] as DiaryInfo[])




onMounted(async () => {
  const resp = await getDiaryList();
  diaryList.value = resp.data.reverse()
  console.log(diaryList.value)
})

</script>

<style scoped lang="scss">

.diary-list-container{
  height: 100%;
}
.page-content{

}
.pagination-block{
  display: flex;
  justify-content: center;
}

</style>
