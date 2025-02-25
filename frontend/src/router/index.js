
import AdminHome from '../components/TrainTicket/AdminHome.vue';
import HomeTrainTicket from '../components/TrainTicket/TheHome.vue';
import TheHome from '../components/TheHome.vue';
import TrainQueryPage from '../components/TrainTicket/TrainQueryPage.vue';
import TrainDetailPage from '@/components/TrainTicket/TrainDetailPage.vue';
import OrderPage from '@/components/TrainTicket/OrderHome.vue';
import OrderDetail from '@/components/TrainTicket/OrderDetail.vue';
import OrderList from '@/components/TrainTicket/OrderList.vue';
import MyTicket from '@/components/TrainTicket/MyTicket.vue';
import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        path: '/',
        component: TheHome
    },
    {
        path: '/admin',
        name: 'admin',
        component: AdminHome
    },
    {
        path: '/trainTicket',
        name: 'train_home',
        component: HomeTrainTicket,
        children:
            [{
                path: 'query',
                name: 'train_query',
                component: TrainQueryPage,
                meta: {
                    keepAlive: true,
                  },
            },
            {
                path: 'query_detail',
                name: 'train_detail',
                component: TrainDetailPage,
            },
            {
                path: 'order',
                name: 'order',
                component: OrderPage,
                children:
                    [{
                        path: 'order_submitted',
                        name: 'order_submitted',
                        component: OrderList,
                    },
                    {
                        path: 'order_paid',
                        name: 'order_paid',
                        component: OrderList,
                    },
                    {
                        path: 'my_ticket',
                        name: 'my_ticket',
                        component: MyTicket
                    },           
                    {
                        path: 'order_submitted_detail',
                        name: 'order_submitted_detail',
                        component: OrderDetail
                    },
                    {
                        path: 'order_paid_detail',
                        name: 'order_paid_detail',
                        component: OrderDetail
                    },
                    ]
            },
            ]
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;