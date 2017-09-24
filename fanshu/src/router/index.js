import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import List from '@/pages/List'
import Write from '@/pages/Write'
import Article from '@/pages/Article'
import Login from '@/pages/Login'


Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/blog',
      name: 'List',
      component: List
    },
    {
      path: '/blog/:id',
      name: 'Article',
      component: Article
    },
    {
      path: '/write',
      name: 'Write',
      component: Write
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
