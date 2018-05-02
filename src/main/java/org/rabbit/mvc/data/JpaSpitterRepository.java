package org.rabbit.mvc.data;

import org.rabbit.mvc.Spitter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class JpaSpitterRepository implements SpitterRepository{

    @PersistenceContext
    private EntityManager em;

    public void addSpitter(Spitter spitter){
        em.persist(spitter);
    }

    public Spitter getSpitterById(long id){
        return em.find(Spitter.class, id);
    }

    public Spitter save(Spitter spitter) {
        return em.merge(spitter);
    }

    public Spitter findByUsername(String username){
        return em.find(Spitter.class, username);
    }

}
