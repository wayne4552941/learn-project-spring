package service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;

import bean.CartItem;
import bean.CourseBean;
import bean.OrderItem;
import bean.OrderUser;
import ecpay.payment.integration.domain.AioCheckOutALL;
import mail.JavaMail;
import util.WebUtils;

public interface OrderServiceInterface {
	
	public String ecPay(String orderId, String url, AioCheckOutALL obj);
		

	public List<CourseBean> searchLearn(int id) throws SQLException;
		

	public List<OrderUser> orderSearch(String search) ;
		

	public List<OrderItem> orderItemList(String cartId);
		

	public OrderUser orderItemUser(String id);
		

	public void deleteOrder(String id);
	

	public void updateOrder(int userStatus, String status, String orderId) throws SQLException;
	
		
	public void addOrder(int id);
	
	public List<OrderUser> orderList(int id, int status) ;
		
}
