package com.example.coffee.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionError{
    @ExceptionHandler(Exception.class)
    public String log() {
        return "error";
    }
}
