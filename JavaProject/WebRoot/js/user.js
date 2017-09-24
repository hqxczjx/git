$(document).ready(function() {
	$('input[type=submit]').click(function() {
		var pwd = $('input[name=pwd]').val();
		var repwd = $('input[name=repwd]').val();
		if(pwd === '' || repwd === ''){
			alert('请确认密码');
			return false;
		}
		else if(pwd !== repwd){
			alert('两次密码不一致，请重新输入');
			$('input[name=pwd]').val('');
			$('input[name=repwd]').val('');
			return false;
		}
	});
});