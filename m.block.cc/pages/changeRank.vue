<template>
  <section class="container">
    <nav>
      <div :class="{ active : $route.query.orderby === '-1' }" @click="go('/changeRank?orderby=-1')"><span>{{ $t('message.home_title2') }}</span></div>
      <div :class="{ active : $route.query.orderby === '1' }" @click="go('/changeRank?orderby=1')"><span>{{ $t('message.home_title3') }}</span></div>
    </nav>
    <div class="change-time">
      <div @click="change = 'change1h'" :class="{ active : change === 'change1h' }">
        <span>{{ $t('message.home_1h') }}</span>
      </div>
      <div @click="change = 'change1d'" :class="{ active : change === 'change1d' }">
        <span>{{ $t('message.home_1d') }}</span>
      </div>
      <div @click="change = 'change7d'" :class="{ active : change === 'change7d' }">
        <span>{{ $t('message.home_1w') }}</span>
      </div>
      <div class="limit" @click.stop="showMarketMenu = !showMarketMenu">
        <span>{{ whoSelMarket === 1 ? $t('message.defalut_q200') : $t('message.defalut_all') }}</span>
        <div class="limit-menu" v-show="showMarketMenu === true">
          <p :class="{ 'active' : whoSelMarket === 1 }" @click="selectMenu(1)">{{ $t('message.defalut_q200') }}</p>
          <p :class="{ 'active' : whoSelMarket === 2 }" @click="selectMenu(2)">{{ $t('message.defalut_all') }}</p>
        </div>
      </div>
    </div>
    <div class="content">
      <div class="list">
        <div class="list-head">
          <span>{{ $t('message.coin_name') }}</span>
          <span @click="setSel(1); dataSort()">{{ $t('message.rise_decline') }}<i class="title-change" :class="addUp(1)"></i></span>
          <span @click="setSel(2); dataSort()">{{ $t('message.coin_price') }}<i class="title-price" :class="addUp(2)"></i></span>
        </div>
        <div class="list-content" v-for="(item, index) in list" :key="index" @click="go(`/coin/${item.id}`)">
          <div class="item-desc">
            <p>
              <span><span :class="num(index + 1)">{{ index + 1 }}</span></span>
              <strong>{{ item.symbol }}</strong>
              <span v-if="$store.state.locale === 'zh'">{{ item.zhName ? item.zhName : item.name }}</span>
              <span v-else>{{ item.name }}</span>
            </p>
            <p>{{ $t('message.coin_volume') }}<small style="transform: translateY(2px);display: inline-block;font-size: 0.34rem;"><img style="height: 0.34rem;transform: translateY(0.0533rem);" src="/images/money-icon/gray-ccc/USD.png" alt="">{{ format(item.volume_ex * rate.rate, lan, true).num }}{{ format(item.volume_ex, lan, true).unit }}</small></p>
          </div>
          <div class="item-price">
            <img style="height: 0.3467rem;width: 0.3467rem;transform: translateY(0.0667rem);" src="/images/money-icon/black/USD.png" alt="">
            <span>{{ format(item.price * rate.rate, lan, true).num }}</span>
          </div>
          <div class="item-change">
            <span :class="[{ 'list-up' : item[change] > 0 }, { 'list-down' : item[change] < 0 }]">{{ item[change] > 0 ? `+${item[change]}` : item[change] }}%</span>
          </div>
        </div>
      </div>
      <button class="more" @click="add" v-show="page !== pageCount - 1">{{ loading }}</button>
    </div>
  </section>
