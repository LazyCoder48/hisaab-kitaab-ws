package com.rapd.hisabkitab.app.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Copyright (c) 2024.
 * ajite created AppResponsePojo.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppResponsePojo {

    private Object data;
    private String jwt;
    private int    httpResponseCode;
    private String httpResponseBody;

}
