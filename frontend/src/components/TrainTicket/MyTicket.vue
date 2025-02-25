<template>
  <div>
    <the-ticket :can_refund="true" :can_use="true" :axiosPath=axiosPath :param="userStore.idNum"></the-ticket>
    <!-- 考虑用slot实现退票、使用功能 -->
  </div>
</template>

<script>
// import axios from 'axios';
import TheTicket from './TheTicket.vue';
export default {
  created(){
    this.axiosPath = '/my_ticket';
  },
  props: {
    tickets: Array
  },
  mounted(){

  },
  data() {
    return {
      axiosPath:'',
    }
  },
  methods: {

  },
  components: {
    TheTicket,
  }



}
</script>

<style scoped></style>