package service;

import javax.servlet.*;
import bean.*;



import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import cartdao.impt.CourseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class courseCrudServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    response.setContentType("text/html; charset=UTF-8");
	    // To prevent caching 
	   response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
	   response.setHeader("Pragma","no-cache"); // HTTP 1.0
	   response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
	   MemberBean user =(MemberBean)request.getSession().getAttribute("user");
		if(user == null) {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	    
	   if (request.getParameter("submit")!=null)
	     pressSubmit(request, response);
	   else if (request.getParameter("confirm")!=null)
	     pressConfirm(request, response);
	}
	
	 public void pressSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		 CourseBean  courseBean = new CourseBean();
		 try {
			BeanUtils.populate(courseBean, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		 
		
		courseBean.setCourse_picture("./images/"+courseBean.getCourse_picture());
	    request.getSession(true).setAttribute("reg_course",courseBean);
	    request.getRequestDispatcher("/CheckCourse.jsp").forward(request,response);

	  }
	 
	 public void pressConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		 CourseDao courseDAO = new CourseDao();
	   
	      CourseBean courseData = (CourseBean)request.getSession(true).getAttribute("reg_course");
	      courseDAO.insertCourse(courseData);
	      System.out.println(courseData);
	      request.getRequestDispatcher("/courseInsertSuccess.jsp").forward(request,response);
//	      if (courseDAO.insertCourse(courseData))
//	        {
//	          System.out.println("Get some SQL commands done!");
//	        }
	   
	           
	  }
	 
	 


}
