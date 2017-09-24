<template>
  <div class="index">
    <swiper loop auto :list="urlList" :index="urlIndex" @on-index-change="urlListChange"></swiper>
    <div class="flex-container">
      <a class="fenlei" style="background: #ffb3a7;">
        <button style="color: #c32136;" @click="goToCateForOne(1)"><strong>流行服饰</strong></button>
      </a>
      <a class="fenlei" style="background: #3eede7;">
        <button style="color: #8d4bbb;" @click="goToCateForOne(1)"><strong>电子产品</strong></button>
      </a>
      <a class="fenlei" style="background: #ffc64b;">
        <button style="color: #c89b40;" @click="goToCateForOne(1)"><strong>运动户外</strong></button>
      </a>
      <a class="fenlei" style="background: #3de1ad;">
        <button style="color: #50616d;" @click="goToCateForOne(0)"><strong>全部分类</strong></button>
      </a>
    </div>
    <div class="index-shop">
      <fieldset>
        <legend>推荐商品</legend>
        <router-link v-for="item in urlList" :to="item.url"><img :src="item.img"></router-link>
      </fieldset>
    </div>
  </div>
</template>

<script>
  import { Swiper, AjaxPlugin } from 'vux'

  export default {
    name: 'Home',
    components: {
      Swiper
    },
    data () {
      return {
        urlList: [],
        urlIndex: 0
      }
    },
    methods: {
      urlListChange (index) {
        this.urlIndex = index
      },
      goToCateForOne (value) {
        this.$store.commit('updateCateStatus', {wCate: value});
        this.$router.push({ path: '/category' });
      }
    },
    beforeCreate () {
      this.$store.commit('updateLoadingStatus', {isLoading: true});
    },
    mounted () {
      this.$http.get('/index/index/showTopPictures').then((res) => {
        let results = [];
        for(let i = 0; i < res.data.length; i++){
          results[i] = {
            url: '/detail?id=' + res.data[i].id,
            img: 'http://windows10.microdone.cn' + res.data[i].picUrl,
            title: res.data[i].name
          }
        }
        this.urlList = results;
        this.urlIndex = results.length;
        this.$store.commit('updateLoadingStatus', {isLoading: false});
      }, (error) => {
        console.log(error);
      });
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .flex-container{
    margin-top: 10px;
    width: 100%;
    height:  60px;
    display: flex;
    justify-content: space-around;
  }
  .fenlei{
    width: 23%;
    height: 100%;
    display: inline-block;
    margin: 0;
    padding: 0;
    text-align: center;
    line-height: 1.5em;
    text-decoration: none;
  }
  .fenlei button{
    border: 1px solid transparent;
    padding: 0;
    margin: 0;
    height: 100%;
    background-color: transparent;
    width: 100%;
    outline: none;
  }
  .index-shop a{
    width: 100%;
    height: 150px;
    display: block;
    margin: 5px 0;
    overflow: hidden;
  }
  .index-shop a img{
    width: 100%;
    height: 100%;
  }
  fieldset{
    border: 0;
    border-top: 1px solid #c0ebd7;
    margin-top: 10px;
  }
  legend{
    text-align: center;
    color: #bacac6;
    padding: 0 10px;
  }
</style>
