<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,bean.*"%>
<!DOCTYPE html>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add</title>
<link rel="shortcut icon" type="image/x-icon" href="assets/images/smalllogo.png" />
<style>
.st1{
width:500px;
margin:auto;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
<c:if test="${sessionScope.user == null}"><% request.getRequestDispatcher("/Login.jsp").forward(request, response); %></c:if>
	<form action="ColumnServlet" method="get" enctype="multipart/form-data">
		<fieldset>
		
			<legend class="st1">新增專欄</legend>
			<div class="st1">
				<label for="" class="t1">發佈時間:</label> <input type="date"
					name="publish_time">
			</div>
			<div class="st1">

				<label for="account1" class="t1">使用者編號:</label> <input type="text"
					id="account1" autocomplete="off" name="user_id">
			</div>

			<div class="st1">
				<label for="pwd1" class="t1">作者:</label><input type="text" id="pwd1"
					name="author">
			</div>

			<div class="st1">
				<label for="" class="t1">權限:</label> <select type="text" name="role">
					<option value="管理者">管理者
					<option>
				</select>

			</div>
			<div class="st1">
				<label class="t1">內容:</label>
				<textarea cols="10" rows="10" type="text" name="contents">
			</textarea>
			</div>
		</fieldset>
		<center>
			<div class="sub">
				<input type="submit" name="add" value="送出"> <input
					type="submit" name="backToQuery" value="返回">
			</div>
		</center>


	</form>
</body>
</html>