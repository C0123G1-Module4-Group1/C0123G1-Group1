package com.example.coffee.product.dto;

public class TypeProductDTO {
    private  Integer id;

    private String typeName;

    public TypeProductDTO(Integer id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public TypeProductDTO() {
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
