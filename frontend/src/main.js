import { createApp} from 'vue'
import App from './App.vue'

import BaseCard from './components/UI/BaseCard.vue';
import BaseButton from './components/UI/BaseButton.vue';
import BaseDialog from './components/UI/BaseDialog.vue';
import { createPinia } from 'pinia';
import router from './router';
import globalStoreMixin from './stores/globalStoreMixin';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate';

const pinia = createPinia()

pinia.use(piniaPluginPersistedstate);
const app = createApp(App)

app.use(pinia)
app.use(router)

app.component('base-card', BaseCard);
app.component('base-button', BaseButton);
app.component('base-dialog', BaseDialog);
app.mixin(globalStoreMixin);
app.mount('#app');