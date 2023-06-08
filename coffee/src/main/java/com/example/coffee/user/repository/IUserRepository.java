package com.example.coffee.user.repository;

import com.example.coffee.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User,Integer> {
    User findByAccountAndDeleteStatusIsFalse(String account);

}
