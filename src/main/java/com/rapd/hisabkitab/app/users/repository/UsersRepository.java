package com.rapd.hisabkitab.app.users.repository;

import com.rapd.hisabkitab.app.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Optional<Users> findByUsername(@NonNull String username);

}


