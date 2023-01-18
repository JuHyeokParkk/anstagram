package com.project.anstagram.posts.repository;

import com.project.anstagram.posts.domain.Posts;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PostsRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Posts posts) {
        em.persist(posts);
        return posts.getId();
    }

    public Posts findById(Long id) {
        return em.find(Posts.class, id);
    }


}
