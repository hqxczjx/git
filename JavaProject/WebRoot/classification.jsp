<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<dl class="fl-nav">
			<dt>
				<a href="ClassificationSer" style="color: white;">全部分类</a>
			</dt>
			
			<c:forEach items="${tlist}" var="t">
				<dd>
					<a href="ClassificationSer?type=${t.typeId }">${t.typeName}</a>
				</dd>
			</c:forEach>
			
		</dl>
		<div class="fl-info">
			<p>
				<span>分类：${typeString}</span> <span>命中数：${typeSum}</span>
			</p>
			<ul style="padding: 10px;">
				<c:forEach items="${pageUtilBookInfo.list }" var="l" varStatus="i">
					<li>
						<p>
							<span>书本Id:${l.bookId}.</span> <span><strong><a href="DetailSer?id=${l.bookId }"
									style="text-decoration: none;">${l.bookName }</a></strong></span> <span>${l.bookIden }</span>
						</p>
						<p style="font-size: 14px;">
							<span>${l.author }著 </span> <span></span>
							<span style="float: right;">库存：${l.bookNum }</span>
						</p>
					</li>		
				</c:forEach>
		
			</ul>
			
			<nav style="text-align: center;">
				<div>
					    共 ${pageUtilBookInfo.pageNumber} 页，当前第  ${pageUtilBookInfo.index }  页  
					 <a href="ClassificationSer?type=${TypeId}&index=1">首页</a> 
					 <a href="ClassificationSer?type=${TypeId}&index=${pageUtilBookInfo.index>1? pageUtilBookInfo.index-1 : 1}">上一页</a>  
					 <c:forEach begin="1" end="${pageUtilBookInfo.pageNumber}" var="i">
					    <a href="ClassificationSer?type=${TypeId}&index=${i}">${i}</a>
					 </c:forEach>
				     <a href="ClassificationSer?type=${TypeId}&index=${pageUtilBookInfo.pageNumber>pageUtilBookInfo.index ? pageUtilBookInfo.index+1 : pageUtilBookInfo.pageNumber}">下一页</a>
				    
				     <a href="ClassificationSer?type=${TypeId}&index=${pageUtilBookInfo.pageNumber}">末页</a> 
				</div>
			</nav>
			
		</div>
	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>
