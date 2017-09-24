<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header
	style="padding: 5px;background: url(img/headerBg.jpg) repeat-x;height: 110px;">
	<h2
		style="padding: 0 60px;display: inline-block;width: 70%;background: url(img/header_img02.jpg) right no-repeat;height: 110px;line-height: 40px;">
		<div><a href="first" style="text-decoration: none;">图书管理系统</a></div>
		<p style="padding: 0;margin: 0;font-size: 16px;">Online Public
			Access Catalogue</p>
	</h2>
	<div
		style="display: inline-block;width: 18%;text-align: right;padding-left: 10px;vertical-align: bottom;height: 110px;">
		<c:choose>
			<c:when test="${ empty info || info.roleinfo.roleId != 2}">
				<a href="${base }LoginSer">登录</a>
			</c:when>
			
			<c:otherwise>
				<span>${sessionScope.info.userAcount }</span>
				<a href="LoginSer?action=logout" style="color:red;">注销</a>
			</c:otherwise>
		</c:choose>
	</div>
</header>
<nav class="hander-nav" style="background: url(img/banner.png);">
	<dl>
		<dd>
			<a href="IndexSer">书目检索</a>
		</dd>
		<dd>
			<a href="ClassificationSer">分类浏览</a>
		</dd>
		<dd>
			<a href="HotSer">热门推荐</a>
		</dd>
		
		<c:choose>
			<c:when test="${ empty info || info.roleinfo.roleId != 2}">
				
			</c:when>
			
			<c:otherwise>
				<dd>
					<a href="user/PersonSer">个人中心</a>
				</dd>
			</c:otherwise>
		</c:choose>
		
		
	</dl>
</nav>