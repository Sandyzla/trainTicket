<template>
    <div>
        <base-dialog v-if="dialog_message">
            <template #header>
                购票提示
            </template>
            <template #content>
                {{ dialog_message }}
            </template>
            <template #button>
                <base-button @click="closeDialog">好的</base-button>
            </template>
        </base-dialog>
        <div class="content">
            <div class="order">
                <div class="order_header">
                    <span class="train-id">{{ order.trainId }}</span>
                    <span class="price">价格: ￥{{ order.price }}</span>
                    <span v-if="order.state == 'submitted'" class="state">待支付</span>
                    <span v-else class="state">已支付</span>
                </div>
                <div class="details">
                    <p>开点:{{ order.tFrom.substring(0, 5) }}-{{ order.tTo.substring(0, 5) }}（历时{{ order.duration }}） {{
                        order.staFrom
                        }}-{{ order.staTo }}</p>
                    <p>创建时间: {{ order.createTime }}</p>
                </div>
            </div>
        </div>
        <the-ticket :can_refund="false" :can_use="false" :axiosPath=axiosPath :param="order.orderId"></the-ticket>
        <div v-if="order_state == 'submitted'" class="but_div">
            <base-button @click="cancelOrder">取消订单</base-button>
            <base-button @click="payOrder">支付</base-button>
        </div>

    </div>



</template>

<script>
import axios from 'axios';
import TheTicket from './TheTicket.vue';
export default {
    watch: {
        $route() {
            // 根据路由参数重新获取数据
            this.fetchData();
        }
    },

    created() {
        this.fetchData();
    },
    data() {
        return {
            axiosPath: '',
            order: {},
            order_state: '',
            dialog_message: '',
        }
    },
    methods: {
        cancelOrder() {
            let that = this;
            axios({
                method: 'post',
                url: this.userStore.url + "/order_cancel",
                headers: {
                    'Content-Type': 'text/plain' //传字符串需要这个
                },
                data: this.order.orderId
            })
                .then(function (response) {
                    if (response.data.code == 'success') {
                        that.dialog_message = '已取消订单！'
                    }
                });
        },
        payOrder() {
            let that = this;
            axios({
                method: 'post',
                url: this.userStore.url + "/order_pay",
                headers: {
                    'Content-Type': 'text/plain' //传字符串需要这个
                },
                data: this.order.orderId
            })
                .then(function (response) {
                    if (response.data.code == 'success') {
                        that.dialog_message = '已支付订单！'
                    }
                });
        },
        fetchData() {
            this.order = history.state;
            this.order_state = history.state.order_state;
            this.axiosPath = '/order_ticket'
        },
        closeDialog() {
            this.dialog_message = '';
            this.$router.replace({
                name: 'order',
            })
        }
    },
    components: {
        TheTicket
    }
}

</script>

<style scoped>
div.but_div {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 10px;
    margin-bottom: 10px;
}

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
</style>