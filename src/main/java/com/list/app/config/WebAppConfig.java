package com.list.app.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Author : Mukul.Sharma
 */

@Configuration
@ComponentScan("com.list.app")
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {

	/*
	 * View Resolver -> Template Engine -> Template Resolver
	 */

	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".html");
		// NB, selecting HTML5 as the template mode.
		resolver.setTemplateMode("HTML5");
		resolver.setCacheable(false);
		// resolver.setOrder(2);
		return resolver;

	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();

		Set<TemplateResolver> templateResolvers = new HashSet<TemplateResolver>();
		templateResolvers.add(templateResolver());

		engine.setTemplateResolvers(templateResolvers);

		return engine;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[] { "*" });
		viewResolver.setCache(false);
		return viewResolver;
	}

}
