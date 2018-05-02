package org.rabbit.mvc.config;

import org.rabbit.mvc.Spitter;
import org.rabbit.mvc.Spittle;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"org.rabbit.mvc.config", "org.rabbit.mvc.data"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
public class RootConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setPackagesToScan(new String[]{"org.rabbit.mvc.data"});
        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        sfb.setHibernateProperties(props);
        sfb.setAnnotatedClasses(new Class<?>[]{Spitter.class, Spittle.class});
        return sfb;
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("spitterPU");
        return emfb;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource datasource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(datasource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        return emfb;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.HSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
        return adapter;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }
}
