<template>
  <div class="page-title">
    <span>仪表盘</span>
  </div>
  <div class="page-content">
    <calendar-heatmap
        class="calendar"
        :values="timeData"
        :end-date="currentDate"
        :tooltip="true"
    />
  </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import {getCalendarHeatmapData} from "@/lib/api";
import moment from "moment";

const timeData = ref<Object>([])
const currentDate = ref('')
onMounted(async () => {
  currentDate.value = moment(new Date()).format('YYYY-MM-DD')
  console.log(currentDate.value)
  const resp = await getCalendarHeatmapData()
  if (resp.code == 200) {
    timeData.value = resp.data
  }
})
</script>

<style scoped lang="scss">
.calendar {
  width: 60%;
  height: 60%;
  font-size: 10px;
  fill: #767676;
}
</style>
