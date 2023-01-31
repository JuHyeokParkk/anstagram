package com.project.anstagram.posts.domain;

import com.project.anstagram.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Posts {

    @Id @GeneratedValue
    private Long id;
    private String username;

    private String contents;

    @OneToMany(mappedBy = "posts", orphanRemoval = true)
    private List<Likes> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "posts", orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
