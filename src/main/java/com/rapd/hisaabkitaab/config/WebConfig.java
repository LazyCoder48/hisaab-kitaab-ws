package com.rapd.hisaabkitaab.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Copyright (c) 2025.
 * ajite created WebConfig.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("add cors mappings");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4201")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE", "PATCH")
                .allowCredentials(true).exposedHeaders("Content-Disposition");
        log.info("done adding cors mappings");

    }

}