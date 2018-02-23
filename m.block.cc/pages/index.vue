<template>
  <section class="container">
    <nav class="total">
      <div class="item">
        <div>
          <p class="price-name">{{ BTC.symbol }}-{{ BTC.zhName }}/{{ BTC.name }}</p>
          <div>
            <img style="height: 0.3733rem;width: 0.3733rem;transform: translateY(12%);display: inline-block;" :src="setCoinImg(rate.symbol, btcUp)" alt="">
            <span class="price" :class="[{ down : btcUp < 0 }, { up : btcUp > 0 }]">{{ format(BTC.price * rate.rate, lan, true).num }}{{ format(BTC.price * rate.rate, lan, true).unit }}</span>
            <img style="display:inline-block;width: 0.24rem;height: 0.24rem;margin-left: 0.1067rem;" :src="btcUp > 0 ? `/images/increaseicon.png` : (btcUp < 0 ? `/images/decreaseicon.png` : '')" alt/>
          </div>
        </div>
      </div>
      <i class="line"></i>
      <div class="item">
        <div>
          <p class="price-name">{{ ETH.symbol }}-{{ ETH.zhName }}/{{ ETH.name }}</p>
          <div>
            <img style="height: 0.3733rem;width: 0.3733rem;transform: translateY(12%);display: inline-block;" :src="setCoinImg(rate.symbol, ethUp)" alt="">
            <span class="price" :class="[{ down : ethUp < 0 }, { up : ethUp > 0 }]">{{ format(ETH.price * rate.rate, lan, true).num }}{{ format(ETH.price * rate.rate, lan, true).unit }}</span>
            <img style="display:inline-block;width: 0.24rem;height: 0.24rem;margin-left: 0.1067rem;" :src="ethUp > 0 ? `/images/increaseicon.png` : (ethUp < 0 ? `/images/decreaseicon.png` : '')" alt/>
          </div>
        </div>
      </div>
    </nav>
    <main>
      <div class="head">
        <div>{{ $t('message.block_coin_list') }}</div>
        <div class="group">
          <span class="btn" :class="{ 'active' : openCurrency === true }" @click.stop="openCurrency = !openCurrency">
            {{ rate.symbol }}<img :src="setCoinImg(rate.symbol, 'white')" alt>
          </span>
          <label>
            <img src="/images/searchiconsmall.png" />
            <input v-model="searchInfo" :placeholder="$t('message.home_coin_input')"/>
          </label>
        </div>
        <div class="currency-menu" v-if="openCurrency">
          <div v-for="item in totalSymbol" :key="item.name" :class="{ active : item.name === rate.symbol }" @click="changeRate(item.name)">
            {{ item.name }}&nbsp;
            <img v-if="item.name === rate.symbol" :src="setCoinImg(item.name, 'white')" alt>
            <img v-else :src="setCoinImg(item.name, 'gray-999')" alt>
          </div>
        </div>
      </div>
      <div class="content">
        <!-- <info-list :list="list" :changeList="test" :rate="rate" :lan="lan" :error="listNull"></info-list> -->
        <div class="list">
          <div class="list-head">
            <span>#</span>
            <span>{{ $t('message.coin_name') }}</span>
            <span @click="setSel(1); dataSort()">{{ $t('message.rise_decline') }}<i class="title-change" :class="addUp(1)"></i></span>
            <span @click="setSel(2); dataSort()">{{ $t('message.coin_price') }}<i class="title-price" :class="addUp(2)"></i></span>
          </div>
          <nuxt-link tag="div" class="list-content" v-for="(item, index) in list" :key="index" :to="`/coin/${item.id}`">
            <div class="item-desc">
              <p>
                <span><span :class="num(index + 1)">{{ index + 1 }}</span></span>
                <strong style="font-weight: 500;">{{ item.symbol }}</strong>
                <span v-if="$store.state.locale === 'zh'">{{ item.zhName }}</span>
                <span v-else>{{ item.name }}</span>
              </p>
              <p>
                <small style="font-size: 0.2933rem;">{{ $t('message.coin_volume') }}</small>
                <img style="height: 0.3733rem;" class="icon" :src="setCoinImg(rate.symbol, 'gray-ccc')" alt="">
                <small style="font-size: 0.3467rem;">{{ format(item.volume_ex * rate.rate, lan).num }}</small>
                <small style="font-size: 0.2933rem;">{{ format(item.volume_ex, lan).unit }}</small>
              </p>
            </div>
            <div class="item-price" :class="[{ 'list-up' : test[index] > 0 }, { 'list-down' : test[index] < 0 }]">
              <img class="icon" :src="setCoinImg(rate.symbol, test[index])" alt="">
              <span>{{ format(item.price * rate.rate, lan, true).num }}</span>
              <img :src="`${test[index] > 0 ? '/images/increaseicon.png' : (`${test[index] < 0 ? '/images/decreaseicon.png' : ''}`)}`" alt="">
            </div>
            <div class="item-change">
              <span :class="[{ 'list-up' : item.change1d > 0 }, { 'list-down' : item.change1d < 0 }]">{{ item.change1d > 0 ? `${item.change1d}` : item.change1d }}%</span>
            </div>
          </nuxt-link>
        </div>
        <button class="more" @click="add" v-show="page !== pageSum - 1 && listNull === false">{{ loading }}</button>
      </div>
    </main>
  </section>
