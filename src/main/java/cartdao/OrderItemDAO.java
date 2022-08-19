package cartdao;

import java.util.List;

import bean.CartItem;
import bean.OrderItem;

public interface OrderItemDAO {
	public void addOrderItem(String orderID,List<CartItem> cart);
	//public void deleteOrderItem(String orderID);
	public List<OrderItem> orderItemList(String orderID);
}
