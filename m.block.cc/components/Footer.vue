<template>
  <footer v-if="show">
    <div class="download" >
      <div>
        <img src="/images/ICON.png"/>
        <span>Block.cc APP-{{ $t('message.footer_title') }}</span>
      </div>
      <a href="https://www.pgyer.com/5Cdb">{{ $t('message.download') }}</a>
    </div>
    <div class="version">
      <span>{{ $t('message.touch') }}</span>
      <span>|</span>
      <span><a :href="$store.state.url">{{ $t('message.computer') }}</a></span>
      <span @click="rTop">{{ $t('message.scroll') }}</span>
    </div>
    <p class="about">Block.cc Copyright &copy; 2017</p>
  </footer>
</template>
<script>
// import i18n from './../plugins/i18n'
export default {
  name: 'my-footer',
  // i18n,
  props: ['show'],
  methods: {
    rTop() {
      let timer = requestAnimationFrame(function fn() {
        if (document.documentElement.scrollTop > 0 || document.body.scrollTop > 0) {
          document.documentElement.scrollTop = document.documentElement.scrollTop / 2
          document.body.scrollTop = document.body.scrollTop / 2
          timer = requestAnimationFrame(fn)
        } else {
          cancelAnimationFrame(timer)
        }
      })
    },
    setLan(val = 'zh') {
      localStorage.setItem('lan', val)
    }
  },
  mounted() {
    if (!localStorage.getItem('lan')) {
      this.setLan()
    }
    // i18n.locale = localStorage.getItem('lan')
    // i18n.locale = this.$store.state.locale
    this.lan = this.$store.state.locale
  }
}
</script>
<style scoped>
.download {
  border-top: 0.04rem solid #d6d6d6;
  height: 1.3333rem;
  width: 100vw;
  padding: 0.2rem 0.3333rem 0.2rem 0.2667rem;
}
.download img {
  width: 1rem;
  height: 1rem;
  vertical-align: middle;
}
.download > div {
  display: inline-block;
  margin-top: 0.16rem;
}
.download > a {
  border-radius: 0.0667rem;
  position: relative;
  top: -0.1rem;
  float: right;
  border: 0;
  right: 0.3333rem;
  font-size: 0.3733rem;
  margin: 0.2667rem;
  background-color: #36a0ff;
  color: #ffffff;
  padding: 0.2rem;
  outline: none;
  text-decoration: none;
}
.download span {
  position: relative;
  top: 0.0667rem;
  font-size: 0.4267rem;
  margin-left: 0.1333rem;
}
.version {
  border-top: 1px solid #e6e6e6;
  border-bottom: 1px solid #e6e6e6;
  padding: 0.4667rem 0.2667rem 0.4rem 0.2667rem;
  font-size: 0.4267rem;
}
.version > span:nth-child(1) {
  color: #999999;
}
.version > span:nth-child(2) {
  color: #e6e6e6;
  font-size: 0.2667rem;
  position: relative;
  top: -0.08rem;
  margin: 0 0.1333rem;
}
.version > span:nth-child(1) {
  color: #7a7a7a;
}
.version > span:nth-child(4) {
  color: #999999;
  float: right;
  margin-right: 0.1067rem;
}
.version a {
  text-decoration: none;
  color: #333333;
}
.about {
  text-align: center;
  color: #666666;
  padding-top: 0.4rem;
  padding-bottom: 0.3333rem;
  font-size: 0.3733rem;
}
</style>
