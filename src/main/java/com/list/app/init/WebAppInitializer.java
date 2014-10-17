package com.list.app.init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.list.app.config.RepositoryConfig;
import com.list.app.config.SecurityConfig;
import com.list.app.config.ToDoAppConfig;
import com.list.app.config.WebMvcConfig;

/**
 * Author : Mukul.Sharma
 */

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * To load SecurityConfig in existing ApplicationInitializer.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RepositoryConfig.class, SecurityConfig.class, ToDoAppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}

/**
 * Method 2 :
 * 
 * public class WebInitializer implements WebApplicationInitializer {
 * 
 * @Override public void onStartup(ServletContext servletContext) throws
 *           ServletException {
 * 
 *           AnnotationConfigWebApplicationContext appContext = new
 *           AnnotationConfigWebApplicationContext();
 *           appContext.register(WebAppConfig.class);
 * 
 *           appContext.setServletContext(servletContext);
 * 
 *           Dynamic servlet = servletContext.addServlet("dispatcher", new
 *           DispatcherServlet(appContext)); servlet.addMapping("/");
 *           servlet.setLoadOnStartup(1);
 * 
 *           } }
 **/
