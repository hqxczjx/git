<template>
  <div class="list">
    <div class="left">
      <table>
        <thead class="title">
          <tr>
            <td>{{ $t('message.coin_name') }}</td>
          </tr>
        </thead>
        <tbody style="background: #ffffff;">
          <tr style="height: 1.3333rem;border-bottom: 1px solid #e6e6e6;" v-for="(item,index) in list" :key="index" @click="go(`/exchange/${item.exchange_name}`)">
            <td @click="go(`/coin/${item.coin_id}`)">
              <p>{{ item.display_pair_name.split('/')[0] }}<span style="margin-right: -0.0533rem;">/</span><span>{{ item.display_pair_name.split('/')[1] }}</span></p>
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
            <td @click="setSel(1); dataSort()">{{ $t('message.coin_price') }}<i class="price-name" :class="addUp(1)"></i></td>
            <td v-if="list[0].change1d" @click="setSel(2); dataSort()">{{ $t('message.rise') }}<i class="price-name" :class="addUp(2)"></i></td>
            <td @click="setSel(3); dataSort()">{{ $t('message.coin_title_24high') }}<i class="high-name" :class="addUp(3)"></i></td>
            <td @click="setSel(4); dataSort()">{{ $t('message.coin_title_24low') }}<i class="low-name" :class="addUp(4)"></i></td>
            <td @click="setSel(5); dataSort()">{{ $t('message.home_volume') }}<i class="volumn-name" :class="addUp(5)"></i></td>
            <td>{{ $t('message.block_exchange_volumePercent') }}</td>
          </tr>
        </thead>
        <tbody>
          <tr style="height: 1.3333rem;" v-for="(item, index) in list" :key="index">
            <td>1</td>
            <td>1</td>
            <td>
              <p><img style="height: 0.3733rem;transform: translateY(0.0533rem);" :src="setCoinImg(rate.symbol, 0)" alt="">{{ format(item.price * rate.rate, lan, true).num }}</p>
              <p class="sub"><img style="height: 0.2667rem;transform: translateY(0.056rem);" :src="setCoinImg(item.display_pair_name.split('/')[1], 'gray-999')" alt="">{{ format(item.native_price, lan, true).num }}</p>
            </td>
            <td v-if="item.change1d" class="change"><span :style="{ 'background-color': item.change1d < 0 ? '#ea4434' : '' }">{{ item.change1d }}%</span></td>
            <td><span><img style="height: 0.4rem;transform: translateY(0.0533rem);" :src="setCoinImg(rate.symbol, 0)" alt="">{{ format(item.high1d * rate.rate, lan, true).num }}</span></td>
            <td><span><img style="height: 0.4rem;transform: translateY(0.0533rem);" :src="setCoinImg(rate.symbol, 0)" alt="">{{ format(item.low1d * rate.rate, lan, true).num }}</span></td>
            <td><span><img style="height: 0.4rem;transform: translateY(0.0533rem);" :src="setCoinImg(rate.symbol, 0)" alt="">{{ format(item.volume * rate.rate, lan).num }}{{ format(item.volume	 * rate.rate, lan).unit }}</span></td>
            <td><span>{{ item.percent }}</span></td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- <v-table :width="1700" :height="200" :columns="columns" :table-data="tableData"></v-table> -->
  </div>
