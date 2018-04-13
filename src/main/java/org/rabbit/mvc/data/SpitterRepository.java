package org.rabbit.mvc.data;

import org.rabbit.mvc.Spitter;

public interface SpitterRepository {
    void save(Spitter spitter);
    Spitter findByUsername(String username);
}
