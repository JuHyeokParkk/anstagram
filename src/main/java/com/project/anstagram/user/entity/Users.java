package com.project.anstagram.user.entity;

import com.project.anstagram.posts.domain.Likes;
import com.project.anstagram.posts.domain.Posts;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter
public class Users {

    @Id @GeneratedValue
    private Long id;
    private String nickname;

    @OneToMany(mappedBy = "users")
    private List<Likes> likeList;

    @OneToMany(mappedBy = "users")
    private List<Posts> postsList;

}
