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
		<div class="person-left">
			<img style="width: 200px;height: 200px;border-radius: 5px;"
				src="img/header_img02.jpg" />
			<h3>2014030401002</h3>
		</div>
		<div class="person-right">
		
					<div style="margin-left: 360px;" >
					<h3>修改密码</h3>
					</div>
					<form action="#" method="post">
						<input 
							type="password" placeholder="输入密码"
							style="height: 30px;width: 400px;margin-top: 10px;margin-left: 200px;" /> 
							<input
							type="password" placeholder="重复密码"
							style="height: 30px;width: 400px;margin-top: 10px;margin-left: 200px;" />
							<input
								class="dropbtn"
							type="submit" value="确认" 
							style="display: block;margin-top: 10px;margin-left: 300px; auto;width: 200px;height: 25px;background: #66AFE9;" />
					</form>
		
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
