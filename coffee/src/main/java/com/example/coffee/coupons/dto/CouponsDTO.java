package com.example.coffee.coupons.dto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CouponsDTO implements Validator {
    private Integer id;
    @NotBlank(message = "Can't be left blank")
    private String codeCoupons;
    @NotBlank(message = "Can't be left blank")
    private Float value;
    @NotBlank(message = "Can't be left blank")
    private Float proviso;
    @NotBlank(message = "Can't be left blank")
    private LocalDateTime beginTime;
    @NotBlank(message = "Can't be left blank")
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

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getProviso() {
        return proviso;
    }

    public void setProviso(Float proviso) {
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
        String regexCodeCoupons = "^[A-Z0-9]{10,10}$";
        CouponsDTO couponsDTO =(CouponsDTO) target;
        if (!couponsDTO.codeCoupons.matches(regexCodeCoupons)){
            errors.rejectValue("codeCoupons", "", "Can't be less than 0 and contain only uppercase letters and numbers");
        }
       if (!(couponsDTO.value>0)){
           errors.rejectValue("value", "", "Not less than 0");
       }
        if (!(couponsDTO.proviso>0)){
            errors.rejectValue("proviso", "", "Not less than 0");
        }

    }
}
