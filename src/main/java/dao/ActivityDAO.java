package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.ActivityBean;
import util.JdbcUtil;

public class ActivityDAO {
	private Connection connection;
	private String tableName = "activitys";
	
	public ActivityDAO() {}
	public ActivityDAO(Connection connection) {
		this.connection = connection;
	}
	//輸入判斷 取得活動 
	public List<ActivityBean> selectActivities(String sqlWhere) throws SQLException{
		if (sqlWhere == null) {
			sqlWhere = "";
		}
		Connection connection = JdbcUtil.getConnection();
		String sql = "select * from "+tableName+" "+ sqlWhere+" "+"order by start_time DESC";
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		ResultSet resultSet = prepareStatement.executeQuery();
		List<ActivityBean> activities = rsToList(resultSet);
		JdbcUtil.closeConnection(connection);
		return activities;
	}
	//取得所有活動
	public List<ActivityBean> selectActivities() throws SQLException{
		String sqlWhere = null;
		List<ActivityBean> activities = selectActivities(sqlWhere);
		return activities;
	}
	//新增活動	
	public boolean insertActivities(ActivityBean activity) throws SQLException{
		String sql  = "INSERT INTO activitys(title,content,start_time,end_time,imgPath) VALUES ( ?,?,?,?,?)";
		Connection connection = JdbcUtil.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, activity.getTitle());
		prepareStatement.setString(2, activity.getContent());
		prepareStatement.setString(3, activity.getStart_time());
		prepareStatement.setString(4, activity.getEnd_time());
		prepareStatement.setString(5, activity.getImgPath());
		boolean row = prepareStatement.execute();
		JdbcUtil.closeConnection(connection);
		return row;
		
	}
	//刪除活動
	public boolean deleteActivities(ActivityBean activity) throws SQLException {
		boolean execute = deleteActivities(activity.getId());
		return execute ;
	}
	public boolean deleteActivities(String id) throws SQLException {
		Connection connection = JdbcUtil.getConnection();
		String sql = "DELETE FROM "+tableName+" WHERE id = ?";
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, id);
		boolean execute = prepareStatement.execute();
		JdbcUtil.closeConnection(connection);
		return execute ;
	}
	//修改活動
	public boolean updateActivities(ActivityBean activity) throws SQLException{
		String sql = "UPDATE "+tableName+" SET title = ?,content = ?,start_time = ? ,end_time = ?,imgPath = ? WHERE id = ?";
		Connection connection = JdbcUtil.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, activity.getTitle());
		prepareStatement.setString(2, activity.getContent());
		prepareStatement.setString(3, activity.getStart_time());
		prepareStatement.setString(4, activity.getEnd_time());
		prepareStatement.setString(5, activity.getImgPath());
		prepareStatement.setString(6, activity.getId());
		
		boolean row = prepareStatement.execute();
		JdbcUtil.closeConnection(connection);
		return row;
	}
	
	
	
	

	
//===========================================================================================	
//=============================非公開方法======================================================	
//===========================================================================================	
	//取得 resultSet 回傳 LinkedList<Activity> activities
	private List<ActivityBean> rsToList(ResultSet resultSet){
		LinkedList<ActivityBean> activities = new LinkedList<ActivityBean>();
		try {
			while(resultSet.next()) {
				ActivityBean activity = new ActivityBean();
				activity.setId(resultSet.getString("id"));
				activity.setTitle(resultSet.getString("title"));
				activity.setContent(resultSet.getString("content"));
				activity.setStart_time(resultSet.getString("start_time"));
				activity.setEnd_time(resultSet.getString("end_time"));
				activity.setImgPath(resultSet.getString("imgPath"));
				activities.add(activity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		return activities;
	}
//================================================================================================	
}
