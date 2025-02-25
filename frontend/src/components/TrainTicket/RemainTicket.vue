<template>
    <div class="header-content">
        <div class="header">
            <i>{{ propData.trainId }}</i>余票信息 
        </div>
        <div class="content">
            <span class="time_go">{{ propData.tGo.substring(0, 5) }}</span><i>开 - </i>
            <span class="time_arrive">{{ propData.tArrive.substring(0, 5) }}到</span>
            <span class="station"> &#40;{{ propData.staFrom }}->{{ propData.staTo }}&#41;</span>
            <div class="ticketCard">
                <div class="ticket"> 商务座
                    <div v-if="seatData.get('商务座').num > 0">{{ seatData.get('商务座').num }}张</div>
                    <div v-else>无票</div>
                    <div v-if="seatData.get('商务座').num >= 0">￥{{ seatData.get('商务座').price }}</div>
                </div>
            </div>
            <div class="ticketCard">
                <div class="ticket"> 一等座
                    <div v-if="seatData.get('一等座').num > 0">{{ seatData.get('一等座').num }}张</div>
                    <div v-else>无票</div>
                    <div v-if="seatData.get('一等座').num >= 0">￥{{ seatData.get('一等座').price }}</div>
                </div>
            </div>
            <div class="ticketCard">
                <div class="ticket"> 二等座
                    <div v-if="seatData.get('二等座').num > 0">{{ seatData.get('二等座').num }}张</div>
                    <div v-else>无票</div>
                    <div v-if="seatData.get('二等座').num >= 0">￥{{ seatData.get('二等座').price }}</div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
// import axios from 'axios';
export default {
    props: {
        remainTicketQuery: {}
    },
    created(){
        const parsedSeatMap = JSON.parse(this.propData.seatMap);
        this.seatData = new Map(Object.entries(parsedSeatMap));
    },
    data() {
        return {
            propData: { ...this.remainTicketQuery },
            seatData: new Map()
        }
    },
}

</script>

<style scoped>
.station {
    color: rgb(44, 80, 133);
}

.ticket{
    display: inline-block;
    font-weight: 500;
    font-size: 15px;
    width: 65px;
    height: 65px;
    margin: auto;
    margin-right: 20px;
    margin-left: 20px;
    margin-bottom: 10px;
    background-clip: border-box;
    border-radius: 8px;
    box-shadow: 2px 2px rgba(113, 89, 89, 0.42);
    padding: 2px 2px;
    background-color: rgba(206, 206, 206, 0.491);
}

.time_go {
    font-size: 20px;
}

.time_arrive {
    font-size: 15px;
}

.ticketCard {
    display: inline-flex;
    flex-direction: column;
    text-align: center;
}
</style>