package com.rapd.hisabkitab.app.interceptor;

import com.rapd.hisabkitab.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * Copyright (c) 2024.
 * ajite created AuthInterceptor.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 12/09/24, 10:52 am
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/auth")) {
            log.info("requestURI: {}", requestURI);
            return true;
        } else {
            String authToken = request.getHeader("Authorization");
            String jwt       = authToken.substring(7);
            if (jwtService.isTokenExpired(jwt)) {
                throw new SessionAuthenticationException("User session is expired, please Login again");
            }

        }
        return false;
    }


    @Override
    public void postHandle(
        HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView
    ) {

    }

    @Override
    public void afterCompletion(
        HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex
    ) {

    }


}
