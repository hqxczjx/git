// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import api from './api'
import store from './assets'


Vue.config.productionTip = false;

/* eslint-disable no-new */

localStorage.setItem('debug', 'leancloud*');
Vue.mixin({
  beforeCreate(){
    if(!this.$api){
      this.$api = api;
    }
  }
});

new Vue({
  el: '#app',
  router,
  store,
  // template: '<App/>',
  // components: { App }
  render: h => h(App)
});
