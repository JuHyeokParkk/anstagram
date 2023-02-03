package com.project.anstagram.user.entity;

import com.project.anstagram.posts.domain.Comment;
import com.project.anstagram.posts.domain.Likes;
import com.project.anstagram.posts.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    private Long id;
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Likes> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Posts> postsList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

}
