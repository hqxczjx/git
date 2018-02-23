<template>
  <section class="container">
    <div class="search">
      <nav class="title">
        <label>
          <i class="icon"></i>
          <input v-model.trim="searchInfo" placeholder="搜索币种、交易所、地址、交易哈希"/>
          <i v-show="searchInfo !== ''" class="info-close" @click="delInfo"></i>
        </label>
        <span @click="goBack()">取消</span>
      </nav>
      <div class="search-info" v-if="searchInfo !== ''">
        <div class="list-title">
          <button :class="{ active : selCate === 0 }" @click="selCate = 0">币种</button>
          <button :class="{ active : selCate === 1 }" @click="selCate = 1">交易所</button>
          <button :class="{ active : selCate === 2 }" @click="selCate = 2">ICO</button>
          <button :class="{ active : selCate === 3 }" @click="selCate = 3">IFO</button>
        </div>
        <div class="list" v-if="listNull === false">
          <div class="item" v-for="(item, index) in selList" :key="index" @click="go('/about')">
            <img src="https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bitcoin.png"/>
            <span v-if="item.symbol">{{ item.symbol }}-</span>
            <span v-else-if="item.display_name">{{ item.display_name }}-</span>
            <span v-if="item.name">{{ item.name }}</span>
          </div>
        </div>
        <div class="error" v-if="listNull === true">
          <img src="https://blockchains.oss-cn-shanghai.aliyuncs.com/static/blockcc/noResult.png"/>
          <p>您搜索的关键字是:</p>
          <p class="key">{{ searchInfo }}</p>
          <p>未找到符合查询的数据，请更换关键词后重试。</p>
        </div>
      </div>
      <div class="empty-search" v-if="isEmpty === true && searchInfo === ''">
        <p>热门搜索</p>
        <div>
          <span class="item" v-for="(item, index) in hotSearch" :key="index" @click="searchInfo = item">{{ item }}</span>
        </div>
      </div>
      <div class="history-search" v-if="isEmpty === false && searchInfo === ''">
        <div class="list">
          <p v-for="(item, index) in historyList" :key="index" @click="searchInfo = item"><i class="icon"></i>{{ item }}</p>
        </div>
        <div class="name">
          <span>历史记录</span>
          <span class="close" @click="delHistory">关闭</span>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import axios from 'axios'
