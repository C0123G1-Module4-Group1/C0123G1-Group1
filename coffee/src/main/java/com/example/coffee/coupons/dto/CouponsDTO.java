package com.example.coffee.coupons.dto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CouponsDTO implements Validator {
    private Integer id;
    @NotBlank(message = "cannot be left blank")
    private String codeCoupons;
    @NotNull(message = "cannot be left blank")
    @DecimalMin(value = "0.01", message = "Value cannot be 0%")
    @DecimalMax(value ="1.0",message = "Value should not be more than 100%" )
    private Float valuee;
    @NotNull(message = "cannot be left blank")
    @DecimalMin(value = "0.01", message = "Value cannot be 0%")
    private Float proviso;
    private LocalDate beginTime;
    private LocalDate endTime;

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

    public Float getValuee() {
        return valuee;
    }

    public void setValuee(Float valuee) {
        this.valuee = valuee;
    }

    public Float getProviso() {
        return proviso;
    }

    public void setProviso(Float proviso) {
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
        String regexCodeCoupons = "^[A-Z0-9]{1,10}$";
        CouponsDTO couponsDTO =(CouponsDTO) target;
        if (!couponsDTO.codeCoupons.matches(regexCodeCoupons)){
            errors.rejectValue("codeCoupons", "", "Can't be less than 0 and contain only uppercase letters and numbers");
        }
    }
}
