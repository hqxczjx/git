<template>
  <div id="app">
    <nuxt class="body" :class="{ 'show-drawer' : sDrawer }" />
    <my-footer class="footer"/>
  </div>
</template>
<script>
import MyFooter from './../components/Footer.vue'
import axios from 'axios'
export default {
  name: 'app',
  components: {
    MyFooter
  },
  data() {
    return {
      sDrawer: false
    }
  },
  methods: {
    changeDrawer() {
      this.sDrawer = !this.sDrawer
    }
  },
  mounted() {
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
    let h = document.querySelectorAll('.header,.body,.footer')
    h.forEach((item) => {
      item.addEventListener('click', () => {
        this.sDrawer = false
      })
    })
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
<style scoped>
* {
  padding: 0;
  margin: 0;
}
@font-face {
  font-family: "NotoSansHans Regular";
  src: url("/NotoSansHans-Regular.otf");
}
#app {
  display: flex;
  flex-flow: column;
  min-height: 100vh;
  overflow-x: hidden;
  position: relative;
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
  width: 0vw;
  background-color: #333333;
  border: 1px solid #333333;
  transition: width .3s;
  overflow: hidden;
}
.drawer.active {
  width: 43.33vw;
}
.no-scroll {
  overflow: hidden;
}
.show-drawer {
  left: 43.33vw;
  overflow: hidden;
}
.drawer-list>dt,.drawer-list>dd {
  display: inline-block;
  width: 100%;
  padding: 3.33vw;
  color: #999999;
  white-space: nowrap;
  font-size: 4.2667vw;
  border-bottom: 0.133vw solid #434343;
}
.drawer-list>dt {
  color: #CBCBCB;
  padding: 0;
}
.drawer-list>dt span {
  vertical-align: top;
  margin-top: 5.33vw;
  display: inline-block;
}
.drawer-list>dt span:nth-child(3) {
  font-size: 2.667vw;
  margin-top: 6.2667vw;
  color: #676767;
}
.drawer-list img {
  width: 7.33vw;
  margin: 4vw 2vw 2vw 4vw;
}
</style>
