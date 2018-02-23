<template>
  <section class="container">
    <nav>
      <span>{{ $t('message.index_icon_exchange') }}</span>
      <span class="btn" style="display: none;">筛选</span>
    </nav>
    <div class="list">
      <div class="item" v-for="(item, index) in list" :key="index" @click="go(`/exchange/${item.name}`)">
        <span class="num">{{ index + 1 }}</span>
        <img :src="`https://blockchains.oss-cn-shanghai.aliyuncs.com/static/Exchange/${item.name}.png`" alt="" :onerror="imgError">
        <div class="item-name">
          <p>{{ item.display_name }}</p>
          <img :src="`/images/star/${item.star}star.png`" alt="" :onerror="imgError">
        </div>
        <div class="item-data">
          <p>{{ format(item.volume_BTC, lan, true).num }}</p>
          <p>24H{{ $t('message.block_exchange_volume') }}<img style="height: 0.2667rem;transform: scale(1.4) translateY(1px);" src="/images/money-icon/gray-ccc/BTC.png" alt=""></p>
        </div>
        <p :style="{ 'padding-left' : $store.state.locale === 'en' ? '0px' : '' }">
          {{ item.exchangeType.length > 0 ? `${$t('message.api_type')}：${exType(item.exchangeType).join('、')}` : '' }}
          <span>{{ $t('message.exchange_tab_4') }}：{{ item.pairNum }}</span>
          <span v-if="item.country && $store.state.locale === 'zh'">{{ $t('message.block_exchange_country') }}：{{ !!item.country ? item.country.name : '' }}</span>
          <span v-if="item.country && $store.state.locale !== 'zh'">{{ $t('message.block_exchange_country') }}：{{ !!item.country ? item.country.en_name : '' }}</span>
        </p>
      </div>
      <button class="more" @click="add">{{ loading }}</button>
    </div>
  </section>
</template>
<script>
import axios from 'axios'
import format from './../../static/format.js'
import HttpUtil from '../../plugins/http_util';
export default {
  async asyncData({ params }) {
    let res = await HttpUtil.get('exchange/list')
    return {
      list: res.data.data.list,
      page: res.data.data.page,
      pageCount: res.data.data.pageCount
    }
  },
  name: 'exchange',
  data() {
    return {
      lan: '',
      loading: this.$t('message.load_more'),
      imgError: 'this.src="https://blockchains.oss-cn-shanghai.aliyuncs.com/static/main/noneimg.svg"'
    }
  },
  watch: {
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
    go(url) {
      this.$router.push({ path: url })
    },
    exType (arr) {
      return arr.map((val) => {
        if (val === '1') {
          return this.$t('message.exchange_sort_type_1')
        } else if (val === '2') {
          return this.$t('message.exchange_sort_type_2')
        } else if (val === '3') {
          return '法币'
        } else {
          return ''
        }
      })
    },
    add() {
      this.loading = this.$t('message.loading')
      if (this.searchInfo !== '') {
        if (this.page !== this.pageCount - 1) {
          HttpUtil.get(`exchange/list?page=${this.page + 1}`).then((res) => {
            if (res.data.data.list.length !== 0) {
              this.list = [...this.list, ...res.data.data.list]
              this.page = res.data.data.page
              this.pageCount = res.data.data.pageCount
            }
            this.loading = this.$t('message.load_more')
          })
        }
      } else {
        if (this.page !== this.pageCount - 1) {
          this.getList(this.page + 1)
        }
      }
    }
  },
  mounted() {
    this.lan = localStorage.getItem('lan')
    this.$store.commit('setUrl', { url: 'https://block.cc/exchange' })
  }
}
</script>
<style scoped>
@import url('https://blockchains.oss-cn-shanghai.aliyuncs.com/static/Exchange/spirts/sprite.css');
nav {
  padding: 0 0.2667rem;
  font-size: 0.4267rem;
}
nav>span:not(.btn) {
  margin: 0.4rem 0;
  display: inline-block;
}
nav>.btn {
  background-color: #333333;
  margin: 0.1333rem 0;
  padding: 0.32rem 0.64rem 0.32rem 0.2667rem;
  color: #ffffff;
  height: 1.0667rem;
  box-sizing: border-box;
  font-size: 0.3733rem;
  border-radius: 0.1333rem;
  float: right;
}
.list>.item {
  border-top: 1px solid #e6e6e6;
  height: 2.4rem;
  box-sizing: border-box;
  padding: 0.3467rem 0.2667rem 0.3467rem 0.1333rem;
}
.item>.num {
  width: 0.6667rem;
  text-align: center;
  display: inline-block;
  color: #999999;
  font-size: 0.2933rem;
}
.item>img {
  width: 1.1733rem;
  height: 1.1733rem;
  border-radius: 0.1333rem;
  vertical-align: middle;
}
.item>div {
  display: inline-block;
  margin-left: 0.2667rem;
  vertical-align: top;
}
.item>.item-name {
  color: #333333;
  font-size: 0.5067rem;
}
.item>.item-name>p {
  max-width: 4rem;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.item>.item-name>img {
  width: 2.4rem;
}
.item>.item-data {
  color: #333333;
  font-size: 0.4267rem;
  float: right;
  text-align: right;
}
.item>div>*:last-of-type {
  vertical-align: super;
}
.item>.item-data>p:last-of-type {
  color: #cccccc;
  font-size: 0.2933rem;
  margin-top: 0.08rem;
}
.item>p {
  padding-left: 2.1333rem;
  font-size: 0.2933rem;
  color: #999999;
}
.item>p>span {
  float: right;
  margin-left: 0.4rem;
}
.more {
  width: 100vw;
  text-align: center;
  padding: 0.2933rem 0;
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