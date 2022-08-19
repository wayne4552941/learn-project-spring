package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;

/**
 * Application Lifecycle Listener implementation class SessionFactoryListener
 *
 */
@WebListener
public class SessionFactoryListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SessionFactoryListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	HibernateUtil.closeSessionFactory();
    	System.out.println("SessionFactory Closed!!");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	SessionFactory factory = HibernateUtil.getFactory();
    	System.out.println("SessionFactory created!!");
    }
	
}
