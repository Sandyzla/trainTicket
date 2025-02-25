<template>
  <div class="container">
    <base-card class="login">
      <img src="../../assets/TrainTicket/logo.png" />
      <div>
        <div>
          <label style="max-width: 30%;">账号</label>
          <input placeholder="身份证号 或 手机号" v-model="loginQuery.id">
        </div>
        <div>
          <label>密码</label>
          <input type="password" v-model="loginQuery.ps">
        </div>
        <div>
          <label style="color:brown" v-show="showLoginError">{{ loginErrorMes.messege }}</label>
        </div>
        <div class="button_field">
          <base-button :class="{ shake: disabled }" type="button" @click="clickLogin">登录</base-button>
          <base-button @click="toRegisterPage" type="button">没有账号?</base-button>
          <base-button @click="toAdminPage">我是管理员</base-button>
          <base-button @click="sendBack">返回</base-button>
        </div>
      </div>
    </base-card>
  </div>

</template>

<script>
import axios from 'axios';
export default {
  emits: ['sendBack'],
  data() {
    return {
      loginQuery: { id: '511133197301240418', ps: '123' },
      disabled: false,
      showLoginError: false,
      loginErrorMes: []
    }
  },
  methods: {
    clickLogin() {
      var that = this;
      axios.post(this.userStore.url + "/login", this.loginQuery)
        .then(function (response) {
          if (response.data.code === 'fail') {
            that.showLoginError = true;
            that.loginErrorMes = response.data.message;
            that.disabled = true;
            setTimeout(() => {
              that.disabled = false
            }, 1000)
          }
          else {
            that.userStore.idNum = response.data.data.idNum;
            that.userStore.password = response.data.data.password;
            that.userStore.userName = response.data.data.userName;
            that.userStore.phone = response.data.data.phone;
            that.userStore.isLogined = true;
            that.$emit('sendBack','back');
          }
        })
    },
    toAdminPage() {
      this.$emit('sendBack','back');
      this.$router.push({ name: 'admin' });
    },
    toRegisterPage(){
      this.$emit('sendBack','register');
    },
    sendBack() {
      this.$emit('sendBack','back');
    }
  }

}
</script>

<style scoped>
@import '../layouts/Animation.css';


.container {
  display: flex;
  justify-content: center;
  z-index: 5;
  /* align-items: center; */
  /* height: 590px */
}

.login {
  color:rgb(39, 39, 39);
  position: fixed;
  width: 400px;
  height: 240px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  margin: auto;
  /* margin-top: 150px; */
  /* margin-bottom: 20px; */
}

.errorInput {
  background-color: tomato;
}

div.align-left {
  text-align: left;
  margin: auto;
}

form {
  overflow: visible;
  height: 150px;
}

img {
  width: 60px;
}

input{
  background-color: rgba(228, 244, 251, 0.95);
}
label{
  margin-right: 10px;
}

.button_field{
  margin-top: 15px;
}
</style>