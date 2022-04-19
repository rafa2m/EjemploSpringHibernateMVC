package com.example.springhibernatemvc;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataBaseConfig {
	@Autowired
	private Environment env;

	private DataSource dataSource;

	private LocalContainerEntityManagerFactoryBean entityManager;
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		entityManager =
				new LocalContainerEntityManagerFactoryBean();
		//se asigna el origen de datos al gestor de entidades
		entityManager.setDataSource(dataSource());
		entityManager.setPackagesToScan(env.getProperty("entityManager.packagesToScan"));
		
		HibernateJpaVendorAdapter hibernateAdapter = 
				new HibernateJpaVendorAdapter();
		//se asigna el ORM al gestor de entidades
		entityManager.setJpaVendorAdapter(hibernateAdapter);
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));		
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		
		entityManager.setJpaProperties(properties);
		
		return entityManager;
	}
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(this.entityManager.getObject());
		return transactionManager;
	}
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTraslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
