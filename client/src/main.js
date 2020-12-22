import Vue from 'vue'
import './plugins/axios'
import './plugins/element.js'
import './plugins/vue-router'

import App from './App.vue'
import router from './routes'

Vue.config.productionTip = false

Vue.prototype.$myalert = function (xxx) {
  console.error(xxx);
}

new Vue({
  router,
  render: h => h(App),
  created() {
    this.$axios({
      url: "/ctest"
    }).then(() => console.log(0));
  }
}).$mount('#app')
