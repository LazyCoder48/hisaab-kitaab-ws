package com.rapd.hisabkitab;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * Copyright (c) 2024.
 * ajite created ServletInitializer.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 */

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HisabKitabApplication.class);
    }

}
