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
<script type="text/javascript" src="js/main.js"></script>
</head>

<body>
	<%@ include file="head.jsp"%>
	<div class="main">
		<div class="lb"
			style="height: 300px;width: 995px;margin: 0 auto;overflow: hidden;position: relative;">
			<div
				style="width: 100%;height: 100%;background: url(img/img1.jpg);position: absolute;left: -995px;"></div>
			<div
				style="width: 100%;height: 100%;background: url(img/img2.jpg);position: absolute;left: 0px;"></div>
			<div
				style="width: 100%;height: 100%;background: url(img/img3.jpg);position: absolute;left: 995px;"></div>
			<div
				style="width: 100%;height: 100%;background: url(img/img4.jpg);position: absolute;left: 995px;"></div>
			<div
				style="width: 100%;height: 100%;background: url(img/img5.jpg);position: absolute;left: 995px;"></div>
		</div>
		<div class="index-nav">
			<dl>
				<dt>分类</dt>
				<c:forEach items="${types }" var="type">
				<dd>
					<a href="ClassificationSer?type=${type.typeId }"><button class="myButton">${type.typeName }</button></a>
				</dd>
	
				</c:forEach>
				<dd style="float: right;">
					<a href="ClassificationSer">更多></a>
				</dd>
			</dl>
		</div>
		<hr style="width: 995px;margin: 0 auto;" />
		<div style="width: 995px;margin: 0 auto;padding: 10px;">
			<h3 style="text-align: center;padding-bottom: 10px;">
				热门图书<span style="font-size: 16px;float: right;"><a href="HotSer">更多></a></span>
			</h3>
			
			<div class="book-list">
				<dl>
				<c:forEach varStatus="s" items="${hots }" var="book">
					<dd>
						<a href="DetailSer?id=${book.bookId }" class="book-total">
							<div class="book" style="background: url(${book.bookPic});">

							</div>
							<div class="book-info">
								<p style="padding-top: 210px;text-align: center;">${book.bookName }</p>
							</div>
							<p class="book-name">${book.bookName }</p>
						</a>
					</dd>
					</c:forEach>
				</dl>
			</div>
			
		</div>
		<hr style="width: 995px;margin: 20px auto;" />
	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>
