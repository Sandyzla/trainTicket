import { defineStore } from 'pinia';

//所有组件都能调用
export const useUserStore = defineStore('user', {
  state: () => ({
    isLogined: false,
    idNum: "",
    phone: "",
    userName: "",
    password: "",
    url:'http://localhost:8888',
  }),
  persist:true,
  actions: {

  },
});
