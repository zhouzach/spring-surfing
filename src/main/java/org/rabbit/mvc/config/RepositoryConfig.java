package org.rabbit.mvc.config;

import org.rabbit.mvc.data.JdbcSpitterRepository;
import org.rabbit.mvc.data.SpitterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;


public class RepositoryConfig {
    @Bean
    public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcSpitterRepository(jdbcTemplate);
    }

}
