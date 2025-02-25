arrowtab<template>
  <div>
    <div class="query">
      <div>
        <input type="text" id="from" name="from" placeholder="出发" v-model="query.from">
      </div>
      <div @click="reverseFromTo">
        <img class="arrow" src="../../assets/TrainTicket/arrow.png" />
      </div>
      <div>
        <input type="text" id="to" name="to" placeholder="到达" v-model="query.to">
      </div>
      <base-button @click="queryData">查询</base-button>
    </div>
    <div v-if="afterFilteredTrains.length > 0" class="result">
      <input type="text" v-model="search" class="filterinput" @input="filterTable" placeholder="搜索车次...">
      <table>
        <thead>
          <tr>
            <th @click="sortTable('trainId')" :class="['clickable', sortKey === 'trainId' ? sortOrder : '']">车次
              <span v-if="sortKey === 'trainId'"></span>
            </th>
            <th>行程</th>
            <th>始发</th>
            <!-- <th>终点</th> -->
            <th @click="sortTable('tGo')" :class="['clickable', sortKey === 'tGo' ? sortOrder : '']">开点
              <span v-if="sortKey === 'tGo'"></span>
            </th>
            <th @click="sortTable('tArrive')" :class="['clickable', sortKey === 'tArrive' ? sortOrder : '']">到点
              <span v-if="sortKey === 'tArrive'"></span>
            </th>
            <!-- <th>类型</th> -->
            <th @click="sortTable('duration')" :class="['clickable', sortKey === 'duration' ? sortOrder : '']">时间
              <span v-if="sortKey === 'duration'"></span>
            </th>
            <th>商务座</th>
            <th>一等座</th>
            <th>二等座</th>
            <th>详情</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(train, index) in afterFilteredTrains" :key="train.trainId">
            <td>{{ train.trainId }}</td>
            <td>
              <div class="td_up">
                {{ train.staFrom }}
              </div>
              <div class="td_bottom">
                {{ train.staTo }}
              </div>
            </td>
            <td v-if="train.noFrom == 1">始发</td>
            <td v-else></td>
            <td>{{ train.tGo.substring(0, 5) }}</td>
            <td>{{ train.tArrive.substring(0, 5) }}</td>
            <!-- <td>{{ train.type }}</td> -->
            <td>{{ formattedTime(train.duration) }}</td>
            <td>
              <div v-if="train.seatMap['商务座'].num < 0"></div>
              <div class="td_up" v-else-if="train.seatMap['商务座'].num == 0">无票</div>
              <div class="td_up" v-else>{{ train.seatMap['商务座'].num }}张</div>
              <div class="td_bottom" v-if="train.seatMap['商务座'].num >= 0">￥{{ train.seatMap['商务座'].price }}</div>
              <div class="td_bottom" v-else></div>
            </td>
            <td>
              <div v-if="train.seatMap['一等座'].num < 0"></div>
              <div class="td_up" v-else-if="train.seatMap['一等座'].num == 0">无票</div>
              <div class="td_up" v-else>{{ train.seatMap['一等座'].num }}张</div>
              <div class="td_bottom" v-if="train.seatMap['一等座'].num >= 0">￥{{ train.seatMap['一等座'].price }}</div>
              <div class="td_bottom" v-else></div>
            </td>
            <td>
              <div v-if="train.seatMap['二等座'].num < 0"></div>
              <div class="td_up" v-else-if="train.seatMap['二等座'].num == 0">无票</div>
              <div class="td_up" v-else>{{ train.seatMap['二等座'].num }}张</div>
              <div class="td_bottom" v-if="train.seatMap['二等座'].num >= 0">￥{{ train.seatMap['二等座'].price }}</div>
              <div class="td_bottom" v-else></div>
            </td>
            <td><button @click="gotoTrainDetailPage(index)" class="detailBut">购票</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {

  // activated() {
  //   console.log('组件已激活');
  // },
  // deactivated() {
  //   console.log('组件已停用');
  // },
  data() {
    return {
      query: { from: '温州南', to: '宁波' },
      trains: [], //从服务器返回的完整数据
      search: '',
      afterFilteredTrains: [], //呈现(筛选后)的数据
      sortKey: 'tGo',
      sortOrder: 'asc',
    }

  },
  methods: {

    filterTable() {
      let tmpTrains = this.trains.filter(train =>
        Object.values(train).some(value =>
          String(value).toLowerCase().includes(this.search.toLowerCase())
        )
      );
      this.afterFilteredTrains = tmpTrains;
    },
    sortTrains() {
      if (this.sortKey) {
        this.afterFilteredTrains.sort((a, b) => {
          let result = 0;
          if (a[this.sortKey] < b[this.sortKey]) result = -1;
          if (a[this.sortKey] > b[this.sortKey]) result = 1;
          return this.sortOrder === 'asc' ? result : -result;
        });
      }
    },
    sortTable(key) {
      if (this.sortKey === key) {
        this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
      } else {
        this.sortKey = key;
        this.sortOrder = 'asc';
      }
      this.sortTrains(); // 调用排序方法
    },
    queryData() {
      var that = this;
      axios.post('http://localhost:8888/fromTo', this.query).then(function (response) {
        if (that.trains.length == 0) {
          that.trains = response.data;
          that.afterFilteredTrains = response.data;
        }
        else {
          that.trains = [];
          that.afterFilteredTrains = [];
          setTimeout(function () {
            that.trains = response.data;
            that.afterFilteredTrains = response.data;
          }, 300);
        }
      })
    },
    reverseFromTo() {
      const tmp = this.query.from;
      this.query.from = this.query.to;
      this.query.to = tmp;
    },
    gotoTrainDetailPage(index) { //路由并传参到train_detail,
      this.$router.push({
        name: 'train_detail',
        state: {
          trainId: this.afterFilteredTrains.at(index).trainId,
          noFrom: this.afterFilteredTrains.at(index).noFrom,
          noTo: this.afterFilteredTrains.at(index).noTo,
          tGo: this.afterFilteredTrains.at(index).tGo, //离开出发站时刻
          tArrive: this.afterFilteredTrains.at(index).tArrive, //到达目的站时刻
          staFrom: this.afterFilteredTrains.at(index).staFrom, //出发站
          staTo: this.afterFilteredTrains.at(index).staTo, //到达站
          seatMap: JSON.stringify(this.afterFilteredTrains.at(index).seatMap), //余票信息(map<string, object>需要先转json，接收后再恢复)
        }
      });
    },
    formattedTime(totalMinutes) {
      const hours = Math.floor(totalMinutes / 60);
      const minutes = totalMinutes % 60;
      return `${hours}小时${minutes}分钟`;
    }
  },


}
</script>

