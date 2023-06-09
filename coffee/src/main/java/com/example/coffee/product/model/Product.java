package com.example.coffee.product.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column(name = "name_product",columnDefinition = "VARCHAR(50)",nullable = false)
     private  String name;
    @Column(name = "img",columnDefinition = "TEXT")
     private String image;
    @Column(name = "description",columnDefinition = "TEXT")
    private  String describes;
    @Column(name = "price",columnDefinition = "FLOAT")
    private Float price;
    @Column(name = "currency_unit",columnDefinition = "VARCHAR(10)")

    private String currency;
    @Column(name = "status_delete",columnDefinition = "BIT DEFAULT 0")
    private boolean status;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false, columnDefinition = "DATETIME", nullable = false)
     private LocalDateTime createTime;
    @UpdateTimestamp
    @Column(name = "update_time", updatable = false, columnDefinition = "DATETIME")
     private  LocalDateTime updateTime;
    @ManyToOne
    @JoinColumn(name = "id_type_product" ,referencedColumnName = "id")
     private TypeProduct typeProduct;
    @ManyToOne
    @JoinColumn(name = "id_size" ,referencedColumnName = "id")
    private SizeProduct sizeProduct;

    public Product() {
    }

    public Product(Integer id, String name, String image, String describes, Float price, String currency, boolean status, LocalDateTime createTime, LocalDateTime updateTime, TypeProduct typeProduct, SizeProduct sizeProduct) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.describes = describes;
        this.price = price;
        this.currency = currency;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.typeProduct = typeProduct;
        this.sizeProduct = sizeProduct;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public SizeProduct getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(SizeProduct sizeProduct) {
        this.sizeProduct = sizeProduct;
    }
}
