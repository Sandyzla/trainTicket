<!-- App.vue -->
<template>
  <TopBar></TopBar>



  <div class="container">
    <router-view v-slot="{ Component }">
    <keep-alive>
      <component v-if="$route.meta.keepAlive" :is="Component" />
    </keep-alive>
    <component v-if="!$route.meta.keepAlive" :is="Component" />
  </router-view>
    <!-- <router-view ></router-view>  -->
  </div>

</template>

<script>
import TopBar from './TopBar.vue';
export default {
  components: {
    TopBar,
  },
  methods: {
  }
};
</script>
