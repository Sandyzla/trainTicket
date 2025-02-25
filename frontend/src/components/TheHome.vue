<template>
    <div>
      <base-button @click="goToTrainTicket">火车票购票系统</base-button>
    </div>
  </template>

<script>
// import TheBalls from './components/layouts/TheBalls.vue'
export default {
  components: {
    // TheBalls
  },
  methods: {
    goToTrainTicket() {
      this.$router.push('/trainTicket/query');
      // 或者使用 this.$router.replace('/about')，区别在于 replace 不会保留浏览历史
    }
  }
}
</script>