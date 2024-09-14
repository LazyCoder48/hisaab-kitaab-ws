package com.rapd.hisabkitab.app.users.dto;

import com.rapd.hisabkitab.app.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/*
 * Copyright (c) 2024.
 * ajite created RegisterUserDto.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 14/09/24, 2:00 pm
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
