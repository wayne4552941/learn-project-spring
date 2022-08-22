package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MemberBean;
import service.MemberService;

@WebServlet("/Loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HashMap<String, String> errorMsgMap = new HashMap<String, String>();

		request.setAttribute("errorMsgMap", errorMsgMap);

		HttpSession httpSession = request.getSession();// 這是瀏覽器上的session跟hibernate不一樣
		httpSession.setMaxInactiveInterval(60*60*60);
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		MemberService service = new MemberService();

		MemberBean result = service.checkLogin(account, password);
		Integer status = null ;
		if (result != null) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*60*60);
			session.setAttribute("user", result);
			if (result.getStatus() == 3) {
				request.getRequestDispatcher("/Index.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/Index.jsp").forward(request, response);
			}

		} else {
			request.getRequestDispatcher("Login.jsp").include(request, response);
			out.println("<font color=red size=8 >帳號或密碼有誤!!</font>");
		}
//		if(result!=null) {
//			status = result.getStatus();
//			
//		}
//		if (result != null && status==3) {
//
//		} else if (result != null && (status==1||status==2)) {
//			
//		}else{
//			errorMsgMap.put("LoginError", "<font color=red size=4 >帳號或密碼有誤!!</font>");
//		}
//
//		if (errorMsgMap.isEmpty() && status==3 ) {
//			request.getRequestDispatcher("Index.jsp").forward(request, response);
//		} else if (errorMsgMap.isEmpty() && (status==1||status==2)) {
//			request.getRequestDispatcher("UserIndex.jsp").forward(request, response);
//		} else {
//			System.out.println("測試");
//			request.getRequestDispatcher("Login.jsp").forward(request, response);
//		}

	}

}
