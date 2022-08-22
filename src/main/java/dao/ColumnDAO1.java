package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import bean.ColumnBean;
import util.HibernateUtil;

public class ColumnDAO1 implements ColumnDAOInterface {
	SessionFactory factory;
	private Session session;

	public ColumnDAO1() {
		 factory = HibernateUtil.getFactory();
	}

	@Override
	public boolean insertColumn(ColumnBean column) {
		this.session = factory.getCurrentSession();
		try {
			ColumnBean col = session.get(ColumnBean.class, column.getNo());
			if (col == null) {
				
				session.save(column);
				System.out.println("新增成功~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ColumnBean updateColumn(ColumnBean column) {
		this.session = factory.getCurrentSession();
		System.out.println("Dao Update session.getId為"+ column.getNo());
		ColumnBean col = session.get(ColumnBean.class, column.getNo());
		if (col != null) {

			col.setDate(column.getDate());
			col.setUser_id(column.getUser_id());
			col.setAuthor(column.getAuthor());
			col.setRole(column.getRole());
			col.setContents(column.getContents());
			System.out.println(col.toString());
			session.save(col);
			return col;
		}
		
		return null;
	}

	@Override
	public ColumnBean selectByArticleNo(int article_no) {
		this.session = factory.getCurrentSession();
		return session.get(ColumnBean.class, article_no);

	}

	@Override
	public List<ColumnBean> selectAllColumns() {
		this.session = factory.getCurrentSession();
		Query<ColumnBean> query = session.createQuery("from ColumnBean", ColumnBean.class);
		return query.list();
	}

	@Override
	public boolean deleteColumnByNo(int article_no) {
		this.session = factory.getCurrentSession();
		ColumnBean col = session.get(ColumnBean.class, article_no);
		if (col != null) {
			session.delete(col);
			return true;
		}
		return false;
	}

}
