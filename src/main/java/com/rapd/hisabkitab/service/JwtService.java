package com.rapd.hisabkitab.service;

import com.rapd.hisabkitab.app.users.pojo.UsersPojo;
import com.rapd.hisabkitab.startup.ApplicationStartup;
import com.rapd.hisabkitab.utils.PropertiesMapper;
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
 * Copyright (c) 2024.
 * ajite created JwtService.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 11/09/24, 11:46 pm
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
        log.info("usersPojo {}", usersPojo);

        return generateTokenWithUserDetails(propertiesMapper.generateMapFromObject(usersPojo), userDetails);
    }

    public String generateTokenWithUserDetails(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims,
                          userDetails,
                          Long.valueOf(applicationStartup.getEnvironmentVariable("jwt_expiration_time")));
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, Long jwtExpirationTime) {

        Date now        = new Date(System.currentTimeMillis());
        Date expiration = Date.from(java.time.Instant.now().plusMillis(jwtExpirationTime));
        log.info("expiration {}", expiration);
        extraClaims.put("expiration", expiration);
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

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return !extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(applicationStartup.getEnvironmentVariable("jwt_secret"));
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
