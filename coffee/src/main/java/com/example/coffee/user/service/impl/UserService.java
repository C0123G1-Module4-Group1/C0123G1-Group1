package com.example.coffee.user.service.impl;

import com.example.coffee.user.model.Role;
import com.example.coffee.user.model.User;
import com.example.coffee.user.repository.IUserRepository;
import com.example.coffee.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;


    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User appUser = this.iUserRepository.findByAccountAndDeleteStatusIsFalse(account);

        if (appUser == null) {
            System.out.println("User not found! " + account);
            throw new UsernameNotFoundException("User " + account + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);


        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + appUser.getRole().getName());
        grantList.add(authority);
        System.out.println(grantList);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(appUser.getAccount(),
                appUser.getPassword(), grantList);

        return userDetails;
    }
}
