package com.example.coffee.order.service;

import com.example.coffee.order.model.StatusOrder;

public interface IStatusOrderService {
    StatusOrder findById(int id);
}
