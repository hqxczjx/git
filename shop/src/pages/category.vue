<template>
  <div class="category">
    <search
      v-model="value"
      position="absolute"
      auto-scroll-to-top
      @on-focus="onFocus"
      @on-cancel="onCancel"
      @on-submit="onSubmit(value)"
      ref="search">
    </search>
    <div style="width: 100%;overflow:scroll;-webkit-overflow-scrolling:touch;">
      <tab style="width:500px;" bar-active-color="#668599" :line-width="1">
        <tab-item v-for="item in cate" :selected="nowCate === item.id" @on-item-click="setCate(item.id)">{{ item.name }}</tab-item>
      </tab>
    </div>
    <panel :list="nowList" :type="type"></panel>
  </div>
</template>

<script>
  import { Search, Tab, TabItem, Panel, AjaxPlugin } from 'vux'

  function changeList(that, value) {
    if(value === 0){
      that.nowList = that.list;
      console.log('i am 0');
    }
    else{
      let l = [];
      for(let i = 0; i < that.list.length; i++){
        if(that.list[i].type === value){
          l.push(that.list[i]);
        }
      }
      that.nowList = l;
    }
  }

  export default {
    name: 'Category',
    components: {
      Search,
      Tab,
      TabItem,
      Panel
    },
    data () {
      return {
        type: '5',
        value: '',
        cate: [],
        list: [],
        nowCate: 0,
        nowList: []
      }
    },
    watch: {
      nowCate: function(value) {
        if(value === 0){
          this.nowList = this.list;
        }
        else{
          let l = [];
          for(let i = 0; i < this.list.length; i++){
            if(this.list[i].type === value){
              l.push(this.list[i]);
            }
          }
          this.nowList = l;
        }
      }
    },
    methods: {
      onSubmit (value) {
        this.nowList = [];
        for(let item of this.list){
          if(item.title.indexOf(value) > -1){
            this.nowList.push(item);
          }
        }
      },
      onFocus () {
        console.log('on focus');
      },
      onCancel () {
        this.nowCate = 0;
        this.nowList = this.list;
      },
      setCate (value) {
        this.nowCate = value;
      }
    },
    beforeCreate () {
      this.$store.commit('updateLoadingStatus', {isLoading: true});
    },
    created () {
      this.$http.get('/index/index/showProducts').then((res) => {
        let re_list = res.data.products,
          result_list = [],
          results_cate = this.cate;
        for(let i = 0; i < re_list.length; i++){
          result_list[i] = {
            src: 'http://windows10.microdone.cn' + re_list[i].mainPic,
            title: re_list[i].pname,
            desc: re_list[i].price,
            url: '/detail?id=' + re_list[i].id,
            type: re_list[i].typeID
          }
        }
        results_cate.push({
          id: 0,
          name: '全部',
          uid: '10000000'
        });
        for(let i = 0; i < res.data.types.length; i++){
          results_cate.push(res.data.types[i]);
        }
        this.list = result_list;
        this.nowList = result_list;
        if(this.$store.state.wCate !== 0){
          this.nowCate = this.$store.state.wCate;
          changeList(this, this.nowCate);
          this.$store.commit('updateCateStatus', { wCate: 0 });
        }
        this.cate = results_cate;
        this.$store.commit('updateLoadingStatus', {isLoading: false});
      }, (error) => {
        console.log(error);
      });
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  @import '~vux/src/styles/center.less';
</style>
