package com.rapd.hisabkitab.app.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Copyright (c) 2024.
 * ajite created AppRequestPojo.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppRequestPojo {

    private Object data;
    private String jwt;

}
