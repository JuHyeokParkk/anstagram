package com.project.anstagram.config;

import com.project.anstagram.authentication.JwtProvider;
import com.project.anstagram.authentication.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(new JwtProvider()))
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/refresh");
    }
}
