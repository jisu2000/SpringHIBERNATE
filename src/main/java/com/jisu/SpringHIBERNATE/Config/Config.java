package com.jisu.SpringHIBERNATE.Config;

import java.sql.DriverManager;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@ComponentScan(basePackages = "com.jisu.SpringHIBERNATE.Dao")
public class Config {

    @Bean("ds")
    public DataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/springjdbc");
        ds.setUsername("root");
        ds.setPassword("Jisu@2000");
        return ds;

    }

    @Bean("factory")
    public LocalSessionFactoryBean getfacFactoryBean() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(getDataSource());
        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        prop.setProperty("hibernate.show_sql", "false");
        prop.setProperty("hibernate.hbm2ddl.auto", "update");

        factory.setHibernateProperties(prop);

        factory.setAnnotatedClasses(com.jisu.SpringHIBERNATE.Model.Student.class);

        return factory;

    }

    @Bean("template")
    public HibernateTemplate getTemplate() {
        HibernateTemplate template = new HibernateTemplate();
        template.setSessionFactory(getfacFactoryBean().getObject());
        return template;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

}
