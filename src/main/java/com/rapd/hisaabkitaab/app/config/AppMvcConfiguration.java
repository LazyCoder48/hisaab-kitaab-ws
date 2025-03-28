package com.rapd.hisaabkitaab.app.config;

import com.rapd.hisaabkitaab.app.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Copyright (c) 2025.
 * ajite created AppMvcConfiguration.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
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