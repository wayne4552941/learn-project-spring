<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>課程資料確認</title>
<!--  link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">-->
<link rel="shortcut icon" type="image/x-icon" href="assets/images/smalllogo.png" />
<style>
.tb{
   	border-collapse: collapse;
   	width: 750px; 	
   	/*自動斷行*/
   	word-wrap: break-word;
   	table-layout: fixed;
   }
</style>
</head>
<body>
<jsp:include page="header.jsp"/>

<jsp:useBean id="reg_course" class="bean.CourseBean" scope="session" />
<center>
<h2>請確認課程資料</h2>
</center>
<form action="./CourseServlet" method="post">
<table class=tb align='center'>
<tr bgcolor="#F2F4FB">
    <td>使用者名稱:</td>
    <td><jsp:getProperty name="reg_course" property="user_id" /></td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>課程名稱:</td>
    <td><jsp:getProperty name="reg_course" property="course_name" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>科目名稱代碼:</td>
    <td><jsp:getProperty name="reg_course" property="subject_id" /></td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>開課班別代碼:</td>
    <td><jsp:getProperty name="reg_course" property="education_id" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>課程簡介:</td>
    <td><jsp:getProperty name="reg_course" property="course_introduction" /></td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>課程價格:</td>
    <td><jsp:getProperty name="reg_course" property="course_price" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>課程時數:</td>
    <td><jsp:getProperty name="reg_course" property="course_duration" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>購買人數:</td>
    <td><jsp:getProperty name="reg_course" property="enrollment" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>課程上架日期:</td>
    <td><jsp:getProperty name="reg_course" property="course_date" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>講師姓名:</td>
    <td><jsp:getProperty name="reg_course" property="lecturer_name" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>講師信箱:</td>
    <td><jsp:getProperty name="reg_course" property="lecturer_email" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>課程封面圖片:</td>
    <td><jsp:getProperty name="reg_course" property="course_picture"  /></td>
</tr>
</table>
<br><br>
<center>
<input type="submit" name="confirm" value="確定送出" >
</center>
</form>

</body>
</html>