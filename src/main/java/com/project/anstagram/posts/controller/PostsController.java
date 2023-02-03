package com.project.anstagram.posts.controller;

import com.project.anstagram.posts.EntityAndDtoMapperForPosts;
import com.project.anstagram.posts.domain.Posts;
import com.project.anstagram.posts.dto.PostsDto;
import com.project.anstagram.posts.dto.UserDto;
import com.project.anstagram.posts.service.PostsService;
import com.project.anstagram.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/posts")
    public ResponseEntity savePosts(UserDto userDto, PostsDto postsDto) {
        User user = EntityAndDtoMapperForPosts.DtoToUsers(userDto);
        Posts posts = EntityAndDtoMapperForPosts.DtoToPosts(postsDto);

        postsService.addPosts(posts, user);

        return ResponseEntity.ok().build();
    }





}
