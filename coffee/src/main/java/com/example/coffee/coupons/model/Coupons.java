package com.example.coffee.coupons.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name ="coupons")
@EntityListeners(AuditingEntityListener.class)
public class Coupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code_coupons",columnDefinition = "VARCHAR(8)")
    private String codeCoupons;
    @Column(columnDefinition = "INT DEFAULT 1")
    private Integer valuee;
    private Integer proviso;
    @Column(name = "begin_time")
    private LocalDateTime beginTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
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

    public Integer getValuee() {
        return valuee;
    }

    public void setValuee(Integer value) {
        this.valuee = value;
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
