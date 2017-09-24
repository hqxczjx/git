import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/pages/home.vue'
import Category from '@/pages/category'
import shopCar from '@/pages/shopCar'
import Add from '@/pages/address'
import myStore from '@/pages/myStore'
import person from '@/pages/person'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: function (res) {
        require(['@/pages/home'], res)
      }
    },
    {
      path: '/category',
      name: 'Category',
      component: function (res) {
        require(['@/pages/category'], res)
      }
    },
    {
      path: '/myStore',
      name: 'myStore',
      component: myStore
    },
    {
      path: '/shopCar',
      name: 'shopCar',
      component: shopCar
    },
    {
      path: '/add',
      name: 'add',
      component: Add
    },
    {
      path: '/person',
      name: 'person',
      component: person
    }
  ]
})
