package com.rapd.hisaabkitaab.app.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

/*
 * Copyright (c) 2025.
 * ajite created GlobalExceptionHandler.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex) {

        ProblemDetail errorDetail = switch (ex) {

            case ExpiredJwtException e -> {
                log.error("Encountered ExpiredJwtException :{}", e.getMessage());
                ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
                detail.setProperty("description", e.getMessage());
                yield detail;
            }
            case SessionAuthenticationException e -> {
                log.error("Encountered SessionAuthenticationException :{}", e.getMessage());
                ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
                detail.setProperty("description", e.getMessage());
                yield detail;
            }
            case UsernameNotFoundException e -> {
                log.error("Encountered UsernameNotFoundException :{}", e.getMessage());
                ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
                detail.setProperty("description", e.getMessage());
                yield detail;
            }
            case MalformedJwtException e -> {
                log.error("Encountered MalformedJwtException :{}", e.getMessage());
                ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
                detail.setProperty("description", e.getMessage());
                yield detail;
            }
            case DuplicateKeyException e -> {
                log.error("Encountered DuplicateKeyException :{}", e.getMessage());
                ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
                detail.setProperty("description", e.getMessage());
                yield detail;
            }
            case DataIntegrityViolationException e -> {
                log.error("Encountered DataIntegrityViolationException :{}", e.getMessage());
                ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
                detail.setProperty("description", e.getMessage());
                yield detail;
            }
            case BadCredentialsException e -> {
                log.error("Encountered BadCredentialsException :{}", e.getMessage());
                ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
                detail.setProperty("description", e.getMessage());
                yield detail;
            }
            case AccountStatusException e -> {
                log.error("Encountered AccountStatusException :{}", e.getMessage());
                ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
                detail.setProperty("description", "The account is locked");
                yield detail;
            }
            case AccessDeniedException e -> {
                log.error("Encountered AccessDeniedException :{}", e.getMessage());
                ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
                detail.setProperty("description", "You are not authorized to access this resource");
                yield detail;
            }
            case SignatureException e -> {
                log.error("Encountered SignatureException :{}", e.getMessage());
                ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
                detail.setProperty("description", "The JWT signature is invalid");
                yield detail;
            }
            default -> {
                log.error("{} Internal Server Error {}", ex.getCause(), ex.getMessage(), ex);
                ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                                                                        ex.getMessage());
                detail.setProperty("description", "Unknown internal server error.");
                yield detail;
            }
        };

        log.error("Error occurred {} | {}", ex.getMessage(), errorDetail, ex);
        return errorDetail;


    }


}