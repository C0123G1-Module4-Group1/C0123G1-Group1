package com.example.coffee.order.service.impl;

import com.example.coffee.order.model.StatusOrder;
import com.example.coffee.order.repository.IStatusOrderRepository;
import com.example.coffee.order.service.IStatusOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusOrderService implements IStatusOrderService {
    @Autowired
    private IStatusOrderRepository statusOrderRepository;
    @Override
    public StatusOrder findById(int id) {
        return statusOrderRepository.findById(id).get();
    }
}
