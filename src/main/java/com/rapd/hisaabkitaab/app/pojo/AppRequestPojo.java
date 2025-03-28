package com.rapd.hisaabkitaab.app.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Copyright (c) 2025.
 * ajite created AppRequestPojo.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppRequestPojo {

    private Object data;
    private String jwt;

}