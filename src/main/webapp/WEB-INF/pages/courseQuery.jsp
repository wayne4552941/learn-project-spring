<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="cartdao.impt.CourseDao"%>
<%@ page import="java.util.List"%>
<%@ page import="bean.CourseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢結果</title>
<style>
.tb{
   	border-collapse: collapse;
   	width: 1500px; 	
   	/*自動斷行*/
   	word-wrap: break-word;
   	table-layout: fixed;
   }

</style>
<link rel="shortcut icon" type="image/x-icon" href="assets/images/smalllogo.png" />
</head>
<body>
<jsp:include page="header.jsp"/>
<center><h2>查詢結果</h2></center>
<table class='tb' align='center' border='1' cellspacing='0'>
<br>
		<tr>
		    <td>課程編號</td>
		    <td>課程圖片</td>
			<td>課程名稱</td>
			<td>課程簡述</td>
			<td>課程時長</td>
			<td>課程價格</td>
			<td>已購買人數</td>
			<td>上架日期</td>
			<td>講師姓名</td>
			
		</tr>
		<%
		List<CourseBean> list = (List<CourseBean>) request.getAttribute("queryResult");
		for (CourseBean courseBean : list) {
		%>
		
		
		<tr>
		    <td><h4><center><%=courseBean.getCourse_id() %></center></h4></td>
		    <td><img src="<%=courseBean.getCourse_picture() %>"alt="" title="" width="250" height="200"></td>
			<td><%=courseBean.getCourse_name() %></td>
			<td><%=courseBean.getCourse_introduction() %></td>
			<td><%=courseBean.getCourse_duration() %></td>
			<td><center><%=courseBean.getCourse_price() %></center></td>
			<td><center><%=courseBean.getEnrollment() %></td>
			<td><%=courseBean.getCourse_date() %></center></td>
			<td><center><%=courseBean.getLecturer_name() %></center></td>
			
		</tr>
		

		<%
		}
		%>
		
	</table>
	<br><br>
	<center><a href="CourseServlet1"><input type="submit" name="return" value="返回課程列表"></a></center>
	
</body>
</html>