<%@page
	import="org.eclipse.jdt.internal.compiler.env.IUpdatableModule.UpdateKind"%>
<%@page import="dao.ColumnDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,bean.*,dao.ColumnDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
response.setContentType("text/html;charset=UTF-8");
response.setCharacterEncoding("UTF-8");
request.setCharacterEncoding("UTF-8");

response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>專欄</title>
<link rel="shortcut icon" type="image/x-icon" href="assets/images/smalllogo.png" />
<style>
.listname{text-align:center}

</style>
</head>

<body>
<jsp:include page="header.jsp"/>
<c:if test="${sessionScope.user == null}"><% request.getRequestDispatcher("/Login.jsp").forward(request, response); %></c:if>	
		<center>
			<h1>專欄列表</h1>
			<h2>
			</h2>
		</center>
	<form action="ColumnAdd.jsp" method="get" >

		<button name="addbtn" value="addbtn">新增專欄</button>
	</form>
		<form class="search1" action="ColumnServlet" method="post">
			<input type="text" name="search">
			 <input type="submit" name="searchno" value="查詢">
		</form>


		
			<table border="1"  >
				

				<tr>
					<th>文章編號</th>
					<th>發表日期</th>
					<th>使用者編號</th>
					<th>作者名稱</th>
					<th>權限</th>
					<th>內容</th>
					<th>功能</th>
				</tr>

				<c:choose>
					<c:when test="${column != null }">
						<c:set var="col" value="${column }" />
						<td>${col.no }</td>
						<td>${col.date }</td>
						<td>${col.user_id}</td>
						<td>${col.author }</td>
						<td>${col.role }</td>
						<td>${col.contents }</td>
					</c:when>

					<c:otherwise>
						<%
						List<Column> column = new ColumnDAO().selectAllColumns();
						for (Column c : column) {
						%>
						<tr>

							<td><%=c.getNo()%></td>
							<td><%=c.getDate()%></td>
							<td><%=c.getUser_id()%></td>
							<td><%=c.getAuthor()%></td>
							<td><%=c.getRole()%></td>
							<td style="width:100px;overflow:hidden;white-space:nowrap;word-break:keep-all;"><div style="width:100px;overflow:hidden;white-space:nowrap;"><%=c.getContents()%></div></td>
							
							<td><a href="ColumnUpdate.jsp?article_no=<%=c.getNo()%>&publish_time=<%=c.getDate()%>&user_id=<%=c.getUser_id()%>&author=<%=c.getAuthor()%>&role=<%=c.getRole()%>&contents=<%=c.getContents()%>"><button  name="edit" value="edit">編輯</button></a></td>
						
	                  		
							<form action="ColumnServlet" method="get">
								<td><input type="submit" name="more" value="更多">
								<input type="hidden" name="article_no" value="<%=c.getNo()%>"></td>
							</form>
							
							<form action="ColumnServlet" method="get"
								enctype="multipart/form-data">
								<td><input onclick="if( !(confirm('確認刪除?') ) ) return false" type="submit" name="delete" value="刪除"></td>
									<input type="hidden" name="article_no" value="<%=c.getNo()%>">
							</form>
							
						</tr>
						<%
						}
						%>
					</c:otherwise>
				</c:choose>
			</table>
		
	
</body>
</html>