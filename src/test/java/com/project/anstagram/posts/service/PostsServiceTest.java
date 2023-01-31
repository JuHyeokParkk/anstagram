package com.project.anstagram.posts.service;

import com.project.anstagram.posts.domain.Posts;
import com.project.anstagram.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;


@SpringBootTest
@Transactional
class PostsServiceTest {

    final int POSTS_NUM = 3;
    final Long DELETING_POSTS_ID = 1L;
    @Autowired
    PostsService postsService;
    User testUser;

    @BeforeEach
    public void init() {
        testUser = new User();

        IntStream
                .range(0, POSTS_NUM)
                .forEach(index -> {
                    Posts posts = new Posts();
                    posts.setContents(Integer.toString(index));
                    postsService.addPosts(posts, testUser);
                });
    }

    @DisplayName("한 사용자는 여러 개의 게시글을 등록할 수 있다.")
    @Test
    public void savePosts() {

        // @BeforeEach에 임의의 게시글 등록

        Assertions.assertThat(testUser.getPostsList().size()).isEqualTo(POSTS_NUM);

        IntStream
                .range(0, POSTS_NUM)
                .forEach(index ->
                        Assertions.assertThat(testUser.getPostsList().get(index).getContents()).isEqualTo(Integer.toString(index))
                );
    }

    @DisplayName("게시글 내용은 수정 가능하다.")
    @Test
    public void updatePosts() {
        Posts posts = postsService.findById(POSTS_NUM - 1L);
        postsService.update(POSTS_NUM - 1L, "updated");

        String afterUpdatedContents = posts.getContents();

        Assertions.assertThat(posts.getContents()).isEqualTo(afterUpdatedContents);


    }

    @DisplayName("게시글은 삭제가 가능하다.")
    @Test
    public void deletePosts() {
        postsService.remove(DELETING_POSTS_ID, testUser);

        List<Posts> postsList = testUser.getPostsList();

        Assertions.assertThat(postsList.size()).isEqualTo(POSTS_NUM - 1);

        postsList.stream().forEach(p -> Assertions.assertThat(p.getId()).isNotEqualTo(DELETING_POSTS_ID));

    }


}