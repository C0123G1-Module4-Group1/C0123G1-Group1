package com.example.coffee.user.dto;

import com.example.coffee.user.model.Role;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto  {
    private Integer id;
    @NotBlank(message = "cannot be left blank")
    private String account;
    @NotBlank(message = "cannot be left blank")
    @Size(min = 5, max = 100, message = "Password should not be more than 100 characters or less than 5 characters!")
    private String password;
    private Boolean deleteStatus;
    private Role role;

    public UserDto() {
    }

    public UserDto(String account, String password, Role role) {
        this.account = account;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
