package com.project.anstagram.posts.service;

import com.project.anstagram.posts.domain.Posts;
import com.project.anstagram.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(Posts posts) {
        return postsRepository.save(posts);
    }

    public Posts findById(Long id) {
        return postsRepository.findById(id);
    }


}
