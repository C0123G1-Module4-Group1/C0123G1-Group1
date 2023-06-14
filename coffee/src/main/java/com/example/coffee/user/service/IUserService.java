package com.example.coffee.user.service;


import com.example.coffee.user.model.User;
import org.springframework.security.core.Authentication;

public interface IUserService {
    boolean savePass(String pass, String newPass, Authentication authentication);

    User findUserByUserName(String name);
}
