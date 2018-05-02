package org.rabbit.mvc.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.rabbit.mvc.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class HibernateSpitterRepository implements SpitterRepository {
    @Autowired
    public SessionFactory sessionFactory;

    public HibernateSpitterRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();


    }
    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);

        return new Spitter(
                (Long)id,
                spitter.getUserName(),
                spitter.getPassword(),
                spitter.getFirstName(),
                spitter.getLastName(),
                spitter.getEmail()
        );
    }

    public Spitter findOne(long id) {
        return (Spitter) currentSession().get(Spitter.class, id);
    }

    public Spitter findByUsername(String username) {
        return (Spitter) currentSession().createCriteria(Spitter.class)
                .add(Restrictions.eq("username", username))
                .list().get(0);
    }

    public List<Spitter> findAll() {
        return (List<Spitter>) currentSession().createCriteria(Spitter.class).list();
    }

}
