<template>
  <section class="container">
    <nav>
      <div class="outline">
        <img :src="`https://blockchains.oss-cn-shanghai.aliyuncs.com/static/Exchange/${list.name}.png`" alt="" :onerror="imgError">
        <div>
          <p class="name">{{ list.display_name }}</p>
          <img :src="`/images/star/${list.star}star.png`" alt="">
        </div>
        <div>
          <p class="exchange">{{ format(list.volume_USD, lan, true).num }}</p>
          <span class="ex-name">24H{{ $t('message.block_exchange_volume') }}<img style="height: 0.2667rem;transform: scale(1.4) translateY(0.0267rem);" :src="setCoinImg(rate.symbol, 'gray-ccc')" alt=""></span>
        </div>
      </div>
      <div class="desc">
        <div v-if="list.exchangeType.length > 0">
          <p v-if="$store.state.locale === 'zh'">{{ exType(list.exchangeType).join('、') }}</p>
          <p v-else>{{ exType(list.exchangeType).join(',') }}</p>
          <span>{{ $t('message.api_type') }}</span>
        </div>
        <div v-if="list.country">
          <p v-if="$store.state.locale === 'zh'">{{ !!list.country ? list.country.name : '' }}</p>
          <p v-else>{{ !!list.country ? list.country.en_name : '' }}</p>
          <span>{{ $t('message.block_exchange_country') }}</span>
        </div>
        <div>
          <p>{{ list.pairNum }}</p>
          <span>{{ $t('message.exchange_tab_4') }}</span>
        </div>
      </div>
    </nav>
    <div class="menu">
      <span @click="showContent = 1" :class="{ active : showContent === 1 }">{{ $t('message.market') }}</span>
      <span @click="showContent = 2" :class="{ active : showContent === 2 }">{{ $t('message.coin_baseInfo') }}</span>
      <span class="btn" @click.stop="openCurrency = !openCurrency">{{ rate.symbol }}(<img style="height: 0.2667rem;transform: scale(2);margin: 0 0.0533rem;" :src="setCoinImg(rate.symbol, 'white')">)<i class="drop" :class="{ 'active' : openCurrency === true }"></i></span>
      <div class="currency-menu" v-if="openCurrency">
        <div v-for="item in totalSymbol" :key="item.name" :class="{ active : item.name === rate.symbol }" @click="changeRate(item.name)">
          {{ item.name }}&nbsp;
          <img v-if="item.name === rate.symbol" :src="setCoinImg(item.name, 'white')" alt>
          <img v-else :src="setCoinImg(item.name, 'gray-999')" alt>
        </div>
      </div>
    </div>
    <transition name="switch">
      <div class="content" v-if="showContent === 1">
        <info-list v-if="info.length > 0" :list="info" :rate="rate" :lan="stateLan"></info-list>
        <button v-if="showAll === false" class="data-add" @click="add">{{ loading }}</button>
      </div>
    </transition>
    <transition name="switch">
      <div class="content" v-if="showContent === 2">
        <div class="desc" v-if="desc !== ''">
          <p>{{ desc }}<a href.prevent="#" @click="showDesc" v-if="desc.length > 50">{{ desc === list.descriptions[this.stateLan][0] ? this.$t('message.block_close') : this.$t('message.block_getmoreList') }}</a></p>
        </div>
        <div class="info">
          <div v-if="list.fee.url">
            <span>{{ $t('message.exchange_tab3') }}</span>
            <a :href="list.fee.url">{{ list.fee.url }}</a>
          </div>
          <div v-if="list.link">
            <span>{{ $t('message.block_coin_website') }}</span>
            <a :href="list.link" target="_blank">{{ list.link }}</a>
          </div>
          <div v-if="list.twitter.url">
            <span>Twitter</span>
            <a v-if="list.twitter" :href="list.twitter.url">{{ list.twitter.name }}</a>
          </div>
        </div>
      </div>
    </transition>
  </section>
