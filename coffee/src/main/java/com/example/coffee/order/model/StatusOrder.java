package com.example.coffee.order.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
@Entity
@Table(name = "status_order")
public class StatusOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "status", nullable = false, columnDefinition = "TEXT")
    private String status;

    public StatusOrder(Integer id, String status) {
        this.id = id;
        this.status = status;
    }
    public StatusOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
