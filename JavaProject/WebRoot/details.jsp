<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>图书管理系统</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.css"
	rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.js"></script>
</head>

<body>
	<%@ include file="head.jsp"%>
	<div class="main">
		<div style="width: 60%;margin: 0 auto;">
			<div style="overflow: auto;">
				<div style="display: inline-block;vertical-align: top;float: left;">
					<h3 style="margin: 5px;">
						${book.bookName } <span class="label label-default">${book.type.typeName }</span>
					</h3>
					<p style="padding: 5px;" class="isbn" data-id="${book.bookId }">ISBN：${book.bookIden }</p>
					<p style="padding: 5px;">作者：${book.author }</p>
				

					<p style="padding: 5px;">馆藏${book.bookNum}可借：<span></span><span class="borrow"></span></p>
				</div>
				<img src="${book.bookPic}" class="book" style="float: right;" height="200px"/>
			</div>
			<h3>简介</h3>
			<blockquote>
					<p>${book.bookItr }</p>
			</blockquote>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne">点击查看目录</a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse">
					<div class="panel-body">${book.bookDetail}</div>
				</div>
			</div>
		</div>
		
	</div>
	
	<script type="text/javascript">
	var borrow = function(){
		$.ajax({
				type:"post",
				url:"AjaxBookIdSumSer",
				data:{
					bookId: $('.isbn').data('id')
				},
				success:function(data){
					$('.borrow').html(data);
				},
				error:function(e){
					alert("error");
				}
			});
		}
		
		$(document).ready(function(){
			borrow();
		});
	
	</script>
	
	<%@ include file="foot.jsp"%>
</body>
</html>
