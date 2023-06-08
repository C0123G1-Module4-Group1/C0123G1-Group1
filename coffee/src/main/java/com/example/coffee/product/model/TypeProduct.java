package com.example.coffee.product.model;

import javax.persistence.*;

@Entity(name = "type_product")
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String typeName;
    @Column(name = "status_delete", columnDefinition = "BIT")
    private boolean status;

    public TypeProduct() {
    }

    public TypeProduct(Integer id, String typeName, boolean status) {
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