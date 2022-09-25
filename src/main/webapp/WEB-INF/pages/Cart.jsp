<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,bean.*"%>

<!DOCTYPE html>
<link type="text/css" rel="stylesheet" href="css/style.css">
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="shortcut icon" type="image/x-icon" href="assets/images/smalllogo.png" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">


</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="container">
		<div lass="row justify-content-center">
			<form class="cart-form" method="post">
				<table class="table table-striped" style="text-align:center;">
					<th class="table-success">課程照片</th>
					<th class="table-success">課程名稱</th>
					<th class="table-success">課程分類</th>
					<th class="table-success">課程時長</th>
					<th class="table-success">講師</th>
					<th class="table-success">購買人數</th>
					<th class="table-success" >價格</th>
					<th class="table-success" ><div style="vertical-align:middle;">刪除</div></th>
					<c:forEach var="cart" items="${cartList}">
						<c:url var="tempLink" value="CartServlet">
							<c:param name="command" value="DELETE" />
							<c:param name="cartID" value="${cart.id}" />
						</c:url>
						<tr>
							<c:forEach var="cou" items="${course}">
								<c:if test="${cart.courseBean.course_id == cou.course_id}">
									<td><img src="${cou.course_picture }" alt="" title=""
										width="150" height="100"></td>
								</c:if>
							</c:forEach>
							<td style="vertical-align:middle;">${cart.itemName}</td>
							<c:forEach var="cou" items="${course}">
								<c:if test="${cart.courseBean.course_id == cou.course_id}">
									<td style="vertical-align:middle;"><c:choose>
											<c:when test="${cou.subject_id == 1}">數學</c:when>
											<c:when test="${cou.subject_id == 2}">英文</c:when>
											<c:when test="${cou.subject_id == 3}">多益</c:when>
										</c:choose></td>
									<td style="vertical-align:middle;">${cou.course_duration}</td>
									<td style="vertical-align:middle;">${cou.lecturer_name }</td>
									<td style="vertical-align:middle;">${cou.enrollment }</td>
								</c:if>
							</c:forEach>
							<td style="vertical-align:middle;">$${cart.courseBean.course_price}</td>

							<td class="product-remove" style="vertical-align:middle;"><a href="${tempLink}"
								class="remove"></a></td>

						</tr>
					</c:forEach>
					<c:if test="${empty cartList}">
						<tr>
							<td colspan="8">目前購物車沒有資料!!!</td>

						</tr>
						<tr>

							<td colspan="8"><a href="CourseServlet" style="color: red"><input
									type="hidden" name="return" value="前往探索更多課程~">前往探索更多課程~</a></td>
						</tr>
					</c:if>
					<tr>
						<td colspan="7"></td>
						<th>總金額: $${countTotal.get(0)}</th>
					</tr>
				</table>
			</form>
			<table class="table table-striped" style="text-align:center;">
				<tr>
					<td colspan="8"><input type="hidden" name="command"
						value="ADD"> <c:if test="${empty cartList}">
							<button class="btn btn-success" id="myBtn" disabled
								onclick="if( !(confirm('確認購買?') ) ) return false ; alert('生成訂單!!!');">確認購買</button>
						</c:if> <c:if test="${not empty cartList}">
								<form action="OrderServlet" method="post">
									<input type="hidden" name="command" value="ADD">
									<button class="btn btn-success" 
									onclick="if( !(confirm('確認購買?') ) ) return false ; alert('生成訂單!!!');">確認購買</button>
								</form>
<!-- 							<a href="OrderServlet?command=ADD"> -->
<!-- 								<button class="btn btn-success" -->
<!-- 									onclick="if( !(confirm('確認購買?') ) ) return false ; alert('生成訂單!!!');">確認購買</button> -->
<!-- 							</a> -->
						</c:if></td>


				</tr>
				<tr>
					<td colspan="10">
						<form action="CartServlet" method="post">
							<input type="hidden" name="command" value="CLEAR">
							<button class="btn btn-danger"
								onclick="if( !(confirm('確認清除?') ) ) return false">清空購物車</button>
						</form>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>
</body>
</html>