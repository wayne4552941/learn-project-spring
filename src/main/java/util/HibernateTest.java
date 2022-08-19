package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateTest {
	
	static Session session = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SessionFactory factory = HibernateUtil.getFactory();
			session = factory.getCurrentSession();
			//session = factory.openSession();
			session.beginTransaction();
			System.out.println("Benging Transaction");
			
			if(session.isOpen()) {
				System.out.println("連線打開!!!!!!!!!!!!!!!!!!!");
			}else {
				System.out.println("連線關閉!!!!!!!!!!!!!!!!!!!!!!");
			}
			
			session.getTransaction().commit();
			System.out.println("Commit!!");
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			System.out.println("Trasaction Roll BacK!!");
			e.printStackTrace();
		}finally {
			System.out.println("Session back to Connection pool");
			if(session.isOpen()) {
				System.out.println("finally連線打開!!!!!!!!!!!!!!!!!!!");
			}else {
				System.out.println("finally連線關閉!!!!!!!!!!!!!!!!!!!!!!");
			}
		}
	}

}
