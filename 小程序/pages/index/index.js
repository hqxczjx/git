//index.js
//获取应用实例
/*var app = getApp()
Page({
  data: {
    motto: 'zjx',
    userInfo: {}
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    console.log('onLoad')
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function(userInfo){
      //更新数据
      that.setData({
        userInfo:userInfo
      })
    })
  }
})*/
Page({
  data: {
    add1:1,
    add2:2,
    fal:0,
    jia:false,
    jian:false,
    first:0
  },
  /*changecal: function(e){
    this.setData({
      fal: parseInt(this.data.add1) + parseInt(this.data.add2)
    })
  },
  inputHandle1: function(e){
    this.data.add1 = e.detail.value;
  },
  inputHandle2: function(e){
    this.data.add2 = e.detail.value;
  },*/
  btnClick9: function(e){
    if(this.data.fal === 0 || !(this.data.jia == false && this.data.jian ==false)){
      this.setData({
        fal: 9
      })
    }
    else{
      this.setData({
        fal: this.data.fal + '9'
      })
    }
  },
  btnClick8: function(e){
    if(this.data.fal === 0 || !(this.data.jia == false && this.data.jian ==false)){
      this.setData({
        fal: 8
      })
    }
    else{
      this.setData({
        fal: this.data.fal + '8'
      })
    }
  },
  btnClick7: function(e){
    if(this.data.fal === 0 || !(this.data.jia == false && this.data.jian ==false)){
      this.setData({
        fal: 7
      })
    }
    else{
      this.setData({
        fal: this.data.fal + '7'
      })
    }
  },
  btnClick6: function(e){
    if(this.data.fal === 0 || !(this.data.jia == false && this.data.jian ==false)){
      this.setData({
        fal: 6
      })
    }
    else{
      this.setData({
        fal: this.data.fal + '6'
      })
    }
  },
  btnClick5: function(e){
    if(this.data.fal === 0 || !(this.data.jia == false && this.data.jian ==false)){
      this.setData({
        fal: 5
      })
    }
    else{
      this.setData({
        fal: this.data.fal + '5'
      })
    }
  },
  btnClick4: function(e){
    if(this.data.fal === 0 || !(this.data.jia == false && this.data.jian ==false)){
      this.setData({
        fal: 4
      })
    }
    else{
      this.setData({
        fal: this.data.fal + '4'
      })
    }
  },
  btnClick3: function(e){
    if(this.data.fal === 0 || !(this.data.jia == false && this.data.jian ==false)){
      this.setData({
        fal: 3
      })
    }
    else{
      this.setData({
        fal: this.data.fal + '3'
      })
    }
  },
  btnClick2: function(e){
    if(this.data.fal === 0 || !(this.data.jia == false && this.data.jian ==false)){
      this.setData({
        fal: 2
      })
    }
    else{
      this.setData({
        fal: this.data.fal + '2'
      })
    }
  },
  btnClick1: function(e){
    if(this.data.fal === 0 || !(this.data.jia == false && this.data.jian ==false)){
      this.setData({
        fal: 1
      })
    }
    else{
      this.setData({
        fal: this.data.fal + '1'
      })
    }
  },
  yun: function(e){
    if(e.target.id == 'a1'){
      this.data.jia = true;
      this.data.jian = false;
    }
    else if(e.target.id == 'a2'){
      this.data.jia = false;
      this.data.jian = true;
    }
    this.setData({
      first: parseInt(this.data.fal),
      fal: 0
    })
  },
  last: function(e){
    if(this.data.jia == true){
      this.setData({
        fal: this.data.first + parseInt(this.data.fal)
      });
    }
    else if(this.data.jian == true){
      this.setData({
        fal: this.data.first - parseInt(this.data.fal)
      });
    }
  }
})