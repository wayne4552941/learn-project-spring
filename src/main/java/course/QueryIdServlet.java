package course;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CourseBean;
import cartdao.impt.CourseDao;

/**
 * Servlet implementation class QueryIdServlet
 */
@WebServlet("/QueryIdServlet")
public class QueryIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public QueryIdServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        int course_id = Integer.parseInt(request.getParameter("keyword")) ;
        CourseDao dao = new CourseDao();
        try {
			CourseBean cb = dao.selectById(course_id);
			System.out.println(cb.getCourse_id());
			request.setAttribute("queryId", cb);
		    request.getRequestDispatcher("courseQueryId.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		 
		
//			response.sendRedirect("QueryId.jsp");
		}
      
	}
	


}
