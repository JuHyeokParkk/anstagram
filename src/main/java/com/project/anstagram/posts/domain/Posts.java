package com.project.anstagram.posts.domain;

import com.project.anstagram.user.entity.Users;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Posts {

    @Id @GeneratedValue
    private Long id;
    private String username;

    private String contents;

    @OneToMany(mappedBy = "posts")
    private List<Likes> likeList;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;
}
