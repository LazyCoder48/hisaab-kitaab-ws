package com.rapd.hisabkitab.startup;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
 * Copyright (c) 2024.
 * ajite created ApplicationStartup.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 30/9/2024 9:11:57
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationStartup {

    @Getter
    private final ConfigurableEnvironment environment;

    @Setter
    private Map<String, String> environmentVariables = new HashMap<>();

    public void loadEnvironmentVariables() throws IOException {

        Resource resource = new ClassPathResource("jwt.properties");
        try (InputStream inputStream = resource.getInputStream()) {
            Properties properties = new Properties();
            properties.load(inputStream);

            properties.forEach((key, value) -> environmentVariables.put((String) key, (String) value));

            log.info("Loaded 'jwt.properties' into environment variables.");
        } catch (IOException e) {
            throw new IOException("Unable to load properties file, please contact site owner");
        }
        log.info("Environment variables - size: {} | values: {}", environmentVariables.size(), environmentVariables);

    }

    public String getEnvironmentVariable(String key) {
        return environmentVariables.get(key);
    }


    @PostConstruct
    public void init() throws IOException {
        loadEnvironmentVariables();
    }


}
