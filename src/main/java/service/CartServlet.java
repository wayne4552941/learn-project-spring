package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import bean.CartItem;
import bean.CourseBean;
import bean.MemberBean;
import dao.*;
import memberbean.User;
import cartdao.impt.CartDaoImpt;
import cartdao.impt.CourseDao;
import util.WebUtils;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	CartService cartService = new CartService();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		MemberBean user = (MemberBean) request.getSession().getAttribute("user");
		if (user != null) {
			System.out.println("會員在購物車");
		} else {
			String servletPath = request.getServletPath();
			System.out.println(servletPath);
			request.setAttribute("path", servletPath);
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
			return;
		}
		String command = request.getParameter("command");
		if (command == null) {
			cartList(request, response);
			return;
		}
		System.out.println(command);
		try {

			switch (command) {
			case "CARTLIST":
				cartList(request, response);
				break;
			case "ADD":
				cartAdd(request, response);
				break;
			case "DELETE":
				cartDelete(request, response);
				break;
			case "CLEAR":
				cartClear(request, response);
				break;
			case "COUNT":
				getCount(request, response);
				break;

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private void getCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberBean user = (MemberBean) request.getSession().getAttribute("user");
		String jsonString = cartService.getCount(user.getUser_id());
		response.getWriter().write(jsonString);
	}

	private void cartClear(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		MemberBean user = (MemberBean) request.getSession().getAttribute("user");
		cartService.cartClear(user.getUser_id());
		String servletPath = request.getServletPath();
		request.setAttribute("path", servletPath);
		cartList(request, response);
	}

	private void cartAdd(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		String CartID = request.getParameter("courseID");
		MemberBean user = (MemberBean) request.getSession().getAttribute("user");
		cartService.cartAdd(CartID, user.getUser_id());		
		response.sendRedirect(request.getHeader("Referer"));

	}

	private void cartDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("cartID");
		cartService.cartDelete(id);
		response.sendRedirect(request.getHeader("Referer"));
	}

	private void cartList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberBean user = (MemberBean) request.getSession().getAttribute("user");
		List<CartItem> cartList = cartService.cartList(user.getUser_id());
		request.setAttribute("cartList", cartList);
		List countPriceTotal = cartService.getCountPriceTotal(cartList);
		request.setAttribute("countTotal", countPriceTotal);
		List<CourseBean> courseList = cartService.courseList();
		request.setAttribute("course", courseList);
		request.getRequestDispatcher("/Cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