</template>
<script>
import infoList from './../../components/InfoList'
import axios from 'axios'
import format from './../../static/format.js'
import HttpUtil from '../../plugins/http_util';
export default {
  name: 'exchangeById',
  components: {
    infoList
  },
  async asyncData({ params }) {
    let res = await HttpUtil.get(`exchange/get?name=${params.id}`)
    let resInfo = await HttpUtil.get(`exchange/tickers?name=${params.id}&page=0&size=50`)
    return {
      list: res.data.data,
      desc: res.data.data.descriptions,
      info: resInfo.data.data.tickers,
      page: resInfo.data.data.page,
      pageCount: resInfo.data.data.pageCount
    }
  },
  head(){
    let name = this.list.zhName;
    let title = this.list.name + this.info;
    let keywords = `${name},${name}交易平台,${name}官网,${name}手续费,${name}怎么样,${name}行情,${name}简介,${name}微博/地址,${name}客服,${name}哪个国家的`;
    let content = `block.cc免费提供${name}交易平台实时行情查询，${name}24小时成交量,${name}所有交易对数据，还包括${name}官网，${name}简介,${name}所在国家,${name}客服等数据`;
    return this.$setSeo(title,content,keywords);
  },
  data() {
    return {
      imgError: 'this.src="https://blockchains.oss-cn-shanghai.aliyuncs.com/static/main/noneimg.svg"',
      showContent: 1,
      lan: '',
      rate: {
        type: '$',
        symbol: 'USD',
        rate: 1
      },
      openCurrency: false,
      totalSymbol: [],
      showAll: false,
      info:' | block.cc',
      loading: this.$t('message.load_more')
    }
  },
  watch: {
    stateLan(newVal) {
      this.loading = this.$t('message.load_more')
      this.setDesc()
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
    go(url) {
      this.$router.push({ path: url })
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
    exType (arr) {
      return arr.map((val) => {
        if (val === '1') {
          return this.$t('message.exchange_sort_type_1')
        } else if (val === '2') {
          return this.$t('message.exchange_sort_type_1')
        } else if (val === '3') {
          return '法币'
        } else {
          return ''
        }
      })
    },
    add() {
      this.loading = this.$t('message.loading')
      // this.pageNum = this.list.exchangeInfo.length
      HttpUtil.get(`exchange/tickers?name=${this.$route.params.id}&page=${++this.page}&size=50`).then((res) => {
        this.info = [...this.info, ...res.data.data.tickers]
        this.page = res.data.data.page
        this.pageCount = res.data.data.pageCount
        if (this.page >= this.pageCount - 1) {
          this.showAll = true
        }
        this.loading = this.$t('message.load_more')
      })
    },
    setDesc() {
      if (!!this.list.descriptions && this.list.descriptions[this.stateLan].length !== 0 && this.list.descriptions[this.stateLan][0].length > 50) {
        this.desc = this.list.descriptions[this.stateLan][0].slice(0, 50)
        this.desc += '...'
      } else {
        this.desc = this.list.descriptions[this.stateLan].length !== 0 ? this.list.descriptions[this.stateLan][0] : ''
      }
    },
    showDesc() {
      if (this.desc.length > 53) {
        this.setDesc()
      } else {
        this.desc = this.list.descriptions[this.stateLan][0]
      }
    }
  },
  mounted() {
    this.$store.commit('setUrl', { url: `https://block.cc/exchange/${this.$route.params.id}` })
    // this.lan = localStorage.getItem('lan')
    this.lan = this.$store.state.locale
    this.totalSymbol = JSON.parse(localStorage.getItem('B-symbol'))
    this.setDesc()
    if (this.page >= this.pageCount - 1) {
      this.showAll = true
    }
    document.addEventListener('click', () => {
      this.openCurrency = false
    })
  }
}
</script>
<style scoped>
nav {
  padding: 0.6667rem 0.2667rem 0.5067rem 0.2667rem;
  height: 4rem;
  box-sizing: border-box;
}
nav>.outline {
  display: flex;
  flex-wrap: nowrap;
  margin-bottom: 0.4533rem;
}
nav>.outline>img {
  width: 1.3333rem;
  height: 1.3333rem;
  margin-right: 0.2667rem;
  border-radius: 0.1333rem;
}
nav>.outline>div:first-of-type {
  align-self: center;
  flex-grow: 1;
}
nav>.outline .name {
  font-size: 0.5067rem;
  color: #333333;
}
.outline .name+img {
  width: 2.96rem;
}
nav>.outline>div:last-of-type {
  align-self: center;
  flex-grow: 1;
  text-align: right;
}
nav>.outline .exchange {
  font-size: 0.4533rem;
  color: #333333;
}
nav>.outline .ex-name {
  font-size: 0.2933rem;
  color: #c7c7cc;
}
nav>.desc {
  display: flex;
  flex-wrap: nowrap;
}
nav>.desc>div:nth-child(1) {
  text-align: left;
  width: 3.4667rem;
}
nav>.desc>div:nth-child(2) {
  text-align: center;
  flex-grow: 1;
}
nav>.desc>div:nth-child(3) {
  text-align: right;
  flex-grow: 1;
}
nav>.desc>div>p {
  font-size: 0.4267rem;
  margin-bottom: 0.16rem;
  color: #999999;
}
nav>.desc>div>span {
  font-size: 0.2933rem;
  color: #c7c7cc;
}

.menu {
  padding-right: 0.2667rem;
  position: relative;
  font-size: 0.4267rem;
  border-top: 0.16rem solid #eeeeee;
  box-shadow: #eeeeee 0 0.0267rem 0.0667rem 0;
  /* overflow: hidden; */
}
.menu>span:not(.btn) {
  display: inline-block;
  color: #c7c7cc;
  padding: 0.3733rem 0.32rem 0.3467rem 0.2667rem;
  border-bottom: 0.08rem solid transparent;
}
.menu>span.active {
  border-bottom: 0.08rem solid #2e90ed;
  color: #2e90ed;
}
.menu>.btn {
  background-color: #333333;
  margin: 0.1333rem 0;
  padding: 0.2667rem 0.4267rem 0.2667rem 0.2667rem;
  color: #ffffff;
  height: 1.0667rem;
  box-sizing: border-box;
  font-size: 0.4267rem;
  border-radius: 0.0667rem;
  float: right;
  position: relative;
}
.menu .currency-menu {
  position: absolute;
  top: 86%;
  left: 0;
  right: 0;
  display: flex;
  flex-wrap: wrap;
  z-index: 30;
  background-color: #333333;
}
.menu .currency-menu>div {
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
.menu .currency-menu>div.active {
  background-color: #4f4f4f;
  color: #ffffff;
}
.menu .currency-menu img {
  height: 0.1333rem;
  width: 0.1333rem;
  transform: scale(3.5) translateY(-0.0187rem) translateX(-0.0133rem);
}
.menu .drop {
  display: inline-block;
  border-top: 0.1067rem solid #999999;
  border-left: 0.1067rem solid transparent;
  border-right: 0.1067rem solid transparent;
  position: absolute;
  right: 0.1867rem;
  top: 50%;
  transition: all .5s;
  transform: rotate(0deg);
}
.menu .drop.active {
  transform: rotate(180deg);
}
/* 切换动画 */
.switch-enter-active, .switch-leave-active {
  transition: opacity .2s;
}
.switch-enter, .switch-leave-to {
  opacity: 0;
}
.switch-enter-to, .switch-leave {
  opacity: 1;
}

.content>.desc {
  padding: 0.4rem 0.2667rem;
  font-size: 0.4267rem;
  color: #333333;
  border-bottom: 1px solid #e6e6e6;
}
.content a {
  text-decoration: none;color: #2e90ed;
}
.content>.info {
  min-height: 4.2667rem;
  padding: 0.2667rem;
}
.content>.info>div {
  padding: 0.2rem 0;
  color: #c7c7cc;
  font-size: 0.2933rem;
}
.content>.info a {
  float: right;
  width: 5rem;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  text-align: right;
}
.data-add {
  width: 100%;
  padding: 2.667vw 0;
  font-size: 3.733vw;
  border-top: 1.33vw solid #eeeeee;
  border-bottom: 1.33vw solid #eeeeee;
  background-color: #ffffff;
  color: #999999;
  outline: none;
}
</style>
