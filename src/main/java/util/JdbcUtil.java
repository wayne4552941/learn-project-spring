package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.management.Query;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.ibatis.executor.loader.ResultLoaderMap.LoadPair;
import org.hibernate.cfg.annotations.QueryBinder;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.trigyn.jws.dbutils.service.DataSourceFactory;

public class JdbcUtil {
	private static DataSource dataSource;
	
	static {
		try {
			
			FileInputStream in = new FileInputStream("C:\\github\\learn-project\\src\\main\\java\\druid.properties");
//			FileInputStream in = new FileInputStream("D:\\webgit\\teamproject\\learn-project\\src\\main\\java\\druid.properties");
			//InputStream is = JdbcUtil.class.getClassLoader().getSystemResourceAsStream("druid.properties");   //ClassLoader.getSystemResourceAsStream("druid.properties");
//			FileInputStream in = new FileInputStream("C:\\Users\\ASUS\\Desktop\\WayneCode\\JAVA\\JAVAworkspace\\learn-project\\druid.properties");
//			FileInputStream in = new FileInputStream("C:\\Users\\ACER\\Desktop\\github\\learn-project\\src\\main\\java\\druid.properties");
//			FileInputStream in = new FileInputStream("D:/github/learn-project/src/main/java/druid.properties");
			//FileInputStream in = new FileInputStream("druid.properties");
			
			Properties p = new Properties();
			p.load(in);
		
			dataSource = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		if(!connection.isClosed()) {
			System.out.println("登入成功!!!!!!!!!!!");
		}
		return connection;
	}
	public static void closeConnection(Connection con) throws SQLException {
		if(con != null) {
			con.close();
		}
	}
	
}
