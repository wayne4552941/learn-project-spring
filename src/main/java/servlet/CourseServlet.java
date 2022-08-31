package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import bean.CourseBean;
import service.CourseService;

@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
	private static final long serialVersionUID = 1L;

	public CourseServlet() {
		super();
	}

	CourseService service = new CourseService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
		String action = request.getParameter("action");
		System.out.println(action);

		if (action == null) {
			action = "1";
		}
		switch (action) {
		case "insert":
			insertCourse(request, response);
			break;
		case "queryId":
			queryId(request, response);
			break;
		case "queryName":
			queryName(request, response);
			break;
		case "delete":
			deleteCourse(request, response);
			break;
		case "update":
			showUpdateDetails(request, response);
			break;
		case "updateOk":
			updateSubmit(request, response);
			break;
		case "details":
			detailsCourse(request, response);
			break;
		default:
			listCourse(request, response);
			break;
		}
	}

	private void listCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<CourseBean> list = service.selectAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/List.jsp").forward(request, response);

	}

	private void insertCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseBean cosBean = new CourseBean();
		try {
			request.setCharacterEncoding("UTF-8");
			BeanUtils.populate(cosBean, request.getParameterMap());
			cosBean.setCourse_picture("images/" + cosBean.getCourse_picture());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		service.insert(cosBean);
		System.out.println(cosBean);
		request.getRequestDispatcher("/InsertSuccess.jsp").forward(request, response);
	}

	private void detailsCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseBean cbean = service.select(Integer.parseInt(request.getParameter("course_id")));
		request.setAttribute("cbean", cbean);
		request.getRequestDispatcher("Details.jsp").forward(request, response);

	}

	private void queryId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("errorMsgMap", errorMsgMap);
//		int course_id = Integer.parseInt(request.getParameter("keyword"));
//		CourseBean cb = service.select(course_id);
		String course_id = request.getParameter("keyword");
		if(course_id == "") {
			course_id = "0";
		};
		CourseBean cb = service.select(Integer.parseInt(course_id));
		if (cb != null) {
			request.setAttribute("queryId", cb);
			request.getRequestDispatcher("QueryId.jsp").forward(request, response);
		} else {
			if(cb == null || course_id == "0") {
				errorMsgMap.put("QueryError", "<font color=red size=5>查無資料!!</font>");
//			request.getRequestDispatcher("QueryFailed.jsp").forward(request, response);	
			}
		
};
		if (!errorMsgMap.isEmpty()) {
			listCourse(request, response);
		};
		System.out.println(cb.getCourse_id());

	}

	private void queryName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("errorMsgMap", errorMsgMap);
		String course_name = request.getParameter("keyword");
		List<CourseBean> list = service.selectName(course_name);
		if(course_name == "") {
			listCourse(request, response);
		}
		if (!list.isEmpty()) {
			request.setAttribute("queryResult", list);
			request.getRequestDispatcher("Query.jsp").forward(request, response);
		} else {
				errorMsgMap.put("QueryError", "<font color=red size=5>查無資料!!</font>");
//			request.getRequestDispatcher("QueryFailed.jsp").forward(request, response);
			
		}
		if (!errorMsgMap.isEmpty()) {
			listCourse(request, response);
		}

	}

	protected void showUpdateDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("course_id"));
		CourseBean bean = service.select(Integer.parseInt(request.getParameter("course_id")));
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("Update.jsp").forward(request, response);

	}

	protected void updateSubmit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int subject_id = Integer.parseInt(request.getParameter("subject_id"));
		int education_id = Integer.parseInt(request.getParameter("education_id"));
		String course_name = request.getParameter("course_name");
		String course_introduction = request.getParameter("course_introduction");
		float course_price = Float.parseFloat(request.getParameter("course_price"));
		String course_duration = request.getParameter("course_duration");
		int enrollment = Integer.parseInt(request.getParameter("enrollment"));
		String course_date = request.getParameter("course_date");
		String lecturer_name = request.getParameter("lecturer_name");
		String lecturer_email = request.getParameter("lecturer_email");
		String course_picture = request.getParameter("course_picture");
		int course_id = Integer.parseInt(request.getParameter("course_id"));
		service.updateOne(course_id, user_id, subject_id, education_id, course_name, course_introduction, course_price,
				course_duration, enrollment, course_date, lecturer_name, lecturer_email, course_picture);

		listCourse(request, response);
	}

	private void deleteCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parameter = request.getParameter("course_id");
		System.out.println("delete:" + parameter);
		Integer.parseInt(parameter);
		service.deleteOne(Integer.parseInt(parameter));
		System.out.println(service.deleteOne(Integer.parseInt(parameter)));
		System.out.println("刪除完畢");
		listCourse(request, response);

	}

}
