package com.project.anstagram.posts.repository;

import com.project.anstagram.posts.domain.Likes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LikeRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Likes like) {
        em.persist(like);
        return like.getId();
    }

    public Likes findById(Long id) {
        return em.find(Likes.class, id);
    }

    public void remove(Likes like) {
        em.remove(like);
    }


}
