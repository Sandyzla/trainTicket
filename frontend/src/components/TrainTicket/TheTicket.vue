<template>
  <div class="ticket_flex">
    <div v-for="ticket in ticketsView" :key="ticket.orderId + ticket.idNum" class="ticket-card">
      <div class="ticket-header">
        <span class="train-id">{{ ticket.trainId }}</span>
        <span class="price">￥{{ ticket.price }}</span>
      </div>
      <div class="ticket-body">
        <div class="station-info">
          <div class="from">
            <p class="station">{{ ticket.staFrom }}</p>
            <p class="time">{{ ticket.tFrom.substring(0, 5) }}</p>
          </div>
          <p></p>
          <div>

            <p class="duration">（历时{{ ticket.duration }}）</p>
          </div>

          <div class="to">
            <p class="station">{{ ticket.staTo }}</p>
            <p class="time">{{ ticket.tTo.substring(0, 5) }}</p>
          </div>
        </div>
        <div class="info-row">
          <div class="seat-info">
            <p>{{ ticket.carr }}车 {{ ticket.sRow }}{{ ticket.sCol }} {{ ticket.seatType }}</p>
          </div>
          <div class="passenger-info">
            <p>乘车人: {{ ticket.passengerName }}</p>
          </div>
        </div>
        <p class="small-text">所属订单: {{ ticket.orderId }}</p>
      </div>
    </div>
    <div v-if="ticketsView.length == 0" class="content">还没有车票，刷新试试？</div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  props: {
    can_refund: Boolean,
    can_use: Boolean,
    axiosPath: String, //axios请求路径
    param: String, //axios请求参数 
  },
  mounted() {
    var that = this;
    axios({
      method: 'post',
      url: this.userStore.url + this.axiosPath,
      headers: {
        'Content-Type': 'text/plain' //传字符串需要这个
      },
      data: this.param
    })
      .then(function (response) {
        that.ticketsView = response.data.data;

      });
  },
  data() {
    return {
      ticketsView: []
    }
  },
  methods: {
    // refundTicket(index) {
    //   this.tickets.at(index).trainId;
    //   var that = this;
    //   that.tickets.at(index).idNum
    //   axios.post('http://localhost:8888/refundTicket', {
    //     idNum: that.tickets.at(index).idNum,
    //     trainId: that.tickets.at(index).trainId
    //   }).then(function () {
    //   })
    // }
  },



}
</script>
<style scoped>
.ticket_flex {
  display: flex;
  flex-direction: row;
}

.ticket-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  width: 300px;
  max-width: 100%;
  margin: 5px auto;
}

.ticket-header {
  display: flex;
  justify-content: space-between;
  border-bottom: 1px solid #ddd;
  padding-bottom: 5px;
  margin-bottom: 5px;
}

.train-id {
  font-size: 1.3em;
  font-weight: bold;
}

.price {
  font-size: 1.1em;
  color: #e74c3c;
}

.ticket-body {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.station-info {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.from,
.to {
  text-align: center;
}

.label {
  font-size: 0.9em;
  color: #7f8c8d;
  margin-bottom: 5px;
}

.time,
.station {
  font-size: 1em;
  color: #34495e;
}

.info-row {
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin-top: 10px;
}

.seat-info {
  font-size: 1em;
}

.passenger-info {
  font-size: 1em;
  text-align: right;
}

.small-text {
  font-size: 0.8em;
  color: #7f8c8d;
  width: 100%;
  text-align: center;
}

p {
  margin: 0px;
}

.duration {
  line-height: 40px;
  font-size: 13px;
  color: #989898;
}
</style>