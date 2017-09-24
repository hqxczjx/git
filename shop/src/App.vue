<template>
  <div id="app">
    <router-view></router-view>
    <loading :show="this.$store.state.isLoading"></loading>
    <my-footer class="foot" v-if="wRouter"></my-footer>
  </div>
</template>

<script>
  import { Loading } from 'vux';
  import MyFooter from '@/components/Footer';

  export default {
  name: 'app',
  components: {
    Loading,
    MyFooter
  },
  computed: {
    wRouter: function () {
      if(this.$route.path !== '/shopCar' && this.$route.path !== '/add' && this.$route.path !== '/detail'){
        return true;
      }
      else{
        return false;
      }
    }
  }
}
</script>

<style lang="less">
@import '~vux/src/styles/reset.less';
@import '//at.alicdn.com/t/font_376145_ighnvqb6y5019k9.css';
body {
  background-color: #fbf9fe;
  #app{
    margin-bottom: 55px;
  }
  .foot{
    position: fixed;
    left: 0;
    right: 0;
    bottom: 0;
  }
}
</style>
