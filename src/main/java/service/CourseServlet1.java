package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.CourseBean;
import bean.MemberBean;
import cartdao.impt.CourseDao;
import memberbean.User;

/**
 * Servlet implementation class CourseServlet1
 */
@WebServlet("/CourseServlet1")
public class CourseServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	DataSource datasource = null;

	public CourseServlet1() {
		super();
	}

	@Override
//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//		super.init(config);
//		InitialContext itct = null;
//		try {
//
//			itct = new InitialContext();
//			datasource = (DataSource) itct.lookup("java:comp/env/jdbc/EmployeeDB");
//
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberBean user =(MemberBean)request.getSession().getAttribute("user");
		System.out.println(user);
		if(user == null) {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		try {
			 user = (MemberBean) request.getSession().getAttribute("user");
			if (user != null) {
				System.out.println("課程管理");
			} else {
				String servletPath = request.getServletPath();
				System.out.println(servletPath);
				request.setAttribute("path", servletPath);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
//			connection = datasource.getConnection();
			CourseDao courseDao = new CourseDao();
			
			List<CourseBean> list = courseDao.selectAll();
			
			System.out.println(list);
			request.setAttribute("list", list);
			request.getRequestDispatcher("courseList.jsp").forward(request, response);
			String s = "";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
////		Connection connection = null;
//		try {
////			connection = datasource.getConnection();
//			CourseDao courseDao = new CourseDao();
//			try {
//				courseDao.createConnection();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			List<CourseBean> list = courseDao.selectAll();
//			request.setAttribute("list", list);
//	        request.getRequestDispatcher("./List.jsp").forward(request, response);
//	        courseDao.closeConnection();
////			response.sendRedirect("ListCourse.jsp");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
