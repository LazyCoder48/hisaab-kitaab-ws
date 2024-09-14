package com.rapd.hisabkitab.app.users.service;

import com.rapd.hisabkitab.app.pojo.AppRequestPojo;
import com.rapd.hisabkitab.app.pojo.AppResponsePojo;
import com.rapd.hisabkitab.app.users.dto.LoginUserDto;
import com.rapd.hisabkitab.app.users.dto.RegisterUserDto;
import com.rapd.hisabkitab.app.users.entity.Users;
import com.rapd.hisabkitab.app.users.pojo.UsersPojo;
import com.rapd.hisabkitab.app.users.repository.UsersRepository;
import com.rapd.hisabkitab.service.JwtService;
import com.rapd.hisabkitab.utils.PropertiesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Optional;

/*
 * Copyright (c) 2024.
 * ajite created UsersServiceImpl.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 11/09/24, 10:28 pm
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository       usersRepository;
    private final PasswordEncoder       passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService            jwtService;
    private final PropertiesMapper      propertiesMapper;

    /**
     * Handles the signup process for a new user.
     * <p>
     * This method takes an AppRequestPojo object containing user details, checks if a user with the same username
     * already exists, and if not, creates a new user in the database. It then returns a response containing the user's
     * information.
     *
     * @param appRequestPojo the request object containing the user details for signup.
     * @return a ResponseEntity containing an AppResponsePojo object, which holds the data of the newly created user.
     * @throws DuplicateKeyException if a user with the same username already exists.
     */
    @Override
    public ResponseEntity<AppResponsePojo> signup(AppRequestPojo appRequestPojo) {
        //        RegisterUserDto registerDto = new RegisterUserDto();
        @SuppressWarnings("unchecked")
        RegisterUserDto registerDto
            = propertiesMapper.mapSourceToTarget((LinkedHashMap<String, Object>) appRequestPojo.getData(),
                                                 RegisterUserDto.class);
        Optional<Users> existingUserOptional = usersRepository.findByUsername(registerDto.getUsername());
        if (existingUserOptional.isPresent()) {
            log.warn("user already exists with username {}", registerDto.getUsername());
            throw new DuplicateKeyException("User already exists");
        }

        Users user = Users.builder().username(registerDto.getUsername()).firstName(registerDto.getFirstName()).lastName(
            registerDto.getLastName()).contactNumber(registerDto.getContactNumber()).password(passwordEncoder.encode(
            registerDto.getPassword())).createdAt(new Date()).build();
        try {
            Users savedUser = usersRepository.save(user);
            log.info("Saved user {} successfully @{}", savedUser.getUsername(), savedUser.getCreatedAt());
            AppResponsePojo appResponsePojo = new AppResponsePojo();
            UsersPojo       usersPojo       = new UsersPojo();
            BeanUtils.copyProperties(savedUser, usersPojo);
            appResponsePojo.setData(usersPojo);
            return ResponseEntity.ok(appResponsePojo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("A user already registered with the given contact number");
        }
    }

    /**
     * Authenticates a user based on the provided credentials and generates a JWT token upon successful authentication.
     *
     * @param appRequestPojo the request object containing user login credentials
     * @return a ResponseEntity containing the authentication response, including user details and JWT token
     */
    @Override
    public ResponseEntity<AppResponsePojo> authenticate(AppRequestPojo appRequestPojo) {
        LoginUserDto loginUserDto = new LoginUserDto();
        if (appRequestPojo.getData() instanceof LinkedHashMap) {
            @SuppressWarnings("unchecked")
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) appRequestPojo.getData();
            loginUserDto.setUsername(map.get("username").toString());
            loginUserDto.setPassword(map.get("password").toString());
        } else {
            // Handle other possible cases
            loginUserDto = (LoginUserDto) appRequestPojo.getData();
        }


        log.info("Authenticating user {}", loginUserDto.getUsername());
        try {
            Optional<Users> userOptional = usersRepository.findByUsername(loginUserDto.getUsername());
            if (userOptional.isEmpty()) {
                throw new UsernameNotFoundException(
                    "A user with username " + loginUserDto.getUsername() + " not found");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(),
                                                                                       loginUserDto.getPassword()));
            log.info("User {} authenticated successfully", loginUserDto.getUsername());

            Users           authenticatedUser = userOptional.get();
            AppResponsePojo appResponsePojo   = new AppResponsePojo();
            UsersPojo       usersPojo         = new UsersPojo();
            BeanUtils.copyProperties(authenticatedUser, usersPojo);
            appResponsePojo.setData(usersPojo);
            String jwt = jwtService.generateToken(authenticatedUser);
            appResponsePojo.setJwt(jwt);
            appResponsePojo.setHttpResponseCode(HttpStatus.OK.value());
            appResponsePojo.setHttpResponseBody("Authentication successful");
            return ResponseEntity.ok(appResponsePojo);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Username and password do not match");
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }

    /**
     * Updates the user details based on the provided request data.
     *
     * @param appRequestPojo the request payload containing the JWT and user data to be updated
     * @return ResponseEntity containing the response payload with updated user details
     * @throws UsernameNotFoundException if the user is not found based on the username extracted from the JWT
     * @throws SessionAuthenticationException if the JWT has expired and the session is not valid
     */
    @Override
    public ResponseEntity<AppResponsePojo> updateUserDetails(AppRequestPojo appRequestPojo) {
        log.info("Update user details {}", appRequestPojo);
        String jwt = appRequestPojo.getJwt();
        if (jwtService.isTokenExpired(jwt)) {
            log.info("jwt is active");
            String userName = jwtService.extractUsername(jwt);
            log.info("userName {}", userName);
            Optional<Users> userOptional = usersRepository.findByUsername(userName);
            if (userOptional.isPresent()) {
                log.info("user is available");
                Users user = new Users();
                BeanUtils.copyProperties(appRequestPojo.getData(), user);
                user.setUpdatedAt(new Date());
                Users updatedUser = usersRepository.save(user);
                log.info("Updated user {} successfully @{}", updatedUser.getUsername(), updatedUser.getUpdatedAt());
                AppResponsePojo appResponsePojo = new AppResponsePojo();
                UsersPojo       usersPojo       = new UsersPojo();
                BeanUtils.copyProperties(updatedUser, usersPojo);
                appResponsePojo.setData(usersPojo);
                return ResponseEntity.ok(appResponsePojo);
            } else {
                throw new UsernameNotFoundException("user " + userName + " not found");
            }
        } else {
            throw new SessionAuthenticationException("Session expired, Please login again");
        }
    }
}
