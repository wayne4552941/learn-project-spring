package cartdao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import util.JdbcUtil;

public class BaseDAO<T> {
	private QueryRunner queryRunner = new QueryRunner();
	public void update(String sql,Object...o) {
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			queryRunner.update(con,sql,o);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public T QueryForOne(String sql,Class t,Object...o) {
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			T query = queryRunner.query(con, sql,new BeanHandler<T>(t),o);
			return query;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public List<T> QueryForList(String sql,Class t,Object...o) {
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			List<T> query = queryRunner.query(con, sql, new BeanListHandler<T>(t),o);
			return query;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public <X> X QueryForObject(String sql,Object...o) {
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			X query = queryRunner.query(con, sql, new ScalarHandler<X>(),o);
			return query;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public List<Object[]> QueryForObjectArray(String sql,Object...o) {
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			 List<Object[]> query = queryRunner.query(con, sql, new ArrayListHandler(),o);
			return query;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
