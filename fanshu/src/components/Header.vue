<template>
  <div class="head" v-show="headShow">
    <router-link to="/">index</router-link>
    <router-link to="/blog">blog</router-link>
    <router-link to="/write" v-show="!this.$store.state.isLogin">write</router-link>
    <router-link class="login" to="/login" v-if="this.$store.state.isLogin">login</router-link>
    <a class="login" @click="logout" href="#" v-else>logout</a>
  </div>
</template>

<script>
  export default {
    name: 'head',
    data () {
      return {
        msg: ''
      }
    },
    methods: {
      logout: function (e) {
        e.preventDefault();
        this.$api.SDK.User.logOut();
        this.$store.commit('changeLogin');
        console.log(this.$api.SDK.User.current());
        this.$router.push({ path: '/' });
      }
    },
    computed: {
      headShow: function () {
        if(this.$route.path === '/write'){
          return false;
        }
        else {
          return true;
        }
      }
    }
  }
</script>

<style>
  .head{
    text-align: center;
  }
  .login{
    float: right;
    padding-right: 10px;
  }
</style>
