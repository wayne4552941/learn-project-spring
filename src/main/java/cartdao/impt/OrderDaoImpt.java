package cartdao.impt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.CartItem;
import bean.CourseBean;
import bean.MemberBean;
import bean.OrderItem;
import bean.OrderUser;
import cartdao.BaseDAO;

import cartdao.impt.*;
import util.HibernateUtil;
import cartdao.OrderDAO;

public class OrderDaoImpt extends BaseDAO<OrderUser> implements OrderDAO {
	
	SessionFactory factory = null;
	
	public OrderDaoImpt() {
		factory = HibernateUtil.getFactory();
	}
	
	OrderItemDaoImpt daoImpt = new OrderItemDaoImpt();

	@Override
	public void addOrder(List<CartItem> cart) {
		Session session = factory.getCurrentSession();
		try {
			int count = 0;
			double price = 0;
			for (CartItem item : cart) {
				count += item.getCount();
				price += item.getCourseBean().getCourse_price();
			}
			MemberBean memberBean = session.get(MemberBean.class, cart.get(0).getMemberBean().getUser_id());
			
			OrderUser order = new OrderUser();
			Date date = new Date();
			String orderID = String.valueOf(date.getTime() + String.valueOf(cart.get(0).getUser_id()));
			order.setOrder_id(orderID);
			//order.setUser_id(cart.get(0).getUser_id());
			order.setDate(date);
			order.setStatus(0);
			order.setTotoalcount(count);
			order.setTotoalprice(price);
			order.setMemberBean(memberBean);
			session.save(order);
			daoImpt.addOrderItem(orderID, cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrder(String orderID) {
		Session session = factory.getCurrentSession();
		try {
			OrderUser orderUser = session.get(OrderUser.class, orderID);
			session.delete(orderUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrder(OrderUser user) {
		Session session = factory.getCurrentSession();
		try {
			OrderUser orderUser = session.get(OrderUser.class, user.getOrder_id());
			orderUser.setStatus(user.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderUser> orderList(int id) {//找用戶的訂單
		Session session = factory.getCurrentSession();
		try {
			List<OrderUser> resultList = session.createQuery("from OrderUser  where user_id = :id",OrderUser.class)
			.setParameter("id", id).getResultList();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<OrderUser> orderList() {
		Session session = factory.getCurrentSession();
		try {
			List<OrderUser> resultList = session.createQuery("from OrderUser ",OrderUser.class)
			.getResultList();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OrderUser orderUser(String orderID) {//找訂單的用戶
		Session session = factory.getCurrentSession();
		try {
			OrderUser orderUser = session.get(OrderUser.class, orderID);
			return orderUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<OrderUser> orderSearch(String search) {
		Session session = factory.getCurrentSession();
		try {
			System.out.println(search);
			List<OrderUser> resultList = session.createQuery("from OrderUser o where o.memberBean.account like :a or o.memberBean.name like :n or o.order_id like :o",OrderUser.class)
			.setParameter("a", search)
			.setParameter("n", search)
			.setParameter("o", search)
			.getResultList();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<CourseBean>  queryUserItem(int userID) throws SQLException {
		Session session = factory.getCurrentSession();
		try {
			List<String> resultList = session.createQuery("select order_id from OrderUser where user_id = :id and status = 2",String.class)
				.setParameter("id", userID)
				.getResultList();
			CourseDao dao = new CourseDao();
			System.out.println(resultList);
			List<CourseBean> courses = new ArrayList<>();
			for (String o : resultList) {
				List<Integer> orderItemIDList = daoImpt.orderItemIDList(o);
				for (Integer item : orderItemIDList) {
					CourseBean course = dao.select(item);
					courses.add(course);
				}
			}
			return courses;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
