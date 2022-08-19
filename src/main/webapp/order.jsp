<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,bean.*"%>
<!DOCTYPE html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">

<style>
#btn {
	background-color: red;
}
</style>
<script type="text/javascript" src="jquery-3.6.0.js"></script>
<script>
	
</script>
<link rel="shortcut icon" type="image/x-icon" href="assets/images/smalllogo.png" />
</head>
<html>
<body>
	<c:if test="${sessionScope.user == null}"><% request.getRequestDispatcher("/Login.jsp").forward(request, response); %></c:if>
	<jsp:include page="header.jsp" />
	<c:choose>
		<c:when test="${sessionScope.user.status == 3}">
			<div style="width: 1500px;" class="container">
				<div class="row justify-content-center">
					<div class="col-auto">
						<form action="OrderServlet" method="post">
							<input type="hidden" name="command" value="SEARCH"> 搜尋訂單:
							<input type="search" name="search"></input>
							<button onclick="search()">查詢</button>
						</form>
						<table class="table table-striped table-hover">
							<th>會員帳號</th>
							<th>會員姓名</th>
							<th>會員信箱</th>
							<th>訂單編號</th>
							<th>會員編號</th>
							<th>訂單生成日期</th>
							<th>總數</th>
							<th>訂單狀態</th>
							<th>折扣</th>
							<th>總價</th>
							<th >詳細</th>
							<th>刪除</th>
							<c:forEach items="${order}" var="order">
								<c:url var="up" value="OrderServlet">
									<c:param name="command" value="ITEMLIST" />
									<c:param name="cartID" value="${order.order_id}" />
									<c:param name="userID" value="${order.memberBean.user_id}" />
								</c:url>
								<c:url var="upStatus" value="OrderServlet">
									<c:param name="command" value="EPAY" />
									<c:param name="orderID" value="${order.order_id}" />
									<c:param name="status" value="${1}" />
									<c:param name="cartID" value="${order.order_id}" />
									<c:param name="userID" value="${order.memberBean.user_id}" />
								</c:url>
								<c:url var="de" value="OrderServlet">
									<c:param name="command" value="DELETE" />
									<c:param name="cartID" value="${order.order_id}" />
								</c:url>
								<tr>
									<td>${order.memberBean.account}</td>
									<td>${order.memberBean.name}</td>
									<td>${order.memberBean.email}</td>
									<td>${order.order_id }</td>
									<td>${order.memberBean.user_id }</td>
									<td>${order.date }</td>
									<td>${order.totoalcount }</td>
									<td><c:choose>
											<c:when test="${order.status == 0}">未付款</c:when>
											<c:when test="${order.status == 1}">已付款</c:when>
											<c:when test="${order.status == 2}">完成訂單</c:when>
										</c:choose></td>
									<td>${order.discount }</td>
									<td>$${order.totoalprice }</td>

									<td>
										<form action="OrderServlet" method="post">
											<input type="hidden" name="command" value="ITEMLIST" /> <input
												type="hidden" name="cartID" value="${order.order_id}" />
											<button style="background-color: blue;">詳細</button>
										</form> <%-- 									<a href="${up}"><button>修改</button></a> --%>
									</td>
									<td>
									
										<form action="OrderServlet" method="post" onclick="if( !(confirm('確認刪除?') ) ) return false">
											<input type="hidden" name="command" value="DELETE" />
											<input type="hidden" name="cartID" value="${order.order_id}" />
											<button>刪除</button>
										</form>
<!-- 									<a onclick="if( !(confirm('確認刪除?') ) ) return false" -->
<%-- 										href="${de}"><button>刪除</button></a> --%>
										</td>
								</tr>
							</c:forEach>
							
						</table>
							<c:if test="${nan == null }">
								<div style="border: 1px solid #ddd">
   									 <div style="width: 100px; margin: auto;">查無此訂單!!!</div>
								</div>
								
							</c:if>
					</div>
				</div>
			</div>
		</c:when>

		<c:otherwise>
			<div style="width: 1500px;" class="container">
				<div class="row justify-content-center">
					<div class="col-auto">

						<table class="table table-striped table-hover">

							<th>訂單編號</th>
							<th>訂單生成日期</th>
							<th>購買數量</th>
							<th>訂單狀態</th>
							<th>折扣</th>
							<th>總價</th>
							<th>詳細</th>
							<th>刪除</th>
							<c:forEach items="${order}" var="order">
								<c:url var="up" value="OrderServlet">
									<c:param name="command" value="ITEMLIST" />
									<c:param name="cartID" value="${order.order_id}" />
								</c:url>
								<c:url var="upStatus" value="OrderServlet">
									<c:param name="command" value="ITEMLIST" />
									<c:param name="orderID" value="${order.order_id}" />
									<c:param name="status" value="${1}" />
									<c:param name="cartID" value="${order.order_id}" />
									<c:param name="userID" value="${order.memberBean.user_id}" />
								</c:url>
								<c:url var="de" value="OrderServlet">
									<c:param name="command" value="DELETE" />
									<c:param name="cartID" value="${order.order_id}" />
								</c:url>
								<tr>

									<td>${order.order_id }</td>
									<td>${order.date }</td>
									<td>${order.totoalcount }</td>
									<td><c:choose>
											<c:when test="${order.status == 0}">未付款</c:when>
											<c:when test="${order.status == 1}">已付款</c:when>
											<c:when test="${order.status == 2}">完成訂單</c:when>
										</c:choose></td>
									<td>${order.discount }</td>
									<td>$${order.totoalprice }</td>

									<c:if test="${order.status == 0}">
										<td>
											<form action="OrderServlet" method="post">
												<input type="hidden" name="command" value="ITEMLIST" />
												<input type="hidden" name="orderID" value="${order.order_id}" />
												<input type="hidden" name="status" value="${1}" />
												<input type="hidden" name="cartID" value="${order.order_id}" />
												<input type="hidden" name="userID" value="${order.memberBean.user_id}" />
												<button>結帳</button>
											</form> 
<%-- 											<a href="${upStatus}"><button>結帳</button></a> --%>
										</td>
									</c:if>
									<c:if test="${order.status == 1}">
										<td><a href="${upStatus}"><button id="btn" disabled>待審核</button></a></td>
									</c:if>
									<c:if test="${order.status == 2}">
										<td>
											<form action="OrderServlet" method="post">
												<input type="hidden" name="command" value="ITEMLIST" />
												<input type="hidden" name="cartID" value="${order.order_id}" />
												<button style="background-color: blue;">詳細</button>
											</form> 
<%-- 											<a href="${up}"><button style="background-color: blue;">詳細</button></a> --%>
										</td>
									</c:if>

									<c:if test="${order.status == 0}">
										<td>
											<form action="OrderServlet" method="post"
												onclick="if( !(confirm('確認刪除?') ) ) return false">
												<input type="hidden" name="command" value="DELETE" /> <input
													type="hidden" name="cartID" value="${order.order_id}" />
												<button>刪除</button>
											</form> <!-- 										<a onclick="if( !(confirm('確認刪除?') ) ) return false" -->
<%-- 											href="${de}"><button>刪除</button></a> --%>
											</td>
									</c:if>
									<c:if test="${order.status != 0}">
										<td></td>
									</c:if>

								</tr>
							</c:forEach>

						</table>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>
</body>
</html>