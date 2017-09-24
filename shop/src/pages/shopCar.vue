<template>
  <div class="shopCar">
    <x-header class="x-title">购物车<a slot="right" @click="delList">删除</a></x-header>
    <div class="check-shop" v-for="item in allList">
      <div class="shop-click" v-model="checkList">
        <input :id="'check' + item.record" type="checkbox" name="check" v-model="checkList" :value="item">
        <label :for="'check' + item.record" class="check"></label>
      </div>
      <div style="flex-grow: 0">
        <router-link :to="'/detail?id=' + item.id"><img :src="item.selectG.pic" style="width: 80px;height: 80px;"></router-link>
      </div>
      <div style="flex-grow: 1;align-self: flex-start;padding: 5px;">
        <h4>商品1</h4>
        <select style="margin: 5px 0;display: block;width: 100px;" v-model="item.selectG">
          <option v-for="guigeList in item.guige" :value="guigeList">{{ guigeList.desc }}</option>
        </select>
        <inline-x-number width="30px" :min="0" :value="item.num" v-model="item.num"></inline-x-number>
      </div>
      <div style="flex-grow: 0;align-self: flex-start;padding: 5px;">
        <h4 style="color: #ff3300;">￥{{ item.selectG.price }}</h4>
      </div>
    </div>
    <footer class="shop-car-footer">
      <div class="check-all">
        <input id="all" type="checkbox" @change="checkAll" v-model="checkAllList" name="all">
        <label for="all"></label>
        <span>全选</span>
      </div>
      <div class="sum">
        <p>合计：￥{{ shopSum }}</p>
        <p style="color: #3bcb97;">不含运费</p>
      </div>
      <a class="btn-shop-car-submit" @click="formSub">结算</a>
    </footer>
  </div>
</template>

