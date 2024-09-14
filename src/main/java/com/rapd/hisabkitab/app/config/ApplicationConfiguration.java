package com.rapd.hisabkitab.app.config;

import com.rapd.hisabkitab.app.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Copyright (c) 2024.
 * ajite created ApplicationConfiguration.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 11/09/24, 11:56 pm
 */

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfiguration implements WebMvcConfigurer {

    private final UsersRepository usersRepository;


    @Bean
    UserDetailsService userDetailsService() {
        return username -> usersRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(
            "User " + username + " not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


}
