package com.rapd.hisabkitab.app.users.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link com.rapd.hisabkitab.app.users.entity.Users}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersPojo implements Serializable {

    @Serial
    private static final long           serialVersionUID = 4491708442151973759L;
    private              String         username;
    private              OffsetDateTime createdAt;
    private              OffsetDateTime updatedAt;
    private              String         firstName;
    private              String         lastName;
    private              String         contactNumber;
    private              String         city;
    private              String         state;
    private              String         country;
    private              String         zipCode;

}
