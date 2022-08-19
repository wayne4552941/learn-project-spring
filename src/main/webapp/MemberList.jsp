<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.MemberBean"%>
<%@ page import="dao.AdminDao"%>
<%@ page import="service.AdminServlet"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員後台</title>
<link rel="shortcut icon" type="image/x-icon" href="assets/images/smalllogo.png" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<c:if test="${sessionScope.user == null}"><% request.getRequestDispatcher("/Login.jsp").forward(request, response); %></c:if>
	<br>
	<a href="AddNewUser.jsp">新增會員</a>
	<hr>
	<div>
		<div>
			<h3>會員清單</h3>
		</div>
		<table align='center' border='1' cellspacing='0'>
			<thead>
				<tr>
					<th>ID</th>
					<th>暱稱</th>
					<th>帳號</th>
					<th>密碼</th>
					<th>身分</th>
					<th>姓名</th>
					<th>大頭貼</th>
					<th>性別</th>
					<th>生日</th>
					<th>手機號碼</th>
					<th>信箱</th>
					<th>註冊日期</th>
					<th>Actions</th>
				</tr>
			</thead>
			<c:forEach var="mb" items="${listUser}">



				<tbody>


					<tr>
						<td><c:out value="${mb.user_id}" /></td>
						<td><c:out value="${mb.nick}" /></td>
						<td><c:out value="${mb.account}" /></td>
						<td><c:out value="${mb.password}" /></td>
						<td><c:choose>
								<c:when test="${mb.status==1}">
             		 						學生
       							</c:when>
								<c:when test="${mb.status==2}">
              								老師
       							</c:when>
								<c:otherwise>
              						管理員
       							</c:otherwise>
							</c:choose></td>
						<td><c:out value="${mb.name}" /></td>
						<td><img src="${mb.img}" width="150" height="100""></td>
						<td><c:out value="${mb.sex}" /></td>
						<td><c:out value="${mb.birthday}" /></td>
						<td><c:out value="${mb.cellphone}" /></td>
						<td><c:out value="${mb.email}" /></td>
						<td><c:out value="${mb.joinDate}" /></td>
						<td><a
							href="AdminServlet?action=edit&account=${mb.account} ">修改</a>
							<a onclick="if( !(confirm('確認刪除?') ) ) return false"
							href="AdminServlet?action=delete&account=${mb.account} ">刪除</a>
						</td>
					</tr>

				</tbody>
			</c:forEach>
			</div>
</body>
</html>