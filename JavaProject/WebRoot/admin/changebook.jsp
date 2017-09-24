<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addbook.jsp' starting page</title>
    
	<link rel="stylesheet" type="text/css" href="css/style.css" />
  <link rel="stylesheet" type="text/css" href="css/style2.css" />
  <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.css"
	rel="stylesheet">
  <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.js"></script>

  </head>
  
  <body>
   	<%@ include file="head.jsp"%>
   	<div>
		<form action="bookmannager.html" method="post" style="width: 500px;margin: 0 auto;">
			
			<div><strong>书籍名称：</strong><br />
				<input style="height: 30px;width: 400px;" placeholder="书籍名称" name="bookname"/>
			</div>
			<div><strong>书籍作者：</strong><br />
				<input style="height: 30px;width: 400px;" placeholder="书籍作者" name="auther"/>
				
			</div>
			<div><strong>出版社：</strong><br />
				<input style="height: 30px;width: 400px;" placeholder="出版社" name="bookpress"/>
			</div>
			<div><strong>书籍类型：</strong><br />
				<input style="height: 30px;width: 400px;" placeholder="书籍类型" name="bookclass"/>
			</div>
			
			<div><strong>书籍简介：</strong><br />
				<input style="height: 30px;width: 400px;" placeholder="书籍简介" name="booklshow"/>
				
			</div>
			<div><strong>书籍数量：</strong><br />
				<input style="height: 30px;width: 400px;" placeholder="书籍配图" name="booknum"/>
			</div>
			<div><strong>书籍配图：</strong><br />
				<input style="height: 30px;width: 400px;" placeholder="书籍配图" name="bookpic"type="file"  />
			</div>
			<div >
				<input type="button" value="确认修改"
					style="display: block;height: 25px;width: 80px; background: #66AFE9;"/>
			</div>
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
