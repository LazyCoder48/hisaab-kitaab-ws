package com.rapd.hisabkitab.app.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link com.rapd.hisabkitab.app.users.entity.Users}
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginUserDto {

    private              String username;
    private              String password;

}
