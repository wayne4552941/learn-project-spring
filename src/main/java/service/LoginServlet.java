package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MemberBean;
import dao.logindao;
import memberbean.User;

/**
 * Servlet implementation class loginservlet1
 */
@WebServlet("/Loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try {

			String account = request.getParameter("account");
			String password = request.getParameter("password");
			System.out.println(account);
			System.out.println(password);
			PrintWriter out = response.getWriter();
			logindao logindao = new logindao();
			MemberBean user = logindao.checkAccount(account, password);
			if (user != null) {
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(60*60*60);
				session.setAttribute("user", user);
				request.getRequestDispatcher("/Index.jsp").forward(request, response);

			} else {

				request.getRequestDispatcher("Login.jsp").include(request, response);
				out.println("<font color=red size=8 >帳號或密碼有誤!!</font>");
			}

		} catch (Exception e) {

		}
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
