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
		  <link rel="stylesheet" type="text/css" href="css/style2.css" />
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.css"
	rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.js"></script>
</head>

<body>
		
		<%@ include file="head.jsp"%>
		<div class="main">
		
			<div>
				<h4>
					<a href="admin/TuiJianSer?action=add">增加</a>
				</h4>
			</div>
		
			<table class="hot-info">
				<tr style="background: #DCDCDC;">
					<th>id</th>
					<th>消息内容</th>
					<th>是否显示</th>
					<th>操作</th>
				</tr>
				<c:forEach varStatus="s" items="${list }" var="l">
					<tr>
						<td>${l.msgId }</td>
						<td>${l.msgContent }</td>
						<c:choose>
							<c:when test="${l.isActive eq 1 }">
								<td>显示</td>
							</c:when>
							<c:otherwise>
								<td>不显示</td>
							</c:otherwise>
						</c:choose>
						<td><a href="admin/TuiJianSer?action=update&id=${l.msgId }">修改</a>|<a href="admin/TuiJianSer?action=del&id=${l.msgId}" onclick="confirm('是否要删除!')">删除</a></td>
					</tr>
				</c:forEach>
			</table>
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
