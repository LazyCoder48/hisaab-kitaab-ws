package com.rapd.hisaabkitaab.app.users.service;

import com.rapd.hisaabkitaab.app.pojo.AppRequestPojo;
import com.rapd.hisaabkitaab.app.pojo.AppResponsePojo;
import org.springframework.http.ResponseEntity;

/*
 * Copyright (c) 2025.
 * ajite created AppUserService.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

public interface AppUserService {

    ResponseEntity<AppResponsePojo> updateUser(AppRequestPojo appRequestPojo);

    ResponseEntity<AppResponsePojo> logoutUser(AppRequestPojo appRequestPojo);


}