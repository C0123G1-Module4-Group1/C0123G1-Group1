package com.example.coffee.shopping_cart.model;

public class CartOnline {
    private Integer idProduct;
    private String nameProduct;
    private String img;
    private Float price;

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    private String sizeName;
    private Float sizeRate;
    private Integer quantity;
    private String email;

    public Integer getIdProduct() {
        return idProduct;
    }

    public CartOnline(Integer idProduct, String nameProduct, String img, Float price, String sizeName, Float sizeRate, Integer quantity, String email) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.img = img;
        this.price = price;
        this.sizeName = sizeName;
        this.sizeRate = sizeRate;
        this.quantity = quantity;
        this.email = email;
    }

    public CartOnline() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Float getSizeRate() {
        return sizeRate;
    }

    public void setSizeRate(Float sizeRate) {
        this.sizeRate = sizeRate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
