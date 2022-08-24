<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="bean.MemberBean"%>

<%@ page import="dao.AdminDao"%>


<%@ page import="java.util.List"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>會員後台</title>

<link rel="shortcut icon" type="image/x-icon"
	href="assets/images/smalllogo.png" />

</head>

<body>

	<jsp:include page="header.jsp" />

	<c:if test="${sessionScope.user == null}">
		<%
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
		%>
	</c:if>

	<br>

	<div>

		<a href="AddNewUser.jsp">新增會員</a>

	</div>



	<div>

		<form action="AdminServlet?action=queryAccount" method="post">

			<label> 帳號查詢 : <input type="text" name="keyword">

			</label> <input type="submit" name="query" value="查詢">

		</form>

	</div>

	<hr>



	<div>

		<h3>會員查詢</h3>

	</div>

	<table align='center' border='1' cellspacing='0'>

		<thead>

			<tr>

				<th>ID</th>

				<th>暱稱</th>

				<th>帳號</th>

				<!-- <th>密碼</th> -->

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

		<%
		List<MemberBean> list = (List<MemberBean>) request.getAttribute("queryResult");
		for (MemberBean memberBean : list) {
		%>




		<tbody>



			<tr>

				<td><%=memberBean.getUser_id()%></td>

				<td><%=memberBean.getNick()%></td>

				<td><%=memberBean.getAccount()%></td>
				<!-- <td><c:out value="${mb.password}" /></td> -->

				<td><c:choose>

						<c:when test="<%=memberBean.getStatus() == 1%>">

             		 						學生

       							</c:when>

						<c:when test="<%=memberBean.getStatus() == 2%>">

              								老師

       							</c:when>

						<c:otherwise>

              						管理員

       							</c:otherwise>

					</c:choose></td>

				<td><%=memberBean.getName()%></td>

				<td><img src="<%=memberBean.getImg()%>" width="150"
					height="100"></td>

				<td><%=memberBean.getSex()%></td>

				<td><%=memberBean.getBirthday()%></td>

				<td><%=memberBean.getCellphone()%></td>

				<td><%=memberBean.getEmail()%></td>

				<td><%=memberBean.getJoinDate()%></td>

				<td><a
					href="AdminServlet?action=edit&account=<%=memberBean.getAccount()%> ">修改</a>

					<a onclick="if( !(confirm('確認刪除?') ) ) return false"
					href="AdminServlet?action=delete&account=<%=memberBean.getAccount()%> ">刪除</a>

				</td>

			</tr>
			<%
			}
			%>
			
			</table>
		</tbody>
		
		<center><a href="AdminServlet?action=list"><input type="submit" name="return" value="返回會員列表"></a></center>
</body>

</html>