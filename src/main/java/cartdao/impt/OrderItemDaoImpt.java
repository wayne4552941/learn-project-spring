package cartdao.impt;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.CartItem;
import bean.OrderItem;
import bean.OrderUser;
import cartdao.BaseDAO;
import cartdao.OrderItemDAO;
import util.HibernateUtil;

public class OrderItemDaoImpt extends BaseDAO<OrderItem> implements OrderItemDAO {


	SessionFactory factory = null;
	
	public OrderItemDaoImpt() {
		factory = HibernateUtil.getFactory();
	}
	
	@Override
	public void addOrderItem(String orderID , List<CartItem> cart) {
		Session session = factory.getCurrentSession();
		try {
			OrderUser orderUser = session.get(OrderUser.class, orderID);
			System.out.println(orderUser);
			for(CartItem item : cart) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder_id(orderID);
				orderItem.setItem_id(item.getItem_id());
				orderItem.setName(item.getItemName());
				orderItem.setCount(item.getCount());
				orderItem.setPrice(item.getPrice());
				orderItem.setOrderUser(orderUser);
				session.save(orderItem);
				orderUser.addorderItems(orderItem);
			}
			//session.save(orderUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<OrderItem> orderItemList(String orderID) {
		Session session = factory.getCurrentSession();
		try {
			List<OrderItem> resultList = session.createQuery("from OrderItem where order_id = :id", OrderItem.class)
			.setParameter("id", orderID).getResultList();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Integer> orderItemIDList(String orderID) {
		Session session = factory.getCurrentSession();
		try {
			List<Integer> resultList = session.createQuery("select item_id from OrderItem where order_id = :id", Integer.class)
			.setParameter("id", orderID)
			.getResultList();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
