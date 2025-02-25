<template>
  <div class="layout">
    <div class="head">
      <h2>管理员首页</h2>
    </div>
    <div class="nav">
      <h3>座位管理</h3>
      <div class="imgContainer">
        <img class="imgbut" @click="backLogin" src="../../assets/TrainTicket/back.png">
      </div>
    </div>
    <div class="main">
      <div class="query">
        <label for="trainId">车次号</label>
        <input class="train" :class="{ lockinput: isLocked }" id="trainId" v-model="trainId" ref="inputRef" />
        <img class="imgbut" v-show="isLocked" @click="unlock" src="../../assets/TrainTicket/locked.png" />
        <img class="imgbut" v-show="!isLocked" @click="fetchData" :class="{ shake: search_disabled }"
          src="../../assets/TrainTicket/unlock.png" />
      </div>
      <div class="error" v-if="search_showError">{{ search_errorMes }}</div>
      <!-- {{ query }} -->
      <div class="carr_container">
        <div class="imgContainer" v-if="carrs.length == 0 || carrs[0] > 1"> <!-- 第一个add按钮 -->
          <img class="imgbut" src="../../assets/TrainTicket/add.png" @click="addOneCarr(0)" />
        </div>
        <TransitionGroup name="fade">
          <div v-for="(l, index) in query" :key="l.carr" class="carrAndAdd">
            <div class="carr">
              <div class="carr_num">{{ l.carr }}车</div>
              <!-- <div>{{ l.seatType }}</div> -->
              <select id="pet-select" name="seatType" class="seat_type" v-model="seatTypes[index]">
                <option value="商务座">商务</option>
                <option value="一等座">一等</option>
                <option value="二等座">二等</option>
              </select>

              <div class="imgContainer">
                <img class="imgbut" src="../../assets/TrainTicket/delete.png" @click="deleteCarr(index)" />
              </div>
              <!-- <div class="row">{{ l.sRow }}排</div> -->
              <div class="row">
                <input class="inrow" v-model="sRows[index]" type="number" min="1" max="10">排
              </div>
              <div v-if="seatTypes[index] === '商务座'" class="seat_num">定员:{{ sRows[index]* 3 }}人</div>
              <div v-if="seatTypes[index] === '一等座'" class="seat_num">定员:{{ sRows[index]* 4 }}人</div>
              <div v-if="seatTypes[index] === '二等座'" class="seat_num">定员:{{ sRows[index]* 5 }}人</div>
            </div>
            <div class="imgContainer" v-if="!isCarrJoin(index)">
              <img class="imgbut" src="../../assets/TrainTicket/add.png" @click="addOneCarr(l.carr, index)" />
            </div>
          </div>
        </TransitionGroup>


      </div>
      <Transition name="fade">
        <div v-if="successUpdate" class="successLayer">
          <img class="succeddImg" src="../../assets/TrainTicket/success.png" />
        </div>
      </Transition>
      <base-button @click="clearQuery">清空编辑</base-button>
      <base-button @click="reset">重置</base-button>
      <base-button @click="updateSeatCarr" :class="{ shake: update_disabled }">更新数据</base-button>
      <base-button class="but" @click="clearDataBase">清空该车次在数据库的座位数据</base-button>
      <div class="error" v-if="update_showError">{{ update_errorMes }}</div>
    </div>
  </div>
</template>


