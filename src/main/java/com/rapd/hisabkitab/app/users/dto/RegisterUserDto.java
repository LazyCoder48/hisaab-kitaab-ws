package com.rapd.hisabkitab.app.users.dto;

import com.rapd.hisabkitab.app.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link Users}
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RegisterUserDto {

    private              String username;
    private              String firstName;
    private              String lastName;
    private              String contactNumber;
    private              String password;

}
