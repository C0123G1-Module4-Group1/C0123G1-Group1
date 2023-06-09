package com.example.coffee.order.dto;

import com.example.coffee.coupons.model.Coupons;
import com.example.coffee.customer.model.Customer;
import com.example.coffee.order.model.StatusOrder;
import com.example.coffee.staff.model.Staff;
import java.time.LocalDateTime;

public class OrderDTO {
    private Integer id;

    private Staff staff;
    private Customer customer;

    private Coupons coupons;

    private String note;

    private StatusOrder status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private boolean deleteStatus;
    private String totalPrice;

    public OrderDTO() {
    }

    public OrderDTO(Integer id, Staff staff, Customer customer, Coupons coupons, String note, StatusOrder status, LocalDateTime createTime, LocalDateTime updateTime, boolean deleteStatus, String totalPrice) {
        this.id = id;
        this.staff = staff;
        this.customer = customer;
        this.coupons = coupons;
        this.note = note;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteStatus = deleteStatus;
        this.totalPrice = totalPrice;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