</template>
<script>
import axios from 'axios'
import format from './../static/format.js'
import HttpUtil from './../plugins/http_util';
export default {
  name: 'rank',
  async asyncData({query}) {
    let res = await HttpUtil.get(`coin/list?size=50&select=change1d&orderby=${query.orderby}&limitType=1`)
    return {
      list: res.data.data.list,
      page: res.data.data.page,
      pageCount: res.data.data.pageCount
    }
  },
  data() {
    return {
      rate: {
        type: '$',
        symbol: 'USD',
        rate: 1
      },
      lan: '',
      test: [],
      totalSymbol: [],
      whoSel: '',
      isUp: false,
      loading: this.$t('message.load_more'),
      change: 'change1d',
      orderby: 1,
      limitType: '&limitType=1',
      showMarketMenu: false,
      whoSelMarket: 1
    }
  },
  watch: {
    change(newVal) {
      this.whoSel = ''
      HttpUtil.get(`coin/list?size=50&select=${newVal}&orderby=${this.$route.query.orderby}${this.limitType}`).then((res) => {
        this.list = res.data.data.list
        this.page = res.data.data.page
        this.pageCount = res.data.data.pageCount
      })
      this.$store.commit('setUrl', { url: `https://block.cc/changeRank?select=${this.change}&orderby=${this.$route.query.orderby}${this.limitType}` })
    },
    limitType(newVal) {
      this.whoSel = ''
      HttpUtil.get(`coin/list?size=50&select=${this.change}&orderby=${this.$route.query.orderby}${newVal}`).then((res) => {
        this.list = res.data.data.list
        this.page = res.data.data.page
        this.pageCount = res.data.data.pageCount
      })
      this.$store.commit('setUrl', { url: `https://block.cc/changeRank?select=${this.change}&orderby=${this.$route.query.orderby}${this.limitType}` })
    },
    '$route'(to, from) {
      this.orderby = this.$route.query.orderby
      HttpUtil.get(`coin/list?size=50&select=${this.change}&orderby=${this.orderby}${this.limitType}`).then((res) => {
        this.list = res.data.data.list
        this.page = res.data.data.page
        this.pageCount = res.data.data.pageCount
      })
      this.$store.commit('setUrl', { url: `https://block.cc/changeRank?select=${this.change}&orderby=${this.$route.query.orderby}${this.limitType}` })
    },
    stateLan(newVal) {
      this.loading = this.$t('message.load_more')
    }
  },
  computed:{
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
    selectMenu(val) {
      this.whoSelMarket = val
      if (this.whoSelMarket === 1) {
        this.limitType = '&limitType=1'
      } else {
        this.limitType = ''
      }
      HttpUtil.get(`coin/list?page=${this.page + 1}&size=50&select=${this.change}&orderby=${this.$route.query.orderby}${this.limitType}`).then((res) => {
        if (res.data.data.list.length !== 0) {
          this.list = [...this.list, ...res.data.data.list]
          this.page = res.data.data.page
          this.pageCount = res.data.data.pageCount
        }
        this.loading = this.$t('message.load_more')
      })
    },
    add() {
      this.loading = this.$t('message.loading')
      if (this.page !== this.pageCount - 1) {
        HttpUtil.get(`coin/list?page=${this.page + 1}&size=50&select=${this.change}&orderby=${this.$route.query.orderby}${this.limitType}`).then((res) => {
          if (res.data.data.list.length !== 0) {
            this.list = [...this.list, ...res.data.data.list]
            this.page = res.data.data.page
            this.pageCount = res.data.data.pageCount
          }
          this.loading = this.$t('message.load_more')
        })
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
              return b[this.change] - a[this.change]
            })
          } else {
            this.list = this.list.sort((a, b) => {
              return a[this.change] - b[this.change]
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
    }
  },
  mounted() {    
    this.lan = localStorage.getItem('lan')
    this.totalSymbol = JSON.parse(localStorage.getItem('B-symbol'))
    this.$store.commit('setUrl', { url: `https://block.cc/changeRank?select=${this.change}&orderby=${this.$route.query.orderby}${this.limitType}` })
    document.addEventListener('click', () => {
      this.showMarketMenu = false
    })
  }
}
</script>
<style scoped>
nav {
  display: flex;
}
nav>div {
  width: 50%;
  border-bottom: 1px solid #e6e6e6;
  background-color: #ffffff;
  font-size: 0.4267rem;
  text-align: center;
  padding: 0.4rem 0;
}
nav>div:first-child {
  border-right: 1px solid #e6e6e6;
}
nav>div.active {
  background-color: #f5f5f5;
  color: #2e90ed;
}
.change-time {
  display: flex;
  padding: 0 0.2667rem;
  background-color: #f5f5f5;
  border-bottom: 1px solid #e6e6e6;
}
.change-time>div {
  flex-grow: 1;
  margin: 0 0.3467rem;
  text-align: center;
  font-size: 0.32rem;
  color: #333333;
  border-bottom: 5px solid transparent;
  padding: 0.2667rem 0;
}
.change-time>div.active {
  color: #2e90ed;
  border-bottom: 5px solid #2e90ed;
}
.change-time>div.limit {
  position: relative;
  margin: 0;
  border-bottom: 0;
  background-color: #333333;
  color: #ffffff;
  text-align: left;
  flex-grow: 0;
  border-radius: 0.0667rem;
  box-sizing: border-box;
  width: 2.2667rem;
  padding: 0.2667rem 0.4rem 0.2667rem 0.16rem;
}
.limit::after {
  content: '';
  display: inline-block;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  right: 0.0667rem;
  border-top: 0.1067rem solid #999999;
  border-left: 0.1067rem solid transparent;
  border-right: 0.1067rem solid transparent;
}
.limit .limit-menu {
  position: absolute;
  z-index: 20;
  background-color: #333333;
  top: 100%;
  left: 0;
  right: 0;
}
.limit .limit-menu>p.active,.limit .limit-menu>p:active {
  color: #ffffff;
  background-color: #4f4f4f;
}
.limit .limit-menu>p {
  color: #999999;
  padding: 0.2667rem 0.3733rem 0.2667rem 0.1333rem;
}

.content .list>.list-head {
  font-size: 0.2933rem;
  padding: 0.1067rem 0.4rem;
  color: #999999;
  border-bottom: 1px solid #e6e6e6;
}
.content .list>.list-head>* {
  display: inline-block;
}
.content .list>.list-head>span:nth-child(2),.content .list>.list-head>span:nth-child(3) {
  float: right;
  width: 1.7867rem;
  text-align: right;
}
.title-price,.title-change {
  position: relative;
  display: inline-block;
  width: 0.3467rem;
}
.title-price::after,.title-change::after {
  content: '';
  width: 0.5333rem;
  height: 0.6667rem;
  display: block;
  position: absolute;
  transform: translateY(-40%);
  left: 0;
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
  left: 0;
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
  left: 0;
  background: url(/images/trangle3.png) no-repeat;
  background-size: 0.32rem 0.32rem;
}
.content .list-content {
  display: flex;
  flex-wrap: nowrap;
  height: 1.36rem;
  border-bottom: 1px solid #e6e6e6;
}
.list-content>.item-desc {
  padding: 0.2667rem 0 0.1867rem 0.4rem;
  flex-grow: 2;
}
.list-content>.item-desc>p:nth-child(1) {
  position: relative;
}
.list-content>.item-desc>p>span:nth-of-type(1) {
  text-align: right;
  font-size: 0.2933rem;
  position: absolute;
  width: 0.75rem;
  left: -7%;
  top: 50%;
  transform: translateY(-50%);
}
.list-content>.item-desc span>span {
  border-radius: 0.0667rem;
  background-color: #cccccc;
  color: #ffffff;
  display: inline-block;
  text-align: center;
  /* padding: 0.04rem 0.0933rem; */
  min-width: 0.3467rem;
  box-sizing: border-box;
  font-size: 0.2667rem;
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
  padding-left: 0.4533rem;
  font-size: 0.3733rem;
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
}
.list-content>.item-desc i {
  padding-right: 0.1067rem;
}
.list-content>.item-price {
  text-align: right;
  font-size: 0.4267rem;
  align-self: center;
  font-weight: bold;
}
.item-price img {
  width: 20px;
  height: 20px;
  display: inline-block;
}
.item-price.list-up {
  color: #34a853;
}
.item-price.list-down {
  color: #ea4235;
}
.list-content>.item-change {
  text-align: right;
  font-size: 0.2933rem;
  margin-right: 0.4rem;
  width: 1.7867rem;
  align-self: center;
}
.list-content>.item-change>span {
  /* background-color: #34a953; */
  padding: 0.1333rem 0.16rem;
  color: #ffffff;
  border-radius: 0.1333rem;
  vertical-align: sub;
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
