package com.project.anstagram.posts.service;

import com.project.anstagram.posts.repository.UserRepository;
import com.project.anstagram.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(User user) {
        return userRepository.save(user).getId();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public boolean ExistsByEmailAndPassword(String email, String password) {
        return userRepository.existsByEmailAndPassword(email, password);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
