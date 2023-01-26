package com.project.anstagram.posts.repository;

import com.project.anstagram.user.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(User user) {
        em.persist(user);
        return user.getId();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }
}
