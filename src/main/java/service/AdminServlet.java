package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MemberBean;
import dao.AdminDao;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		this.adminDao = new AdminDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		try {
			System.out.println(action);
			switch (action) {
			
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insertUser(request, response);
				break;
			case "delete":
				deleteUser(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateUser(request, response);
				break;
			case "list":
				listUser(request, response);
				break;
			}
		} catch (Exception e) {

		}
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String account = request.getParameter("account");
		MemberBean existingUser = adminDao.selectUserByAccount(account);
		request.setAttribute("mb", existingUser);
		System.out.println(existingUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AddNewUser.jsp");
		dispatcher.forward(request, response);
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<MemberBean> listUser = adminDao.selectAllMembers();
		
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("MemberList.jsp");
		dispatcher.forward(request, response);

	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String nick = request.getParameter("nick");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		int status = Integer.parseInt(request.getParameter("status"));
		String name = request.getParameter("name");
		String img = request.getParameter("img");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String cellphone = request.getParameter("cellphone");
		String email = request.getParameter("email");
		String joinDate = request.getParameter("joinDate");

		MemberBean memberBean = new MemberBean(user_id, nick, account, password, status, name, img, sex, birthday,
				cellphone, email, joinDate);
		adminDao.updateUser(memberBean);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminServlet?action=list");
		dispatcher.forward(request, response);
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String account = request.getParameter("account");

		adminDao.deleteUser(account);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminServlet?action=list");
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String nick = request.getParameter("nick");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		int status = Integer.parseInt(request.getParameter("status"));
		String name = request.getParameter("name");
		String img = request.getParameter("img");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String cellphone = request.getParameter("cellphone");
		String email = request.getParameter("email");
		String joinDate = request.getParameter("joinDate");
		MemberBean newUser = new MemberBean(null, nick, account, password, status, name, img, sex, birthday,
				cellphone, email, joinDate);
		adminDao.insertUser(newUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminServlet?action=list");
		dispatcher.forward(request, response);

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("MemberList.jsp");
		dispatcher.forward(request, response);

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
