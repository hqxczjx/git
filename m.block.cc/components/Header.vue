<template>
  <div class="header" v-if="show">
    <img :src="`/images/${changeIcon()}.png`" class="menu" @click.stop="showDrawer"/>
    <nuxt-link class="logo" to="/">
      <img src="/images/logo.png">
    </nuxt-link>
    <div class="language" @click.stop="showLanguageMenu = !showLanguageMenu">
      <span v-if="showLanguage === 'zh'">中文</span>
      <span v-if="showLanguage === 'en'">English</span>
      <div v-if="showLanguageMenu === true">
        <p @click.stop="changeLanguage('zh')" :class="{ active: showLanguage === 'zh' }">中文</p>
        <p @click.stop="changeLanguage('en')" :class="{ active: showLanguage === 'en' }">English</p>
      </div>
    </div>
    <div class="person" style="display: none;">
      <img src="/images/person.png"/>
    </div>
    <div class="search" @click="go('/search')" style="display: none;">
      <img src="/images/searchicon.png"/>
    </div>
  </div>
</template>
<script>
// import i18n from './../plugins/i18n'
// import axios from 'axios'
export default {
  // i18n,
  name: 'my-header',
  props: ['show', 'showIcon'],
  data() {
    return {
      isShowDrawer: false,
      showLanguage: '',
      showLanguageMenu: false,
      date: new Date()
    }
  },
  watch: {
    showIcon(newVal) {
      this.isShowDrawer = newVal
      this.changeIcon()
    }
  },
  methods: {
    showDrawer() {
      this.$emit('showD')
      this.isShowDrawer = true
    },
    go(url) {
      this.$router.push({ path: url })
    },
    changeIcon() {
      if (this.isShowDrawer === true) {
        return 'close'
      } else {
        return 'moreicon'
      }
    },
    changeLanguage(val) {
      if (this.showLanguage !== val) {        
        this.showLanguage = val
        this.$i18n.locale = val
        this.showLanguageMenu = false
        this.$store.commit('setLocale', { locale: val })
        // this.$router.go(0)
      }
    }
  },
  mounted() {
    this.showLanguage = this.$store.state.locale
    document.addEventListener('click', () => {
      this.showLanguageMenu = false
    })
  }
}

</script>
<style scoped>
.header {
  height: 1.3333rem;
  width: 100vw;
  background-color: #2b2b2b;
  /* overflow: hidden; */
  position: relative;
}
.header .menu {
  /* width: 0.6133rem; */
  height: 0.48rem;
  padding: 0.4rem 0.2667rem;
  /* vertical-align: top; */
}
.header .logo {
  display: inline-block;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  height: 100%;
}
.header .logo img {
  width: 3.28rem;
  padding-top: 0.32rem;
  padding-bottom: 0.32rem;
  display: inline-block;
}
.header .search,
.header .person {
  float: right;
  margin: 0.3467rem;
}
.header .search {
  margin-right: 0;
  margin-left: 0;
}
.header .search img,
.header .person img {
  width: 0.5867rem;
}
.language {
  float: right;
  text-align: center;
  font-size: 0.3733rem;
  position: relative;
  width: 1.8667rem;
  height: 1.3333rem;
  color: #999999;
  line-height: 1.3333rem;
}
.language>span {
  position: relative;
}
.language>span::after {
  content: '';
  position: absolute;
  bottom: 0.0533rem;
  right: -0.1867rem;
  border-bottom: 0.16rem solid #999999;
  border-left: 0.16rem solid transparent;
  width: 0;
  height: 0;
}
.language>div {
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  background-color: #2b2b2b;
  color: #999999;
  width: 100%;
  z-index: 20;
}
.language>div>p {
  height: 1.0667rem;
  width: 100%;
  line-height: 1.0667rem;
  background-color: #2b2b2b;
  color: #999999;
}
.language>div>p.active {
  background-color: #4f4f4f;
  color: #ffffff;
}
</style>
