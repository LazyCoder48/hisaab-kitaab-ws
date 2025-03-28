package com.rapd.hisaabkitaab.app.users.service;

import com.rapd.hisaabkitaab.app.pojo.AppRequestPojo;
import com.rapd.hisaabkitaab.app.pojo.AppResponsePojo;
import com.rapd.hisaabkitaab.app.users.entity.Users;
import com.rapd.hisaabkitaab.app.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/*
 * Copyright (c) 2025.
 * ajite created AppUserServiceImpl.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final UsersRepository usersRepository;

    /**
     * Updates the user information based on the provided request data.
     *
     * @param appRequestPojo the request object containing user data to be updated
     * @return ResponseEntity containing the response data and status of the update operation
     */
    @Override
    public ResponseEntity<AppResponsePojo> updateUser(AppRequestPojo appRequestPojo) {

        log.info("Update User {}", appRequestPojo);
        try {

            Users updateUsers = (Users) appRequestPojo.getData();


        } catch (Exception e) {
            log.error("error while updating user {} ", e.getMessage(), e);
        }


        return null;
    }

    /**
     * Logs out the user based on the provided request data.
     *
     * @param appRequestPojo the request object containing user information for logout
     * @return ResponseEntity containing the response data and status of the logout operation
     */
    @Override
    public ResponseEntity<AppResponsePojo> logoutUser(AppRequestPojo appRequestPojo) {
        return null;
    }
}