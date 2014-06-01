package com.adarrivi.spring.solution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.adarrivi")
@EnableTransactionManagement
public class DaoConfiguration {

    private static final String ENTITIES_PACKAGE = "com.adarrivi";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost/spring_training_exercise");
        datasource.setUsername("training");
        datasource.setPassword("training");
        return datasource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return txManager;
    }

    @Bean
    public JpaVendorAdapter hibernateVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter hibernateVendorAdapter, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(ENTITIES_PACKAGE);
        entityManagerFactory.setJpaVendorAdapter(hibernateVendorAdapter);
        Properties properties = new Properties();
        properties.put("hibernate.connection.autocommit", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.show_sql", false);
        properties.put("use_sql_comments", true);
        entityManagerFactory.setJpaProperties(properties);
        return entityManagerFactory;
    }

}
