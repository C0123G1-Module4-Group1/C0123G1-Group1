package com.example.coffee.coupons.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name ="coupons")
@EntityListeners(AuditingEntityListener.class)
public class Coupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code_coupons")
    private String codeCoupons;
    @Column(columnDefinition = "FLOAT DEFAULT 1.0")
    private Float valuee;
    @Column(name = "proviso",columnDefinition = "FLOAT DEFAULT 1.0")
    private Float proviso;
    @Column(name = "begin_time")
    private LocalDate beginTime;
    @Column(name = "end_time")
    private LocalDate endTime;
    @CreationTimestamp
    @Column(name = "create_time", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDate createTime;
    @Column(name = "update_time", columnDefinition = "TIMESTAMP DEFAULT now()")
    @UpdateTimestamp
    private LocalDate updateTime;
    @Column(name = "delete_status")
    private boolean deleteStatus;


    public Coupons() {
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

    public void setValuee(Float value) {
        this.valuee = value;
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
