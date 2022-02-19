<template>

  Test hello1
  {{this.title}}
  <Sidebar/>
</template>

<script>

import Sidebar from "@/components/Sidebar";

export default {
  name: "Test",
  components:{
    Sidebar
  },
  data(){
    return{
      title:''
    }
  },
  methods:{

  },
  async created() {
    const data = await this.$api.test()
    console.log(data)
    this.title = data
  }
}
</script>

<style scoped>

</style>