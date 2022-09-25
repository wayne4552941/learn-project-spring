<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>

<%@ page import="java.util.List"%>
<%@ page import="bean.CourseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>課程詳情</title>
<link rel="shortcut icon" type="image/x-icon" href="assets/images/smalllogo.png" />
</head>

<%  CourseBean courseBean =(CourseBean)request.getAttribute("cbean"); %>

<body class="details-page">
	<jsp:include page="header.jsp" />
	</div>
	<div class="main-content main-content-details single no-sidebar">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb-trail breadcrumbs">
						<ul class="trail-items breadcrumb">
							<li class="trail-item trail-begin"><a href="Index.jsp">首頁</a></li>
							<li class="trail-item"><a href="CourseServlet1">課程列表</a></li>
							<li class="trail-item trail-end active">課程詳情</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div
					class="content-area content-details full-width col-lg-9 col-md-8 col-sm-12 col-xs-12">
					<div class="site-main">
						<div class="details-product">
							<div class="details-thumd">
								<div
									class="image-preview-container image-thick-box image_preview_container">
									<img id="img_zoom"
										data-zoom-image="<%=courseBean.getCourse_picture() %>"
										src="<%=courseBean.getCourse_picture() %>" alt="img"><a
										href="#" class="btn-zoom open_qv"><i class="fa fa-search"
										aria-hidden="true"></i></a>
								</div>

							</div>
							<div class="details-infor">
								<h1><%=courseBean.getCourse_name() %></h1>
								<div class="stars-rating">
									<div class="star-rating">
										<span class="star-5"></span>
									</div>
									<div class="count-star">(7)</div>
								</div>

								<div class="product-details-description">
									<ul>
										<li>課程時長:<%=courseBean.getCourse_duration() %></li>
										<li>已購買人數:<%=courseBean.getEnrollment() %></li>
										<li>講師姓名:<%=courseBean.getLecturer_name() %></li>
										<li>講師信箱:<%=courseBean.getLecturer_email() %></li>
										<li>上架日期:<%=courseBean.getCourse_date() %></li>
									</ul>
								</div>

								<div class="group-button">
									<div class="yith-wcwl-add-to-wishlist">
										<div class="yith-wcwl-add-button">
											<a href="#">加入最愛</a>
										</div>
									</div>
									<div class="availability">課程價格:</div>
									<div class="price">
										<span>$ <%=courseBean.getCourse_price() %></span>
									</div>

									<div class="quantity-add-to-cart">
										<div class="quantity">
											<form action="CartServlet">
												<input type="hidden" name="command" value="ADD"> <input
													type="hidden" name="courseID" value="${cbean.course_id}">
												<button class="single_add_to_cart_button button">加入購物車</button>
											</form>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-details-product">
								<ul class="tab-link">
									<li class="active"><a data-toggle="tab"
										aria-expanded="true"><h3>課程簡介</h3></a></li>
								</ul>
								<div class="tab-container">
									<div id="product-descriptions" class="tab-panel active">
										<p>
										<h5>
											★
											<%=courseBean.getCourse_introduction() %></h5>
										</p>

									</div>



								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	</div>

</body>
</html>