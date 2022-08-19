package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Column;
import bean.MemberBean;
import dao.ColumnDAO;

/**
 * Servlet implementation class ColumnServlet
 */
@WebServlet("/ColumnServlet")
public class ColumnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ColumnDAO columnDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ColumnServlet() {
		this.columnDAO = new ColumnDAO();

	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(request.getParameter("update"));
	MemberBean user =(MemberBean)request.getSession().getAttribute("user");
		if(user == null) {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		if (request.getParameter("add") != null) {
			if (Add(request, response)) {
				request.getRequestDispatcher("/ColumnConfirmAdd.jsp").forward(request, response);
			} else {
				//
			}
		} else if (request.getParameter("confirm") != null) {
			request.getRequestDispatcher("/ColumnQueryAll.jsp").forward(request, response);
		} else if (request.getParameter("backToQuery") != null) {
			request.getRequestDispatcher("/ColumnQueryAll.jsp").forward(request, response);
		}

		if (request.getParameter("delete") != null) {
			delete(request, response);
			request.getRequestDispatcher("/ColumnQueryAll.jsp").forward(request, response);
		}
		// update
		if (request.getParameter("update") != null) {
			update(request, response);
			request.getRequestDispatcher("/ColumnQueryAll.jsp").forward(request, response);
		}
		if(request.getParameter("searchno")!=null) {
			search(request,response);
		}
		if(request.getParameter("more")!=null) {
			more(request,response);
			request.getRequestDispatcher("ColumnMoreContents");
		}
	}

	public boolean Add(HttpServletRequest request, HttpServletResponse response) {
		Column column = new Column();
		System.out.println(request.getParameter("publish_time"));
		column.setDate(request.getParameter("publish_time"));
		column.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		column.setAuthor(request.getParameter("author"));
		column.setRole(request.getParameter("role"));
		column.setContents(request.getParameter("contents"));

		return columnDAO.insertColumn(column);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		ColumnDAO columndao = new ColumnDAO();
		columndao.deleteColumnByNo(Integer.parseInt(request.getParameter("article_no")));

	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		Column column = new Column();

		column.setDate(request.getParameter("publish_time"));
		column.setUser_id(Integer.parseInt(request.getParameter("user_id")));	
		column.setAuthor(request.getParameter("author"));
		column.setRole(request.getParameter("role"));
		column.setContents(request.getParameter("contents"));
		String no = request.getParameter("article_no");

		System.out.println("測試servlet取值"+no);
		
		int ii;
		try {
			String o = request.getParameter("article_no");
			ii = Integer.parseInt(o.trim());
			column.setNo(ii);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("---TEST---");
		System.out.println(request.getParameter("publish_time"));
		System.out.println(Integer.parseInt(request.getParameter("user_id")));
		System.out.println(request.getParameter("author"));
		System.out.println(request.getParameter("role"));
		System.out.println(request.getParameter("contents"));
		System.out.println(no);
		
		columnDAO.updateColumn(column);
		

	}
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int articleno = 0;
		try {
			articleno= Integer.parseInt(request.getParameter("search"));
			
		} catch (Exception e) {
			articleno =0;
		}
		ColumnDAO columndao = new ColumnDAO();
		try {
			Column col = columndao.selectByArticleNo(articleno);
			request.setAttribute("column",col);
			request.getRequestDispatcher("/ColumnQueryAll.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void more(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int articleno= Integer.parseInt(request.getParameter("article_no"));
		System.out.println(request.getParameter("article_no"));
		ColumnDAO columndao = new ColumnDAO();
		try {
			Column col = columndao.selectByArticleNo(articleno);
			request.getSession(true).setAttribute("col", col);
			request.getRequestDispatcher("/ColumnMoreContents.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
