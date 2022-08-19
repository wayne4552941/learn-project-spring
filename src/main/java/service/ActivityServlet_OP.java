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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bean.ActivityBean;
import dao.ActivityDAO;
import util.JdbcUtil;

/**
 * Servlet implementation class Activity_OP
 */
@WebServlet("/Activity_OP")
public class ActivityServlet_OP extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DataSource ds = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivityServlet_OP() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		
		try {
			request.setCharacterEncoding("UTF-8");
			ActivityDAO activityDAO = new ActivityDAO();
			//判斷請求
			
			List<ActivityBean> activities = null;
			if(request.getParameter("request")!= null) {
				System.out.println(request.getParameter("request"));
				if (request.getParameter("request").equals("修改")) {
					ActivityBean activityBean = requestReadActivity(request, response);
					request.setAttribute("activityBean", activityBean);
					request.getRequestDispatcher("ActivityUpdate.jsp").forward(request, response);
					return;
				} else if (request.getParameter("request").equals("Update")) {
					updateActivities(request, response, activityDAO);
					activities = activityDAO.selectActivities();
				} else if (request.getParameter("request").equals("新增")) {
					request.getRequestDispatcher("ActivityInster.jsp").forward(request, response);
					return;
				} else if (request.getParameter("request").equals("Insert")) {
					insertActivities(request, response, activityDAO);
					activities = activityDAO.selectActivities();
				} else if (request.getParameter("request").equals("刪除")) {
					deleteActivities(request, response, activityDAO);
					activities = activityDAO.selectActivities();
				}else if (request.getParameter("request").equals("查詢")) {
					String[] yearAndMonth = request.getParameter("select").split("-");
					if (yearAndMonth.length == 2) {
						String year = yearAndMonth[0];
						String month = yearAndMonth[1];
						String where = "where (DATEPART(yy, start_time) = " + year + " AND DATEPART(mm, start_time) = "
								+ month + ")";
						activities = activityDAO.selectActivities(where);
					} else {

						activities = activityDAO.selectActivities();
					}
				}
			}else {
				
				activities = activityDAO.selectActivities();
				
			}
			
			
			HttpSession session = request.getSession();
			session.setAttribute("selectAllActivity", activities);
			//request.setAttribute("selectAllActivity", activities);
			//request.getRequestDispatcher("/Activity_OP.jsp").forward(request, response);
			response.sendRedirect("Activity_OP.jsp");
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			System.out.println("連線錯誤");
		}
	}
	
	//=====================================================================================================================
	//=========================private 方法=================================================================================
	//=====================================================================================================================
	//調用資料庫新增活動
	//在request裡，讀出做成"活動物件"
	private ActivityBean requestReadActivity(HttpServletRequest request, HttpServletResponse response) {
		ActivityBean activity = new ActivityBean();
		activity.setId(request.getParameter("activity_id"));
		activity.setTitle(request.getParameter("activity_title"));
		activity.setContent(request.getParameter("activity_content"));
		activity.setStart_time(request.getParameter("activity_start_time").replace("T"," "));
		activity.setEnd_time(request.getParameter("activity_end_time").replace("T"," "));
		System.out.println(request.getParameter("activity_imgPath"));
		activity.setImgPath(request.getParameter("activity_imgPath"));
		
		
		return activity;
	}
	private void insertActivities(HttpServletRequest request, HttpServletResponse response ,ActivityDAO activityDAO) {
		try {
			ActivityBean activity = requestReadActivity(request, response);
			activityDAO.insertActivities(activity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//調用資料庫刪除活動
	private void deleteActivities(HttpServletRequest request, HttpServletResponse response ,ActivityDAO activityDAO ) {
		String activity_id = request.getParameter("activity_id");
		try {
			activityDAO.deleteActivities(activity_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//調用資料庫更新活動
	private void updateActivities(HttpServletRequest request, HttpServletResponse response ,ActivityDAO activityDAO ) {
		ActivityBean activity = requestReadActivity(request, response);
		try {
			activityDAO.updateActivities(activity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
	}
	
	

}
