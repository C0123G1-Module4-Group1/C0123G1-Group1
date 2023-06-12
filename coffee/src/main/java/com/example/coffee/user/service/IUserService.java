package com.example.coffee.user.service;


import org.springframework.security.core.Authentication;

public interface IUserService {
    boolean savePass(String pass, String newPass, Authentication authentication);
}
