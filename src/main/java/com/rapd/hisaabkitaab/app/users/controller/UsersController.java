package com.rapd.hisaabkitaab.app.users.controller;

import com.rapd.hisaabkitaab.app.pojo.AppRequestPojo;
import com.rapd.hisaabkitaab.app.pojo.AppResponsePojo;
import com.rapd.hisaabkitaab.app.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Copyright (c) 2025.
 * ajite created UsersController.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

@RequestMapping("/users")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PutMapping("/update/details")
    public ResponseEntity<AppResponsePojo> updateUserDetails(@RequestBody AppRequestPojo appRequestPojo) {
        log.info("update details for {}", appRequestPojo);
        return usersService.updateUserDetails(appRequestPojo);


    }


}