package service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CartItem;
import bean.CourseBean;

import bean.OrderItem;
import bean.OrderUser;
import cartdao.impt.CartDaoImpt;
import cartdao.impt.CourseDao;
import cartdao.impt.OrderDaoImpt;
import cartdao.impt.OrderItemDaoImpt;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import mail.JavaMail;
import util.WebUtils;

public class OrderService  implements OrderServiceInterface{

	OrderDaoImpt order = new OrderDaoImpt();
	CartDaoImpt cartDaoImpt = new CartDaoImpt();
	OrderItemDaoImpt orderItem = new OrderItemDaoImpt();
	CourseDao courseDao = new CourseDao();
	static AllInOne allInOne = new AllInOne("");

	@Override
	public String ecPay(String orderId, String url, AioCheckOutALL obj) {
		OrderUser orderUser = order.orderUser(orderId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateToStr = dateFormat.format(orderUser.getDate());
		List<OrderItem> orderItemList = orderItem.orderItemList(orderId);
		String itemName = "";
		int i = 0;
		for (OrderItem item : orderItemList) {
			if (i == orderItemList.size() - 1) {
				itemName += item.getName();
			} else {
				itemName += item.getName() + "#";
				i++;
			}
		}

		obj.setMerchantTradeNo(orderId + String.valueOf((int) (Math.random() * 10000)));
		obj.setMerchantTradeDate(dateToStr);
		obj.setTotalAmount(String.valueOf((int) orderUser.getTotoalprice()));
		obj.setTradeDesc("test Description");
		obj.setReturnURL("http://211.23.128.214:5000");
		System.out.println(obj.getReturnURL() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// obj.setReturnURL("http://localhost:8080/learn-project/OrderServlet?command=UPDATE&status=1&orderID="+orderId);
		// obj.setOrderResultURL(url+"?command=UPDATE&status=1&orderID="+orderId);
		obj.setNeedExtraPaidInfo("N");
		obj.setItemName(itemName);
		obj.getPaymentInfoURL();
		obj.getReturnURL();
		System.out.println(obj.getPaymentInfoURL() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		obj.setClientBackURL(url + "?command=UPDATE&status=1&orderID=" + orderId);
		String form = allInOne.aioCheckOut(obj, null);
		return form;
	}

	public static boolean cmprChkMacValue() {
		Hashtable<String, String> dict = new Hashtable<String, String>();
		dict.put("MerchantID", "2000132");
		dict.put("CheckMacValue", "50BE3989953C1734E32DD18EB23698241E035F9CBCAC74371CCCF09E0E15BD61");
		return allInOne.compareCheckMacValue(dict);
	}
	
	@Override
	public List<CourseBean> searchLearn(int id) throws SQLException {
		List<CourseBean> queryUserItem = order.queryUserItem(id);
		return queryUserItem;
	}

	@Override
	public List<OrderUser> orderSearch(String search) {
		search = "%" + search.trim() + "%";
		List<OrderUser> orderSearch = order.orderSearch(search);
		if (orderSearch.size() == 0) {
			return null;
		}
		return orderSearch;
	}
	
	@Override
	public List<OrderItem> orderItemList(String cartId) {
		List<OrderItem> orderItemList = orderItem.orderItemList(cartId);
		return orderItemList;
	}
	
	@Override
	public OrderUser orderItemUser(String id) {
		OrderUser orderUser = order.orderUser(id);
		return orderUser;
	}

	@Override
	public void deleteOrder(String id) {
		order.deleteOrder(id);
	}

	@Override
	public void updateOrder(int userStatus, String status, String orderId) throws SQLException {
		OrderUser orderUser = order.orderUser(orderId);
		orderUser.setStatus(WebUtils.paseInt(status));
		order.updateOrder(orderUser);
		
		if("2".equalsIgnoreCase(status)) {
			List<Integer> orderItemIDList = orderItem.orderItemIDList(orderId);
			for (Integer item : orderItemIDList) {
				CourseBean courseBean = courseDao.select(item);
				orderItem.updateEnrollment(courseBean.getEnrollment() + 1, courseBean.getCourse_id());
			}
		}

		String txt = "<h2>" + "訂單編號: " + orderUser.getOrder_id() + "<br>" + "訂單生成日期: " + orderUser.getDate() + "<br>"
				+ "購買人姓名: " + orderUser.getMemberBean().getName() + "<br>" + "購買人信箱: "
				+ orderUser.getMemberBean().getEmail() + "<br>" + "總金額: " + orderUser.getTotoalprice() + "<h2>";
		JavaMail javaMail = new JavaMail();
		javaMail.setCustomer("ggyy45529441@gmail.com");
		javaMail.setSubject("好學生-EEIT49 線上付款成功!");
		javaMail.setTxt(txt);
		javaMail.sendMail();

	}

	@Override
	public void addOrder(int id) {
		List<CartItem> carList = cartDaoImpt.carList(id);
		order.addOrder(carList);
		cartDaoImpt.clearCard(id);
	}

	@Override
	public List<OrderUser> orderList(int id, int status) {
		List<OrderUser> orderList = null;
		if (status != 3) {
			orderList = order.orderList(id);
			return orderList;
		} else {
			orderList = order.orderList();
			return orderList;
		}
	}

}
