package cartdao;

import java.util.List;

import bean.CartItem;
import bean.OrderUser;

public interface OrderDAO {
	public void addOrder(List<CartItem> cart );
	public void deleteOrder(String orderID);
	public void updateOrder(OrderUser user);
	public List<OrderUser> orderList(int id);
	public OrderUser orderUser(String orderID);
	public List<OrderUser> orderSearch(String search);
}