<script>
  import { XHeader, InlineXNumber, AjaxPlugin } from 'vux'
  export default {
    name: 'shopCar',
    components: {
      XHeader,
      InlineXNumber
    },
    data () {
      return {
        allList: [],
        checkList: [],
        sendList: [],
        checkAllList: false
      }
    },
    watch: {
      checkList () {
        if(this.checkList.length === this.allList.length){
          this.checkAllList = true;
        }
        else{
          this.checkAllList = false;
        }
      },
      checkAllList () {
        if(this.checkAllList){
          this.checkList = this.allList;
        }
      }
    },
    computed: {
      shopSum: function () {
        let sum = 0,
          list = this.checkList;
        for(let i = 0; i < list.length; i++){
          sum += list[i].selectG.price * list[i].num;
        }
        return sum.toFixed(2);
      }
    },
    methods: {
      formSub: function () {
        if(this.checkList.length === 0){
          alert('你还没选中商品哦~');
        }
        else{
          this.$http.post('', this.checkList).then((res) => {
            this.$router.push({ path: '/add' });
          }, (error) => {
            console.log(error);
          });
        }
      },
      checkAll: function (event) {
        if(!event.currentTarget.checked){
          this.checkList = [];
        }
        else{
          this.checkList = this.allList;
        }
      },
      delList: function () {
        delItem(this.allList, this.checkList);
        this.checkList = [];
      }
    },
    beforeCreate () {
      this.$store.commit('updateLoadingStatus', {isLoading: true});
    },
    mounted () {
      this.$http.get('/index/Ajax/showCart').then((res) => {
        let data = res.data;
        let all = [];
        let item = {};
        let g = [];
        for(let i = 0; i < data.length; i++){
          g = [];
          for(let j = 0; j < data[i].guiges.length; j++){
            g.push({
              type: data[i].guiges[j].id,
              desc: data[i].guiges[j]['w_name'],
              price: data[i].guiges[j]['w_price'],
              pic: data[i].guiges[j].mainpic,
              maxNum: data[i].guiges[j].kucun
            });
          }
          item = {
            record: data[i].id,
            id: data[i].product.id,
            title: data[i].product.pname,
            selectG: {
              type: data[i].guige.id,
              desc: data[i].guige['w_name'],
              price: data[i].guige['w_price'],
              pic: data[i].guige.mainpic,
              maxNum: data[i].guige.kucun
            },
            guige: g,
            num: data[i].num
          };
          all.push(item);
        }
        this.allList = all;
        this.$store.commit('updateLoadingStatus', {isLoading: false});
      }, (error) => {
        console.log(error);
      });
    }
  }

  function delItem(arr, itemArr) {
    while (itemArr.length > 0){
      let num = arr.indexOf(itemArr[0]);
      if(num > -1){
        arr.splice(num, 1);
        itemArr.shift();
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .x-title{
    background-color: #c83c23;
  }
  .check-shop{
    display: flex;
    align-items: center;
    padding: 5px;
    border-bottom: 1px dotted #3de1ad;
  }
  /*选中商品*/
  .shop-click{
    display: inline-block;
    flex-grow: 0;
    margin-right: 5px;
  }
  /*商品数量选择*/
  .shop-car-changeSum>button{
    border: 0;
    width: 30px;
    height: 100%;
    text-align: center;
  }
  .shop-car-changeSum>input{
    width: 30px;
    text-align: center;
    height: 100%;
    border: 0;
    border-left: 1px solid #bbcdc5;
    border-right: 1px solid #bbcdc5;
  }
  .shop-click>input[type=checkbox]{
    display: none;
  }
  .check{
    display: inline-block;
    width: 16px;
    height: 16px;
    border: 1px solid #3bcb97;
    border-radius: 50%;
    box-shadow: 0 0 0 4px #ffffff inset;
    background-color: #ffffff;
  }
  .shop-click>input[type=checkbox]:checked + label{
    background-color: #808080;
  }
  /*底部栏*/
  .shop-car-footer{
    position: fixed;
    display: flex;
    align-items: center;
    left: 0;
    right: 0;
    bottom: 0;
    width: 100%;
    height: 50px;
    background-color: #e3f9fd;
    border-top: 1px solid #c0ebd7;
  }
  .check-all{
    display: inline-block;
    flex-grow: 0;
    padding-left: 6px;
  }
  .check-all input[name=all]{
    display: none;
  }
  .check-all input[name=all] + label{
    display: inline-block;
    vertical-align: text-bottom;
    width: 16px;
    height: 16px;
    border: 1px solid #3bcb97;
    border-radius: 50%;
    box-shadow: 0 0 0 4px #ffffff inset;
    background-color: #ffffff;
  }
  .check-all input[name=all]:checked + label{
    background-color: #808080;
  }
  .sum{
    display: inline-block;
    flex-grow: 1;
    text-align: right;
    margin-right: 10px;
  }
  .btn-shop-car-submit{
    flex-grow: 0;
    -moz-box-shadow:inset 0 1px 0 0 #f5978e;
    -webkit-box-shadow:inset 0 1px 0 0 #f5978e;
    box-shadow:inset 0 1px 0 0 #f5978e;
    background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f24537), color-stop(1, #c62d1f));
    background:-moz-linear-gradient(top, #f24537 5%, #c62d1f 100%);
    background:-webkit-linear-gradient(top, #f24537 5%, #c62d1f 100%);
    background:-o-linear-gradient(top, #f24537 5%, #c62d1f 100%);
    background:-ms-linear-gradient(top, #f24537 5%, #c62d1f 100%);
    background:linear-gradient(to bottom, #f24537 5%, #c62d1f 100%);
    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f24537', endColorstr='#c62d1f',GradientType=0);
    background-color:#f24537;
    -moz-border-radius:6px;
    -webkit-border-radius:6px;
    border-radius:6px;
    border:1px solid #d02718;
    display:inline-block;
    cursor:pointer;
    color:#ffffff;
    font-size:15px;
    font-weight:bold;
    padding:6px 20px;
    text-decoration:none;
    text-shadow:0 1px 0 #810e05;
    margin-right: 5px;
  }
  .btn-shop-car-submit:active{
    position:relative;
    top:1px;
  }
</style>