</template>
<script>
import axios from 'axios'
// import i18n from './../plugins/i18n.js'
import format from './../static/format.js'
import HttpUtil from './../plugins/http_util';
export default {
  // i18n,
  name: 'index',
  // components: {
  //   infoList
  // },
  async asyncData({ params, store }) {
    // console.log(params);
    // this.$i18n.locale = store.state.locale
    // console.log(i18n.locale);
    
    let res = await HttpUtil.get(`coin/list?page=0&size=50`,{});
    return {
      list: res.data.data.list,
      BTC: res.data.data.list.find((val) => {
        return val.zhName === '比特币'
      }),
      ETH: res.data.data.list.find((val) => {
        return val.zhName === '以太坊'
      })
    }
  },
  data() {
    return {
      searchInfo: '',
      active_exchange: 0,
      total_market_cap_usd: 0,
      active_cryptocurrencies: 0,
      total_volume_usd: 0,
      baseTime: 0,
      imgError: 'this.src="https://blockchains.oss-cn-shanghai.aliyuncs.com/static/main/noneimg.svg"',
      listNull: false,
      dataSum: 0,
      filterMenu: '',
      dataFilter: '',
      lan: '',
      loading: this.$t('message.load_more'),
      page: 0,
      pageSum: 0,
      pageUrl: '',
      rate: {
        type: '$',
        symbol: 'USD',
        rate: 1
      },
      btcUp: 0,
      ethUp: 0,
      openCurrency: false,
      test: [],
      totalSymbol: [],
      whoSel: '',
      isUp: false
    }
  },
  watch: {
    list() {
      this.test = Array(this.list.length).fill(0)
    },
    searchInfo(newVal) {
      this.list = []
      this.loading = this.$t('message.loading')
      if (newVal !== '') {
        this.pageUrl = `coin/list?str=${newVal}`
      } else {
        this.pageUrl = `exchange/list?page=`
      }
      HttpUtil.get(`coin/list?str=${newVal}`).then((res) => {
        if (res.data.data.list.length !== 0) {
          this.list = res.data.data.list
          this.page = res.data.data.page
          this.pageSum = res.data.data.pageCount
          this.listNull = false
          this.loading = this.$t('message.load_more')
        } else {
          this.listNull = true
        }
      })
    },
    stateLan(newVal) {
      this.loading = this.$t('message.load_more')
    }
  },
  computed: {
    stateLan() {
      return this.$store.state.locale
    }
  },
  methods: {
    format(num, lan, isPrice) {
      return format(num, lan, isPrice)
    },
    changeRate(val) {
      let total = JSON.parse(localStorage.getItem('B-option')).rates
      let rate = {
        symbol: val,
        rate: total[val],
        timestamps: new Date().getTime()
      }
      localStorage.setItem('B-rate', JSON.stringify(rate))
      let symbol = JSON.parse(localStorage.getItem('B-symbol')).find((n) => {
        return n.name === val
      })
      this.rate = {
        type: symbol.symbol,
        symbol: val,
        rate: rate.rate
      }
    },
    setCoinImg(symbol, isUp) {
      if (symbol === 'XBT') {
        symbol = 'BTC'
      }
      if (isUp === 'gray-ccc') {
        return `/images/money-icon/gray-ccc/${symbol}.png`
      } else if (isUp === 'white') {
        return `/images/money-icon/white/${symbol}.png`
      } else if (isUp === 'gray-999') {
        return `/images/money-icon/gray-999/${symbol}.png`
      } else {
        if (isUp > 0) {
          return `/images/money-icon/green/${symbol}.png`
        } else if (isUp < 0) {
          return `/images/money-icon/red/${symbol}.png`
        } else {
          return `/images/money-icon/black/${symbol}.png`
        }
      }
    },
    changeRateFa(val) {
      return val.toLowerCase()
    },
    go(url) {
      this.$router.push({ path: url })
    },
    selChange(val) {
        if (val === 1) {
          return 'rise'
        } else if (val === -1) {
          return 'drop'
        }
      },
    num(index) {
      if (index === 1) {
        return 'one'
      } else if (index === 2) {
        return 'two'
      } else if (index === 3) {
        return 'three'
      } else {
        return ''
      }
    },
    add() {
      this.loading = this.$t('message.loading')
      let self = this
      if (this.searchInfo !== '') {
        if (this.page !== this.pageSum - 1) {
          HttpUtil.get(`${self.pageUrl}&page=${self.page + 1}`).then((res) => {
            if (res.data.data.list.length !== 0) {
              self.list = [...self.list, ...res.data.data.list]
              self.page = res.data.data.page
              self.pageSum = res.data.data.pageCount
            }
            self.loading = this.$t('message.load_more')
          })
        }
      } else {
        if (this.page !== this.pageSum - 1) {
          this.getList(this.page + 1)
        }
      }
    },
    addUp(val) {
      if (val === this.whoSel) {
        if (this.isUp) {
          return 'up'
        } else {
          return 'down'
        }
      } else {
        return ''
      }
    },
    setSel(val) {
      if (this.whoSel === val) {
        this.isUp = !this.isUp
      } else {
        this.whoSel = val
        this.isUp = true
      }
    },
    dataSort() {
      switch (this.whoSel) {
        case 2:
          if (this.isUp) {
            this.list = this.list.sort((a, b) => {
              return b.price - a.price
            })
          } else {
            this.list = this.list.sort((a, b) => {
              return a.price - b.price
            })
          }
          break
        case 1:
          if (this.isUp) {
            this.list = this.list.sort((a, b) => {
              return b.change1d - a.change1d
            })
          } else {
            this.list = this.list.sort((a, b) => {
              return a.change1d - b.change1d
            })
          }
          break
        default:
          this.list = this.list.sort((a, b) => {
            if (b.name > a.name) {
              return -1
            } else if (b.name < a.name) {
              return 1
            } else {
              return 0
            }
          })
          break
      }
    },
    getList(sum = 0) {
      this.loading = this.$t('message.loading')
      HttpUtil.get(`coin/list?page=${sum}&size=50`).then((res) => {
        if (res.data.code === 0) {
          let data = res.data.data.list
          this.list = [...this.list, ...data]
          this.test = Array(this.list.length).fill(0)
          this.page = res.data.data.page
          this.pageSum = res.data.data.pageCount
          this.BTC = this.list.find((val) => {
            return val.zhName === '比特币'
          })
          this.ETH = this.list.find((val) => {
            return val.zhName === '以太坊'
          })
          this.loading = this.$t('message.load_more')
          // this.getWs()
        }
      })
    },
    getWs() {
      let ws = new WebSocket('wss://data.block.cc/ws')
      ws.addEventListener('open', () => {
        if (this.list) {
          for (let coin of this.list) {
            ws.send(JSON.stringify({
              event: 'subscribe',
              channel: coin.symbol.toLowerCase() + '_price'
            }))
          }
        }
      })
      ws.addEventListener('message', (res) => {
        if (typeof res.data === 'string') {
          let data = JSON.parse(res.data)
          if (data.channel) {
            let l = this.list.findIndex((val) => {
              return val.symbol === data.channel.split('').slice(0, -6).join('')
            })
            if (data.price) {
              if (this.list[l].price > data.price) {
                if (this.list[l].symbol === 'BTC') {
                  this.btcUp = -1
                } else if (this.list[l].symbol === 'ETH') {
                  this.ethUp = -1
                }
                this.test[l] = -1
              } else if (this.list[l].price < data.price) {
                if (this.list[l].symbol === 'BTC') {
                  this.btcUp = 1
                } else if (this.list[l].symbol === 'ETH') {
                  this.ethUp = 1
                }
                this.test[l] = 1
              } else {
                if (this.list[l].symbol === 'BTC') {
                  this.btcUp = 0
                } else if (this.list[l].symbol === 'ETH') {
                  this.ethUp = 0
                }
                this.test[l] = 0
              }
              this.list[l].price = data.price
            }
          }
        }
      })
    }
  },
  mounted() {
    // i18n.locale = this.$store.state.locale
    // console.log(axios.defaults.headers);
    
    // this.$i18n.locale = this.$store.state.locale
    // console.log(i18n.locale);
    // console.log(this.$store.state.locale);
    this.loading = this.$t('message.load_more')
    this.$store.commit('setUrl', { url: 'https://block.cc' })
    // this.lan = localStorage.getItem('lan')
    this.lan = this.$store.state.locale
    this.totalSymbol = JSON.parse(localStorage.getItem('B-symbol'))
    this.getWs()
    // window.addEventListener('scroll', () => {
    //   let sTop = document.documentElement.scrollTop || document.body.scrollTop
    //   if (sTop > 200) {
    //     this.scrollTop = true
    //   } else {
    //     this.scrollTop = false
    //   }
    // })
    document.addEventListener('click', () => {
      this.openCurrency = false
    })
    // window.addEventListener('touchmove', () => {
    //   let nav = document.querySelector('nav.total')
    //   let top = document.querySelector('#app>.header')
    //   let title = document.querySelector('main .head')
    //   let tr = document.querySelector('thead')
    //   let leftTitle = document.querySelector('.left .title')
    //   if (document.documentElement.scrollTop < (nav.clientHeight + top.clientHeight - title.clientHeight)) {
    //     title.classList.remove('fixed')
    //   }
    //   if (title.getBoundingClientRect().top === 0) {
    //     return false
    //   } else if (title.getBoundingClientRect().top < 0) {
    //     title.classList.add('fixed')
    //   }

    //   if (document.documentElement.scrollTop < (nav.clientHeight + top.clientHeight - title.clientHeight)) {
    //     tr.classList.remove('fixed')
    //     leftTitle.classList.remove('fixed')
    //   }
    //   if (tr.getBoundingClientRect().top === 0) {
    //     return false
    //   } else if (tr.getBoundingClientRect().top < 0) {
    //     tr.classList.add('fixed')
    //     leftTitle.classList.add('fixed')
    //   }
    // })
    // window.addEventListener('scroll', () => {
    //   let nav = document.querySelector('nav.total')
    //   let top = document.querySelector('#app>.header')
    //   let title = document.querySelector('main .head')
    //   let tr = document.querySelector('thead')
    //   let leftTitle = document.querySelector('.left .title')
    //   if (document.documentElement.scrollTop < (nav.clientHeight + top.clientHeight - title.clientHeight)) {
    //     title.classList.remove('fixed')
    //   }
    //   if (title.getBoundingClientRect().top === 0) {
    //     return false
    //   } else if (title.getBoundingClientRect().top < 0) {
    //     title.classList.add('fixed')
    //   }

    //   if (document.documentElement.scrollTop < (nav.clientHeight + top.clientHeight - title.clientHeight)) {
    //     tr.classList.remove('fixed')
    //     leftTitle.classList.remove('fixed')
    //   }
    //   if (tr.getBoundingClientRect().top === 0) {
    //     return false
    //   } else if (tr.getBoundingClientRect().top < 0) {
    //     tr.classList.add('fixed')
    //     leftTitle.classList.add('fixed')
    //   }
    // })
  }
}
</script>

