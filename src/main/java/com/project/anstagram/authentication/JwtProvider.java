package com.project.anstagram.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtProvider {

    private static Key key;

    @PostConstruct
    protected void initSecretKey() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    private static int jwtExpirationMs = 1000000;

    public String generateJwt(String nickname) {
        return Jwts.builder()
                .setSubject(nickname)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key)
                .compact();
    }

    public String getNicknameFromJwt(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String getJwtFromCookie(HttpServletRequest request) {
        Cookie jwtCookie = Arrays.stream(request.getCookies()).filter(Cookie -> Cookie.getName().equals("jwt"))
                .findFirst().get();

        return jwtCookie == null ? "" : jwtCookie.getName();
    }

    public boolean validateJwt(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
