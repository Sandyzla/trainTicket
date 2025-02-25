<template>
    <div class="container">
        <div class="header-content">
            <div class="header">
                <button :class="{ active: selectedTab === 'order_submitted' }"
                    @click="gotoRouterByName('order_submitted')">
                    待确认订单
                </button>
                <button :class="{ active: selectedTab === 'order_paid' }" @click="gotoRouterByName('order_paid')">
                    已支付订单
                </button>
                <button :class="{ active: selectedTab === 'my_ticket' }" @click="gotoRouterByName('my_ticket')">
                    我的车票
                </button>
            </div>
            <RouterView>
            </RouterView>
        </div>
    </div>
</template>

<script>
export default {
    mounted() {
        this.$router.replace({
            name: this.selectedTab,
            state: {
                axiosPath: '/' + this.selectedTab
            }
        })
    },
    data() {
        return {
            selectedTab: 'order_submitted',
        };
    },
    methods: {
        gotoRouterByName(route) {
            this.selectedTab = route;
            this.$router.replace({
                name: route,
                state: {
                    axiosPath: '/' + route
                }
            })
        },
    },
};
</script>

<style scoped>
.container {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

.tabs {
    display: flex;
    background-color: #f0f0f0;
}

button {
    flex: 1;
    padding: 10px;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s;
}

button.active {
    background-color: #ffffff;
    font-weight: bold;
}

button:not(.active) {
    background-color: #c0c0c0;
}
</style>