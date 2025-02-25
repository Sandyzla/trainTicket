<template>
  <div>
    <div :key="order.orderId" v-for="(order, index) in orders" class="content">
    <div class="order">
      <div class="order_header">
        <span class="train-id">{{ order.trainId }}</span>
        <span class="price">价格: ￥{{ order.price }}</span>
        <span v-if="order.state == 'submitted'" class="state">待支付</span>
        <span v-else class="state">已支付</span>
      </div>
      <div class="details">
        <p>开点:{{ order.tFrom.substring(0, 5) }}-{{ order.tTo.substring(0, 5) }}（历时{{ order.duration }}） {{ order.staFrom
        }}-{{ order.staTo }}</p>
        <p>创建时间: {{ order.createTime }}</p>
      </div>
    </div>
    <div class="but_div">
      <base-button @click="gotoOrderDetail(index)">查看</base-button>
    </div>
   
  </div>
  <div v-if="orders.length == 0" class="content">还没有订单，刷新试试？</div>
  </div>
 
</template>

<script>
import axios from 'axios';
export default {

  watch: {
    $route() {
      // 根据路由参数重新获取数据
      this.fetchData();
    }
  },
  mounted() {
    this.fetchData();
  },
  data() {
    return {
      orders: [],
      order_state: '',
    }
  },
  methods: {
    gotoOrderDetail(index) {
      let route_name = '';
      if (this.order_state == 'submitted') {
        route_name = 'order_submitted_detail';
      } else {
        route_name = 'order_paid_detail';
      }
      this.$router.push({
        name: route_name,
        state: {
          orderId: this.orders[index].orderId,
          userName: this.orders[index].userName,
          createTime: this.orders[index].createTime,
          state: this.orders[index].state,
          price: this.orders[index].price,
          trainId: this.orders[index].trainId,
          staFrom: this.orders[index].staFrom,
          tFrom: this.orders[index].tFrom,
          staTo: this.orders[index].staTo,
          tTo: this.orders[index].tTo,
          duration: this.orders[index].duration,
          order_state: this.order_state,
        }
      });
    },
    fetchData() {
      if (history.state.axiosPath == '/order_submitted') {
        this.order_state = 'submitted';
      } else {
        this.order_state = 'paid';
      }
      var that = this;
      axios({
        method: 'post',
        url: this.userStore.url + history.state.axiosPath,
        headers: {
          'Content-Type': 'text/plain' //传字符串需要这个
        },
        data: this.userStore.idNum
      })
        .then(function (response) {
          that.orders = response.data.data;
        });
    },

  },



}
</script>

<style scoped>
div.order {
  margin: 10px;
  color: rgb(117, 117, 117);
}

.order {
  padding-bottom: 5px;
}

.order_header {
  display: flex;
  justify-content: space-between;
  margin-top: 5px;
}

.train-id {
  font-size: 1.3em;
  font-weight: bold;
}

.state {
  font-size: 1.3em;
  color: #fa8a8a;
  font-weight: 600;
  /* background-color: white; */
}

.details {
  margin-top: 5px;
}

.details p {
  line-height: 1.6;
  /* color: #34495e; */
  margin: 5px 0;
}

.price {
  font-weight: 600;
  font-size: 1.3em;
}

.label,
p {
  margin: 10px;
}

div.but_div {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 10px;
    margin-bottom: 10px;
}
</style>