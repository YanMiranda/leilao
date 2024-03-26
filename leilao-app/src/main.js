import Vue from 'vue'
import VueTheMask from 'vue-the-mask'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import vuetify from './plugin/vuetify.js'
import 'vuetify/dist/vuetify.min.css'
import isMobile from './layout/layout.js'

Vue.config.productionTip = false

Vue.prototype.$axios = axios;

axios.defaults.baseURL = 'http://localhost:8081/';

Vue.use(vuetify);

Vue.mixin(isMobile);

Vue.use(VueTheMask);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
