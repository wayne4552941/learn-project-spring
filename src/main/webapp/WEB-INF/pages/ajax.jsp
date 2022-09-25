<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-3.6.0.js"></script>
<script>
	$(function() {

		// ajax--get请求
		$("#btn").click(
				function() {

					$.getJSON(
							"http://localhost:8080/learn-project/CartServlet",
							"command=COUNT", function(data) {
								$("#msg").html("數量  " + data[1]);
							});

				});
	})
</script>
</head>
<body>
	<button id="btn">$.get请求</button>
	<div id="msg">55</div>
	
	<%="hello"+"<br>"+"neeee" %>
	<form action="test.jsp">
	<input type="hidden" name="aa" value="abcxyz"/>
	<input type="submit" value="送出"/>
	</form>
</body>
</html>