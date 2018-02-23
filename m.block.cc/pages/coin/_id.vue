<template>
  <section class="container">
    <nav>
      <p class="name">
        <img :src="`https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/${list.id}.png`" :onerror="imgError"/>
        <span>{{list.symbol}}-{{list.zhName}}({{list.name}})</span>
        <span class="rate" :class="{ 'active' : openCurrency === true }" @click.stop="openCurrency = !openCurrency">
          {{ rate.symbol }}&nbsp;<img style="height: 0.3467rem;transform: translateY(-0.0533rem);margin: -0.1067rem;" :src="setCoinImg(rate.symbol, 0)" alt>&nbsp;
        </span>
        <span class="currency-menu" v-if="openCurrency">
          <div v-for="item in totalSymbol" :key="item.name" :class="{ active : item.name === rate.symbol }" @click="changeRate(item.name)">
            {{ item.name }}&nbsp;
            <img v-if="item.name === rate.symbol" :src="setCoinImg(item.name, 'white')" alt>
            <img v-else :src="setCoinImg(item.name, 'gray-999')" alt>
          </div>
        </span>
      </p>
      <div>
        <p class="total">
          <img style="height: 0.4267rem;transform: translateY(0.0533rem);" :src="setCoinImg(rate.symbol, 0)" alt="">
          <span>{{format(list.price * rate.rate, stateLan, true).num}}{{format(list.price * list.CNY_RATE, stateLan, true).unit}}</span>
          <span :style="{ 'background-color': list.change1d >= 0 ? '#34a953' : '' }">{{list.change1d}}%</span>
        </p>
        <p class="sub">
          <span>
            <img style="height: 0.3467rem;transform: translateY(0.06rem);" :src="setCoinImg('USD', 0)" alt="">{{format(list.price, stateLan, true).num}}{{format(list.price, stateLan, true).unit}}
          </span>
          <span>
            <img style="height: 0.3467rem;transform: translateY(0.06rem);" :src="setCoinImg('BTC', 0)" alt="">{{format(list.price * list.BTC_RATE, stateLan, true).num}}{{format(list.price * list.BTC_RATE, stateLan, true).unit}}
          </span>
          <span>24H{{ $t('message.rise_decline') }}</span>
        </p>
        <p class="info" v-if="ico !== '' && icoCost !== 0">
          <span>{{ $t('message.ico_cost') }}：</span>
          <span>
            <img style="height: 0.28rem;transform: scale(1.5);margin-right: 0.0267rem;" :src="setCoinImg('USD', 0)" alt>{{ icoCost }}
          </span>
          <span>{{ (list.price / icoCost).toFixed(2) }}{{ $t('message.defalut_roi') }}<i class="fa fa-long-arrow-up"></i></span>
        </p>
      </div>
    </nav>
    <aside class="chart">
      <div>
        <p class="title">
          <span>{{ $t('message.block_coin_marketCap') }}</span>
          <span style="margin-right: 0.1333rem;">{{ $t('message.rank') }}:{{list.level}}</span>
        </p>
        <p class="cny">
          <span>
            <img style="height: 0.3467rem;transform: translateY(0.06rem) translateX(-0.0533rem);margin-right: -0.08rem;" :src="setCoinImg(rate.symbol, 0)" alt="">{{format(list.marketCap * rate.rate, stateLan, false).num}}
          </span>
          <span style="transform: translateY(-0.0133rem);display: inline-block;">{{format(list.marketCap * list.CNY_RATE, stateLan, false).unit}}</span>
        </p>
        <p class="usd">
          <span>
            <img style="height: 0.32rem;transform: translateY(0.06rem) translateX(-0.0533rem);margin-right: -0.08rem;" :src="`/images/money-icon/gray-999/USD.png`" alt="">{{format(list.marketCap, stateLan, false).num}}
          </span>
          <span>{{format(list.marketCap, stateLan, false).unit}}</span>
        </p>
        <p class="btc">
          <span>
            <img style="height: 0.32rem;transform: translateY(0.05rem) translateX(-0.0533rem);margin-right: -0.08rem;" :src="`/images/money-icon/gray-999/BTC.png`" alt="">{{format(list.marketCap * list.BTC_RATE, stateLan, false).num}}
          </span>
          <span>{{format(list.marketCap * list.BTC_RATE, stateLan, false).unit}}</span>
        </p>
        <!-- <div class="range">
          <p>{{format(list.marketCap / total.total_market_cap_usd * 100, lan, true).num}}%</p>
          <span>占全球总市值</span>
        </div> -->
      </div>
      <div>
        <p class="title">
          <span>{{ $t('message.coin_volume') }}</span>
          <small>(24H)</small>
          <span>{{ $t('message.rank') }}:{{list.volume_level}}</span>
        </p>
        <p class="cny">
            <img style="height: 0.3467rem;transform: translateY(0.06rem) translateX(-0.0533rem);margin-right: -0.08rem;" :src="setCoinImg(rate.symbol, 0)" alt="">{{format(list.volume_ex * rate.rate, stateLan, false).num}}
          </span>
          <span style="transform: translateY(-0.0267rem);display: inline-block;">{{format(list.volume_ex * list.CNY_RATE, stateLan, false).unit}}</span>
        </p>
        <p class="usd">
          <span>
            <img style="height: 0.32rem;transform: translateY(0.06rem) translateX(-0.0533rem);margin-right: -0.08rem;" :src="`/images/money-icon/gray-999/USD.png`" alt="">{{format(list.volume_ex, stateLan, false).num}}
          </span>
          <span>{{format(list.volume_ex, stateLan, false).unit}}</span>
        </p>
        <p class="btc">
          <span>
            <img style="height: 0.32rem;transform: translateY(0.05rem) translateX(-0.0533rem);margin-right: -0.08rem;" :src="`/images/money-icon/gray-999/BTC.png`" alt="">{{format(list.volume_ex * list.BTC_RATE, stateLan, false).num}}
          </span>
          <span>{{format(list.volume_ex * list.BTC_RATE, stateLan, false).unit}}</span>
        </p>
        <!-- <div class="range">
          <p>{{format(list.volume_ex / list.price / list.available_supply * 100, lan, true).num}}%</p>
          <span>换手率</span>
        </div> -->
      </div>
      <div>
        <p class="title" style="padding-bottom: 0.1333rem;">
          <span>{{ $t('message.block_coin_available') }}</span>
        </p>
        <p class="cny">
          <span>{{format(list.available_supply, stateLan).num.slice(0, -3)}}{{format(list.supple, stateLan).unit}}</span>
          <span style="transform: translateY(0.0267rem);display: inline-block;">{{list.symbol}}</span>
        </p>
        <p style="color: #cccccc;padding-bottom: 0;margin-bottom: -0.0667rem;">
          <span>{{ $t('message.block_coin_total') }}</span>
        </p>
        <p class="btc">
          <span style="color: #333333;font-size: 0.3733rem;">{{format(list.supple, stateLan).num.slice(0, -3)}}{{format(list.supple, stateLan).unit}}</span>
          <span>{{list.symbol}}</span>
        </p>
        <!-- <div class="range">
          <p>{{format(list.available_supply / list.supple * 100, lan, true).num}}%</p>
          <span>流通率</span>
        </div> -->
      </div>
    </aside>
    <div class="menu">
      <div class="btn-group">
        <button @click="detail = 'market'" :class="{ 'active' : detail === 'market' }">{{ $t('message.market') }}</button>
        <button @click="detail = 'base'" :class="{ 'active' : detail === 'base' }">{{ $t('message.coin_baseInfo') }}</button>
      </div>
      <transition name="switch">
        <div class="market" v-if="detail === 'market'">
          <div class="list">
            <div class="left">
              <table>
                <thead class="title">
                  <tr>
                    <td>{{ $t('message.block_coin_exchange') }}</td>
                  </tr>
                </thead>
                <tbody style="background: #ffffff;">
                  <tr v-for="(item,index) in tableData" :key="index" @click="go(`/exchange/${item.exchange_name}`)">
                    <td>
                      <p>{{ headUpperCase(item.exchange_display_name) }}<span>{{ item.display_pair_name.split('/')[1] }}</span></p>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="right">
              <table>
                <thead>
                  <tr>
                    <td>名称</td>
                    <td>名称</td>
                    <td @click="setSel(1); dataSort()">{{ $t('message.block_coin_price') }}<i class="price-name" :class="addUp(1)"></i></td>
                    <td @click="setSel(4); dataSort()">{{ $t('message.block_exchange_volume') }}<i class="volumn-name" :class="addUp(4)"></i></td>
                    <td>{{ $t('message.block_exchange_volume') }}(%)</td>
                    <td @click="setSel(2); dataSort()">{{ $t('message.coin_title_24high') }}<i class="high-name" :class="addUp(2)"></i></td>
                    <td @click="setSel(3); dataSort()">{{ $t('message.coin_title_24low') }}<i class="low-name" :class="addUp(3)"></i></td>
                    <td>{{ $t('message.updateTime') }}</td>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in tableData" :key="index">
                    <td>1</td>
                    <td>1</td>
                    <td>
                      <p>
                        <img style="height: 0.3733rem;transform: translateY(0.0533rem);" :src="setCoinImg(rate.symbol, 0)" alt="">{{ format(item.price * rate.rate, stateLan, true).num }}
                      </p>
                      <p class="sub">
                        <img style="height: 0.32rem;transform: translateY(0.0667rem);" :src="setCoinImg(item.display_pair_name.split('/')[1], 'gray-999')" alt="">{{ format(item.native_price, stateLan, true).num }}
                      </p>
                    </td>
                    <td>
                      <span>
                        <img style="height: 0.3733rem;transform: translateY(0.0533rem);" :src="setCoinImg(rate.symbol, 0)" alt="">{{ format(item.volume * rate.rate, stateLan).num }}
                        <span style="transform: translateY(2px);">{{ format(item.volume	 * rate.rate, stateLan).unit }}</span>
                      </span>
                    </td>
                    <td class="change"><span>{{ item.percent }}</span></td>
                    <td>
                      <span>
                        <img style="height: 0.3733rem;transform: translateY(0.0533rem);" :src="setCoinImg(rate.symbol, 0)" alt="">{{ format(item.high1d	 * rate.rate, stateLan, true).num }}
                      </span>
                    </td>
                    <td>
                      <span>
                        <img style="height: 0.3733rem;transform: translateY(0.0533rem);" :src="setCoinImg(rate.symbol, 0)" alt="">{{ format(item.low1d * rate.rate, stateLan, true).num }}
                      </span>
                    </td>
                    <td><span>{{ formatDate(item.timestamps) }}</span></td>
                  </tr>
                </tbody>
              </table>
            </div>
            <!-- <v-table :width="1700" :height="200" :columns="columns" :table-data="tableData"></v-table> -->
          </div>
          <button v-if="showAll === false" class="data-add" @click="add">{{ loading }}</button>
        </div>
      </transition>
      <transition name="switch">
        <div class="base" v-if="detail === 'base'">
          <p v-if="list.descriptions.zh.length > 0 && $store.state.locale === 'zh'">{{ desc }}<a href.prevent="#" @click="showDesc" v-if="desc.length > 50">{{ desc === list.descriptions.zh[0] ? $t('message.block_close') : $t('message.block_getmoreList') }}</a></p>
          <p v-if="list.descriptions.en.length > 0 && $store.state.locale === 'en'">{{ desc }}<a href.prevent="#" @click="showDesc" v-if="desc.length > 50">{{ desc === list.descriptions.en[0] ? $t('message.block_close') : $t('message.block_getmoreList') }}</a></p>
          <!-- <p v-if="lan === 'en'" v-for="(item, index) in list.descriptions.en" :key="index">{{ item }}</p> -->
          <dl>
            <dd v-if="list.name">
              <div>{{ $t('message.english_name') }}:</div>
              <div>{{ list.name }}</div>
            </dd>
          </dl>
          <dl>
            <dd v-if="list.zhName">
              <div>{{ $t('message.chinese_name') }}:</div>
              <div>{{ list.zhName }}</div>
            </dd>
          </dl>
          <dl>
            <dd v-if="list.symbol">
              <div>{{ $t('message.ico_shortName') }}:</div>
              <div>{{ list.symbol }}</div>
            </dd>
          </dl>
          <dl>
            <dd v-if="date">
              <div>{{ $t('message.block_coin_time') }}:</div>
              <div>{{ date }}</div>
            </dd>
          </dl>
          <dl>
            <dd v-if="list.Support.length > 0">
              <div>{{ $t('message.block_coin_support') }}:</div>
              <div>{{ list.Support.length }}</div>
            </dd>
          </dl>
          <dl>
            <dd v-if="list.whitepaper">
              <div>{{ $t('message.block_coin_whitepaper') }}:</div>
              <div><a v-if="list.whitepaper" :href="list.whitepaper">{{ $t('message.defalut_download') }}</a></div>
            </dd>
          </dl>
          <dl>
            <dd v-if="list.websites.length > 0">
              <div>{{ $t('message.block_coin_website') }}:</div>
              <div><a v-if="list.websites" v-for="(item, index) in list.websites" :key="index" :href="item">{{ $t('message.block_coin_website') }}{{ index + 1 === list.websites.length ? `${index + 1}` : `${index + 1}、` }}</a></div>
            </dd>
          </dl>
          <dl>
            <dd v-if="list.Explorers.length > 0">
              <div>{{ $t('message.home_blockurl') }}:</div>
              <div><a v-if="list.Explorers" v-for="(item, index) in list.Explorers" :key="index" :href="item">{{ $t('message.home_blockurl') }}{{ index + 1 === list.Explorers.length ? `${index + 1}` : `${index + 1}、` }}</a></div>
            </dd>
          </dl>
          <dl>
            <dd v-if="list.message.length > 0">
              <div>{{ $t('message.block_coin_forum') }}:</div>
              <div><a v-if="list.message" v-for="(item, index) in list.message" :key="index" :href="item">{{ $t('message.block_coin_forum') }}{{ index + 1 === list.message.length ? `${index + 1}` : `${index + 1}、` }}</a></div>
            </dd>
          </dl>
          <dl>
            <dd>
              <div>ICO{{ $t('message.action_more') }}:</div>
              <div><a href="https://block.cc/ico">https://block.cc/ico</a></div>
            </dd>
          </dl>
        </div>
      </transition>
      <!-- <transition name="switch">
        <div class="trend" v-if="detail === 'trend'">
          <div>
            <p style="padding: 1.33vw;" v-if="!list.hasKline">{{ no }}</p>
            <line-chart v-else :id="`${ list.id }`" :content="k" :type="rate.symbol" :rate="rate"></line-chart>
          </div>
        </div>
      </transition> -->
      <!-- <transition name="switch">
        <div class="ico" v-if="detail === 'ico'">
          <div>
            <dl class="ico-list">
              <dd>
                <div>状态:</div>
                <div>已完成</div>
              </dd>
              <dd>
                <div>众筹开始时间:</div>
                <div>2014-02-02</div>
              </dd>
            </dl>
          </div>
          <div class="desc">
            <p>
              <span>XXX项目介绍</span>
            </p>
            <div class="content">//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css<a href="#">查看更多</a></div>
          </div>
          <div class="desc">
            <p>
              <span>XXXXX团队信息</span>
            </p>
            <div class="people">
              <p><i class="fa fa-user-circle-o fa-fw"></i>Country of origin (data missing) --</p>
              <p><i class="fa fa-user-circle-o fa-fw"></i>Country of origin (data missing) --</p>
            </div>
          </div>
          <div class="desc">
            <p>
              <span>媒体&链接</span>
            </p>
            <div class="link">
              <div>
                <img src="/images/detail/websiteicon.png"/>
                <span><a href="#">官网</a></span>
              </div>
              <div>
                <img src="/images/detail/websiteicon.png"/>
                <span><a href="#">官网</a></span>
              </div>
              <div>
                <img src="/images/detail/websiteicon.png"/>
                <span><a href="#">官网</a></span>
              </div>
            </div>
          </div>
          <div class="desc">
            <p>
              <span>技术</span>
            </p>
            <div class="tech">
              <dl class="ico-list">
                <dd>
                  <div>Blockchain:</div>
                  <div>ESC</div>
                </dd>
                <dd>
                  <div>Country:</div>
                  <div>Unknown</div>
                </dd>
              </dl>
            </div>
          </div>
        </div>
      </transition> -->
    </div>
  </section>
