package org.rabbit.mvc.data;

import org.rabbit.mvc.Spitter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PostAuthorize;

@Configuration
@ComponentScan
public interface SpitterRepository {
    Spitter save(Spitter spitter);

    @PostAuthorize("returnObject.username == principal.username")
    Spitter findByUsername(String username);
}
