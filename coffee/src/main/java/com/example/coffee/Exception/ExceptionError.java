package com.example.coffee.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;



public class ExceptionError{
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoFoundException(Exception e){
        return "error";
    }
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public String Error500(Exception e){
        return "500";
    }

}
