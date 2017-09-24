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
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.css"
	rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.js"></script>
<script type="text/javascript" src="./js/user.js"></script>
</head>

<body>
	<%@ include file="../head.jsp"%>
	<div class="main">
		
		<div class="person-right">

			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#home"
					aria-controls="home" role="tab" data-toggle="tab">基本信息</a></li>
				<li role="presentation"><a href="#profile"
					aria-controls="profile" role="tab" data-toggle="tab">当前借阅</a></li>
				<li role="presentation"><a href="#messages"
					aria-controls="messages" role="tab" data-toggle="tab">历史借阅</a></li>
				<li role="presentation"><a href="#settings"
					aria-controls="settings" role="tab" data-toggle="tab">修改信息</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane fade in active" id="home"
					style="padding: 10px;">
					<p>证件开始时间：2014-09-28</p>
					<p>证件结束时间：2018-07-30</p>
					<button class="btn btn-danger">最多可借：18</button>
					<button class="btn btn-success">当前已借：${list.size()}</button>
				</div>
				<div role="tabpanel" class="tab-pane fade" id="profile">
					<div class="fl-info">
						<p>
							<span>当前借阅：</span>
						</p>
						<ul style="padding: 10px;">
							<c:forEach  items="${list}" var="r" begin="0" varStatus="s">

								<c:choose>
									<c:when test="${r.recordState == 0 || r.recordState == 1 }">
										<li>
											<p>
												<span>${r.book.bookId}</span> <span><strong><a
														href="DetailSer?id=${r.book.bookId }"
														style="text-decoration: none;">${r.book.bookName}</a></strong></span> <span>${r.book.bookIden}</span>
												<span>应还时间：${r.recordEndDate}</span>
												<c:choose>
													<c:when test="${r.recordState == 0 }">
														<button class="btn btn-sm btn-success"
															style="float: right;line-height: 16px;height: 14px;">
															<a href="user/PersonSer?action=xujie&id=${r.recordId }">续借</a>
														</button>
													</c:when>
													<c:otherwise>
														<button class="btn btn-sm btn-success"
															style="float: right;line-height: 16px;height: 14px;">已续借</button>
													</c:otherwise>
												</c:choose>
											</p>
										</li>
									</c:when>
								</c:choose>
							</c:forEach>

						</ul>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane fade" id="messages">
					<div class="fl-info">
						<p>
							<span>至今借阅：</span>
						</p>
						<ul style="padding: 10px;">
							<c:forEach items="${list}" var="r" begin="0" varStatus="s">
								<li>
									<p>
										<span>${s.count}</span> <span><strong>借书日期:${r.recordBeginDate
												} 书名:<a href="DetailSer?id=${r.book.bookId }"
												style="text-decoration: none;">${r.book.bookName}</a>
										</strong></span> <span>ISBN:${r.book.bookIden}</span>
									</p>
									<p style="font-size: 14px;">
										<span>${r.book.author}</span> <span style="float: right;">状态:
											<c:choose>
												<c:when test="${r.recordState ==0}">
												正在借书
											</c:when>
												<c:when test="${r.recordState ==1}">
												正在续借
											</c:when>
												<c:when test="${r.recordState ==2}">
												已还书
											</c:when>
												<c:otherwise>
												拖欠中
											</c:otherwise>
											</c:choose>

										</span>
									</p>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane fade text-center" id="settings"
					style="padding: 10px;">
					<h3>修改密码</h3>
					<form action="RegisterSer?action=update" method="post">
						<input type="password" name="pwd" placeholder="输入密码"
							style="height: 30px;width: 400px;margin: 10px;" /> <input
							type="password" name="repwd" placeholder="重复密码"
							style="height: 30px;width: 400px;margin: 10px;" /> <input
							type="submit" value="确认" class="btn btn-primary"
							style="display: block;margin: 0 auto;width: 200px;" />
					</form>
				</div>
			</div>

		</div>
	</div>
	<%@ include file="../foot.jsp"%>
</body>
</html>
