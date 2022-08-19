package dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import bean.EduBean;
import bean.ExamBean;
import bean.SubBean;
import util.HibernateUtil;



public class ExamDao implements ExamDaoInterface {
	

	private Session session;
	
	public ExamDao(Session session) {
	
		this.session = session;

	}
	
	@Override
	public ExamBean insert(ExamBean examBean) {

			session.save(examBean);

		
		return examBean;
	}
	
	
	
	@Override
	public List<ExamBean> select(Integer subIdx,Integer eduIdx) {
		
		SubBean subBean = session.get(SubBean.class, subIdx);
		EduBean eduBean = session.get(EduBean.class, eduIdx);
		
		String hql = "from ExamBean where subject = :subIdx and education = :eduIdx";
		
		Query<ExamBean> query = session.createQuery(hql).setParameter("subIdx", subBean)
		 .setParameter("eduIdx", eduBean);
									 
		List<ExamBean> list = query.getResultList();
		
		return list;
	}

	@Override
	public List<ExamBean> selectAll() {
		Query<ExamBean> query= session.createQuery("from ExamBean",ExamBean.class);
		return query.list();
	}

	@Override
	public ExamBean update(Integer updateId, ExamBean examBean) {
		ExamBean rsExBean = session.get(ExamBean.class, updateId);
		System.out.println(examBean);
		
		if(rsExBean != null) {
			try {
				BeanUtils.copyProperties(rsExBean, examBean);
				System.out.println(rsExBean);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			return rsExBean;
		}
		return rsExBean;
	}


	@Override
	public boolean deleteOne(Integer deleteId) {
		ExamBean rsExBean = session.get(ExamBean.class, deleteId);
		
		if(rsExBean!=null) {
			session.delete(rsExBean);
			return true;
		}
		
		return false;
	}
}
