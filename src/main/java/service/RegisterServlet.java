//package service;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import dao.RegisterDao;
//import bean.MemberBean;
//
///**
// * Servlet implementation class registerservlet1
// */
//@WebServlet("/RegisterServlet")
//public class RegisterServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	private RegisterDao registerdao=new RegisterDao();
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public RegisterServlet() {
//        super();
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	
//	String account=request.getParameter("account");
//	String password=request.getParameter("password");
//	String email=request.getParameter("email");
//	
//	 MemberBean user = new MemberBean();
//	 user.setAccount(account);
//	 user.setPassword(password);
//	 user.setEmail(email);
//	try {
//		
//		registerdao.registeruser(user);
//		RequestDispatcher dispatcher=request.getRequestDispatcher("Login.jsp");
//		dispatcher.forward(request, response);
//		
//		
//		
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	 
//	}
//	
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//}
