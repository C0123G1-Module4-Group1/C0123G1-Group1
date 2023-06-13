package com.example.coffee.config;


import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class CustomStringToLocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String from) {
        return "today".equalsIgnoreCase(from) ? LocalDate.now() : LocalDate.parse(from);
    }
}
