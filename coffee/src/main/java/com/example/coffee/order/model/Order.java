package com.example.coffee.order.model;

import com.example.coffee.coupons.model.Coupons;
import com.example.coffee.customer.model.Customer;
import com.example.coffee.staff.model.Staff;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code_order", columnDefinition = "VARCHAR(10)")
    private String codeOrder;
    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private Staff staff;
    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "id_coupons", referencedColumnName = "id")
    private Coupons coupons;
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private StatusOrder status;
    @CreationTimestamp
    @Column(name = "create_time", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDateTime createTime;

    @Column(name = "update_time", columnDefinition = "TIMESTAMP DEFAULT now()")
    @UpdateTimestamp
    private LocalDateTime updateTime;
    @Column(name = "status_delete")
    private boolean deleteStatus;

    public Order(String codeOrder, Staff staff, Customer customer, Coupons coupons, StatusOrder status, String note) {
        this.codeOrder = codeOrder;
        this.staff = staff;
        this.customer = customer;
        this.coupons = coupons;
        this.status = status;
        this.note = note;
    }

    public Order(Staff staff, Customer customer, Coupons coupons, StatusOrder status, String note) {
        this.staff = staff;
        this.customer = customer;
        this.coupons = coupons;
        this.status = status;
        this.note = note;
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Coupons getCoupons() {
        return coupons;
    }

    public void setCoupons(Coupons coupons) {
        this.coupons = coupons;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
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

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Order(Integer id, Staff staff, Customer customer, Coupons coupons, String note, StatusOrder status, LocalDateTime createTime, LocalDateTime updateTime, boolean deleteStatus) {
        this.id = id;
        this.staff = staff;
        this.customer = customer;
        this.coupons = coupons;
        this.note = note;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
    }

    public Order() {
    }
}
