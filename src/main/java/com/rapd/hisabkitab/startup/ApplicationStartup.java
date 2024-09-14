package com.rapd.hisabkitab.startup;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
 * Copyright (c) 2024.
 * ajite created ApplicationStartup.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationStartup {

    private final ConfigurableEnvironment environment;

    @Setter
    private Map<String, String> environmentVariables = new HashMap<>();

    public void loadEnvironmentVariables() {

        environment.getSystemEnvironment().forEach((k, v) -> environmentVariables.put(k, (String) v));
        log.info("Environment variables - size: {} | values: {}", environmentVariables.size(), environmentVariables);

    }

    public String getEnvironmentVariable(String key) {
        return environmentVariables.get(key);
    }


    @PostConstruct
    public void init() {
        loadEnvironmentVariables();
    }


}
