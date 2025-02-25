<template>
  <div class="wrapper">
    <div class="header-content">
      <div class="header">
        注册
      </div>
      <div class="content">
        <div class="form-container">
          <div class="form-row">
            <label>身份证号</label>
            <input v-model="registerQuery.idNum" @input="validateIdNum">
            <label v-if="is_idNum_used">该用户已注册</label>
            <img v-else-if="errorMes_idNum" src="../../assets/TrainTicket/error.png">
            <img v-else src="../../assets/TrainTicket/ok.png">
          </div>
          <div class="form-row">
            <label>设置密码</label>
            <input type="password" v-model="registerQuery.password">
            <img v-if="errorMes_password" src="../../assets/TrainTicket/error.png">
            <img v-else src="../../assets/TrainTicket/ok.png">
          </div>
          <div class="form-row">
            <label>确认密码</label>
            <input type="password" v-model="comfirmPassword" @input="validateConfirmPassword">
            <img v-if="errorMes_password" src="../../assets/TrainTicket/error.png">
            <img v-else src="../../assets/TrainTicket/ok.png">
          </div>
          <div class="form-row">
            <label>手机号码</label>
            <input v-model="registerQuery.phone" @input="validatePhone">
            <img v-if="errorMes_phone" src="../../assets/TrainTicket/error.png">
            <img v-else src="../../assets/TrainTicket/ok.png">
          </div>
          <div class="form-row">
            <label>用户姓名</label>
            <input v-model="registerQuery.userName" @input="validateName">
            <img v-if="errorMes_name" src="../../assets/TrainTicket/error.png">
            <img v-else src="../../assets/TrainTicket/ok.png">
          </div>
          <div>
          </div>
        </div>
        <div>
          <base-button type="button" @click="clickRegister" :class="{ shake: disabled }">注册并登录
          </base-button>
          <base-button type="button" @click="$emit('sendBack')">返回</base-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import axios from 'axios';
export default {
  emits: ['sendBack'],
  data() {
    return {
      registerQuery: { idNum: '', phone: '', userName: '', password: '' },
      comfirmPassword: '',
      errorMes_idNum: '未输入',
      errorMes_password: '未输入',
      errorMes_phone: '未输入',
      errorMes_name: '未输入',
      disabled: false,
      user_existed: false,
      is_idNum_used: false,
    }
  },
  methods: {
    validateIdNum() {
      const regex = /^[1-9]\d{5}(18|19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])\d{3}(\d|X)$/i;
      if (!regex.test(this.registerQuery.idNum)) {
        this.errorMes_idNum = '身份证号码格式不正确';
        this.is_idNum_used = false;
      } else {
        var that = this;
        axios({ //后端获取到idNum时多了一个'=',设置headers可以解决这个问题
          method: 'post',
          url: 'http://localhost:8888/registerListen',
          headers: {
            'Content-Type': 'application/json; charset=utf-8'
          },
          data: this.registerQuery.idNum
        }).then(function (response) {
          if (response.data.code === 'fail') {
            that.errorMes_idNum = '用户已存在';
            that.is_idNum_used = true;
          }
          else {
            that.errorMes_idNum = '';
            that.is_idNum_used = false;
          }
        })
      }
    },
    validateConfirmPassword() {
      if (this.registerQuery.password != this.comfirmPassword ||
        this.comfirmPassword.length == 0) {
        this.errorMes_password = '空密码或密码不一致';
      } else {
        this.errorMes_password = '';
      }
    },
    validatePhone() {
      const regex = /^1[3-9]\d{9}$/;
      if (regex.test(this.registerQuery.phone)) {
        this.errorMes_phone = '';
      } else {
        this.errorMes_phone = '格式有误'
      }
    },
    validateName() {
      const regex = /^[\u4e00-\u9fa5]{2,4}$/;
      if (regex.test(this.registerQuery.userName)) {
        this.errorMes_name = '';
      } else {
        this.errorMes_name = '格式有误'
      }
    },
    clickRegister() {
      var that = this;
      if (!this.errorMes_idNum && !this.errorMes_password && !this.errorMes_phone && !this.errorMes_name) {
        axios.post("http://localhost:8888/register", this.registerQuery)
          .then(function (response) {
            if (response.data.code === 'fail') {
              that.disabled = true;
              setTimeout(() => {
                that.disabled = false
              }, 1000)
            }
            else {
              that.userStore.idNum = that.registerQuery.idNum;
              that.userStore.password = that.registerQuery.password;
              that.userStore.userName = that.registerQuery.userName;
              that.userStore.phone = that.registerQuery.phone;
              that.userStore.isLogined = true;
              that.$emit('sendBack');
            }
          });
      } else {
        that.disabled = true;
        setTimeout(() => {
          that.disabled = false
        }, 1000)
      }
    },
  },
}
</script>

<style scoped>
@import '../layouts/Animation.css';

div.wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 4;
  background-color: rgb(110, 193, 193);
}

.form-container {
  display: grid;
  width: 500px;
  height: 340px;
  /* 5行，每行50px */
  grid-template-columns: 100px 240px 140px;
  gap: 20px;
  /* 可选，设置列间距 */

}

.form-row {
  display: contents;
  justify-content: center;
  align-items: center;
}

div.align-left {
  text-align: left;
  margin: auto;
}

.errorInput {
  background-color: tomato;
}

label {
  display: flex;
}

label.error {
  color: rgb(164, 51, 51);
}

img {
  width: 50px;
}

img:not(.icon) {
  width: 20px;
  margin-top: 10px;
}
</style>