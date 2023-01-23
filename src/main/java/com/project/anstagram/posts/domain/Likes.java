package com.project.anstagram.posts.domain;

import com.project.anstagram.user.entity.Users;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Likes {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;
}
