package com.rapd.hisabkitab.app.users.controller;

import com.rapd.hisabkitab.app.pojo.AppRequestPojo;
import com.rapd.hisabkitab.app.pojo.AppResponsePojo;
import com.rapd.hisabkitab.app.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Copyright (c) 2024.
 * ajite created UserAuthenticationController.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 14/09/24, 2:00 pm
 */

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class UserAuthenticationController {

    private final UsersService usersService;


    /**
     * Registers a new user with the provided signup details.
     *
     * @param appRequestPojo the request containing the signup details
     * @return a response entity containing the signup result and relevant details
     */
    @PostMapping("/signup")
    public ResponseEntity<AppResponsePojo> signUp(@RequestBody AppRequestPojo appRequestPojo) {
        log.info("sign up for {}", appRequestPojo);
        return usersService.signup(appRequestPojo);
    }

    /**
     * Authenticates a user based on the provided login details.
     *
     * @param appRequestPojo the request containing the user's login details
     * @return a response entity containing the authentication result and relevant details
     */
    @PostMapping("/login")
    public ResponseEntity<AppResponsePojo> login(@RequestBody AppRequestPojo appRequestPojo) {
        log.info("login for {}", appRequestPojo);
        return usersService.authenticate(appRequestPojo);
    }

}
