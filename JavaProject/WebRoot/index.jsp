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
		<marquee direction="left" style="background: #F5F5F5;">公告：

		<c:forEach varStatus="m" items="${msgInfo }" var="msg">
			<span>
				${msg.msgContent}
			</span>
		</c:forEach>
		
		</marquee>
		<fieldset style="padding: 10px;height: 300px;">
			<legend>书目检索</legend>
			<form action="CheckSer" method="get" style="width: 500px;margin: 0 auto;">
				<input style="height: 30px;width: 400px;" placeholder="想搜什么都能搜到哦~" name="searchwhat"/>
				<input type="submit" value="搜索" class="myBtn-submit" />
				<div style="font-size: 14px;">
					<span>搜索类型：</span> <input type="radio" name="searchType"
						value="all" checked="checked" />全部 <input type="radio"
						name="searchType" value="book" />书名 <input type="radio"
						name="searchType" value="isbn" />ISBN <input type="radio"
						name="searchType" value="author" />作者 <span>匹配类型：</span> <input
						type="radio" name="matchType" value="free" checked="checked" />任意匹配
					<input type="radio" name="matchType" value="all" />完全匹配
				</div>
			</form>
		</fieldset>
	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>
