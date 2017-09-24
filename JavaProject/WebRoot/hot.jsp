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
		<table class="hot-info">
			<tr style="background: #DCDCDC;">
				<th></th>
				<th>书名</th>
				<th>作者</th>
				<th>类型</th>
				<th>ISBN</th>
				<th>馆藏</th>
			</tr>
			<c:forEach varStatus="s" items="${books }" var="book">
			<tr>
				<td>${s.count }</td>
				<td><a href="DetailSer?id=${book.bookId }">${book.bookName }</td>
				<td>${book.author}</a></td>
				<td><a href="ClassificationSer?type=${book.type.typeId }">${book.type.typeName }</a></td>
				<td>${book.bookIden }</td>
				<td>${book.bookNum }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>
