package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;



public class ExamConnect {
		
	DataSource ds = null;
    Connection conn = null;
    InitialContext ctx = null;
    
	public Connection getcon(){

			try {
				ctx = new InitialContext();
				ds = (DataSource)ctx.lookup("java:comp/env/jdbc/EmployeeDB");
				System.out.println(ds);
				if (ds == null)
					throw new ServletException("Unknown DataSource jdbc/EmployeeDB");
				
				conn = ds.getConnection();
				
				if (!conn.isClosed()) {
					
					System.out.println("開啟連線");
				}
				
			} catch (NamingException e) {
				System.out.println("Error in NamingException" );
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Error in SQLException" );
				e.printStackTrace();
			} catch (ServletException e) {
				System.out.println("Error in ServletExption" );				
				e.printStackTrace();
			}
		return conn;
	}
	
	public void closeCon(ResultSet rs,PreparedStatement preState, Connection conn) throws SQLException {
		if(rs != null ) 
			rs.close();
		
		if (preState != null) 
			preState.close();
		
		if (conn != null) 
			conn.close();
		
	}
}
