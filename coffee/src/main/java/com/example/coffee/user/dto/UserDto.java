package com.example.coffee.user.dto;

import com.example.coffee.user.model.Role;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto  {
    private Integer id;
    @NotBlank(message = "Tài khoản ko được để trống")
    private String account;
    @NotBlank(message = "Tài khoản ko được để trống")
    @Size(min = 5, max = 100, message = "Mật khẩu không được quá 100 kí tự hoặc dưới 5 kí tự!")
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
