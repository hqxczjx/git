<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header style="padding: 5px;background: url(img/headerBg.jpg) repeat-x;height: 110px;">
				<h2 style="padding: 0 60px;display: inline-block;width: 70%;background: url(img/header_img02.jpg) right no-repeat;height: 110px;line-height: 40px;">
		<div><a href="first" style="text-decoration: none;">图书管理系统</a><small>后台管理</small></div>
		<p style="padding: 0;margin: 0;font-size: 16px;">
			Online Public Access Catalogue
		</p>
	          </h2>		
	          <div
		style="display: inline-block;width: 18%;text-align: right;padding-left: 10px;vertical-align: bottom;height: 110px;">
		<div class="dropdown">
        <button class="dropbtn" >管理员设置</button>
        <div class="dropdown-content">
        <a href="LoginAdminSer?action=layout">退 &nbsp;&nbsp;&nbsp;&nbsp;出&nbsp;</a>
  
  </div>
	</div>
		</header>
<nav class="hander-nav" style="background: url(img/banner.png);">
			<dl >
				<dd>
				<a href="admin/bookmanager">图书管理</a>
				
			</dd>
			<dd>
				<a href="${base}admin/UsermanagerSer">用户管理</a>
				
			</dd>
			<dd>
				<a href="${base}admin/LendandreturnSer">借书还书</a>
				
			</dd>
			<dd>
				<a href="admin/TuiJianSer">推荐信息</a>
			</dd>
</nav>