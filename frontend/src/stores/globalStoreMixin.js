// mixins/globalStoreMixin.js
import { useUserStore } from '@/stores/userStore';

export default {
  computed: {
    userStore() {
      return useUserStore();
    }
  }
};
