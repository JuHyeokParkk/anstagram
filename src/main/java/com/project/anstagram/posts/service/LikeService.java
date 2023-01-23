package com.project.anstagram.posts.service;

import com.project.anstagram.posts.domain.Likes;
import com.project.anstagram.posts.domain.Posts;
import com.project.anstagram.posts.repository.LikeRepository;
import com.project.anstagram.user.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;


    @Transactional
    public void updateLike(Posts posts, Users users) {
        if(didUserPushLike(posts, users)) {
            Likes userLike = posts.getLikeList().stream().filter(like ->
                    like.getUsers().getId() == users.getId()
            ).findFirst().get();
            minusLike(posts, userLike);
            return;
        }

        plusLike(posts, users);
    }

    public boolean didUserPushLike(Posts posts, Users users) {
        List<Likes> likeList = posts.getLikeList();

        if(likeList == null) {
            return false;
        }

        Likes userLike = likeList.stream().filter(like ->
                like.getUsers().getId() == users.getId()
        ).findFirst().get();

        if(userLike == null) {
            return false;
        }

        return true;
    }

    public void plusLike(Posts posts, Users users) {
        Likes like = new Likes();
        like.setPosts(posts);
        like.setUsers(users);

        likeRepository.save(like);

        List<Likes> likeList = posts.getLikeList();
        likeList.add(like);
    }

    public void minusLike(Posts posts, Likes userLike) {
        likeRepository.remove(userLike);

        List<Likes> likeList = posts.getLikeList();
        likeList.remove(userLike);
    }
}
