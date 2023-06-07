package com.example.coffee.product.dto;

public class SizeProductDTO {
    private  Integer id;
    private String size;

    public SizeProductDTO() {
    }

    public SizeProductDTO(Integer id, String size) {
        this.id = id;
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
