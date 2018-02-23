<template>
  <div id="app">
    <my-header :show="show" :showIcon="changeIcon" class="header" :class="{ 'show-drawer' : sDrawer }"  @showD="changeDrawer"/>
    <div class="drawer" :class="{ 'active': sDrawer === true }">
      <dl class="drawer-list">
        <dt style="display: none;">
          <img src="/images/peopleicon.png"/>
          <span>登入</span>
          <span>|</span>
          <span>注册</span>
        </dt>
        <dd @click.stop="go('/')">{{ $t('message.title_index') }}</dd>
        <dd @click.stop="go('/')" style="display: none;">{{ $t('message.home_allcoin') }}</dd>
        <dd @click.stop="go('/changeRank?orderby=-1')">{{ $t('message.rise_and_decline') }}</dd>
        <dd @click.stop="go('/exchange')">{{ $t('message.index_icon_exchange') }}</dd>
        <dd @click.stop="go('/')" style="display: none;">{{ $t('message.index_icon_notice') }}</dd>
        <dd style="display: none;">ICO资讯</dd>
        <dd style="display: none;">IFO分叉币</dd>
        <dd @click.stop="go('/')" style="display: none;">新币上市</dd>
        <dd style="display: none;">安卓APP下载</dd>
      </dl>
      <div class="drawer-background" v-show="sDrawer"></div>
    </div>
    <nuxt class="body" :class="{ 'show-drawer' : sDrawer === true }" />
    <my-footer :show="show"  class="footer"/>
  </div>
</template>

<script>
import MyFooter from './../components/Footer.vue'
import MyHeader from './../components/Header.vue'
import axios from 'axios'
// import i18n from './../plugins/i18n'
import HttpUtil from './../plugins/http_util';

export default {
  // i18n,
  name: 'app',
  components: {
    MyHeader,
    MyFooter
  },
  data() {
    return {
      sDrawer: false,
      show:true,
      lan: '',
      changeIcon: false // false为正常，true为关闭图标
    }
  },
  watch: {
    '$route'(to, from) {
      if (to.params !== from.params) {
        this.sDrawer = false
        this.changeIcon = false
      }
    }
  },
  created(){
    if(this.$route.query.Client == 'bitxin'){
      this.show = false;
    }
  },
  methods: {
    changeDrawer() {
      this.sDrawer = !this.sDrawer
      this.changeIcon = true
    },
    go(url) {
      this.$router.push({ path: url })
    }
  },
  mounted() {
    // console.log(axios.defaults.headers);
    // axios.defaults.withCredentials = true
    // axios.defaults.withCredentials = true
    // axios.defaults.headers.common['test'] = 'aaaaaaaaaaaaaaaaaaaaaaaa'
    // console.log(axios.defaults.headers.common);
    
    // i18n.locale = this.$store.state.locale
    this.lan = this.$store.state.locale
    document.documentElement.scrollTop = 0
    document.body.scrollTop = 0
    let date = new Date()
    if (!localStorage.getItem('B-option') || (date.getTime() - JSON.parse(localStorage.getItem('B-option')).timestamps) / (24 * 3600 * 1000) >= 1) {
      axios.get('https://data.block.cc/api/v1/exrate?base=USD&symbols=CNY,BTC,ETH,JPY,KRW,EUR,HKD,RUB').then((res) => {
        let data = {
          rates: {},
          timestamps: 0
        }
        data.rates = res.data.data.rates
        data.rates.USD = 1
        data.timestamps = res.data.data.timestamps
        localStorage.setItem('B-option', JSON.stringify(data))
      })
    }
    let d = document.querySelector('.drawer')
    d.addEventListener('touchmove', (e) => {
      e.preventDefault()
    })
    let bg = document.querySelector('.drawer-background')
    bg.addEventListener('click', (e) => {
      e.stopPropagation();
      this.sDrawer = false
      this.changeIcon = false
    })
    d.addEventListener('click', () => {
      this.sDrawer = true
    })
    // let h = document.querySelectorAll('.header,.container,.footer')
    // h.forEach((item) => {
    //   item.addEventListener('click', () => {
    //     this.sDrawer = false
    //   })
    // })
    if (!localStorage.getItem('B-rate')) {
      let rate = {
        symbol: 'USD',
        rate: 1,
        timestamps: new Date().getTime()
      }
      localStorage.setItem('B-rate', JSON.stringify(rate))
    }
    if (!localStorage.getItem('B-symbol')) {
      let symbol = [{
        name: 'USD',
        symbol: '$'
      }, {
        name: 'CNY',
        symbol: '¥'
      }, {
        name: 'BTC',
        symbol: '฿'
      }, {
        name: 'ETH',
        symbol: '¤'
      }, {
        name: 'EUR',
        symbol: '€'
      }, {
        name: 'HKD',
        symbol: '¥'
      }, {
        name: 'JPY',
        symbol: '¥'
      }, {
        name: 'KRW',
        symbol: '₩'
      }, {
        name: 'RUB',
        symbol: '₽'
      }, {
        name: 'XBT',
        symbol: '฿'
      }]
      localStorage.setItem('B-symbol', JSON.stringify(symbol))
    }
  }
}
</script>