<style scoped>
nav.total {
  height: 1.8133rem;
  border-bottom: 0.16rem solid #eeeeee;
  display: flex;
}
nav .item {
  height: 100%;
  flex-grow: 1;
  margin: 0.2667rem 0 0.4rem 0.2667rem;
}
.item .price-name {
  font-size: 0.4267rem;
  color: #333333;
}
.line {
  display: inline-block;
  border-left: 1px solid #e6e6e6;
  height: 0.6933rem;
  margin-top: 0.6133rem;
}
.price-name+div>span:first-of-type {
  font-size: 0.48rem;
  font-weight: bold;
}
.item span {
  color: #333333;
}
.item .price {
  font-size: 0.4267rem;
}
.item span.up {
  color: #34A853;
}
.item span.down {
  color: #ea4235;
}
.item img:first-of-type {
  width: 0.2667rem;
  height: 0.2667rem;
}
.item .price-name+div {
  position: relative;
  margin-top: 0.1067rem;
}

main .head {
  height: 1.3333rem;
  display: flex;
  flex-wrap: nowrap;
  border-bottom: 1px solid #e6e6e6;
  position: relative;
}
thead.fixed,.left .title.fixed {
  position: fixed;
  top: 1.3333rem;
  left: 0;
  right: 0;
  z-index: 30;
  background-color: #ffffff;
}
.head.fixed {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 30;
  background-color: #ffffff;
}
.head>* {
  box-sizing: border-box;
  flex-grow: 1;
  text-align: left;
}
.head>*:nth-child(1) {
  padding: 0.4rem 0 0.4rem 0.2667rem;
  font-size: 0.4267rem;
}
.currency-menu {
  position: absolute;
  top: 90%;
  left: 0;
  right: 0;
  display: flex;
  flex-wrap: wrap;
  z-index: 30;
  background-color: #333333;
}
.currency-menu>div {
  color: #999999;
  padding: 0.3333rem;
  text-align: center;
  /* padding-left: 0.4rem; */
  font-size: 0.3733rem;
  box-sizing: border-box;
  border-bottom: 1px solid #434343;
  border-right: 1px solid #434343;
  width: calc(100vw  / 5);
  position: relative;
}
.currency-menu>div.active {
  background-color: #4f4f4f;
  color: #ffffff;
}
.currency-menu img {
  height: 0.1333rem;
  width: 0.1333rem;
  transform: scale(3.5) translateY(-0.0187rem) translateX(-0.0133rem);
}
.group {
  padding: 0.1333rem 0.2667rem 0.1333rem 0;
  display: flex;
  justify-content: flex-end;
}
.group label {
  position: relative;
  height: 1.0667rem;
  vertical-align: bottom;
  display: inline-block;
}
.group>label img {
  position: absolute;
  left: 0.24rem;
  top: 55%;
  transform: translateY(-50%);
  width: 0.32rem;
}
.head .group input {
  padding: 0 0 0 0.64rem;
  border: 1px solid #c1c1c1;
  border-radius: 0.0667rem;
  font-size: 0.3733rem;
  width: 3.6267rem;
  line-height: normal;
  box-sizing: border-box;
  height: 1.0667rem;
  outline: none;
}
.group span.btn {
  height: 1.0667rem;
  margin-right: 0.1067rem;
  padding: 0.2933rem 0.3733rem 0.2933rem 0.1067rem;
  padding-left: 0.4rem;
  padding-right: 0.3733rem;
  text-align: left;
  font-size: 0.3733rem;
  box-sizing: border-box;
  width: 2rem;
  border: 0;
  color: #ffffff;
  background-color: #333333;
  border-radius: 0.0667rem;
  outline: none;
  position: relative;
  /* top: 0.133vw; */
}
.group span.btn::after {
  content: '';
  display: block;
  position: absolute;
  top: 45%;
  right: 0.1867rem;
  border-top: 0.1067rem solid #999999;
  border-right: 0.1067rem solid transparent;
  border-left: 0.1067rem solid transparent;
  transition: all .5s;
  transform: rotate(0deg);
}
.group span.btn.active::after {
  content: '';
  display: block;
  position: absolute;
  top: 45%;
  right: 0.1867rem;
  border-top: 0.1067rem solid #999999;
  border-right: 0.1067rem solid transparent;
  border-left: 0.1067rem solid transparent;
  transform: rotate(180deg);
}
.group span.btn>img {
  height: 0.1333rem;
  width: 0.1333rem;
  transform: scale(3.5) translateY(-0.0187rem) translateX(0.03rem);
}

