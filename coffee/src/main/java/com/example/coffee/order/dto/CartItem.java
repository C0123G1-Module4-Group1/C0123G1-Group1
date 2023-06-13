package com.example.coffee.order.dto;

public class CartItem {
    private Integer id;
    private Integer idProduct;
    private String nameProduct;
    private String sizeProduct;
    private Float sizeRate;
    private Float priceProduct;
    private Integer quantity;
    public CartItem() {
    }

    public CartItem(Integer id, Integer idProduct, String nameProduct, String sizeProduct, Float sizeRate, Float priceProduct, Integer quantity) {
        this.id = id;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.sizeProduct = sizeProduct;
        this.sizeRate = sizeRate;
        this.priceProduct = priceProduct;
        this.quantity = quantity;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(String sizeProduct) {
        this.sizeProduct = sizeProduct;
    }

    public Float getSizeRate() {
        return sizeRate;
    }

    public void setSizeRate(Float sizeRate) {
        this.sizeRate = sizeRate;
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
}
