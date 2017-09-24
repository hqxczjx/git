import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLoading: false,
    wCate: 0
  },
  mutations: {
    updateLoadingStatus (state, payload) {
      state.isLoading = payload.isLoading
    },
    updateCateStatus (state, updateCate) {
      state.wCate = updateCate.wCate
    }
  }
});
