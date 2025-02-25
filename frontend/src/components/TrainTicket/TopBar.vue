<!-- Topbar.vue -->
<template>
  <div class="topBar">
    <img src="../../assets/TrainTicket/logo.png" class="logo"/>
    <label class="topButton" @click="gotoRouterByName('train_query')">查询</label> 
    <div class="spacer"></div>
      <label v-if="userStore.isLogined" @click="gotoRouterByName('order')" class="topButton">我的订单</label>
      <label v-if="userStore.isLogined" @click="logout" class="topButton">注销</label>
      <span v-if="userStore.isLogined">{{ userStore.userName }}</span>
    <label v-if="!userStore.isLogined" @click="toggleLoginUI" class="topButton">登录</label>
    <login-page v-if="isLoginUI" @sendBack="toggleLoginUI" />
    <register-page v-if="isRegisterUI" @sendBack="toggleRegisterUI"></register-page>
  </div>
</template>

<script>
import LoginPage from './LoginPage.vue';
import RegisterPage from './RegisterPage.vue';

export default {
  components: {
    LoginPage,
    RegisterPage
  },
  data() {
    return {
      isLoginUI: false,
      isRegisterUI: false,
    }
  },
  methods: {
    toggleLoginUI(destination) {
      if (destination == 'register') {
        this.isRegisterUI = true;
      }
      this.isLoginUI = !this.isLoginUI;
    },
    toggleRegisterUI() {
      this.isLoginUI = false;
      this.isRegisterUI = false;
    },
    gotoRouterByName(route) {
      this.$router.push({ name: route })
    },
    logout() {
      this.userStore.isLogined = false;
    }
  },
};
</script>
<style scoped>
span {
  color: rgb(32, 32, 32);
  font-weight: 600;
}

.topBar {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.topButton {
  color: rgb(145, 145, 145);
  border-radius: 20%/40%;
  font-weight: 600;
  width: 80px;
  font-size: 16px;
  padding-left: 15px;
  padding-right: 15px;
  height: 30px;
  display: flex;
    align-items: center; /* 垂直居中 */
    justify-content: center;
}

.topButton:hover {
  outline: 1px solid rgb(192, 192, 192);
  background-color: rgb(234, 234, 234);
}

img.logo{
  width: 40px;
  margin: 10px;
}

.spacer {
  flex-grow: 1; /* 填充中间的空白区域 */
}
</style>