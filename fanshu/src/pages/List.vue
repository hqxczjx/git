<template>
  <div class="hello">
    <ul v-for="item in msg">
      <li><router-link :to="{ name: 'Article', params: { id: item.get('title') }}">{{ item.get('title') }}</router-link></li>
    </ul>
  </div>
</template>

<script>
  import AV from 'leancloud-storage';
  export default {
    name: 'hello',
    data () {
      return {
        msg: []
      }
    },
    created () {
      var that = this;
      var cq = new AV.Query('Article');
      cq.find().then(function (data) {
        that.msg = data;
      }, function (error) {
        console.log(error);
      });
    }
  }
</script>

<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
