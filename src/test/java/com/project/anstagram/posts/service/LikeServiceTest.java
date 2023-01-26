package com.project.anstagram.posts.service;

import com.project.anstagram.posts.domain.Posts;
import com.project.anstagram.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class LikeServiceTest {

    @Autowired private LikeService likeService;
    @Autowired private UserService userService;
    @Autowired private PostsService postsService;

    @DisplayName("한 사용자의 특정 게시글에 대한 좋아요 push 여부를 확인할 수 있다.")
    @Test
    public void checkPushingLike() {
        User user = new User();
        user.setNickname("user1");

        userService.save(user);

        Posts posts = new Posts();
        postsService.addUser(posts, user);

        Assertions.assertThat(likeService.didUserPushLike(posts, user)).isFalse();

        likeService.updateLike(posts, user);

        Assertions.assertThat(likeService.didUserPushLike(posts, user)).isTrue();
    }

    @DisplayName("게시글에 눌렀던 좋아요를 취소한다.")
    @Test
    public void checkMinusLike() {
        User user = new User();
        user.setNickname("user2");

        userService.save(user);

        Posts posts = new Posts();
        postsService.addUser(posts, user);

        // 좋아요 push
        likeService.updateLike(posts, user);

        // 좋아요 cancel
        likeService.updateLike(posts, user);

        Assertions.assertThat(likeService.didUserPushLike(posts, user)).isFalse();
    }



}
