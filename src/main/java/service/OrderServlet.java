package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.platform.engine.support.descriptor.CompositeTestSource;

import bean.CartItem;
import bean.CourseBean;
import bean.MemberBean;
import bean.OrderItem;
import bean.OrderUser;
import cartdao.OrderDAO;
import cartdao.impt.CartDaoImpt;
import cartdao.impt.CourseDao;
import cartdao.impt.OrderDaoImpt;
import cartdao.impt.OrderItemDaoImpt;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import mail.JavaMail;
import memberbean.User;
import util.WebUtils;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	OrderService orderService = new OrderService();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}
	

	private void ecPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderID = request.getParameter("orderID");
		String url =request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath() + request.getServletPath();
		String form = orderService.ecPay(orderID, url);
		request.setAttribute("ecpay", form);
		request.getRequestDispatcher("/ecpay.jsp").forward(request, response);
		
	}
	
	private void searchLearn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		MemberBean user = (MemberBean) request.getSession().getAttribute("user");
		List<CourseBean> searchLearn = orderService.searchLearn(user.getUser_id());
		System.out.println(searchLearn);
		int ran = (int)(Math.random()* 5 + 1);
		request.setAttribute("ran",ran);
		request.setAttribute("myItem",searchLearn);
		request.getRequestDispatcher("/myLearn.jsp").forward(request, response);
	}

	private void orderSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("search");
		List<OrderUser> orderSearch = orderService.orderSearch(search);
		request.setAttribute("nan",orderSearch);
		request.setAttribute("order", orderSearch);
		request.getRequestDispatcher("/order.jsp").forward(request, response);
	}

	private void orderItemList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("cartID");
		List<OrderItem> orderItemList = orderService.orderItemList(id);
		request.setAttribute("itemList", orderItemList);
		String user_id = request.getParameter("userID");
		OrderUser orderItemUser = orderService.orderItemUser(id);
		request.setAttribute("user", orderItemUser);
		request.getRequestDispatcher("/orderUpdate.jsp").forward(request, response);
	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String id = request.getParameter("cartID");
		orderService.deleteOrder(id);
		orderList(request, response);
	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		MemberBean user = (MemberBean) request.getSession().getAttribute("user");
		String status = request.getParameter("status");
		String orderID = request.getParameter("orderID");
		orderService.updateOrder(user.getStatus(), status, orderID);
		orderList(request, response);
	}

	private void addOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		MemberBean user = (MemberBean) request.getSession().getAttribute("user");
		orderService.addOrder(user.getUser_id());
		orderList(request, response);
	}

	private void orderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberBean user = (MemberBean) request.getSession().getAttribute("user");
		List<OrderUser> orderList = orderService.orderList(user.getUser_id(),user.getStatus());
		for(OrderUser orderUser : orderList ) {
			System.out.println(orderUser);
		}
		request.setAttribute("nan", orderList);
		request.setAttribute("order", orderList);
		request.getRequestDispatcher("/order.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		MemberBean user =(MemberBean)request.getSession().getAttribute("user");
		if(user == null) {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		String command = request.getParameter("command");
		if (command == null) {
			orderList(request, response);
			return;
		}
		PrintWriter out = response.getWriter();
		System.out.println(command);
		try {
			switch (command) {
			case "ORDERLIST":
				out.print("清單");
				orderList(request, response);
				break;
			case "ADD":
				out.print("新增");
				addOrder(request, response);
				break;
			case "DELETE":
				out.print("刪除");
				deleteOrder(request, response);
				break;
			case "UPDATE":
				out.print("更新");
				updateOrder(request, response);
				break;
			case "ITEMLIST":
				out.print("品項清單");
				orderItemList(request, response);
				break;
			case "SEARCH":
				out.print("搜尋");
				orderSearch(request, response);
				break;
			case "LEARN":
				searchLearn(request, response);
				break;
			case "ECPAY":
				ecPay(request, response);
				break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
