<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="fl-info" style="margin: 0 auto;width: 80%;display: block;">
			<p>
				检索到

				<mark>${sum}</mark>
				条结果
			</p>
			<c:forEach 	varStatus="s" items="${books}" var="b">
			<ul style="padding: 10px;">
				<li>
					<p>
						<span>${s.count}.</span> <span><strong>书名:<a href="DetailSer?id=${b.bookId }"
								style="text-decoration: none;">${b.bookName } </a></strong></span> <span>ISBN:${b.bookIden }</span>
					</p>
					<p style="font-size: 14px;">
						<span>作者:${b.author } </span>  
						<span
							style="float: right;">库存:${b.bookNum }</span>
					</p>
				</li>
			</ul>
			</c:forEach>
			
		</div>
	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>
