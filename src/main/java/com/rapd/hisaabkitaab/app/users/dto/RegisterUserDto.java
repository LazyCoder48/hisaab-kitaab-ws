package com.rapd.hisaabkitaab.app.users.dto;

import com.rapd.hisaabkitaab.app.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/*
 * Copyright (c) 2025.
 * ajite created RegisterUserDto.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

/**
 * DTO for {@link Users}
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RegisterUserDto {

    private String username;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String password;

}