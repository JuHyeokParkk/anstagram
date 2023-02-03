package com.project.anstagram.posts;

import com.project.anstagram.posts.domain.Posts;
import com.project.anstagram.posts.dto.PostsDto;
import com.project.anstagram.posts.dto.UserDto;
import com.project.anstagram.user.entity.User;

public class EntityAndDtoMapperForPosts {

    public static Posts DtoToPosts(PostsDto postsDto) {
        return Posts.builder()
                .contents(postsDto.getContent())
                .build();

    }

    public static User DtoToUsers(UserDto userDto) {
        return User.builder()
                .build();
    }
}
