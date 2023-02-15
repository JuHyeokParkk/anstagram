package com.project.anstagram.authentication.interceptor;

import com.project.anstagram.authentication.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String jwt = jwtProvider.getJwtFromCookie(request);

        if(jwt == null) {
            response.sendRedirect("/login");
            return false;
        }

        if(!jwtProvider.validateJwt(jwt)) {
            throw new Exception();
        }

        return true;

    }
}
