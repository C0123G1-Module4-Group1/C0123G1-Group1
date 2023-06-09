package com.example.coffee.customer.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CustomerDTO {

    private Integer id;

    @NotBlank(message = "Không được để trống")
    private String name;
    @NotBlank(message = "Không được để trống")
    private String phoneNumber;
    @NotBlank(message = "Không được để trống")
    private String email;
    @NotBlank(message = "Không được để trống")
    private String adress;

    private LocalDate createTime;

    private LocalDate updateTime;

    private boolean deleteStatus;

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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
