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
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.ExamBean;
import bean.QuesBean;
import service.ExamService;
import util.ExamUtil;
import util.HibernateUtil;




//import ebookshop.CartItem;

/**
 * Servlet implementation class ExamController
 */
@WebServlet("/ExamController")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataSource ds;	
	List<QuesBean> theQuestable = new ArrayList<QuesBean>();

	
	/////////////////取得連線
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  //setup response character encoding type
		  
		response.setContentType("text/html");   //setup response content type
		response.setCharacterEncoding("UTF-8");
		
	    String nextPage = "";
	    String todo = request.getParameter("todo");
	    
		List<ExamBean> theExamTable= new ArrayList<ExamBean>();
	    //連線物件	    
		SessionFactory factory = HibernateUtil.getFactory();
		Session session = factory.getCurrentSession();
		ExamService examService = new ExamService(session);


		//////////CRUD方法//////////CRUD方法	//////////CRUD方法	//////////CRUD方法	//////////CRUD方法	//////////CRUD方法			         
	    if (todo.equals("insert")) {
	    	
	    	//拿參數
			String subString = request.getParameter("subject");
			String eduString = request.getParameter("education");
			String examName = request.getParameter("examName");
			String examDate = request.getParameter("examDate");
	    	
			if (!ExamUtil.datacheck(examDate)){
				
				nextPage = "/ExamInsert.jsp";
				String warn = "資料錯誤";
				request.setAttribute("warn", warn);
				
			}else {
			
				examService.insert(subString, eduString, examName, examDate);
			
				nextPage = "/Exam.jsp";
			}		
			
			
		} else if (todo.equals("delete")) {
			
			//刪除
			String rmIdString = request.getParameter("examID");
			Integer rmIdx = Integer.valueOf(rmIdString);
			examService.delete(rmIdx);
			
			//展現刪除成果
	    	theExamTable = examService.selectAll();
	    	request.getSession().setAttribute("examTable", theExamTable);  
			
			nextPage = "/Exam.jsp";
		} else if (todo.equals("update")) {
					
	    	//拿參數
			String updateId = request.getParameter("examID");
			String subString = request.getParameter("subject");
			String eduString = request.getParameter("education");
			String examName = request.getParameter("examName");
			String examDate = request.getParameter("examDate");
	    	
			if (!ExamUtil.datacheck(examDate)){
				
				nextPage = "/ExamUpdate.jsp";
				String warn = "資料錯誤";
				request.setAttribute("warn", warn);
				
			}else {
			
				examService.update(updateId,subString, eduString, examName, examDate);
				
		    	theExamTable = examService.selectAll();
		    	request.getSession().setAttribute("examTable", theExamTable); 	
		    	
				nextPage = "/Exam.jsp";
			}		
				
			
			
		} else if (todo.equals("query")) {
		    //參數抓取與設置參數bean
			String subString = request.getParameter("subject");
			String eduString = request.getParameter("education");
			

			
			//取得結果
			theExamTable = examService.select(subString,eduString);
			request.getSession().setAttribute("examTable", theExamTable);    	
			
			
			nextPage = "/Exam.jsp";
	    } else if (todo.equals("queryAll")) {
	    	
	    	theExamTable = examService.selectAll();
	    	request.getSession().setAttribute("examTable", theExamTable);    	

	    	
	    	nextPage = "/Exam.jsp";
		//////////考試內容呈現Button	//////////考試內容呈現Button
		} else if (todo.equals("test")) {

			
			
			
			
			

			nextPage = "/ExamPaper.jsp";
		} else if(todo.equals("testSubmit")) {
			

			
			
			nextPage = "/Exam.jsp";
			
		}
	    
	    //DISPATCHER
	    ServletContext servletContext = getServletContext();
	    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
	    requestDispatcher.forward(request, response);
		
	}

}
