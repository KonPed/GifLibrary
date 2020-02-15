package com.konped.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("application.properties")
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean localSessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        /* Uncomment if you prefer to load properties directly from hibernate.cfg.xml file. */
//        Resource config = new ClassPathResource("hibernate.cfg.xml");
//        localSessionFactoryBean.setConfigLocation(config);
        localSessionFactoryBean.setPackagesToScan(env.getProperty("giflibrary.entity.packages"));
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        return localSessionFactoryBean;
    }

    /* Implement Datasource from Apache Tomcat for connection pooling. */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("giflib.db.driver"));
        dataSource.setUrl(env.getProperty("giflib.db.url"));
        dataSource.setUsername(env.getProperty("giflib.db.username"));
        dataSource.setPassword(env.getProperty("giflib.db.password"));
        return dataSource;
    }

    /* Hibernate properties */
    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        return hibernateProperties;
    }
}
