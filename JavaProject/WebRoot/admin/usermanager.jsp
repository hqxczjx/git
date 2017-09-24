<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


	<%@ include file="head.jsp"%>
	<div class="main">
	<h4 style="width: 80%;margin: 0 auto;"><a href="${base}admin/addUser.jsp">添加用戶</a></h4>
		<table class="hot-info">
			<div role="tabpanel" class="tab-pane fade text-center" id="settings"
				style="padding: 10px;">
				<table class="hot-info">
					<tr style="background: #DCDCDC;">

						<th>用户id</th>
						<th>用户名</th>
						<th>权限</th>
						<th>状态</th>
						<th>操作</th>
					</tr>

					<c:forEach items="${pageUtil.list}" var="r" begin="0" varStatus="s">
						<tr>
							<td>${r.userId }</td>
							<td>${r.userAcount }</td>
							<td>${r.roleinfo.roleName }</td>
							<td><c:choose>
									<c:when test="${r.deleted == 0 }">存在</c:when>
									<c:when test="${r.deleted == 1 }">已删除</c:when>
								</c:choose></td>
							<td>
							<c:choose>
									<c:when test="${r.deleted == 0 }">
							<button
									style="border: none ;background-color: #FFFFFF ; "onclick="window.location.href='admin/UsermanagerSer?action=del&id=${r.userId}'">删除</button>
								<button style="border: none ;background-color: #FFFFFF ;"onclick="window.location.href='admin/UsermanagerSer?action=update&id=${r.userId}'">编辑</button>
								</c:when>
								</c:choose>
								</td>
						
						</tr>
					</c:forEach>
				</table>
				
				<nav  style="display: inline-block;width: 100%;text-align: center;padding: 10px;height: 60px;">
				<div>
					    共 ${pageUtil.pageNumber} 页，当前第  ${pageUtil.index }  页  
					 <a href="admin/UsermanagerSer?index=1">首页</a> 
					 <a href="admin/UsermanagerSer?index=${pageUtil.index>1? pageUtil.index-1 : 1}">上一页</a>  
					 <c:forEach begin="1" end="${pageUtil.pageNumber}" var="i">
					    <a href="admin/UsermanagerSer?index=${i}">${i}</a>
					 </c:forEach>
				     <a href="admin/UsermanagerSer?index=${pageUtil.pageNumber>pageUtil.index ? pageUtil.index+1 : pageUtil.pageNumber}">下一页</a>
				     <a href="admin/UsermanagerSer?index=${pageUtil.pageNumber}">末页</a> 
				</div>
			</nav>
			
			

			</div>
			</div>


			
			<footer
				style="text-align: center;background: #a1a1a1;position: absolute;bottom: 0;left: 0;right: 0;padding: 10px;height: 60px;">
			<p>版权所有：最酷炫的人</p>
			<p>from2017-2018</p>
			</footer>
			<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
			<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
			<!-- Include all compiled plugins (below), or include individual files as needed -->
			<script src="js/bootstrap.min.js"></script>
</body>
</html>
