package com.rapd.hisaabkitaab.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Copyright (c) 2025.
 * ajite created PropertiesMapper.java
 * Project: hisab-kitab-ws | Module: hisab-kitab-ws
 * Last updated on 28/3/2025 11:13:37
 */

@Component
@Slf4j
public class PropertiesMapper {

    public <T> T mapSourceToTarget(LinkedHashMap<String, Object> source, Class<T> targetClass) {

        T target = null;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
            Field[] targetFields = targetClass.getDeclaredFields();
            for (Map.Entry<String, Object> entry : source.entrySet()) {
                String key   = entry.getKey();
                Object value = entry.getValue();

                for (Field targetField : targetFields) {
                    if (key.equals(targetField.getName())) {
                        targetField.setAccessible(true);
                        targetField.set(target, value);
                        break;
                    }
                }
            }
        } catch (InstantiationException e) {
            log.error("InstantiationException occurred: {}", e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException occurred: {}", e.getMessage(), e);
        } catch (InvocationTargetException e) {
            log.error("InvocationTargetException occurred: {}", e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            log.error("NoSuchMethodException occurred: {}", e.getMessage(), e);
        }
        return target;
    }


    public Map<String, Object> generateMapFromObject(Object source) {

        Map<String, Object> map = new HashMap<>();
        try {
            Field[] fields = source.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(source));
            }
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException occurred: {}", e.getMessage(), e);
        }
        return map;
    }

}