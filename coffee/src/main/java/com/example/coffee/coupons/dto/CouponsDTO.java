package com.example.coffee.coupons.dto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class CouponsDTO implements Validator {
    private Integer id;
    private String codeCoupons;
    @NotNull(message = "cannot be left blank")
    @Min(value = 1, message = "cannot be less than or less equal 0%")
    @Max(value =100,message = "cannot be more than 100%" )
    private Integer valuee;
    @NotNull(message = "cannot be left blank")
    @Min(value = 0,message = "cannot be less than 0")
    private Integer proviso;
    @NotNull(message = "cannot be left blank")
    @FutureOrPresent(message = "Current or future date and time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime beginTime;
    @NotNull(message = "cannot be left blank")
    @Future(message="Future date and time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private boolean deleteStatus;


    public CouponsDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeCoupons() {
        return codeCoupons;
    }

    public void setCodeCoupons(String codeCoupons) {
        this.codeCoupons = codeCoupons;
    }

    public Integer getValuee() {
        return valuee;
    }

    public void setValuee(Integer valuee) {
        this.valuee = valuee;
    }

    public Integer getProviso() {
        return proviso;
    }

    public void setProviso(Integer proviso) {
        this.proviso = proviso;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
    @Override
    public void validate(Object target, Errors errors) {
    }
}
