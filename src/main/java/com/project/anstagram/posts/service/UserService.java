package com.project.anstagram.posts.service;

import com.project.anstagram.posts.repository.UserRepository;
import com.project.anstagram.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }
}
