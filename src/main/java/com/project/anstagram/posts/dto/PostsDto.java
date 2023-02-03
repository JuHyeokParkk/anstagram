package com.project.anstagram.posts.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PostsDto {

    private String content;
    private LocalDateTime localDateTime;


}
