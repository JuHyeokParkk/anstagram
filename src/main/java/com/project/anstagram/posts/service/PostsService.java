package com.project.anstagram.posts.service;

import com.project.anstagram.posts.domain.Likes;
import com.project.anstagram.posts.domain.Posts;
import com.project.anstagram.posts.repository.PostsRepository;
import com.project.anstagram.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    public Posts findById(Long id) {
        return postsRepository.findById(id);
    }

    public void addPosts(Posts posts, User user) {
            posts.setUser(user);
            user.getPostsList().add(posts);
            postsRepository.save(posts);
    }

    public void addLike(Posts posts, Likes likes) {
        posts.getLikeList().add(likes);
    }

    public void remove(Long postId, User user) {
        Posts posts = postsRepository.findById(postId);

        if(!posts.getUser().equals(user)) {
            return;
        }

        user.getPostsList().remove(posts);

        postsRepository.remove(posts);
    }

    public void update(Long postId, String content) {
        postsRepository.update(postId, content);
    }


}
