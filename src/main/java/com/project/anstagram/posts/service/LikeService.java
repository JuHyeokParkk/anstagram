package com.project.anstagram.posts.service;

import com.project.anstagram.posts.domain.Like;
import com.project.anstagram.posts.domain.Posts;
import com.project.anstagram.posts.repository.LikeRepository;
import com.project.anstagram.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;


    @Transactional
    public void updateLike(Posts posts, User user) {
        if(didUserPushLike(posts, user)) {
            Like userLike = posts.getLikeList().stream().filter(like ->
                    like.getUser().getId() == user.getId()
            ).findFirst().get();
            minusLike(posts, userLike);
            return;
        }

        plusLike(posts, user);
    }

    public boolean didUserPushLike(Posts posts, User user) {
        List<Like> likeList = posts.getLikeList();

        if(likeList == null) {
            return false;
        }

        Like userLike = likeList.stream().filter(like ->
                like.getUser().getId() == user.getId()
        ).findFirst().get();

        if(userLike == null) {
            return false;
        }

        return true;
    }

    public void plusLike(Posts posts, User user) {
        Like like = new Like();
        like.setPosts(posts);
        like.setUser(user);

        likeRepository.save(like);

        List<Like> likeList = posts.getLikeList();
        likeList.add(like);
    }

    public void minusLike(Posts posts, Like userLike) {
        likeRepository.remove(userLike);

        List<Like> likeList = posts.getLikeList();
        likeList.remove(userLike);
    }
}
