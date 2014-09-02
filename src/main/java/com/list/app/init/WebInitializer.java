package com.list.app.init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.list.app.config.WebAppConfig;

/**
 * Author : Mukul.Sharma
 */

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return getServletConfigClasses();
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { WebAppConfig.class };
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
