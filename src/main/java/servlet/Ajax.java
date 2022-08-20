package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.CartItem;
import bean.OrderUser;
import cartdao.OrderDAO;
import cartdao.impt.CartDaoImpt;
import cartdao.impt.OrderDaoImpt;

/**
 * Servlet implementation class Ajax
 */
@WebServlet("/Ajax")
public class Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Ajax() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 解决响应中文乱码
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("  jQueryGet  == 方法调用了");
		OrderDaoImpt daoImpt = new OrderDaoImpt();
		CartDaoImpt cartDaoImpt = new CartDaoImpt();
		List<CartItem> carList = cartDaoImpt.carList(1);
		List countTotal = cartDaoImpt.getCountTotal(carList);
		Gson gson = new Gson();
		String JsonString = gson.toJson(countTotal);
		System.out.println(JsonString);
		System.out.println(countTotal.get(1) + "!!!!!!!!");
		response.getWriter().write(JsonString);
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
