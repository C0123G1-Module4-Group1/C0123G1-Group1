package com.example.coffee.staff.dto;

import com.example.coffee.user.dto.UserDto;
import com.example.coffee.user.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class StaffDto implements Validator {
    private Integer id;
    @NotBlank(message = "cannot be left blank")
    private String name;
    @NotBlank(message = "cannot be left blank")
    private String address;
    @NotBlank(message = "cannot be left blank")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number must have 10 digits, starting with 0")
    private String phoneNumber;
    @Email(message = "Must be in correct email format(*@gmail.com)")
    @NotBlank(message = "cannot be left blank")
    private String email;
    private UserDto userDto;
    private Boolean deleteStatus;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public StaffDto() {
    }

    public StaffDto(Integer id, String name, String address, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public StaffDto(Integer id, String name, String address, String phoneNumber, String email, UserDto userDto, Boolean deleteStatus, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userDto = userDto;
        this.deleteStatus = deleteStatus;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
