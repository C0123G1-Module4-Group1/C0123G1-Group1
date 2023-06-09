package com.example.coffee.order.service;

import com.example.coffee.coupons.model.Coupons;
import com.example.coffee.customer.model.Customer;
import com.example.coffee.order.model.StatusOrder;
import com.example.coffee.staff.model.Staff;

import java.time.LocalDateTime;

public interface OrderProjiection {
    Integer getId();
    Staff getStaff();
    Customer getCustomer();
    Coupons getCoupons();
    String getNote();
    StatusOrder getStatus();
    LocalDateTime getCreateTime();
    LocalDateTime getUpdateTime();
    boolean getDeleteStatus();
    String getTotalPrice();
}
