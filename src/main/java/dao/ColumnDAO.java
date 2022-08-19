package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Column;
import util.JdbcUtil;

//This DAO class provides CRUD database operations for the table article in the database.
public class ColumnDAO {
	private Connection conn = null;

	public Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String urlStr = "jdbc:sqlserver://localhost:1433;" + "databaseName=demo;user=sa;password=s3163552;"
				+ "trustServerCertificate=true";
		this.conn = JdbcUtil.getConnection();
		boolean status = !conn.isClosed();

		if (status) {
			System.out.println("已開啟連線");
			return conn;
		}
		return null;
	}

	public void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("已關閉連線");
		}
	}
	
	//create column
	public boolean insertColumn(Column column){
		String sql="insert into article values(?,?,?,?,?)";
		try {
			conn = createConnection();
			PreparedStatement prepareStatement;
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setObject(1, column.getDate());
			prepareStatement.setObject(2, column.getUser_id());
			prepareStatement.setObject(3, column.getAuthor());
			prepareStatement.setObject(4, column.getRole());
			prepareStatement.setObject(5, column.getContents());
			boolean rowInserted = prepareStatement.executeUpdate()>0;
			
			prepareStatement.close();
			return rowInserted;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				JdbcUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//update column
	public boolean updateColumn(Column column) {
		boolean rowUpdated;
		try {
			conn=createConnection();
			String sql="update article set publish_time=?,user_id=?,author=?,role=?,contents=? where article_no=?";
			PreparedStatement preState = conn.prepareStatement(sql);
			preState.setString(1, column.getDate());
			preState.setInt(2, column.getUser_id());
			preState.setString(3, column.getAuthor());
			preState.setString(4, column.getRole());
			preState.setString(5, column.getContents());
			preState.setInt(6, column.getNo());
			
			
			System.out.println("----------");
			System.out.println("成功"+column.getDate());
			System.out.println("成功"+column.getUser_id());
			System.out.println("成功"+column.getAuthor());
			System.out.println("成功"+column.getRole());
			System.out.println("成功"+column.getContents());
			System.out.println("成功"+column.getNo());
		
			rowUpdated = preState.executeUpdate()>0;
			return rowUpdated;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			try {
				JdbcUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//select column by no
	public Column selectByArticleNo(int article_no) throws SQLException {
		Column column = null;
		String sql="select * from article where article_no=?";
		try {
			conn=createConnection();
			PreparedStatement preState = conn.prepareStatement(sql);
			preState.setInt(1, article_no);
			System.out.println(article_no);
			ResultSet rs = preState.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("article_no");
				String date = rs.getString("publish_time");
				int user_id = rs.getInt("user_id");
				String author= rs.getString("author");
				String role = rs.getString("role");
				String contents=rs.getString("contents");
				column= new Column(no, date, user_id, author,role,contents);}			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return column;
	}
	//select column
	public List<Column> selectAllColumns() throws SQLException{
		try {
			conn = createConnection();
			ArrayList<Column> columns = new ArrayList<>();
			String sql = "select * from article";
			PreparedStatement preState = conn.prepareStatement(sql);
			ResultSet rs = preState.executeQuery();
			while(rs.next()) {
				int no = rs.getInt(1);
				String date = rs.getString(2);
				int user_id = rs.getInt(3);
				String author = rs.getString(4);
				String role = rs.getString(5);
				String contents = rs.getString(6);
				Column col = new Column(no, date, user_id, author, role, contents);
				columns.add(col);
			}
			rs.close();
			preState.close();
			return columns;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}	finally {
			try {
				JdbcUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	public int deleteColumnByNo(int article_no) {
		int rowDeleted = 0;
		try {
			conn = createConnection();
			String sql="delete from article where article_no=?";
			PreparedStatement preState = conn.prepareStatement(sql);
			preState.setInt(1, article_no);
			rowDeleted=preState.executeUpdate();
			preState.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rowDeleted; 
	}
}
