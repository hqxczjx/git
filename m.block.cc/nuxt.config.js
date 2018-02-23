module.exports = {
  /*
  ** Headers of the page
  */
  head: {
    title: 'blockcc_区块链数字货币大全_最新最全虚拟币价格和资讯',
    meta: [
      { charset: 'utf-8' },
      { hid: 'description', name: 'description', content: 'coinmarket,数字货币数据,区块数据,block,blockchain,数字货币行情,虚拟货币' },
      { hid: 'keywords', name: 'keywords', content: 'block.cc 是专注区块、数字货币行情、资讯的平台,提供实时行情、专业K线、及虚拟货币资讯等多方位服务,我们的宗旨是ALL IN ONE BLOCK,即数据整合汇聚，打造一个大数据平台' }
    ],
    script: [
      { src: 'https://blockchains.oss-cn-shanghai.aliyuncs.com/static/prod/flexible.js' },
      { src: 'https://cdn.bootcss.com/babel-polyfill/6.23.0/polyfill.min.js' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      { rel: 'stylesheet', href: '//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' },
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: ['~/components/loading.vue'],
  // render: {
  //   bundleRenderer: {
  //     cache: require('lru-cache')({
  //       max: 1000,
  //       maxAge: 1000 * 60 * 15
  //     })
  //   }
  // },
  /*
  ** Build configuration
  */
  build: {
    /*
    ** Run ESLint on save
    */
    vendor: ['axios', 'vue-i18n']
    // extend (config, { isDev, isClient }) {
    //   if (isDev && isClient) {
    //     config.module.rules.push({
    //       enforce: 'pre',
    //       test: /\.(js|vue)$/,
    //       // loader: 'eslint-loader',
    //       exclude: /(node_modules)/
    //     })
    //   }
    // }
  },
  // router: {
  //   middleware: 'i18n'
  // },
  // cache: true,
  plugins: [
    { src: '~/plugins/globe.js'},
    { src: '~/plugins/i18n.js' }
  ]
}
