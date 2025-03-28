package com.rapd.hisaabkitaab.app.users.repository;

import com.rapd.hisaabkitaab.app.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Copyright (c) 2025.
 * ajite created UsersRepository.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Optional<Users> findByUsername(@NonNull String username);

}