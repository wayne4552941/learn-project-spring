package fourth.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


//相當於Web.xml的Java程式組態
public class WebApplinitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override //相當於beans.config.xml
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootAppConfig.class};
		//return null;
	}

	@Override //相當於mvc-servlet.xml
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebAppConfig.class};
		//return null;
	}

	@Override //相當於DispatcherServlet url-pattern
	protected String[] getServletMappings() {
		return new String[] {"/"};
		//return null;
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8",true);
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] {characterEncodingFilter};
	}
	
}
