package course;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import bean.CourseBean;
import cartdao.impt.CourseDao;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateServlet() {
        super();
    }
    
    
    
 
       

    
    
	 
	protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        System.out.println(request.getParameter("course_id"));

//        int user_id = Integer.parseInt(request.getParameter("user_id"));
//        int subject_id = Integer.parseInt(request.getParameter("subject_id"));
//        int education_id = Integer.parseInt(request.getParameter("education_id"));
//        String course_name = request.getParameter("course_name");
//        String course_introduction = request.getParameter("course_introduction");
//        float course_price = Float.parseFloat(request.getParameter("course_price"));
//        String course_duration = request.getParameter("course_duration");
//        int enrollment = Integer.parseInt(request.getParameter("enrollment"));
//        String course_date = request.getParameter("course_date");
//        String lecturer_name = request.getParameter("lecturer_name");
//        String lecturer_email = request.getParameter("lecturer_email");
//        String course_picture = request.getParameter("course_picture");
//        int course_id = Integer.parseInt(request.getParameter("course_id"));
        CourseDao dao = new CourseDao();
//        try {
//			dao.createConnection();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
        try {
			CourseBean bean = dao.selectById(Integer.parseInt(request.getParameter("course_id")));
			request.setAttribute("bean", bean);
			request.getRequestDispatcher("courseUpdate.jsp").forward(request, response);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
//		int update = dao.update(course_id,user_id, subject_id, education_id,course_name, course_introduction,
//				course_price, course_duration, enrollment, course_date, lecturer_name,
//				lecturer_email, course_picture);
//		request.setAttribute("update", update);
//        response.sendRedirect("Update.jsp");
//        try {
//			dao.closeConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
	
//	protected  void updateSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        request.setCharacterEncoding("UTF-8");
//
//        int user_id = Integer.parseInt(request.getParameter("user_id"));
//        int subject_id = Integer.parseInt(request.getParameter("subject_id"));
//        int education_id = Integer.parseInt(request.getParameter("education_id"));
//        String course_name = request.getParameter("course_name");
//        String course_introduction = request.getParameter("course_introduction");
//        float course_price = Float.parseFloat(request.getParameter("course_price"));
//        String course_duration = request.getParameter("course_duration");
//        int enrollment = Integer.parseInt(request.getParameter("enrollment"));
//        String course_date = request.getParameter("course_date");
//        String lecturer_name = request.getParameter("lecturer_name");
//        String lecturer_email = request.getParameter("lecturer_email");
//        String course_picture = request.getParameter("course_picture");
//        int course_id = Integer.parseInt(request.getParameter("course_id"));
//        
//        CourseDao dao = new CourseDao();
//        try {
//			dao.createConnection();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//       
//		dao.update(course_id,user_id, subject_id, education_id,course_name, course_introduction,
//				course_price, course_duration, enrollment, course_date, lecturer_name,
//				lecturer_email, course_picture);
//		
//	    request.getRequestDispatcher("/CourseServlet1").forward(request,response);
////        response.sendRedirect("Update.jsp");
//        try {
//			dao.closeConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	

}
