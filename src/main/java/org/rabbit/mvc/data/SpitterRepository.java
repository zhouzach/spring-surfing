package org.rabbit.mvc.data;

import org.rabbit.mvc.Spitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public interface SpitterRepository {
    void save(Spitter spitter);
    Spitter findByUsername(String username);
}
