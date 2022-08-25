package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import bean.CartItem;
import bean.CourseBean;
import util.WebUtils;

public interface CartServiceInterface {
	
	public String getCount(int id) throws IOException ;
		

	public void cartClear(int userId) ;
		

	public void cartAdd(String cartId,int id) throws SQLException;
		

	public void cartDelete(String id);
		

	public List<CartItem> cartList(int id);
		
	public List<CourseBean> courseList();
		
	public List getCountPriceTotal(List<CartItem> cart);
		
}
