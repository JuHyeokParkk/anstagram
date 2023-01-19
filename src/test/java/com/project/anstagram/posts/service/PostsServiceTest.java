package com.project.anstagram.posts.service;

import com.project.anstagram.posts.domain.Posts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class PostsServiceTest {

    @Autowired
    PostsService postsService;

    @DisplayName("setter를 통해 값 설정, 게시글을 저장한다.")
    @Test
    public void savePosts() {
        Posts posts = new Posts();
        posts.setUsername("testUser");
        posts.setContents("글 내용");

        long savedPostsId = postsService.save(posts);

        Posts findPosts = postsService.findById(savedPostsId);

        Assertions.assertThat(savedPostsId).isEqualTo(posts.getId());
        Assertions.assertThat(posts).isEqualTo(findPosts);
    }

}