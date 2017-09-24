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
		
		<div style="width: 995px;margin: 0 auto;">
			<div style="width: 650px;margin: 0 auto;">
				<form action="${base }admin/LendandreturnSer" method="post">
					<input type="radio" name="searchType" value="user"
						checked="checked" />查找用户 <input type="radio" name="searchType"
						value="book" />查找书籍 <input style="height: 30px;width: 400px;"
						placeholder="输入需要查找的对应编号" name="searchwhat" /> <input
						type="submit" value="搜索" class="myBtn-submit" /> <span class="em"></span>
				</form>
			</div>
			
			
			
			
			
			<div>
				<table class="hot-info" style="width: 995px;" id="resultTab">
					<tr style="background: #DCDCDC;">

						<th>记录编号</th>
						<th onclick="orderByName('resultTab',2);">用户ID</th>
						<th>用户名</th>
						<th>书本ISBN</th>
						<th>书名</th>
						<th>作者</th>
						<th>借书时间</th>
						<th>到期时间</th>
						<th>借书状态</th>
						<th>还书时间</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageUtil.list}" var="r" begin="0" varStatus="s">
						<tr>
							<td>${r.recordId }</td>
							<td>${r.user.userId}</td>
							<td>${r.user.userAcount}</td>
							<td>${r.book.bookIden }</td>
							<td>${r.book.bookName }</td>
							<td>${r.book.author }</td>
							<td>${r.recordBeginDate}</td>
							<td>${r.recordEndDate}</td>
							<td><c:choose>
									<c:when test="${r.recordState == 0}">借阅中</c:when>
									<c:when test="${r.recordState == 1}">续借中</c:when>
									<c:when test="${r.recordState == 2}">已还书</c:when>
									<c:otherwise>逾期</c:otherwise>
								</c:choose></td>
							<td>${r.recordDate}</td>

							<td>
								<c:choose>
									<c:when test="${r.recordState <= 1 }">
										<button style="border: none ;background-color: #FFFFFF ; " onclick="window.location.href='${base}admin/LendandreturnSer?action=renew&id=${r.recordId }'">续借</button>
										<button style="border: none ;background-color: #FFFFFF ;"
											onclick="window.location.href='${base}admin/LendandreturnSer?action=toreturn&id=${r.recordId }'">还书</button>
									</c:when>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
				
				<nav  style="display: inline-block;width: 100%;text-align: center;padding: 10px;height: 60px;">
				<div>
					    共 ${pageUtil.pageNumber} 页，当前第  ${pageUtil.index }  页  
					 <a href="admin/LendandreturnSer?findid=${findid}&index=1">首页</a> 
					 <a href="admin/LendandreturnSer?findid=${findid}&index=${pageUtil.index>1? pageUtil.index-1 : 1}">上一页</a>  
					 <c:forEach begin="1" end="${pageUtil.pageNumber}" var="i">
					    <a href="admin/LendandreturnSer?findid=${findid}&index=${i}">${i}</a>
					 </c:forEach>
				     <a href="admin/LendandreturnSer?findid=${findid}&index=${pageUtil.pageNumber>pageUtil.index ? pageUtil.index+1 : pageUtil.pageNumber}">下一页</a>
				     <a href="admin/LendandreturnSer?findid=${findid}&index=${pageUtil.pageNumber}">末页</a> 
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
		<script>
    function  orderByName(sTableID, iCol, sDataType) {
         var  oTable = document.getElementById(sTableID);
         var  oTBody = oTable.tBodies[0];
         var  colDataRows = oTBody.rows;
         var  aTRs =  new  Array;
         for  (  var  i = 0; i < colDataRows.length; i++) {
            aTRs[i] = colDataRows[i];
        }
         if  (oTable.sortCol == iCol) {
            aTRs.reverse();
        }  else  {
            aTRs.sort(generateCompareTRs(iCol, sDataType));
        }
         var  oFragment = document.createDocumentFragment();
         for  (  var  j = 0; j < aTRs.length; j++) {
            oFragment.appendChild(aTRs[j]);
        }
        oTBody.appendChild(oFragment);
        oTable.sortCol = iCol;
    }
    
    
    function  convert(sValue, sDataType) {
         switch  (sDataType) {
         case   "int" :
             return  parseInt(sValue);
         case   "float" :
             return  parseFloat(sValue);
         case   "date" :
             return   new  Date(Date.parse(sValue));
         default :
             return  sValue.toString();
        }
    }
    
    function  generateCompareTRs(iCol, sDataType) {
         return   function  compareTRs(oTR1, oTR2) {
            vValue1 = convert(oTR1.cells[iCol].firstChild.nodeValue, sDataType);
            vValue2 = convert(oTR2.cells[iCol].firstChild.nodeValue, sDataType);
             if  (vValue1 < vValue2) {
                 return  -1;
            }  else   if  (vValue1 > vValue2) {
                 return  1;
            }  else  {
                 return  0;
            }
        };
    }
		</script>
		<script>
			$(document)
					.ready(
							function() {
								var num = /^\d+$/;
								var str = /^[a-zA-Z]+$/;
								$('input[type=submit]')
										.click(
												function() {
													var check = $('input[type=radio]:checked');
													if (check.val() === 'user') {
														if (num
																.test($(
																		'input[name=searchwhat]')
																		.val()) !== true) {
															$('.em')
																	.html(
																			'格式不正确，请输入数字');
																			return false;
														} else {
															$('.em').html('');
														}
													} else if (check.val() === 'book') {
														if (str
																.test($(
																		'input[name=searchwhat]')
																		.val()) !== true) {
															$('.em')
																	.html(
																			'格式不正确，请输入字母');
																			return false;
														} else {
															$('.em').html('');
														}
													}
												});
							});
		</script>
</body>
</html>
