package com.example.coffee.customer.dto;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CustomerDTO implements Validator {
    private Integer id;

    @NotBlank(message = "cannot be left blank")
    private String name;
    @NotBlank(message = "cannot be left blank")
    private String phoneNumber;
    @NotBlank(message = "cannot be left blank")
    private String email;
    @NotBlank(message = "cannot be left blank")
    private String address;

    private LocalDate createTime;

    private LocalDate updateTime;

    private boolean statusDelete;

    public CustomerDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isStatusDelete() {
        return statusDelete;
    }

    public void setStatusDelete(boolean statusDelete) {
        this.statusDelete = statusDelete;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String regexNameCustomer = "^\\p{Lu}\\p{Ll}*(\\s\\p{Lu}\\p{Ll}*)*$";
        String regexEmailCustomer = "^[a-z]\\w{5,}\\@[a-z]{3,5}\\.[a-z]{2,5}$";
        String regexPhoneNumberCustomer = "^((\\+84)|0)[0-9]{9,10}$";
        CustomerDTO customerDTO =(CustomerDTO) target;
        if (!customerDTO.name.matches(regexNameCustomer)){
            errors.rejectValue("name", "", "Can't be less than 0 and contain only uppercase letters and numbers");
        }
        if (!customerDTO.email.matches(regexEmailCustomer)){
            errors.rejectValue("email", "", "Email must be at least 6 characters and .com,@");
        }
        if (!customerDTO.phoneNumber.matches(regexPhoneNumberCustomer)){
            errors.rejectValue("phoneNumber", "", "Phone number must start from 0 or +84 and have 9 or 10 numbers");
        }
    }
}
