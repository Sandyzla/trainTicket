<template>
    <div class="container">
        <RemainTicket :remain-ticket-query="remainTicketQuery"></RemainTicket>
        <div class="header-content">
            <div  class="header">
                经停信息
                <button v-if="!isShowPassInfo" @click="TogglePassInfPage">+</button>
                <button v-if="isShowPassInfo" @click="TogglePassInfPage">-</button>
            </div>
            <div class="content">
                <PassInf :pass-inf-query="passInfQuery" v-show="isShowPassInfo" ref="passInfRef" />
            </div>
        </div>

        <TicketConsider :ticket-consider-query="ticketConsiderQuery"></TicketConsider>
        <base-button @click="returnToQuery">返回查询页面</base-button>
    </div>
</template>

<script>
import PassInf from './PassInf.vue'
import RemainTicket from './RemainTicket.vue';
import TicketConsider from './TicketConsider.vue';
export default {
    created() {
        this.passInfQuery = {
            trainId: history.state.trainId,
            noFrom: history.state.noFrom,
            noTo: history.state.noTo
        };
        this.ticketConsiderQuery = {        
            trainId: history.state.trainId,
            noFrom: history.state.noFrom,
            noTo: history.state.noTo,
            seatMap: history.state.seatMap,
        };
        this.remainTicketQuery = {
            trainId: history.state.trainId,
            staFrom: history.state.staFrom,
            staTo: history.state.staTo,
            tGo: history.state.tGo,
            tArrive: history.state.tArrive,
            seatMap: history.state.seatMap,
        }
    },
    data() {
        return {
            isShowPassInfo: false,
            passInfQuery: {},
            ticketConsiderQuery: {},
            remainTicketQuery: {}
        }
    },
    methods: {
        returnToQuery() {
            this.$router.go(-1)
        },
        TogglePassInfPage() {
            this.isShowPassInfo = !this.isShowPassInfo;
        }
    },
    components: {
        PassInf,
        TicketConsider,
        RemainTicket
    },
}
</script>

<style scoped>
.container {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}
</style>