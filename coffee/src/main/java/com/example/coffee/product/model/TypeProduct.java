package com.example.coffee.product.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "type_product")
@EntityListeners(AuditingEntityListener.class)
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String typeName;
    @Column(name = "status_delete", columnDefinition = "BIT")
    private boolean status;
    @CreationTimestamp
    @Column(name = "create_time", updatable = false, columnDefinition = "DATETIME")
    private LocalDateTime createTime;
    @UpdateTimestamp
    @Column(name = "update_time", updatable = false, columnDefinition = "DATETIME")
    private  LocalDateTime updateTime;

    public TypeProduct() {
    }

    public TypeProduct(Integer id, String typeName, boolean status, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.typeName = typeName;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}