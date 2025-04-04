package com.rapd.hisaabkitaab.app.config;

import com.rapd.hisaabkitaab.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
 * Copyright (c) 2025.
 * ajite created JwtAuthFilterConfig.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilterConfig extends OncePerRequestFilter {

    private final JwtService         jwtService;
    private final UserDetailsService userDetailsService;


    /**
     * The method processes the given request, performs JWT authentication, and delegates the request to the next filter
     * in the chain if authentication is successful or not required.
     *
     * @param request      the servlet request
     * @param response     the servlet response
     * @param filterChain  the filter chain
     * @throws ServletException if an exception occurs during the processing
     * @throws IOException      if an input or output exception occurs during the processing
     */
    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        } else {
            //            try {
            final String   jwt            = authorizationHeader.substring(7);
            final String   userEmail      = jwtService.extractUsername(jwt);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (null != userEmail && authentication == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                                                                                                            null,
                                                                                                            userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        }
    }

}