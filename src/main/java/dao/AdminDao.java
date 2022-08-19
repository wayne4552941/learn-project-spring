package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MemberBean;
import util.JdbcUtil;

public class AdminDao {

	private String url = "jdbc:sqlserver://localhost:1433;databaseName=e-learning;trustServerCertificate=true";
	private String user = "sa";
	private String password = "p@ssw0rd!";
	
	private static final String insertUser = "insert into member(nick,account,password,status,name,img,sex,birthday,cellphone,email,joinDate) values (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String selectUserByAccount = "select * from member where account=?";
	private static final String selectUser = "select * from  member ";
	private static final String deleteUser = "delete from member where account=?;";
	private static final String updateUser = "update member set nick=?,account=?,password=?,status=?,name=?,img=?,sex=?,birthday=?,cellphone=?,email=?,joinDate=? where user_id=?";
	
	
	
	
	public AdminDao() {
		
	}
	
	//連線資料庫
	protected Connection getConnection() {
		Connection connection = null;
		
			try {
				connection = JdbcUtil.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return connection;
	}
	//新增使用者(沒有新增user_id)
	public boolean insertUser(MemberBean userBean) throws SQLException {
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertUser);) {
			preparedStatement.setString(1, userBean.getNick());
			preparedStatement.setString(2, userBean.getAccount());
			preparedStatement.setString(3, userBean.getPassword());
			preparedStatement.setString(4, String.valueOf(userBean.getStatus()));
			preparedStatement.setString(5, userBean.getName());
			preparedStatement.setString(6, "img/"+userBean.getImg());
			preparedStatement.setString(7, userBean.getSex());
			preparedStatement.setString(8, userBean.getBirthday());
			preparedStatement.setString(9, userBean.getCellphone());
			preparedStatement.setString(10, userBean.getEmail());
			preparedStatement.setString(11, userBean.getJoinDate());
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//查詢所有會員
	public List<MemberBean> selectAllMembers(){
		List<MemberBean> listMembers=new  ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectUser);) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MemberBean mb=new MemberBean();
				mb.setUser_id(resultSet.getInt("user_id"));
				mb.setNick(resultSet.getString("nick"));
				mb.setAccount(resultSet.getString("account"));
				mb.setPassword(resultSet.getString("password"));
				mb.setStatus(resultSet.getInt("status"));
				mb.setName(resultSet.getString("name"));
				mb.setImg(resultSet.getString("img"));
				mb.setSex(resultSet.getString("sex"));
				mb.setBirthday(resultSet.getString("birthday"));
				mb.setCellphone(resultSet.getString("cellphone"));
				mb.setEmail(resultSet.getString("email"));
				mb.setJoinDate(resultSet.getString("joinDate"));
				
				listMembers.add(mb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMembers;
	}
	
	//透過帳號刪除
	public boolean deleteUser(String account) {
		boolean  rowDelete = false;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(deleteUser);) {
			preparedStatement.setString(1, account);
			  rowDelete=preparedStatement.executeUpdate() >0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowDelete;
	}
	
	//透過帳號查詢(這邊下面沒有account)
	public  MemberBean selectUserByAccount(String account) throws SQLException {
		MemberBean mb=null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectUserByAccount);) {
			preparedStatement.setString(1, account);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				
				int user_id=resultSet.getInt("user_id");
				String nick=resultSet.getString("nick");
				String password=resultSet.getString("password");
				int status=resultSet.getInt("status");
				String name=resultSet.getString("name");
				String img=resultSet.getString("img");
				String sex=resultSet.getString("sex");
				String birthday=resultSet.getString("birthday");
				String cellphone=resultSet.getString("cellphone");
				String email=resultSet.getString("email");
				String joinDate=resultSet.getString("joinDate");
				
				mb=new MemberBean(user_id,nick,account,password,status,name,img,sex,birthday,cellphone,email,joinDate);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mb;
	}
	
	//修改
	public boolean updateUser(MemberBean userBean) throws SQLException {
		boolean rowUpdate;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateUser);) {
			preparedStatement.setString(1, userBean.getNick());
			preparedStatement.setString(2, userBean.getAccount());
			preparedStatement.setString(3, userBean.getPassword());
			preparedStatement.setInt(4, userBean.getStatus());
			preparedStatement.setString(5, userBean.getName());
			preparedStatement.setString(6, "img/"+userBean.getImg());
			preparedStatement.setString(7, userBean.getSex());
			preparedStatement.setString(8, userBean.getBirthday());
			preparedStatement.setString(9, userBean.getCellphone());
			preparedStatement.setString(10, userBean.getEmail());
			preparedStatement.setString(11, userBean.getJoinDate());
			preparedStatement.setInt(12, userBean.getUser_id());
			
			rowUpdate=preparedStatement.executeUpdate()>0;
		}
		
		return rowUpdate;
		
	}
}
