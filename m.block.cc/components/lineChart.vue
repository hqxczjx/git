<template>
  <div class="lineChart">
    <div :id="id"></div>
  </div>
</template>
<script>
  import i18n from './../plugins/i18n'
  import Highcharts from 'highcharts'
  export default{
    data() {
      return {
        options: {
          chart: {
            zoomType: 'x'
          },
          // tooltip: {
          //   shared: true,
          //   hideDelay: 50,
          //   xDateFormat: '%A, %b %d %Y, %H:%M:%S'
          // },
          credits: {enabled: false},
          legend: {
            enabled: true,
            align: 'center',
            backgroundColor: '#FFFFFF',
            borderColor: 'black',
            borderWidth: 0,
            layout: 'horizontal',
            verticalAlign: 'bottom',
            y: 0,
            shadow: false,
            floating: false
          },
          rangeSelector: {
            allButtonsEnabled: true,
            buttons: [{type: 'day', count: 1, text: i18n.messages[i18n.locale].message.scale_day}, {
              type: 'week',
              count: 1,
              text: i18n.messages[i18n.locale].message.scale_week
            }, {type: 'month', count: 1, text: i18n.messages[i18n.locale].message.scale_month}, {
              type: 'month',
              count: 3,
              text: i18n.messages[i18n.locale].message.scale_3month
            }, {type: 'year', count: 1, text: i18n.messages[i18n.locale].message.scale_year}, {
              type: 'ytd',
              count: 1,
              text: i18n.messages[i18n.locale].message.scale_todayyear
            }, {type: 'all', text: i18n.messages[i18n.locale].message.scale_all}],
            selected: 6,
            inputEnabled: true,
            enabled: true
          },
          xAxis: [{
            minRange: 24 * 3600 * 1000
          }],
          yAxis: [{
            labels: {
              formatter: function () {
                return '$' + this.axis.defaultLabelFormatter.call(this)
              },
              align: 'right',
              style: {
                color: '#7cb5ec'
              }
            },
            title: {
              text: i18n.messages[i18n.locale].message.block_coin_marketCap,
              style: {
                color: '#7cb5ec',
                'font-weight': 'bold'
              }
            },
            showEmpty: false,
            height: '80%',
            opposite: false,
            floor: 0
          },
          {
            labels: {
              style: {
                color: '#009933'
              },
              align: 'left',
              x: 15
            },
            title: {
              text: i18n.messages[i18n.locale].message.block_exchange_price + '(' + 'BTC' + ')',
              style: {
                color: '#009933',
                'font-weight': 'bold'
              }
            },
            showEmpty: false,
            height: '80%',
            opposite: true,
            floor: 0
          },
          {
            labels: {
              style: {
                color: '#f7931a'
              },
              align: 'left',
              x: 15
            },
            title: {
              text: i18n.messages[i18n.locale].message.block_exchange_price + '(BTC)',
              style: {
                color: '#f7931a',
                'font-weight': 'bold'
              }
            },
            showEmpty: false,
            height: '80%',
            opposite: true,
            floor: 0
          },
          {
            labels: {
              align: 'right',
              style: {
                color: '#777'
              }
            },
            title: {
              text: '24h' + i18n.messages[i18n.locale].message.block_exchange_volume,
              style: {
                color: '#777',
                'font-weight': 'bold'
              }
            },
            showEmpty: false,
            top: '80%',
            height: '20%',
            offset: 2,
            lineWidth: 1,
            opposite: false
          }],
          tooltip: {
            shared: true,
            split: false
          }
        }
      }
    },
    props: {
      id: {
        type: String
      },
      content: {}, // 数据
      rate: {
        rate: 1,
        symbol: 'USD'
      },
      type: String, // 显示货币符号
      other: {}
    },
    watch: {
      content(value) {
        if (value.length > 0) {
          this.DrawLineChart(value)
        }
      }
    },
    mounted() {
      Highcharts.setOptions({
        global: {
          useUTC: false
        },
        lang: {
          downloadJPEG: '下载jpg',
          downloadPDF: '下载pdf',
          downloadPNG: '下载png',
          downloadSVG: '下载svg',
          loading: '',
          months: [i18n.messages[i18n.locale].message.time_month_one, i18n.messages[i18n.locale].message.time_month_two, i18n.messages[i18n.locale].message.time_month_three, i18n.messages[i18n.locale].message.time_month_four, i18n.messages[i18n.locale].message.time_month_five, i18n.messages[i18n.locale].message.time_month_six, i18n.messages[i18n.locale].message.time_month_seven, i18n.messages[i18n.locale].message.time_month_eight, i18n.messages[i18n.locale].message.time_month_nine, i18n.messages[i18n.locale].message.time_month_ten, i18n.messages[i18n.locale].message.time_month_eleven, i18n.messages[i18n.locale].message.time_month_twelve],
          printChart: '打印图表',
          shortMonths: [i18n.messages[i18n.locale].message.time_month_one, i18n.messages[i18n.locale].message.time_month_two, i18n.messages[i18n.locale].message.time_month_three, i18n.messages[i18n.locale].message.time_month_four, i18n.messages[i18n.locale].message.time_month_five, i18n.messages[i18n.locale].message.time_month_six, i18n.messages[i18n.locale].message.time_month_seven, i18n.messages[i18n.locale].message.time_month_eight, i18n.messages[i18n.locale].message.time_month_nine, i18n.messages[i18n.locale].message.time_month_ten, i18n.messages[i18n.locale].message.time_month_eleven, i18n.messages[i18n.locale].message.time_month_twelve],
          rangeSelectorFrom: '从',
          rangeSelectorTo: '到',
          rangeSelectorZoom: '缩放',
          resetZoom: '恢复初始缩放等级',
          resetZoomTitle: ' 1:1缩放等级',
          shortWeekdays: [i18n.messages[i18n.locale].message.time_week_one, i18n.messages[i18n.locale].message.time_week_two, i18n.messages[i18n.locale].message.time_week_three, i18n.messages[i18n.locale].message.time_week_four, i18n.messages[i18n.locale].message.time_week_five, i18n.messages[i18n.locale].message.time_week_six, i18n.messages[i18n.locale].message.time_week_seven],
          weekdays: [i18n.messages[i18n.locale].message.time_week_one, i18n.messages[i18n.locale].message.time_week_two, i18n.messages[i18n.locale].message.time_week_three, i18n.messages[i18n.locale].message.time_week_four, i18n.messages[i18n.locale].message.time_week_five, i18n.messages[i18n.locale].message.time_week_six, i18n.messages[i18n.locale].message.time_week_seven],
          numericSymbols: [i18n.messages[i18n.locale].message.num_thousand, i18n.messages[i18n.locale].message.num_million, i18n.messages[i18n.locale].message.num_billion],
          thousandsSep: ','
        }
      })
      this.DrawLineChart(this.content)
    },
    methods: {
      DrawLineChart(val) {
        if (val[0] || val[1]) {
          this.options.series = [{
            name: i18n.messages[i18n.locale].message.block_exchange_price + '(' + this.rate.symbol + ')',
            yAxis: 1,
            color: '#009933',
            tooltip: {
              valueSuffix: ' ' + this.rate.symbol,
              valueDecimals: 4
            },
            data: val[1].data,
            dataGrouping: {
              enabled: false
            }
          }, {
            type: 'column',
            name: '24H ' + i18n.messages[i18n.locale].message.block_exchange_volume,
            color: '#777',
            yAxis: 3,
            data: val[0].data,
            tooltip: {
              valueSuffix: ' ' + this.rate.symbol,
              valueDecimals: 2
            },
            dataGrouping: {
              approximation: 'average',
              enabled: false
            }
          }
          ]
        }
        this.options.yAxis[1].title.text = i18n.messages[i18n.locale].message.block_exchange_price + '(' + this.rate.symbol + ')'
        this.options.yAxis[2].title.text = i18n.messages[i18n.locale].message.block_exchange_price + '(' + this.rate.symbol + ')'
        if (this.type === 'BTC' || this.type === 'ETH') this.options.tooltip.valueDecimals = 8
        Highcharts.stockChart(this.id, this.options)
      }
    }
  }
</script>
