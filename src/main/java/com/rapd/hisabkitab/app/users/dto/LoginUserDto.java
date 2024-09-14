package com.rapd.hisabkitab.app.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/*
 * Copyright (c) 2024.
 * ajite created LoginUserDto.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 14/09/24, 2:00 pm
 */

/**
 * DTO for {@link com.rapd.hisabkitab.app.users.entity.Users}
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginUserDto {

    private String username;
    private String password;

}