.content .list>.list-head {
  font-size: 0.2933rem;
  padding: 0.16rem 0;
  color: #999999;
  background-color: #f5f5f5;
}
.content .list>.list-head>* {
  display: inline-block;
}
.content .list>.list-head>span:first-of-type {
  padding-left: 0.4267rem;
  text-align: right;
}
.content .list>.list-head>span:nth-child(2) {
  width: 2.1867rem;
  text-align: left;
  padding-left: 0.16rem;
}
.content .list>.list-head>span:nth-child(4) {
  float: right;
  margin-right: 0.7467rem;
  text-align: center;
}
.content .list>.list-head>span:nth-child(3) {
  width: 1.6rem;
  float: right;
  text-align: center;
}
.title-price,.title-change {
  position: relative;
  display: inline-block;
  width: 0.5rem;
}
.title-price::after,.title-change::after {
  content: '';
  width: 0.5333rem;
  height: 0.6667rem;
  display: block;
  position: absolute;
  transform: translateY(-40%);
  left: 0.08rem;
  background: url(/images/trangle1.png) no-repeat;
  background-size: 0.32rem 0.32rem;
}
.title-price.up::after,.title-change.up::after {
  content: '';
  width: 0.5333rem;
  height: 0.6667rem;
  display: block;
  position: absolute;
  transform: translateY(-40%);
  left: 0.08rem;
  background: url(/images/trangle2.png) no-repeat;
  background-size: 0.32rem 0.32rem;
}
.title-price.down::after,.title-change.down::after {
  content: '';
  width: 0.5333rem;
  height: 0.6667rem;
  display: block;
  position: absolute;
  transform: translateY(-40%);
  left: 0.08rem;
  background: url(/images/trangle3.png) no-repeat;
  background-size: 0.32rem 0.32rem;
}
.content .list-content {
  display: flex;
  flex-wrap: nowrap;
  height: 1.3333rem;
  overflow: hidden;
  border-bottom: 1px solid #e6e6e6;
}
.list-content>* {
  align-self: center;
}
.list-content>.item-desc {
  padding: 0.2667rem 0 0.1867rem 0.1333rem;
  flex-grow: 2;
}
.list-content>.item-desc>p:nth-child(1) {
  position: relative;
  display: flex;
}
.list-content>.item-desc>p:nth-child(1)>* {
  align-self: flex-end;
}
.list-content>.item-desc>p>span:nth-of-type(1) {
  text-align: center;
  font-size: 0.32rem;
  position: absolute;
  width: 0.5867rem;
  /* left: -0.08rem; */
  top: 44%;
  transform: translateY(-50%);
}
.list-content>.item-desc span>span {
  border-radius: 0.0667rem;
  background-color: #cccccc;
  color: #ffffff;
  display: inline-block;
  min-width: 0.32rem;
  height: 0.32rem;
  box-sizing: border-box;
  padding: 0.0267rem;
  /* padding: 0.04rem 0.08rem; */
  font-size: 0.2133rem;
}
.one {
  background-color: #eb4333 !important;
}
.two {
  background-color: #fe8500 !important;
}
.three {
  background-color: #feb41c !important;
}
.list-content>.item-desc strong {
  padding-left: 0.64rem;
  font-size: 0.3733rem;
  /* font-family: 'NotoSansHans Medium'; */
  color: #333333;
}
.list-content>.item-desc>p>span:nth-of-type(2) {
  font-size: 0.2933rem;
  color: #999999;
  padding-left: 0.2667rem;
}
.list-content>.item-desc>p:nth-of-type(2) {
  font-size: 0.2933rem;
  color: #cccccc;
  padding-left: 0.1333rem;
  display: flex;
}
.list-content>.item-desc>p:nth-of-type(2)>* {
  align-self: flex-end;
}
.list-content>.item-desc>p .icon {
  height: 0.4rem;
  transform: translateY(-0.0133rem);
  /* vertical-align: sub; */
}
.list-content>.item-desc i {
  padding-left: 0.1067rem;
  width: auto;
}
.list-content>.item-desc i::before {
  font-size: 0.2133rem;
}
.list-content>.item-price {
  padding: 0.4267rem 0;
  text-align: right;
  font-size: 0.48rem;
  font-weight: bold;
  margin-right: 0.4rem;
}
.item-price>.icon {
  height: 0.3733rem;
  vertical-align: middle;
}
.item-price img:not(.icon) {
  width: 0.24rem;
  height: 0.24rem;
  display: inline-block;
  margin-left: 0.1067rem;
  border: 0;
}
/* src没有图片时隐藏边框 */
img[src=""] {
  opacity: 0;
}
.item-price.list-up {
  color: #34a853;
}
.item-price.list-down {
  color: #ea4235;
}
.list-content>.item-change {
  padding: 0.4267rem 0;
  text-align: center;
  font-size: 0.32rem;
  flex-basis: 1.7867rem;
  box-sizing: border-box;
  padding-right: 0.1867rem;
}
.list-content>.item-change>span {
  background-color: #34a953;
  padding: 0.1067rem 0;
  color: #ffffff;
  border-radius: 0.1333rem;
  vertical-align: middle;
  width: 1.4667rem;
  display: inline-block;
}
.item-change>span.list-up {
  background-color: #34a853;
}
.item-change>span.list-down {
  background-color: #ea4235;
}

.more {
  width: 100vw;
  text-align: center;
  padding: 0.2667rem 0;
  background-color: #ffffff;
  border-top: 0.1333rem solid #eeeeee;
  border-right: 0;
  border-left: 0;
  font-size: 0.3733rem;
  color: #999999;
  outline: none;
  border-bottom: 0.1333rem solid #eeeeee;
}
</style>
