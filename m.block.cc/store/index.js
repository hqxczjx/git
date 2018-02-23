import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = () => new Vuex.Store({//触发2
  state: {
    url: '',
    locales: ['en', 'zh'],
    locale: 'en'
  },
  mutations: {
    setUrl(state, val) {
      state.url = val.url
    },
    setLocale(state, val) {
      state.locale = val.locale
    }
  }
  // actions: {
  //   nuxtServerInit ({ commit }, { req }) {
  //     if (req.headers.cookie) {        
  //       let lan = req.headers.cookie.split('=')[1]
  //       commit('setLocale', { locale: lan })
  //     }
  //   }
  // }
})

export default store
