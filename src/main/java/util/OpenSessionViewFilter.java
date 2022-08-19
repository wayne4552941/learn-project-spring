package util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Servlet Filter implementation class OpenSessionViewFilter
 */
@WebFilter(urlPatterns = "/CartServlet")
public class OpenSessionViewFilter extends HttpFilter implements Filter {
     
	private Session session;
    /**
     * @see HttpFilter#HttpFilter()
     */
    public OpenSessionViewFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			SessionFactory factory = HibernateUtil.getFactory();
			session = factory.getCurrentSession();
			//session = factory.openSession();
			session.beginTransaction();
			System.out.println("Benging Transaction");
			
			chain.doFilter(request, response);
			
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
				System.out.println("**********************************************************************");
				session.close();
			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
