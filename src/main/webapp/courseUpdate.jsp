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
<style>
.tb {
	border-collapse: collapse;
	width: 750px;
	/*自動斷行*/
	word-wrap: break-word;
	table-layout: fixed;
}
</style>
<title>課程資料修改</title>
<link rel="shortcut icon" type="image/x-icon"
	href="assets/images/smalllogo.png" />
</head>

<body>
	<jsp:include page="header.jsp" />
	<a href="CourseServlet1"><input type="submit" name="return"
		value="回上頁"></a>
	<center>
		<h1>課程資料修改</h1>
	</center>
	<form action="UpdateServlet2" method="get">
		<table class=tb align='center'>
			<%--
    CourseDao dao = new CourseDao();
    CourseBean courseBean = dao.selectById(Integer.parseInt(request.getParameter("course_id")));
    int course_id = Integer.parseInt(request.getParameter("course_id"));
    String course_name = courseBean.getCourse_name();
     
--%>
			<%
			CourseBean bean = (CourseBean) request.getAttribute("bean");
			%>


			<tr>

				<input type="hidden" name="course_id"
					value="<%=bean.getCourse_id()%>">
			</tr>
			<tr>
				<td>使用者名稱:</td>
				<td><input type="text" name="user_id"
					value="<%=bean.getUser_id()%>"></td>
			</tr>
			<tr>
				<td>課程名稱:</td>
				<td><input type="text" name="course_name" size="30"
					maxlength="30" value="<%=bean.getCourse_name()%>"></td>
			</tr>
			<tr>
				<td>科目名稱代碼:</td>
				<td><input type="text" name="subject_id" size="2" maxlength="5"
					title="科目名稱代碼" value="<%=bean.getSubject_id()%>"></td>
			</tr>
			<tr>
				<td>開課班別代碼:</td>
				<td><input type="text" name="education_id" size="2"
					maxlength="10" title="開課班別代碼" value="<%=bean.getEducation_id()%>"></td>
			</tr>
			<tr>
				<td>課程簡介:</td>
				<td><textarea cols="20" rows="4" name="course_introduction"
						title="課程簡介"><%=bean.getCourse_introduction()%></textarea></td>
			</tr>
			<tr>
				<td>課程價格:</td>
				<td><input type="text" name="course_price" size="5"
					title="課程價格" value="<%=bean.getCourse_price()%>"></td>
			</tr>
			<tr>
				<td>課程時數:</td>
				<td><input type="text" name="course_duration" size="10"
					title="課程時數" value="<%=bean.getCourse_duration()%>"></td>
			</tr>
			<tr>
				<td>已購買人數:</td>
				<td><input type="text" name="enrollment" size="3" title="報名人數"
					value="<%=bean.getEnrollment()%>"></td>
			</tr>
			<tr>
				<td>課程上架日期:</td>
				<td><input type="date" name="course_date" title="開課日期"
					value="<%=bean.getCourse_date()%>"></td>
			</tr>
			<tr>
				<td>講師姓名:</td>
				<td><input type="text" name="lecturer_name" size="5"
					title="講師姓名" value="<%=bean.getLecturer_name()%>"></td>
			</tr>
			<tr>
				<td>講師信箱:</td>
				<td><input type="text" name="lecturer_email" size="15"
					title="講師信箱" value="<%=bean.getLecturer_email()%>"></td>
			</tr>
			<tr>
				<td>課程封面圖片:</td>
				<td><img src="<%=bean.getCourse_picture()%>" id="img" alt=""
					title="" width="200" height="150"> <input id="imgPath"
					type="hidden" name="course_picture" title="課程封面圖片"
					value="<%=bean.getCourse_picture()%>"> <input
					onchange="previewImg(this)" type="file" name="course_picture"
					title="課程封面圖片"></td>
			</tr>

		</table>
		<script>
			if (request.getParameter("submit") != null) {
				alert("修改成功!!!!")
			}
		</script>

		<center>
			<br> <br> <a href=""><input
				onclick="if( !(confirm('確定要修改嗎?') ) ) return false" type="submit"
				name="submit" value="確定"></a>

		</center>


	</form>
	<script>
		function previewImg(element) {

			let file = element.files[0];
			let img = element.parentElement.querySelector("#img");
			let imgPath = element.parentElement.querySelector("#imgPath");
			if (file) {
				img.src = URL.createObjectURL(file);
				imgPath.value = "./images/" + file.name;
			}

		}
	</script>

</body>
</html>