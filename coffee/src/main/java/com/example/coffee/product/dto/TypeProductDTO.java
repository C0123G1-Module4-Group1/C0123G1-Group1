package com.example.coffee.product.dto;

public class TypeProductDTO {
    private  Integer id;

    private String typeName;
    private boolean status;


    public TypeProductDTO() {
    }

    public TypeProductDTO(Integer id, String typeName, boolean status) {
        this.id = id;
        this.typeName = typeName;
        this.status = status;
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
