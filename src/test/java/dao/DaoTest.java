package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import bean.CartItem;
import bean.CourseBean;
import cartdao.BaseDAO;

import dao.*;
import cartdao.impt.CartDaoImpt;
import cartdao.impt.OrderDaoImpt;

import ecpay.payment.integration.AllInOne;

class DaoTest {

	
	
	
	@Test
	void testQueryOb() {
		BaseDAO bas = new BaseDAO();
		String sql = " select count(*) from Cart;";
		Object Ob = bas.QueryForObject(sql);
		System.out.println(Ob);
		Date date = new Date();
		System.out.println(date.getTime());
	}
	
	@Test
	void testQueryUser1() {
		OrderDaoImpt daoImpt = new OrderDaoImpt();
		String sql = "select order_id from Order_user where user_id = ? and status = 2";
//		Object[] queryUser = daoImpt.queryUser(1);
//		System.out.println(queryUser[1]);
//		System.out.println(Arrays.toString(queryUser));
	}
	@Test
	void ecpay() {
		AllInOne allInOne = new AllInOne("");

System.out.println((int)(Math.random()*10000));
	}

}
