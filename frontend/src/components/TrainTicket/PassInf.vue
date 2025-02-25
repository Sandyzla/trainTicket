<template>
    <div class="container">
      <table>
        <thead>
          <tr>
            <td>站名</td>
            <td>到时</td>
            <td>发时</td>
            <td>停留</td>
          </tr>
        </thead>
        <tr v-for="l in passList" :key="l.no" :class="passStyle(l.no)">
          <td>{{ l.staName }}</td>
          <td v-if="l.no == 1">----</td>
          <td v-if="l.no > 1">{{ l.tArrive.substring(0, 5) }}</td>
          <td v-if="l.no == passList.length">----</td>
          <td v-if="l.no < passList.length">{{ l.tGo.substring(0, 5) }}</td>
          <td>{{ l.tMid }}</td>
        </tr>
      </table>
    </div>
</template>

<script>
import axios from 'axios';
export default {
  props: {
    passInfQuery: {}
  },
  mounted() {
    var that = this;
    axios.post(this.userStore.url+'/timeListPass', this.passInfQuery).then(function (response) {
      that.passList = response.data;
    })
  },
  data() {
    return {
      passList: [],
    }
  },
  methods: {
    passStyle(i) {
      if (i < this.passInfQuery.noFrom || i > this.passInfQuery.noTo) return 'notPass';
      else if (i == this.passInfQuery.noFrom || i == this.passInfQuery.noTo) return 'fromTo';
      else return 'isPass';
    }

  },



}
</script>

<style scoped>

div.container {
  overflow-y: auto;
  display: block;
  max-height: 400px;
}

table {
  width: 90%;
  margin: auto;
}

thead {
  font-style: italic;
  color: rgb(16, 77, 105);
}

tr {
  list-style: none;
  font-size: 14px;
  margin: auto;
}

tr.isPass {
  color: rgb(0, 0, 0);
}

tr.notPass {
  color: rgb(151, 138, 138)
}

tr.fromTo {
  color: rgb(0, 0, 0);
  font-weight: 600;
}

div.body {
  margin: 0;
  display: inline;
}

div.bottom {
  display: block;
  text-align: center;
}
</style>
