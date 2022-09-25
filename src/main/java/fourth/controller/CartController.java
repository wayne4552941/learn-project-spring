package fourth.controller;



import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import bean.CartItem;
import bean.OrderUser;

@Controller
@Transactional
public class CartController {
	
	@Autowired
	CartItem cartItem;
	
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@GetMapping("/login")
	public String login() throws SQLException {
		Session session = sessionFactory.getCurrentSession();
		try {
			try {
				List<OrderUser> resultList = session.createQuery("from OrderUser ",OrderUser.class)
				.getResultList();
				System.out.println(resultList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Login";
	}
	
	
}
