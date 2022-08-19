package cartdao.impt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.CourseBean;
import cartdao.BaseDAO;
import util.HibernateUtil;
import util.JdbcUtil;



public class CourseDao extends BaseDAO<CourseBean>{
	
	private Connection conn =null;;
	SessionFactory factory = null;
	
	public CourseDao() {
		factory = HibernateUtil.getFactory();
	}
	public Connection createConnection() throws SQLException, ClassNotFoundException {
		//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//String urlStr = "jdbc:sqlserver://localhost:1433;databaseName=project;user=sa;password=P@ssw0rd!;trustServerCertificate=true";
		conn = JdbcUtil.getConnection();

		boolean status = !conn.isClosed();

		if (status) {
			System.out.println("已開啟連線");
			return conn;
		}
		return null;
	}

	public void closeConnection() throws SQLException  {
		if (conn != null) {
			conn.close();
			System.out.println("已關閉連線");
		}
	}
	  
	 
	  
	  public void insertCourse(bean.CourseBean courseData) {
		  try {
			  conn = JdbcUtil.getConnection();
			  String sql = "insert into course values(?,?,?,?,?,?,?,?,?,?,?,?)";
//			  String sql = "insert into course values('"
//					  + courseData.getUser_id()+"','"
//					  + courseData.getSubject_id()+"','"
//                      + courseData.getEducation_id()+"','"
//                	  + courseData.getCourse_name()+"','"
//	                  + courseData.getCourse_introduction()+"','"
//                      + courseData.getCourse_price()+"','"
//                      + courseData.getCourse_duration()+"','"
//                      + courseData.getEnrollment()+"','" 
//                      + courseData.getCourse_date()+"','"
//                      + courseData.getLecturer_name()+"','"
//                      + courseData.getLecturer_email()+"','"
//                      + courseData.getCourse_picture()+ "')";
//			  Statement statement = conn.prepareStatement(sql);
//			  int insertcount = statement.executeUpdate(sql);
			  QueryRunner queryRunner = new QueryRunner();
			  queryRunner.update(conn,sql,courseData.getUser_id(),courseData.getSubject_id(),courseData.getEducation_id(),courseData.getCourse_name(),courseData.getCourse_introduction(),
					  courseData.getCourse_price(),courseData.getCourse_duration(),courseData.getEnrollment(),courseData.getCourse_date(),courseData.getLecturer_name(),courseData.getLecturer_email(),courseData.getCourse_picture());
//		      if (insertcount >= 1) return true;
//		      else                  return false;
//			  
		  }
		  catch (Exception e) {
			    System.err.println("新增學生資料時發生錯誤:" + e);
				
		    }finally {
		    	try {
					JdbcUtil.closeConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		
	  }
	  //查詢全部
	  public List<CourseBean> selectAll() {
	        List<CourseBean> list = new ArrayList<>();
	        String sql = "select * from course";
	        try {
	        	conn = createConnection();
	            PreparedStatement prestatement = conn.prepareStatement(sql);
	            ResultSet resultSet = prestatement.executeQuery();
	            while (resultSet.next()) {
	            	CourseBean cb = new CourseBean();
	            	cb.setCourse_id(resultSet.getInt("course_id"));
	            	cb.setUser_id(resultSet.getInt("user_id"));
	            	cb.setSubject_id(resultSet.getInt("subject_id"));
	            	cb.setEducation_id(resultSet.getInt("education_id"));
	            	cb.setCourse_name(resultSet.getString("course_name"));
	            	cb.setCourse_introduction(resultSet.getString("course_introduction"));
	            	cb.setCourse_price(resultSet.getDouble("course_price"));
	            	cb.setCourse_duration(resultSet.getString("course_duration"));
	            	cb.setEnrollment(resultSet.getInt("enrollment"));
	            	cb.setCourse_date(resultSet.getString("course_date"));
	            	cb.setLecturer_name(resultSet.getString("lecturer_name"));
	            	cb.setLecturer_email(resultSet.getString("lecturer_email"));
	            	cb.setCourse_picture(resultSet.getString("course_picture"));
	                
	                list.add(cb);
	                
	            }resultSet.close();
	             prestatement.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
				try {
					JdbcUtil.closeConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        
	        return list;
	    }
	  
	//透過課程編號查詢
//	  public CourseBean selectById(int course_id) throws SQLException {
//		  Session session = factory.getCurrentSession();
//			try {
//				CourseBean courseBean = session.get(CourseBean.class, course_id);
//				return courseBean;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return null;
//	  }
	  public CourseBean selectById(int course_id) throws SQLException {
		  
		  String sql = "select * from course where course_id = ?";
		  CourseBean cb = new CourseBean();
		  try {
			  conn = createConnection();
			  PreparedStatement pst = conn.prepareStatement(sql);
			  pst.setInt(1, course_id);
			  
			  ResultSet resultSet = pst.executeQuery();
			  
			  while(resultSet.next()) {
				  cb.setCourse_id(resultSet.getInt("course_id"));
				  cb.setUser_id(resultSet.getInt("user_id"));
				  cb.setSubject_id(resultSet.getInt("subject_id"));
				  cb.setEducation_id(resultSet.getInt("education_id"));
				  cb.setCourse_name(resultSet.getString("course_name"));
				  cb.setCourse_introduction(resultSet.getString("course_introduction"));
				  cb.setCourse_price(resultSet.getDouble("course_price"));
				  cb.setCourse_duration(resultSet.getString("course_duration"));
				  cb.setEnrollment(resultSet.getInt("enrollment"));
				  cb.setCourse_date(resultSet.getString("course_date"));
				  cb.setLecturer_name(resultSet.getString("lecturer_name"));
				  cb.setLecturer_email(resultSet.getString("lecturer_email"));
				  cb.setCourse_picture(resultSet.getString("course_picture"));
			  }
			  resultSet.close();
			  pst.close();
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
		  
		  return cb;
		  
	  }
	        
	  //透過課程名稱查詢
	  public List<CourseBean> selectByCourseName(String keyword) throws ClassNotFoundException {
		  List<CourseBean> list = new ArrayList<>();
	        String sql = "select * from course where course_name like ?";
	        try {
	        	conn = createConnection();
	            PreparedStatement prestatement = conn.prepareStatement(sql);
	            prestatement.setString(1, "%"+ keyword + "%");
	            ResultSet resultSet = prestatement.executeQuery();
	            while (resultSet.next()) {
	            	CourseBean cb = new CourseBean();
	            	cb.setCourse_id(resultSet.getInt("course_id"));
	            	cb.setUser_id(resultSet.getInt("user_id"));
	            	cb.setSubject_id(resultSet.getInt("subject_id"));
	            	cb.setEducation_id(resultSet.getInt("education_id"));
	            	cb.setCourse_name(resultSet.getString("course_name"));
	            	cb.setCourse_introduction(resultSet.getString("course_introduction"));
	            	cb.setCourse_price(resultSet.getDouble("course_price"));
	            	cb.setCourse_duration(resultSet.getString("course_duration"));
	            	cb.setEnrollment(resultSet.getInt("enrollment"));
	            	cb.setCourse_date(resultSet.getString("course_date"));
	            	cb.setLecturer_name(resultSet.getString("lecturer_name"));
	            	cb.setLecturer_email(resultSet.getString("lecturer_email"));
	            	cb.setCourse_picture(resultSet.getString("course_picture"));
	                
	                list.add(cb);
	                
	            }resultSet.close();
	             prestatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	try {
					JdbcUtil.closeConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	       
	        return list;
	    }
	  
	  //修改資料
	  public int update(int course_id, int user_id, int subject_id, int education_id, String course_name, String course_introduction,
				double course_price, String course_duration, int enrollment, String course_date, String lecturer_name,
				String lecturer_email, String course_picture) throws ClassNotFoundException {
	       // String sql = "update course set user_id = ? set subject_id = ? set education_id = ? set course_name = ? set course_introduction = ? set course_price = ? set course_duration = ?"
	        	//	+ "set enrollment = ? set course_date = ? set lecturer_name = ? set lecturer_email = ? set course_picture = ? where course_id = ?";
	        String sql = "update course set user_id = ?, subject_id = ?, education_id = ? ,course_name = ? , course_introduction = ?, course_price = ? , course_duration = ?"
	        		+ ", enrollment = ? , course_date = ? , lecturer_name = ? , lecturer_email = ? , course_picture = ? where course_id = ?";
	        int result = 0;
	        try {
	        	conn = createConnection();
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setInt(1, user_id);
	            statement.setInt(2, subject_id);
	            statement.setInt(3, education_id);
	            statement.setString(4, course_name);
	            statement.setString(5, course_introduction);
	            statement.setDouble(6, course_price);
	            statement.setString(7, course_duration);
	            statement.setInt(8, enrollment);
	            statement.setString(9, course_date);
	            statement.setString(10, lecturer_name);
	            statement.setString(11, lecturer_email);
	            statement.setString(12, course_picture);
	            statement.setInt(13, course_id);
	            result = statement.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
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
	  public void updateEnrollment(int enrollment,int id) {
		  // String sql = "update course set user_id = ? set subject_id = ? set education_id = ? set course_name = ? set course_introduction = ? set course_price = ? set course_duration = ?"
		  //	+ "set enrollment = ? set course_date = ? set lecturer_name = ? set lecturer_email = ? set course_picture = ? where course_id = ?";
		  String sql = "update course set enrollment = ?  where course_id = ?";
		  int result = 0;
		  try {
			  conn = JdbcUtil.getConnection();
			  update(sql, enrollment,id);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }finally {
			  try {
					JdbcUtil.closeConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		  
	  }
	  
	//透過課程編號刪除 
	  public int deleteByCourseId(int course_id) throws ClassNotFoundException {
	        String sql = "delete from course where course_id = ?";
	        int result = 0 ;
	        try {
	        	conn = createConnection();
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setInt(1, course_id);
	            result = statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
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
	  
	//透過課程名稱刪除 
//	  public int deleteByCourseName(String coursename) {
//	        String sql = "delete from course where course_name = ?";
//	        int result = 0 ;
//	        try {
//	            PreparedStatement statement = conn.prepareStatement(sql);
//	            statement.setString(1, coursename);
//	            result = statement.executeUpdate();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	        return result;
//	    }

	}



