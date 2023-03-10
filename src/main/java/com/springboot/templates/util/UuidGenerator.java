package com.springboot.templates.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidGenerator implements IRandomStringGenerator {
    @Override
    public String random() {
        return UUID.randomUUID().toString();
    }
}
