<template>
  <div class="MyFooter">
    <tabbar>
      <tabbar-item link="/" :selected="this.$route.path === '/'">
        <i slot="icon" class="iconfont icon-31shouye"></i>
        <span slot="label">首页</span>
      </tabbar-item>
      <tabbar-item link="/category" :selected="this.$route.path === '/category'">
        <i slot="icon" class="iconfont icon-fenlei"></i>
        <span slot="label">分类</span>
      </tabbar-item>
      <tabbar-item link="/myStore" :selected="this.$route.path === '/myStore'">
        <i slot="icon" class="iconfont icon-dianpu"></i>
        <span slot="label">我的小店</span>
      </tabbar-item>
      <tabbar-item link="/shopCar" :selected="this.$route.path === '/shopCar'">
        <i slot="icon" class="iconfont icon-31gouwuche"></i>
        <span slot="label">购物车</span>
      </tabbar-item>
      <tabbar-item link="/person" :selected="this.$route.path === '/person'">
        <i slot="icon" class="iconfont icon-31wode"></i>
        <span slot="label">个人中心</span>
      </tabbar-item>
    </tabbar>
  </div>
</template>

<script>
  import { Tabbar, TabbarItem } from 'vux'

  export default {
    name: 'MyFooter',
    components: {
      Tabbar,
      TabbarItem
    },
    data () {
      return {
        active: false
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