<style scoped>
@import '../layouts/Animation.css';

input {
  padding: 5px;
  font-size: 20px;
  height: 40px;
  width: 110px;
  display: block;
  text-align: center;
  /* margin: 0 auto; */
  outline: none;
}


.detailBut {
  color: rgb(44, 138, 205);
  cursor: pointer;
  border: none;
  font-size: 14px;
  background-color: white;
}

.detailBut:hover {
  color: #10700d;
}

.query {
  background-color: rgb(91, 91, 91);
  top: 60px;
  /* left: 38px; */
  width: 100%;
  display: flex;
  margin-bottom: 20px;
  align-items: center;
  justify-content: center;
}

.filterinput {
  border: none;
  height: 25px;
  width: 280px;
  margin-bottom: 4px;
  background-color: rgb(223, 223, 223);

}

.result {
  display: flex;
  flex-direction: column;
  margin: auto;
  width: 1000px;
}

.icon {
  width: 40px;
}

div.seat {
  font-size: 16px;
  font-weight: 800;
  color: rgba(56, 164, 74, 0.836);
}

div.noSeat {
  font-size: 16px;
  color: #666
}

.arrow {
  width: 30px;
  margin-left: 10px;
  margin-right: 10px;
}

table {
  border-collapse: collapse;
}

th,
td {
  border: 1px solid #ddd;
  padding-top: 3px;
  padding-bottom: 3px;
  text-align: center;
  /* 使表格元素居中对齐 */
}

th {
  font-size: 14px;
}

th.clickable {
  background-color: #f2f2f2;
  cursor: pointer;
}

th.clickable:hover {
  background-color: #ddd;
}

th.asc::after {
  content: '↑';
  color: #10700d;
}

th.desc::after {
  content: '↓';
  color: #10700d;
}

div.td_up {
  font-weight: 500;
  color: #666;
  font-size: 15px;

}

div.td_bottom {
  color: #415292;
  font-size: 15px;
  font-family: 'Rock Salt', cursive;
}

/* 使用:nth-child选择器设置列宽 */
th:nth-child(1),
td:nth-child(1) {
  width: 150px;
}

th:nth-child(2),
td:nth-child(2) {
  width: 100px;
}

th:nth-child(3),
td:nth-child(3) {
  width: 50px;
}

th:nth-child(4),
td:nth-child(4) {
  width: 75px;
}

th:nth-child(5),
td:nth-child(5) {
  width: 75px;
}

th:nth-child(6),
td:nth-child(6) {
  width: 150px;
}

th:nth-child(7),
td:nth-child(7),
th:nth-child(8),
td:nth-child(8),
th:nth-child(9),
td:nth-child(9) {
  width: 100px;
}

th:nth-child(10),
td:nth-child(10) {
  width: 100px;
}
</style>