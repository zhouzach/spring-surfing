package org.rabbit.mvc.data;

import org.rabbit.mvc.Spitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpitterJpaRepository extends JpaRepository<Spitter, Long>{
    Spitter findByUsername(String username);
}
