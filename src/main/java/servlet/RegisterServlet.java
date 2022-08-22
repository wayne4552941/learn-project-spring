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

import bean.MemberBean;
import service.MemberService;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public RegisterServlet() {
		super();
	}

	MemberService service = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HashMap<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("errorMsgMap", errorMsgMap);
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		MemberBean newRegister = new MemberBean();
		newRegister.setAccount(account);
		newRegister.setPassword(password);
		newRegister.setEmail(email);
		
		MemberBean result = service.checkLogin(account, password);
		Integer status=null;
		System.out.println(result);
		if (result!=null) {
			status = result.getStatus();
			errorMsgMap.put("RegisterError", "<font color=red size=4 >已經註冊!!</font>");
			request.getRequestDispatcher("Register.jsp").forward(request, response);
		}else {
			
		
		try {
			service.registerUser(newRegister);
			request.getRequestDispatcher("Login.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
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
