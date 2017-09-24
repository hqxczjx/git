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
		<div style="width: 500px;margin: 0 auto;text-align: center;">
			<form action="RegisterSer?action=add" method="post">
				<h3>注册界面</h3>
				<c:choose>
					<c:when test="${!empty msg }">
						<span>
							${msg }
						</span>
					</c:when>
				</c:choose>
				<input type="text" name="user" placeholder="用户名"
					style="width: 100%; height: 30px;display: block;margin: 10px;" />
				<input type="password" name="pwd" placeholder="密码"
					style="width: 100%; height: 30px;display: block;margin: 10px;" />
				<input type="password" name="repwd" placeholder="重复密码"
					style="width: 100%; height: 30px;display: block;margin: 10px;" />
				<input type="submit" value="注册" class="myBtn-submit"
					style="width: 200px;margin: 10px;" />
			</form>
			<p>
				已有账号？<a href="LoginSer">点击登录</a>
			</p>
		</div>
	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>
