package com.project.anstagram.authentication;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtProviderTest {

    @Autowired JwtProvider jwtProvider;

    @DisplayName("JWT를 생성하고, 해당 JWT로부터 닉네임 정보를 얻는다.")
    @Test
    public void generate() {
        String nickname = "user1";
        String jwt = jwtProvider.generateJwt(nickname);

        Assertions.assertThat(jwt).isNotNull();

        Assertions.assertThat(jwtProvider.getNicknameFromJwt(jwt)).isEqualTo(nickname);
    }
}
