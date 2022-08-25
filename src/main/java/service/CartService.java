package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.CartItem;
import bean.CourseBean;
import bean.MemberBean;
import cartdao.impt.CartDaoImpt;
import cartdao.impt.CourseDao;
import util.WebUtils;

public class CartService implements CartServiceInterface{
	
	CartDaoImpt cartDaoImpt = new CartDaoImpt();
	CourseDao courseDao = new CourseDao();
	
	@Override
	public String getCount(int id) throws IOException {
		List<CartItem> carList = cartDaoImpt.carList(id);
		List countTotal = cartDaoImpt.getCountTotal(carList);
		Gson gson = new Gson();
		String JsonString = gson.toJson(countTotal);
		return JsonString;
	}

	@Override
	public void cartClear(int userId) {
		cartDaoImpt.clearCard(userId);
	}

	@Override
	public void cartAdd(String cartId,int id) throws SQLException{
		CourseBean course = courseDao.select(WebUtils.paseInt(cartId));
		CartItem cart = new CartItem(0, id, course.getCourse_id(), course.getCourse_name(), 1,
				course.getCourse_price());
		cart.setCourseBean(course);
		cartDaoImpt.addCart(cart);
	}

	@Override
	public void cartDelete(String id){
		cartDaoImpt.deleteCart(WebUtils.paseInt(id));

	}

	@Override
	public List<CartItem> cartList(int id){
		List<CartItem> cartList = cartDaoImpt.carList(id);
		List countTotal = cartDaoImpt.getCountTotal(cartList);
		return cartList;
	}
	
	@Override
	public List<CourseBean> courseList(){
		List<CourseBean> selectAll = courseDao.selectAll();
		return selectAll;
	}
	
	@Override
	public List getCountPriceTotal(List<CartItem> cart) {
		List countTotal = cartDaoImpt.getCountTotal(cart);
		return countTotal;
	}
	
}
