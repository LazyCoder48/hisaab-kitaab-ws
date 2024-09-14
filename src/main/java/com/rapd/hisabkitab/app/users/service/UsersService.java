package com.rapd.hisabkitab.app.users.service;

import com.rapd.hisabkitab.app.pojo.AppRequestPojo;
import com.rapd.hisabkitab.app.pojo.AppResponsePojo;
import org.springframework.http.ResponseEntity;

/*
 * Copyright (c) 2024.
 * ajite created UsersService.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 14/09/24, 2:00 pm
 */

/**
 * Provides user-related services such as registration, authentication, and updating user details.
 */
public interface UsersService {

    /**
     * Registers a new application request based on the provided details.
     *
     * @param appRequestPojo the request containing the signup details
     * @return a response entity containing the signup result and relevant details
     */
    ResponseEntity<AppResponsePojo> signup(AppRequestPojo appRequestPojo);

    /**
     * Authenticates an application request based on the provided details.
     *
     * @param appRequestPojo the request containing the authentication details
     * @return a response entity containing the authentication result and details
     */
    ResponseEntity<AppResponsePojo> authenticate(AppRequestPojo appRequestPojo);

    /**
     * Updates the user details based on the provided request.
     *
     * @param appRequestPojo the request containing the user details to be updated
     * @return a response entity containing the result of the update operation and relevant details
     */
    ResponseEntity<AppResponsePojo> updateUserDetails(AppRequestPojo appRequestPojo);
}