<style>
* {
  padding: 0;
  margin: 0;
  font-family: "Microsoft YaHei",sans-serif;
}
/* @media only screen and (min-width: 641px) {
  html {
    font-size: 125%!important;
  }
}

@media only screen and (min-width: 561px) {
  html {
    font-size: 109%!important;
  }
}

@media only screen and (min-width: 481px) {
  html {
    font-size: 94%!important;
  }
}

html {
  font-size: 62.5%;
}
@media (max-width: 414px) {
  html {
    font-size: 35.9375%;
  }
}
@media (max-width: 432px) {
  html {
    font-size: 37.5%;
  }
}
@media (max-width: 480px) {
  html {
    font-size: 41.667%;
  }
}
@media (max-width: 540px) {
  html {
    font-size: 46.875%;
  }
}
@media (max-width: 640px) {
  html {
    font-size: 55.556%;
  }
}
@media (max-width: 720px) {
  html {
    font-size: 62.5%;
  }
} */
/* @font-face {
  font-family: "NotoSansHans Regular";
  src: url("/NotoSansHans-Regular.otf");
} */
#app {
  display: flex;
  flex-flow: column;
  min-height: 100vh;
  overflow: hidden;
  position: relative;
}
.header {
  position: relative;
  left: 0;
  transition: left .3s;
}
.body {
  flex: 1;
  position: relative;
  left: 0;
  transition: left .3s;
}
.drawer {
  position: fixed;
  z-index: 30;
  top: 0;
  bottom: 0;
  left: 0;
  width: 0rem;
  background-color: #333333;
  border: 1px solid #333333;
  transition: width .3s;
  overflow: hidden;
}
.drawer.active {
  width: 4.3333rem;
}
.no-scroll {
  overflow: hidden;
}
.show-drawer {
  left: 4.3333rem;
  overflow: hidden;
}
.drawer-list>dt,.drawer-list>dd {
  display: inline-block;
  width: 100%;
  padding: 0.3333rem;
  color: #999999;
  white-space: nowrap;
  font-size: 0.4267rem;
  border-bottom: 1px solid #434343;
}
.drawer-list>dt {
  color: #CBCBCB;
  padding: 0;
}
.drawer-list>dt span {
  vertical-align: top;
  margin-top: 0.5333rem;
  display: inline-block;
}
.drawer-list>dt span:nth-child(3) {
  font-size: 0.2667rem;
  margin-top: 0.6267rem;
  color: #676767;
}
.drawer-list img {
  width: 0.7333rem;
  margin: 0.4rem 0.2rem 0.2rem 0.4rem;
}
.drawer-background {
  position: fixed;
  top: 0;
  left: 4.3333rem;
  right: 0;
  bottom: 0;
  z-index: 29;
}
</style>
