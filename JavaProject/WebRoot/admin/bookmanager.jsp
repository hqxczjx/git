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
<link rel="stylesheet" type="text/css" href="css/style2.css" />
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.css"
	rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.js"></script>
</head>

<body>

		 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="js/bootstrap.min.js"></script>
		


	
	<%@ include file="head.jsp"%>
	<div class="main">
		<dl class="fl-nav">

		
			<dt class="fix">
				<a href="admin/bookmanager">全部分类</a>

			</dt>
			
			<c:forEach varStatus="s" items="${types }" var="type">
			<dd>
					<a href="admin/bookmanager?typeId=${type.typeId }">${type.typeName }</a>
				</dd>
					
			</c:forEach>

			<a href="admin/bookmanager?tag=add"><img src="img/jia.jpg" height="30px"></img></a>
		</dl>


		<div class="fl-info">
			
			<table class="hot-info">
			<tr style="background: #DCDCDC;">
				<th>图片</th>
				<th>作者</th>
				<th>书名</th>
				<th>简介</th>
				<th>ISBN</th>
				<th>馆藏</th>
				<th>操作</th>
			</tr>
				<c:forEach varStatus="s" items="${pageUtil.list }" var="book">
					<tr >
					<th>	<img src="${book.bookPic}" class="book" style="float: right;" height="50px"/></th>
						<th>${book.author }</th>
						<th>${book.bookName }</th>
						<th>${book.bookItr	 }</th>
						<th>${book.bookIden }</th>
						<th>${book.bookNum }</th>
						<th>

							<a href="admin/bookmanager?tag=del&bookid=${book.bookId }&typeId=${TypeId}&index=${pageUtil.pageNumber}" onclick="return confirm('你确定要删除  ${book.bookName} 书籍信息吗？')">
							<button style="border: none ;background-color: #FFFFFF ;" >删除</button></a>
							<a href="admin/bookmanager?tag=update&bookid=${book.bookId }" >
							<button style="border: none ;background-color: #FFFFFF ;">编辑</button></a>
							

						</th>
					</tr>
			</c:forEach>		
					

			
		</table>
			
			<nav  style="display: inline-block;width: 100%;text-align: center;padding: 10px;height: 60px;">
				<div>
					    共 ${pageUtil.pageNumber} 页，当前第  ${pageUtil.index }  页  
					 <a href="admin/bookmanager?typeId=${TypeId}&index=1">首页</a> 
					 <a href="admin/bookmanager?typeId=${TypeId}&index=${pageUtil.index>1? pageUtil.index-1 : 1}">上一页</a>  
					 <c:forEach begin="1" end="${pageUtil.pageNumber}" var="i">
					    <a href="admin/bookmanager?typeId=${TypeId}&index=${i}">${i}</a>
					 </c:forEach>
				     <a href="admin/bookmanager?typeId=${TypeId}&index=${pageUtil.pageNumber>pageUtil.index ? pageUtil.index+1 : pageUtil.pageNumber}">下一页</a>
				     <a href="admin/bookmanager?typeId=${TypeId}&index=${pageUtil.pageNumber}">末页</a> 
				</div>
			</nav>
			
		</div>
	</div>
	
	    <footer
		style="text-align: center;background: #a1a1a1;position: absolute;bottom: 0;left: 0;right: 0;padding: 10px;height: 60px;">
	<p>版权所有：最酷炫的人</p>
	<p>from2017-2018</p>
	</footer>
	</body>
</html>
