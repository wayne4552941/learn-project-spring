<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="cartdao.impt.CourseDao"%>
<%@ page import="java.util.List"%>
<%@ page import="bean.CourseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>課程功能列表</title>
<style>
.tb{
   	border-collapse: collapse;
   	width: 1500px; 	
   	/*自動斷行*/
   	word-wrap: break-word;
   	table-layout: fixed;
   }
.bt{
   background-color:red;
}
</style>
<link rel="shortcut icon" type="image/x-icon" href="assets/images/smalllogo.png" />
</head>
<body >
<jsp:include page="header.jsp"/>
<c:if test="${sessionScope.user == null}"><% request.getRequestDispatcher("/Login.jsp").forward(request, response); %></c:if>
<div align="right">	
<form action="QueryServlet" method="post">
           <label>
               課程名稱 : <input type="text" name="keyword">
           </label>
            <input type="submit" name="query" value="查詢" >
        </form>
        <form action="QueryIdServlet" method="get">
            <label>
                課程編號 : <input type="text" name="keyword">
            </label>
            <input type="submit" name="query" value="查詢">
        </form>
  
       
</div>

<table class=tb align='center' border='1'>
		<tr>
		    <td align="center">課程編號</td>
		    <td align="center">課程圖片</td>
			<td align="center">課程名稱</td>
			<td align="center">課程時長</td>
			<td align="center">課程價格</td>
			<td align="center">已購買人數</td>
			<td align="center">上架日期</td>
			<td align="center">講師姓名</td>
			<td>課程資訊</td>
			<td>修改功能</td>
			<td>刪除功能</td>
		</tr>
		<%
		List<CourseBean> list = (List<CourseBean>) request.getAttribute("list");
		for (CourseBean courseBean : list) {
		%>
		
		<tr>
		    <td><h4><center><%=courseBean.getCourse_id() %></center></h4></td>
		    <td><img src="<%=courseBean.getCourse_picture() %>"alt="" title="" width="250" height="200"></td>
			<td><%=courseBean.getCourse_name() %></td>
			<td><%=courseBean.getCourse_duration() %></td>
			<td><center><%=courseBean.getCourse_price()%></center></td>
			<td><center><%=courseBean.getEnrollment() %></td>
			<td><%=courseBean.getCourse_date() %></center></td>
			<td><center><%=courseBean.getLecturer_name() %></center></td>
			<td>
			<%--request.setAttribute("bean", courseBean); --%>
            <a href="DetailServlet?course_id=<%=courseBean.getCourse_id() %>"><input type="submit" name="details" value="查看詳情"></a>
            </td>
			<td>
			<%--request.setAttribute("bean", courseBean); --%>
            <a href="UpdateServlet?course_id=<%=courseBean.getCourse_id() %>"><input type="submit" name="update" value="修改課程"></a>
            </td>
            <td>
            <a  onclick="if( !(confirm('確認刪除?') ) ) return false" href="DeletServlet?course_id=<%=courseBean.getCourse_id() %>"><button  class=bt  type="submit" name="delete" value="刪除課程">刪除課程</button></a>
            </td>
		</tr>

		<%
		}
		%>
		
<script>

</script>		
		
	</table>
	<div align="center">	
	<a href="CourseForm.jsp"><input type="submit" name="addcourse"  value="新增課程"></a>
	</div>
	

</body>
</html>