import HttpUtil from './../plugins/http_util';
export default {
  name: 'search',
  layout: 'searchPage',
  data() {
    return {
      searchInfo: '',
      list: [],
      selList: [],
      isEmpty: true,
      selCate: 0,
      historyList: [],
      hotSearch: ['BTC', 'BTC介绍', 'dsadas', 'APP下载'],
      listNull: false
    }
  },
  watch: {
    searchInfo(newVal) {
      if (newVal !== '') {
        HttpUtil.get(`https://block.cc/api/v1/search?str=${newVal}`).then((res) => {
          this.list = res.data.data
          this.selList = this.cateList(this.selCate)
          this.listNull = false
        })
      } else {
        this.selCate = 0
        this.selList = []
        this.listNull = false
      }
    },
    selCate(newVal) {
      this.selList = this.cateList(newVal)
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    },
    go(url) {
      let data
      if (!localStorage.getItem('B-search-info')) {
        data = new Set()
      } else {
        data = new Set(JSON.parse(localStorage.getItem('B-search-info')))
      }
      data.add(this.searchInfo)
      localStorage.setItem('B-search-info', JSON.stringify(data))
      this.$router.push({ path: url })
    },
    delHistory() {
      this.isEmpty = true
      localStorage.removeItem('B-search-info')
    },
    delInfo() {
      this.searchInfo = ''
    },
    cateList(num) {
      switch (num) {
        case 0:
          if (this.list[0] && this.list[0].length !== 0) {
            if (this.list[0].COIN.length === 0) {
              this.$nextTick(() => {
                this.listNull = true
              })
              return []
            } else {
              this.listNull = false
              return this.list[0].COIN
            }
          }
          break
        case 1:
          if (this.list[1] && this.list[1].length !== 0) {
            if (this.list[1].EXCHANGE.length === 0) {
              this.listNull = true
              return []
            } else {
              this.listNull = false
              return this.list[1].EXCHANGE
            }
          }
          break
        case 2:
          if (this.list[2] && this.list[2].length !== 0) {
            if (this.list[2].IFO.length === 0) {
              this.listNull = true
              return []
            } else {
              this.listNull = false
              return this.list[2].IFO
            }
          }
          break
        case 3:
          if (this.list[3] && this.list[3].length !== 0) {
            if (this.list[3].ICO.length === 0) {
              this.listNull = true
              return []
            } else {
              this.listNull = false
              return this.list[3].ICO
            }
          }
          break
      }
    }
  },
  mounted() {
    let history = JSON.parse(localStorage.getItem('B-search-info'))
    if (history) {
      this.historyList = history
      this.isEmpty = false
    }
  }
}
</script>
<style scoped>
.title {
    background-color: #2b2b2b;
    padding: 2.667vw;
  }
  .title>label {
    position: relative;
  }
  .title input {
    width: 82vw;
    box-sizing: border-box;
    padding-left: 6.67vw;
    padding-right: 7.33vw;
    height: 8.533vw;
    border-radius: 0.667vw;
    font-size: 3.2vw;
    line-height: normal;
    border: 0;
  }
  .title label {
    display: inline-block;
  }
  label i.icon {
    position: absolute;
    width: 7vw;
    height: 6.33vw;
    top: 50%;
    transform: translateY(-50%) scale(0.6);
    left: 0.667vw;
    background: url(/images/searchiconsmall.png) no-repeat;
  }
  label i.info-close {
    position: absolute;
    width: 9vw;
    height: 9vw;
    top: 50%;
    transform: translateY(-50%) scale(0.4);
    right: 0.667vw;
    background: url(/images/deleteicon.png) no-repeat;
  }
  .title input {
    font-size: 3.2vw;
  }
  .title input::-webkit-input-placeholder {
    color: #cccccc;
  }
  .title input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
    color: #cccccc;
  }
  .title input::-moz-placeholder { /* Mozilla Firefox 19+ */
    color: #cccccc;
  }
  .title input:-ms-input-placeholder { /* Internet Explorer 10-11 */
    color: #cccccc;
  }
  .title>span {
    font-size: 3.733vw;
    color: #ffffff;
    display: inline-block;
    padding-left: 3.33vw;
    position: relative;
    top: 0.4vw;
  }

  .empty-search {
    padding: 2.667vw;
    font-size: 4.2667vw;
    color: #333333;
  }
  .empty-search>p {
    font-size: 3.4667vw;
    padding-top: 4vw;
    padding-bottom: 2.667vw;
  }
  .empty-search span.item {
    float: left;
    width: 50%;
    padding: 3.0667vw 0;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    text-decoration: none;
    color: #333333;
  }

  .history-search {
    padding-top: 2.667vw;
  }
  .history-search>div.list {
    padding: 0 2.667vw;
  }
  div.list>p {
    position: relative;
    padding: 2.667vw 0 2.667vw 4.667vw;
    font-size: 4.2667vw;
    color: #333333;
  }
  div.list i.icon {
    position: absolute;
    width: 30px;
    height: 30px;
    top: 50%;
    transform: translateY(-40%) scale(0.7);
    left: -10px;
    background: url(/images/searchiconsmall.png) no-repeat;
  }
  .history-search div.name {
    padding: 2.667vw;
    background-color: #f5f5f5;
    font-size: 3.4667vw;
  }
  span.close {
    color: #2e91ee;
    position: relative;
    float: right;
  }
  span.close::after {
    content: '\00D7';
    font-size: 4.2667vw;
    position: absolute;
    display: inline-block;
    top: 50%;
    transform: translateY(-50%);
    left: -4.2667vw;
  }

  .list-title {
    height: 10.667vw;
    display: flex;
  }
  .list-title>button {
    flex-grow: 1;
    border: 0;
    border-bottom: 0.667vw solid transparent;
    margin: 0 2.667vw;
    padding: 3.33vw 5.33vw 2.667vw 5.33vw;
    color: #333333;
    font-size: 3.733vw;
    background-color: #ffffff;
    outline: none;
  }
  .list-title>button.active {
    color: #2e90ed;
    border-bottom: 0.667vw solid #2e90ed;
  }

  .search-info .item {
    background-color: #ffffff;
    padding: 3.2vw 4.533vw;
  }
  .search-info .item>img {
    position: relative;
    top: 0.8vw;
  }
  .search-info .item>span {
    color: #333333;
    font-size: 4.2667vw;
  }
  .search-info .item:active {
    background-color: #999999;
  }
  .search-info .item:active>span {
    color: #ffffff;
  }
  .error {
    padding: 2.667vw;
    font-size: 3.4667vw;
    text-align: center;
  }
  .error>p:first-of-type {
    text-align: left;
  }
  .error .key {
    border: 0.133vw solid #e6f4ff;
    text-align: center;
    color: #2c3e50;
    padding: 1.33vw 0;
    margin: 2.667vw 0;
  }
</style>
