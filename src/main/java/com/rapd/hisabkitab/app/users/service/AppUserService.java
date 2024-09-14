package com.rapd.hisabkitab.app.users.service;

import com.rapd.hisabkitab.app.pojo.AppRequestPojo;
import com.rapd.hisabkitab.app.pojo.AppResponsePojo;
import org.springframework.http.ResponseEntity;

/*
 * Copyright (c) 2024.
 * ajite created AppUserService.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 */

public interface AppUserService {

    ResponseEntity<AppResponsePojo> updateUser(AppRequestPojo appRequestPojo);

    ResponseEntity<AppResponsePojo> logoutUser(AppRequestPojo appRequestPojo);


}
