package com.rapd.hisabkitab.app.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/*
 * Copyright (c) 2024.
 * ajite created Users.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 30/9/2024 9:11:57
 */

/**
 * Entity that represents a user in the application.
 * This class is annotated with JPA annotations and implements the UserDetails
 * interface for Spring Security integration.
 * <p>
 * Annotations used:
 * - @Builder: Enables the builder pattern for this class.
 * - @AllArgsConstructor: Generates a constructor with a parameter for each field.
 * - @NoArgsConstructor: Generates an empty constructor.
 * - @Getter and @Setter: Generates getter and setter methods for all fields.
 * - @ToString: Generates a toString() method.
 * - @Accessors(chain = true): Enables chained accessor methods.
 * - @Entity: Marks this class as a JPA entity.
 * - @Table: Specifies the table name and schema for this entity.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "users", schema = "public")
public class Users implements Serializable, UserDetails {

    @Serial
    private static final long                   serialVersionUID = -2107953066338471450L;
    @Transient
    private              List<GrantedAuthority> authorities;
    @Id
    @Column(name = "username", nullable = false, length = 300, unique = true)
    private              String                 username;
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false, updatable = false)
    private              Date                   createdAt;
    @Column(name = "updated_at")
    private              Date                   updatedAt;
    @Column(name = "first_name", nullable = false, length = 50)
    private              String                 firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private              String                 lastName;
    @Column(name = "contact_number", nullable = false, length = 10)
    private              String                 contactNumber;
    @Column(name = "city", length = 100)
    private              String                 city;
    @Column(name = "state", length = 100)
    private              String                 state;
    @Column(name = "country", length = 100)
    private              String                 country;
    @Column(name = "zip_code", length = 10)
    private              String                 zipCode;
    @Column(name = "password")
    private              String                 password;
    @Column(name = "jwt_token", columnDefinition = "bytea[]")
    private              byte[]                 jwtToken;

    /**
     * Retrieves the authorities granted to the user.
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true if the user's account is valid (non-expired), false if no longer valid (expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked or unlocked.
     *
     * @return true if the user's account is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     *
     * @return true if the user's credentials are valid (non-expired), false if no longer valid (expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
