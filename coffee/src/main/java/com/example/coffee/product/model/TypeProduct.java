package com.example.coffee.product.model;

import javax.persistence.*;

@Entity(name = "type_product")
public class TypeProduct {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(name = "type_name", columnDefinition = "VARCHAR(50)", nullable = false)
        private String typeName;

        public TypeProduct() {
        }

        public TypeProduct(Integer id, String typeName) {
            this.id = id;
            this.typeName = typeName;
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