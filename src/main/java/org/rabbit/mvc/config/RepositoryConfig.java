package org.rabbit.mvc.config;

import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.rabbit.mvc.data.JdbcSpitterRepository;
import org.rabbit.mvc.data.SpitterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;


public class RepositoryConfig {
    @Bean
    public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcSpitterRepository(jdbcTemplate);
    }

    @Bean
    public SessionFactoryImpl sessionFactory(MetadataImplementor metadata,
                                             SessionFactory.SessionFactoryOptions sessionFactoryOptions,
                                             SessionFactoryObserver observer) {
        return new SessionFactoryImpl(metadata, sessionFactoryOptions, observer);
    }

}
