<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    

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
   	<div style="height: 100%;">

	
		<form action="" method="post" style="width: 500px;margin: 0 auto;" enctype="multipart/form-data">
			<input type="hidden" value="${book.bookId}" name="bookid">
			<div style="margin-top: 10px"><strong>书籍名称：</strong><br />
				<input style="height: 30px;width: 400px;" placeholder="书籍名称" name="bookname" value="${book.bookName }"/>
			</div>
			<div style="margin-top: 10px"><strong>书籍作者：</strong><br />
				<input style="height: 30px;width: 400px;" placeholder="书籍作者" name="author" value="${book.author }"/>
				
			</div>
		
		<div style="margin-top: 10px"><strong>书籍简介：</strong><br />
				<input style="height: 30px;width: 400px;" placeholder="书籍简介" name="bookitr" value="${book.bookItr }"/>
				
			</div>
			<div style="margin-top: 10px"><strong>书籍类型：</strong>
				<select name="typeid">
				<c:forEach items="${types }" var="type">
				
				  <c:choose>
	               <c:when test="${type.typeId eq book.type.typeId }">
	                  <option value="${type.typeId }" selected="selected">${type.typeName }</option>
	               </c:when>
	               <c:otherwise>
	                  <option value="${type.typeId }" >${type.typeName}</option>	               
	               </c:otherwise>
	             </c:choose>
				</c:forEach>
				</select>
			</div>
			
			<div style="margin-top: 10px"><strong>书籍详情：</strong><br />
				<textarea style="height: 100px;width: 400px;" placeholder="书籍详情" name="bookdetail" >${book.bookDetail }</textarea>
				
			</div>
			<div style="margin-top: 10px"><strong>书籍数量：</strong>
				<input style="height: 30px;width: 100px;" placeholder="书籍数量" name="booknum" value="${book.bookNum }"/>
				<strong>书籍编码：</strong>
				<input style="height: 30px;width: 150px;" placeholder="书籍编码" name="bookiden" value="${book.bookIden }"/>
			</div>
			
			<input type="hidden" value="${book.bookPic}" name ="bookpic2" >
		
			<div style="margin-top: 10px"><strong>书籍配图：</strong><br />
				<input style="height: 30px;width: 400px;"  name="bookpic" type="file"  />
			</div>
			<div >
				<input type="submit" value="确认"

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
