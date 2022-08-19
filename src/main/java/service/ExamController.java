package service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.catalina.connector.Request;

import bean.ExamBean;
import bean.MemberBean;
import dao.ExamDao;
import dao.ExamUtil;


//import ebookshop.CartItem;

/**
 * Servlet implementation class ExamController
 */
@WebServlet("/ExamController")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataSource ds;	
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	
	/////////////////取得連線
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  //setup response character encoding type
		  
		response.setContentType("text/html");   //setup response content type
		response.setCharacterEncoding("UTF-8");
		
	    String nextPage = "";
	    String todo = request.getParameter("todo");
	    MemberBean user =(MemberBean)request.getSession().getAttribute("user");
		if(user == null) {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}

		
		//預防資料出錯		
//		System.out.println(subject);
//		System.out.println(education);
//		System.out.println(examDate);
//		System.out.println(examName);
		
    	
	    
		List<ExamBean> theExamTable= new ArrayList<ExamBean>();
	    //連線物件	    
	    ExamDao examDao = new ExamDao();

	    
	    
		//////////CRUD方法//////////CRUD方法	//////////CRUD方法	//////////CRUD方法	//////////CRUD方法	//////////CRUD方法			         
	    if (todo.equals("insert")) {

		    //參數抓取與設置參數bean
	    ExamBean insBean = new ExamBean();
		String subject = request.getParameter("subject");
		String education = request.getParameter("education");
		String examName = request.getParameter("examName");
		String examDate = request.getParameter("examDate");
    	
		insBean.setSubject(subject);
		insBean.setEducation(education);
		insBean.setExamName(examName);
		//System.out.println(examDate);		
		
		//資料驗證
		if (!ExamUtil.datacheck(examDate)){
			nextPage = "/ExamInsert.jsp";
			String warn = "資料錯誤";
			request.setAttribute("warn", warn);
		}else {
			
			Date tDate;
			
			try {
				tDate = new SimpleDateFormat("yyyy-MM-dd").parse(examDate);

				System.out.println(tDate);
				insBean.setDate(tDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				
				int row = examDao.insertExam(insBean);
				request.getSession().setAttribute("examTable", theExamTable);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			nextPage = "/Exam.jsp";
		}

		} else if (todo.equals("remove")) {
			
		    ExamBean rmBean = new ExamBean();
			String examID = request.getParameter("examID");
			
			rmBean.setExamID(Integer.parseInt(examID));
			
			try {
				
				ExamBean deRsBean = new ExamBean();
				int row = examDao.removeExam(rmBean);
				theExamTable = examDao.queryAll();
				request.getSession().setAttribute("examTable", theExamTable);
//		        theExamTable.remove(examTableIndex);
//		        System.out.println("刪了"+row+"筆");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			System.out.println("刪除了"+row+"筆");	  
			nextPage = "/Exam.jsp";
		} else if (todo.equals("update")) {
			
		    //參數抓取與設置參數bean
	    ExamBean upBean = new ExamBean();
	    String examID = request.getParameter("examID");
		String subject = request.getParameter("subject");
		String education = request.getParameter("education");
		String examName = request.getParameter("examName");
		String examDate = request.getParameter("examDate");
    	
		upBean.setExamID(Integer.parseInt(examID));
		upBean.setSubject(subject);
		upBean.setEducation(education);
		upBean.setExamName(examName);
		//資料驗證
		if (!ExamUtil.datacheck(examDate)){
			nextPage = "/ExamUpdate.jsp";
			String warn = "資料錯誤";
			request.setAttribute("warn", warn);
		}else {
			
			Date tDate;
			
			try {
				tDate = new SimpleDateFormat("yyyy-MM-dd").parse(examDate);
				upBean.setDate(tDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				ExamBean upRsBean = new ExamBean();
				int row = examDao.updateExam(upBean);
				request.getSession().setAttribute("examTable", theExamTable);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			nextPage = "/Exam.jsp";
			
		}		

			
		} else if (todo.equals("query")) {
		    //參數抓取與設置參數bean
		    ExamBean quBean = new ExamBean();
			String subject = request.getParameter("subject");
			String education = request.getParameter("education");
			

			quBean.setSubject(subject);
			quBean.setEducation(education);
			System.out.println(subject);
			System.out.println(education);
			try {
//				ExamBean quRsBean = new ExamBean();
				theExamTable = examDao.queryExam(quBean);
//				theExamTable.add(quRsBean);
				request.getSession().setAttribute("examTable", theExamTable);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < theExamTable.size(); i++) {
				System.out.println(theExamTable.get(i).getSubject() + theExamTable.get(i).getEducation()
						+ theExamTable.get(i).getExamName());
			}
			//分派網頁網址
			nextPage = "/Exam.jsp";
	    } else if (todo.equals("queryAll")) {
	    	
	    	try {
	    		
				theExamTable = examDao.queryAll();
				request.getSession().setAttribute("examTable", theExamTable);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	nextPage = "/Exam.jsp";
		//////////考試內容呈現Button	//////////考試內容呈現Button
		} else if (todo.equals("content")) {
			
			
//			nextPage = "/order.jsp";
		}
	    
	    //DISPATCHER
	    ServletContext servletContext = getServletContext();
	    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
	    requestDispatcher.forward(request, response);
		
	}

}