</template>
<script>
import axios from 'axios'
import format from './../../static/format.js'
import HttpUtil from '../../plugins/http_util';
// import i18n from './../../plugins/i18n'
export default {
  // i18n,
  name: 'coin',
  async asyncData({ params, store, req }) {
    
    // console.log(i18n.locale);
    // console.log(req.headers.cookie);
    
    // let temp = req.headers.cookie.split(';')
    // console.log(temp[temp.length - 1].split('=')[1]);
    // console.log('qwert');
    // i18n.locale = temp[temp.length - 1].split('=')[1]
    // console.log(i18n.locale);
    let res = await HttpUtil.get('coin/get',{ params: { coin: params.id}})
    let total = await HttpUtil.get('getBaseTotalInfo')
    let resTable = await HttpUtil.get('coin/tickers',{ params: { coin: params.id,page:0,size:50}})
    let cnyRate = res.data.data.CNY_RATE
    return {
      list: res.data.data,
      total: total.data.data,
      date: new Date(res.data.data.publicTime).toLocaleDateString().replace(/\//g, '-'),
      desc: res.data.data.descriptions,
      tableData: resTable.data.data.tickers,
      page: resTable.data.data.page,
      pageCount: resTable.data.data.pageCount,
      ico: res.data.data.ico ? res.data.data.ico.icoPrice : '',
      rate: {
        type: '￥',
        symbol: 'CNY',
        rate: cnyRate
      },
    }
  },
  data() {
    return {
      imgError: 'this.src="https://blockchains.oss-cn-shanghai.aliyuncs.com/static/main/noneimg.svg"',
      // list: [],
      rateMenu: false,
      lan: '',
      totalSymbol: [],
      detail: 'market',
      dateList: [],
      showAll: false,
      kline: [],
      whoSel: '',
      isUp: false,
      cnyRate: 0,
      usdRate: 1,
      icoCost: 0,
      loading: this.$t('message.load_more'),
      openCurrency: false
    }
  },
  head(){
    let price = format(this.list.price * this.cnyRate, this.stateLan, true).num;
    // let title = price+''+this.rate.symbol;
    let title = price+''+'CNY';
    let keywords = `${this.list.name},${this.list.symbol},${this.list.zhName},${this.list.zhName}价格,${this.list.zhName}行情,${this.list.zhName}是什么,${this.list.zhName}交易网,${this.list.zhName}交易平台,如何购买${this.list.zhName},${this.list.zhName}官网,${this.list.zhName}价格,${this.list.zhName}骗局,${this.list.name} Ico信息`;
    let content = `block.cc免费提供${this.list.zhName}今日价格查询，${this.list.zhName}涨跌数据以及全球的${this.list.zhName}交易平台，还包括${this.list.zhName}官网，${this.list.zhName}市值排行，${this.list.zhName}流通及发行数量，${this.list.zhName}历史行情趋势等数据`;
    return this.$setSeo(title,content,keywords);
  },
  watch: {
    detail() {
      if (this.detail !== 'market') {
        this.showAll = false
      }
    },
    stateLan(newVal) {
      this.loading = this.$t('message.load_more')
    }
  },
  computed: {
    stateLan() {
      return this.$store.state.locale
    },
    k() {
      let total = []
      this.kline.map((val) => {
        total.push({
          data: val.data.map((iVal) => {
            let data = [...iVal]
            data[1] = iVal[1] * this.rate.rate
            return data
          })
        })
      })
      return total
    }
  },
  methods: {
    format(num, lan, isPrice) {
      return format(num, lan, isPrice)
    },
    go(url) {
      this.$router.push({ path: url })
    },
    formatDate(time) {
      let date = new Date().getTime()
      let diffTime = date - time
      let day = Math.floor(diffTime / (24 * 3600 * 1000))
      if (day > 0) {
        let d = new Date()
        d.setTime(date)
        return (`${d.getFullYear()}-${d.getMonth() + 1}-${d.getDate()} ${d.getHours()}:${d.getMinutes()}:${d.getSeconds()}`)
      } else {
        let leave1 = diffTime % (24 * 3600 * 1000)
        let hours = Math.floor(leave1 / (3600 * 1000))
        if (hours > 0) {
          return (hours + ' ' + this.$t('message.onehourbefore'))
        } else {
          let leave2 = leave1 % (3600 * 1000)
          let minutes = Math.floor(leave2 / (60 * 1000))
          if (minutes > 0) {
            return (minutes + ' ' + this.$t('message.oneminbefore'))
          } else {
            return (this.$t('message.recentbefore'))
          }
        }
      }
    },
    headUpperCase(str) {
      let temp = str[0].toUpperCase()
      return temp + str.slice(1)
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
      if (this.rate.symbol === 'CNY') {
        this.rate = {
          type: symbol.symbol,
          symbol: val,
          rate: this.list.CNY_RATE
        }
      }
      if (this.rate.symbol === 'BTC') {
        this.rate = {
          type: symbol.symbol,
          symbol: val,
          rate: this.list.BTC_RATE
        }
      }
    },
    setCNY() {
      let cny = JSON.parse(localStorage.getItem('B-option')).rates
      this.cnyRate = cny.CNY
    },
    setDesc() {
      // console.log(this.stateLan);
      
      if (!!this.list.descriptions && this.list.descriptions[this.stateLan].length !== 0 && this.list.descriptions[this.stateLan][0].length > 50) {
        this.desc = this.list.descriptions[this.$store.state.locale][0].slice(0, 50)
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
        case 1:
          if (this.isUp) {
            this.tableData = this.tableData.sort((a, b) => {
              return b.price - a.price
            })
          } else {
            this.tableData = this.tableData.sort((a, b) => {
              return a.price - b.price
            })
          }
          break
        case 2:
          if (this.isUp) {
            this.tableData = this.tableData.sort((a, b) => {
              return b.high1d - a.high1d
            })
          } else {
            this.tableData = this.tableData.sort((a, b) => {
              return a.high1d - b.high1d
            })
          }
          break
        case 3:
          if (this.isUp) {
            this.tableData = this.tableData.sort((a, b) => {
              return b.low1d - a.low1d
            })
          } else {
            this.tableData = this.tableData.sort((a, b) => {
              return a.low1d - b.low1d
            })
          }
          break
        case 4:
          if (this.isUp) {
            this.tableData = this.tableData.sort((a, b) => {
              return b.volume - a.volume
            })
          } else {
            this.tableData = this.tableData.sort((a, b) => {
              return a.volume - b.volume
            })
          }
          break
        default:
          this.tableData = this.tableData.sort((a, b) => {
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
    add() {
      this.loading = this.$t('message.loading')
      // this.pageNum = this.list.exchangeInfo.length
      HttpUtil.get(`coin/tickers?coin=${this.$route.params.id}&page=${++this.page}&size=50`).then((res) => {
        this.tableData = [...this.tableData, ...res.data.data.tickers]
        this.page = res.data.data.page
        this.pageCount = res.data.data.pageCount
        if (this.page >= this.pageCount - 1) {
          this.showAll = true
        }
        this.loading = this.$t('message.load_more')
      })
    },
    // getList() {
    //   axios.get(`https://block.cc/api/v1/coin/get?coin=${this.$route.params.id}`).then((res) => {
    //     this.list = res.data.data
    //     this.date = new Date(this.list.publicTime).toLocaleDateString().replace(/\//g, '-')
    //     let date = new Date().getTime()
    //     for (let item of this.list.exchangeInfo) {
    //       let diffTime = date - item.timestamps
    //       let day = Math.floor(diffTime / (24 * 3600 * 1000))
    //       if (day > 0) {
    //         let d = new Date()
    //         d.setTime(date)
    //         this.dateList.push(`${d.getFullYear()}-${d.getMonth() + 1}-${d.getDate()} ${d.getHours()}:${d.getMinutes()}:${d.getSeconds()}`)
    //       } else {
    //         let leave1 = diffTime % (24 * 3600 * 1000)
    //         let hours = Math.floor(leave1 / (3600 * 1000))
    //         if (hours > 0) {
    //           this.dateList.push(hours + '小时前')
    //         } else {
    //           let leave2 = leave1 % (3600 * 1000)
    //           let minutes = Math.floor(leave2 / (60 * 1000))
    //           if (minutes > 0) {
    //             this.dateList.push(minutes + '分钟前')
    //           } else {
    //             this.dateList.push('刚刚')
    //           }
    //         }
    //       }
    //     }
    //   })
    // },
    getKline() {
      HttpUtil.get(`marketKline/${this.$route.params.id}`).then((res) => {
        if (res.status === 200) {
          this.kline = [{
            data: res.data.data.price_usd
          }, {
            data: res.data.data.volume_usd
          }]
        }
      })
    }
  },
  mounted() {
    // console.log(axios.defaults);
    
    // i18n.locale = this.$store.state.locale
    // console.log(i18n.locale);
    // document.cookie = `lan=${this.$store.state.locale}`
    this.$store.commit('setUrl', { url: `https://block.cc/coin/${this.$route.params.id}` })
    this.lan = this.$store.state.locale
    this.totalSymbol = JSON.parse(localStorage.getItem('B-symbol'))
    this.rate = {
      type: '￥',
      symbol: 'CNY',
      rate: this.rate.rate
    }
    this.setDesc()
    this.setCNY()
    if (this.page >= this.pageCount - 1) {
      this.showAll = true
    }
    // this.getList()
    this.getKline()
    if (this.ico !== '') {
      let temp = this.ico.find((val) => {
        return val.symbol === 'USD'
      })
      if (temp && temp.price) {
        this.icoCost = temp.price
      } else {
        this.icoCost = 0
      }
      console.log(this.icoCost)
    }
    document.addEventListener('click', () => {
      this.rateMenu = false
      this.openCurrency = false
    })
  }
}
</script>
<style scoped>
@import url('//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css');
/*rate-menu动画*/
/* .menu-enter-active, .menu-leave-active {
  transition: opacity .3s;
}
.menu-enter, .menu-leave-to {
  opacity: 0;
}
.menu-enter-to, .menu-leave {
  opacity: 1;
} */
/*切换动画*/
.switch-enter-active, .switch-leave-active {
  transition: opacity .2s;
}
.switch-enter, .switch-leave-to {
  opacity: 0;
}
.switch-enter-to, .switch-leave {
  opacity: 1;
}
/* .info {
  padding: 1.33vw 1.33vw 0;
  text-align: left;
}
.info p {
  color: #808080;
}
.info>div {
  margin: 1.33vw 0;
}
.info>div * {
  margin: 0.667vw;
}
.info .price {
  font-size: 3.733vw;
  margin-right: 2.667vw;
}
.info .change {
  font-size: 2.4vw;
}
.info .change.up {
  color: red;
}
.info .change.down {
  color: green;
}
.info .index>p {
  float: left;
  margin-right: 1.33vw;
}
.info .rank {
  background-color: #ff3300;
  color: #ffffff;
  padding: 0.133vw 1.33vw;
  border-radius: 0.667vw;
}
.info .item {
  display: flex;
}
.info .item>div {
  flex-grow: 1;
}
.menu h3 {
  text-align: left;
  padding: 1.33vw;
} */
.btn-group::after {
  content: '.';
  height: 0;
  display: block;
  visibility: hidden;
  clear: both;
}
.btn-group>button {
  float: left;
  border: none;
  background-color: #ffffff;
  padding: 0.3067rem 0.2667rem;
  width: calc(100% / 4);
  font-size: 0.4267rem;
  color: #333333;
  outline: none;
}
.btn-group>button.active, .btn-group>button:active {
  border-bottom: 0.0667rem solid #3aa0ff;
  color: #3aa0ff;
}
.base {
  border-bottom: 0.1333rem solid #eeeeee;
}
.base p {
  text-align: left;
  font-size: 0.4267rem;
  padding: 0 0.2667rem 0.4667rem 0.2667rem;
  color: #333333;
}
.base>p>a {
  text-decoration: none;
  color: #38a0ff;
  cursor: pointer;
}
.base p:nth-of-type(1) {
  padding-top: 0.2667rem;
  padding-bottom: 0.4rem;
}
.base::after {
  content: '.';
  height: 0;
  display: block;
  visibility: hidden;
  clear: both;
}
.base dl {
  width: 100%;
  box-sizing: border-box;
}
.base dd {
  padding: 0.2667rem;
  margin: 0;
  text-align: left;
  border: 1px solid #eeeeee;
  font-size: 0.3467rem;
}
.base dd a {
  padding-right: 0.0667rem;
  text-decoration: none;
}
.base dd>div:nth-child(1) {
  float: left;
  color: #cccccc;
}
.base dd>div:nth-child(2) {
  float: right;
  color: #333333;
}
.base dd::after {
  content: '.';
  display: block;
  height: 0;
  visibility: hidden;
  clear: both;
}
/* .ico>div:first-child {
  padding: 0.2667rem 0;
}
.ico-list {
  display: flex;
}
.ico-list>dd {
  width: 50%;
}
.ico-list>dd>div {
  width: 50%;
  float: left;
  padding: 0.2667rem;
  box-sizing: border-box;
}
.ico-list>dd:last-child>div {
  padding: 0.2667rem 0;
}
.ico-list div:first-of-type {
  font-size: 0.3467rem;
  color: #999999;
}
.ico-list div:last-child {
  font-size: 3.733vw;
  color: #333333;
  padding-right: 0;
}
.ico-list div a {
  text-decoration: none;
  color: #2d91ec;
}
.ico .desc {
  padding: 2.667vw 0;
}
.ico .desc:last-child {
  padding-bottom: 0;
}
.ico .desc>p {
  padding: 0 2.667vw 4vw 2.667vw;
  border-bottom: 0.4vw solid #e6e6e6;
}
.ico .desc>p span {
  border-left: 0.8vw solid #38a0ff;
  padding-left: 2vw;
  font-size: 4.2667vw;
}
.ico .desc>.content {
  padding: 0 2.667vw;
  padding-top: 2.667vw;
  font-size: 4.2667vw;
}
.ico .desc>.content a {
  text-decoration: none;
  color: #2e90ed;
  float: right;
}
.ico .desc>div i {
  margin-right: 1.33vw;
}
.ico .desc>.people {
  padding: 0 2.667vw;
  padding-top: 2.667vw;
  font-size: 4.2667vw;
}
.ico .desc>.link {
  padding: 2.667vw;
}
.ico .desc>.link>div {
  display: inline-block;
  width: calc(100% / 3 - 0.667vw);
  font-size: 4.2667vw;
}
.ico .desc>.link a {
  text-decoration: none;
  color: #2e90ed;
}
.ico .desc>.link img {
  vertical-align: middle;
} */
/* list */
.list {
  position: relative;
  overflow: hidden;
}
.list thead>tr {
  /* height: 0.6667rem; */
  /* line-height: 0.6667rem; */
  /* font-size: 0.2933rem; */
  color: #999999;
  background-color: #f5f5f5;
  height: 0.6667rem;
  font-size: 0.2933rem;
}
.left {
  display: inline-block;
  width: 3.84rem;
  z-index: 20;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
}
.left table {
  border-collapse: collapse;
}
.left td {
  padding-left: 0.2667rem;
}
.left p {
  font-size: 0.4267rem;
  color: #333333;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  width: 3.2rem;
}
.left span {
  font-size: 0.2667rem;
  color: #999999;
  margin-left: 0.1067rem;
}
.left tr {
  width: 3.84rem;
  border-right: 1px solid #e6e6e6;
}
.right {
  overflow: auto;
  width: 100%;
}
.right table th,table td{
  white-space: nowrap;
}
.right table{
  border-collapse: collapse;
  width: 100%;
  overflow-x: scroll;
  border: 0;
}
.right thead tr td {
  padding-right: 0;
}
.right thead tr {
  border: 0;
}
.right td {
  /* border: 0; */
  /* font-size: 0.3733rem; */
  white-space: nowrap;
  text-align: right;
}
.right td:nth-child(1),.right td:nth-child(2) {
  width: 5rem;
  padding-right: 0.4rem;
  opacity: 0;
}
.right td:last-of-type {
  padding-right: 4vw;
}
.right tr>td {
  padding-left: 0.8rem;
}
.right tr>td:not(.change) {
  color: #333333;
}
.right tr>td>p {
  font-size: 0.48rem;
  font-weight: bold;
}
.right tr>td>p.sub {
  font-size: 0.2667rem;
  color: #999999;
  font-weight: normal;
}
.right tr>td>span {
  position: relative;
  font-size: 0.3733rem;
}
.left tbody td,.right tbody td {
  border-bottom: 1px solid #e6e6e6;
  height: 1.36rem;
}
/* .right tr span>span {
  position: absolute;
  top: -2.667vw;
  bottom: -2.667vw;
  left: -4vw;
  right: -6.667vw;
  background-color: #ffffff;
  z-index: -2;
  opacity: 0;
} */
@keyframes up {
  0% {
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
span.bg-up {
  background-color: #e3f4e7!important;
  animation: up 2s infinite;
}
span.bg-down {
  background-color: #fbe5e2!important;
  animation: up 2s infinite;
}
/* .change {
  color: #34A853;
} */
.price-name,.high-name,.low-name,.volumn-name {
  position: relative;
  display: inline-block;
  width: 0.3467rem;
}
.price-name::after,.high-name::after,.low-name::after,.volumn-name::after {
  content: '';
  width: 0.4rem;
  height: 0.4rem;
  display: block;
  position: absolute;
  transform: translateY(-64%);
  left: 0.0667rem;
  background: url(../../static/images/trangle1.png) no-repeat;
  background-size: 0.32rem 0.32rem;
}
.up::after {
  content: '';
  width: 0.4rem;
  height: 0.4rem;
  display: block;
  position: absolute;
  left: 0.0667rem;
  transform: translateY(-64%);
  background: url(/images/trangle2.png) no-repeat;
  background-size: 0.32rem 0.32rem;
}
.down::after {
  content: '';
  width: 0.4rem;
  height: 0.4rem;
  display: block;
  position: absolute;
  left: 0.0667rem;;
  transform: translateY(-64%);
  background: url(/images/trangle3.png) no-repeat;
  background-size: 0.32rem 0.32rem;
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

nav {
  color: #333333;
}
nav .name {
  border-bottom: 1px solid #e6e6e6;
  font-size: 0.4267rem;
  padding: 0.2133rem 0.2667rem;
  position: relative;
}
nav .name>img {
  vertical-align: sub;
  margin-right: 0.1333rem;
  width: 0.5067rem;
  height: 0.5067rem;
  border-radius: 50%;
}
nav .name>.rate {
  float: right;
  height: 0.7733rem;
  line-height: 0.7733rem;
  width: 2.0533rem;
  border: 2px solid #333333;
  border-radius: 0.0667rem;
  text-align: center;
  box-sizing: border-box;
  font-size: 0.2933rem;
  position: relative;
  bottom: 0.0533rem;
  /* transition: all 2s; */
  /* padding: 0.2133rem; */
}
nav .name>.rate::after {
  content: '';
  border-top: 0.1067rem solid #999999;
  border-left: 0.1067rem solid transparent;
  border-right: 0.1067rem solid transparent;
  position: absolute;
  top: 54%;
  transition: all .5s;
  transform: translateY(-50%) rotate(0deg);
}
nav .name>.rate.active::after {
  content: '';
  border-top: 0.1067rem solid #999999;
  border-left: 0.1067rem solid transparent;
  border-right: 0.1067rem solid transparent;
  position: absolute;
  top: 54%;
  transform: translateY(-50%) rotate(180deg);
}
nav .currency-menu {
  position: absolute;
  top: 85%;
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
nav .currency-menu>div.active {
  background-color: #4f4f4f;
  color: #ffffff;
}
nav .currency-menu img {
  height: 0.1333rem;
  width: 0.1333rem;
  transform: scale(3.5) translateY(-0.0187rem) translateX(-0.0133rem);
}
nav>div {
  padding: 3.33vw 2.667vw;
}
nav .total>span:nth-child(1) {
  font-size: 0.4267rem;
}
nav .total>span:nth-child(2) {
  font-size: 0.8rem;
  font-weight: bold;
}
nav .total>span:nth-child(3) {
  font-size: 0.4rem;
  color: #ffffff;
  width: 2.1067rem;
  text-align: center;
  background-color: #ea4434;
  padding: 0.08rem 0;
  /* padding: 0.08rem 0.3467rem; */
  border-radius: 0.1333rem;
  float: right;
  margin-top: 0.1333rem;
  font-weight: bold;
}
nav .sub {
  display: flex;
  font-size: 0.3467rem;
}
nav .sub>* {
  align-self: flex-end;
}
nav .sub>span:nth-child(1),nav .sub>span:nth-child(2) {
  color: #666666;
  /* font-size: 0.3467rem; */
  line-height: 1.5;
}
nav .sub>span:nth-child(1) {
  flex-grow: 1;
}
nav .sub>span:nth-child(2) {
  flex-grow: 3;
}
nav .sub>span:nth-child(3) {
  color: #999999;
  /* font-size: 0.3467rem; */
}
nav .info {
  padding-top: 2.667vw;
  font-size: 3.733vw;
  display: flex;
}
nav .info>* {
  align-self: center;
}
nav .info>span:nth-child(1) {
  color: #999999;
}
nav .info>span:nth-child(2) {
  color: #333333;
}
nav .info>span:nth-child(3) {
  color: #ffffff;
  background-color: #34a953;
  padding: 0.1333rem 0.1867rem 0.1333rem 0.1333rem;
  margin-left: 0.2667rem;
  border-radius: 0.0667rem;
  font-size: 0.24rem;
  position: relative;
}
nav .info>span>i {
  margin-left: 0.1067rem;
}
.chart {
  /* height: 5.04rem; */
  /* 临时 */
  height: 2.6667rem;
  padding: 1.33vw 0;
  background-color: #eeeeee;
  display: flex;
}
.chart>div:nth-child(1),.chart>div:nth-child(2) {
  margin-right: 1.33vw;
}
.chart>div {
  width: calc(100% / 3);
  padding: 0.32rem 1.0667vw 0.2667rem 2vw;
  background-color: #ffffff;
  font-size: 0.2933rem;
  color: #999999;
}
.chart>div>p {
  padding-bottom: 0.33vw;
}
.chart .title {
  font-size: 0.2933rem;
  color: #cccccc;
  padding-bottom: 0.2133rem;
}
.chart .title>span:nth-of-type(1) {
  display: inline-block;
}
.chart .title>span:nth-of-type(2) {
  font-size: 0.2133rem;
  color: #ffffff;
  background-color: #34a953;
  text-align: center;
  padding: 0.0533rem 0.1333rem;
  border-radius: 0.667vw;
  float: right;
  box-sizing: border-box;
  width: 1.2rem;
}
.chart .cny>span:nth-child(1) {
  color: #333333;
  font-size: 0.3733rem;
}
.chart .range {
  color: #333333;
  text-align: center;
  height: 150px;
  box-sizing: border-box;
  padding-top: 0.8rem;
}
.chart .range>p {
  font-size: 0.5333rem;
}
.chart .range>span {
  font-size: 0.2933rem;
}
</style>
