<template>
  <div>
    <input ref="user" type="text" name="user" placeholder="username"/>
    <input ref="pwd" type="password" name="pwd" placeholder="password"/>
    <button @click="Login">login</button>
  </div>
</template>

<script>
  export default {
    name: 'head',
    data () {
      return {
        username: 'admin',
        password: 'admin'
      }
    },
    methods: {
      Login: function () {
        this.$api.SDK.User.logIn(this.$refs.user.value, this.$refs.pwd.value).then((loginedUser)=>{
          this.$store.commit('changeLogin');
          this.$router.push({ path: '/' });
        }, (error)=>{
          alert('error')
        });
//        if(this.$refs.user.value === this.username &&
//          this.$refs.pwd.value === this.password){
//            this.$router.push({ path: '/' });
//        }
//        else{
//          alert('error');
//        }
      }
    }
  }
</script>

<style>

</style>
