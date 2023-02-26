package com.project.anstagram.authentication.login;

import com.project.anstagram.authentication.JwtProvider;
import com.project.anstagram.authentication.PasswordEncoder;
import com.project.anstagram.authentication.login.dto.LoginDto;
import com.project.anstagram.posts.repository.UserRepository;
import com.project.anstagram.posts.service.UserService;
import com.project.anstagram.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtProvider jwtProvider;
    private final UserService userService;

    public String generateToken(LoginDto loginDto) {

        User loginUser = userService.findByEmail(loginDto.getEmail()).orElseThrow();

        if(loginUser.getPassword() != PasswordEncoder.encrypt(loginDto.getPassword())) {
            throw new RuntimeException();
        }

        return jwtProvider.generateJwt(loginUser.getNickname());
    }

}
