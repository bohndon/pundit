
package com.nbohn.pundit.rest.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Java Persistence API Configuration.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.nbohn.pundit.rest.repository")    // Register Repositories.
@ComponentScan("com.nbohn.pundit.rest.model.entity")                         // Register Entity Beans.
@EnableTransactionManagement
public class JpaConfiguration {

    /**
     * Pooled Data Source
     * @return Pooled Data Source.
     */
    @Bean
    public DataSource dataSource() {
        return dataSourceHikari();
    }

    /**
     * ExampleEntity Manager Factory.
     * @return  ExampleEntity Manager Factory.
     */
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.nbohn.pundit.rest.model");

        // JPA Vendor and Properties.
        emf.setJpaVendorAdapter(jpaVendorAdaptorHibernate());
        emf.setJpaProperties(jpaVendorPropertiesHibernate());

        emf.afterPropertiesSet();
        return emf.getObject();
    }

    /**
     * Platform Transaction Manager.
     * @return Platform Transaction Manager.
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

    /**
     * Persistence Exception Translation Post Processor.
     * @return  Persistence Exception Translation Post Processor.
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    /**
     * Hikari Pooled Data Source.
     * @return Hikari Pooled Data Source.
     */
    @Bean
    public DataSource dataSourceHikari() {
        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:postgresql://pg-ill-vip1-m1.dev.oclc.org:5600/illdb");
//        config.setUsername("wsill_dev01_u");
//        config.setPassword("wsill_dev01_u");
        config.setJdbcUrl("jdbc:h2:mem:test");
        config.setAutoCommit(false);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    /**
     * Hibernate JPA Vendor Adaptor.
     * @return Hiberante JPA Vendor Adaptor.
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdaptorHibernate() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setDatabase(Database.H2);
        return vendorAdapter;
    }

    /**
     * Hibernate Properties.
     * @return Hibernate properties.
     */
    @Bean
    public Properties jpaVendorPropertiesHibernate() {
        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return properties;
    }


    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.yaml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }
}
