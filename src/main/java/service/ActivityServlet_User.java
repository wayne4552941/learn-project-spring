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

import bean.ActivityBean;
import dao.ActivityDAO;
import util.JdbcUtil;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/Activity")
public class ActivityServlet_User extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DataSource ds = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		try {
			ActivityDAO activityDAO = new ActivityDAO();
			List<ActivityBean> activities = null;
			if (req.getParameter("request")!=null&&req.getParameter("request").equals("查詢")) {
				String[] yearAndMonth = req.getParameter("select").split("-");
				if (yearAndMonth.length == 2) {
					String year = yearAndMonth[0];
					String month = yearAndMonth[1];
					String where = "where (DATEPART(yy, start_time) = " + year + " AND DATEPART(mm, start_time) = "
							+ month + ")";
					activities = activityDAO.selectActivities(where);
				} else {

					activities = activityDAO.selectActivities();
				}

			}else {
				
				activities = activityDAO.selectActivities();
			} 
			req.setAttribute("selectAllActivity", activities);
			req.getRequestDispatcher("Activity_User.jsp").forward(req, resp);

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			System.err.println("連線錯誤");
			
		}
	}

}