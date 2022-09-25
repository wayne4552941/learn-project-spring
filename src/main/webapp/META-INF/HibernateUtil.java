package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class HibernateUtil {
	
	private static final SessionFactory factory = createSessionFactory();
	
	private static SessionFactory createSessionFactory() {
		StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

		SessionFactory factory = new MetadataSources(build).buildMetadata().buildSessionFactory();

		return factory;
	}

	public static SessionFactory getFactory() {
		return factory;
	}
	
	public static void closeSessionFactory() {
		if(factory != null) {
			factory.close();
		}
	}
}
