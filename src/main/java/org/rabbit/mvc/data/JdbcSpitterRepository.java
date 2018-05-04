package org.rabbit.mvc.data;

import org.rabbit.mvc.Spitter;
import org.rabbit.mvc.config.DataSourceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Import(DataSourceConfiguration.class)
@Repository
public class JdbcSpitterRepository implements SpitterRepository {

    private JdbcOperations jdbcOperations;

    String InSERT_SPITTER = "";
    String SELECT_SPITTER_BY_ID = "";

    @Autowired
    public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    public Spitter save(Spitter spitter) {
        return new Spitter();
    }

    public Spitter findByUsername(String username) {
        return new Spitter();
    }

    public void addSpitter(Spitter spitter) {
        jdbcOperations.update(InSERT_SPITTER, spitter.getUserName(), spitter.getPassword());
    }

    public Spitter findOne(Long id) {
        return jdbcOperations.queryForObject(
                SELECT_SPITTER_BY_ID,
                (rs, rowNum) -> {
                    return new Spitter(
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("email")
                    );
                },
                id);

    }



}

