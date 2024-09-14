package com.rapd.hisabkitab.app.config;

import com.rapd.hisabkitab.app.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Copyright (c) 2024.
 * ajite created AppMvcConfiguration.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 11/09/24, 11:53 pm
 */

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
public class AppMvcConfiguration implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }

}
