import Vue from 'vue'

import 'normalize.css/normalize.css'// A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n
import VCharts from 'v-charts'

import '@/styles/index.scss' // global css

import App from './App'
import router from './router'
import store from './store'

import '@/icons' // icon
import '@/permission' // permission control

// // 引入第三方js
// import '@/assets/js/jquery-1.8.3.js'
// import '@/assets/js/reconnecting-websocket.min.js'
// import '@/assets/js/config.js'
// import '@/assets/js/api.js'
// import '@/assets/js/handlingMessage.js'
// import '@/assets/js/tanchuang.js'
// import '@/assets/js/tanchuangapi.js'
// //
// window.ws = new ReconnectingWebSocket("ws://" + msg_host + ":" + msg_port + "/websocket", null, {
//     debug: true,
//     reconnectInterval: 5000
// });




Vue.use(ElementUI, { locale })
Vue.use(VCharts)

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
