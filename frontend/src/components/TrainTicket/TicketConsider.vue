<template>
  <div class="header-content">
    <base-dialog v-if="error_mes">
      <template #header>
        购票提示
      </template>
      <template #content>
        {{ error_mes }}
      </template>
      <template #button>
        <base-button @click="closeDialog">好的</base-button>
      </template>
    </base-dialog>
    <div class="header">
      订单信息
    </div>
    <div class="content">
      <div v-if="!first_exist_seatType && userStore.isLogined" class="loginWarn">
        <p><b>本车次无余票，请联系管理员</b></p>
      </div>
      <div v-if="!userStore.isLogined" class="loginWarn">
        <p><b>您需要登录后才能购票</b></p>
      </div>
      <div v-show="userStore.isLogined && first_exist_seatType">

        <label>
          <input type="checkbox" v-model="isAddSelf" style="zoom:130%; vertical-align:middle;"
            @change="toggleSelfOrder" />
          为自己添加记录
        </label>
        <base-button @click="addOrder">添加他人记录</base-button>
        <label v-if="totalPrice > 0">总计￥<b>{{ totalPrice }}</b></label>
        <table v-if="orders.length > 0">

          <tbody>
            <tr v-for="(order, index) in orders" :key="index">
              <td>{{ index + 1 }}</td>
              <td>
                <select v-model="order.seatType" @change="updatePrice(index)">
                  <option v-for="(data, seatType) in seatData" :key="seatType" :value="data[0]">
                    {{ data[0] }}
                  </option>
                </select>
              </td>
              <td>￥{{ order.price }}</td>
              <td><input v-model="order.userName" type="text" placeholder="姓名" class="nameInput" /></td>
              <td>
                <select v-model="order.idType">
                  <option value="居民身份证">居民身份证</option>
                </select>
              </td>
              <td><input v-model="order.idNum" type="text" placeholder="证件号码" /></td>
              <td><base-button @click="removeOrder(index)">删除</base-button></td>
            </tr>
          </tbody>
        </table>
        <div class="formButtonDiv">
          <base-button v-if="orders.length > 0" @click="buyTicket">提交</base-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  props: {
    ticketConsiderQuery: {}
  },
  created() {
    const seatDataObject = JSON.parse(this.propData.seatMap);
    const seatDataMap = new Map(Object.entries(seatDataObject));
    this.seatData = new Map([...seatDataMap].filter(([key, value]) => value.num > 0 && key != ''));
    if (this.seatData.size > 0) {
      this.first_exist_seatType = this.seatData.keys().next().value;
    }
    this.query = {
      requestIdNum: this.userStore.idNum,
      trainId: this.propData.trainId,
      noFrom: this.propData.noFrom,
      noTo: this.propData.noTo,
      lists: [],
    };
  },
  data() {
    return {
      propData: { ...this.ticketConsiderQuery },
      seatData: new Map(),
      query: {},
      isAddSelf: false,
      orders: [],
      first_exist_seatType: '',
      error_mes: '',
    }
  },
  computed: {
    // 计算 totalPrice
    totalPrice() {
      return this.orders.reduce((acc, order) => acc + order.price, 0);
    },
  },
  methods: {
    addOrder() {
      this.orders.push({
        idType: '居民身份证',
        idNum: '',
        userName: '',
        seatType: this.first_exist_seatType,
        price: this.seatData.get(this.first_exist_seatType).price,
      });
    },
    removeOrder(index) {
      if (this.isAddSelf && index == 0) {
        this.isAddSelf = false;
      }
      this.orders.splice(index, 1);
    },
    toggleSelfOrder() {
      if (this.isAddSelf) {
        this.orders.unshift({
          idType: '居民身份证',
          idNum: this.userStore.idNum,
          userName: this.userStore.userName,
          seatType: this.first_exist_seatType,
          price: this.seatData.get(this.first_exist_seatType).price,
        });
      } else {
        this.orders.splice(0, 1);
      }
    },
    buyTicket() {
      for (let i = 0; i < this.orders.length; i++) {
        if (!this.validateIdNum(this.orders[i].idNum) || !this.validateName(this.orders[i].userName)) {
          this.error_mes = '输入格式有误，请检查身份证或姓名';
        }
      }
      if (!this.error_mes) {
        this.query.lists = [];
        this.query.lists.push(this.orders.filter(order => order.seatType === '商务座'));
        this.query.lists.push(this.orders.filter(order => order.seatType === '一等座'));
        this.query.lists.push(this.orders.filter(order => order.seatType === '二等座'));

        var that = this;
        axios.post(this.userStore.url + '/order_submit', this.query).then(function (response) {
          if (response.data.code != 'success') {
            that.error_mes = response.data.message;
          }
          else {
            that.error_mes = '成功购票，请前往订单页面支付';
          }
        });
      }

    },
    updatePrice(index) {
      this.orders[index].price = this.seatData.get(this.orders[index].seatType).price;
    },
    validateIdNum(idNum) {
      const regex = /^[1-9]\d{5}(18|19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])\d{3}(\d|X)$/i;
      return regex.test(idNum);
    },
    validateName(name) {
      const regex = /^[\u4e00-\u9fa5]{2,4}$/;
      return regex.test(name);
    },
    closeDialog() {
      if (this.error_mes == '成功购票，请前往订单页面支付') {
        this.$router.push({
          name: 'order',
          // state: {
          //     axiosPath: '/' + this.selectedTab
          // }
        })
      }
      this.error_mes = '';

    }
  }
}
</script>

<style scoped>
.container {
  /* max-width: 30%; */
  margin: auto;
  /* margin-top: 50px; */
  width: 600px;
  height: 400px;
  /* padding-top: 30px; */
}

.loginWarn {
  color: brown
}

input,
select,
label {
  font-size: 14px;
}

th,
td {
  text-align: center;
}

.nameInput {
  width: 90px;
}

.formButtonDiv {
  margin-top: 10px;
  padding-top: 10px;
  border-top: 1px solid rgb(91, 91, 91);
  text-align: center;
}

select {
  border-bottom: 2px solid rgb(90, 90, 90);
  background-color: white;
  height: 30px;
  border-radius: 5px;
  vertical-align: middle;
}

label {
  height: 30px;
  vertical-align: middle;
}
</style>
