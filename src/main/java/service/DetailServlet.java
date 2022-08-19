package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CourseBean;
import bean.MemberBean;
import cartdao.impt.CourseDao;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        MemberBean user =(MemberBean)request.getSession().getAttribute("user");
		if(user == null) {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}

        CourseDao dao = new CourseDao();
        
        try {
			CourseBean cbean = dao.selectById(Integer.parseInt(request.getParameter("course_id")));
			request.setAttribute("cbean", cbean);
			request.getRequestDispatcher("courseDetails.jsp").forward(request, response);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	
		
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    }
	}
		

