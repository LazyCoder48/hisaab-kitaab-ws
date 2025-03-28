package com.rapd.hisaabkitaab.service;

import com.rapd.hisaabkitaab.app.users.pojo.UsersPojo;
import com.rapd.hisaabkitaab.startup.ApplicationStartup;
import com.rapd.hisaabkitaab.utils.PropertiesMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/*
 * Copyright (c) 2025.
 * ajite created JwtService.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private final ApplicationStartup applicationStartup;
    private final PropertiesMapper   propertiesMapper;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {

        UsersPojo usersPojo = new UsersPojo();
        BeanUtils.copyProperties(userDetails, usersPojo);
        log.info("Generating token for usersPojo: {}", usersPojo); // Added logging

        return generateTokenWithUserDetails(propertiesMapper.generateMapFromObject(usersPojo), userDetails);
    }

    public String generateTokenWithUserDetails(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims,
                          userDetails,
                          Long.valueOf(applicationStartup.getEnvironmentVariable("jwt_expiration_time")));
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, Long jwtExpirationTime) {
        Date now        = new Date(System.currentTimeMillis());
        Date expiration = new Date(now.getTime() + jwtExpirationTime * 1000 * 60);
        extraClaims.put("expiration", expiration);
        long diffInMillis  = expiration.getTime() - now.getTime();
        long diffInSeconds = diffInMillis / 1000 % 60;
        long diffInMinutes = (diffInMillis / (1000 * 60)) % 60;
        long diffInHours   = (diffInMillis / (1000 * 60 * 60)) % 24;
        long diffInDays    = diffInMillis / (1000 * 60 * 60 * 24);
        log.info("token expiration {} - validity ({} days, {} hours, {} minutes, {} seconds)",
                 expiration, diffInDays, diffInHours, diffInMinutes, diffInSeconds);

        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setId(userDetails.getUsername())
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isTokenValid(String token, String username) {
        final String jwtUsername = extractUsername(token);
        return (jwtUsername.equals(username)) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody(); // Wrapped in try-catch for better error handling
        } catch (Exception e) {
            log.error("Error while extracting claims: ", e);
            throw new RuntimeException("Invalid JWT token");  // Better exception handling
        }
    }

    private Key getSignInKey() {
        try {
            String jwtSecret = applicationStartup.getEnvironmentVariable("jwt_secret");
            byte[] keyBytes  = Decoders.BASE64URL.decode(jwtSecret);
            return Keys.hmacShaKeyFor(keyBytes); // Proper error handling
        } catch (Exception e) {
            log.error("Error while decoding secret: ", e);
            throw new RuntimeException("Failed to decode JWT secret");  // Better error handling
        }
    }
}