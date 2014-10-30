package com.list.app.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Author : Mukul.Sharma
 */

public class WebAppInitializer implements WebApplicationInitializer {

	/**
	 * To load SecurityConfig in existing ApplicationInitializer.
	 */
	// @Override
	// protected Class<?>[] getRootConfigClasses() {
	// return new Class[] { RepositoryConfig.class, SecurityConfig.class,
	// ToDoAppConfig.class };
	// }
	//
	// @Override
	// protected Class<?>[] getServletConfigClasses() {
	//
	// return new Class[] { WebMvcConfig.class };
	// }
	//
	// @Override
	// protected String[] getServletMappings() {
	// return new String[] { "/" };
	// }

	// Method 2 :

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.setConfigLocation("com.list.app.config");
		servletContext.addListener(new ContextLoaderListener(appContext));
		// appContext.register(WebAppConfig.class);

		appContext.setServletContext(servletContext);

		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);

	}
}