<script>
import axios from 'axios'
export default {
  data() {
    return {
      trainId: "G590/G591",
      carriageData: [],//从fetchData后端查询车次获取的数据
      query: [], //carriageData的拷贝数据,作为编辑时的临时数据
      seatTypes: [],
      carrs: [],
      sRows: [],
      search_disabled: false,
      search_showError: false,
      search_errorMes: '',
      update_disabled: false,
      update_showError: false,
      update_errorMes: '',
      isLocked: false,
      successUpdate: false,
    }
  },
  methods: {
    fetchData() {
      var that = this;
      axios({
        method: 'post',
        url: 'http://localhost:8888/admin/searchSeat',
        headers: {
          'Content-Type': 'text/plain' //传字符串需要这个
        },
        data: this.trainId
      })
        .then(function (response) {
          if (response.data.code === 'error_1' || response.data.code === 'error_2') { //未找到车次信息
            that.search_disabled = true;
            that.search_errorMes = response.data.messege;
            that.search_showError = true;
            setTimeout(() => {
              that.search_disabled = false
            }, 1000)
          }
          else if (response.data.code === 'tip_1') { //数据库空数据,需要在管理员界面编辑
            that.isLocked = true;
            that.$refs.inputRef.disabled = true;
            that.search_errorMes = response.data.messege;
            that.search_showError = true;
          }
          else {
            that.isLocked = true;
            that.$refs.inputRef.disabled = true;
            that.carriageData = response.data.data;
            that.query = that.carriageData;// 拷贝数据,便于回档?
            that.search_showError = false;
            for (let i = 0; i < that.query.length; i++) {
              that.carrs[i] = that.query[i].carr;
              that.seatTypes[i] = that.query[i].seatType;
              that.sRows[i] = that.query[i].sRow;
            }
          }
        });
    },
    saveSeatCarr() {
      if (!this.checkCanEdit()) return;
      for (let i = 0; i < this.query.length; i++) {
        this.query[i].carr = this.carrs[i];
        this.query[i].seatType = this.seatTypes[i];
        this.query[i].sRow = this.sRows[i];
      }
    },
    updateSeatCarr() {
      if (!this.checkCanEdit()) return;
      this.saveSeatCarr();
      var that = this;
      axios.post('http://localhost:8888/admin/rectifySeat', this.query).then(function (response) {
        if (response.data.code === 'success') {
          that.showSuccess();
        } else {
          that.update_disabled = true;
          that.update_errorMes = response.data.messege;
          that.update_showError = true;
          setTimeout(() => {
            that.update_disabled = false
          }, 1000)
        }
      });
    },
    addOneCarr(carr, index) {
      if (!this.checkCanEdit()) return;
      this.query.splice(index + 1, 0, { trainId: this.trainId, carr: carr + 1, seatType: "二等座", sRow: 1 });
      //为了v-model能及时显示默认数据,需要也为三个数组赋默认值
      this.carrs.splice(index + 1, 0, carr + 1);
      this.seatTypes.splice(index + 1, 0, "二等座");
      this.sRows.splice(index + 1, 0, 1);
      this.search_showError = false;
      this.update_showError = false;
    },
    isCarrJoin(index) { //检查车厢是否相邻,若相邻则v-if不显示add按钮
      if (index == this.carrs.length) return false;
      return (this.carrs[index + 1] == this.carrs[index] + 1);
    },
    deleteCarr(index) {
      if (!this.checkCanEdit()) return;
      this.query.splice(index, 1);
      this.seatTypes.splice(index, 1);
      this.carrs.splice(index, 1);
      this.sRows.splice(index, 1);
    },
    clearDataBase() {
      if (!this.checkCanEdit()) return;
      axios({
        method: 'post',
        url: 'http://localhost:8888/admin/clearSeatCarr',
        headers: {
          'Content-Type': 'text/plain' //传字符串需要这个
        },
        data: this.trainId
      });
      this.showSuccess();
    },
    clearQuery() {
      if (!this.checkCanEdit()) return;
      this.query = [];
      this.seatTypes = [];
      this.carrs = [];
      this.sRows = [];
    },
    unlock() {
      this.query = [];
      this.seatTypes = [];
      this.carrs = [];
      this.sRows = [];
      this.isLocked = false;
      this.$refs.inputRef.disabled = false;
      this.search_showError = false;
      this.update_showError = false;
    },
    checkCanEdit() {
      if (this.isLocked == false) {
        this.search_disabled = true;
        this.search_errorMes = "需要先确认车次号";
        this.search_showError = true;
        setTimeout(() => {
          this.search_disabled = false
        }, 1000)
        return false;
      } else
        return true;
    },
    reset() {
      if (!this.checkCanEdit()) return;
      this.fetchData();
    },
    showSuccess() {
      this.successUpdate = true;
      setTimeout(() => {
        this.successUpdate = false;
      }, 2000)
    },
    backLogin() {
      this.$router.replace({ path: '/' });
    }
  },
}
</script>

<style scoped>
/* @import './layouts/Animation.css'; */

.layout {
  margin-top: 20px;
  display: grid;
  background-color: rgba(252, 252, 252, 0.555);
  widows: 100%;
  height: 100%;
  grid-template: "head head" 60px
    "nav main" 1fr/161px 1fr
}

.layout>div {

  border: 4px dashed rgba(186, 186, 160, 0.555);
}

.head {
  grid-area: head;
  margin-bottom: -4px;
}

.nav {
  grid-area: nav;
  padding: 20px;
  margin: 0px -4px 0px 0px;
}

.main {
  padding: 20px;
  grid-area: main;
}

.query {
  display: flex;
  align-items: center;
}

input.train {
  width: 170px;
  height: 40px;
  text-align: center;
  font-size: 20px;
  margin: 10px;
  border-width: 0px;
  border-bottom: 2px solid rgb(53, 53, 53);
}

.lockinput {
  background-color: rgb(181, 179, 179);
}

input.inrow,
select {
  width: 70px;
  height: 30px;
  text-align: center;
  font-size: 20px;
  border-width: 0px;
  border-bottom: 2px solid rgb(53, 53, 53);
  font-family: 'Rock Salt', cursive;
}

div.carr_container {
  padding: 20px;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  /* justify-content: center; */
}

div.carr {
  display: grid;
  grid-template: "carr_num seat_type delete_carr" 1fr
    "row seat_num seat_num" 1fr/100px 80px 1fr;
  width: 240px;
  height: 100px;
  border: 4px dashed rgba(53, 90, 95, 0.801);
  border-radius: 8px;
  /* background-color: rgb(152, 211, 246); */
  font-size: 20px;
  padding: 10px 10px 10px 10px;
  font-family: 'Rock Salt', cursive;
  text-align: center;
  justify-content: center;
  align-items: center;
}

div.carr_num {
  grid-area: carr_num;
}

div.seat_type {
  grid-area: seat_type;
}

div.delete_carr {
  grid-area: delete_carr;
}

div.row {
  grid-area: row;

}

div.seat_num {
  grid-area: seat_num;
}

.imgContainer {
  display: inline-flex;
  height: fit-content;
  width: fit-content;
}

.imgbut:hover {
  background-color: rgb(201, 201, 201);
}

.imgbut {
  width: 40px;
}

div.error {
  color: brown;
}

.fade-move,
.fade-enter-active,
.fade-leave-active {
  transition: all 0.2s cubic-bezier(0.55, 0, 0.55, 1);
}

/* 2. 声明进入和离开的状态 */
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: scaleY(0.01) translate(30px, 0);
}

/* 3. 确保离开的项目被移除出了布局流
      以便正确地计算移动时的动画效果。 */
.fade-leave-active {
  position: absolute;
}

.carrAndAdd {
  display: flex;
  justify-content: center;
  align-items: center;
}

.successLayer {
  z-index: 888;
  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  left: 47%;
  top: 30%;
  width: 10%;
  height: 20%;
}

.succeddImg {
  width: 90px;
}

.but {
  border-radius: 10%/80%;
}
</style>