package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import bean.ExamBean;
import util.JdbcUtil;

public class ExamDao {
	static final String QUID_SQL_STRING = " SELECT S.SubjectName, ED.EducationLevel,E.ExamName, E.Score, E.ExamDate\r\n"
			+ "FROM Examination E JOIN Subject S on E.SubjectName = S.SubjectID \r\n"
			+ "				      JOIN Education ED on E.EducationLevel = ED.EducationID";
	
	static final String QU_SQL_STRING = "SELECT E.ExamID,S.SubjectName, ED.EducationLevel,E.ExamName, E.ExamDate\r\n"
			+ "FROM Examination E JOIN Subject S on E.SubjectName = S.SubjectID\r\n"
			+ "				   JOIN Education ED on E.EducationLevel = ED.EducationID\r\n"
			+ "\r\n"
			+ "Where  S.SubjectName = ? and ED.EducationLevel = ?";
	
	static final String QUALL_SQL_STRING = "SELECT E.ExamID,S.SubjectName, ED.EducationLevel,E.ExamName, E.ExamDate\r\n"
										 + "FROM Examination E JOIN Subject S on E.SubjectName = S.SubjectID\r\n"
										 + "				   JOIN Education ED on E.EducationLevel = ED.EducationID";
			
	static final String RE_SQL_STRING = "Delete from Examination where ExamID = ?";
	
	static final String INS_SQL_STRING = "insert into Examination(SubjectName, EducationLevel, ExamName, ExamDate ) values(?,?,?,?)";
	
	static final String UP_SQL_STRING = "    UPDATE Examination\r\n"
										+ "  SET SubjectName=? ,EducationLevel=?,ExamDate=?, ExamName=? \r\n"
										+ "  WHERE ExamID= ?;";
	  
	
	public int insertExam(ExamBean ExamData) throws SQLException {
		//建立連線
		ExamConnect connItem = new ExamConnect();
		Connection conn = JdbcUtil.getConnection();
		
		//SQL準備
		String sqlString = INS_SQL_STRING;
		PreparedStatement preState = null;
		ResultSet rs = null;
		
		int subIdx = ExamUtil.getSubIdx(ExamData.getSubject());
		int eduIdx = ExamUtil.getEduIdx(ExamData.getEducation());
		
		preState = conn.prepareStatement(sqlString);
		preState.setInt(1, subIdx);
		preState.setInt(2, eduIdx);
		preState.setString(3, ExamData.getExamName());
		preState.setObject(4, ExamData.getDate());
		
		int row = preState.executeUpdate();		                             
		//System.out.println(sqlString);
		
		System.out.println("Dao增加"+row+"筆");
		
		connItem.closeCon(rs,preState,conn);
		return row;
	}
	  
	public int removeExam(ExamBean ExamData) throws SQLException {
		//建立連線
		ExamConnect connItem = new ExamConnect();
		Connection conn = JdbcUtil.getConnection();
		
		//SQL準備
		String sqlString = RE_SQL_STRING;
		PreparedStatement preState = null;
		ResultSet rs = null;
		preState = conn.prepareStatement(sqlString);
		preState.setInt(1, ExamData.getExamID());

		int row = preState.executeUpdate();		                             

		//System.out.println(sqlString);
		
		System.out.println("Dao刪除"+row+"筆");
		connItem.closeCon(rs,preState,conn);
		return row;
	}	
	
	public int updateExam(ExamBean ExamData) throws SQLException {
		//建立連線
		ExamConnect connItem = new ExamConnect();
		Connection conn = JdbcUtil.getConnection();
		
		//SQL準備
		String sqlString = UP_SQL_STRING;
		PreparedStatement preState = null;
		ResultSet rs = null;
		

		int subIdx = ExamUtil.getSubIdx(ExamData.getSubject());
		int eduIdx = ExamUtil.getEduIdx(ExamData.getEducation());	
		

		preState = conn.prepareStatement(sqlString);
		preState.setInt(1, subIdx);
		preState.setInt(2, eduIdx);
		preState.setObject(3, ExamData.getDate());
		preState.setString(4, ExamData.getExamName());
		preState.setInt(5, ExamData.getExamID());
		int row = preState.executeUpdate();			
		
		System.out.println("Dao修改"+row+"筆");
		connItem.closeCon(rs,preState,conn);
		return row;
	}
	
	
	public List<ExamBean> queryExam(ExamBean ExamData) throws SQLException{
		//建立連線
		ExamConnect connItem = new ExamConnect();
		Connection conn = JdbcUtil.getConnection();
		
		System.out.println("進入qudao");
		//SQL準備
		String sqlString = QU_SQL_STRING;
		PreparedStatement preState = null;
		ResultSet rs = null;
		
		//建立儲存物件
		List<ExamBean> quBeanList = new ArrayList<ExamBean>();
		
		//System.out.println(subject+education);
		preState = conn.prepareStatement(sqlString);
		preState.setString(1, ExamData.getSubject());
		preState.setString(2, ExamData.getEducation());
		rs = preState.executeQuery();		                             
		System.out.println("進入qudao2");
		while (rs.next()) {
			ExamBean quBean = new ExamBean(
					rs.getInt("ExamID"),
					rs.getString("SubjectName"),
					rs.getString("EducationLevel"),
					rs.getString("ExamName"),
					rs.getDate("ExamDate"));
			quBeanList.add(quBean);
		}
		System.out.println("進入qudao3");
		connItem.closeCon(rs,preState,conn);
		return quBeanList;
	}

	  
	public List<ExamBean> queryAll() throws SQLException{
		//建立連線
		ExamConnect connItem = new ExamConnect();
		Connection conn = JdbcUtil.getConnection();
		
		//SQL準備
		String sqlString = QUALL_SQL_STRING;
		PreparedStatement preState = null;
		ResultSet rs = null;
		
		//建立儲存物件
		List<ExamBean> quAllList = new ArrayList<ExamBean>();
		
		preState = conn.prepareStatement(sqlString);
		rs = preState.executeQuery();		                             
		
		while (rs.next()) {
			ExamBean quAllBean = new ExamBean(
										rs.getInt("ExamID"),
										rs.getString("SubjectName"),
										rs.getString("EducationLevel"),
										rs.getString("ExamName"),
										rs.getDate("ExamDate"));
			quAllList.add(quAllBean);
		}
		
//		  for (int i = 0; i < quAllList.size(); i++) {
//			  System.out.println(quAllList.get(i).getSubject()+quAllList.get(i).getEducation()+quAllList.get(i).getExamName());
//		  }
		
		connItem.closeCon(rs,preState,conn);
		return quAllList;
		
	}
	  
	  
	  
	  
	  
//	  public static void main(String args[]) {
//		  
//		  DsExamDao dsTestDao = new DsExamDao();
//		  ExamBean testBean = new ExamBean();
//		  testBean.setSubject("Chinese");
//		  testBean.setEducation("Adult");
//		  
//		  try {
//			  dsTestDao.queryExam(testBean);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		  
//	  }
	
}
