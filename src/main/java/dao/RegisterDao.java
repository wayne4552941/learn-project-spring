package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.MemberBean;
import util.JdbcUtil;


public class RegisterDao {

	public   int registeruser(MemberBean user){
		
		int result=0;
		Connection conn = null;
		try {
			 conn = JdbcUtil.getConnection();
			PreparedStatement prestate=conn.prepareStatement("insert into member(account,password,email) values (?,?,?)");
			prestate.setString(1, user.getAccount());
			prestate.setString(2, user.getPassword());
			prestate.setString(3, user.getEmail());
			
			result=prestate.executeUpdate();
			
			
			
		} catch (Exception e) {
			
		}finally {
			try {
				JdbcUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
		
		
		
	}
}
