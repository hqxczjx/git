<template>
  <div class="hello">
    <div class="nav">
      <button class="btn" @click="change">{{txt}}</button>
      <div class="show" ref="test">
        <h1>hello</h1>
      </div>
    </div>
    <div class="full" ref="first" style="height: 100%;">
        <div class="scroll"></div>
        <div class="page" style="background-color: #ffffff;">
            <div>
              <h1 class="text-output"></h1><h1 class="raf" style="display: inline;">|</h1>
            </div>
        </div>
        <div class="page" style="background-color: #ffffff;">
            <h2>Contact</h2>
            <div class="social">
              <dl>
                <dd>
                  <i class="fa fa-qq" aria-hidden="true"></i>QQ:<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=965405501&site=qq&menu=yes">965405501</a>
                </dd>
                <dd>
                  <i class="fa fa-github" aria-hidden="true"></i>GitHub:<a target="_blank" href="https://github.com/hqxczjx">hqxczjx</a>
                </dd>
                <dd>
                  <i class="fa fa-home" aria-hidden="true"></i>Blog:<a target="_blank" href="http://loliboom.com.cn/">loliboom.com.cn</a>
                </dd>
              </dl>
            </div>
        </div>
        <div class="page" style="background-color: #ffffff;">
            <div>
              <h2>Lab</h2>
              <div style="margin: 30px auto;">
                <div class="lab-items">
                  <a target="_blank" href="./../../static/createImage.html">
                    <h3>Create Image</h3>
                  </a>
                </div>
                <div class="lab-items">
                  <a target="_blank" href="./../../static/waterFall.html">
                    <h3>Water Fall</h3>
                  </a>
                </div>
                <div class="lab-items">
                  <a target="_blank" href="./../../static/fullPage.html">
                    <h3>Full Page</h3>
                  </a>
                </div>
              </div>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import {fullPage} from './../assets/courtier-fullPage.js'
import {textOutPut} from './../assets/courtier-textInput.js'
// fullPage(5);
export default {
  name: "HelloWorld",
  data() {
    return {
      msg: "Hello,i'm courtier",
      show: true,
      txt: 'menu'
    };
  },
  methods: {
    change(e) {
      this.show = !this.show;
      if(this.show === true) {
        this.txt = 'menu';
        e.target.classList.remove('close');
        e.target.classList.add('btn');
        setTimeout(() => {
          document.querySelector('.nav').classList.remove('nav-show');
        }, 1000);
      }
      else {
        this.txt = 'close';
        e.target.classList.remove('btn');
        e.target.classList.add('close');
        document.querySelector('.nav').classList.add('nav-show');
      }
    }
  },
  watch: {
    show() {
      if(this.show === false) {
        this.$refs.test.classList.remove('no-active');
        this.$refs.test.classList.add('active');
      }
      else {
        this.$refs.test.classList.remove('active');
        this.$refs.test.classList.add('no-active');
      }
    }
  },
  mounted: function(){
    textOutPut(this.msg);
    fullPage(3);
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.hello {
  height: 100%;
}
.btn {
  border: 1px solid transparent;
  background-color: #ffffff;
  position: absolute;
  top: 0;
  right: 0;
  margin: 0 auto;
  width: 150px;
  height: 80px;
  cursor: pointer;
  z-index: 10;
  box-shadow: #ffffff 0 0 0 0;
  transition: box-shadow 1s;
}
.btn:hover {
  box-shadow: #8a8a8a 0 0 30px 10px;
}
.close {
  border: 1px solid transparent;
  background-color: #ffffff;
  position: absolute;
  top: 0;
  right: 0;
  margin: 0 auto;
  z-index: 10;
  width: 150px;
  height: 80px;
  cursor: pointer;
}
.show {
  background-color: #ffffff;
  position: absolute;
  top: 0;
  right: 0;
  width: 150px;
  height: 80px;
}
.nav {
  position: fixed;
  top: 0;
  right: 0;
  z-index: 5;
}
.nav-show {
  bottom: 0;
  left: 0;
}
/* .new-big {
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 10;
} */
.active {
  width: 100%;
  height: 100%;
  transition: width .5s ease-in-out,height .5s ease-in-out;
  box-shadow: #8a8a8a 0 0 30px 10px;
}
.no-active {
  width: 150px;
  height:80px;
  transition: width .5s ease-in-out,height .5s ease-in-out;
}
.page {
  padding-top: 80px;
}
/* .test-enter-active {
  animation: new-big 2s;
}
.test-leave-active {
  animation: new-big 2s reverse;
} */
/* @keyframes new-big {
  0% {
    width: 150px;
    height: 80px;
  }
  100% {
    width: 100%;
    height: 100%;
  }
} */
.social {
  display: inline-block;
  transform: translateY(150%);
}
.social dl {
  line-height: 2.5;
}
.social dd {
  text-align: left;
}
.lab-items {
  display: inline-block;
  width: 25%;
  height: 150px;
  border-radius: 5px;
  margin: 10px;
  transition: box-shadow .5s;
  border: 1px solid #eeeeee;
}
.lab-items:hover {
  box-shadow: #8a8a8a 10px 10px 5px;
}
.lab-items a {
  display: block;
  height: 100%;
  width: 100%;
  text-decoration: none;
  color: #000000;
  line-height: 150px;
}
</style>
