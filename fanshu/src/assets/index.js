import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLogin: true
  },
  mutations: {
    changeLogin (state) {
      state.isLogin = !state.isLogin;
    }
  }
})
