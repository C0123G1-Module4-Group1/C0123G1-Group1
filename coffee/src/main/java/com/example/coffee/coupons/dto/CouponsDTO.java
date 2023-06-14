package com.example.coffee.coupons.dto;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CouponsDTO implements Validator {
    @NotBlank(message = "cannot be left blank")
    private String codeCoupons;
    @NotNull(message = "cannot be left blank")
    @Min(value = 1, message = "cannot be less than or less equal 0%")
    @Max(value =100,message = "cannot be more than 100%" )
    private Integer valuee;
    @NotNull(message = "cannot be left blank")
    @Min(value = 0,message = "cannot be less than 0")
    private Integer proviso;
    private LocalDate beginTime;
    private LocalDate endTime;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private boolean deleteStatus;


    public CouponsDTO() {
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

    public LocalDate getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDate beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
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
//        String regexCodeCoupons = "^[A-Z0-9]{1,10}$";
//        CouponsDTO couponsDTO =(CouponsDTO) target;
//        if (!couponsDTO.codeCoupons.matches(regexCodeCoupons)){
//            errors.rejectValue("codeCoupons", "", "Can't be less than 0 and contain only uppercase letters and numbers");
//        }
    }
}
