package com.example.coffee.coupons.dto;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CouponsDTO {
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
}
