package com.java.spring.security.examples.authentication.application.config;

import com.java.spring.security.examples.authentication.controller.security.ControllerSecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.java.spring.security.examples.authentication.controller.security.repository")
@Import(ControllerSecurityConfiguration.class)
public class Config {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        ds.setDriverClassName("org.h2.Driver");
        ds.setUsername("sa");
        ds.setPassword("123");
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
        //hibernateAdapter.setGenerateDdl(true);
        hibernateAdapter.setShowSql(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(hibernateAdapter);
        factory.setPackagesToScan("com.java.spring.security.examples.authentication.controller.security.repository.model");
        factory.setDataSource(dataSource());
        return factory;
    }
}
