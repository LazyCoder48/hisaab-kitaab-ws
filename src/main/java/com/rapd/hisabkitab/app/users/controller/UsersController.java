package com.rapd.hisabkitab.app.users.controller;

import com.rapd.hisabkitab.app.pojo.AppRequestPojo;
import com.rapd.hisabkitab.app.pojo.AppResponsePojo;
import com.rapd.hisabkitab.app.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PutMapping("/update/details")
    public ResponseEntity<AppResponsePojo> updateUserDetails(@RequestBody AppRequestPojo appRequestPojo) {
        log.info("update details for {}", appRequestPojo);
        return usersService.updateUserDetails(appRequestPojo);


    }


}
