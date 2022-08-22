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

import bean.ColumnBean;

import dao.ColumnDAO1;

@WebServlet("/ColumnServlet")
public class ColumnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ColumnDAO1 columnDAO1;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ColumnServlet() {
		this.columnDAO1 = new ColumnDAO1();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// insert
		System.out.println(request.getParameter("add") + "**********");
		try {
			System.out.println("add Servlet");
			if (request.getParameter("add")!=null) {
				System.out.println("*******************************************");
				if (Add(request, response)) {
					request.getRequestDispatcher("/ColumnConfirmAdd.jsp").forward(request, response);
				} else {
					System.out.println("something wrong");
				}
			} else if (request.getParameter("confirm") != null) {
				request.getRequestDispatcher("/ColumnQueryAll.jsp").forward(request, response);
			} else if (request.getParameter("backToQuery") != null) {
				request.getRequestDispatcher("/ColumnQueryAll.jsp").forward(request, response);
			}
			// delete
			System.out.println("del Servlet");
			if (request.getParameter("delete") != null) {
				delete(request, response);
				request.getRequestDispatcher("/ColumnQueryAll.jsp").forward(request, response);
			}
			// update
			System.out.println(request.getParameter("update"));
			if (request.getParameter("update") != null) {
				
				System.out.println("update in servlet");
				update(request, response);
				request.getRequestDispatcher("/ColumnQueryAll.jsp").forward(request, response);
			}
			// search
			if (request.getParameter("searchno") != null) {
				search(request, response);
			}
			// detail
			if (request.getParameter("more") != null) {
				more(request, response);
				request.getRequestDispatcher("ColumnMoreContents");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.getRequestDispatcher("/ColumnQueryAll.jsp").forward(request, response);
		} 
	}

	public boolean Add(HttpServletRequest request, HttpServletResponse response) {
		ColumnBean column = new ColumnBean();
		System.out.println(request.getParameter("publish_time"));
		column.setDate(request.getParameter("publish_time"));
		column.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		column.setAuthor(request.getParameter("author"));
		column.setRole(request.getParameter("role"));
		column.setContents(request.getParameter("contents"));

		return columnDAO1.insertColumn(column);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		ColumnDAO1 columndao = new ColumnDAO1();
		columndao.deleteColumnByNo(Integer.parseInt(request.getParameter("article_no")));
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		ColumnBean column = new ColumnBean();
		column.setNo(Integer.parseInt(request.getParameter("article_no")));
		column.setDate(request.getParameter("publish_time"));
		column.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		column.setAuthor(request.getParameter("author"));
		column.setRole(request.getParameter("role"));
		column.setContents(request.getParameter("contents"));
		String no = request.getParameter("article_no");
		System.out.println("測試servlet取值" + no);

		columnDAO1.updateColumn(column);

		// int ii;
		try {
			// String o = request.getParameter("article_no");
			// ii = Integer.parseInt(o.trim());
			// column.setNo(ii);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int articleno = Integer.parseInt(request.getParameter("search"));
		ColumnDAO1 columndao = new ColumnDAO1();
		try {
			ColumnBean col = columndao.selectByArticleNo(articleno);
			request.setAttribute("column", col);
			request.getRequestDispatcher("/ColumnQueryAll.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void more(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int articleno = Integer.parseInt(request.getParameter("article_no"));
		System.out.println(request.getParameter("article_no"));
		ColumnDAO1 columndao = new ColumnDAO1();
		try {
			ColumnBean col = columndao.selectByArticleNo(articleno);
			request.getSession(true).setAttribute("col", col);
			request.getRequestDispatcher("/ColumnMoreContents.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
