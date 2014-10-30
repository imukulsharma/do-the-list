package com.list.app.config;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Author : Mukul.Sharma
 */

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class RepositoryConfig {

	@Autowired
	Environment env;

	@Bean
	public DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(env.getProperty("jdbc.datasource.url"));
		ds.setDriverClassName(env.getProperty("jdbc.datasource.driverClassName"));
		ds.setUsername(env.getProperty("jdbc.datasource.username"));
		ds.setPassword(env.getProperty("jdbc.datasource.password"));
		ds.setValidationQuery("SELECT 1");
		ds.setValidationQueryTimeout(5);

		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(getDataSource());
		emf.setPackagesToScan("com.list.app");

		Map<String, Object> opts = emf.getJpaPropertyMap();
		opts.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		opts.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

		HibernateJpaVendorAdapter jpa = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(jpa);

		return emf;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
