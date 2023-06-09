package com.example.coffee.coupons.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "coupons")
public class Coupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code_coupons",nullable = false)
    private String codeCoupons;
    @Column(name = "value", columnDefinition = "FLOAT DEFAULT 1")
    private Float value;
    @Column(name = "proviso", columnDefinition = "FLOAT DEFAULT 100")
    private Float proviso;
    @CreationTimestamp
    @Column(name = "begin_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDateTime beginTime;
    @CreationTimestamp
    @Column(name = "end_time", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDateTime endTime;
    @CreationTimestamp
    @Column(name = "create_time", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDateTime createTime;

    @Column(name = "update_time", columnDefinition = "TIMESTAMP DEFAULT now()")
    @UpdateTimestamp
    private LocalDateTime updateTime;
    @Column(name = "status_delete")
    private boolean deleteStatus;

    public Coupons() {
    }

    public Coupons(Integer id, String codeCoupons, Float value, Float proviso, LocalDateTime beginTime, LocalDateTime endTime, LocalDateTime createTime, LocalDateTime updateTime, boolean deleteStatus) {
        this.id = id;
        this.codeCoupons = codeCoupons;
        this.value = value;
        this.proviso = proviso;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
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
