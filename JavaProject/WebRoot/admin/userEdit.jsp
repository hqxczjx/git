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
			<h3>修改用户</h3>
			<div style="width: 500px;margin: 0 auto;text-align: center;">
				<form action="${base}admin/UsermanagerSer?action=edit" method="post">
					<h4>用户ID：${user.userId }</h4>
					<input type="text" name="user" placeholder="用户名" value="${user.userAcount }" style="width: 100%; height: 30px;display: block;margin: 10px;" />
					<input type="password" name="pwd" placeholder="密码"  style="width: 100%; height: 30px;display: block;margin: 10px;" />
					<input type="submit" value="提交" class="myBtn-submit" style="width: 200px;margin: 10px;" />
				</form>
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
