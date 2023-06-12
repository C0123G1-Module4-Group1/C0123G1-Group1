package com.example.coffee.user.model;

import com.example.coffee.user.dto.MyUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static MyUser getPrincipal(){
        return(MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
    }
}
