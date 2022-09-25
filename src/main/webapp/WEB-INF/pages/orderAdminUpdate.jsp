<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,bean.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
				<div class="row justify-content-center">
					<div class="col-8">
						<form action="OrderServlet" method="post">
							<c:set value="${user}" var="order" />
							<input type="hidden" name="command" value="UPDATE"> <input
								type="hidden" name="orderID" value="${order.order_id}">
							<table class="table table-striped">
								<tr>
									<td colspan="8">
										<table class="table mb-0">

											<th>訂單編號</th>
											<th>訂單生成日期</th>
											<th>訂單狀態</th>
											<c:url var="de" value="OrderServlet">
												<c:param name="command" value="DELETE" />
												<c:param name="cartID" value="${order.order_id}" />
											</c:url>

											<tr>
												<td>${order.order_id }</td>
												<td>${order.date }</td>

												<td><select name="status">
														<option value="${order.status}"><c:choose>
																<c:when test="${order.status == 0}">未付款</c:when>
																<c:when test="${order.status == 1}">已付款</c:when>
																<c:when test="${order.status == 2}">完成訂單</c:when>
															</c:choose></option>
														<option value="0">未付款</option>
														<option value="1">已付款</option>
														<option value="2">完成訂單</option>
												</select></td>

											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="8">
										<table class="table mb-0">

											<th>會員帳號</th>
											<th>會員姓名</th>
											<th>會員信箱</th>
											<th>會員手機</th>
											<tr>
												<td>${order.memberBean.account}</td>
												<td>${order.memberBean.name}</td>
												<td>${order.memberBean.email}</td>
												<td>${order.memberBean.cellphone}</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="8">
										<table class="table mb-0">

											<th>商品編號</th>
											<th>商品名稱</th>
											<th>商品數量</th>
											<th>商品價錢</th>

											<tr>
												<c:forEach items="${itemList}" var="item">
													<tr>
														<td>${item.item_id}</td>
														<td>${item.name}</td>
														<td>${item.count}</td>
														<td>$${item.price}</td>
													</tr>
												</c:forEach>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<table class="table">
								<tr>
									<td colspan="7"></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td colspan="7"></td>
									<td></td>
									<td></td>
									<th>總價</th>
									<td>$${order.totoalprice }</td>
								</tr>
								<tr>
									<td colspan="7"></td>
									<td></td>
									<td></td>
									<th>折扣</th>
									<td>${order.discount }</td>
								</tr>
								<tr>
									<td colspan="7"></td>
									<td></td>
									<td></td>
									<th>合計</th>
									<td>$${order.totoalprice }</td>
								</tr>
							</table>
							<div style="margin: auto; width: 88px;">

								<button
									onclick="if( !(confirm('確認修改?') ) ) return false ; alert('修改成功!!!');">
									<center>確認修改</center>
								</button>
							</div>

						</form>
					</div>
				</div>
			</div>
</body>
</html>