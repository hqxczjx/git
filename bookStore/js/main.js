/**
 * Created by ZJX on 2017/5/17.
 */
$(document).ready(function(){
    //轮播图
    $(".main_visual").hover(function(){
        $("#btn_prev,#btn_next").fadeIn()
    },function(){
        $("#btn_prev,#btn_next").fadeOut()
    });
    var $dragBln = false;
    $(".main_image").touchSlider({
        flexible : true,
        speed : 200,
        btn_prev : $("#btn_prev"),
        btn_next : $("#btn_next"),
        paging : $(".flicking_con a"),
        counter : function (e){
            $(".flicking_con a").removeClass("on").eq(e.current-1).addClass("on");
        }
    });
    $(".main_image").bind("mousedown", function() {
        $dragBln = false;
    });
    $(".main_image").bind("dragstart", function() {
        $dragBln = true;
    });
    $(".main_image a").click(function(){
        if($dragBln) {
            return false;
        }
    });
    var timer = setInterval(function(){
        $("#btn_next").click();
    }, 5000);
    $(".main_visual").hover(function(){
        clearInterval(timer);
    },function(){
        timer = setInterval(function(){
            $("#btn_next").click();
        },5000);
    });
    $(".main_image").bind("touchstart",function(){
        clearInterval(timer);
    }).bind("touchend", function(){
        timer = setInterval(function(){
            $("#btn_next").click();
        }, 5000);
    });

    //书效果
    $('.zheMu').hover(function () {
        $(this).find('.img-name').css('bottom','150px');
        $(this).parent().children('.book-name').css('display','none');
    },function () {
        $(this).find('.img-name').css('bottom','-20px');
        var that = $(this);
        setTimeout(function () {
            that.parent().children('.book-name').css('display','block');
        },400);
    });

    //回到顶部
    $('.ding').click(function () {
        var speed=200;//滑动的速度
        $('body,html').animate({ scrollTop: 0 }, speed);
    });

    //删除收藏
    $('.delColl').click(function () {
        $(this).parent().remove();
    });

    $('a[href="#coll"]').click(function () {
        $(this).tab('show');
        $('.list-group').children().removeClass('active');
        $(this).addClass('active');
    });

    $('a[href="#xg"]').click(function () {
        $(this).tab('show');
        $('.list-group').children().removeClass('active');
        $(this).addClass('active');
    });

    //修改信息
    $('.btnSubmit').click(function (e) {
        //e.preventDefault();
        if($('.input-style[type="text"]').val() === ''){
            alert('用户名不能为空');
            e.preventDefault();
        }
        else if($('.input-style[type="email"]').val() === ''){
            alert('邮箱不能为空');
            e.preventDefault();
        }
        else if($('.input-style[type="password"]').val() === ''){
            alert('密码不能为空');
            e.preventDefault();
        }
        else if($('.pwd1').val() !== $('.pwd2').val()){
            alert('两次密码输入不一样');
            e.preventDefault();
        }
        else{
            alert('修改成功');
        }
    });
    
    //判断是否能进入个人中心
    $('.btnPerson').click(function () {
        if(window.localStorage.getItem('status') !== null && window.localStorage.getItem('status') !== undefined){
            window.location.href = 'person.html';
        }
        else{
            alert('你还没登录');
            window.location.href = 'login.html';
        }
    });

    //判断是否登录
    if(window.localStorage.getItem('status') !== null && window.localStorage.getItem('status') !== undefined){
        $('.login').hide();
        $('.login').parent().children('span').html('欢迎您，' + window.localStorage.getItem('status'));
        $('.login').parent().children('span').show();
        $('.exit').show();
    }

    //图书详情
    $('.book-card').click(function () {
        window.location.href = 'bookDetails.html#' + $(this).parent().find('.bookName').html();
    });

    //登录
    $('.btnLogin').click(function () {
        var userIp = $('.loginuser').val(),
            userpwd = $('.loginpwd').val();
        if(window.localStorage.getItem(userIp) !== null && window.localStorage.getItem(userIp) !== undefined){
            var now = JSON.parse(window.localStorage.getItem(userIp));
            if(userpwd === now.password){
                window.localStorage.setItem('status', userIp);
                alert('登录成功');
                window.location.href = 'index.html';
            }
            else{
                alert('密码错误');
            }
        }
        else{
            alert('用户不存在');
        }
    });
    
    //退出
    $('.exit').click(function () {
        window.localStorage.removeItem('status');
        $('.login').show();
        $(this).parent().children('span').hide();
        $(this).hide();
        window.location.href = 'index.html';
    });

    $('.bookColl').click(function () {
        var thisBook = JSON.parse(window.localStorage.getItem($('.bookName').text().slice(0, $('.bookName').text().length - 3)));
        var name = window.localStorage.getItem('status');
        if(name !== null && name !== undefined){
            var list = JSON.parse(window.localStorage.getItem(name));
            list.coll.push(thisBook);
            window.localStorage.setItem(name,JSON.stringify(list));
            alert('收藏成功');
        }
        else{
            alert('你还没登录');
        }
    });

});