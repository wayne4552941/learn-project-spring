package cartdao.impt;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.CartItem;
import bean.CourseBean;
import cartdao.BaseDAO;
import cartdao.CartDAO;
import util.HibernateUtil;



public class CartDaoImpt extends BaseDAO<CartItem> implements CartDAO {
	
	SessionFactory factory = null;
	
	public CartDaoImpt() {
		factory = HibernateUtil.getFactory();
	}
	
	@Override
	public List<CartItem> carList(int id) {
		Session session = factory.getCurrentSession();
		try {
			List<CartItem> resultList = session.createQuery("from CartItem where user_id = :id",CartItem.class)
					.setParameter("id", id).getResultList();
			return resultList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addCart(CartItem cart) {
		Session session = factory.getCurrentSession();
		try {
			session.save(cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCart(int paseInt) {
		Session session = factory.getCurrentSession();
		try {
			CartItem cartItem = session.get(CartItem.class, paseInt);
			session.delete(cartItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void clearCard(int userId) {
		Session session = factory.getCurrentSession();
		try {
			session.createQuery("delete from CartItem where user_id = :id")
			.setParameter("id", userId).executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public List getCountTotal(List<CartItem> cart) {
		List list = new ArrayList();
		int count = 0;
		double price = 0;
		for(CartItem item : cart) {
			count += item.getCount();
			price += item.getPrice();
		}
		list.add(price);
		list.add(count);
		return list;
	}
}
