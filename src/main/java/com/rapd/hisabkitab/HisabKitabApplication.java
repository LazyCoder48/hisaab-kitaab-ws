package com.rapd.hisabkitab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * Copyright (c) 2024.
 * ajite created HisabKitabApplication.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 */

@SpringBootApplication
@EntityScan(basePackages = "com.rapd")
@ComponentScan(basePackages = "com.rapd")
@EnableJpaRepositories
@EnableJpaAuditing
public class HisabKitabApplication {

    public static void main(String[] args) {
        SpringApplication.run(HisabKitabApplication.class, args);
    }

}
