package com.example.coffee.product.dto;


import com.example.coffee.order.model.SizeProduct;
import com.example.coffee.product.model.TypeProduct;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class ProductDTO {
    private  Integer id;
    private  String name;
    private MultipartFile image;
    private  String describes;
    private LocalDateTime createTime;
    private  LocalDateTime updateTime;
    private TypeProduct typeProduct;
    private SizeProduct sizeProduct;
    private Float price;
    private String currency;
    private boolean status;


    public ProductDTO() {
    }

    public ProductDTO(Integer id, String name, MultipartFile image, String describes, LocalDateTime createTime, LocalDateTime updateTime, TypeProduct typeProduct, SizeProduct sizeProduct, Float price, String currency, boolean status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.describes = describes;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.typeProduct = typeProduct;
        this.sizeProduct = sizeProduct;
        this.price = price;
        this.currency = currency;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public SizeProduct getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(SizeProduct sizeProduct) {
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }
}