</template>
<script>
  import format from './../static/format'
  export default {
    name: 'InfoList',
    // props: ['list', 'rate', 'lan', 'error', 'changeList'],
    props: {
      list: {
        type: Array,
        default: []
      },
      rate: Object,
      lan: String
      // error: Boolean,
      // changeList: Array
    },
    data() {
      return {
        imgError: 'this.src="https://blockchains.oss-cn-shanghai.aliyuncs.com/static/main/noneimg.svg"',
        whoSel: '',
        isUp: false,
        cnyRate: 0
        // isError: false
      }
    },
    methods: {
      go(url) {
        this.$router.push({ path: url })
      },
      setSel(val) {
        if (this.whoSel === val) {
          this.isUp = !this.isUp
        } else {
          this.whoSel = val
          this.isUp = true
        }
      },
      format(num, lan, isPrice) {
        return format(num, lan, isPrice)
      },
      selChange(val) {
        if (val === 1) {
          return 'rise'
        } else if (val === -1) {
          return 'drop'
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
      setCNY() {
        let cny = JSON.parse(localStorage.getItem('B-option')).rates
        this.cnyRate = cny.CNY
      },
      changeRateFa(val) {
        return val.toLowerCase()
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
      dataSort() {
        switch (this.whoSel) {
          case 1:
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
          case 2:
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
          case 3:
            if (this.isUp) {
              this.list = this.list.sort((a, b) => {
                return b.high1d - a.high1d
              })
            } else {
              this.list = this.list.sort((a, b) => {
                return a.high1d - b.high1d
              })
            }
            break
          case 4:
            if (this.isUp) {
              this.list = this.list.sort((a, b) => {
                return b.low1d - a.low1d
              })
            } else {
              this.list = this.list.sort((a, b) => {
                return a.low1d - b.low1d
              })
            }
            break
          case 5:
            if (this.isUp) {
              this.list = this.list.sort((a, b) => {
                return b.volume - a.volume
              })
            } else {
              this.list = this.list.sort((a, b) => {
                return a.volume - b.volume
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
      // add() {
      //   this.pageNum = this.list.length
      //   this.showAll = true
      // }
    },
    mounted() {
      this.setCNY()
      // let change = document.querySelector('.table-head>td:nth-child(4)')
      // let table = document.querySelector('table')
      // let temp = table.getBoundingClientRect().left
      // change.classList.add('scroll')
      // table.addEventListener('touchmove', () => {
      //   // console.log(change.getBoundingClientRect().right)
      //   if (table.getBoundingClientRect().left !== temp) {
      //     change.classList.remove('scroll')
      //   } else {
      //     // change.classList.add('scroll')
      //   }
      // })
    }
  }
</script>
<style scoped>
  * {
    color: #333333;
  }
  /* thead.fixed,.left .title.fixed {
    position: fixed;
    top: 13.33vw;
    left: 0;
    right: 0;
    z-index: 30;
    background-color: #ffffff;
  } */
  /* list */
.list {
  position: relative;
  overflow: hidden;
}
.list thead {
  height: 0.6667rem;
  line-height: 0.6667rem;
  font-size: 0.2933rem;
  color: #999999;
  background-color: #f5f5f5;
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
.left td {
  padding-left: 0.2667rem;
}
.left p {
  font-size: 0.4267rem;
  color: #333333;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  width: 3.5733rem;
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
.right table,.left table{
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
.right tr {
  border-bottom: 1px solid #e6e6e6;
}
.right td {
  border: 0;
  font-size: 0.3733rem;
  white-space: nowrap;
  text-align: right;
}
.right td:nth-child(1),td:nth-child(2) {
  width: 4.2667vw;
  opacity: 0;
}
.right td:last-of-type {
  padding-right: 4vw;
}
.right tr>td {
  text-align: right;
  padding-left: 0.9333rem;
}
.right tr>td>p {
  font-size: 0.4267rem;
  color: #333333;
}
.right tr>td:nth-of-type(3)>p:not(.sub) {
  font-weight: bold;
}
.right tr>td>p.sub {
  font-size: 0.2667rem;
  color: #999999;
}
.right tr>td>span {
  position: relative;
}
.right tr span>span {
  position: absolute;
  top: -2.667vw;
  bottom: -2.667vw;
  left: -4vw;
  right: -6.667vw;
  background-color: #ffffff;
  z-index: -2;
  opacity: 0;
}
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
.change>span {
  color: #ffffff;
  background-color: #34A853;
  font-size: 0.2933rem;
  width: 1.4667rem;
  display: inline-block;
  text-align: center;
  padding: 0.1333rem 0;
  border-radius: 0.1333rem;
}
.price-name,.high-name,.low-name,.volumn-name {
  position: relative;
  display: inline-block;
  width: 0.3467rem;
}
.price-name::after,.high-name::after,.low-name::after,.volumn-name::after {
  content: '';
  width: 0.5333rem;
  height: 0.6667rem;
  display: block;
  position: absolute;
  transform: translateY(-44%);
  left: 0.0667rem;
  background: url(/images/trangle1.png) no-repeat;
  background-size: 0.2933rem 0.3467rem;
}
.up::after {
  content: '';
  width: 0.5333rem;
  height: 0.6667rem;
  display: block;
  position: absolute;
  left: 0.0667rem;
  transform: translateY(-44%);
  background: url(/images/trangle2.png) no-repeat;
  background-size: 0.2933rem 0.3467rem;
}
.down::after {
  content: '';
  width: 0.5333rem;
  height: 0.6667rem;
  display: block;
  position: absolute;
  left: 0.0667rem;;
  transform: translateY(-44%);
  background: url(/images/trangle3.png) no-repeat;
  background-size: 0.2933rem 0.3467rem;
}
</style>
