package com.project.anstagram.posts.service;

import com.project.anstagram.posts.domain.Comment;
import com.project.anstagram.posts.domain.Posts;
import com.project.anstagram.posts.repository.PostsRepository;
import com.project.anstagram.posts.repository.UserRepository;
import com.project.anstagram.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    public void addComment(Long parentId, Long postsId, Long userId, String content) {
        Posts posts = postsRepository.findById(postsId);
        User user = userRepository.findById(userId);

        Comment comment = createComment(parentId, posts, user, content);

        posts.getCommentList().add(comment);
    }

    public List<Comment> findAllComment(Long postsId) {
        Posts posts = postsRepository.findById(postsId);
        List<Comment> commentList = posts.getCommentList();

        List<Comment> parentCommentList = commentList.stream().filter(c -> c.getParent().getId() == 0)
                .collect(Collectors.toList());

        List<Comment> allCommentList = new ArrayList<>();

        for (Comment comment : parentCommentList) {
            allCommentList.add(comment);

            List<Comment> childCommentList = commentList.stream().filter(c -> c.getParent().getId() == comment.getId())
                    .collect(Collectors.toList());

            if(childCommentList != null) {
                allCommentList.addAll(childCommentList);
            }

        }

        return allCommentList;
    }

    public void updateComment(Long postsId, Long CommentId, String content) {
        Posts posts = postsRepository.findById(postsId);
        List<Comment> commentList = posts.getCommentList();

        Comment comment = commentList.stream().filter(c -> c.getId() == CommentId)
                .findAny().get();

        comment.setContent(content);
    }

    private Comment createComment(Long parentId, Posts posts, User user, String content) {
        Comment comment = new Comment();
        comment.setPosts(posts);
        comment.setContent(content);
        comment.setUser(user);

        if(parentId != 0) {
            Comment parentComment = posts.getCommentList().stream().filter(c -> c.getId() == parentId)
                    .findAny().get();

            comment.setParent(parentComment);
        }

        return comment;

    }
}
