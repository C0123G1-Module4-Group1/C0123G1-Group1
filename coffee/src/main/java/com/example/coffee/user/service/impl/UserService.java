package com.example.coffee.user.service.impl;

import com.example.coffee.user.model.Role;
import com.example.coffee.user.model.User;
import com.example.coffee.user.repository.IUserRepository;
import com.example.coffee.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User appUser = this.iUserRepository.findByAccountAndDeleteStatusIsFalse(account);
        if (appUser == null) {
            System.out.println("User not found! " + account);
            throw new UsernameNotFoundException("User " + account + " was not found in the database");
        }


        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + appUser.getRole().getName());
        grantList.add(authority);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(appUser.getAccount(),
                appUser.getPassword(), grantList);

        return userDetails;
    }

    @Override
    public boolean savePass(String pass, String newPass, Authentication authentication) {
        User user = this.iUserRepository.findByAccountAndDeleteStatusIsFalse(authentication.getName());
            user.setPassword(passwordEncoder.encode(newPass));
            this.iUserRepository.save(user);
            return true;
    }

    @Override
    public User findUserByUserName(String name) {
        return iUserRepository.findByAccountAndDeleteStatusIsFalse(name);
    }
}
