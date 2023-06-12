package com.example.coffee.order.dto;

import com.example.coffee.order.model.Order;
import com.example.coffee.order.model.SizeProduct;
import com.example.coffee.product.model.Product;
import java.time.LocalDateTime;

public class OrderDetailDTO {
    private Integer id;

    private Order order;

    private Product product;

    private SizeProduct sizeProduct;

    private Float priceProduct;

    private Integer quantity;

    private LocalDateTime createTime;


    private LocalDateTime updateTime;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(Integer id, Order order, Product product, SizeProduct sizeProduct, Float priceProduct, Integer quantity, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.sizeProduct = sizeProduct;
        this.priceProduct = priceProduct;
        this.quantity = quantity;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Float priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}
