package com.project.anstagram.posts.repository;

import com.project.anstagram.posts.domain.Like;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LikeRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Like like) {
        em.persist(like);
        return like.getId();
    }

    public Like findById(Long id) {
        return em.find(Like.class, id);
    }

    public void remove(Like like) {
        em.remove(like);
    }


}
