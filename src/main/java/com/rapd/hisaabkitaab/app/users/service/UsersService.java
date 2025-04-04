package com.rapd.hisaabkitaab.app.users.service;

import com.rapd.hisaabkitaab.app.pojo.AppRequestPojo;
import com.rapd.hisaabkitaab.app.pojo.AppResponsePojo;
import org.springframework.http.ResponseEntity;

/*
 * Copyright (c) 2025.
 * ajite created UsersService.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

/**
 * UsersService interface provides methods for user-related operations
 * such as signup, authentication, updating user details, and logout.
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

    /**
     * Logs out a user based on the provided request details.
     *
     * @param appRequestPojo the request containing the logout details
     * @return a response entity containing the result of the logout operation and relevant details
     */
    ResponseEntity<AppResponsePojo> logout(AppRequestPojo appRequestPojo);
}