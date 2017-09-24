var sum = 1;
//轮播图
var lunbo = function() {
	$('.lb>div').eq(sum - 1 < 0 ? 4 : sum - 1).animate({
		top: '300px'
	});
	$('.lb>div').eq(sum - 1 < 0 ? 4 : sum - 1).animate({
		left: '995px'
	});
	$('.lb>div').eq(sum - 1 < 0 ? 4 : sum - 1).animate({
		top: '0px'
	});
	$('.lb>div').eq(sum).animate({
		left: '-995px'
	}, 1000);
	$('.lb>div').eq(sum + 1 >= 5 ? 0 : sum + 1).animate({
		left: '0px'
	}, 1000);
	sum = sum + 1;
	if (sum >= 5) {
		sum = 0;
	}
}

var timer = null;

function change() {
	clearInterval(timer);
	timer = null;
	timer = setInterval(lunbo, 3000);
}

$(document).ready(function() {
	//判断页面是否显示，是则播放轮播图，不是就停止播放
	document.addEventListener("visibilitychange", function() {
		if(document.hidden === true){
			clearInterval(timer);
			timer = null;
		}
		else{
			timer = setInterval(lunbo, 3000);
		}
	});
	
	//热门图书效果
	$('.book-info').mouseenter(function() {
		$(this).children().stop().animate({
			paddingTop: "10px"
		}, 500);
		$(this).parent().children('.book-name').stop().fadeOut(500);
	});
	$('.book-info').mouseleave(function() {
		$(this).children().stop().animate({
			paddingTop: "210px"
		}, 500);
		$(this).parent().children('.book-name').stop().fadeIn(1000);
	});

	//分类的热门图书
	var temp = 1;
	$('.fl-hot a').mouseenter(function() {
		temp = $(this).data('num');
		$('.fl-hot-show-list').children('div').hide();
		$('.num' + temp).show();
		switch (temp) {
			case 1:
				$('.fl-hot-show-list>p').html('<span class="glyphicon glyphicon-play">计算机');
				break;
			case 2:
				$('.fl-hot-show-list>p').html('<span class="glyphicon glyphicon-play">人文');
				break;
			case 3:
				$('.fl-hot-show-list>p').html('<span class="glyphicon glyphicon-play">数学');
				break;
			case 4:
				$('.fl-hot-show-list>p').html('<span class="glyphicon glyphicon-play">语文');
				break;
			case 5:
				$('.fl-hot-show-list>p').html('<span class="glyphicon glyphicon-play">英语');
				break;
			case 6:
				$('.fl-hot-show-list>p').html('<span class="glyphicon glyphicon-play">历史');
				break;
			case 7:
				$('.fl-hot-show-list>p').html('<span class="glyphicon glyphicon-play">地理');
				break;
			case 8:
				$('.fl-hot-show-list>p').html('<span class="glyphicon glyphicon-play">生物');
				break;
			case 9:
				$('.fl-hot-show-list>p').html('<span class="glyphicon glyphicon-play">物理');
				break;
		}
	});
});
change();