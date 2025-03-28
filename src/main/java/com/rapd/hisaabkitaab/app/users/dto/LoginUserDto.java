package com.rapd.hisaabkitaab.app.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/*
 * Copyright (c) 2025.
 * ajite created LoginUserDto.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

/**
 * DTO for {@link com.rapd.hisaabkitaab.app.users.entity.Users}
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginUserDto {

    private String username;
    private String password;

}