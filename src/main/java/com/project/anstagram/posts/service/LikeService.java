package com.project.anstagram.posts.service;

import com.project.anstagram.posts.domain.Likes;
import com.project.anstagram.posts.domain.Posts;
import com.project.anstagram.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostsService postsService;

    @Transactional
    public void updateLike(Posts posts, User user) {
        if(didUserPushLike(posts, user)) {
            Likes userLike = posts.getLikeList().stream().filter(like ->
                    like.getUser().getId() == user.getId()
            ).findFirst().get();
            minusLike(posts, userLike);
            return;
        }

        plusLike(posts, user);
    }

    public boolean didUserPushLike(Posts posts, User user) {
        List<Likes> likeList = posts.getLikeList();

        if(likeList == null) {
            return false;
        }

        Likes userLike = likeList.stream().filter(like ->
                like.getUser().getId() == user.getId()
        ).findFirst().orElse(null);

        if(userLike == null) {
            return false;
        }

        return true;
    }

    public void plusLike(Posts posts, User user) {
        Likes like = new Likes();
        like.setPosts(posts);
        like.setUser(user);

        postsService.addLike(posts, like);
    }

    public void minusLike(Posts posts, Likes userLike) {
        List<Likes> likeList = posts.getLikeList();
        likeList.remove(userLike);

    }
}
